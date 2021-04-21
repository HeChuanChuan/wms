package com.ruoyi.wms.domain;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class WmsCustomer extends BaseEntity {
    /**
     * 客户主键ID
     */
    private Long cusId;

    /**
     * 客户中文全称
     */
    private String cusFullName;

    /**
     * 客户中文简称
     */
    private String cusSimpleName;

    /**
     * 客户代码
     */
    private String cusCode;

    /**
     * 客户英文名称
     */
    private String cusEnglishName;

    /**
     * 客户曾用企业代码
     */
    private String cusCodeBefore;

    /**
     * 客户曾用企业名称
     */
    private String cusNameBefore;

    /**
     * 客户等级
     */
    private Byte cusLevel;

    /**
     * 客户所属行业
     */
    private String cusIndustry;

    /**
     * 客户签约时间
     */
    private Date cusSignTime;

    /**
     * 客户终止合作时间
     */
    private Date cusExpireTime;

    /**
     * 客户归属组织代码
     */
    private String cusOrgCode;

    /**
     * 客户归属省份代码
     */
    private String cusProvinceCode;

    /**
     * 客户归属市代码
     */
    private String cusCityCode;

    /**
     * 客户归属县区代码
     */
    private String cusTownCode;

    /**
     * 客户地址
     */
    private String cusAddress;

    /**
     * 客户邮箱编码
     */
    private String cusPostCode;

    /**
     * 客户主联系人
     */
    private String cusContactMan;

    /**
     * 客户电话
     */
    private String cusPhoneNumber;

    /**
     * 客户手机号码
     */
    private String cusMobileNumber;

    /**
     * 客户传真
     */
    private String cusFax;

    /**
     * 客户邮箱地址
     */
    private String cusEmailAddress;

    /**
     * 客户网页地址
     */
    private String cusWebAddress;

    /**
     * 法人身份证号
     */
    private String cusLegalIdCard;

    /**
     * 注册资金
     */
    private String cusRegisCapital;

    /**
     * 币别
     */
    private String cusMoneyType;

    /**
     * 营业执照号
     */
    private String cusLicenseNumer;

    /**
     * 税务登记证号
     */
    private String cusTaxRegisNumber;

    /**
     * 道路运输经营许可证
     */
    private String cusRoadTransNumber;

    /**
     * 主营业务
     */
    private String cusMainBusiness;

    /**
     * 注册日期
     */
    private Date cusRegisTime;

    /**
     * 客户状态
     */
    private String cusStatus;

    /**
     * 客户是否被删除
     */
    private String cusIsDelete;
}