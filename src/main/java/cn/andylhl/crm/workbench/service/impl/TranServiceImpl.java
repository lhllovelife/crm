package cn.andylhl.crm.workbench.service.impl;

import cn.andylhl.crm.exception.CustomerExecption;
import cn.andylhl.crm.exception.TranExecption;
import cn.andylhl.crm.exception.TranHistoryExecption;
import cn.andylhl.crm.utils.Const;
import cn.andylhl.crm.utils.DateUtil;
import cn.andylhl.crm.utils.UUIDUtil;
import cn.andylhl.crm.workbench.dao.CustomerDao;
import cn.andylhl.crm.workbench.dao.TranDao;
import cn.andylhl.crm.workbench.dao.TranHistoryDao;
import cn.andylhl.crm.workbench.domain.Customer;
import cn.andylhl.crm.workbench.domain.Tran;
import cn.andylhl.crm.workbench.domain.TranHistory;
import cn.andylhl.crm.workbench.service.TranService;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

/***
 * @Title: TranServiceImpl
 * @Description: 交易业务实现类
 * @author: lhl
 * @date: 2020/10/27 22:00
 */
public class TranServiceImpl implements TranService {

    private TranDao tranDao;
    private CustomerDao customerDao;
    private TranHistoryDao tranHistoryDao;

    public void setTranDao(TranDao tranDao) {
        this.tranDao = tranDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setTranHistoryDao(TranHistoryDao tranHistoryDao) {
        this.tranHistoryDao = tranHistoryDao;
    }

    /**
     * 保存交易信息
     * @param tran
     */
    @Override
    public void save(Tran tran) throws CustomerExecption, TranExecption, TranHistoryExecption {
        /*如果没有客户，则新创建客户
        交易添加
        交易历史添加*/
        String customerName = tran.getCustomerId();
        //精确查询是否有该客户
        Customer customer = customerDao.getCustomerByName(customerName);
        if (customer == null){
            //如果该客户不存在，则新创建客户
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setOwner(tran.getOwner());
            customer.setName(customerName);
            customer.setCreateBy(tran.getCreateBy());
            customer.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
            customer.setContactSummary(tran.getContactSummary());
            customer.setNextContactTime(tran.getNextContactTime());
            customer.setDescription(tran.getDescription());
            int count1 = customerDao.save(customer);
            if (count1 != 1){
                throw new CustomerExecption("客户信息保存异常");
            }
        }
        //交易添加
        tran.setCustomerId(customer.getId());
        int count2 = tranDao.save(tran);
        if (count2 != 1){
            throw new TranExecption("交易信息保存异常");
        }
        //交易历史添加
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setStage(tran.getStage());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setCreateBy(tran.getCreateBy());
        tranHistory.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        tranHistory.setTranId(tran.getId());
        int count3 = tranHistoryDao.save(tranHistory);
        if (count3 != 1){
            throw new TranHistoryExecption("交易历史信息保存异常");
        }
    }

    /**
     * 展示交易详细信息
     * @param id
     * @return
     */
    @Override
    public Tran detail(String id) {
        return tranDao.detail(id);
    }
}
