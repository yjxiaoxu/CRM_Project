package com.yjxiaoxu.crm.workbench.service.impl;

import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.workbench.dao.CustomerDao;
import com.yjxiaoxu.crm.workbench.service.CustomerService;

import java.util.List;

/**
 * ClassName:CustomerServiceImpl
 * Package:com.yjxiaoxu.crm.workbench.service.impl
 * Description:
 *
 * @Date:2020/12/16 21:49
 * @Author:小许33058485@qq.com
 */
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);

    @Override
    public List<String> getCustomerName(String name) {
        List<String> cList = customerDao.getCustomerName(name);
        return cList;
    }
}
