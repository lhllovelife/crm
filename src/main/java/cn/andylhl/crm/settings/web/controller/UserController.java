package cn.andylhl.crm.settings.web.controller;

import cn.andylhl.crm.exception.LoginException;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.MD5Util;
import cn.andylhl.crm.utils.PrintJson;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 * @Title: UserController
 * @Description: 用户控制器
 * @author: lhl
 * @date: 2020/10/8 18:03
 */
@WebServlet(urlPatterns = {"/settings/user/login.do"})
public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*使用模板方设计模式，算法骨架*/
        System.out.println("进入到UserController");
        String path = request.getServletPath();
        if("/settings/user/login.do".equals(path)){
            doLogin(request, response);
        }
    }

    /**
     * 验证登录
     * @param request
     * @param response
     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到验证登录");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        UserService service = (UserService) ac.getBean("userServiceImpl");
        //获取参数
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        loginPwd = MD5Util.getMD5(loginPwd);
//        String ip = request.getServerName();
        String ip = request.getRemoteAddr();
        System.out.println("来访ip: " + ip);
        try {
            User user = service.login(loginAct, loginPwd, ip);
            //将登录的用户信息存入session中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //响应json
            PrintJson.printJsonFlag(response, true);

        } catch (LoginException e) {
            e.printStackTrace();
            //获取异常信息
            String msg = e.getMessage();
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("success", false);
            resMap.put("msg", msg);
            PrintJson.printJsonObj(response, resMap);
        }


    }
}
