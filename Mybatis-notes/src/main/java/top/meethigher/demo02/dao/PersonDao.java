package top.meethigher.demo02.dao;

import org.apache.ibatis.annotations.Select;
import top.meethigher.demo02.domain.Person;

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
    @Select("select * from fairy")
    List<Person> findAll();
}
