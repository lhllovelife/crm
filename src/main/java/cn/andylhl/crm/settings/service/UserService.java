package cn.andylhl.crm.settings.service;

import cn.andylhl.crm.exception.LoginException;
import cn.andylhl.crm.settings.domain.User;

import java.util.List;

/***
 * @Title: UserService
 * @Description:
 * @author: lhl
 * @date: 2020/10/8 18:02
 */
public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();

}
