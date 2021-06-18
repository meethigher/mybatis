package top.meethigher.demo09.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.meethigher.demo09.dao.UserDao;
import top.meethigher.demo09.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * UnitTest
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/15
 */
public class UserTest {
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
     * 查询所有用户，以及每个用户对应的所有账户
     */
    @Test
    public void testFindAll(){
        List<User> all = userDao.findAll();
        for(User u:all){
            System.out.println(u);
        }
    }

}
