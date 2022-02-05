package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.TranHistory;

import java.util.List;

/**
 * ClassName:TranHistoryDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/12/12 23:49
 * @Author:小许33058485@qq.com
 */
public interface TranHistoryDao {
    //添加交易历史
    int save(TranHistory tranHistory);
    //根据交易id获取交易历史
    List<TranHistory> getTranHistory(String id);
}
