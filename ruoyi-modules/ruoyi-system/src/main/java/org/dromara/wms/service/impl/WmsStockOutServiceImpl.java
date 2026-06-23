package org.dromara.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.wms.domain.WmsProduct;
import org.dromara.wms.domain.WmsStockIn;
import org.dromara.wms.domain.vo.WmsStockInVo;
import org.dromara.wms.mapper.WmsProductMapper;
import org.springframework.stereotype.Service;
import org.dromara.wms.domain.bo.WmsStockOutBo;
import org.dromara.wms.domain.vo.WmsStockOutVo;
import org.dromara.wms.domain.WmsStockOut;
import org.dromara.wms.mapper.WmsStockOutMapper;
import org.dromara.wms.service.IWmsStockOutService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 出库管理Service业务层处理
 *
 * @author chensheng
 * @date 2026-06-16
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class WmsStockOutServiceImpl implements IWmsStockOutService {

    private final WmsStockOutMapper baseMapper;

    private final WmsProductMapper productMapper;

    /**
     * 查询出库管理
     *
     * @param id 主键
     * @return 出库管理
     */
    @Override
    public WmsStockOutVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询出库管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 出库管理分页列表
     */
    @Override
    public TableDataInfo<WmsStockOutVo> queryPageList(WmsStockOutBo bo, PageQuery pageQuery) {
        Map<String, Object> params = bo.getParams();
        QueryWrapper<WmsStockOut> wrapper = Wrappers.query();
        wrapper
            .like(StringUtils.isNotBlank(bo.getCustomer()), "s.customer", (bo.getCustomer()))
            .like(StringUtils.isNotBlank(bo.getProductName()), "p.product_name", bo.getProductName())
            .eq(bo.getQuantity() != null, "s.quantity", bo.getQuantity())
//            .like(StringUtils.isNotBlank(user.getPhonenumber()), "u.phonenumber", user.getPhonenumber())
//            .between(params.get("beginTime") != null && params.get("endTime") != null,
//                "u.create_time", params.get("beginTime"), params.get("endTime"))
//            .and(ObjectUtil.isNotNull(bo.getDeptId()), w -> {
//                List<Long> deptIds = deptMapper.selectDeptAndChildById(user.getDeptId());
//                w.in("u.dept_id", deptIds);
//            })
            .orderByAsc("s.create_time");

        Page<WmsStockOutVo> result = baseMapper.selectCustomPage(pageQuery.build(), wrapper);
        return TableDataInfo.build(result);

    }

    /**
     * 查询符合条件的出库管理列表
     *
     * @param bo 查询条件
     * @return 出库管理列表
     */
    @Override
    public List<WmsStockOutVo> queryList(WmsStockOutBo bo) {
        LambdaQueryWrapper<WmsStockOut> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsStockOut> buildQueryWrapper(WmsStockOutBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsStockOut> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsStockOut::getId);
        lqw.eq(bo.getProductId() != null, WmsStockOut::getProductId, bo.getProductId());
        lqw.eq(bo.getQuantity() != null, WmsStockOut::getQuantity, bo.getQuantity());
        lqw.like(StringUtils.isNotBlank(bo.getCustomer()), WmsStockOut::getCustomer, bo.getCustomer());
        return lqw;
    }

    /**
     * 新增出库管理
     *
     * @param bo 出库管理
     * @return 是否新增成功
     */
    @Override
    @Transactional
    public Boolean insertByBo(WmsStockOutBo bo) {
        WmsProduct product =
            productMapper.selectById(bo.getProductId());

        if(product.getStockQty() < bo.getQuantity()) {
            throw new ServiceException("库存不足");
        }

        WmsStockOut add = MapstructUtils.convert(bo, WmsStockOut.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        product.setStockQty(
            product.getStockQty()
                - add.getQuantity()
        );

        productMapper.updateById(product);
        return flag;
    }

    /**
     * 修改出库管理
     *
     * @param bo 出库管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsStockOutBo bo) {
        WmsStockOut update = MapstructUtils.convert(bo, WmsStockOut.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsStockOut entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除出库管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }


}
