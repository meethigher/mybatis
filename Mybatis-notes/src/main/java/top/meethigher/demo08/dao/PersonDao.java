package top.meethigher.demo08.dao;

import top.meethigher.demo08.domain.Person;
import top.meethigher.demo08.domain.QueryVo;

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
     *
     * @return
     */
    List<Person> findAll();

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Person findPersonById(Integer id);

    /**
     * 模糊查询
     *
     * @param name
     * @return
     */
    List<Person> findByName(String name);


    /**
     * 根据QueryVo中的条件查询
     *
     * @param vo
     * @return
     */
    List<Person> findPersonByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     * @param person 有可能有用户名，也有可能没有用户名
     * @return
     */
    List<Person> findByCondition(Person person);

    /**
     * 通过id列表来查询
     * @param vo
     * @return
     */
    List<Person> findByIds(QueryVo vo);
}
