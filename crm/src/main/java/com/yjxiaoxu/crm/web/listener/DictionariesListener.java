package com.yjxiaoxu.crm.web.listener;

import com.yjxiaoxu.crm.settings.domain.DicType;
import com.yjxiaoxu.crm.settings.domain.DicValue;
import com.yjxiaoxu.crm.settings.service.DicService;
import com.yjxiaoxu.crm.settings.service.impl.DicServiceImpl;
import com.yjxiaoxu.crm.utils.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * ClassName:DictionariesListener
 * Package:com.yjxiaoxu.crm.web.listener
 * Description:
 *
 * @Date:2020/11/27 15:08
 * @Author:小许33058485@qq.com
 */
public class DictionariesListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("上下文域对象创建了");
        ServletContext application = servletContextEvent.getServletContext();
        //创建动态代理实现类
        DicService dicService = (DicService) ServiceFactory.getService(new DicServiceImpl());
        Map<String, List<DicValue>> map = dicService.getAllDicValue();
        Set<String> set = map.keySet();
        for (String key : set) {
            application.setAttribute(key + "List", map.get(key));
        }
        System.out.println("服务器缓存数据字典结束-----------------------");
        //创建资源绑定器
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Stage2Possibility");
        Map<String, String> map1 = new HashMap<>();
        Enumeration<String> e = resourceBundle.getKeys();
        while(e.hasMoreElements()) {
            String key = e.nextElement();
            String value = resourceBundle.getString(key);
            map1.put(key, value);
        }
        application.setAttribute("map1", map1);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("上下文域对象销毁了");
    }
}
