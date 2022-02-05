package com.yjxiaoxu.crm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName:MD5Util
 * Package:com.yjxiaoxu.crm.utils
 * Description:提供MD5加密的工具类
 *
 * @Date:2020/10/30 14:16
 * @Author:小许33058485@qq.com
 */
public class MD5Util {
    //工具类的构造方法一般私有化
    private MD5Util() {

    }
    //提供一个MD5加密的方法，返回一个字符串
    public static String getMD5(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
