package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.ActivityExecption;
import cn.andylhl.crm.workbench.domain.Activity;

/***
 * @Title: ActivityService
 * @Description:
 * @author: lhl
 * @date: 2020/10/9 15:11
 */
public interface ActivityService {

    void save(Activity activity) throws ActivityExecption;
}
