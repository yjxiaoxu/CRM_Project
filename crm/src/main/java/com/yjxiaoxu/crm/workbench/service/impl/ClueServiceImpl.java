package com.yjxiaoxu.crm.workbench.service.impl;

import com.yjxiaoxu.crm.settings.dao.UserDao;
import com.yjxiaoxu.crm.settings.domain.User;
import com.yjxiaoxu.crm.utils.DateTimeUtil;
import com.yjxiaoxu.crm.utils.SqlSessionUtil;
import com.yjxiaoxu.crm.utils.UUIDUtil;
import com.yjxiaoxu.crm.vo.PaginationVO;
import com.yjxiaoxu.crm.workbench.dao.*;
import com.yjxiaoxu.crm.workbench.domain.*;
import com.yjxiaoxu.crm.workbench.service.ClueService;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ClueServiceImpl
 * Package:com.yjxiaoxu.crm.workbench.service.impl
 * Description:
 *
 * @Date:2020/11/27 14:39
 * @Author:小许33058485@qq.com
 */
public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    private ClueRemarkDao clueRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);

    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private CustomerRemarkDao customerRemarkDao = SqlSessionUtil.getSqlSession().getMapper(CustomerRemarkDao.class);

    private ContactsDao contactsDao = SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
    private ContactsRemarkDao contactsRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ContactsRemarkDao.class);
    private ContactsActivityRelationDao contactsActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ContactsActivityRelationDao.class);

    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);

    @Override
    public boolean saveClue(Clue clue) {
        boolean flag = true;
        int count = clueDao.saveClue(clue);
        if (count != 1) {
            flag = false;

        }
        return flag;
    }

    @Override
    public PaginationVO pageList(Map<String, Object> map) {
        Long total = clueDao.getTotalClue(map);
        List<Clue> clueList = clueDao.getClueList(map);
        //创建VO类对象
        PaginationVO paginationVO = new PaginationVO();
        paginationVO.setTotal(total);
        paginationVO.setPageList(clueList);
        return paginationVO;
    }

    @Override
    public boolean deleteByIds(String[] id) {
        boolean flag = true;
        int count = clueDao.deleteByIds(id);
        if (count == 0) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getClueById(String id) {
        List<User> userList = userDao.getUserList();
        Clue clue = clueDao.getClueById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("c", clue);
        return map;
    }

    @Override
    public boolean updateClue(Clue clue) {
        boolean flag = true;
        int count = clueDao.updateClue(clue);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Clue getDetail(String id) {
        Clue clue = clueDao.getDetail(id);
        return clue;
    }

    @Override
    public boolean convert(String clueId, Tran tran, String createBy) {
        boolean flag = true;
        String createTime = DateTimeUtil.getSysTime();
        //(1) 获取到线索id，通过线索id获取线索对象（线索对象当中封装了线索的信息）
        Clue clue = clueDao.getClueById1(clueId);
        //(2) 通过线索对象提取客户信息，当该客户不存在的时候，新建客户（根据公司的名称精确匹配，判断该客户是否存在！）
        String company = clue.getCompany();
        Customer customer = customerDao.getCustomerByName(company);
        if (customer == null) {
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setCreateBy(createBy);
            customer.setName(clue.getCompany());
            customer.setOwner(clue.getOwner());
            customer.setPhone(clue.getPhone());
            customer.setWebsite(clue.getWebsite());
            customer.setNextContactTime(clue.getNextContactTime());
            customer.setDescription(clue.getDescription());
            customer.setCreateTime(createTime);
            customer.setAddress(clue.getAddress());
            customer.setContactSummary(clue.getContactSummary());
            int count = customerDao.save(customer);
            if (count != 1) {
                flag = false;
            }
        }
        //(3) 通过线索对象提取联系人信息，保存联系人
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setSource(clue.getSource());
        contacts.setOwner(clue.getOwner());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setFullname(clue.getFullname());
        contacts.setEmail(clue.getEmail());
        contacts.setDescription(clue.getDescription());
        contacts.setCreateBy(createBy);
        contacts.setCreateTime(createTime);
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setAppellation(clue.getAppellation());
        contacts.setAddress(clue.getAddress());
        contacts.setCustomerId(customer.getId());
        int count1 = contactsDao.save(contacts);
        if (count1 != 1) {
            flag = false;
        }
        //(4) 线索备注转换到客户备注以及联系人备注
        List<ClueRemark> crList = clueRemarkDao.getRemarkList(clueId);
        //遍历List集合
        for (ClueRemark clueRemark : crList) {
            String noteContent = clueRemark.getNoteContent();
            //创建客户备注对象
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateBy(createBy);
            customerRemark.setCreateTime(createTime);
            customerRemark.setCustomerId(customer.getId());
            customerRemark.setNoteContent(noteContent);
            customerRemark.setEditFlag("0");
            //添加客户备注
            int count2 = customerRemarkDao.save(customerRemark);
            if (count2 != 1) {
                flag = false;
            }
            //创建联系人备注对象
            ContactsRemark contactsRemark = new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setContactsId(contacts.getId());
            contactsRemark.setNoteContent(noteContent);
            contactsRemark.setCreateBy(createBy);
            contactsRemark.setCreateTime(createTime);
            contactsRemark.setEditFlag("0");
            //添加联系人备注
            int count3 = contactsRemarkDao.save(contactsRemark);
            if (count3 != 1) {
                flag = false;
            }
        }
        //(5) “线索和市场活动”的关系转换到“联系人和市场活动”的关系
        //根据线索id获取线索_市场活动_关联对象
        List<ClueActivityRelation> clueActivityRelationList = clueActivityRelationDao.getListByClueId(clueId);
        for (ClueActivityRelation clueActivityRelation: clueActivityRelationList) {
            String activityId = clueActivityRelation.getActivityId();
            //创建联系人和市场活动关系对象
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityId(activityId);
            contactsActivityRelation.setContactsId(contacts.getId());
            //保存联系人和市场活动关系
            int count4 = contactsActivityRelationDao.save(contactsActivityRelation);
            if (count4 != 1) {
                flag = false;
            }
        }
        //(6) 如果有创建交易需求，创建一条交易
        if (tran != null) {
            //添加交易
            tran.setOwner(clue.getOwner());
            tran.setCreateTime(createTime);
            tran.setSource(clue.getSource());
            tran.setNextContactTime(clue.getNextContactTime());
            tran.setDescription(clue.getDescription());
            tran.setCustomerId(customer.getId());
            tran.setContactsId(contacts.getId());
            tran.setContactSummary(clue.getContactSummary());
            int count5 = tranDao.save(tran);
            if (count5 != 1) {
                flag = false;
            }
            //(7) 如果创建了交易，则创建一条该交易下的交易历史
            TranHistory tranHistory = new TranHistory();
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setCreateBy(createBy);
            tranHistory.setCreateTime(createTime);
            tranHistory.setTranId(tran.getId());
            tranHistory.setStage(tran.getStage());
            tranHistory.setMoney(tran.getMoney());
            tranHistory.setExpectedDate(tran.getExpectedDate());
            int count6 = tranHistoryDao.save(tranHistory);
            if (count6 != 1) {
                flag = false;
            }
        }
        //(8) 删除线索备注
        for (ClueRemark clueRemark : crList) {
            int count7 = clueRemarkDao.deleteClueRemarkByClueId(clueRemark);
            if (count7 != 1) {
                flag = false;
            }
        }
        //(9) 删除线索和市场活动的关系
        for (ClueActivityRelation clueActivityRelation: clueActivityRelationList) {
            int count8 = clueActivityRelationDao.delete(clueActivityRelation);
            if (count8 != 1) {
                flag = false;
            }
        }
        //(10) 删除线索
        int count9 = clueDao.deleteById(clueId);
        if (count9 != 1) {
            flag = false;
        }
        return flag;
    }

}
