package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.Contacts;

import java.util.List;

/**
 * ClassName:ContactsDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/12/12 19:36
 * @Author:小许33058485@qq.com
 */
public interface ContactsDao {
    //保存交易人方法
    int save(Contacts contacts);
    //根据联系人姓名获取联系人对象，支持模糊查询
    List<Contacts> getContactsByName(String name);
}
