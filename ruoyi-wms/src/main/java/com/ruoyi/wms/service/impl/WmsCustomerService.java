package com.ruoyi.wms.service.impl;

import com.ruoyi.wms.domain.WmsCustomer;
import com.ruoyi.wms.service.IWmsCustomerService;

import java.util.List;

/**
 * Description：
 * Author：Hechuanchuan
 * Date：2021/4/22 14:35
 */
public class WmsCustomerService implements IWmsCustomerService {
    /**
     * 根据条件分页查询客户数据
     *
     * @param customer
     * @return
     */
    @Override
    public List<WmsCustomer> list(WmsCustomer customer) {
        return null;
    }

    /**
     * 查询所有客户
     *
     * @return
     */
    @Override
    public List<WmsCustomer> selectCustomerAll() {
        return null;
    }

    /**
     * 新增保存客户信息
     *
     * @param customer
     * @return
     */
    @Override
    public int insertCustomer(WmsCustomer customer) {
        return 0;
    }

    /**
     * 修改客户信息
     *
     * @param customer
     * @return
     */
    @Override
    public int updateCustomer(WmsCustomer customer) {
        return 0;
    }

    /**
     * 根据ID批量删除客户信息
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteCustomerByIds(Long[] ids) {
        return 0;
    }

    /**
     * 修改客户状态
     *
     * @param customer
     * @return
     */
    @Override
    public int updateCustomerStatus(WmsCustomer customer) {
        return 0;
    }

    /**
     * 校验客户编码是否唯一
     *
     * @param customer
     * @return
     */
    @Override
    public String checkCustomerCodeUnique(WmsCustomer customer) {
        return null;
    }
}
