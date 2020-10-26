package cn.andylhl.crm.workbench.service.impl;

import cn.andylhl.crm.exception.*;
import cn.andylhl.crm.utils.Const;
import cn.andylhl.crm.utils.DateUtil;
import cn.andylhl.crm.utils.UUIDUtil;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.dao.*;
import cn.andylhl.crm.workbench.domain.*;
import cn.andylhl.crm.workbench.service.ClueService;

import java.util.*;

/***
 * @Title: ClueServiceImpl
 * @Description: 线索业务实现类
 * @author: lhl
 * @date: 2020/10/22 14:53
 */
public class ClueServiceImpl implements ClueService {
    private ActivityDao activityDao;
    //线索相关dao
    private ClueDao clueDao;
    private ClueRemarkDao clueRemarkDao;
    private ClueActivityRelationDao clueActivityRelationDao;
    //客户相关dao
    private CustomerDao customerDao;
    private CustomerRemarkDao customerRemarkDao;
    //联系人相关dao
    private ContactsDao contactsDao;
    private ContactsRemarkDao contactsRemarkDao;
    private ContactsActivityRelationDao contactsActivityRelationDao;
    //交易相关dao
    private TranDao tranDao;
    private TranRemarkDao tranRemarkDao;
    private TranHistoryDao tranHistoryDao;

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void setClueDao(ClueDao clueDao) {
        this.clueDao = clueDao;
    }

    public void setClueRemarkDao(ClueRemarkDao clueRemarkDao) {
        this.clueRemarkDao = clueRemarkDao;
    }

    public void setClueActivityRelationDao(ClueActivityRelationDao clueActivityRelationDao) {
        this.clueActivityRelationDao = clueActivityRelationDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setCustomerRemarkDao(CustomerRemarkDao customerRemarkDao) {
        this.customerRemarkDao = customerRemarkDao;
    }

    public void setContactsDao(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    public void setContactsRemarkDao(ContactsRemarkDao contactsRemarkDao) {
        this.contactsRemarkDao = contactsRemarkDao;
    }

    public void setContactsActivityRelationDao(ContactsActivityRelationDao contactsActivityRelationDao) {
        this.contactsActivityRelationDao = contactsActivityRelationDao;
    }

    public void setTranDao(TranDao tranDao) {
        this.tranDao = tranDao;
    }

    public void setTranRemarkDao(TranRemarkDao tranRemarkDao) {
        this.tranRemarkDao = tranRemarkDao;
    }

    public void setTranHistoryDao(TranHistoryDao tranHistoryDao) {
        this.tranHistoryDao = tranHistoryDao;
    }

    /**
     * 保存线索对象
     * @param clue
     */
    @Override
    public void save(Clue clue) throws ClueExecption {
        int count = 0;
        count = clueDao.save(clue);
        if (count != 1){
            throw new ClueExecption("保存线索对象异常");
        }
    }

    @Override
    public PaginationVO<Clue> pageList(Map<String, Object> conditionMap) {
        PaginationVO<Clue> paginationVO = new PaginationVO<>();
        int total = clueDao.getTotalByConditionMap(conditionMap);
        List<Clue> dataList = clueDao.getClueListByConditionMap(conditionMap);
        paginationVO.setTotal(total);
        paginationVO.setDataList(dataList);
        return paginationVO;
    }

    /**
     * 删除线索对象，及线索对象备注，及线索对象与市场活动之间关系
     * @param ids
     */
    @Override
    public void deleteByIds(String[] ids) throws ClueExecption {
        int remarkCount1 = clueRemarkDao.getClueRemarkSizeByIds(ids);
        int remarkCount2 = clueRemarkDao.deleteClueRemarkByIds(ids);
        if(remarkCount1 != remarkCount2){
            throw new ClueExecption("删除线索对象相关备注异常");
        }
        int carCount1 = clueActivityRelationDao.getCarSizeByIds(ids);
        int carCount2 = clueActivityRelationDao.deleteCarByyIds(ids);
        if (carCount1 != carCount2){
            throw new ClueExecption("删除线索相关市场活动异常");
        }
        int clueCount = clueDao.deleteByIds(ids);
        if (clueCount != ids.length){
            throw new ClueExecption("删除线索对象异常");
        }
    }

    /**
     * 根据id获取线索对象信息
     * @param id
     * @return
     */
    @Override
    public Clue getClueById(String id) {
        return clueDao.getClueById(id);
    }

    /**
     * 更新线索对象信息
     * @param clue
     */
    @Override
    public void update(Clue clue) throws ClueExecption {
        int count = clueDao.update(clue);
        if (count != 1){
            throw new ClueExecption("线索对象更新异常");
        }
    }

    /**
     * //根绝id获取线索对象信息(owner显示为真名)
     * @param id
     * @return
     */
    @Override
    public Clue getDetailById(String id) {
        return clueDao.getDetailById(id);
    }

    /**
     * 获取线索相关备注
     * @param id
     * @return
     */
    @Override
    public List<ClueRemark> getRemarkListById(String id) {
        return clueRemarkDao.getRemarkListById(id);
    }

    /**
     * 根据id删除执行备注
     * @param id
     */
    @Override
    public void deleteRemarkById(String id) throws ClueRemarkException {
        int count = clueRemarkDao.deleteRemarkById(id);
        if (count != 1){
            throw new ClueRemarkException("删除线索备注异常");
        }
    }

    /**
     * 保存线索备注
     * @param clueRemark
     */
    @Override
    public void saveRemark(ClueRemark clueRemark) throws ClueRemarkException {
        int count = clueRemarkDao.saveRemark(clueRemark);
        if (count != 1){
            throw new ClueRemarkException("线索备注保存异常");
        }
    }

    /**
     * 更新备注
     * @param clueRemark
     */
    @Override
    public void updateRemark(ClueRemark clueRemark) throws ClueRemarkException {
        int count = clueRemarkDao.updateRemark(clueRemark);
        if (count != 1){
            throw new ClueRemarkException("更新备注异常");
        }
    }

    /**
     * 根据线索id，查询所关联的市场活动
     * @param clueId
     * @return
     */
    @Override
    public List<Activity> getActivityListByClueId(String clueId) {
        return activityDao.getActivityListByClueId(clueId);
    }

    /**
     * 解除关联
     * @param id
     */
    @Override
    public void deleteCarById(String id) throws ClueActivityRelationExecption {
        int count = clueActivityRelationDao.deleteCarById(id);
        if(count != 1){
            throw new ClueActivityRelationExecption("解除关联异常");
        }
    }

    /**
     * 获取该线索未关联的市场活动(带参数模糊查询)
     * @param paraMap
     * @return
     */
    @Override
    public List<Activity> getActivityListByNameAndNotByClueId(Map<String, String> paraMap) {
        return activityDao.getActivityListByNameAndNotByClueId(paraMap);
    }

    /**
     * 关联市场活动
     * @param clueId
     * @param aids
     */
    @Override
    public void saveCar(String clueId, String[] aids) throws ClueActivityRelationExecption {
        //遍历aids执行关联
        int count = 0;
        for (String aid : aids){
            ClueActivityRelation car = new ClueActivityRelation();
            car.setId(UUIDUtil.getUUID());
            car.setClueId(clueId);
            car.setActivityId(aid);
            count += clueActivityRelationDao.saveCar(car);
        }
        if (count != aids.length){
            throw new ClueActivityRelationExecption("关联市场活动异常");
        }
    }

    /**
     * 进行线索转换（tran不为空时，创建一笔交易）
     * @param clueId
     * @param tran
     * @param createBy
     */
    @Override
    public void convert(String clueId, Tran tran, String createBy) throws CustomerExecption, ContactsExecption, CustomerRemarkExecption, ContactsRemarkExecption, ContactsActivityRelationExecption, TranExecption, TranHistoryExecption, ClueExecption {
        //(1) 获取到线索id，通过线索id获取线索对象（线索对象当中封装了线索的信息）
        //owner为32位
        Clue clue = clueDao.getClueById(clueId);
        //(2) 通过线索对象提取客户信息，当该客户不存在的时候，新建客户（根据公司的名称精确匹配，判断该客户是否存在！）
        String companyName = clue.getCompany();
        //根据公司的名称精确匹配，判断该客户是否存在
        Customer customer = customerDao.getCustomerByName(companyName);
        if (customer == null){
            //该客户(公司)不存在，新建一个客户
            //从线索对象中取出公司相关的信息存入客户表中
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setOwner(clue.getOwner());
            customer.setName(clue.getCompany());
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setCreateBy(createBy);
            customer.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
            customer.setContactSummary(clue.getContactSummary());
            customer.setNextContactTime(clue.getNextContactTime());
            customer.setDescription(clue.getDescription());
            customer.setAddress(clue.getAddress());
            //将提取的客户信息进行存储
            int count1 = customerDao.save(customer);
            if (count1 != 1){
                throw new CustomerExecption("客户信息保存异常");
            }
        }
        //(3) 通过线索对象提取联系人信息，保存联系人
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setOwner(clue.getOwner());
        contacts.setSource(clue.getSource());
        contacts.setCustomerId(customer.getId());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setCreateBy(createBy);
        contacts.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        contacts.setDescription(clue.getDescription());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setAddress(clue.getAddress());
        //保存联系人信息
        int count2 = contactsDao.save(contacts);
        if (count2 != 1){
            throw new ContactsExecption("联系人信息保存异常");
        }
        //(4) 线索备注转换到客户备注以及联系人备注
        //查询该线索的备注
        List<ClueRemark> clueRemarkList = clueRemarkDao.getRemarkListById(clueId);
        //遍历提取备注信息，保存到客户备注表
        for (ClueRemark clueRemark : clueRemarkList){
            String noteContent = clueRemark.getNoteContent();
            //创建客户备注对象
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setNoteContent(noteContent);
            customerRemark.setCreateBy(createBy);
            customerRemark.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
            customerRemark.setEditFlag("0");
            customerRemark.setCustomerId(customer.getId());
            //保存客户备注信息
            int count3 = customerRemarkDao.save(customerRemark);
            if (count3 != 1){
                throw new CustomerRemarkExecption("客户备注信息保存异常");
            }
        }
        //遍历提取备注信息，保存到联系人备注表
        for (ClueRemark clueRemark : clueRemarkList){
            String noteContent = clueRemark.getNoteContent();
            ContactsRemark contactsRemark = new ContactsRemark();
            /*
                id
                noteContent
                createBy
                createTime
                editFlag
                contactsId
             */
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setNoteContent(noteContent);
            contactsRemark.setCreateBy(createBy);
            contactsRemark.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
            contactsRemark.setEditFlag("0");
            contactsRemark.setContactsId(contacts.getId());
            //保存客户备注信息
            int count4 = contactsRemarkDao.save(contactsRemark);
            if (count4 != 1){
                throw new ContactsRemarkExecption("联系人备注信息保存异常");
            }
        }
        //(5) “线索和市场活动”的关系转换到“联系人和市场活动”的关系
        //获取线索与市场活动的关系信息
        List<ClueActivityRelation> clueActivityRelationList = clueActivityRelationDao.getClueActivityRelationById(clueId);
        //遍历该集合，转换到联系人和市场活动关系表中
        for (ClueActivityRelation clueActivityRelation : clueActivityRelationList){
            String activityId = clueActivityRelation.getActivityId();
            //创建联系人市场活动关系对象
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setContactsId(contacts.getId());
            contactsActivityRelation.setActivityId(activityId);
            //保存联系人市场活动信息
            int count5 = contactsActivityRelationDao.save(contactsActivityRelation);
            if (count5 != 1){
                throw new ContactsActivityRelationExecption("联系人市场活动信息保存异常");
            }
        }
        //(6) 如果有创建交易需求，创建一条交易
        if (tran != null){
            //创建一笔交易
            tran.setOwner(clue.getOwner());
            tran.setCustomerId(customer.getId());
            tran.setSource(clue.getSource());
            tran.setContactsId(contacts.getId());
            tran.setCreateBy(createBy);
            tran.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
            tran.setDescription(clue.getDescription());
            tran.setContactSummary(clue.getContactSummary());
            tran.setNextContactTime(clue.getNextContactTime());
            //保存交易信息
            int count6 = tranDao.save(tran);
            if (count6 != 1){
                throw new TranExecption("交易信息保存异常");
            }
            //(7) 如果创建了交易，则创建一条该交易下的交易历史
            //创建交易历史对象
            TranHistory tranHistory = new TranHistory();
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setStage(tran.getStage());
            tranHistory.setMoney(tran.getMoney());
            tranHistory.setExpectedDate(tran.getExpectedDate());
            tranHistory.setCreateBy(createBy);
            tranHistory.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
            tranHistory.setTranId(tran.getId());
            //保存交易历史信息
            int count7 = tranHistoryDao.save(tranHistory);
            if (count7 != 1){
                throw new TranHistoryExecption("交易历史信息保存异常");
            }
        }
        //(8) 删除线索备注
        //根据clueId删除备注
        int count8 = clueRemarkDao.deleteRemarkByClueId(clueId);
        //(9) 删除线索和市场活动的关系
        //根据线索id删除市场活动关系
        int count9 = clueActivityRelationDao.deleteCarByClueId(clueId);
        //根据id删除线索
        int count10 = clueDao.deleteById(clueId);
        if (count10 != 1){
            throw new ClueExecption("线索信息删除异常");
        }


    }
}
/*
id
stage
money
expectedDate
createTime
createBy
tranId

 */