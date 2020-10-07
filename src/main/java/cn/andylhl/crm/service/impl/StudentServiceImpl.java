package cn.andylhl.crm.service.impl;

import cn.andylhl.crm.dao.StudentDao;
import cn.andylhl.crm.domain.Student;
import cn.andylhl.crm.service.StudentService;

/***
 * @Title: StudentService
 * @Description:
 * @author: lhl
 * @date: 2020/10/6 15:25
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * 新增一个学生
     * @param student
     * @return
     */
    public int insertStu(Student student){
        return studentDao.insertStu(student);
    }
}
