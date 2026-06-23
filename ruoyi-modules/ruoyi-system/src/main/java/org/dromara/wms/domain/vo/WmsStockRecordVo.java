package org.dromara.wms.domain.vo;

import org.dromara.wms.domain.WmsStockRecord;
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
 * 库存流水视图对象 wms_stock_record
 *
 * @author chensheng
 * @date 2026-06-23
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsStockRecord.class)
public class WmsStockRecordVo implements Serializable {

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
     * 变动类型(IN/OUT)
     */
    @ExcelProperty(value = "变动类型(IN/OUT)")
    private String changeType;

    /**
     * 变动数量
     */
    @ExcelProperty(value = "变动数量")
    private Long quantity;

    /**
     * 变动前库存
     */
    @ExcelProperty(value = "变动前库存")
    private Long beforeQty;

    /**
     * 变动后库存
     */
    @ExcelProperty(value = "变动后库存")
    private Long afterQty;

    /**
     * 业务ID(入库/出库单ID)
     */
    @ExcelProperty(value = "业务ID(入库/出库单ID)")
    private Long bizId;

    /**
     * 业务类型(STOCK_IN/STOCK_OUT)
     */
    @ExcelProperty(value = "业务类型(STOCK_IN/STOCK_OUT)")
    private String bizType;

    /**
     * 操作人
     */
    @ExcelProperty(value = "操作人")
    private String operator;


}
