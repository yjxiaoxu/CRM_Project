package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.Activity;
import com.yjxiaoxu.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;


/**
 * ClassName:ActivityDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/11/3 14:32
 * @Author:小许33058485@qq.com
 */
public interface ActivityDao {
    //保存市场活动方法
    int save(Activity activity);
    //查看市场活动信息的记录条数
    Long getTotalByCondition(Map<String, Object> map);
    //查看所有的市场活动信息
    List<Activity> getActivityListByCondition(Map<String, Object> map);
    //删除市场活动记录
    long deleteByActivityId(String[] ids);
    //根据Id获取市场活动对象
    Activity getActivityById(String id);
    //通过Id修改市场活动
    int updateById(Activity activity);
    //根据Id获取市场活动对象
    Activity detailById(String id);
    //根据线索id获取相关市场活动
    List<Activity> refreshRelationByClueId(String id);
    //根据市场活动名称模糊查询市场活动表
    List<Activity> getActivityByName(Map<String, String> map);
    //获取市场活动源
    List<Activity> getActivityListOrName(String activityName);
}
