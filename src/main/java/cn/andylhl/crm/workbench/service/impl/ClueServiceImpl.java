package cn.andylhl.crm.workbench.service.impl;

import cn.andylhl.crm.exception.ClueExecption;
import cn.andylhl.crm.workbench.dao.ActivityDao;
import cn.andylhl.crm.workbench.dao.ClueDao;
import cn.andylhl.crm.workbench.domain.Clue;
import cn.andylhl.crm.workbench.service.ClueService;

/***
 * @Title: ClueServiceImpl
 * @Description: 线索业务实现类
 * @author: lhl
 * @date: 2020/10/22 14:53
 */
public class ClueServiceImpl implements ClueService {
    private ActivityDao activityDao;
    private ClueDao clueDao;

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void setClueDao(ClueDao clueDao) {
        this.clueDao = clueDao;
    }

    /**
     * 保存线索对象
     * @param clue
     */
    @Override
    public void save(Clue clue) throws ClueExecption {
        int count = 0;
        count = clueDao.save(clue);
        if (count != 1){
            throw new ClueExecption("保存线索对象异常");
        }
    }
}
