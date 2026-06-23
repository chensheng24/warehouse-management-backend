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
import org.dromara.wms.domain.vo.WmsStockOutVo;
import org.dromara.wms.domain.bo.WmsStockOutBo;
import org.dromara.wms.service.IWmsStockOutService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 出库管理
 *
 * @author chensheng
 * @date 2026-06-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/stockOut")
public class WmsStockOutController extends BaseController {

    private final IWmsStockOutService wmsStockOutService;

    /**
     * 查询出库管理列表
     */
    @SaCheckPermission("wms:stockOut:list")
    @GetMapping("/list")
    public TableDataInfo<WmsStockOutVo> list(WmsStockOutBo bo, PageQuery pageQuery) {
        return wmsStockOutService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出出库管理列表
     */
    @SaCheckPermission("wms:stockOut:export")
    @Log(title = "出库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsStockOutBo bo, HttpServletResponse response) {
        List<WmsStockOutVo> list = wmsStockOutService.queryList(bo);
        ExcelUtil.exportExcel(list, "出库管理", WmsStockOutVo.class, response);
    }

    /**
     * 获取出库管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:stockOut:query")
    @GetMapping("/{id}")
    public R<WmsStockOutVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(wmsStockOutService.queryById(id));
    }

    /**
     * 新增出库管理
     */
    @SaCheckPermission("wms:stockOut:add")
    @Log(title = "出库管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsStockOutBo bo) {
        return toAjax(wmsStockOutService.insertByBo(bo));
    }

    /**
     * 修改出库管理
     */
    @SaCheckPermission("wms:stockOut:edit")
    @Log(title = "出库管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsStockOutBo bo) {
        return toAjax(wmsStockOutService.updateByBo(bo));
    }

    /**
     * 删除出库管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wms:stockOut:remove")
    @Log(title = "出库管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(wmsStockOutService.deleteWithValidByIds(List.of(ids), true));
    }
}
