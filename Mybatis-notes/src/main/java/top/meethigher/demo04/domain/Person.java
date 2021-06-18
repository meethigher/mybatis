package top.meethigher.demo04.domain;

import java.io.Serializable;

/**
 * Person
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/1/25
 */
//该接口是一个mini接口，没有必须要实现的方法，implements Serializable只是为了标注该对象是可被序列化的
public class Person implements Serializable {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private Integer grade;
    private String school;
    private String position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", school='" + school + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
