package com.ruoyi.wms.domain;

import java.util.Date;
import lombok.Data;

@Data
public class WmsCustomer {
    private Long id;

    private String cusFullName;

    private String cusSimpleName;

    private String cusCode;

    private String cusEnglishName;

    private String cusCodeBefore;

    private String cusNameBefore;

    private Byte cusLevel;

    private String cusIndustry;

    private Date cusSignTime;

    private Date cusExpireTime;

    private String cusOrgCode;

    private String cusProvinceCode;

    private String cusCityCode;

    private String cusTownCode;

    private String cusAddress;

    private String cusPostCode;

    private String cusContactMan;

    private String cusPhoneNumber;

    private String cusMobileNumber;

    private String cusFax;

    private String cusEmailAddress;

    private String cusWebAddress;

    private String cusLegalIdCard;

    private String cusRegisCapital;

    private String cusMoneyType;

    private String cusLicenseNumer;

    private String cusTaxRegisNumber;

    private String cusRoadTransNumber;

    private String cusMainBusiness;

    private Date cusRegisTime;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}