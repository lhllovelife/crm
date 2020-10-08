package cn.andylhl.crm.settings.service;

import cn.andylhl.crm.settings.domain.User;

/***
 * @Title: UserService
 * @Description:
 * @author: lhl
 * @date: 2020/10/8 18:02
 */
public interface UserService {

    User login(String logAct, String logPwd, String ip);
}
