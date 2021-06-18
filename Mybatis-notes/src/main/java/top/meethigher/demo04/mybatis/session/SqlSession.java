package top.meethigher.demo04.mybatis.session;

/**
 * SqlSession
 * 自定义mybatis中和数据库交互的核心类
 * 它里面可以创建dao接口的代理对象
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/9
 */
public interface SqlSession {
    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    //正常应是T getMapper()，但是泛型一般先声明再使用，所以前面加<T>表示声明
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
