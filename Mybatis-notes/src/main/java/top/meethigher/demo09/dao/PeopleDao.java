package top.meethigher.demo09.dao;

import top.meethigher.demo09.domain.People;

import java.util.List;

/**
 * PeopleDao
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/15
 */
public interface PeopleDao {
    /**
     * 查询所有个人，以及对应的所有角色信息
     * @return
     */
    List<People> findAll();
}
