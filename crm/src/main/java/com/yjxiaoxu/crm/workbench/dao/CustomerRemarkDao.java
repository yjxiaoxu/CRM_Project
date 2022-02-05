package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.CustomerRemark;

/**
 * ClassName:CustomerRemarkDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/12/12 19:57
 * @Author:小许33058485@qq.com
 */
public interface CustomerRemarkDao {
    //保存客户备注
    int save(CustomerRemark customerRemark);
}
