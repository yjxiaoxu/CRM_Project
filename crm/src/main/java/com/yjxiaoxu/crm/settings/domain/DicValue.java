package com.yjxiaoxu.crm.settings.domain;

/**
 * ClassName:DicValue
 * Package:com.yjxiaoxu.crm.settings.domain
 * Description:
 *
 * @Date:2020/11/27 15:25
 * @Author:小许33058485@qq.com
 */
public class DicValue {
    private String id;  //标识
    private String value;   //字典值
    private String text;    //文本
    private String orderNo; //排序号
    private String typeCode;    //字典类型编码
    //setter和getter方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "DicValue{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", text='" + text + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", typeCode='" + typeCode + '\'' +
                '}';
    }
}
