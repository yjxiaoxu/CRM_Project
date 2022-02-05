package com.yjxiaoxu.crm.workbench.domain;

/**
 * ClassName:TranHistory
 * Package:com.yjxiaoxu.crm.workbench.domain
 * Description:创建一个javabean交易历史类
 *
 * @Date:2020/12/12 15:50
 * @Author:小许33058485@qq.com
 */
public class TranHistory {
    private String id;  //id,唯一标识
    private String stage;   //阶段
    private String money;   //交易金额
    private String expectedDate;    //期望交易时间/
    private String createTime;  //创建时间
    private String createBy;    //创建人
    private String tranId;  //交易id,作为外键
    private String possibility; //额外添加的属性，交易的可能性，在交易详细页面中展示交易历史时用到

//setter和getter方法

    public String getPossibility() {
        return possibility;
    }

    public void setPossibility(String possibility) {
        this.possibility = possibility;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
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

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "TranHistory{" +
                "id='" + id + '\'' +
                ", stage='" + stage + '\'' +
                ", money='" + money + '\'' +
                ", expectedDate='" + expectedDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", tranId='" + tranId + '\'' +
                '}';
    }
}
