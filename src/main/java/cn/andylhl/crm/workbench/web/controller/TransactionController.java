package cn.andylhl.crm.workbench.web.controller;


import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.PrintJson;
import cn.andylhl.crm.workbench.domain.Customer;
import cn.andylhl.crm.workbench.service.CustomerService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedReader;
import java.util.List;

/***
 * @Title: TransactionController
 * @Description: 交易控制器
 * @author: lhl
 * @date: 2020/10/27 18:05
 */
@WebServlet(urlPatterns = {"/workbench/transaction/getUserList.do", "/workbench/transaction/getCustomerName.do"})
public class TransactionController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*使用模板方设计模式，算法骨架*/
        System.out.println("进入到交易控制器");

        String path = request.getServletPath();
        if ("/workbench/transaction/getUserList.do".equals(path)) {
            getUserList(request, response);
        }
        else if ("/workbench/transaction/getCustomerName.do".equals(path)){
            getCustomerName(request, response);
        }
        else {
            System.out.println("无效访问地址");
        }
    }

    /**
     * 根据名字模糊查询表中客户姓名
     * @param request
     * @param response
     */
    private void getCustomerName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取客户列表列表");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        CustomerService service = (CustomerService) ac.getBean("customerServiceImpl");
        //接收参数
        String name = request.getParameter("name");
        List<String> cusnameList = service.getCustomerName(name);
        PrintJson.printJsonObj(response, cusnameList);
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
