package com.yjxiaoxu.crm.settings.service.impl;

import com.yjxiaoxu.crm.settings.dao.DicTypeDao;
import com.yjxiaoxu.crm.settings.dao.DicValueDao;
import com.yjxiaoxu.crm.settings.domain.DicType;
import com.yjxiaoxu.crm.settings.domain.DicValue;
import com.yjxiaoxu.crm.settings.service.DicService;
import com.yjxiaoxu.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:DicServiceImpl
 * Package:com.yjxiaoxu.crm.settings.service.impl
 * Description:
 *
 * @Date:2020/11/27 15:46
 * @Author:小许33058485@qq.com
 */
public class DicServiceImpl implements DicService {
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    @Override
    public Map<String, List<DicValue>> getAllDicValue() {
        List<DicType> list = dicTypeDao.getTypeCode();
        Map<String, List<DicValue>> map = new HashMap<>();
        for (DicType dic : list) {
            map.put(dic.getCode(), dicValueDao.getAllDicValue(dic.getCode()));
        }
        return map;
    }
}
