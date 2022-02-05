package com.yjxiaoxu.crm.workbench.service.impl;

import com.yjxiaoxu.crm.utils.DateTimeUtil;
import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.utils.UUIDUtil;
import com.yjxiaoxu.crm.vo.PaginationVO;
import com.yjxiaoxu.crm.workbench.dao.CustomerDao;
import com.yjxiaoxu.crm.workbench.dao.TranDao;
import com.yjxiaoxu.crm.workbench.dao.TranHistoryDao;
import com.yjxiaoxu.crm.workbench.domain.Customer;
import com.yjxiaoxu.crm.workbench.domain.Tran;
import com.yjxiaoxu.crm.workbench.domain.TranHistory;
import com.yjxiaoxu.crm.workbench.service.TranService;

import java.util.List;
import java.util.Map;

/**
 * ClassName:TranServiceImpl
 * Package:com.yjxiaoxu.crm.workbench.service.impl
 * Description:
 *
 * @Date:2020/12/20 15:57
 * @Author:小许33058485@qq.com
 */
public class TranServiceImpl implements TranService {
    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);

    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    @Override
    public boolean save(Tran tran, String customerName) {
        boolean flag = true;
        //通过客户名称判断客户表中是否有该客户，如果没有，则在表中新建客户信息
        Customer customer = customerDao.getCustomerByName(customerName);
        if (customer == null) {
            customer = new Customer();
            String id = UUIDUtil.getUUID();
            String owner = tran.getOwner();
            String name = customerName;
            String createBy = tran.getCreateBy();
            String createTime = DateTimeUtil.getSysTime();
            String contactSummary = tran.getContactSummary();
            String nextContactTime = tran.getNextContactTime();
            String description = tran.getDescription();
            customer.setId(id);
            customer.setOwner(owner);
            customer.setName(name);
            customer.setCreateBy(createBy);
            customer.setCreateTime(createTime);
            customer.setContactSummary(contactSummary);
            customer.setNextContactTime(nextContactTime);
            customer.setDescription(description);
            //创建客户
            int count1 = customerDao.save(customer);
            if (count1 != 1) {
                flag = false;
            }
        }
        String customerId = customer.getId();
        tran.setCustomerId(customerId);
        //（4）添加交易记录（注意先后顺序，添加完客户之后，才知道客户id）
        int count2 = tranDao.save(tran);
        if (count2 != 1) {
            flag = false;
        }
		//（5）添加交易历史
        TranHistory tranHistory = new TranHistory();
        String id = UUIDUtil.getUUID();
        String stage = tran.getStage();
        String money = tran.getMoney();
        String expectedDate = tran.getExpectedDate();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = tran.getCreateBy();
        String tranId = tran.getId();
        tranHistory.setId(id);
        tranHistory.setStage(stage);
        tranHistory.setMoney(money);
        tranHistory.setExpectedDate(expectedDate);
        tranHistory.setCreateTime(createTime);
        tranHistory.setCreateBy(createBy);
        tranHistory.setTranId(tranId);
        int count3 = tranHistoryDao.save(tranHistory);
        if (count3 != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public PaginationVO pageList(Map<String, Object> map) {
        PaginationVO paginationVO = new PaginationVO();
        Long total = tranDao.getTotal(map);
        List<Tran> tList = tranDao.getTranList(map);
        paginationVO.setTotal(total);
        paginationVO.setPageList(tList);
        return paginationVO;
    }

    @Override
    public Tran getDetailById(String id) {
        Tran tran = tranDao.getDetailById(id);
        return tran;
    }
}
