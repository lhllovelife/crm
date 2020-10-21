package cn.andylhl.crm.web.listener;

import cn.andylhl.crm.settings.domain.DicValue;
import cn.andylhl.crm.settings.service.DicService;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.settings.service.impl.DicServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * @Title: SystemInitListener
 * @Description:
 * @author: lhl
 * @date: 2020/10/15 22:24
 */
public class SystemInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("--上下文创建完毕---");
        //将数据字典加载到内存中来
        System.out.println("取数据字典开始");
        ServletContext application = event.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        DicService service = (DicService) ac.getBean("dicServiceImpl");
        Map<String, List<DicValue>> map = service.getAll();
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            List<DicValue> valueList = map.get(key);
            application.setAttribute(key, valueList);
        }
        System.out.println("取数据字典结束");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
