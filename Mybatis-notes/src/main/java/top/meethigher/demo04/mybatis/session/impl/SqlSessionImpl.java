package top.meethigher.demo04.mybatis.session.impl;

import top.meethigher.demo04.mybatis.config.Configuration;
import top.meethigher.demo04.mybatis.session.SqlSession;
import top.meethigher.demo04.mybatis.session.proxy.MapperProxy;
import top.meethigher.demo04.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSessionImpl
 * SqlSession接口的实现类
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/9
 */
public class SqlSessionImpl implements SqlSession {
    private Configuration config;
    private Connection conn;

    public SqlSessionImpl(Configuration config) {
        this.config = config;
        this.conn= DataSourceUtil.getConnection(config);
    }

    /**
     * 用于创建代理对象
     *
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MapperProxy(config.getMappers(),conn));
    }

    /**
     * 用于释放资源
     */
    @Override
    public void close() {
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接释放有误...");
        }
    }
}
