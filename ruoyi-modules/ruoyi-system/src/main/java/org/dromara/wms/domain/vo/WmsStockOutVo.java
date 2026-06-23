package org.dromara.wms.domain.vo;

import org.dromara.wms.domain.WmsStockOut;
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
 * 出库管理视图对象 wms_stock_out
 *
 * @author chensheng
 * @date 2026-06-16
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsStockOut.class)
public class WmsStockOutVo implements Serializable {

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
    private Long productId;


    /**
     * 商品名称
     **/
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 出库数量
     */
    @ExcelProperty(value = "出库数量")
    private Long quantity;

    /**
     * 客户
     */
    @ExcelProperty(value = "客户")
    private String customer;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;


}
