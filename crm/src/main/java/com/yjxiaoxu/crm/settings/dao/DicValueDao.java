package com.yjxiaoxu.crm.settings.dao;

import com.yjxiaoxu.crm.settings.domain.DicValue;

import java.util.List;

/**
 * ClassName:DicValueDao
 * Package:com.yjxiaoxu.crm.settings.dao
 * Description:
 *
 * @Date:2020/11/27 15:42
 * @Author:小许33058485@qq.com
 */
public interface DicValueDao {
    //通过字典编码获取字典值
    List<DicValue> getAllDicValue(String code);
}
