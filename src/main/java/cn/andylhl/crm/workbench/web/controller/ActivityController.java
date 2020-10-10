package cn.andylhl.crm.workbench.web.controller;

import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.Const;
import cn.andylhl.crm.utils.DateUtil;
import cn.andylhl.crm.utils.PrintJson;
import cn.andylhl.crm.utils.UUIDUtil;
import cn.andylhl.crm.utils.WebUtil;
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
import java.util.List;

/***
 * @Title: ActivityController
 * @Description: 市场活动模块控制器
 * @author: lhl
 * @date: 2020/10/9 15:19
 */

@WebServlet(urlPatterns = {"/workbench/activity/getUserList.do","/workbench/activity/save.do"})
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
        else {
            System.out.println("无效访问地址");
        }
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
