package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.Customer;

/***
 * @Title: CustomerDao
 * @Description: 客户dao
 * @author: lhl
 * @date: 2020/10/25 21:53
 */
public interface CustomerDao {

    //根据公司的名称精确匹配，判断该客户是否存在
    Customer getCustomerByName(String companyName);

    //将提取的客户信息进行存储
    int save(Customer customer);
}
