package cn.andylhl.crm.domain;

/***
 * @Title: Student
 * @Description: 学生bean
 * @author: lhl
 * @date: 2020/10/6 15:14
 */
public class Student {

    private String id;
    private String name;
    private Integer age;

    public Student() {
    }

    public Student(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
