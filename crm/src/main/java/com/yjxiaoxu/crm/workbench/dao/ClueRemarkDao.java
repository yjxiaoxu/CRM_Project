package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.ClueRemark;

import java.util.List;

/**
 * ClassName:ClueRemarkDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/12/4 15:23
 * @Author:小许33058485@qq.com
 */
public interface ClueRemarkDao {
    //根据线索id获取备注信息
    List<ClueRemark> refresh(String cid);
    //根据线索备注id删除备注信息
    int deleteClueRemarkById(String id);
    //保存线索备注信息
    int saveClueRemark(ClueRemark clueRemark);
    //根据线索id获取线索备注集合
    List<ClueRemark> getRemarkList(String clueId);
    //根据线索备注id删除线索备注
    int deleteClueRemarkByClueId(ClueRemark clueRemark);
}
