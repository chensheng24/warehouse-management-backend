package org.dromara.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dromara.system.domain.SysUser;
import org.dromara.wms.domain.WmsStockIn;
import org.dromara.wms.domain.bo.WmsStockInBo;
import org.dromara.wms.domain.vo.WmsStockInVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 入库管理Mapper接口
 *
 * @author chensheng
 * @date 2026-06-03
 */
@Mapper
public interface WmsStockInMapper extends BaseMapperPlus<WmsStockIn, WmsStockInVo> {
    Page<WmsStockInVo> selectCustomPage(@Param("page") Page<WmsStockInVo> page, @Param(Constants.WRAPPER) Wrapper<WmsStockIn> queryWrapper);
}
