package cn.andylhl.crm.settings.web.controller;

import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    }
}
