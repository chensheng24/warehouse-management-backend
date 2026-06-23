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
import org.dromara.wms.domain.bo.WmsStockRecordBo;
import org.dromara.wms.domain.vo.WmsStockRecordVo;
import org.dromara.wms.domain.WmsStockRecord;
import org.dromara.wms.mapper.WmsStockRecordMapper;
import org.dromara.wms.service.IWmsStockRecordService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 库存流水Service业务层处理
 *
 * @author chensheng
 * @date 2026-06-23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class WmsStockRecordServiceImpl implements IWmsStockRecordService {

    private final WmsStockRecordMapper baseMapper;

    /**
     * 查询库存流水
     *
     * @param id 主键
     * @return 库存流水
     */
    @Override
    public WmsStockRecordVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询库存流水列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库存流水分页列表
     */
    @Override
    public TableDataInfo<WmsStockRecordVo> queryPageList(WmsStockRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsStockRecord> lqw = buildQueryWrapper(bo);
        Page<WmsStockRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的库存流水列表
     *
     * @param bo 查询条件
     * @return 库存流水列表
     */
    @Override
    public List<WmsStockRecordVo> queryList(WmsStockRecordBo bo) {
        LambdaQueryWrapper<WmsStockRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsStockRecord> buildQueryWrapper(WmsStockRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsStockRecord> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsStockRecord::getId);
        lqw.eq(bo.getProductId() != null, WmsStockRecord::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getChangeType()), WmsStockRecord::getChangeType, bo.getChangeType());
        lqw.eq(bo.getQuantity() != null, WmsStockRecord::getQuantity, bo.getQuantity());
        lqw.eq(bo.getBeforeQty() != null, WmsStockRecord::getBeforeQty, bo.getBeforeQty());
        lqw.eq(bo.getAfterQty() != null, WmsStockRecord::getAfterQty, bo.getAfterQty());
        lqw.eq(bo.getBizId() != null, WmsStockRecord::getBizId, bo.getBizId());
        lqw.eq(StringUtils.isNotBlank(bo.getBizType()), WmsStockRecord::getBizType, bo.getBizType());
        lqw.eq(StringUtils.isNotBlank(bo.getOperator()), WmsStockRecord::getOperator, bo.getOperator());
        return lqw;
    }

    /**
     * 新增库存流水
     *
     * @param bo 库存流水
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsStockRecordBo bo) {
        WmsStockRecord add = MapstructUtils.convert(bo, WmsStockRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改库存流水
     *
     * @param bo 库存流水
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsStockRecordBo bo) {
        WmsStockRecord update = MapstructUtils.convert(bo, WmsStockRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsStockRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除库存流水信息
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
