package top.meethigher.demo03.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import top.meethigher.demo03.dao.PersonDao;
import top.meethigher.demo03.domain.Person;

import java.util.List;

/**
 * PersonDaoImpl
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/8
 */
public class PersonDaoImpl implements PersonDao {
    private SqlSessionFactory factory;
    public PersonDaoImpl(SqlSessionFactory factory){
        this.factory=factory;
    }
    @Override
    public List<Person> findAll() {
        //1.使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        //2.使用session执行查询所有方法，里面的参数是配置文件里指定命名空间下的某个语句id
        List<Person> persons = session.selectList("top.meethigher.demo03.dao.PersonDao.findAll");
        session.close();//关闭
        //3.返回查询结果
        return persons;
    }
}
