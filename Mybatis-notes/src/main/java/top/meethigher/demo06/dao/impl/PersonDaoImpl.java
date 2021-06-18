package top.meethigher.demo06.dao.impl;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import top.meethigher.demo06.dao.PersonDao;
import top.meethigher.demo06.domain.Person;
import top.meethigher.demo06.domain.QueryVo;

import java.util.List;

/**
 * PersonDaoImpl
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/12
 */
public class PersonDaoImpl implements PersonDao {
    private SqlSession sqlSession;
    private SqlSessionFactory factory;

    //之所以这个地方用sqlsessionfactory是因为在这里面创建的sqlsession可以直接在这里关闭，事务提交也可以直接在这里提交
    public PersonDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public void init() {
        sqlSession = factory.openSession();
    }

    public void destroy(boolean isCommit) {
        if (isCommit) {
            sqlSession.commit();
            System.out.println("提交事务");
        }
        sqlSession.close();
    }

    @Override
    public List<Person> findAll() {
        init();
        List<Person> personList = sqlSession.selectList("top.meethigher.demo06.dao.PersonDao.findAll");//此处参数是能获取配置信息的key,namespace.增删改查id
        destroy(false);
        return personList;
    }

    @Override
    public void savePerson(Person person) {
        init();
        //参数还有一个Object表示，传入的变量，如果不传，会返回xxx关键字为null的字样。
        sqlSession.insert("top.meethigher.demo06.dao.PersonDao.savePerson", person);
        destroy(true);
    }

    @Override
    public void updatePerson(Person person) {
        init();
        sqlSession.update("top.meethigher.demo06.dao.PersonDao.updatePerson", person);
        destroy(true);
    }

    @Override
    public void deletePerson(Integer id) {
        init();
        sqlSession.delete("top.meethigher.demo06.dao.PersonDao.deletePerson", id);
        destroy(true);
    }

    @Override
    public List<Person> findByName(String name) {
        init();
        List<Person> objects = sqlSession.selectList("top.meethigher.demo06.dao.PersonDao.findByName", name);
        destroy(false);
        return objects;
    }

    @Override
    public List<Person> findPersonByVo(QueryVo vo) {
        init();
        List<Person> objects = sqlSession.selectList("top.meethigher.demo06.dao.PersonDao.findPersonByVo", vo);
        destroy(false);
        return objects;
    }

    //下面两个是重点
    @Override
    public Person findPersonById(Integer id) {
        init();
        Person p = sqlSession.selectOne("top.meethigher.demo06.dao.PersonDao.findPersonById", id);
        destroy(false);
        return p;
    }

    @Override
    public int findTotal() {
        init();
        Integer i = sqlSession.selectOne("top.meethigher.demo06.dao.PersonDao.findTotal");
        destroy(false);
        return i;
    }
}
