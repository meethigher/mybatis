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

import java.io.InputStream;

/**
 * SecondCacheTest
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/16
 */
public class SecondCacheTest {
    private InputStream in;
    private SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("Mybatis.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);

    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession1 = factory.openSession();
        UserDao dao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = dao1.findById(1);
        System.out.println(user1);//top.meethigher.demo11.domain.User@1f760b47
        sqlSession1.close();//一级缓存消失

        SqlSession sqlSession2 = factory.openSession();
        UserDao dao2 = sqlSession2.getMapper(UserDao.class);
        User user2 = dao2.findById(1);//这一次就不查询了，只查询第一次
        System.out.println(user2);//top.meethigher.demo11.domain.User@33f676f6
        sqlSession2.close();

        //因为二级缓存中存放的是数据，就是一串字符串，而不是对象。获取数据时，创建一个新的对象，将字符串填充进去，所以是false
        System.out.println(user1 == user2);//false
    }
}
