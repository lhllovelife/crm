package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.domain.ClueActivityRelation;

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

    //根据id删除一条关联
    int deleteCarById(String id);

    //添加一条线索与市场活动的关联
    int saveCar(ClueActivityRelation car);

    //获取线索与市场活动的关系信息
    List<ClueActivityRelation> getClueActivityRelationById(String clueId);

    //根据线索id删除市场活动关系
    int deleteCarByClueId(String clueId);
}
