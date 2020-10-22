package cn.andylhl.crm.workbench.dao;

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
}
