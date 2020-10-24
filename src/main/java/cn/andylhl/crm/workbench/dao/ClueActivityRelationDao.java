package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.Activity;

import java.util.List;

/***
 * @Title: ClueActivityRelation
 * @Description: 线索市场活动dao
 * @author: lhl
 * @date: 2020/10/22 21:58
 */
public interface ClueActivityRelationDao {

    //根据id数组查询线索所关联的市场活动数目
    int getCarSizeByIds(String[] ids);

    //根据id数组删除线索所关联的市场活动
    int deleteCarByyIds(String[] ids);

}
