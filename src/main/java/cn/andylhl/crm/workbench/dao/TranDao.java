package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.Tran;

/***
 * @Title: TranDao
 * @Description: 交易dao
 * @author: lhl
 * @date: 2020/10/25 21:40
 */
public interface TranDao {

    //保存交易信息
    int save(Tran tran);

    //获取交易详细信息
    Tran detail(String id);

    //变更交易状态
    int update(Tran tran);
}
