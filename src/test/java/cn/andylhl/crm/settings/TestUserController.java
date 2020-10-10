package cn.andylhl.crm.settings;

import cn.andylhl.crm.exception.ActivityExecption;
import cn.andylhl.crm.settings.dao.UserDao;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.MD5Util;
import cn.andylhl.crm.utils.UUIDUtil;
import cn.andylhl.crm.workbench.dao.ActivityDao;
import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.service.ActivityService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.HashMap;
import java.util.List;
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

    @Test
    public void test02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) ac.getBean("userDao");
        List<User> userList = userDao.getUerList();
        for (User u : userList){
            System.out.println(u);
        }
    }

    @Test
    public void test003(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        System.out.println("servcice: "+ service.getClass().getName());
        Activity activity = new Activity();
        activity.setId(UUIDUtil.getUUID());
        try {
            service.save(activity);
            System.out.println("没有异常出现");
        } catch (ActivityExecption activityExecption) {
            activityExecption.printStackTrace();
            System.out.println("异常已经被捕捉");
        }
    }
}
