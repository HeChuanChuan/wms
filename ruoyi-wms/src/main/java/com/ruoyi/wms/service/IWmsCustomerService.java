package com.ruoyi.wms.service;


import com.ruoyi.wms.domain.WmsCustomer;

import java.util.List;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/4/22 13:50
 */
public interface IWmsCustomerService {
    /**
     * 根据条件分页查询客户数据
     *
     * @param customer
     * @return
     */
    List<WmsCustomer> list(WmsCustomer customer);

    /**
     * 查询所有客户
     *
     * @return
     */
    List<WmsCustomer> selectCustomerAll();

    /**
     * 新增保存客户信息
     *
     * @param customer
     * @return
     */
    int insertCustomer(WmsCustomer customer);

    /**
     * 修改客户信息
     *
     * @param customer
     * @return
     */
    int updateCustomer(WmsCustomer customer);

    /**
     * 根据ID批量删除客户信息
     *
     * @param ids
     * @return
     */
    int deleteCustomerByIds(Long[] ids);

    /**
     * 修改客户状态
     *
     * @param customer
     * @return
     */
    int updateCustomerStatus(WmsCustomer customer);

    /**
     * 校验客户编码是否唯一
     *
     * @param customer
     * @return
     */
    String checkCustomerCodeUnique(WmsCustomer customer);
}
