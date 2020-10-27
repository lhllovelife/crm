package cn.andylhl.crm.workbench.service;

import java.util.List;

/***
 * @Title: CustomerService
 * @Description: 客户业务
 * @author: lhl
 * @date: 2020/10/27 19:02
 */
public interface CustomerService {

    //根据名字模糊查询表中客户姓名
    List<String> getCustomerName(String name);
}
