package com.yjxiaoxu.crm.workbench.service;

import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.workbench.domain.ClueRemark;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * ClassName:ClueRemarkService
 * Package:com.yjxiaoxu.crm.workbench.service
 * Description:
 *
 * @Date:2020/12/4 15:20
 * @Author:小许33058485@qq.com
 */
public interface ClueRemarkService {
    //根据线索id获取备注信息
    List<ClueRemark> refresh(String cid);
    //根据线索备注id删除备注信息
    boolean deleteClueRemarkById(String id);
    //保存线索备注动方法
    boolean saveClueRemark(ClueRemark clueRemark);
}
