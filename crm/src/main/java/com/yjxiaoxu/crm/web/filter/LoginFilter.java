package com.yjxiaoxu.crm.web.filter;

import com.yjxiaoxu.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:LoginFilter
 * Package:com.yjxiaoxu.crm.web.filter
 * Description:定义一个防止恶意访问的过滤器
 *
 * @Date:2020/11/1 14:39
 * @Author:小许33058485@qq.com
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("进入到防止恶意访问过滤器工作");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取用户访问的路径
        String path = request.getServletPath();
        if ("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)) {
            //放行
            filterChain.doFilter(request, response);
        } else {
            //验证是否已登录
            User user = (User) request.getSession().getAttribute("user");   //user != nuu,说明用户已登录
            if (user != null) {
                //放行
                filterChain.doFilter(request, response);
            } else {
                //重定向到登录页面
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
