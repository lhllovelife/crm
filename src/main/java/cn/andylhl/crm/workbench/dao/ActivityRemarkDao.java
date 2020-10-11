package cn.andylhl.crm.workbench.dao;

/***
 * @Title: ActivityRemarkDao
 * @Description:
 * @author: lhl
 * @date: 2020/10/9 15:08
 */
public interface ActivityRemarkDao {

    //根据id获取备注数量
    int getCountByIds(String[] ids);

    //根据id删除市场活动备注
    int deleteActRemByIds(String[] ids);
}
