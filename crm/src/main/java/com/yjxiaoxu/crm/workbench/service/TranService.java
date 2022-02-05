package com.yjxiaoxu.crm.workbench.service;

import com.yjxiaoxu.crm.vo.PaginationVO;
import com.yjxiaoxu.crm.workbench.domain.Tran;

import java.util.Map;

/**
 * ClassName:TranService
 * Package:com.yjxiaoxu.crm.workbench.service
 * Description:
 *
 * @Date:2020/12/20 15:57
 * @Author:小许33058485@qq.com
 */
public interface TranService {

    //保存交易
    boolean save(Tran tran, String customerName);
    //刷新交易列表
    PaginationVO pageList(Map<String, Object> map);
    //根据Id获取交易详细信息
    Tran getDetailById(String id);
}
