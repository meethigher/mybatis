package top.meethigher.demo11.dao;

import top.meethigher.demo11.domain.User;

import java.util.List;

/**
 * UserDao
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/14
 */
public interface UserDao {
    /**
     * 查询所有用户，同时获取到用户下所有账户的信息
     * @return
     */
    List<User> findAll();

    /**
     * 通过id查询
     * @return
     */
    User findById(Integer id);
}
