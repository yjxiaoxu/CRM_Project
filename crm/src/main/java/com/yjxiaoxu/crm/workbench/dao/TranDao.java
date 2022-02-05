package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

/**
 * ClassName:TranDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/12/12 23:46
 * @Author:小许33058485@qq.com
 */
public interface TranDao {
    //添加交易
    int save(Tran tran);
    //获取交易列表，可根据所有者、名称、....等
    List<Tran> getTranList(Map<String, Object> map);
    //获取总记录条数
    Long getTotal(Map<String, Object> map);
    //根据交易id获取交易详细信息
    Tran getDetailById(String id);
}
