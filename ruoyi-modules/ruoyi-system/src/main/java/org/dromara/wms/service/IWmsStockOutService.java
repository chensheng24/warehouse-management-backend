package org.dromara.wms.service;

import org.dromara.wms.domain.vo.WmsStockOutVo;
import org.dromara.wms.domain.bo.WmsStockOutBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 出库管理Service接口
 *
 * @author chensheng
 * @date 2026-06-16
 */
public interface IWmsStockOutService {

    /**
     * 查询出库管理
     *
     * @param id 主键
     * @return 出库管理
     */
    WmsStockOutVo queryById(Long id);

    /**
     * 分页查询出库管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 出库管理分页列表
     */
    TableDataInfo<WmsStockOutVo> queryPageList(WmsStockOutBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的出库管理列表
     *
     * @param bo 查询条件
     * @return 出库管理列表
     */
    List<WmsStockOutVo> queryList(WmsStockOutBo bo);

    /**
     * 新增出库管理
     *
     * @param bo 出库管理
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsStockOutBo bo);

    /**
     * 修改出库管理
     *
     * @param bo 出库管理
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsStockOutBo bo);

    /**
     * 校验并批量删除出库管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
