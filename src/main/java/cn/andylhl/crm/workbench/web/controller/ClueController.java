package cn.andylhl.crm.workbench.web.controller;

import cn.andylhl.crm.exception.ClueExecption;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.*;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.domain.Clue;
import cn.andylhl.crm.workbench.service.ActivityService;
import cn.andylhl.crm.workbench.service.ClueService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @Title: ClueController
 * @Description: 线索控制器
 * @author: lhl
 * @date: 2020/10/21 19:32
 */
@WebServlet(urlPatterns = {"/workbench/clue/getUserList.do", "/workbench/clue/save.do", "/workbench/clue/pageList.do", "/workbench/clue/delete.do", "/workbench/clue/getUserListAndClueById.do", "/workbench/clue/update.do", "/workbench/clue/detail.do"})
public class ClueController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*使用模板方设计模式，算法骨架*/
        System.out.println("进入到ClueController");

        String path = request.getServletPath();
        if ("/workbench/clue/getUserList.do".equals(path)) {
            getUserList(request, response);
        }
        else if ("/workbench/clue/save.do".equals(path)){
            save(request, response);
        }
        else if ("/workbench/clue/pageList.do".equals(path)){
            pageList(request, response);
        }
        else if ("/workbench/clue/delete.do".equals(path)){
            delete(request, response);
        }
        else if ("/workbench/clue/getUserListAndClueById.do".equals(path)){
            getUserListAndClueById(request, response);
        }
        else if ("/workbench/clue/update.do".equals(path)){
            update(request, response);
        }
        else if ("/workbench/clue/detail.do".equals(path)){
            detail(request, response);
        }
        else {
            System.out.println("无效访问地址");
        }
    }

    /**
     * 跳转到详细信息页
     * @param request
     * @param response
     */
    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行跳转到详细信息页");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");
        String id = request.getParameter("id");
        Clue clue = service.getDetailById(id);
        request.setAttribute("clue", clue);
        request.getRequestDispatcher("/workbench/clue/detail.jsp").forward(request, response);
    }

    /**
     * 线索对象信息修改
     * @param request
     * @param response
     */
    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行线索对象信息更新操作");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");
        Clue clue = new Clue();
        WebUtil.makeRequestToObject(request, clue);
        String editBy = ((User) request.getSession().getAttribute("user")).getName();
        clue.setEditBy(editBy);
        clue.setEditTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        try {
            service.update(clue);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            PrintJson.printJsonFlag(response, false);
            e.printStackTrace();
        }
/*
Clue{

id='9151f19f53f54fe9b0d68af6c1e8d866',
fullname='张勇',
appellation='先生',
owner='40f6cdea0bd34aceb77492a1656d9fb3',
company='阿里妈妈',
job='CEO',
email='zy@alimama.com',
phone='阿里妈妈公司座机123',
website='alimama.com',
mphone='1333636',
state='试图联系',
source='广告',
createBy='null', createTime='null',
editBy='null', editTime='null',
description='更新描述', contactSummary='更新联系纪要',
nextContactTime='2020-10-24',
address='杭州市阿里妈妈地址更细'
}
 */
    }

    /**
     * 根据线索线索对象id查询线索对象信息，并且获取用户列表
     * @param request
     * @param response
     */
    private void getUserListAndClueById(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取用户列表和市对象信息");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        UserService userService = (UserService) ac.getBean("userServiceImpl");
        ClueService clueService = (ClueService) ac.getBean("clueServiceImpl");
        //获取参数
        String id = request.getParameter("id");
        List<User> userList = userService.getUserList();
        Clue clue = clueService.getClueById(id);
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("clue", clue);
        PrintJson.printJsonObj(response, map);
    }

    /**
     * 删除线索对象，及线索对象备注，及线索对象与市场活动之间关系
     * @param request
     * @param response
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行线索对象删除操作");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");
        String[] ids = request.getParameterValues("id");
        try {
            service.deleteByIds(ids);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            PrintJson.printJsonFlag(response, false);
            e.printStackTrace();
        }

    }

    /**
     * 分页查询线索信息（带参数）
     * @param request
     * @param response
     */
    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("分页查询线索信息（带参数）");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        pageNo = (pageNo - 1) * pageSize;

        String fullname  = request.getParameter("fullname");
        String company = request.getParameter("company");
        String phone = request.getParameter("phone");
        String source = request.getParameter("source");
        String owner = request.getParameter("owner");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("pageNo", pageNo);
        conditionMap.put("pageSize", pageSize);
        conditionMap.put("fullname", fullname);
        conditionMap.put("company", company);
        conditionMap.put("phone", phone);
        conditionMap.put("source", source);
        conditionMap.put("owner", owner);
        conditionMap.put("mphone", mphone);
        conditionMap.put("state", state);
        PaginationVO<Clue> paginationVO = service.pageList(conditionMap);
        PrintJson.printJsonObj(response, paginationVO);
    }

    /**
     * 保存线索对象
     * @param request
     * @param response
     */
    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行保存线索对象操作");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        //获取参数
        Clue clue = new Clue();
        WebUtil.makeRequestToObject(request,clue);
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateBy(createBy);
        clue.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        try {
            service.save(clue);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            PrintJson.printJsonFlag(response, false);
            e.printStackTrace();
        }
    }

    /**
     * 获取用户列表
     * @param request
     * @param response
     */
    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取用户列表");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        UserService service = (UserService) ac.getBean("userServiceImpl");
        List<User> userList = service.getUserList();
        PrintJson.printJsonObj(response, userList);
    }
}