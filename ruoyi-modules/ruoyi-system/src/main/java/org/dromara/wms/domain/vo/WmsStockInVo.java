package org.dromara.wms.domain.vo;

import org.dromara.wms.domain.WmsStockIn;
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
 * 入库管理视图对象 wms_stock_in
 *
 * @author chensheng
 * @date 2026-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsStockIn.class)
public class WmsStockInVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private String productId;

    /**
     * 商品名称
     **/
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 入库数量
     */
    @ExcelProperty(value = "入库数量")
    private Long quantity;

    /**
     * 供应商
     */
    @ExcelProperty(value = "供应商")
    private String supplier;


}
