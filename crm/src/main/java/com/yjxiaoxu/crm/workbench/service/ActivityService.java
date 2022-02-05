package com.yjxiaoxu.crm.workbench.service;

import com.yjxiaoxu.crm.vo.PaginationVO;
import com.yjxiaoxu.crm.workbench.domain.Activity;
import com.yjxiaoxu.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;


/**
 * ClassName:ActivityService
 * Package:com.yjxiaoxu.crm.workbench.service
 * Description:
 *
 * @Date:2020/11/3 14:32
 * @Author:小许33058485@qq.com
 */
public interface ActivityService {

    //保存市场活动方法
    boolean save(Activity activity);
    //查看市场活动方法
    PaginationVO pageList(Map<String, Object> map);
    //删除市场活动方法
    boolean deleteByActivityId(String[] ids);
    //获取用户信息和市场活动信息
    Map<String, Object> getUserListAndActivity(String id);
    //通过Id修改市场活动信息
    boolean updateById(Activity activity);
    //通过ID获取市场活动
    Activity detailById(String id);
    //通过市场活动ID获取ActivityRemark对象集合
    List<ActivityRemark> refreshById(String id);
    //根据id删除市场活动备注
    boolean deleteRemarkById(String id);
    //保存备注
    Map<String, Object> saveRemark(ActivityRemark activityRemark);
    //修改备注方法
    boolean updateRemark(ActivityRemark ar);
    //根据线索id获取市场活动
    List<Activity> refreshRelationByClueId(String id);
    //根据市场活动名称模糊查询市场活动表
    List<Activity> getActivityByName(Map<String, String> map);
    //获取市场活动源
    List<Activity> getActivityListOrName(String activityName);
}
