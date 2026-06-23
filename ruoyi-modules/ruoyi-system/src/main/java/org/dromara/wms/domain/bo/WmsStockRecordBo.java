package org.dromara.wms.domain.bo;

import org.dromara.wms.domain.WmsStockRecord;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 库存流水业务对象 wms_stock_record
 *
 * @author chensheng
 * @date 2026-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsStockRecord.class, reverseConvertGenerate = false)
public class WmsStockRecordBo extends BaseEntity {

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
     * 变动类型(IN/OUT)
     */
    @NotBlank(message = "变动类型(IN/OUT)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String changeType;

    /**
     * 变动数量
     */
    @NotNull(message = "变动数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long quantity;

    /**
     * 变动前库存
     */
    @NotNull(message = "变动前库存不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long beforeQty;

    /**
     * 变动后库存
     */
    @NotNull(message = "变动后库存不能为空", groups = { AddGroup.class, EditGroup.class })
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
