package top.meethigher.demo12.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.meethigher.demo12.domain.User;

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
     *
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 通过id查询
     *
     * @return
     */
    @Select("select * from user where uid=${id}")
    User findById(Integer id);

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
     * 模糊查询
     *
     * @param name
     * @return
     */
    @Select("select * from user where username like #{name}")
    List<User> findByName(String name);

    /**
     * 查询总条数
     *
     * @return
     */
    @Select("select count(*) from user")
    int findTotal();
}
