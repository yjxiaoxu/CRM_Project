package com.yjxiaoxu.crm.workbench.domain;

/**
 * ClassName:ClueRemark
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:
 *
 * @Date:2020/12/4 15:24
 * @Author:小许33058485@qq.com
 */
public class ClueRemark {
   private String id;
   private String noteContent;
   private String createBy;
   private String createTime;
   private String editBy;
   private String editTime;
   private String editFlag;
   private String clueId;
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

    public String getClueId() {
        return clueId;
    }

    public void setClueId(String clueId) {
        this.clueId = clueId;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "ClueRemark{" +
                "id='" + id + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editFlag='" + editFlag + '\'' +
                ", clueId='" + clueId + '\'' +
                '}';
    }
}
