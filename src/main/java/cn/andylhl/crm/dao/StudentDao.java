package cn.andylhl.crm.dao;

import cn.andylhl.crm.domain.Student;

/***
 * @Title: StudentDao
 * @Description: 学生dao
 * @author: lhl
 * @date: 2020/10/6 15:17
 */
public interface StudentDao {

    //新增一个学生
    int insertStu(Student student);
}
