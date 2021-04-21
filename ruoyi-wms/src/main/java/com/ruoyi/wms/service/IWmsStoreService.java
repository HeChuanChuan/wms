package com.ruoyi.wms.service;

import com.ruoyi.wms.domain.WmsStore;

import java.util.List;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/3/31 14:43
 */
public interface IWmsStoreService {
    /**
     * 根据条件分页查询仓库数据
     *
     * @param store
     * @return
     */
    List<WmsStore> list(WmsStore store);

    /**
     * 查询所有仓库
     *
     * @return
     */
    List<WmsStore> selectStoreAll();

    /**
     * 新增保存仓库信息
     *
     * @param store
     * @return
     */
    int insertStore(WmsStore store);

    /**
     * 修改仓库信息
     *
     * @param store
     * @return
     */
    int updateStore(WmsStore store);

    /**
     * 根据ID批量删除仓库信息
     *
     * @param ids
     * @return
     */
    int deleteStoreByIds(Long[] ids);

    /**
     * 修改仓库状态
     *
     * @param store
     * @return
     */
    int updateStoreStatus(WmsStore store);

    /**
     * 校验仓库名称是否唯一
     *
     * @param store
     * @return
     */
    String checkStoreNameUnique(WmsStore store);

    /**
     * 校验仓库编码是否唯一
     *
     * @param store
     * @return
     */
    String checkStoreCodeUnique(WmsStore store);
}
