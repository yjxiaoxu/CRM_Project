package com.yjxiaoxu.crm.workbench.web.controller;

import com.yjxiaoxu.crm.settings.domain.User;
import com.yjxiaoxu.crm.settings.service.UserService;
import com.yjxiaoxu.crm.settings.service.impl.UserServiceImpl;
import com.yjxiaoxu.crm.utils.*;
import com.yjxiaoxu.crm.vo.PaginationVO;
import com.yjxiaoxu.crm.workbench.domain.*;
import com.yjxiaoxu.crm.workbench.service.ActivityService;
import com.yjxiaoxu.crm.workbench.service.ClueActivityRelationService;
import com.yjxiaoxu.crm.workbench.service.ClueRemarkService;
import com.yjxiaoxu.crm.workbench.service.ClueService;
import com.yjxiaoxu.crm.workbench.service.impl.ActivityServiceImpl;
import com.yjxiaoxu.crm.workbench.service.impl.ClueActivityRelationServiceImpl;
import com.yjxiaoxu.crm.workbench.service.impl.ClueRemarkServiceImpl;
import com.yjxiaoxu.crm.workbench.service.impl.ClueServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ClueController
 * Package:com.yjxiaoxu.crm.workbench.web.controller
 * Description:
 *
 * @Date:2020/11/27 14:40
 * @Author:小许33058485@qq.com
 */
public class ClueController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到线索控制器------------------------------------------------");
        //获取请求路径
        String path = request.getServletPath();
        if ("/workbench/clue/saveClue.do".equals(path)) {
            saveClue(request, response);
        } else if ("/workbench/clue/getUserList.do".equals(path)) {
            getUserList(request, response);
        } else if ("/workbench/clue/pageList.do".equals(path)) {
            pageList(request, response);
        } else if ("/workbench/clue/delete.do".equals(path)) {
            delete(request, response);
        } else if ("/workbench/clue/getClue.do".equals(path)) {
            getClue(request, response);
        } else if ("/workbench/clue/update.do".equals(path)) {
            update(request, response);
        } else if ("/workbench/clue/detail.do".equals(path)) {
            detail(request, response);
        } else if ("/workbench/clue/getClueRemarkById.do".equals(path)) {
            refresh(request, response);
        } else if ("/workbench/clue/deleteClueRemark.do".equals(path)) {
            deleteClueRemark(request, response);
        } else if ("/workbench/clue/saveClueRemark.do".equals(path)) {
            saveClueRemark(request, response);
        } else if ("/workbench/clue/refreshRelation.do".equals(path)) {
            refreshRelation(request, response);
        } else if ("/workbench/clue/relieveRelation.do".equals(path)) {
            relieveRelation(request, response);
        } else if ("/workbench/clue/getActivityByName.do".equals(path)) {
            getActivityByName(request, response);
        } else if ("/workbench/clue/relation.do".equals(path)) {
            relation(request, response);
        } else if ("/workbench/clue/getActivityListOrByName.do".equals(path)) {
            getActivityListOrByName(request, response);
        } else if ("/workbench/clue/convert.do".equals(path)) {
            convert(request, response);
        }
    }

    private void convert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到线索转换操作");
        String clueId = request.getParameter("clueId");
        String isFlag = request.getParameter("isFlag");
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        //创建交易对象
        Tran tran = null;
        if ("1".equals(isFlag)) {
            tran = new Tran();
            String money = request.getParameter("money");
            String tranName = request.getParameter("tranName");
            String expectedDate = request.getParameter("expectedDate");
            String stage = request.getParameter("stage");
            String activityId = request.getParameter("activityId");
            //将获取到的数据封装到Tran对象中
            tran.setId(UUIDUtil.getUUID());
            tran.setName(tranName);
            tran.setCreateBy(createBy);
            tran.setMoney(money);
            tran.setExpectedDate(expectedDate);
            tran.setStage(stage);
            tran.setActivityId(activityId);
        }
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = clueService.convert(clueId, tran, createBy);
        if (flag) {
            response.sendRedirect(request.getContextPath() + "/workbench/clue/index.jsp");
        }
    }

    private void getActivityListOrByName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到获取市场活动源操作");
        String activityName = request.getParameter("activityName");
        //创建动态代理实现类对象
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> aList = activityService.getActivityListOrName(activityName);
        PrintJson.printJsonObj(response, aList);
    }

    private void relation(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入关联市场活动操作");
        String clueId = request.getParameter("clueId");
        String aids[] = request.getParameterValues("aid");
        //将获取到的对象添加到Map集合中
        Map<String, Object> map = new HashMap<>();
        map.put("clueId", clueId);
        map.put("aids", aids);
        //创建动态代理实现类对象
        ClueActivityRelationService clueActivityRelationService = (ClueActivityRelationService) ServiceFactory.getService(new ClueActivityRelationServiceImpl());
        boolean flag = clueActivityRelationService.relation(clueId, aids);
        PrintJson.printJsonFlag(response, flag);
    }

    private void getActivityByName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到根据市场活动名称模糊查询市场活动表操作");
        String clueId = request.getParameter("clueId");
        String aName = request.getParameter("aName");
        Map<String, String> map = new HashMap<>();
        map.put("clueId", clueId);
        map.put("aName", aName);
        //创建动态代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> aList = activityService.getActivityByName(map);
        PrintJson.printJsonObj(response, aList);
    }

    private void relieveRelation(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入解除关联关系操作");
        String id = request.getParameter("id");
        ClueActivityRelationService clueActivityRelationService = (ClueActivityRelationService) ServiceFactory.getService(new ClueActivityRelationServiceImpl());
        boolean flag = clueActivityRelationService.relieveRelation(id);
        PrintJson.printJsonFlag(response, flag);
    }

    private void refreshRelation(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到根据线索id获取关联关系操作");
        String id = request.getParameter("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> aList = activityService.refreshRelationByClueId(id);
        PrintJson.printJsonObj(response, aList);
    }

    private void saveClueRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到添加备注操作");
        String noteContent = request.getParameter("noteContent");
        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String creteBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "0";
        String clueId = request.getParameter("clueId");
        //创建ClueRemark对象
        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setId(id);
        clueRemark.setNoteContent(noteContent);
        clueRemark.setCreateBy(creteBy);
        clueRemark.setCreateTime(createTime);
        clueRemark.setEditFlag(editFlag);
        clueRemark.setClueId(clueId);
        //创建动态代理实现类对象
        ClueRemarkService clueRemarkService = (ClueRemarkService) ServiceFactory.getService(new ClueRemarkServiceImpl());
        boolean flag = clueRemarkService.saveClueRemark(clueRemark);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("c", clueRemark);
        PrintJson.printJsonObj(response, map);
    }

    private void deleteClueRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到删除线索备注注操作");
        String id = request.getParameter("id");
        //创建动态代理实现类对象
        ClueRemarkService clueRemarkService = (ClueRemarkService) ServiceFactory.getService(new ClueRemarkServiceImpl());
        boolean flag = clueRemarkService.deleteClueRemarkById(id);
        PrintJson.printJsonFlag(response, flag);
    }

    private void refresh(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到获取线索备注操作");
        String cid = request.getParameter("cid");
        //创建动态代理实现类
        ClueRemarkService clueRemarkService = (ClueRemarkService) ServiceFactory.getService(new ClueRemarkServiceImpl());
        List<ClueRemark> cList = clueRemarkService.refresh(cid);
        PrintJson.printJsonObj(response, cList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("进入到传统方式跳转到线索详情页面操作");
        String id = request.getParameter("id");
        //创建动态代理实现类
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        //根据ID获取线索
        Clue clue = clueService.getDetail(id);
        //将获取到的线索对象添加到request遇中
        request.setAttribute("c", clue);
        //使用转发跳转到detail.jsp页面
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入修改线索操作");
        String id = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String editBy = ((User) request.getSession().getAttribute("user")).getName();
        String editTime = DateTimeUtil.getSysTime();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");
        //将获取到的数据封装到Clue对象中
        Clue clue = new Clue();
        clue.setId(id);
        clue.setFullname(fullname);
        clue.setAppellation(appellation);
        clue.setOwner(owner);
        clue.setCompany(company);
        clue.setJob(job);
        clue.setEmail(email);
        clue.setPhone(phone);
        clue.setWebsite(website);
        clue.setMphone(mphone);
        clue.setState(state);
        clue.setSource(source);
        clue.setEditBy(editBy);
        clue.setEditTime(editTime);
        clue.setDescription(description);
        clue.setContactSummary(contactSummary);
        clue.setNextContactTime(nextContactTime);
        clue.setAddress(address);
        //创建动态代理实现类
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        //调用修改线索方法
        boolean flag = clueService.updateClue(clue);
        PrintJson.printJsonFlag(response, flag);
    }

    private void getClue(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入根据id获取线索操作");
        String id = request.getParameter("id");
        //创建动态代理实现类
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        Map<String, Object> map = clueService.getClueById(id);
        //将返回的map以json的格式返回到前端
        PrintJson.printJsonObj(response, map);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到删除线索操作");
        //获取前端发送的要删除线索的ID
        String id[] = request.getParameterValues("id");
        //创建动态代理实现类
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        //执行删除操作
        boolean flag = clueService.deleteByIds(id);
        PrintJson.printJsonFlag(response, flag);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入刷新线索列表操作");
        int pageNo = Integer.valueOf(request.getParameter("pageNo"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String fullname = request.getParameter("fullname");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String phone = request.getParameter("phone");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        int count = (pageNo-1) * pageSize;
        //将获取到的数据封装到Map集合中
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", pageSize);
        map.put("count", count);
        map.put("fullname", fullname);
        map.put("owner", owner);
        map.put("company", company);
        map.put("phone", phone);
        map.put("mphone", mphone);
        map.put("state", state);
        map.put("source", source);
        //创建动态代理实现类
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        PaginationVO paginationVo = clueService.pageList(map);
        //将返回的结果以json的格式返回到前端
        PrintJson.printJsonObj(response, paginationVo);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到获取用户列表操作");
        //获取动态代理实现类
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = userService.getUserList();
        //将获取到的数据以json的格式返回到前端
        PrintJson.printJsonObj(response, userList);
    }


    private void saveClue(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入添加线索操作");
        //获取前端发送过来的数据
        String id = UUIDUtil.getUUID();
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");
        //将获取到的数据封装到clue对象中
        Clue clue = new Clue();
        clue.setId(id);
        clue.setFullname(fullname);
        clue.setAppellation(appellation);
        clue.setOwner(owner);
        clue.setCompany(company);
        clue.setJob(job);
        clue.setEmail(email);
        clue.setPhone(phone);
        clue.setWebsite(website);
        clue.setMphone(mphone);
        clue.setState(state);
        clue.setSource(source);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);
        clue.setDescription(description);
        clue.setContactSummary(contactSummary);
        clue.setNextContactTime(nextContactTime);
        clue.setAddress(address);
        //创建动态代理实现类
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        //调用添加线索方法
        boolean flag = clueService.saveClue(clue);
        //将返回的结果以json的格式返回到前端页面中
        PrintJson.printJsonFlag(response, flag);
    }
}
