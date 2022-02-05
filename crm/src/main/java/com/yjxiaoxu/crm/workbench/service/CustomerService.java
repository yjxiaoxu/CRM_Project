package com.yjxiaoxu.crm.workbench.service;

import java.util.List;

/**
 * ClassName:CustomerService
 * Package:com.yjxiaoxu.crm.workbench.service
 * Description:
 *
 * @Date:2020/12/16 21:49
 * @Author:小许33058485@qq.com
 */
public interface CustomerService {
    //获取客户姓名，支持模糊查询
    List<String> getCustomerName(String name);
}
