package cn.andylhl.crm.settings;

import cn.andylhl.crm.settings.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/***
 * @Title: TestUserController
 * @Description:
 * @author: lhl
 * @date: 2020/10/8 18:31
 */
public class TestUserController {

    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ac.getBean("userServiceImpl");
        System.out.println("--UserService--" + userService.getClass().getName());
        userService.login("", "","" );

    }
}
