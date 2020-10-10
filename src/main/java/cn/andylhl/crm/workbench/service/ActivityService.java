package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.ActivityExecption;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Activity;

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
}
