package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.ContactsActivityRelation;

/**
 * ClassName:ContactsActivityRelationDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/12/12 22:53
 * @Author:小许33058485@qq.com
 */
public interface ContactsActivityRelationDao {
    //保存联系人和市场活动关系
    int save(ContactsActivityRelation contactsActivityRelation);
}
