package com.yjxiaoxu.crm.workbench.service.impl;

import com.yjxiaoxu.crm.utils.ServiceFactory;
import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.workbench.dao.ContactsDao;
import com.yjxiaoxu.crm.workbench.domain.Contacts;
import com.yjxiaoxu.crm.workbench.service.ContactsService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * ClassName:ContactsServiceImpl
 * Package:com.yjxiaoxu.crm.workbench.service.impl
 * Description:
 *
 * @Date:2020/12/19 14:25
 * @Author:小许33058485@qq.com
 */
public class ContactsServiceImpl implements ContactsService {
    private ContactsDao contactsDao = SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);

    @Override
    public List<Contacts> getContactsByName(String name) {
        List<Contacts> cList = contactsDao.getContactsByName(name);
        return cList;
    }
}
