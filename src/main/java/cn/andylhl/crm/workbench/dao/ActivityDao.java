package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/***
 * @Title: ActivityDao
 * @Description:
 * @author: lhl
 * @date: 2020/10/9 15:07
 */
public interface ActivityDao {

    int save(Activity activity);

    int getTotalByCondition(Map<String, Object> conditionMap);

    List<Activity> getActivityByCondition(Map<String, Object> conditionMap);

    //根绝id删除市场活动
    int deleteActByIds(String[] ids);

    Activity getActById(String id);

    //更新市场活动信息
    int updateActivity(Activity activity);

    Activity getActDetailById(String id);

    //根据线索id，查询所关联的市场活动
    List<Activity> getActivityListByClueId(String clueId);
}
