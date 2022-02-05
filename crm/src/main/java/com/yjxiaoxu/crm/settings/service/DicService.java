package com.yjxiaoxu.crm.settings.service;

import com.yjxiaoxu.crm.settings.domain.DicType;
import com.yjxiaoxu.crm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

/**
 * ClassName:DicService
 * Package:com.yjxiaoxu.crm.settings.service
 * Description:
 *
 * @Date:2020/11/27 15:45
 * @Author:小许33058485@qq.com
 */
public interface DicService {
    //获取字典类型对象
    Map<String, List<DicValue>> getAllDicValue();
}
