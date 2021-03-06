package com.yjxiaoxu.crm.workbench.domain;

/**
 * ClassName:Activity
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:创建一个市场活动javabean
 *
 * @Date:2020/11/3 13:44
 * @Author:小许33058485@qq.com
 */
public class Activity {
    private String id;  //主键
    private String owner;   //所有者 外键 关联tbl_user
    private String name;    //市场活动时间
    private String startDate;   //开始时间 格式：yyyy-MM-dd
    private String endDate; //结束时间  格式：yyyy-MM-dd
    private String cost;    //成本
    private String description; //描述
    private String createTime;  //创建时间  格式：yyyy-MM-dd HH:mm:ss
    private String createBy;    //创建人
    private String editTime;    //修改时间  格式：yyyy-MM-dd HH:mm:ss
    private String editBy;  //修改人
    //setter和getter方法


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }
}
