package top.meethigher.demo11.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.meethigher.demo11.dao.UserDao;
import top.meethigher.demo11.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * CacheTest
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/16
 */
public class CacheTest {
    private InputStream is;
    private SqlSession sqlSession;
    private UserDao userDao;
    private SqlSessionFactory factory;

    @Before //用于在测试方法之前执行
    public void init() {
        //1.读取配置文件，获取输入流
        try {
            is = Resources.getResourceAsStream("Mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(is);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取代理dao对象
        userDao= sqlSession.getMapper(UserDao.class);
    }

    @After //用于在测试方法之后执行
    public void destroy() throws IOException {
        sqlSession.commit();
        if (sqlSession != null)
            sqlSession.close();
        if (is != null)
            is.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        User byId = userDao.findById(1);
        System.out.println(byId);//top.meethigher.demo11.domain.User@79defdc
        User byId1 = userDao.findById(1);
        System.out.println(byId1);//top.meethigher.demo11.domain.User@79defdc
        //通过debug可知，实际上只查询了一次
        System.out.println(byId==byId1);//true
        //清除缓存法一：关闭并重新开启一个SqlSession
//        sqlSession.close();
//        sqlSession=factory.openSession();

        //清除缓存法二
        sqlSession.clearCache();

        userDao=sqlSession.getMapper(UserDao.class);
        byId1=userDao.findById(1);
        System.out.println(byId1);//top.meethigher.demo11.domain.User@4b86805d
        System.out.println(byId==byId1);//false
    }
}
