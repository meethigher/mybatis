package top.meethigher.demo04.mybatis.session;

import top.meethigher.demo04.mybatis.config.Configuration;
import top.meethigher.demo04.mybatis.session.impl.SqlSessionFactoryImpl;
import top.meethigher.demo04.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * SqlSessionFactoryBuilder
 * 用于创建SqlSessionFactory对象
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/9
 */
public class SqlSessionFactoryBuilder {
    /**
     * 根据输入的字节流来构建一个SqlSessionFactory工厂
     * @param is
     * @return
     */
    public SqlSessionFactory build(InputStream is){
        Configuration config = XMLConfigBuilder.loadConfiguration(is);
        return new SqlSessionFactoryImpl(config);
    }
}

