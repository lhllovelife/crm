package cn.andylhl.crm.workbench.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * @Title: ClueController
 * @Description: 线索控制器
 * @author: lhl
 * @date: 2020/10/15 21:34
 */

@WebServlet(urlPatterns = {"xxx"})
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
        if("/workbench/clue/xxx.do".equals(path)){

        }
        else {
            System.out.println("无效访问地址");
        }
    }

}
