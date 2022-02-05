package com.yjxiaoxu.crm.utils;

/**
 * ClassName:ServiceFactory
 * Package:com.yjxiaoxu.crm.utils
 * Description:提供一个动态代理工厂
 *
 * @Date:2020/10/29 22:41
 * @Author:小许33058485@qq.com
 */
public class ServiceFactory {
    //提供一个返回动态代理实现类的方法
    public static Object getService(Object object) {
        return new TransactionInvocationHandler(object).getProxy();
    }
}
