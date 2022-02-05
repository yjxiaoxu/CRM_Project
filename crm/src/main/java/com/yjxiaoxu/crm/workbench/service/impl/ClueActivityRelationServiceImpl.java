package com.yjxiaoxu.crm.workbench.service.impl;

import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.utils.UUIDUtil;
import com.yjxiaoxu.crm.workbench.dao.ClueActivityRelationDao;
import com.yjxiaoxu.crm.workbench.domain.ClueActivityRelation;
import com.yjxiaoxu.crm.workbench.service.ClueActivityRelationService;

import java.util.Map;
import java.util.Set;

/**
 * ClassName:ClueActivityRelationServiceImpl
 * Package:com.yjxiaoxu.crm.workbench.service.impl
 * Description:
 *
 * @Date:2020/12/6 17:34
 * @Author:小许33058485@qq.com
 */
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);

    @Override
    public boolean relieveRelation(String id) {
        boolean flag = true;
        int count = clueActivityRelationDao.relieveRelation(id);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean relation(String clueId, String[] aids) {
        boolean flag = true;
        for (String aid : aids) {
            ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setActivityId(aid);
            clueActivityRelation.setClueId(clueId);
            int count = clueActivityRelationDao.relation(clueActivityRelation);
            if (count != 1) {
                flag = false;
            }
        }
        return flag;
    }

}
