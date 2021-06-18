package top.meethigher.demo06.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import top.meethigher.demo06.dao.impl.PersonDaoImpl;
import top.meethigher.demo06.domain.Person;
import top.meethigher.demo06.domain.QueryVo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MybatisTest06
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/12
 */
public class MybatisTest06 {
    private InputStream is;
    private SqlSessionFactory factory;
    private PersonDaoImpl personDao;

    @Before
    public void init() throws Exception {
        //1.读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlSession
        factory = new SqlSessionFactoryBuilder().build(is);
        //3.创建自定义dao实现类对象
        personDao = new PersonDaoImpl(factory);
    }

    @After
    public void destroy() throws Exception {
        if (is != null) {
            is.close();
        }
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
        person.setName("雷军");
        person.setAge(1);
        person.setGender("男");
        person.setGrade(1);
        person.setSchool("武大");
        person.setPosition("武汉");
        personDao.savePerson(person);
        System.out.println("插入的数据：" + person);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdatePerson() {
        Person person = new Person();
        person.setName("雷布斯");
        person.setId(19);
        person.setAge(1);
        person.setGender("男");
        person.setGrade(1);
        person.setSchool("武大");
        person.setPosition("武汉");
        personDao.updatePerson(person);
        System.out.println("更新的数据：" + person);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDeletePerson() {
        personDao.deletePerson(11);
    }

    /**
     * 测试通过id查询
     */
    @Test
    public void testFindPersonById() {
        Person personById = personDao.findPersonById(19);
        System.out.println(personById);
    }

    /**
     * 测试通过名字模糊查询
     */
    @Test
    public void testFindByName() {
        List<Person> byName = personDao.findByName("月");
        System.out.println(byName);
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
