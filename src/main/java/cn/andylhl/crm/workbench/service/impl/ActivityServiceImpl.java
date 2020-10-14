package cn.andylhl.crm.workbench.service.impl;

import cn.andylhl.crm.exception.ActivityExecption;
import cn.andylhl.crm.exception.ActivityRemarkExecption;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.dao.ActivityDao;
import cn.andylhl.crm.workbench.dao.ActivityRemarkDao;
import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.domain.ActivityRemark;
import cn.andylhl.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

/***
 * @Title: ActivityServiceImpl
 * @Description: 市场活动业务处理
 * @author: lhl
 * @date: 2020/10/9 15:11
 */
public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao;
    private ActivityRemarkDao activityRemarkDao;

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void setActivityRemarkDao(ActivityRemarkDao activityRemarkDao) {
        this.activityRemarkDao = activityRemarkDao;
    }

    /**
     * 保存市场活动信息
     * @param activity
     */
    @Override
    public void save(Activity activity) throws ActivityExecption {
        int count = activityDao.save(activity);
        if (count != 1){
            throw new ActivityExecption("保存市场活动信息失败");
        }
    }


    /**
     * 分页带参数查询市场活动相关信息
     * @return
     */
    @Override
    public PaginationVO<Activity> pageList(Map<String, Object> conditionMap) {
        //查询总记录条数
        int total = activityDao.getTotalByCondition(conditionMap);
        //查询Activity信息
        List<Activity> dataList = activityDao.getActivityByCondition(conditionMap);
        //封装结果数据
        PaginationVO<Activity> paginationVO = new PaginationVO<>();
        paginationVO.setTotal(total);
        paginationVO.setDataList(dataList);
        return paginationVO;
    }

    /**
     * 批量删除市场活动
     * @param ids
     * @return
     */
    @Override
    public void deleteAct(String[] ids) throws ActivityRemarkExecption, ActivityExecption {
        //查询该总共需要删除的备注数量
        int remarkCount1 = activityRemarkDao.getCountByIds(ids);
        //执行删除，返回收到影响的记录条数
        int remarkCount2 = activityRemarkDao.deleteActRemByIds(ids);

        if (remarkCount1 != remarkCount2){
            throw new ActivityRemarkExecption("市场活动备注删除数目异常");
        }
        //删除市场活动
        int actCount1 = ids.length;
        int actCount2 = activityDao.deleteActByIds(ids);
        if (actCount1 != actCount2) {
            throw new ActivityExecption("市场活动删除数目异常");
        }

    }

    /**
     * 根据id获取活动对象
     * @param id
     * @return
     */
    @Override
    public Activity getActById(String id) {
        return activityDao.getActById(id);
    }

    @Override
    public void updateActivity(Activity activity) throws ActivityExecption {
        int count = activityDao.updateActivity(activity);
        if (count != 1){
            throw new ActivityExecption("市场活动信息更新异常");
        }
    }

    /**
     * 通过id获得市场活动信息（owner为真是姓名）
     * @param id
     * @return
     */
    @Override
    public Activity getActDetatilById(String id) {
        return activityDao.getActDetailById(id);
    }

    /**
     * 根据id获取市场活动备注信息
     * @param id
     * @return
     */
    @Override
    public List<ActivityRemark> getRemarkListByAid(String id) {
        return activityRemarkDao.getRemarkListByAid(id);
    }
}
