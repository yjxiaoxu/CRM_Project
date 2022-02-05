package com.yjxiaoxu.crm.settings.domain;

/**
 * ClassName:DicTypeDao
 * Package:com.yjxiaoxu.crm.settings.domain
 * Description:
 *
 * @Date:2020/11/27 15:24
 * @Author:小许33058485@qq.com
 */
public class DicType {
    private String code;    //编码
    private String name;    //名称
    private String description; //描述
    //setter和getter方法

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "DicTypeDao{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
