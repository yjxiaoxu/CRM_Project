package com.yjxiaoxu.crm.vo;

import java.util.List;

/**
 * ClassName:PaginationVO
 * Package:com.yjxiaoxu.crm.vo
 * Description:定义一个vo类，用来封装查询时返回的记录的总条数和市场活动对象的集合
 *
 * @Date:2020/11/11 19:57
 * @Author:小许33058485@qq.com
 */
public class PaginationVO<T> {
    //查询记录的总条数
    private long total;
    //返回的市场活动集合
    private List<T> pageList;
    //setter和getter方法

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "PaginationVO{" +
                "total=" + total +
                ", pageList=" + pageList +
                '}';
    }
}
