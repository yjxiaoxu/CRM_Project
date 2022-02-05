package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

/**
 * ClassName:ClueActivityRelationDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/12/6 17:24
 * @Author:小许33058485@qq.com
 */
public interface ClueActivityRelationDao {
    //根据关联关系id删除关联关系
    int relieveRelation(String id);
    //根据线索id和市场活动id关联市场活动
    int relation(ClueActivityRelation clueActivityRelation);
    //根据线索id获取市场线索市场活动关联关系对象
    List<ClueActivityRelation> getListByClueId(String clueId);
    //根据关联关系id删除关联关系（循环删除）
    int delete(ClueActivityRelation clueActivityRelation);
}