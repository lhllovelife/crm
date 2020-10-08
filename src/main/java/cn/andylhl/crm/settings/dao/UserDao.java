package cn.andylhl.crm.settings.dao;

import cn.andylhl.crm.settings.domain.User;

import java.util.Map;

/***
 * @Title: UserDao
 * @Description: 用户dao
 * @author: lhl
 * @date: 2020/10/8 18:00
 */
public interface UserDao {

    /**
     * 根据用户名和密码进行查询用户信息，封装到User对象中
     * @param map key1: loginAct key2 : loginPwd
     * @return User对象
     */
    User login(Map<String, Object> map);
}
