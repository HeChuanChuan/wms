package com.ruoyi.wms.domain;


import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class WmsBin extends BaseEntity {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 库位编码
     */
    private String binCode;

    /**
     * 库位条码
     */
    private String binBarCode;

    /**
     * 库位名称
     */
    private String binName;

    /**
     * 库位类型
     */
    private String binType;

    /**
     * 库位温度
     */
    private String binDeg;

    /**
     * 库位所属仓库
     */
    private String binStore;

    /**
     * 库位所属仓库
     */
    private WmsStore store;

    /**
     * 库位所属客户
     */
    private String binCustomer;

    /**
     * 库位所属客户
     */
    /**
     * 库位体积
     */
    private Double binVolume;

    /**
     * 库位面积
     */
    private Double binArea;

    /**
     * 库位承重
     */
    private Double binWeight;

    /**
     * 库位长度
     */
    private Double binLength;

    /**
     * 库位宽度
     */
    private Double binWidth;

    /**
     * 库位高度
     */
    private Double binHeight;

    /**
     * 库位状态
     */
    private String binStatus;

    /**
     * 库位是否删除
     */
    private String binIsDelete;

    /**
     *库位商品属性
     */
    private String binGoodType;

    /**
     * 库位x坐标
     */
    private Long binXnode;

    /**
     * 库位y坐标
     */
    private Long binYnode;

    /**
     * 库位z坐标
     */
    private Long binZnode;
}