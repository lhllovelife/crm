package cn.andylhl.crm.settings.service.impl;

import cn.andylhl.crm.settings.dao.UserDao;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;

/***
 * @Title: UserServiceImpl
 * @Description: 用户相关的业务处理
 * @author: lhl
 * @date: 2020/10/8 18:02
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    //提供set方法，在配置文件中进行注入

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String logAct, String logPwd, String ip) {
        System.out.println("UserService 登录方法");
        return new User();
    }

}
