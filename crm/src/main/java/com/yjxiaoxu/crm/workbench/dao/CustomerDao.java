package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.Customer;

import java.util.List;

/**
 * ClassName:CustomerDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/12/12 17:21
 * @Author:小许33058485@qq.com
 */
public interface CustomerDao {
    //根据客户名称获取客户对象
    Customer getCustomerByName(String company);
    //保存客户
    int save(Customer customer);
    //获取客户姓名，支持模糊查询
    List<String> getCustomerName(String name);
}
