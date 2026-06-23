package org.dromara.wms.service;

import org.dromara.wms.domain.vo.WmsStockInVo;
import org.dromara.wms.domain.bo.WmsStockInBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 入库管理Service接口
 *
 * @author chensheng
 * @date 2026-06-03
 */
public interface IWmsStockInService {

    /**
     * 查询入库管理
     *
     * @param id 主键
     * @return 入库管理
     */
    WmsStockInVo queryById(Long id);

    /**
     * 分页查询入库管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 入库管理分页列表
     */
    TableDataInfo<WmsStockInVo> queryPageList(WmsStockInBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的入库管理列表
     *
     * @param bo 查询条件
     * @return 入库管理列表
     */
    List<WmsStockInVo> queryList(WmsStockInBo bo);

    /**
     * 新增入库管理
     *
     * @param bo 入库管理
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsStockInBo bo);

    /**
     * 修改入库管理
     *
     * @param bo 入库管理
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsStockInBo bo);

    /**
     * 校验并批量删除入库管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


}
