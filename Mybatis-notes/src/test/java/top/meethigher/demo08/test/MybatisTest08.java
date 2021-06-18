package top.meethigher.demo08.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.meethigher.demo08.dao.PersonDao;
import top.meethigher.demo08.domain.Person;
import top.meethigher.demo08.domain.QueryVo;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * MybatisTest08
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/13
 */
public class MybatisTest08 {
    private InputStream is;
    private SqlSession sqlSession;
    private PersonDao personDao;

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
        personDao = sqlSession.getMapper(PersonDao.class);
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
     * 测试查找所有
     *
     * @throws IOException
     */
    @Test
    public void testFindAll() throws IOException {
        List<Person> persons = personDao.findAll();
        for (Person p : persons) {
            System.out.println(p);
        }
    }


    /**
     * 测试通过id查询
     */
    @Test
    public void testFindPersonById() {
        Person person = personDao.findPersonById(1);
        System.out.println(person);
    }

    /**
     * 测试通过名字模糊查询
     */
    @Test
    public void testFindByName() {
        List<Person> persons = personDao.findByName("%月%");
        System.out.println(persons);
    }


    /**
     * 测试通过包装类实现综合查询
     */
    @Test
    public void testFindPersonByVo() {
        QueryVo queryVo = new QueryVo();
        Person person = new Person();
        person.setName("%月%");
        queryVo.setPerson(person);
        List<Person> personByVo = personDao.findPersonByVo(queryVo);
        System.out.println(personByVo);
    }

    /**
     * 测试通过动态SQL的if和where标签实现查询
     */
    @Test
    public void testFindByCondition() {
        Person person = new Person();
        person.setName("肖战");
        person.setGender("中");
        List<Person> byCondition = personDao.findByCondition(person);
        for (Person p : byCondition)
            System.out.println(p);
    }

    /**
     * 测试通过动态SQL的foreach标签实现查询
     */
    @Test
    public void testFindByIds() {
        QueryVo queryVo = new QueryVo();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        queryVo.setIds(list);
        List<Person> byCondition = personDao.findByIds(queryVo);
        for (Person p : byCondition)
            System.out.println(p);
    }

}
