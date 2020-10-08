package cn.andylhl.crm.test_spring_mybatis;

import cn.andylhl.crm.domain.Student;
import cn.andylhl.crm.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/***
 * @Title: TestStudentServiceImpl
 * @Description: 通过 StudentServiceImpl 测试是否整和成功
 * @author: lhl
 * @date: 2020/10/6 15:46
 */
public class TestStudentServiceImpl {

    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService service = (StudentService) ac.getBean("studentService");

        Student student1 = new Student("A_0009", "小明", 23);
        Student student2 = new Student("A_0009bake", "小明bake", 33);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);

        int count = 0;
        count = service.insertStus(list);
        System.out.println(count);
    }
}
