package com.yjxiaoxu.crm.settings.service;

import com.yjxiaoxu.crm.exception.LoginException;
import com.yjxiaoxu.crm.settings.domain.User;

import java.util.List;

/**
 * ClassName:UserService
 * Package:com.yjxiaoxu.crm.settings.service
 * Description:
 *
 * @Date:2020/10/29 23:30
 * @Author:小许33058485@qq.com
 */
public interface UserService {

    //提供一个登录方法
    User login(String loginAct, String loginPwdMD5, String ip) throws LoginException;

    //提供一个获取所有用户信息的方法
    List<User> getUserList();
}
