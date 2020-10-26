package cn.andylhl.crm.workbench.service;

import cn.andylhl.crm.exception.*;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.domain.Clue;
import cn.andylhl.crm.workbench.domain.ClueRemark;
import cn.andylhl.crm.workbench.domain.Tran;

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

    //获取该线索未关联的市场活动(带参数模糊查询)
    List<Activity> getActivityListByNameAndNotByClueId(Map<String, String> paraMap);

    //关联市场活动
    void saveCar(String clueId, String[] aids) throws ClueActivityRelationExecption;

    //进行线索转换（tran不为空时，创建一笔交易）
    void convert(String clueId, Tran tran, String createBy) throws CustomerExecption, ContactsExecption, CustomerRemarkExecption, ContactsRemarkExecption, ContactsActivityRelationExecption, TranExecption, TranHistoryExecption, ClueExecption;
}
