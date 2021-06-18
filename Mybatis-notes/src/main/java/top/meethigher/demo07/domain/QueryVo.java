package top.meethigher.demo07.domain;

import java.io.Serializable;

/**
 * QueryVo
 * 这个表示综合查询的javabean包装类
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/11
 */
//该接口是一个mini接口，没有必须要实现的方法，implements Serializable只是为了标注该对象是可被序列化的
public class QueryVo implements Serializable {
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
