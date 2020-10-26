package cn.andylhl.crm.exception;

/***
 * @Title: CustomerExecption
 * @Description: 客户相关异常
 * @author: lhl
 * @date: 2020/10/26 9:32
 */
public class CustomerExecption extends Exception {

    public CustomerExecption() {
        super();
    }

    public CustomerExecption(String message) {
        super(message);
    }
}
