package cn.andylhl.crm.controller.temp;

import cn.andylhl.crm.domain.Student;
import cn.andylhl.crm.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/***
 * @Title: StudentController
 * @Description: 学生模块
 * @author: lhl
 * @date: 2020/10/6 15:55
 */

@WebServlet(urlPatterns = {"/Student/insert.do"})
public class StudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        if ("/Student/insert.do".equals(path)){
            doInsert(request, response);
        }
    }

    /**
     * 处理新增学生请求
     * @param request
     * @param response
     */
    private void doInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        Integer age = Integer.valueOf(request.getParameter("age"));
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService service = (StudentService) ac.getBean("studentService");
        Student student = new Student("A_0005", name, age);
        int count = service.insertStu(student);
        //{"success" : true}
        String json = "{\"success\" : " + (count == 1)+ "}";
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
    }
}
