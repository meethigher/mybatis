package top.meethigher.demo09.dao;

import top.meethigher.demo09.domain.Account;
import top.meethigher.demo09.domain.AccountUser;

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
     * 查询所有Account，并且带有用户名称和地址信息
     * @return
     */
    List<AccountUser> findAllAccount();
}
