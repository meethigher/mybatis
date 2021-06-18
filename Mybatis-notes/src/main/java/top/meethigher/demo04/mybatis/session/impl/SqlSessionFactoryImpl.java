package top.meethigher.demo04.mybatis.session.impl;

import top.meethigher.demo04.mybatis.config.Configuration;
import top.meethigher.demo04.mybatis.session.SqlSession;
import top.meethigher.demo04.mybatis.session.SqlSessionFactory;

/**
 * SqlSessionFactoryImpl
 * SqlSessionFactory接口的实现类
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/9
 */
public class SqlSessionFactoryImpl implements SqlSessionFactory {
    private Configuration config;

    public SqlSessionFactoryImpl(Configuration config) {
        this.config = config;
    }

    /**
     * 用于创建一个新的操作数据库对象
     *
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new SqlSessionImpl(config);
    }
}
