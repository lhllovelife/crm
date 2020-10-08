package cn.andylhl.crm.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/***
 * @Title: LoginFilter
 * @Description: 登录拦截过滤器
 * @author: lhl
 * @date: 2020/10/8 22:17
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入登录拦截过滤器");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        String path = request.getServletPath();
        if ("/settings/user/login.do".equals(path) || "/login.jsp".equals(path)){
            //登录页面和处理登录的接口允许访问
            chain.doFilter(request,response);
        }
        else {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") != null){
                //session存在该用户信息
                chain.doFilter(request,response);
            }
            else {
                //重定向到登录页面
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
