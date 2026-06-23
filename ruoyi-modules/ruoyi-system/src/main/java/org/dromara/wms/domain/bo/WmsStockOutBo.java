package org.dromara.wms.domain.bo;

import org.dromara.wms.domain.WmsStockOut;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 出库管理业务对象 wms_stock_out
 *
 * @author chensheng
 * @date 2026-06-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsStockOut.class, reverseConvertGenerate = false)
public class WmsStockOutBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;


    /**
     * 商品名称
     */
    private String productName;

    /**
     * 出库数量
     */
    @NotNull(message = "出库数量不能为空", groups = { AddGroup.class, EditGroup.class })
    @Min(value = 0, message = "出库数量不能小于0", groups = { AddGroup.class, EditGroup.class })
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
