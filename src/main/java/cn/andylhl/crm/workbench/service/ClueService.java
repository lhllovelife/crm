package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.ClueActivityRelationExecption;
import cn.andylhl.crm.exception.ClueExecption;
import cn.andylhl.crm.exception.ClueRemarkException;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.domain.Clue;
import cn.andylhl.crm.workbench.domain.ClueRemark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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

    //更新线索对象信息
    void update(Clue clue) throws ClueExecption;

    //根据id获取线索对象信息(owner显示为真名)
    Clue getDetailById(String id);

    //获取线索相关备注
    List<ClueRemark> getRemarkListById(String id);

    //根据id删除执行备注
    void deleteRemarkById(String id) throws ClueRemarkException;

    //保存线索备注
    void saveRemark(ClueRemark clueRemark) throws ClueRemarkException;

    //更新备注
    void updateRemark(ClueRemark clueRemark) throws ClueRemarkException;
    //根据线索id，查询所关联的市场活动
    List<Activity> getActivityListByClueId(String clueId);

    //解除关联
    void deleteCarById(String id) throws ClueActivityRelationExecption;
}
