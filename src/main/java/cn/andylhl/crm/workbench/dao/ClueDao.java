package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

/***
 * @Title: ClueDao
 * @Description: 线索dao
 * @author: lhl
 * @date: 2020/10/22 14:52
 */
public interface ClueDao {

    int save(Clue clue);

    int getTotalByConditionMap(Map<String, Object> conditionMap);

    List<Clue> getClueListByConditionMap(Map<String, Object> conditionMap);

    //根据id数组删除线索对象
    int deleteByIds(String[] ids);

    //根据id获取线索对象信息
    Clue getClueById(String id);

    //更新线索对象信息
    int update(Clue clue);

    //根绝id获取线索对象信息(owner显示为真名)
    Clue getDetailById(String id);

    //根据id删除线索
    int deleteById(String clueId);
}
