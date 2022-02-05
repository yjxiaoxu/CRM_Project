package com.yjxiaoxu.crm.workbench.dao;

import com.yjxiaoxu.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ClueDao
 * Package:com.yjxiaoxu.crm.workbench.dao
 * Description:
 *
 * @Date:2020/11/27 14:37
 * @Author:小许33058485@qq.com
 */
public interface ClueDao {
    //保存线索的方法
    int saveClue(Clue clue);
    //根据相关条件获取线索对象的总记录条数
    Long getTotalClue(Map<String, Object> map);
    //根据相关条件获取线索对象集合
    List<Clue> getClueList(Map<String, Object> map);
    //根据id删除线索
    int deleteByIds(String[] id);
    //通过ID获取线索
    Clue getClueById(String id);
    //根据id修改线索
    int updateClue(Clue clue);
    //  根据id获取线索详细信息
    Clue getDetail(String id);
    //根据线索id获取线索对象
    Clue getClueById1(String clueId);
    //根据线索id删除线索
    int deleteById(String clueId);
}
