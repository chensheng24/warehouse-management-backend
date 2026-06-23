package org.dromara.wms.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.wms.domain.bo.WmsProductBo;
import org.dromara.wms.domain.vo.WmsProductVo;
import org.dromara.wms.domain.WmsProduct;
import org.dromara.wms.mapper.WmsProductMapper;
import org.dromara.wms.service.IWmsProductService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品Service业务层处理
 *
 * @author chensheng
 * @date 2026-06-02
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class WmsProductServiceImpl implements IWmsProductService {

    private final WmsProductMapper baseMapper;

    /**
     * 查询商品
     *
     * @param id 主键
     * @return 商品
     */
    @Override
    public WmsProductVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询商品列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品分页列表
     */
    @Override
    public TableDataInfo<WmsProductVo> queryPageList(WmsProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsProduct> lqw = buildQueryWrapper(bo);
        Page<WmsProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品列表
     *
     * @param bo 查询条件
     * @return 商品列表
     */
    @Override
    public List<WmsProductVo> queryList(WmsProductBo bo) {
        LambdaQueryWrapper<WmsProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsProduct> buildQueryWrapper(WmsProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsProduct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, WmsProduct::getId, bo.getId());
        lqw.orderByAsc(WmsProduct::getId);
        lqw.like(StringUtils.isNotBlank(bo.getProductCode()), WmsProduct::getProductCode, bo.getProductCode());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), WmsProduct::getProductName, bo.getProductName());
        lqw.eq(StringUtils.isNotBlank(bo.getCategory()), WmsProduct::getCategory, bo.getCategory());
        lqw.eq(StringUtils.isNotBlank(bo.getUnit()), WmsProduct::getUnit, bo.getUnit());
        lqw.eq(bo.getStockQty() != null, WmsProduct::getStockQty, bo.getStockQty());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark()), WmsProduct::getRemark, bo.getRemark());
        return lqw;
    }

    /**
     * 新增商品
     *
     * @param bo 商品
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsProductBo bo) {
        WmsProduct add = MapstructUtils.convert(bo, WmsProduct.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品
     *
     * @param bo 商品
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsProductBo bo) {
        WmsProduct update = MapstructUtils.convert(bo, WmsProduct.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsProduct entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品信息
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
