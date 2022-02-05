package com.yjxiaoxu.crm.workbench.web.controller;

import com.yjxiaoxu.crm.settings.domain.User;
import com.yjxiaoxu.crm.settings.service.UserService;
import com.yjxiaoxu.crm.settings.service.impl.UserServiceImpl;
import com.yjxiaoxu.crm.utils.DateTimeUtil;
import com.yjxiaoxu.crm.utils.PrintJson;
import com.yjxiaoxu.crm.utils.ServiceFactory;
import com.yjxiaoxu.crm.utils.UUIDUtil;
import com.yjxiaoxu.crm.vo.PaginationVO;
import com.yjxiaoxu.crm.workbench.domain.Activity;
import com.yjxiaoxu.crm.workbench.domain.Contacts;
import com.yjxiaoxu.crm.workbench.domain.Tran;
import com.yjxiaoxu.crm.workbench.domain.TranHistory;
import com.yjxiaoxu.crm.workbench.service.*;
import com.yjxiaoxu.crm.workbench.service.impl.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:TranController
 * Package:com.yjxiaoxu.crm.workbench.web.controller
 * Description:
 *
 * @Date:2020/12/16 20:23
 * @Author:小许33058485@qq.com
 */
public class TranController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到交易控制器--------------------------------");
        //获取操作路径
        String path = request.getServletPath();
        if ("/workbench/transaction/add.do".equals(path)) {
            addTran(request, response);
        } else if ("/workbench/transaction/getCustomerName.do".equals(path)) {
            getCustomerName(request, response);
        } else if ("/workbench/transaction/getActivitySource.do".equals(path)) {
            getActivitySource(request, response);
        } else if ("/workbench/transaction/getContactsByName.do".equals(path)) {
            getContactsByName(request, response);
        } else if ("/workbench/transaction/save.do".equals(path)) {
            save(request, response);
        } else if ("/workbench/transaction/pageList.do".equals(path)) {
            pageList(request, response);
        } else if ("/workbench/transaction/detail.do".equals(path)) {
            detail(request, response);
        } else if ("/workbench/transaction/getTranHistory.do".equals(path)) {
            getTranHistory(request, response);
        }
    }

    private void getTranHistory(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到根据交易id获取交易历史操作");
        String id = request.getParameter("id");
        //创建动态代理实现类对象
        TranHistoryService tranHistoryService = (TranHistoryService) ServiceFactory.getService(new TranHistoryServiceImpl());
        List<TranHistory> thList = tranHistoryService.getTranHistory(id);
        //获取阶段和可能性的map
        Map<String, String> pmap = (Map<String, String>) request.getServletContext().getAttribute("map1");
        //遍历获取到的交易历史集合
        if (thList != null) {
            for (TranHistory th : thList) {
                String stage = th.getStage();
                String possibility = pmap.get(stage);
                th.setPossibility(possibility);
            }
        }
        PrintJson.printJsonObj(response, thList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("进入根据交易id获取交易详细信息操作");
        String id = request.getParameter("id");
        TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImpl());
        Tran tran = tranService.getDetailById(id);
        String stage = tran.getStage();
        Map<String, String> map = (Map<String, String>) request.getServletContext().getAttribute("map1");
        String possibility = map.get(stage);
        tran.setPossibility(possibility);
        request.setAttribute("t", tran);
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入刷新交易列表操作");

        int pageNo = Integer.valueOf(request.getParameter("pageNo"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String customerName = request.getParameter("customerName");
        String stage = request.getParameter("stage");
        String type = request.getParameter("type");
        String source = request.getParameter("source");
        String contactsName = request.getParameter("contactsName");
        //分页查询时，根据一条页面要展的记录条数计算出每页应该从第几页开始计算
        int count = (pageNo - 1) * pageSize;
        //将获取到的信息封装到map集合中
        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("pageSize", pageSize);
        map.put("owner", owner);
        map.put("name", name);
        map.put("customerName", customerName);
        map.put("stage", stage);
        map.put("type", type);
        map.put("source", source);
        map.put("contactsName", contactsName);
        //创建动态代理实现类对象
        TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImpl());
        PaginationVO paginationVo = tranService.pageList(map);
        PrintJson.printJsonObj(response, paginationVo);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入保存交易操作");
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String money = request.getParameter("money");
        String name = request.getParameter("name");
        String expectedDate = request.getParameter("expectedDate");
        String customerName = request.getParameter("customerName");
        String stage = request.getParameter("stage");
        String type = request.getParameter("type");
        String source = request.getParameter("source");
        String activityId = request.getParameter("activityId");
        String contactsId = request.getParameter("contactsId");
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        //创建交易对象，并将获取到的数据封装到对象中
        Tran tran = new Tran();
        tran.setId(id);
        tran.setOwner(owner);
        tran.setMoney(money);
        tran.setName(name);
        tran.setExpectedDate(expectedDate);
        tran.setStage(stage);
        tran.setType(type);
        tran.setSource(source);
        tran.setActivityId(activityId);
        tran.setContactsId(contactsId);
        tran.setCreateBy(createBy);
        tran.setCreateTime(createTime);
        tran.setDescription(description);
        tran.setContactSummary(contactSummary);
        tran.setNextContactTime(nextContactTime);

        //创建动态代理实现类对象
        TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImpl());
        boolean flag = tranService.save(tran, customerName);
        if (flag) {
            //重定向到transaction/index.jsp页面
            response.sendRedirect(request.getContextPath() +  "/workbench/transaction/index.jsp");
        }
    }

    private void getContactsByName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入获取联系人列表（支持模糊查询）操作");
        String name = request.getParameter("name");
        ContactsService contactsService = (ContactsService) ServiceFactory.getService(new ContactsServiceImpl());
        List<Contacts> cList = contactsService.getContactsByName(name);
        PrintJson.printJsonObj(response, cList);
    }

    private void getActivitySource(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入获取市场活动源操作");
        String name = request.getParameter("name");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> aList = activityService.getActivityListOrName(name);
        PrintJson.printJsonObj(response, aList);
    }

    private void getCustomerName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到模糊查询客户名称操作");
        String name = request.getParameter("name");
        CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
        List<String> cList = customerService.getCustomerName(name);
        PrintJson.printJsonObj(response, cList);
    }

    private void addTran(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("进入到传统方式跳转到添加交易页面");
        //创建动态代理实现类对象
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        //获取用户列表
        List<User> uList = userService.getUserList();
        //将用户集合添加到request域中
        request.setAttribute("uList", uList);
        //转发到添加交易页面
        request.getRequestDispatcher("save.jsp").forward(request, response);
    }
}
