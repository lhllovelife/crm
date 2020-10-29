package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.CustomerExecption;
import cn.andylhl.crm.exception.TranExecption;
import cn.andylhl.crm.exception.TranHistoryExecption;
import cn.andylhl.crm.workbench.domain.Tran;

/***
 * @Title: TranService
 * @Description: 交易业务
 * @author: lhl
 * @date: 2020/10/27 22:00
 */
public interface TranService {

    //保存交易信息
    void save(Tran tran) throws CustomerExecption, TranExecption, TranHistoryExecption;
}
