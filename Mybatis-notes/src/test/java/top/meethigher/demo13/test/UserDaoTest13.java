package top.meethigher.demo13.test;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.meethigher.demo13.dao.UserDao;
import top.meethigher.demo13.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * MybatisTest13
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/17
 */

public class UserDaoTest13 {
    private InputStream is;
    private SqlSession sqlSession;
    private  SqlSessionFactory factory;
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
        factory= new SqlSessionFactoryBuilder().build(is);
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
    public void testFirstLevelCache(){
        User byId = userDao.findById(1);
        User byId1 = userDao.findById(1);
        System.out.println(byId==byId1);//true，一级缓存其实是不用担心的。
    }

    @Test
    public void testSecondLevelCache(){
        User byId = userDao.findById(1);
        System.out.println(byId);

        sqlSession.close();//释放一级缓存
        sqlSession = factory.openSession();
        userDao=sqlSession.getMapper(UserDao.class);

        User byId1 = userDao.findById(1);
        System.out.println(byId1);
        System.out.println(byId==byId1);
    }


}
