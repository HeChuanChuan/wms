package com.ruoyi.wms.service.impl;

import com.ruoyi.wms.domain.WmsBin;
import com.ruoyi.wms.mapper.WmsBinMapper;
import com.ruoyi.wms.service.IWmsBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/4/21 16:44
 */
@Service
public class WmsBinService implements IWmsBinService {

    @Autowired
    private WmsBinMapper wmsBinMapper;

    /**
     * 根据条件分页查询库位数据
     *
     * @param bin
     * @return
     */
    @Override
    public List<WmsBin> list(WmsBin bin) {
        return wmsBinMapper.selectBinList(bin);
    }

    /**
     * 新增保存库位信息
     *
     * @param bin
     * @return
     */
    @Override
    public int insertBin(WmsBin bin) {
        return 0;
    }

    /**
     * 修改库位信息
     *
     * @param bin
     * @return
     */
    @Override
    public int updateBin(WmsBin bin) {
        return 0;
    }

    /**
     * 根据ID批量删除库位信息
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteBinByIds(Long[] ids) {
        return 0;
    }

    /**
     * 修改库位状态
     *
     * @param bin
     * @return
     */
    @Override
    public int updateBinStatus(WmsBin bin) {
        return 0;
    }

    /**
     * 校验库位名称是否唯一
     *
     * @param bin
     * @return
     */
    @Override
    public String checkBinNameUnique(WmsBin bin) {
        return null;
    }

    /**
     * 校验仓库编码是否唯一
     *
     * @param bin
     * @return
     */
    @Override
    public String checkBinCodeUnique(WmsBin bin) {
        return null;
    }
}
