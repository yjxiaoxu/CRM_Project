package com.yjxiaoxu.crm.exception;

/**
 * ClassName:LoginException
 * Package:com.yjxiaoxu.crm.exception
 * Description:定义一个登录异常类
 *
 * @Date:2020/10/30 13:53
 * @Author:小许33058485@qq.com
 */
public class LoginException extends Exception {
    //无参的构造方法
    public LoginException() {}
    //有参的构造方法
    public LoginException(String msg) {
        super(msg);
    }

}
