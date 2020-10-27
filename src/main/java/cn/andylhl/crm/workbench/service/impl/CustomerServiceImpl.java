package cn.andylhl.crm.workbench.service.impl;

import cn.andylhl.crm.workbench.dao.CustomerDao;
import cn.andylhl.crm.workbench.domain.Customer;
import cn.andylhl.crm.workbench.service.CustomerService;

import java.util.List;

/***
 * @Title: CustomerServiceImpl
 * @Description: 客户业务实现类
 * @author: lhl
 * @date: 2020/10/27 19:02
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 根据名字模糊查询表中客户姓名
     * @param name
     * @return
     */
    @Override
    public List<String> getCustomerName(String name) {
        return customerDao.getCustomerName(name);
    }
}
