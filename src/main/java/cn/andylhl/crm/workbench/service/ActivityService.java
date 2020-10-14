package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.ActivityExecption;
import cn.andylhl.crm.exception.ActivityRemarkExecption;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

/***
 * @Title: ActivityService
 * @Description:
 * @author: lhl
 * @date: 2020/10/9 15:11
 */
public interface ActivityService {

    void save(Activity activity) throws ActivityExecption;

    /**
     * 执行分页带参数查询市场活动信息
     * @return
     */
    PaginationVO<Activity> pageList(Map<String, Object> conditionMap);

    /**
     * 批量删除市场活动
     * @param ids
     * @return
     */
    void deleteAct(String[] ids) throws ActivityRemarkExecption, ActivityExecption;

    Activity getActById(String id);

    /**
     * 更新市场活动信息
     * @param activity
     */
    void updateActivity(Activity activity) throws ActivityExecption;

    /**
     * 通过id查询市场活动详细信息
     * @param id
     * @return
     */
    Activity getActDetatilById(String id);

    List<ActivityRemark> getRemarkListByAid(String id);

    void deleteRemark(String id) throws ActivityRemarkExecption;
}
