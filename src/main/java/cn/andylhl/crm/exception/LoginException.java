package cn.andylhl.crm.exception;

/***
 * @Title: LoginException
 * @Description: 登录异常
 * @author: lhl
 * @date: 2020/10/8 19:56
 */
public class LoginException extends Exception{

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }
}
