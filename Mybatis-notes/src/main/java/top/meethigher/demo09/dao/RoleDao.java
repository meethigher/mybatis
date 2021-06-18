package top.meethigher.demo09.dao;

import top.meethigher.demo09.domain.Role;

import java.util.List;

/**
 * RoleDao
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/15
 */
public interface RoleDao {
    /**
     * 查询所有角色，以及对应的所有个人信息
     * @return
     */
    List<Role> findAll();
}
