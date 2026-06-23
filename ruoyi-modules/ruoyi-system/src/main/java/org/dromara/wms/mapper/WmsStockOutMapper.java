package org.dromara.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.wms.domain.WmsStockIn;
import org.dromara.wms.domain.WmsStockOut;
import org.dromara.wms.domain.vo.WmsStockInVo;
import org.dromara.wms.domain.vo.WmsStockOutVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 出库管理Mapper接口
 *
 * @author chensheng
 * @date 2026-06-16
 */
public interface WmsStockOutMapper extends BaseMapperPlus<WmsStockOut, WmsStockOutVo> {
    Page<WmsStockOutVo> selectCustomPage(@Param("page") Page<WmsStockOutVo> page, @Param(Constants.WRAPPER) Wrapper<WmsStockOut> queryWrapper);
}
