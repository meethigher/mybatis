package top.meethigher.demo13.dao;


import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import top.meethigher.demo13.domain.Account;

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
     *
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "aid", property = "aid"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(column = "uid", property = "user", one = @One(select = "top.meethigher.demo13.dao.UserDao.findById", fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @Select("select * from account where uid=#{id}")
    Account findById(Integer id);

}
