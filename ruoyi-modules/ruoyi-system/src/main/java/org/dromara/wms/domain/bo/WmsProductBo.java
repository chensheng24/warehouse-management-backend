package org.dromara.wms.domain.bo;

import org.dromara.wms.domain.WmsProduct;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品业务对象 wms_product
 *
 * @author chensheng
 * @date 2026-06-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsProduct.class, reverseConvertGenerate = false)
public class WmsProductBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品编码
     */
    @NotBlank(message = "商品编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productCode;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
