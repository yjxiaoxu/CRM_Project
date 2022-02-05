package com.yjxiaoxu.crm.settings.dao;

import com.yjxiaoxu.crm.settings.domain.DicType;

import java.util.List;

/**
 * ClassName:DicTypeDao
 * Package:com.yjxiaoxu.crm.settings.dao
 * Description:
 *
 * @Date:2020/11/27 15:40
 * @Author:小许33058485@qq.com
 */
public interface DicTypeDao {
    //获取所有DicType对象
    List<DicType> getTypeCode();
}
