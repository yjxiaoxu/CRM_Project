package com.yjxiaoxu.crm.workbench.domain;

/**
 * ClassName:CustomerRemark
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:创建一个javabean客户备注类
 *
 * @Date:2020/12/12 15:47
 * @Author:小许33058485@qq.com
 */
public class CustomerRemark {
    private String id; //
    private String noteContent; //
    private String createBy; //
    private String createTime; //
    private String editBy; //
    private String editTime; //
    private String editFlag; //
    private String customerId; //
    //setter和getter方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
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

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "CustomerRemark{" +
                "id='" + id + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editFlag='" + editFlag + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
