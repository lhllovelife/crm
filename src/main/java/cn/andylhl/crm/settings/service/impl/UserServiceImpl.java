package cn.andylhl.crm.settings.service.impl;

import cn.andylhl.crm.exception.LoginException;
import cn.andylhl.crm.settings.dao.UserDao;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.Const;
import cn.andylhl.crm.utils.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 登录验证
     * @param loginAct
     * @param loginPwd
     * @param ip
     * @return
     */
    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        User user = userDao.login(map);
        //1. 验证用户名密码
        if (user == null){
            //查询不到结果
            throw new LoginException("用户名密码不正确");
        }
        //2. 验证失效时间
        String currentTime = DateUtil.format(new Date(), Const.DATE_Format_ALL);
        if (user.getExpireTime().compareTo(currentTime) < 0){
            // > 0 有效  | < 0 失效
            throw new LoginException("该账号已失效");
        }
        //3. 验证锁定状态
        if ("0".equals(user.getLockState())){
            throw new LoginException("该账号已被锁定，请联系管理员");
        }
        //4. 验证ip地址
        if (!user.getAllowIps().contains(ip)){
            throw new LoginException("该ip地址禁止访问");
        }
        //指定到这里，验证成功
        return user;
    }

    /**
     * 获取所有用户信息封装到一个List集合中
     * @return
     */
    @Override
    public List<User> getUserList() {

        return userDao.getUerList();
    }
}
