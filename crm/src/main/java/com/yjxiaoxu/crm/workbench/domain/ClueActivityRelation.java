package com.yjxiaoxu.crm.workbench.domain;

import java.util.Map;

/**
 * ClassName:ClueActivityRelation
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:创建一个javabean类
 *
 * @Date:2020/12/6 17:20
 * @Author:小许33058485@qq.com
 */
public class ClueActivityRelation {
    private String id;
    private String clueId;
    private String activityId;
    //setter和getter方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClueId() {
        return clueId;
    }

    public void setClueId(String clueId) {
        this.clueId = clueId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "ClueActivityRelation{" +
                "id='" + id + '\'' +
                ", clueId='" + clueId + '\'' +
                ", activityId='" + activityId + '\'' +
                '}';
    }

}
