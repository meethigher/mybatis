package top.meethigher.dao;

import top.meethigher.domain.Person;
import top.meethigher.domain.QueryVo;

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

    /**
     * 保存数据
     * @param person
     */
    void savePerson(Person person);

    /**
     * 更新数据
     * @param person
     */
    void updatePerson(Person person);

    /**
     * 删除数据
     * @param id
     */
    void deletePerson(Integer id);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Person findPersonById(Integer id);

    /**
     * 模糊查询
     * @param name
     * @return
     */
    List<Person> findByName(String name);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo中的条件查询
     * @param vo
     * @return
     */
    List<Person> findPersonByVo(QueryVo vo);
}
