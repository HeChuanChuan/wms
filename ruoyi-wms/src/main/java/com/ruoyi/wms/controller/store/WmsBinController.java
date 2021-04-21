package com.ruoyi.wms.controller.store;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.BinConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.wms.domain.WmsBin;
import com.ruoyi.wms.service.impl.WmsBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/4/21 16:46
 */
@RestController
@RequestMapping("/wms/bin")
public class WmsBinController extends BaseController {
    @Autowired
    private WmsBinService binService;

    @PreAuthorize("@ss.hasPermi('wms:bin:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmsBin bin) {
        startPage();
        List<WmsBin> list = binService.list(bin);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('wms:bin:add')")
    @Log(title = "库位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody WmsBin bin) {
        if (BinConstants.NOT_UNIQUE.equals(binService.checkBinCodeUnique(bin))) {
            return AjaxResult.error("新增库位'" + bin.getBinCode() + "'失败,库位编码已存在");
        } else if (BinConstants.NOT_UNIQUE.equals(binService.checkBinNameUnique(bin))) {
            return AjaxResult.error("新增库位'" + bin.getBinName() + "'失败,库位名称已存在");
        }
        bin.setCreateBy(SecurityUtils.getUsername());
        return toAjax(binService.insertBin(bin));
    }

    @PreAuthorize("@ss.hasPermi('wms:bin:edit')")
    @Log(title = "库位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody WmsBin bin) {
        if (BinConstants.NOT_UNIQUE.equals(binService.checkBinCodeUnique(bin))) {
            return AjaxResult.error("新增库位'" + bin.getBinCode() + "'失败,库位编码已存在");
        } else if (BinConstants.NOT_UNIQUE.equals(binService.checkBinNameUnique(bin))) {
            return AjaxResult.error("新增库位'" + bin.getBinName() + "'失败,库位名称已存在");
        }
        bin.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(binService.updateBin(bin));
    }

    @PreAuthorize("@ss.hasPermi('wms:bin:edit')")
    @Log(title = "库位管理", businessType = BusinessType.UPDATE)
    @PutMapping("changeStatus")
    public AjaxResult changeStatus(@RequestBody WmsBin bin) {
        bin.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(binService.updateBinStatus(bin));
    }

    @PreAuthorize("@ss.hasPermi('wms:bin:remove')")
    @Log(title = "库位管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(binService.deleteBinByIds(ids));
    }

    @Log(title = "库位管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('wms:bin:export')")
    @GetMapping("/export")
    public AjaxResult export(WmsBin bin)
    {
        List<WmsBin> list = binService.list(bin);
        ExcelUtil<WmsBin> util = new ExcelUtil<WmsBin>(WmsBin.class);
        return util.exportExcel(list, "库位数据");
    }
}
