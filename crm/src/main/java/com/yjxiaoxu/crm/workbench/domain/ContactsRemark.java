package com.yjxiaoxu.crm.workbench.domain;

/**
 * ClassName:ContactsRemark
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:创建一个javabean联系人备注类
 *
 * @Date:2020/12/12 15:21
 * @Author:小许33058485@qq.com
 */
public class ContactsRemark {
    private String id;  //id,唯一标识
    private String noteContent; //备注
    private String createBy;    //创建人
    private String createTime;  //创建时间
    private String editBy;  //修改人
    private String editTime;    //修改时间
    private String editFlag;    //修改flag,1标识被修改过，0标识未被修改
    private String contactsId;  //联系人id,作为外键
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

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "ContactsRemark{" +
                "id='" + id + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editFlag='" + editFlag + '\'' +
                ", contactsId='" + contactsId + '\'' +
                '}';
    }
}
