package com.yjxiaoxu.crm.settings.domain;

/**
 * ClassName:User
 * Package:com.yjxiaoxu.crm.settings.domain
 * Description:创建一个用户类
 *
 * @Date:2020/10/30 14:34
 * @Author:小许33058485@qq.com
 */
public class User {
    private String id;           //编号
    private String loginAct;    //登录账号
    private String name;    //用户真实姓名
    private String loginPwd;    //登录密码
    private String email;   //邮箱
    private String expireTime;  //账号过期时间
    private String lockState;   //账号状态 0：锁定  1：允许使用
    private String deptno;  //部门编号
    private String allowIps;    //允许登录IP
    private String createTime;  //创建时间
    private String createBy;    //创建人
    private String editTime;    //修改时间
    private String editBy;  //修改人
    //setter和getter方法


    public void setId(String id) {
        this.id = id;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getId() {
        return id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public String getName() {
        return name;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public String getLockState() {
        return lockState;
    }

    public String getDeptno() {
        return deptno;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public String getEditBy() {
        return editBy;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginAct='" + loginAct + '\'' +
                ", name='" + name + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", email='" + email + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", lockState='" + lockState + '\'' +
                ", deptno='" + deptno + '\'' +
                ", allowIps='" + allowIps + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                '}';
    }
}
