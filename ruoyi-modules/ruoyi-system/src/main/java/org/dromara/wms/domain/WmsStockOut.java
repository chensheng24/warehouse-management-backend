package org.dromara.wms.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 出库管理对象 wms_stock_out
 *
 * @author chensheng
 * @date 2026-06-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_stock_out")
public class WmsStockOut extends TenantEntity {

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
     * 出库数量
     */
    private Long quantity;

    /**
     * 客户
     */
    private String customer;

    /**
     *
     */
    private String remark;


}
