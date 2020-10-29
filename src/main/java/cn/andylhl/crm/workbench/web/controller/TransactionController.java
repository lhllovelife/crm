package cn.andylhl.crm.workbench.web.controller;


import cn.andylhl.crm.exception.CustomerExecption;
import cn.andylhl.crm.exception.TranExecption;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.*;
import cn.andylhl.crm.workbench.domain.Customer;
import cn.andylhl.crm.workbench.domain.Tran;
import cn.andylhl.crm.workbench.service.CustomerService;
import cn.andylhl.crm.workbench.service.TranService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedReader;
import java.util.Date;
import java.util.List;

/***
 * @Title: TransactionController
 * @Description: 交易控制器
 * @author: lhl
 * @date: 2020/10/27 18:05
 */
@WebServlet(urlPatterns = {"/workbench/transaction/getUserList.do", "/workbench/transaction/getCustomerName.do", "/workbench/transaction/save.do"})
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
        else if ("/workbench/transaction/save.do".equals(path)){
            save(request, response);
        }
        else {
            System.out.println("无效访问地址");
        }
    }

    /**
     * 保存交易
     * @param request
     * @param response
     */
    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行保存交易信息");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        TranService service = (TranService) ac.getBean("tranServiceImpl");
        //接收参数
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        Tran tran = new Tran();
        WebUtil.makeRequestToObject(request, tran);
        //当前对象中 customerId属性保存的是客户名字
        tran.setId(UUIDUtil.getUUID());
        tran.setCreateBy(createBy);
        tran.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        try {
            service.save(tran);
            //添加成功后重定向到列表页
            response.sendRedirect(request.getContextPath() + "/workbench/transaction/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
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
