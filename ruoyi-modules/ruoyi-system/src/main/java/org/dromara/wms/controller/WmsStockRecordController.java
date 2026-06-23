package org.dromara.wms.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.wms.domain.vo.WmsStockRecordVo;
import org.dromara.wms.domain.bo.WmsStockRecordBo;
import org.dromara.wms.service.IWmsStockRecordService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 库存流水
 *
 * @author chensheng
 * @date 2026-06-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/stockRecord")
public class WmsStockRecordController extends BaseController {

    private final IWmsStockRecordService wmsStockRecordService;

    /**
     * 查询库存流水列表
     */
    @SaCheckPermission("wms:stockRecord:list")
    @GetMapping("/list")
    public TableDataInfo<WmsStockRecordVo> list(WmsStockRecordBo bo, PageQuery pageQuery) {
        return wmsStockRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库存流水列表
     */
    @SaCheckPermission("wms:stockRecord:export")
    @Log(title = "库存流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsStockRecordBo bo, HttpServletResponse response) {
        List<WmsStockRecordVo> list = wmsStockRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "库存流水", WmsStockRecordVo.class, response);
    }

    /**
     * 获取库存流水详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:stockRecord:query")
    @GetMapping("/{id}")
    public R<WmsStockRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(wmsStockRecordService.queryById(id));
    }

    /**
     * 新增库存流水
     */
    @SaCheckPermission("wms:stockRecord:add")
    @Log(title = "库存流水", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsStockRecordBo bo) {
        return toAjax(wmsStockRecordService.insertByBo(bo));
    }

    /**
     * 修改库存流水
     */
    @SaCheckPermission("wms:stockRecord:edit")
    @Log(title = "库存流水", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsStockRecordBo bo) {
        return toAjax(wmsStockRecordService.updateByBo(bo));
    }

    /**
     * 删除库存流水
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wms:stockRecord:remove")
    @Log(title = "库存流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(wmsStockRecordService.deleteWithValidByIds(List.of(ids), true));
    }
}
