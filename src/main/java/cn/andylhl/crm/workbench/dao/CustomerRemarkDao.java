package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.CustomerRemark;

/***
 * @Title: CustomerRemark
 * @Description: 客户备注dao
 * @author: lhl
 * @date: 2020/10/25 21:54
 */
public interface CustomerRemarkDao {

    //保存客户备注信息
    int save(CustomerRemark customerRemark);
}
