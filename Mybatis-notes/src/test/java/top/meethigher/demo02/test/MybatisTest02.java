package top.meethigher.demo02.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.meethigher.demo02.dao.PersonDao;
import top.meethigher.demo02.domain.Person;

import java.io.InputStream;
import java.util.List;

/**
 * MybatisTest
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/1/25
 */
public class MybatisTest02 {
    /**
     * 入门案例
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.动态代理，使用SqlSession创建Dao接口的代理对象
        //理解动态搭理：https://meethigher.top/blog/2020/filter-and-listener/#%E7%90%86%E8%A7%A3%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86demo
        PersonDao personDao = session.getMapper(PersonDao.class);
        //5.使用代理对象执行方法
        List<Person> persons =personDao.findAll();
        for(Person p:persons){
            System.out.println(p);
        }
        //6.释放资源
        session.close();
        is.close();
    }
}
