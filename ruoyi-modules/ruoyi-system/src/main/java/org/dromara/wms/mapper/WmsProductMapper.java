package org.dromara.wms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.wms.domain.WmsProduct;
import org.dromara.wms.domain.vo.WmsProductVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 商品Mapper接口
 *
 * @author chensheng
 * @date 2026-06-02
 */
@Mapper
public interface WmsProductMapper extends BaseMapperPlus<WmsProduct, WmsProductVo> {

}
