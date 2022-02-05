package com.yjxiaoxu.crm.settings.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjxiaoxu.crm.settings.domain.User;
import com.yjxiaoxu.crm.settings.service.UserService;
import com.yjxiaoxu.crm.settings.service.impl.UserServiceImpl;
import com.yjxiaoxu.crm.utils.MD5Util;
import com.yjxiaoxu.crm.utils.PrintJson;
import com.yjxiaoxu.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:UserController
 * Package:com.yjxiaoxu.crm.settings.web.controller
 * Description:
 *
 * @Date:2020/10/30 14:49
 * @Author:小许33058485@qq.com
 */
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入用户控制器工作");
        //获取用户输入的路径
        String path = request.getServletPath();
//        System.out.println(path);
        if ("/settings/user/login.do".equals(path)) {
            login(request, response);
        } else if ("/settings/user/*.do".equals(path)) {

        }
    }

    //登录操作
    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到验证登录操作");
        //获取ajax发送的账号
        String loginAct = request.getParameter("loginAct");
        //获取aja发送的密码
        String loginPwd = request.getParameter("loginPwd");
        //将密码进行MD5加密
        String loginPwdMD5 = MD5Util.getMD5(loginPwd);
//        System.out.println(loginAct + "密码：" + loginPwd);
//        System.out.println("加密后的密码" + loginPwdMD5);
        //获取访问者的ID地址
        String ip = request.getRemoteAddr();
        //System.out.println("访问者的Id-------->" + id);
        //创建代理实现类对象
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        //调用service层的登录方法
        try {
            User user = userService.login(loginAct, loginPwdMD5, ip);
            //如果登录成功，将用户名放入session域中
            request.getSession().setAttribute("user", user);
            /**
             * 如果登录成功，则返回json串  ：{"success":true}
             */
            //使用转换json工具类将字符串转换为json,并返回到前端页面中
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            e.printStackTrace();
            //获取抛出的异常信息，以字符串的形式返回
            String msg = e.getMessage();    //返回的错误信息
            /**
             * 程序执行到此处说明登录失败，此时应该返回的json为
             * {"success":false,"msg":msg}
             */
            System.out.println(msg);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            map.put("msg", msg);
            //使用转换json工具类将字符串转换为json,并返回到前端页面中
           PrintJson.printJsonObj(response, map);
        }
    }
}
