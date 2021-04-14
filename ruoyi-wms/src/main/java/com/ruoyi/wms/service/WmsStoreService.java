package com.ruoyi.wms.service;

import com.ruoyi.common.constant.StoreConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.wms.domain.WmsStore;
import com.ruoyi.wms.mapper.WmsStoreMapper;
import com.ruoyi.wms.mapper.WmsStoreMapper;
import com.ruoyi.wms.service.impl.IWmsStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/3/31 14:33
 */
@Service
public class WmsStoreService implements IWmsStoreService {

    @Autowired
    private WmsStoreMapper wmStoreMapper;

    /**
     * 根据条件分页查询仓库数据
     *
     * @param store
     * @return
     */
    @Override
    public List<WmsStore> list(WmsStore store) {
        return wmStoreMapper.selectStoreList(store);
    }

    /**
     * 新增保存仓库信息
     *
     * @param store
     * @return
     */
    @Override
    public int insertStore(WmsStore store) {
        return wmStoreMapper.insertStore(store);
    }

    /**
     * 修改仓库信息
     *
     * @param store
     * @return
     */
    @Override
    public int updateStore(WmsStore store) {
        return wmStoreMapper.updateStore(store);
    }

    /**
     * 根据ID批量删除仓库信息
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteStoreByIds(Long[] ids) {
        return wmStoreMapper.deleteStoreByIds(ids);
    }

    /**
     * 修改仓库状态
     *
     * @param store
     * @return
     */
    @Override
    public int updateStoreStatus(WmsStore store) {
        return wmStoreMapper.updateStoreStatus(store);
    }

    /**
     * 校验仓库名称是否唯一
     *
     * @param store
     * @return
     */
    @Override
    public String checkStoreNameUnique(WmsStore store) {
        Long id = StringUtils.isNull(store.getId()) ? -1L : store.getId();
        WmsStore wmsStore = wmStoreMapper.checkStoreNameUnique(store.getStoreName());
        if (StringUtils.isNotNull(wmsStore) && wmsStore.getId().longValue() != id.longValue()) {
            return StoreConstants.NOT_UNIQUE;
        }
        return StoreConstants.UNIQUE;
    }

    /**
     * 校验仓库编码是否唯一
     *
     * @param store
     * @return
     */
    @Override
    public String checkStoreCodeUnique(WmsStore store) {
        Long id = StringUtils.isNull(store.getId()) ? -1L : store.getId();
        WmsStore wmsStore = wmStoreMapper.checkStoreCodeUnique(store.getStoreCode());
        if (StringUtils.isNotNull(wmsStore) && wmsStore.getId().longValue() != id.longValue()) {
            return StoreConstants.NOT_UNIQUE;
        }
        return StoreConstants.UNIQUE;
    }
}
