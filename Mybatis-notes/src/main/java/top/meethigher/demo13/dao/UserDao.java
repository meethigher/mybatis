package top.meethigher.demo13.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import top.meethigher.demo13.domain.User;

import java.util.List;

/**
 * UserDao
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/14
 */
@CacheNamespace(blocking = true)
public interface UserDao {
    /**
     * 查询所有用户，同时获取到用户下所有账户的信息
     *
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "uid", property = "uid"),
            @Result(column = "username", property = "username"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address"),
            @Result(column = "uid", property = "accounts", many = @Many(select = "top.meethigher.demo13.dao.AccountDao.findById", fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 通过id查询
     *
     * @return
     */
    @Select("select * from user where uid=${id}")
    @ResultMap("userMap")
    User findById(Integer id);

    /**
     * 模糊查询
     *
     * @param name
     * @return
     */
    @Select("select * from user where username like #{name}")
    @ResultMap(value = {"userMap"})
    List<User> findByName(String name);

    /**
     * 保存
     *
     * @param user
     */
    @Insert("insert into user(username,birthday,sex,address) values(#{username},#{birthday},#{sex},#{address})")
    void saveUser(User user);

    /**
     * 更新
     *
     * @param user
     */
    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where uid=#{uid}")
    void updateUser(User user);

    @Delete("delete from user where uid=${id}")
    void deleteUser(Integer id);


    /**
     * 查询总条数
     *
     * @return
     */
    @Select("select count(*) from user")
    int findTotal();
}
