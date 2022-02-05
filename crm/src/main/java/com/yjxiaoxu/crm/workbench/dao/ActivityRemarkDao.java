package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * ClassName:ActivityRemarkDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/11/18 14:39
 * @Author:小许33058485@qq.com
 */
public interface ActivityRemarkDao {
    //根据市场活动Id查询出相关的市场活动备注条数
    long getCountById(String[] ids);
    //根据OD删除相关的市场活动备注
    long deleteByIds(String[] ids);
    //根据市场活动id获取ActivityRemark对象集合
    List<ActivityRemark> refreshById(String id);
    //根据id删除市场活动备注
    long deleteRemarkById(String id);
    //保存市场活动备注
    int saveRemark(ActivityRemark activityRemark);
    //根据id获取市场活动备注对象
    ActivityRemark getActivityRemarkById(ActivityRemark activityRemark);
    //修改备注方法
    int updateRemark(ActivityRemark ar);
}
