package com.ruoyi.wms.controller.store;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StoreConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.wms.domain.WmsStore;
import com.ruoyi.wms.service.WmsStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description：仓库管理
 * Author：Hechuanchuan
 * Date：2021/3/31 14:10
 */
@RestController
@RequestMapping("/wms/store")
public class WmsStoreController extends BaseController {

    @Autowired
    private WmsStoreService storeService;

    @PreAuthorize("@ss.hasPermi('wms:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmsStore store) {
        startPage();
        List<WmsStore> list = storeService.list(store);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('wms:store:add')")
    @Log(title = "仓库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody WmsStore store) {
        if (StoreConstants.NOT_UNIQUE.equals(storeService.checkStoreCodeUnique(store))) {
            return AjaxResult.error("新增仓库'" + store.getStoreCode() + "'失败,仓库编码已存在");
        } else if (StoreConstants.NOT_UNIQUE.equals(storeService.checkStoreNameUnique(store))) {
            return AjaxResult.error("新增仓库'" + store.getStoreName() + "'失败,仓库名称已存在");
        }
        store.setCreateBy(SecurityUtils.getUsername());
        return toAjax(storeService.insertStore(store));
    }

    @PreAuthorize("@ss.hasPermi('wms:store:edit')")
    @Log(title = "仓库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody WmsStore store) {
        if (StoreConstants.NOT_UNIQUE.equals(storeService.checkStoreCodeUnique(store))) {
            return AjaxResult.error("新增仓库'" + store.getStoreCode() + "'失败,仓库编码已存在");
        } else if (StoreConstants.NOT_UNIQUE.equals(storeService.checkStoreNameUnique(store))) {
            return AjaxResult.error("新增仓库'" + store.getStoreName() + "'失败,仓库名称已存在");
        }
        store.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(storeService.updateStore(store));
    }

    @PreAuthorize("@ss.hasPermi('wms:store:edit')")
    @Log(title = "仓库管理", businessType = BusinessType.UPDATE)
    @PutMapping("changeStatus")
    public AjaxResult changeStatus(@RequestBody WmsStore store) {
        store.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(storeService.updateStoreStatus(store));
    }

    @PreAuthorize("@ss.hasPermi('wms:store:remove')")
    @Log(title = "仓库管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(storeService.deleteStoreByIds(ids));
    }

    @Log(title = "仓库管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('wms:store:export')")
    @GetMapping("/export")
    public AjaxResult export(WmsStore store)
    {
        List<WmsStore> list = storeService.list(store);
        ExcelUtil<WmsStore> util = new ExcelUtil<WmsStore>(WmsStore.class);
        return util.exportExcel(list, "仓库数据");
    }
}
