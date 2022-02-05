package com.yjxiaoxu.crm.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:PrintJson
 * Package:com.yjxiaoxu.crm.utils
 * Description:提供一个转换json串的工具类
 * @Date:2020/10/30 23:46
 * @Author:小许33058485@qq.com
 */
public class PrintJson {
    //工具类的构造方法一般私有化
    private PrintJson() {

    }
    //将boolean值解析为json串
    public static void printJsonFlag(HttpServletResponse response, boolean flag){

        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("success",flag);

        ObjectMapper om = new ObjectMapper();
        try {
            //{"success":true}
            String json = om.writeValueAsString(map);
            response.getWriter().print(json);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    //将对象解析为json串
    public static void printJsonObj(HttpServletResponse response,Object obj){

        /*
         *
         * Person p
         * 	id name age
         * {"id":"?","name":"?","age":?}
         *
         * List<Person> pList
         * [{"id":"?","name":"?","age":?},{"id":"?","name":"?","age":?},{"id":"?","name":"?","age":?}...]
         *
         * Map
         * 	key value
         * {key:value}
         *
         *
         */

        ObjectMapper om = new ObjectMapper();
        try {
            String json = om.writeValueAsString(obj);
            response.getWriter().print(json);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
