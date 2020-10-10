package cn.andylhl.crm.workbench.service.impl;

import cn.andylhl.crm.exception.ActivityExecption;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.dao.ActivityDao;
import cn.andylhl.crm.workbench.domain.Activity;
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

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
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
}
