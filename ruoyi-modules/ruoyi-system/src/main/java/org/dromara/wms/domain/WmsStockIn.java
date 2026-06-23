package org.dromara.wms.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 入库管理对象 wms_stock_in
 *
 * @author chensheng
 * @date 2026-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_stock_in")
public class WmsStockIn extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 入库数量
     */
    private Long quantity;

    /**
     * 供应商
     */
    private String supplier;

    /**
     *
     */
    private String remark;


}
