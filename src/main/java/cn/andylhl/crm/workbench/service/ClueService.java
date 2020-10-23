package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.ClueExecption;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Clue;

import java.util.Map;

/***
 * @Title: ClueService
 * @Description:
 * @author: lhl
 * @date: 2020/10/22 14:53
 */
public interface ClueService {
    //保存线索对象
    void save(Clue clue) throws ClueExecption;

    //执行分页查询带参数查询
    PaginationVO<Clue> pageList(Map<String, Object> conditionMap);

    //删除线索对象，及线索对象备注，及线索对象与市场活动之间关系
    void deleteByIds(String[] ids) throws ClueExecption;

    //根据id获取线索对象信息
    Clue getClueById(String id);
}
