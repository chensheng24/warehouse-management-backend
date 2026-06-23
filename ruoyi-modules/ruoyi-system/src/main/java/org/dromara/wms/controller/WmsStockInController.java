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
import org.dromara.wms.domain.vo.WmsStockInVo;
import org.dromara.wms.domain.bo.WmsStockInBo;
import org.dromara.wms.service.IWmsStockInService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 入库管理
 *
 * @author chensheng
 * @date 2026-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/stockIn")
public class WmsStockInController extends BaseController {

    private final IWmsStockInService wmsStockInService;

    /**
     * 查询入库管理列表
     */
    @SaCheckPermission("wms:stockIn:list")
    @GetMapping("/list")
    public TableDataInfo<WmsStockInVo> list(WmsStockInBo bo, PageQuery pageQuery) {
        return wmsStockInService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出入库管理列表
     */
    @SaCheckPermission("wms:stockIn:export")
    @Log(title = "入库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsStockInBo bo, HttpServletResponse response) {
        List<WmsStockInVo> list = wmsStockInService.queryList(bo);
        ExcelUtil.exportExcel(list, "入库管理", WmsStockInVo.class, response);
    }

    /**
     * 获取入库管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:stockIn:query")
    @GetMapping("/{id}")
    public R<WmsStockInVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(wmsStockInService.queryById(id));
    }

    /**
     * 新增入库管理
     */
    @SaCheckPermission("wms:stockIn:add")
    @Log(title = "入库管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsStockInBo bo) {
        return toAjax(wmsStockInService.insertByBo(bo));
    }

    /**
     * 修改入库管理
     */
    @SaCheckPermission("wms:stockIn:edit")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsStockInBo bo) {
        return toAjax(wmsStockInService.updateByBo(bo));
    }

    /**
     * 删除入库管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wms:stockIn:remove")
    @Log(title = "入库管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(wmsStockInService.deleteWithValidByIds(List.of(ids), true));
    }
}
