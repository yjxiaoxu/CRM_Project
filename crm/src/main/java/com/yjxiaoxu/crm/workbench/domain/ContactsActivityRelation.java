package com.yjxiaoxu.crm.workbench.domain;

/**
 * ClassName:ContactsActivityRelation
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:创建一个javabean联系人和市场活动关联关系表
 *
 * @Date:2020/12/12 15:25
 * @Author:小许33058485@qq.com
 */
public class ContactsActivityRelation {
    private String id;  //唯一标识
    private String contactsId;  //联系人id
    private String activityId;  //市场活动id
    //setter和getter方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
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
        return "id[id = "+id+"]" + "contactsId[contactsId = "+contactsId+"]" + "activityId[activityId = "+activityId+"]";
    }
}
