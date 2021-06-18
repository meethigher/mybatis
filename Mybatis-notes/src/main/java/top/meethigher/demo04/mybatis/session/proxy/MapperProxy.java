package top.meethigher.demo04.mybatis.session.proxy;

import top.meethigher.demo04.mybatis.config.Mapper;
import top.meethigher.demo04.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * MapperProxy
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/2/9
 */
public class MapperProxy implements InvocationHandler {
    //key是命名空间（全限定类名）+方法名
    private Map<String, Mapper> mappers;
    private Connection conn;
    public MapperProxy(Map<String,Mapper> mappers,Connection conn){
        this.mappers=mappers;
        this.conn=conn;
    }
    /**
     * 用于对方法进行增强，增强就是调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.获取方法名
        String methodName = method.getName();
        //2.获取方法所在声明的类的名称
        String className = method.getDeclaringClass().getName();
        //3.找到key
        String key = className + "." + methodName;
        //4.获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);
        //5.判断是否有mapper
        if(mapper==null){
            throw new IllegalArgumentException("传入的参数有误");
        }
        //6.以上都没有问题。则调用工具类查询所有
        return new Executor().selectList(mapper,conn);
    }
}
