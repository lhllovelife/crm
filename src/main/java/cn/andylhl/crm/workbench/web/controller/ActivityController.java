package cn.andylhl.crm.workbench.web.controller;

import cn.andylhl.crm.exception.ActivityExecption;
import cn.andylhl.crm.exception.ActivityRemarkExecption;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.Const;
import cn.andylhl.crm.utils.DateUtil;
import cn.andylhl.crm.utils.PrintJson;
import cn.andylhl.crm.utils.UUIDUtil;
import cn.andylhl.crm.utils.WebUtil;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.domain.ActivityRemark;
import cn.andylhl.crm.workbench.service.ActivityService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @Title: ActivityController
 * @Description: 市场活动模块控制器
 * @author: lhl
 * @date: 2020/10/9 15:19
 */

@WebServlet(urlPatterns = {"/workbench/activity/getUserList.do","/workbench/activity/save.do", "/workbench/activity/pageList.do", "/workbench/activity/delete.do", "/workbench/activity/getUserListAndActivity.do", "/workbench/activity/update.do", "/workbench/activity/detail.do", "/workbench/activity/getRemarkListByAid.do", "/workbench/activity/deleteRemark.do", "/workbench/activity/saveRemark.do"})
public class ActivityController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*使用模板方设计模式，算法骨架*/
        System.out.println("进入到ActivityController");

        String path = request.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request, response);
        }
        else if ("/workbench/activity/save.do".equals(path)){
            save(request, response);
        }
        else if ("/workbench/activity/pageList.do".equals(path)){
            pageList(request, response);
        }
        else if("/workbench/activity/delete.do".equals(path)){
            delete(request, response);
        }
        else if ("/workbench/activity/getUserListAndActivity.do".equals(path)){
            getUserListAndActivity(request, response);
        }
        else if ("/workbench/activity/update.do".equals(path)){
            update(request, response);
        }
        else if ("/workbench/activity/detail.do".equals(path)){
            detail(request, response);
        }
        else if("/workbench/activity/getRemarkListByAid.do".equals(path)){
            getRemarkListByAid(request, response);
        }
        else if("/workbench/activity/deleteRemark.do".equals(path)){
            deleteRemark(request, response);
        }
        else if ("/workbench/activity/saveRemark.do".equals(path)){
            saveRemark(request, response);
        }
        else {
            System.out.println("无效访问地址");
        }
    }

    /**
     * 保存备注信息
     * @param request
     * @param response
     */
    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到保存备注信息控制器");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        String id = request.getParameter("id");
        String noteContent = request.getParameter("noteContent");
        ActivityRemark remark = new ActivityRemark();
        /*
    private String id; //主键
    private String noteContent; //备注内容
    private String createTime; //创建时间 19位 年月日时分秒
    private String createBy; //创建人 varchar(255)
    private String editTime; //修改时间 19位
    private String editBy; //修改人 varchar(255)
    private String editFlag; //修改标记 cahr(1)
    private String activityId; //市场活动id
         */
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        remark.setId(UUIDUtil.getUUID());
        remark.setNoteContent(noteContent);
        remark.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        remark.setCreateBy(user.getName());
        remark.setEditFlag("0");
        remark.setActivityId(id);
        try {
            service.saveRemark(remark);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            e.printStackTrace();
            PrintJson.printJsonFlag(response, false);
        }
    }

    /**
     * 删除指定id的备注信息
     * @param request
     * @param response
     */
    private void deleteRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到删除市场活动备注信息控制器");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        String id = request.getParameter("id");
        try {
            service.deleteRemark(id);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            e.printStackTrace();
            PrintJson.printJsonFlag(response, false);
        }
    }

    /**
     * 根据id获取市场活动备注信息
     * @param request
     * @param response
     */
    private void getRemarkListByAid(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到获取市场活动备注控制器");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        String id = request.getParameter("id");
        List<ActivityRemark> remarkList = service.getRemarkListByAid(id);
        PrintJson.printJsonObj(response, remarkList);
    }

    /**
     *展现市场活动详细信息列表
     * @param request
     * @param response
     */
    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到展现市场活动信息控制器");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        //获取参数（活动id）
        String id = request.getParameter("id");
        //通过id查询该活动的详细信息
        Activity activity = service.getActDetatilById(id);
        //将查询信息放置request域中进行跳转
        request.setAttribute("a", activity);
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request, response);
    }

    /**
     * 更新市场活动
     * @param request
     * @param response
     */
    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到市场活动更新");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        //接收参数
        Activity activity = new Activity();
        WebUtil.makeRequestToObject(request, activity);
        HttpSession session = request.getSession(false);
        String editBy = ((User)session.getAttribute("user")).getName();
        activity.setEditBy(editBy);
        activity.setEditTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        //执行更新
        try {
            service.updateActivity(activity);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            e.printStackTrace();
            PrintJson.printJsonFlag(response, false);
        }


    }

    /**
     * 获取用户对象列表和市场活动对象
     * @param request
     * @param response
     */
    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到获取用户对象列表和市场活动对象");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService activityService = (ActivityService) ac.getBean("activityServiceImpl");
        UserService userService = (UserService) ac.getBean("userServiceImpl");
        //获取参数
        String id = request.getParameter("id");
        //用户列表
        List<User> userList = userService.getUserList();
        //活动对象
        Activity activity = activityService.getActById(id);
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("act", activity);
        PrintJson.printJsonObj(response, map);
    }

    /**
     * 市场活动单个删除和批量删除
     * @param request
     * @param response
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("市场活动单个删除和批量删除");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        //准备数据
        String[] ids = request.getParameterValues("id");
        try {
            service.deleteAct(ids);
            //能执行到这里，说明没有异常发生
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            e.printStackTrace();
            //执行到这里，说明出现异常
            PrintJson.printJsonFlag(response, false);
        }
    }

    /**
     * 市场活动带参数分页查询
     * @param request
     * @param response
     */
    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到市场活动带参数分页查询");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        /*
        pageNo : pageNo,
        pageSize: pageSize,
        name: $.trim($("#serach-name").val()),
        owner: $.trim($("#serach-owner").val()),
        startDate: $.trim($("#serach-startDate").val()),
        endDate: $.trim($("#end-endDate").val())

         */
        //接收参数
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
        int pageNo = Integer.valueOf(pageNoStr);
        int pageSize = Integer.valueOf(pageSizeStr);
        pageNo = (pageNo - 1) * pageSize;
        //封装参数
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("pageNo", pageNo);
        conditionMap.put("pageSize", pageSize);
        conditionMap.put("name", name);
        conditionMap.put("owner", owner);
        conditionMap.put("startDate", startDate);
        conditionMap.put("endDate", endDate);
        PaginationVO<Activity> paginationVO = service.pageList(conditionMap);
        //将返回分页VO对象相应
        PrintJson.printJsonObj(response, paginationVO);

    }

    /**
     * 保存市场活动信息
     * @param request
     * @param response
     */
    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到保存市场活动信息");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        Activity activity = new Activity();
        WebUtil.makeRequestToObject(request, activity);
        System.out.println("工具封装的对象内容");
        /*
        Activity{
            id='null',
            owner='40f6cdea0bd34aceb77492a1656d9fb3',
            name='发传单1',
            startDate='2020-10-10',
            endDate='2020-10-22',
            cost='1000',
            description='描述123',
            createTime='null', createBy='null',
            editTime='null', editBy='null'}
         */
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        HttpSession session = request.getSession(false);
        activity.setCreateBy( ((User)session.getAttribute("user")).getName() );
        try {
            service.save(activity);
            //若未出现异常，则继续向下执行
            //响应json
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            e.printStackTrace();
            //到这里，说明出现异常
            PrintJson.printJsonFlag(response, false);

        }
    }

    /**
     * 获取所有用户信息
     * @param request
     * @param response
     */
    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到获取所有用户信息");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        UserService service = (UserService) ac.getBean("userServiceImpl");
        List<User> userList = service.getUserList();
        PrintJson.printJsonObj(response, userList);
    }

}
