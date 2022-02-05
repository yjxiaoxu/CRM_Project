package com.yjxiaoxu.crm.workbench.domain;

/**
 * ClassName:Customer
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:创建一个javabean顾客类
 *
 * @Date:2020/12/12 15:41
 * @Author:小许33058485@qq.com
 */
public class Customer {
    private String id; //id,作为唯一标识
    private String owner; //所有人
    private String name; //姓名
    private String website; //公司网站
    private String phone; //联系电话
    private String createBy; //创建人
    private String createTime; //创建时间
    private String editBy; //修改人
    private String editTime; //修改时间
    private String contactSummary; //既要
    private String nextContactTime; //下次练习时间
    private String description; //备注
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Customer{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", phone='" + phone + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", contactSummary='" + contactSummary + '\'' +
                ", nextContactTime='" + nextContactTime + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
