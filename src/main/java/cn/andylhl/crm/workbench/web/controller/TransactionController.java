package cn.andylhl.crm.workbench.web.controller;

import cn.andylhl.crm.exception.TranExecption;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.*;
import cn.andylhl.crm.workbench.domain.Tran;
import cn.andylhl.crm.workbench.domain.TranHistory;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @Title: TransactionController
 * @Description: 交易控制器
 * @author: lhl
 * @date: 2020/10/27 18:05
 */
@WebServlet(urlPatterns = {"/workbench/transaction/getUserList.do", "/workbench/transaction/getCustomerName.do", "/workbench/transaction/save.do", "/workbench/transaction/detail.do", "/workbench/transaction/getHistoryList.do", "/workbench/transaction/changetage.do"})
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
        else if ("/workbench/transaction/detail.do".equals(path)){
            detail(request, response);
        }
        else if ("/workbench/transaction/getHistoryList.do".equals(path)){
            getHistoryList(request, response);
        }
        else if ("/workbench/transaction/changetage.do".equals(path)){
            changetage(request, response);
        }
        else {
            System.out.println("无效访问地址");
        }
    }

    /**
     * 更改交易阶段
     * @param request
     * @param response
     */
    private void changetage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行更新交易阶段");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        TranService service = (TranService) ac.getBean("tranServiceImpl");
        //接收参数
        String tranId = request.getParameter("tranId");
        String stage = request.getParameter("stage");
        String money = request.getParameter("money");
        String expectedDate = request.getParameter("expectedDate");
        String editBy = ((User) request.getSession().getAttribute("user")).getName();

        Tran tran = new Tran();
        tran.setId(tranId);
        tran.setStage(stage);
        tran.setMoney(money);
        tran.setExpectedDate(expectedDate);
        tran.setEditBy(editBy);
        tran.setEditTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        //更改交易阶段
        Map<String, Object> map = new HashMap<>();
        try {
            service.changeStage(tran);
            map.put("success", true);
            Tran t = service.detail(tranId);
            Map<String, String> pMap = (Map<String, String>) request.getServletContext().getAttribute("pMap");
            String possibility = pMap.get(t.getStage());
            t.setPossibility(possibility);
            map.put("t", t);
            PrintJson.printJsonObj(response, map);

        } catch (Exception e) {
            map.put("success", false);
            PrintJson.printJsonObj(response, map);
            e.printStackTrace();
        }
    }

    /**
     * 获取交易历史信息
     * @param request
     * @param response
     */
    private void getHistoryList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行获取交易历史信息");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        TranService service = (TranService) ac.getBean("tranServiceImpl");
        String tranId = request.getParameter("tranId");
        List<TranHistory> tranHistoryList = service.getHistoryList(tranId);
        //处理可能性
        Map<String, String> pMap = (Map<String, String>) request.getServletContext().getAttribute("pMap");
        for (TranHistory tranHistory : tranHistoryList){
            String stage = tranHistory.getStage();
            String possibility = pMap.get(stage);
            tranHistory.setPossibility(possibility);
        }

        PrintJson.printJsonObj(response, tranHistoryList);
    }

    /**
     * 展示交易详细信息
     * @param request
     * @param response
     */
    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行展示交易详细信息");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        TranService service = (TranService) ac.getBean("tranServiceImpl");
        String id = request.getParameter("id");
        Tran tran = service.detail(id);
        //处理可能性数值
        Map<String, String> pMap = (Map<String, String>) request.getServletContext().getAttribute("pMap");
        String possibility = pMap.get(tran.getStage());
        tran.setPossibility(possibility);
        request.setAttribute("tran", tran);
        request.getRequestDispatcher("/workbench/transaction/detail.jsp").forward(request, response);
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
