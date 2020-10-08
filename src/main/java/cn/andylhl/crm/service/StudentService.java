package cn.andylhl.crm.service;

import cn.andylhl.crm.domain.Student;

import java.util.List;

/***
 * @Title: StudentService
 * @Description:
 * @author: lhl
 * @date: 2020/10/6 15:28
 */
public interface StudentService {

    int insertStu(Student student);

    int insertStus(List<Student> list);
}
