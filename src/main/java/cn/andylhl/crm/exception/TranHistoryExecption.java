package cn.andylhl.crm.exception;

/***
 * @Title: TranHistoryExecption
 * @Description: 交易历史异常
 * @author: lhl
 * @date: 2020/10/26 12:43
 */
public class TranHistoryExecption extends Exception {

    public TranHistoryExecption() {
        super();
    }

    public TranHistoryExecption(String message) {
        super(message);
    }
}
