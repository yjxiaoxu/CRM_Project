package com.yjxiaoxu.crm.workbench.service.impl;
import com.yjxiaoxu.crm.settings.dao.UserDao;
import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.vo.PaginationVO;
import com.yjxiaoxu.crm.workbench.dao.ActivityDao;
import com.yjxiaoxu.crm.workbench.dao.ActivityRemarkDao;
import com.yjxiaoxu.crm.workbench.domain.Activity;
import com.yjxiaoxu.crm.workbench.domain.ActivityRemark;
import com.yjxiaoxu.crm.workbench.service.ActivityService;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ClassName:ActivityServiceImpl
 * Package:com.yjxiaoxu.crm.workbench.service.impl
 * Description:
 *
 * @Date:2020/11/3 14:33
 * @Author:小许33058485@qq.com
 */
public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    //保存市场活动方法
    @Override
    public boolean save(Activity activity) {
        boolean flag = false;
        int retValue = activityDao.save(activity);
        if (retValue == 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public PaginationVO pageList(Map<String, Object> map) {
        Long total = activityDao.getTotalByCondition(map);  //根据相关条件获取的记录条数
        List<Activity> pageList = activityDao.getActivityListByCondition(map);  //根据相关条件获取的市场活动对象的集合
        //创建VO对象
        PaginationVO<Activity> pageVo = new PaginationVO<>();
        pageVo.setTotal(total);
        pageVo.setPageList(pageList);
        return pageVo;
    }

    //删除市场活动方法
    @Override
    public boolean deleteByActivityId(String[] ids) {
        boolean flag = true;
        //删除市场活动时，要先查询出市场活动描述表里面作为市场活动表中的外键的条数（对市场活动表的描述）
        long count1 = activityRemarkDao.getCountById(ids);
        //删除市场活动备注表中相关记录
        long count2 = activityRemarkDao.deleteByIds(ids);
        if (count1 != count2) {
            flag = false;
        }
        //根据id删除市场活动记录
        long count = activityDao.deleteByActivityId(ids);
        if (count != ids.length) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        Map<String, Object> map = new HashMap<>();
        //获取用户信息
        map.put("userList",userDao.getUserList());
        map.put("a", activityDao.getActivityById(id));
        return map;
    }

    @Override
    public boolean updateById(Activity activity) {
        boolean flag = false;
        int reValue = activityDao.updateById(activity);
        if (reValue == 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Activity detailById(String id) {
        Activity activity = activityDao.detailById(id);
        return activity;
    }

    @Override
    public List<ActivityRemark> refreshById(String id) {
        List<ActivityRemark> arList = activityRemarkDao.refreshById(id);
        return arList;
    }

    @Override
    public boolean deleteRemarkById(String id) {
        boolean flag = true;
        long count = activityRemarkDao.deleteRemarkById(id);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> saveRemark(ActivityRemark activityRemark) {
        boolean flag = true;
        int count = activityRemarkDao.saveRemark(activityRemark);
        if (count != 1) {
            flag = false;
        }
        ActivityRemark a = activityRemarkDao.getActivityRemarkById(activityRemark);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("a", a);
        return map;
    }

    @Override
    public boolean updateRemark(ActivityRemark ar) {
        boolean flag = true;
        int count = activityRemarkDao.updateRemark(ar);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Activity> refreshRelationByClueId(String id) {
        List<Activity> aList = activityDao.refreshRelationByClueId(id);
        return aList;
    }

    @Override
    public List<Activity> getActivityByName(Map<String, String> map) {
        List<Activity> aList = activityDao.getActivityByName(map);
        return aList;
    }

    @Override
    public List<Activity> getActivityListOrName(String activityName) {
        List<Activity> aList = activityDao.getActivityListOrName(activityName);
        return aList;
    }


}
