package org.dromara.wms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.common.core.constant.SystemConstants;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.system.domain.SysUser;
import org.dromara.wms.domain.WmsProduct;
import org.dromara.wms.mapper.WmsProductMapper;
import org.springframework.stereotype.Service;
import org.dromara.wms.domain.bo.WmsStockInBo;
import org.dromara.wms.domain.vo.WmsStockInVo;
import org.dromara.wms.domain.WmsStockIn;
import org.dromara.wms.mapper.WmsStockInMapper;
import org.dromara.wms.service.IWmsStockInService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 入库管理Service业务层处理
 *
 * @author chensheng
 * @date 2026-06-03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class WmsStockInServiceImpl implements IWmsStockInService {

    private final WmsStockInMapper baseMapper;

    private final WmsProductMapper productMapper;

    /**
     * 查询入库管理
     *
     * @param id 主键
     * @return 入库管理
     */
    @Override
    public WmsStockInVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**s
     * 分页查询入库管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 入库管理分页列表
     */
    @Override
    public TableDataInfo<WmsStockInVo> queryPageList(WmsStockInBo bo, PageQuery pageQuery) {
        Map<String, Object> params = bo.getParams();
        QueryWrapper<WmsStockIn> wrapper = Wrappers.query();
        wrapper
            .like(StringUtils.isNotBlank(bo.getSupplier()), "s.supplier", (bo.getSupplier()))
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

        Page<WmsStockInVo> result = baseMapper.selectCustomPage(pageQuery.build(), wrapper);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的入库管理列表
     *
     * @param bo 查询条件
     * @return 入库管理列表
     */
    @Override
    public List<WmsStockInVo> queryList(WmsStockInBo bo) {
        LambdaQueryWrapper<WmsStockIn> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsStockIn> buildQueryWrapper(WmsStockInBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsStockIn> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, WmsStockIn::getId, bo.getId());
        lqw.orderByAsc(WmsStockIn::getId);
        lqw.eq(bo.getProductId() != null, WmsStockIn::getProductId, bo.getProductId());
        lqw.eq(bo.getQuantity() != null, WmsStockIn::getQuantity, bo.getQuantity());
        lqw.like(StringUtils.isNotBlank(bo.getSupplier()), WmsStockIn::getSupplier, bo.getSupplier());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark()), WmsStockIn::getRemark, bo.getRemark());
        return lqw;
    }

    /**
     * 新增入库管理
     *
     * @param bo 入库管理
     * @return 是否新增成功
     */
    @Override
    @Transactional
    public Boolean insertByBo(WmsStockInBo bo) {
        WmsStockIn add = MapstructUtils.convert(bo, WmsStockIn.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        WmsProduct product =
            productMapper.selectById(add.getProductId());

        product.setStockQty(
            product.getStockQty() + add.getQuantity()
        );

        productMapper.updateById(product);

        return flag;
    }

    /**
     * 修改入库管理
     *
     * @param bo 入库管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsStockInBo bo) {
        WmsStockIn update = MapstructUtils.convert(bo, WmsStockIn.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsStockIn entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除入库管理信息
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
