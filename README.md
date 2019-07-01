**手写 mybatis 框架分析**

MyBatis 是基于 JDBC 操作封装的持久层框架，JDBC 是一套 Java 连接数据库的规范


# 目标

* 通过手写 mybatis 框架，理解 mybatis 的框架原理
* 提升面向对象编码能力（OOP）以及设计模式的使用能力
* 可以帮助我们阅读 mybatis 的源码



# 思路

1、Java 项目持久层使用 JDBC 实现，并演示 JDBC 代码

回顾JDBC代码的编写步骤

2、先分析实现代码中技术本身的问题

问题：也就是使用 JDBC 代码实现的问题，包括功能划分（创建连接、statement 操作）、硬编码问题。  
解决方案：针对创建连接和 statement 操作中的硬编码使用配置文件来解决。

3、再分析实现代码中用户体验的问题

问题：就是 CRUD 代码编写比较繁琐。  
解决方案：将具体的CRUD代码进行封装，对外提供简单的接口调用就可以了。

如此我们可以将封装的实现代码搞成一个框架，直接给应用代码调用即可。



# 框架总体设计分析
## 接口如何设计？

SqlSession 接口（CRUD 的方法）、DefaultSqlSession 实现类
* Object selectOne(String statementId，Object object)
* List\<Object\> selectList(String statementId，Object object)
* void insert(String statementId，Object object)

## 配置文件如何编写？

全局配置文件如何编写 (直接参考mybatis本身配置文件的编写)
* 配置数据源 DataSource
* mapper映射文件如何编写（配置SQL语句，一个SQL语句对应一个statement执行，每个statement都有一个唯一的id）
* SQL语句
* 参数
* 映射对象  	

## 读取全局配置文件
将全局配置文件信息封装到一个对象中 **Configuration**
* DataSource 信息
* Map<String, MappedStatement>	 

## 读取映射配置文件

* 很多个 MappedStatement 对象（对应映射文件中的一个 select 标签）
* SQL语句
* statement类型
* 输入参数java类型
* 输出结果java类型

## 接口实现类的功能如何实现？

读取配置文件的工作，一定是提前完成，而且只需要完成一次

### 获取连接
读取配置文件，获取数据源对象，根据数据源获取连接
* 通过Configuration对象获取DataSource对象
* 通过DataSource对象，获取Connection

### 执行 statement 操作
（要考虑是执行哪种Statement，不同的statement，操作不同，参数设置也不同，结果集处理也不同）
* 获取statement的类型。读取映射配置文件，获取要执行的SQL语句的statement类型（statement、preparedstatement、callablestatement）
* 根据statement的ID，去Configuration对象中查找对应的Statement对象
* 通过MappedStatement对象获取statementType类型
  * 如果是 preparedstatement。读取映射配置文件，获取要执行的SQL语句
  * 通过MappedStatement对象获取SQL语句（SQL语句的获取需要仔细处理）
    SELECT * FROM user where id = #{id} and username = #{username}
    SQL语句：SELECT * FROM user where id = ? and username= ?
  * 解析占位符参数：List\<ParameterMapping\>		
    ParameterMapping(参数名称)
    解析#{}中的参数名称：id
  * 给SQL语句设置参数
    遍历List\<ParameterMapping\>挨个处理入参
    获取入参的Java类型，根据类型（8种基本类型、String类型、POJO类型等）判断如何获取参数值
    比如说如果是Integer类型，则只有将入参对象直接赋值给SQL语句即可
    preparedStatement.setObject(1, "王五");
    如果是POJO类型，通过反射根据参数名称获取POJO对象的属性值
    preparedStatement.setObject(1, "王五");
  * 执行statement
    rs = preparedStatement.executeQuery();
  * 处理结果集
    获取要封装的java对象类型（Class对象）
    通过MappedStatement对象获取结果映射的Java类型
    遍历结果集，取出结果集中的每条结果的列名
    通过rs获取metaData（列名）
    根据列名通过反射获取java对象中的field名称
    要求：SQL语句的列名一定要和java对象中的属性名称一致。
    通过反射给指定field赋值

## 配置文件如何解析？

dom4j---第三方
* 指定要解析的配置文件的路径（类路径、磁盘路径、网络路径）---Resource
* 通过类加载器去指定路径加载，放入InputStream流对象
*读取Source资源中的数据
* 通过InputStream流对象，去创建Document对象（dom4j）---此时没有针对xml文件中的语义进行解析
* DocumentReader---去加载InputStream流，创建Document对象的
* 进行mybatis语义解析（全局配置文件语义解析、映射文件语义解析）
* XMLConfigParser---解析全局配置文件
* XMLMapperParser---解析全局配置文件

## SqlSession对象如何创建？

使用工厂模式创建----SqlSessionFactory
批量生产对象（）

## 工厂对象如何创建？

使用构建者模式创建---SqlSessionFactoryBuiler
私人订制（）

# 需求开发

根据用户ID，查询用户信息（user表）