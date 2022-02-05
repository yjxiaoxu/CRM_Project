package com.yjxiaoxu.crm.workbench.service;

import com.yjxiaoxu.crm.settings.domain.User;
import com.yjxiaoxu.crm.vo.PaginationVO;
import com.yjxiaoxu.crm.workbench.domain.Clue;
import com.yjxiaoxu.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ClueService
 * Package:com.yjxiaoxu.crm.workbench.service
 * Description:
 *
 * @Date:2020/11/27 14:39
 * @Author:小许33058485@qq.com
 */
public interface ClueService {
    //添加线索的方法
    boolean saveClue(Clue clue);
    //刷新线索列表
    PaginationVO pageList(Map<String, Object> map);
    //根据ID删除线索
    boolean deleteByIds(String[] id);
    //根据id获取要修改的线索
    Map<String, Object> getClueById(String id);
    //根据id修改线索
    boolean updateClue(Clue clue);
    //根据ID获取Clue对象
    Clue getDetail(String id);
    //线索转换
    boolean convert(String clueId, Tran tran, String createBy);
}
