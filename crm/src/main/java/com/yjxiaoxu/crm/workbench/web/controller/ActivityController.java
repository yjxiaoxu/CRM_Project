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
import com.yjxiaoxu.crm.workbench.domain.ActivityRemark;
import com.yjxiaoxu.crm.workbench.service.ActivityService;
import com.yjxiaoxu.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName:ActivityController
 * Package:com.yjxiaoxu.crm.workbench.web.controller
 * Description:
 *
 * @Date:2020/11/3 14:34
 * @Author:小许33058485@qq.com
 */
public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到市场活动控制器---------------------------------------------");
        //获取用户要进入的请求路径
        String path = request.getServletPath();
        if ("/workbench/activity/getUserList.do".equals(path)) {
            getUserList(request, response);
        } else if ("/workbench/activity/save.do".equals(path)) {
            save(request, response);
        } else if ("/workbench/activity/pageList.do".equals(path)) {
            pageList(request, response);
        } else if ("/workbench/activity/delete.do".equals(path)) {
            delete(request, response);
        } else if ("/workbench/activity/edit.do".equals(path)) {
            edit(request, response);
        } else if("/workbench/activity/update.do".equals(path)) {
            update(request, response);
        } else if ("/workbench/activity/detail.do".equals(path)) {
            detail(request, response);
        } else if ("/workbench/activity/refresh.do".equals(path)) {
            refresh(request, response);
        } else if ("/workbench/activity/deleteRemark.do".equals(path)) {
            deleteRemark(request, response);
        } else if ("/workbench/activity/saveRemark.do".equals(path)) {
            saveRemark(request, response);
        } else if("/workbench/activity/updateRemark.do".equals(path)) {
            updateRemark(request, response);
        }
    }

    private void updateRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入修改市场活动备注操作");
        //获取有前端发送的要修改的市场活动备注id
        String id = request.getParameter("id");
        //获取修改后的备注
        String noteContent = request.getParameter("noteContent");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "1";
        //创建市场活动备注对象
        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setEditTime(editTime);
        ar.setEditBy(editBy);
        ar.setEditFlag(editFlag);
        //创建动态代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.updateRemark(ar);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("ar", ar);
        //将map集合以json格式响应到前端
        PrintJson.printJsonObj(response, map);
    }

    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入添加备注操作");
        //获取前端发送的activityId和备注信息
        String activityId = request.getParameter("aid");
        String noteContent = request.getParameter("noteContent");
        //System.out.println(activityId);
        //System.out.println(noteContent);
        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "0";
        //创建一个市场活动备注
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setActivityId(activityId);
        activityRemark.setCreateBy(createBy);
        activityRemark.setCreateTime(createTime);
        activityRemark.setId(id);
        activityRemark.setEditFlag(editFlag);
        activityRemark.setNoteContent(noteContent);
        //创建动态代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Map<String,Object> map = activityService.saveRemark(activityRemark);
        //将map集合以json格式返回到前端
        PrintJson.printJsonObj(response, map);


    }

    private void deleteRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到删除市场活动备注操作");
        //获取前端发送的市场活动备注id
        String id = request.getParameter("arid");
        //创建动态代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.deleteRemarkById(id);
        PrintJson.printJsonFlag(response, flag);
    }

    private void refresh(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入刷新市场活动备注操作");
        //获取前端发送的市场活动id
        String id = request.getParameter("aid");
        //创建动态代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<ActivityRemark> atList = activityService.refreshById(id);
        //将返回的list集合以json的格式返回到前端
        PrintJson.printJsonObj(response, atList);

    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动详情操作");
        //获取用户要查看详情的市场活动的id
        String id = request.getParameter("id");
        System.out.println(id);
        //创建动态代理实现类对象
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        //通过市场活动id获取市场活动对象
        Activity activity = activityService.detailById(id);
        //将市场活动对象放到request域中
        request.setAttribute("activity", activity);
        //转发到detail.jsp页面中
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入更新市场活动操作");
        String id = request.getParameter("id");
        //获取前端页面发送的市场活动的所有者（获取的是对应的value值）
        String owner = request.getParameter("owner");
        //获取前端页面发送的市场活动的名称
        String name = request.getParameter("name");
        //获取前端页面发送的市场活动的开始时间
        String startDate = request.getParameter("startDate");
        //获取前端页面发送的市场活动结束的时间
        String endDate = request.getParameter("endDate");
        //获取前端页面发送的市场活动需要的成本
        String cost = request.getParameter("cost");
        //获取前端页面发送关于市场活动的相关描述
        String description = request.getParameter("description");
        //创建时间为系统当前时间，通过工具类获取
        String editeTime = DateTimeUtil.getSysTime();
        //创建人为当前系统用户，从session域中获取
        String editBy = ((User) request.getSession().getAttribute("user")).getName();
        System.out.println(id);
        System.out.println(owner);
        System.out.println(name);
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(cost);
        System.out.println();
        System.out.println();
        System.out.println();

        //创建市场活动对象，并将相关的信息封装到一个市场活动对象中
        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        activity.setEditTime(editeTime);
        activity.setEditBy(editBy);
        //创建代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        //调用修改市场信息方法
        boolean flag = activityService.updateById(activity);
        //将获取到的布尔值转换成json，并响应到前端
        PrintJson.printJsonFlag(response, flag);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入获取用户信息和市场活动信息操作");
        String id = request.getParameter("id");
        //System.out.println(id);
        //创建动态代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        //调用方法获取用户信息和市场活动信息
        Map<String, Object> map = activityService.getUserListAndActivity(id);
        //将获取到的map转换为json格式响应到前端
        PrintJson.printJsonObj(response, map);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入删除市场活动操作");
        //获取前端传送数据
        String ids[] = request.getParameterValues("id");
//        for (String param1 : ids) {
//            System.out.println(param1);
//        }
        //创建动态代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        //调用service层中的删除方法
        boolean flag = activityService.deleteByActivityId(ids);
        //将结果以json格式返回到前端页面
        PrintJson.printJsonFlag(response, flag);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查看市场活动对象操作");
        //获取前端输入的相关信息
        int pageNo = Integer.valueOf(request.getParameter("pageNo")); //页数
        int pageSize = Integer.valueOf(request.getParameter("pageSize")); //每一页展示多少条信息
        String name = request.getParameter("name"); //市场活动名称
        String owner = request.getParameter("owner");   //市场活动所有者
        String starDate = request.getParameter("startDate");    //市场活动开始日期
        String endDate = request.getParameter("endDate");   //市场活动结束日期
        //
//        System.out.println("pageSize----------->" + pageNo);
//        System.out.println("pageSize----------->" + pageSize);
//        System.out.println("name--------------->" + name);
//        System.out.println("owner-------------->" + owner);
//        System.out.println("starDate----------->" + starDate);
//        System.out.println("endDate------------->" + endDate);
        int count = (pageNo - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("pageSize", pageSize);
        map.put("name", name);
        map.put("owner", owner);
        map.put("starDate", starDate);
        map.put("endDate", endDate);
        //创建动态代理对象
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        //调用查看市场活动信息的方法
        PaginationVO paginationVo = activityService.pageList(map);
        //将map集合中的内容转换为json，并返回到前端
        PrintJson.printJsonObj(response,  paginationVo);
    }

    //提供一个保存市场活动信息的方法
    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到保存市场活动操作");
        //通过UUID工具类获取一个UUID
        String id = UUIDUtil.getUUID();
        //获取前端页面发送的市场活动的所有者（获取的是对应的value值）
        String owner = request.getParameter("owner");
        //获取前端页面发送的市场活动的名称
        String name = request.getParameter("name");
        //获取前端页面发送的市场活动的开始时间
        String startDate = request.getParameter("startDate");
        //获取前端页面发送的市场活动结束的时间
        String endDate = request.getParameter("endDate");
        //获取前端页面发送的市场活动需要的成本
        String cost = request.getParameter("cost");
        //获取前端页面发送关于市场活动的相关描述
        String description = request.getParameter("description");
        //创建时间为系统当前时间，通过工具类获取
        String createTime = DateTimeUtil.getSysTime();
        //创建人为当前系统用户，从session域中获取
        String createBy = ((User) request.getSession().getAttribute("user")).getName();


        //创建市场活动对象，并将相关的信息封装到一个市场活动对象中
        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);
        //创建代理实现类
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        //调用保存市场信息方法
        boolean flag = activityService.save(activity);
        //将获取到的布尔值转换成json，并响应到前端
        PrintJson.printJsonFlag(response, flag);
    }

    //获取所有用户信息
    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到获取用户集合操作");
        //创见动态代理实现类
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = userService.getUserList();
        //调用json工具类将字符串转换为json，并将其响应回前端页面
        PrintJson.printJsonObj(response, userList);
    }
}
