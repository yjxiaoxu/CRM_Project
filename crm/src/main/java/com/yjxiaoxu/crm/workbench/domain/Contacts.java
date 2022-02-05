package com.yjxiaoxu.crm.workbench.domain;

/**
 * ClassName:ContactsDao
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:创建一个javabean联系人类
 *
 * @Date:2020/12/12 15:15
 * @Author:小许33058485@qq.com
 */
public class Contacts {
    private String id;  //联系人id
    private String owner;   //所有人
    private String source;  //来源
    private String customerId;  //客户id
    private String fullname;    //姓名
    private String appellation; //称呼
    private String email;   //邮箱
    private String mphone;  //电话
    private String job; //工作
    private String birth;   //生日
    private String createBy;    //创建人
    private String createTime;  //创建时间
    private String editBy;  //修改人
    private String editTime;    //修改时间
    private String description; //备注
    private String contactSummary;  //既要
    private String nextContactTime; //下次联系时间
    private String address; //住址
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public void setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public void setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "ContactsDao{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", source='" + source + '\'' +
                ", customerId='" + customerId + '\'' +
                ", fullname='" + fullname + '\'' +
                ", appellation='" + appellation + '\'' +
                ", email='" + email + '\'' +
                ", mphone='" + mphone + '\'' +
                ", job='" + job + '\'' +
                ", birth='" + birth + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", description='" + description + '\'' +
                ", contactSummary='" + contactSummary + '\'' +
                ", nextContactTime='" + nextContactTime + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
