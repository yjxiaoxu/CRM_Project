package com.yjxiaoxu.crm.workbench.service.impl;

import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.workbench.dao.ClueRemarkDao;
import com.yjxiaoxu.crm.workbench.domain.ClueRemark;
import com.yjxiaoxu.crm.workbench.service.ClueRemarkService;

import java.util.List;

/**
 * ClassName:ClueRemarkServiceImpl
 * Package:com.yjxiaoxu.crm.workbench.service.impl
 * Description:
 *
 * @Date:2020/12/4 15:22
 * @Author:小许33058485@qq.com
 */
public class ClueRemarkServiceImpl implements ClueRemarkService {
    private ClueRemarkDao clueRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);

    @Override
    public List<ClueRemark> refresh(String cid) {
        List<ClueRemark> cList = clueRemarkDao.refresh(cid);
        return cList;
    }

    @Override
    public boolean deleteClueRemarkById(String id) {
        boolean flag = true;
        int count = clueRemarkDao.deleteClueRemarkById(id);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean saveClueRemark(ClueRemark clueRemark) {
        boolean flag = true;
        int count = clueRemarkDao.saveClueRemark(clueRemark);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }
}
