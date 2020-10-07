package cn.andylhl.crm.test_spring_mybatis;

import cn.andylhl.crm.domain.Student;
import cn.andylhl.crm.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        String[] names = ac.getBeanDefinitionNames();
        int i = 0;
        System.out.println("SpringIoC容器中创建的对象");
        for (String name : names){
            System.out.println( (++i) + ": " + name);
        }
        StudentService service = (StudentService) ac.getBean("studentService");
        Student student = new Student("A_0003", "zyx", 23);
        int count = service.insertStu(student);
        System.out.println(count);
    }
}
