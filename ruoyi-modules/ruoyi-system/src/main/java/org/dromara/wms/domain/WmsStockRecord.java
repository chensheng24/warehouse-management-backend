package org.dromara.wms.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 库存流水对象 wms_stock_record
 *
 * @author chensheng
 * @date 2026-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_stock_record")
public class WmsStockRecord extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 变动类型(IN/OUT)
     */
    private String changeType;

    /**
     * 变动数量
     */
    private Long quantity;

    /**
     * 变动前库存
     */
    private Long beforeQty;

    /**
     * 变动后库存
     */
    private Long afterQty;

    /**
     * 业务ID(入库/出库单ID)
     */
    private Long bizId;

    /**
     * 业务类型(STOCK_IN/STOCK_OUT)
     */
    private String bizType;

    /**
     * 操作人
     */
    private String operator;


}
