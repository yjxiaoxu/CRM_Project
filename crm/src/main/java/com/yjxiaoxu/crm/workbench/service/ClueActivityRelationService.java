package com.yjxiaoxu.crm.workbench.service;

import java.util.Map;

/**
 * ClassName:ClueActivityRelationService
 * Package:com.yjxiaoxu.crm.workbench.service
 * Description:
 *
 * @Date:2020/12/6 17:34
 * @Author:小许33058485@qq.com
 */
public interface ClueActivityRelationService {
    //根据关联关系id删除关联关系
    boolean relieveRelation(String id);

    //根据市场活动id和线索id关联市场活动
    boolean relation(String clueId, String[] aids);
}
