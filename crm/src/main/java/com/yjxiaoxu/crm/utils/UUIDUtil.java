package com.yjxiaoxu.crm.utils;

import java.util.UUID;

/**
 * ClassName:UUIDUtil
 * Package:com.yjxiaoxu.crm.utils
 * Description:提供一个获取UUID的工具类
 *
 * @Date:2020/10/29 22:43
 * @Author:小许33058485@qq.com
 */
public class UUIDUtil {
    //工具类的构造方法一般私有化
    private UUIDUtil() {

    }
    //提供一个获取UUID的方法
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
