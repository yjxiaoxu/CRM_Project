package com.yjxiaoxu.crm.utils;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ClassName:TransactionInvocationHandler
 * Package:com.yjxiaoxu.crm.utils
 * Description://创建一个动态代理实现类
 *
 * @Date:2020/10/29 22:30
 * @Author:小许33058485@qq.com
 */
public class TransactionInvocationHandler implements InvocationHandler {
    private Object target;
    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession sqlSession = null;
        Object retValue = null;
        try {
            //开启事务
            sqlSession = SqlSessionUtil.getSqlSession();
            //动态代理实现
            retValue = method.invoke(target, args);
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            //打印异常信息
            e.printStackTrace();
            //回滚事务
            SqlSessionUtil.rollback(sqlSession);
            //将异常往上层抛出
            throw e.getCause();
        } finally {
            //释放资源
            SqlSessionUtil.close(sqlSession);
        }
        return retValue;
    }
    //提供一个获取动态代理实现类的方法
    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
