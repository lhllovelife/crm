package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.Contacts;

/***
 * @Title: ContactsDao
 * @Description: 联系人dao
 * @author: lhl
 * @date: 2020/10/25 22:04
 */
public interface ContactsDao {

    //保存联系人信息
    int save(Contacts contacts);
}
