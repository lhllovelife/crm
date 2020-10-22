package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.ClueExecption;
import cn.andylhl.crm.workbench.domain.Clue;

/***
 * @Title: ClueService
 * @Description:
 * @author: lhl
 * @date: 2020/10/22 14:53
 */
public interface ClueService {
    //保存线索对象
    void save(Clue clue) throws ClueExecption;
}
