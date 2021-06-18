package top.meethigher.demo03.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.meethigher.demo03.dao.impl.PersonDaoImpl;
import top.meethigher.demo03.domain.Person;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * MybatisTest03
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/8
 */
public class MybatisTest03 {
    /**
     * 入门案例
     * 使用自定义的PersonDao实现类
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //3.使用工厂创建Dao对象
        PersonDaoImpl personDao = new PersonDaoImpl(factory);

        //4.使用代理对象执行方法
        List<Person> persons =personDao.findAll();
        for(Person p:persons){
            System.out.println(p);
        }
        //5.释放资源
        is.close();
    }
}
