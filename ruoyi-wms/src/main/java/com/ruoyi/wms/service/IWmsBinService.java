package com.ruoyi.wms.service;

import com.ruoyi.wms.domain.WmsBin;

import java.util.List;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/4/21 16:39
 */
public interface IWmsBinService {
    /**
     * 根据条件分页查询库位数据
     *
     * @param bin
     * @return
     */
    List<WmsBin> list(WmsBin bin);

    /**
     * 新增保存库位信息
     *
     * @param bin
     * @return
     */
    int insertBin(WmsBin bin);

    /**
     * 修改库位信息
     *
     * @param bin
     * @return
     */
    int updateBin(WmsBin bin);

    /**
     * 根据ID批量删除库位信息
     *
     * @param ids
     * @return
     */
    int deleteBinByIds(Long[] ids);

    /**
     * 修改库位状态
     *
     * @param bin
     * @return
     */
    int updateBinStatus(WmsBin bin);

    /**
     * 校验库位名称是否唯一
     *
     * @param bin
     * @return
     */
    String checkBinNameUnique(WmsBin bin);

    /**
     * 校验仓库编码是否唯一
     *
     * @param bin
     * @return
     */
    String checkBinCodeUnique(WmsBin bin);
}
