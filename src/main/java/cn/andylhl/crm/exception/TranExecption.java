package cn.andylhl.crm.exception;

/***
 * @Title: TranExecption
 * @Description: 交易异常
 * @author: lhl
 * @date: 2020/10/26 12:27
 */
public class TranExecption extends Exception {

    public TranExecption() {
        super();
    }

    public TranExecption(String message) {
        super(message);
    }
}
