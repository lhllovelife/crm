package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.ContactsRemark;

/***
 * @Title: ContactsRemark
 * @Description: 联系人备注dao
 * @author: lhl
 * @date: 2020/10/25 22:05
 */
public interface ContactsRemarkDao {

    //保存客户备注信息
    int save(ContactsRemark contactsRemark);
}
