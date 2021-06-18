package top.meethigher.demo05.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.meethigher.demo05.dao.PersonDao;
import top.meethigher.demo05.domain.Person;
import top.meethigher.demo05.domain.QueryVo;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试Mybatis的crud操作
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/9
 */
public class MybatisTest05 {
    private InputStream is;
    private SqlSession sqlSession;
    private PersonDao personDao;

    @Before //用于在测试方法之前执行
    public void init() {
        //1.读取配置文件，获取输入流
        try {
            is = Resources.getResourceAsStream("SqlMapConfig.xml");
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
        //提交事务
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

        //只通过以上代码是无法增加数据的，打开log4j下的rootLogger为debug，查看日志信息
        //通过debug日志可知关闭了自动提交事务，如果想要修改数据，那么需要手动提交事务
        /**
         * Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
         * PooledDataSource forcefully closed/removed all connections.
         * PooledDataSource forcefully closed/removed all connections.
         * PooledDataSource forcefully closed/removed all connections.
         * PooledDataSource forcefully closed/removed all connections.
         * Opening JDBC Connection
         * Created connection 150138649.
         * Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@8f2ef19]
         * Preparing: insert into fairy(name,gender,age,grade,school,position) values(?,?,?,?,?,?);
         * Parameters: 肖战(String), 男(String), 38(Integer), 1(Integer), 腾讯(String), 日本(String)
         * Updates: 1
         * Rolling back JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@8f2ef19]
         * Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@8f2ef19]
         * Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@8f2ef19]
         * Returned connection 150138649 to pool.
         */

        //提交事务
//        sqlSession.commit();//为了方便使用，将该代码，放到destroy中
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
