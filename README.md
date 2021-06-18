# 清单

Mybatis-notes，java项目

* demo01：入门案例xml配置
* demo02：入门案例注解配置
* demo03：自定义dao接口的实现类
* demo04：自定义mybatis框架（不采用mybatis的jar包，根据mybatis的原理重写类似mybatis的类和接口，实现xml配置和注解配置）
* demo05：通过mybatis的xml配置方式实现crud操作
* demo06：通过mybatis自定义实现类实现crud操作，并且学习如何通过debug查看底层源码，以及配置文件的配置细节
* demo01~demo06的主配置文件使用SqlMapConfig.xml，里面注释较多，方便学习。demo07之后，使用Mybatis.xml，尽量少添加注释。
* demo07：【没啥重点，看博客就够了】了解Mybatis的数据库连接池，并且了解mybatis底层归还连接的实现方式
* demo08：映射配置文件中动态Sql标签，if、where、foreach以及抽取重复语句优化
* demo09：通过mybatis的实体类实现多表查询，一对一（内连接），一对多（外连接），多对多（外连接+外连接）
* demo10：mybatis获取对象或者列表的懒加载
* demo11：mybatis的一级缓存和二级缓存
* demo12：注解开发，单表CRUD
* demo13：注解的实体类与数据库一一对应，以及实现一对一、一对多、多对多查询，以及注解实现缓存

Mybatis-jndi，javaweb项目

* 学习jndi数据库连接池

mybatis.sql

* 本项目的mysql数据库