package com.ruoyi.wms.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/3/31 14:27
 */
@Data
public class WmsStore extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID
     */
    @Excel(name = "仓库序号", cellType = Excel.ColumnType.NUMERIC)
    private Long storeId;

    /**
     * 仓库编码
     */
    @Excel(name = "仓库编码")
    private String storeCode;

    /**
     * 仓库名称
     */
    @Excel(name = "仓库名称")
    private String storeName;

    /**
     * 仓库属性
     */
    @Excel(name = "仓库属性")
    private String storeQuality;

    /**
     * 仓库状态(0表示停用,1表示可用)
     */
    @Excel(name = "仓库状态")
    private String storeStatus;

    /**
     * 仓库是否被删除
     */
    private String storeIsDelete;
}
