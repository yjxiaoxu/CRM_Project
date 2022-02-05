package com.yjxiaoxu.crm.workbench.service.impl;

import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.workbench.dao.TranHistoryDao;
import com.yjxiaoxu.crm.workbench.domain.TranHistory;
import com.yjxiaoxu.crm.workbench.service.TranHistoryService;

import java.util.List;

/**
 * ClassName:TranHistoryServiceImpl
 * Package:com.yjxiaoxu.crm.workbench.service.impl
 * Description:
 *
 * @Date:2020/12/26 20:20
 * @Author:小许33058485@qq.com
 */
public class TranHistoryServiceImpl implements TranHistoryService {
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);

    @Override
    public List<TranHistory> getTranHistory(String id) {
        List<TranHistory> thList = tranHistoryDao.getTranHistory(id);
        return thList;
    }
}
