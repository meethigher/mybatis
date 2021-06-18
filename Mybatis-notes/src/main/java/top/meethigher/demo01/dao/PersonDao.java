package top.meethigher.demo01.dao;

import top.meethigher.demo01.domain.Person;

import java.util.List;

/**
 * PersonDao 用户的持久层接口
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/1/25
 */
public interface PersonDao {
    /**
     * 查询所有操作
     * @return
     */
    List<Person> findAll();
}
