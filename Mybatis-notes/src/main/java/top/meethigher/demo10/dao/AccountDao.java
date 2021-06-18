package top.meethigher.demo10.dao;

import top.meethigher.demo10.domain.Account;

import java.util.List;

/**
 * AccountDao
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/14
 */
public interface AccountDao {

    /**
     * 查询所有Account，同时获取其所在的User信息
     * @return
     */
    List<Account> findAll();

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Account findById(Integer id);

}
