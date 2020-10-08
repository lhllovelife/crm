package cn.andylhl.crm.settings;

import cn.andylhl.crm.settings.dao.UserDao;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.MD5Util;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.HashMap;
import java.util.Map;

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
//        UserService userService = (UserService) ac.getBean("userServiceImpl");
//        System.out.println("--UserService--" + userService.getClass().getName());
//        userService.login("", "","" );
        UserDao userDao = (UserDao) ac.getBean("userDao");
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", "zs");
        map.put("loginPwd", MD5Util.getMD5("123"));
        User user = userDao.login(map);
        System.out.println("*--查询结果--");
        System.out.println(user);


    }
}
