package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.ContactsActivityRelation;

/***
 * @Title: CcontactsActivityRelationDao
 * @Description:
 * @author: lhl
 * @date: 2020/10/26 9:09
 */
public interface ContactsActivityRelationDao {

    //保存联系人市场活动信息
    int save(ContactsActivityRelation contactsActivityRelation);
}
