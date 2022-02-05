package com.yjxiaoxu.crm.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * ClassName:SqlSessionUtil
 * Package:com.yjxiaoxu.crm.utils
 * Description:创建一个获取SqlSession的工具类
 *
 * @Date:2020/10/29 21:32
 * @Author:小许33058485@qq.com
 */
public class SqlSessionUtil {
    //工具类的构造方法一般私有化
    private SqlSessionUtil() {

    }
    //创建一个SqlSessionFactory对象
    private static SqlSessionFactory sqlSessionFactory = null;
    //创建一个ThreadLocal对象
    private static ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<>();
    //静态代码块，在类加载时创建SqlSessionFactory对象，并且只执行一次
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //提供一个获取SqlSession对象的方法
    public static SqlSession getSqlSession() {
        //从ThreadLocal上获取SqlSession对象
        SqlSession sqlSession = sqlSessionThreadLocal.get();
        //如果sqlSession为空。创建SqlSession对象
        if (sqlSession == null) {
            sqlSession =sqlSessionFactory.openSession();
            //向线程上绑定sqlSession对象
            sqlSessionThreadLocal.set(sqlSession);
        }

        return sqlSession;
    }
    //提供一个回滚事务的方法
    public static void rollback(SqlSession sqlSession) {
        if (sqlSession != null) {
            //回滚事务
            sqlSession.rollback();
        }
    }
    //提供一个释放资源的方法
    public static void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
            sqlSessionThreadLocal.remove();
        }
    }
}
