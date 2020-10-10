package cn.andylhl.crm.workbench.web.controller;

import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.Const;
import cn.andylhl.crm.utils.DateUtil;
import cn.andylhl.crm.utils.PrintJson;
import cn.andylhl.crm.utils.UUIDUtil;
import cn.andylhl.crm.utils.WebUtil;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Activity;
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

@WebServlet(urlPatterns = {"/workbench/activity/getUserList.do","/workbench/activity/save.do", "/workbench/activity/pageList.do"})
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
        else {
            System.out.println("无效访问地址");
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
