package cn.andylhl.crm.settings.dao;

import cn.andylhl.crm.settings.domain.DicValue;

import java.util.List;

/***
 * @Title: DicValue
 * @Description:
 * @author: lhl
 * @date: 2020/10/15 21:51
 */
public interface DicValueDao {
    //通过typeCode值获取所有value
    List<DicValue> getAllByCode(String typeCode);
}
