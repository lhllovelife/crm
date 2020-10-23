package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.ClueRemark;

import java.util.List;

/***
 * @Title: ClueRemarkDao
 * @Description: 线索备注dao
 * @author: lhl
 * @date: 2020/10/22 22:00
 */
public interface ClueRemarkDao {

    //根据id数组查询备注数目
    int getClueRemarkSizeByIds(String[] ids);

    //根据id数组删除备注
    int deleteClueRemarkByIds(String[] ids);

    //根绝id查询线索相关备注
    List<ClueRemark> getRemarkListById(String id);

    //根据id删除线索备注
    int deleteRemarkById(String id);

    //保存备注
    int saveRemark(ClueRemark clueRemark);
}
