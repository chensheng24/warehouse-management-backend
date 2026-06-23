package org.dromara.wms.service;

import org.dromara.wms.domain.vo.WmsStockRecordVo;
import org.dromara.wms.domain.bo.WmsStockRecordBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 库存流水Service接口
 *
 * @author chensheng
 * @date 2026-06-23
 */
public interface IWmsStockRecordService {

    /**
     * 查询库存流水
     *
     * @param id 主键
     * @return 库存流水
     */
    WmsStockRecordVo queryById(Long id);

    /**
     * 分页查询库存流水列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库存流水分页列表
     */
    TableDataInfo<WmsStockRecordVo> queryPageList(WmsStockRecordBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的库存流水列表
     *
     * @param bo 查询条件
     * @return 库存流水列表
     */
    List<WmsStockRecordVo> queryList(WmsStockRecordBo bo);

    /**
     * 新增库存流水
     *
     * @param bo 库存流水
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsStockRecordBo bo);

    /**
     * 修改库存流水
     *
     * @param bo 库存流水
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsStockRecordBo bo);

    /**
     * 校验并批量删除库存流水信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
