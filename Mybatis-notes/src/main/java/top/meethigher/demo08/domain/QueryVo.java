package top.meethigher.demo08.domain;

import java.io.Serializable;
import java.util.List;

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
    private List<Integer> ids;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
