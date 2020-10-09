package cn.andylhl.crm.workbench.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * @Title: ActivityController
 * @Description: 市场活动模块控制器
 * @author: lhl
 * @date: 2020/10/9 15:19
 */

@WebServlet(urlPatterns = {"/workbench/activity/xxx.do"})
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
        if("/settings/user/login.do".equals(path)){
            //
        }
        else {

        }
    }
}
