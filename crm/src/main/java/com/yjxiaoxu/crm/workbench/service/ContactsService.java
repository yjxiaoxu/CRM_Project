package com.yjxiaoxu.crm.workbench.service;

import com.yjxiaoxu.crm.workbench.domain.Contacts;

import java.util.List;

/**
 * ClassName:ContactsService
 * Package:com.yjxiaoxu.crm.workbench.service
 * Description:
 *
 * @Date:2020/12/19 14:25
 * @Author:小许33058485@qq.com
 */
public interface ContactsService {
    //通过联系人名字获取联系人对象，支持模糊查询
    List<Contacts> getContactsByName(String name);
}
