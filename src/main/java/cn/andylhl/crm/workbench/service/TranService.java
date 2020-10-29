package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.CustomerExecption;
import cn.andylhl.crm.exception.TranExecption;
import cn.andylhl.crm.exception.TranHistoryExecption;
import cn.andylhl.crm.workbench.domain.Tran;
import cn.andylhl.crm.workbench.domain.TranHistory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/***
 * @Title: TranService
 * @Description: 交易业务
 * @author: lhl
 * @date: 2020/10/27 22:00
 */
public interface TranService {

    //保存交易信息
    void save(Tran tran) throws CustomerExecption, TranExecption, TranHistoryExecption;

    //展示交易详细信息
    Tran detail(String id);

    //获取交易历史信息
    List<TranHistory> getHistoryList(String tranId);

    //更改交易阶段
    void changeStage(Tran tran) throws TranExecption, TranHistoryExecption;

    //获取交易漏斗图所需数据
    Map<String, Object> getCharts(HttpServletRequest request, HttpServletResponse response);
}
