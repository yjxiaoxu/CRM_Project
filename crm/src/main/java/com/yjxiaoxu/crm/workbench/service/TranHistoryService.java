package com.yjxiaoxu.crm.workbench.service;

import com.yjxiaoxu.crm.workbench.domain.TranHistory;

import java.util.List;

/**
 * ClassName:TranHistoryService
 * Package:com.yjxiaoxu.crm.workbench.service
 * Description:
 *
 * @Date:2020/12/26 20:19
 * @Author:小许33058485@qq.com
 */
public interface TranHistoryService {
    //根据交易id获取交易历史
    List<TranHistory> getTranHistory(String id);
}
