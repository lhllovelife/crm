package cn.andylhl.crm.settings.dao;

import cn.andylhl.crm.settings.domain.DicType;

import java.util.List;

/***
 * @Title: DicType
 * @Description:
 * @author: lhl
 * @date: 2020/10/15 21:50
 */
public interface DicTypeDao {
    //获取所有字典类型对象
    List<DicType> getAll();
}
