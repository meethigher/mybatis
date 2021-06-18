package top.meethigher.demo12.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.meethigher.demo12.dao.UserDao;
import top.meethigher.demo12.domain.User;

import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * MybatisTest12
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/16
 */
public class MybatisTest12 {
    private InputStream is;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before //用于在测试方法之前执行
    public void init() {
        //1.读取配置文件，获取输入流
        try {
            is = Resources.getResourceAsStream("Mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取代理dao对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After //用于在测试方法之后执行
    public void destroy() throws IOException {
        sqlSession.commit();
        if (sqlSession != null)
            sqlSession.close();
        if (is != null)
            is.close();
    }


    @Test
    public void testFindAll() {
        List<User> all = userDao.findAll();
        for (User u : all) {
            System.out.println(u);
        }
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("孙尚香");
        user.setAddress("王者荣耀");
        user.setSex("女");
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(user);

        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUid(2);
        user.setAddress("腾讯");
        user.setUsername("肖战");
        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        userDao.deleteUser(4);
    }

    @Test
    public void testFindById() {
        User byId = userDao.findById(1);
        System.out.println(byId);
    }

    @Test
    public void testFindByName() {
        List<User> byName = userDao.findByName("%香%");
        System.out.println(byName);
    }


    @Test
    public void testFindTotal() {
        int total = userDao.findTotal();
        System.out.println(total);
    }
}
