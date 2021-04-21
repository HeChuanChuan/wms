package com.ruoyi.wms.mapper;


import com.ruoyi.wms.domain.WmsBin;

import java.util.List;

public interface WmsBinMapper {
    List<WmsBin> selectBinList(WmsBin bin);

    int insertBin(WmsBin bin);

    int updateBin(WmsBin bin);

    int deleteBinByIds(Long[] ids);

    int updateBinStatus(WmsBin bin);

    WmsBin checkBinCodeUnique(String binCode);

    WmsBin checkBinNameUnique(String binName);
}