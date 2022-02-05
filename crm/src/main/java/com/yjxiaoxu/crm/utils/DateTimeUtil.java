package com.yjxiaoxu.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:DataTimeUtil
 * Package:com.yjxiaoxu.crm.utils
 * Description:创建一个获取系统当期时间的工具类，获取的时间以特点的格式返回
 *
 * @Date:2020/10/30 13:58
 * @Author:小许33058485@qq.com
 */
public class DateTimeUtil {
    //工具类的构造方法一般私有化
    private DateTimeUtil() {

    }
    //提供一个获取系统当前时间的方法
    public static String getSysTime() {
        Date date = new Date();
        //将系统当前时间以特定方式格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //yyyy:年    MM：月    dd：日    HH：时    mm:分
        String nowTime = sdf.format(date);
        return nowTime;
    }
}
