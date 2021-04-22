package com.ruoyi.wms.mapper;


import com.ruoyi.wms.domain.WmsCustomer;

import java.util.List;

public interface WmsCustomerMapper {
    List<WmsCustomer> selectCustomerList(WmsCustomer customer);

    List<WmsCustomer> selectCustomerAll();

    int insertCustomer(WmsCustomer customer);

    int updateCustomer(WmsCustomer customer);

    int deleteCustomerByIds(Long[] ids);

    int updateCustomerStatus(WmsCustomer customer);

    WmsCustomer checkCustomerCodeUnique(String customerCode);
}