package com.yjxiaoxu.crm.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:EncodingFilter
 * Package:com.yjxiaoxu.crm.web.filter
 * Description:定义一个防止中文乱码的过滤器
 *
 * @Date:2020/11/1 14:15
 * @Author:小许33058485@qq.com
 */
public class EncodingFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入防止中文乱码过滤器工作");
        //通知请求对象采用UTF-8编码方式进行编码
        servletRequest.setCharacterEncoding("UTF-8");
        //通知响应对象采用UTF-8编码方式
        servletResponse.setContentType("text/html;charset=UTF-8");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }


}
