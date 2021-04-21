package com.ruoyi.wms.mapper;

import com.ruoyi.wms.domain.WmsStore;

import java.util.List;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/3/31 16:40
 */
public interface WmsStoreMapper {

    List<WmsStore> selectStoreList(WmsStore store);

    List<WmsStore> selectStoreAll();

    int insertStore(WmsStore store);

    int updateStore(WmsStore store);

    int deleteStoreByIds(Long[] ids);

    int updateStoreStatus(WmsStore store);

    WmsStore checkStoreCodeUnique(String storeCode);

    WmsStore checkStoreNameUnique(String storeName);
}
