package com.yjxiaoxu.crm.settings.dao;

import com.yjxiaoxu.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserDao
 * Package:com.yjxiaoxu.crm.settings.dao
 * Description:dao层定义一个UserDao接口
 *
 * @Date:2020/10/29 23:22
 * @Author:小许33058485@qq.com
 */
public interface UserDao {

    //提供一个登录方法
    User login(Map<String, String> map);

    //提供一个获取所有用户信息的方法
    List<User> getUserList();
}
