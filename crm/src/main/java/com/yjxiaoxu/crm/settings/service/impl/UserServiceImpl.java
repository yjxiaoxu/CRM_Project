package com.yjxiaoxu.crm.settings.service.impl;

import com.yjxiaoxu.crm.exception.LoginException;
import com.yjxiaoxu.crm.settings.dao.UserDao;
import com.yjxiaoxu.crm.settings.domain.User;
import com.yjxiaoxu.crm.settings.service.UserService;
import com.yjxiaoxu.crm.utils.DateTimeUtil;
import com.yjxiaoxu.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:UserServiceImpl
 * Package:com.yjxiaoxu.crm.settings.service.impl
 * Description:
 *
 * @Date:2020/10/29 23:30
 * @Author:小许33058485@qq.com
 */
public class UserServiceImpl implements UserService {
    //创建UserDao对象
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    //提供一个登录方法
    @Override
    public User login(String loginAct, String loginPwdMD5, String ip) throws LoginException{
        Map<String, String> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwdMD5", loginPwdMD5);
        //调用dao层中的登录方法
        User user = userDao.login(map);
        if (user == null) {
            throw new LoginException("账号或密码错误");
        }
        //验证失效时间
        String nowTime = DateTimeUtil.getSysTime(); //获取系统当前时间
        String expireTime = user.getExpireTime();
        int i = expireTime.compareTo(nowTime);    //将当前时间与账号有效时间进行比较  返回值i的值: 大于1 :有效时间大于当前系统时间 等于0：有效时间等于系统当前时间 小于0  ：有效时间小于系统当前时间
        if (i < 0) {
            throw new LoginException("账号已过期");
        }
        //验证ip
        boolean flag = user.getAllowIps().contains(ip);     //flag的值： true，允许登录，false:不允许登录
        if (!flag) {
            throw new LoginException("你的ip受限，请联系管理员");
        }
        //验证账号状态
        String allow = user.getLockState(); //获取账号的状态   "1" :表示允许使用 "0":表示账号已锁定
        if ("0".equals(allow)) {
            throw new LoginException("账号已锁定,请联系管理员");
        }
        return user;
    }

    //提供一个获取所有用户信息的方法
    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;
    }
}
