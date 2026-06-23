package org.dromara.wms.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 商品对象 wms_product
 *
 * @author chensheng
 * @date 2026-06-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_product")
public class WmsProduct extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id",type=IdType.AUTO)
    private Long id;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 分类
     */
    private String category;

    /**
     * 单位
     */
    private String unit;

    /**
     * 库存数量
     */
    private Long stockQty;

    /**
     * 备注
     */
    private String remark;


}
