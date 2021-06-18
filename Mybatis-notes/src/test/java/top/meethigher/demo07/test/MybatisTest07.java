package top.meethigher.demo07.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.meethigher.demo07.dao.PersonDao;
import top.meethigher.demo07.domain.Person;
import top.meethigher.demo07.domain.QueryVo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MybatisTest07
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/13
 */
public class MybatisTest07 {
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
        //3.获取SqlSession对象，传入true表示设置事务自动提交
        sqlSession = factory.openSession(true);
        //4.获取代理dao对象
        personDao = sqlSession.getMapper(PersonDao.class);
    }

    @After //用于在测试方法之后执行
    public void destroy() throws IOException {
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
     * 测试保存数据
     */
    @Test
    public void testSave() throws IOException {
        Person person = new Person();
        person.setName("肖战");
        person.setGender("男");
        person.setAge(38);
        person.setGrade(1);
        person.setSchool("腾讯");
        person.setPosition("日本");
        System.out.println("插入前：" + person);
        personDao.savePerson(person);
        System.out.println("插入后：" + person);

    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdatePerson() {
        Person person = new Person();
        person.setId(11);
        person.setName("蔡徐坤");
        person.setGender("男");
        person.setAge(38);
        person.setGrade(1);
        person.setSchool("腾讯");
        person.setPosition("日本");
        personDao.updatePerson(person);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDeletePerson() {
        personDao.deletePerson(13);
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
        List<Person> persons = personDao.findByName("月");
        System.out.println(persons);
    }

    /**
     * 查询所有条数
     */
    @Test
    public void testFindTotal() {
        int total = personDao.findTotal();
        System.out.println(total);
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
}
