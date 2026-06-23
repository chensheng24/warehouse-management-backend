package org.dromara.wms.domain.vo;

import org.dromara.wms.domain.WmsProduct;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 商品视图对象 wms_product
 *
 * @author chensheng
 * @date 2026-06-02
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsProduct.class)
public class WmsProductVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 商品编码
     */
    @ExcelProperty(value = "商品编码")
    private String productCode;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 分类
     */
    @ExcelProperty(value = "分类")
    private String category;

    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    private String unit;

    /**
     * 库存数量
     */
    @ExcelProperty(value = "库存数量")
    private Long stockQty;


}
