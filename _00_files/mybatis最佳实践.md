MyBatis入门实践
前言
MyBatis和Hibernate一样，是一个优秀的持久层框架。已经说过很多次了，原生的jdbc操作存在大量的重复性代码（如注册驱动，创建连接，创建statement，结果集检测等）。框架的作用就是把这些繁琐的代码封装，这样可以让程序员专注于sql语句本身。

MyBatis通过XML或者注解的方式将要执行的sql语句配置起来，并通过java对象和sql语句映射成最终执行的sql语句。最终由MyBatis框架执行sql，并将结果映射成java对象并返回。

正文

一，MyBatis的执行流程

mybatis配置文件，包括Mybatis全局配置文件和Mybatis映射文件，其中全局配置文件配置了数据源、事务等信息；映射文件配置了SQL执行相关的 
信息。
mybatis通过读取配置文件信息（全局配置文件和映射文件），构造出SqlSessionFactory，即会话工厂。
通过SqlSessionFactory，可以创建SqlSession即会话。Mybatis是通过SqlSession来操作数据库的。
SqlSession本身不能直接操作数据库，它是通过底层的Executor执行器接口来操作数据库的。Executor接口有两个实现类，一个是普通执行器，一个是缓存执行器（默认）。
Executor执行器要处理的SQL信息是封装到一个底层对象MappedStatement中。该对象包括：SQL语句、输入参数映射信息、输出结果集映射信息。其中输入参数和输出结果的映射类型包括java的简单类型、HashMap集合对象、POJO对象类型。

二，MyBatis经典入门案例

环境准备：
Jdk环境：jdk1.8.0_163
Ide环境：idea
数据库环境：MySQL5.6.31
Mybatis：3.4.6

Mysql创建数据库：
班级表：
create table tb_clazz(
    id int primary key auto_increment,
    code varchar(18)
);
班级表插入两条记录：
insert into tb_clazz(code) values ('j1601');
insert into tb_clazz(code) values ('j1602');

学生表：
create table tb_student(
    id int primary key auto_increment,
    name varchar(18),
    sex char(3),
    age int,
    clazz_id int,
    foreign key (clazz_id) references tb_clazz(id)
);

Maven 依赖：
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.4.6</version>
</dependency>
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.13</version>
</dependency>
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.7.16</version>
</dependency>

创建Clazz和Student实体类
public class Clazz {
    private Integer id;
    private String code;
/*getters and setters*/
}
public class Student {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Integer clazz_id;
  /*getters and setters*/
}

Mybatis-config.xml 配置文件：
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <typeAliases>
        <typeAlias type="com.demo.entity.Student" alias="student"></typeAlias>
    </typeAliases>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value=""/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mapper/StudentMapper.xml"/>
        <mapper resource="mapper/ClazzMapper.xml"/>
    </mappers>
</configuration>

创建StudentMapper 接口： 并增加基本增删改查方法，
public interface StudentMapper {
    int addStudent(Student user);
    int updateStudent(Student user);
    int deleteStudent(int id);
    Student selectStudent(int id);
}

创建StudentMapper.xml文件， 实现addStudent
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.demo.mapper.StudentMapper">
    <insert id="addStudent" parameterType="com.demo.entity.Student">
        insert into tb_student (name, sex, age, clazz_id) values(#{name}, #{sex}, #{age}, #{clazz_id})
    </insert>
</mapper>

测试addStudent方法：
public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession session = sqlSessionFactory.openSession();
    Student t = new Student();
    t.setName("james");
    t.setSex("M");
    t.setAge(23);
    t.setClazz_id(1);
    session.insert("addStudent", t);
    session.commit();
    session.close();
}

运行之后有如下的日志打印：说明成功插入一条记录到Student表
==>  Preparing: insert into tb_student (name, sex, age, clazz_id) values(?, ?, ?, ?) 
==> Parameters: james(String), M(String), 23(Integer), 1(Integer)
<==    Updates: 1

实现selectStudent方法，StudentMapper.xml中增加：
<select id="selectStudent" resultType="com.demo.entity.Student" parameterType="java.lang.Integer">
    select * from tb_student where id = #{id}
</select>
测试：
Student s = session.selectOne("com.demo.mapper.StudentMapper.selectStudent", 1);
System.out.println(s);


实现updateStudent方法，StudentMapper.xml中增加：
<update id="updateStudent" parameterType="com.demo.entity.Student">
    update tb_student
    set name = #{name},
        sex = #{sex},
        age = #{age},
        clazz_id = #{clazz_id}
    where id = #{id}
</update>

测试：
Student s = session.selectOne("com.demo.mapper.StudentMapper.selectStudent", 1);
s.setClazz_id(2);
session.update("com.demo.mapper.StudentMapper.updateStudent", s);
session.commit();

实现deleteStudent方法
StudentMapper.xml中增加：
<delete id="deleteStudent" parameterType="java.lang.Integer">
    delete from tb_student where id = #{id}
</delete>

测试：
session.delete("com.demo.mapper.StudentMapper.deleteStudent", 2);
session.commit();

至此，Mybatis实现基本增删改查功能都以实现

ResultMap
　　MyBatis的每一个查询映射的返回类型都是ResultMap，只是当我们提供的返回类型属性是resultType的时候，MyBatis对自动的给我们把对应的值赋给resultType所指定对象的属性，而当我们提供的返回类型是resultMap的时候，将数据库中列数据复制到对象的相应属性上，可以用于复制查询，两者不能同时用。
　　resultMap 元素是 MyBatis 中最重要最强大的元素。它就是让你远离 90%的需要从结果 集中取出数据的 JDBC 代码的那个东西, 而且在一些情形下允许你做一些 JDBC 不支持的事 情。 事实上, 编写相似于对复杂语句联合映射这些等同的代码, 也许可以跨过上千行的代码。 ResultMap 的设计就是简单语句不需要明确的结果映射,而很多复杂语句确实需要描述它们 的关系。
首先来说一下resultType
　　使用resultType进行输出映射，只有查询出来的列名和pojo（实体bean）中的属性名一致，该列才可以映射成功。
　　简单来说也就是你的数据库字段和JavaBean里的字段名称必须一致才能映射成功。
　　所以当我们JavaBean中的字段名和数据库字段名称有不同的时候，或者是多表查询的时候，一般会使用resultMap

1.对象属性和数据库表的列名对应
上面的例子中，对象属性（id、name、sex、age）和数据库表的列名一一对应，名称一致，因此可以直接用resultType="com.demo.entity.Student"来组装对象，但是如果对象属性和数据库列名不一致，就可以用resultMap来实现他们的对应关系，
为了测试，创建一个新的表，并插入几天初始数据，供查询使用：
create table tb_student2(
    stu_id int primary key auto_increment,
    stu_name varchar(18),
    stu_sex char(3),
    stu_age int,
    stu_clazz_id int,
    foreign key (stu_clazz_id) references tb_clazz(id)
);
insert into tb_student(stu_name, stu_sex, stu_age, stu_clazz_id) values('jack', '男', 22,1);
insert into tb_student(stu_name, stu_sex, stu_age, stu_clazz_id) values('rose', '女', 18,1);
insert into tb_student(stu_name, stu_sex, stu_age, stu_clazz_id) values('tom', '男', 25,2);
insert into tb_student(stu_name, stu_sex, stu_age, stu_clazz_id) values('mary', '女', 20,2);

在StudentMapper.xml中增加resultMap， 并修改select节点
<resultMap id="studentResultMap" type="com.demo.entity.Student">
    <id property="id" column="stu_id"/>
    <result property="name" column="stu_name" />
    <result property="sex" column="stu_sex"/>
    <result property="age" column="stu_age"/>
    <result property="clazz_id" column="stu_clazz_id"/>
</resultMap>

<select id="selectStudent" resultMap="studentResultMap" parameterType="java.lang.Integer">
    select * from tb_student2 where stu_id = #{id}
</select>

测试：
Student s = session.selectOne("com.demo.mapper.StudentMapper.selectStudent", 2);
System.out.println(s);

结果：
==>  Preparing: select * from tb_student2 where stu_id = ? 
==> Parameters: 2(Integer)
<==      Total: 1
Student{id=2, name='rose', sex='女', age=18, clazz_id=1}

可以看到，tb_student2表的列名虽然和Student类的属性名不一致，但是也可以通过resultMap来对应起来


在实际的项目开发中，还要更复杂的情况，例如执行的是一个多表查询语句，而返回的对象关联到另一个对象，此时简单的映射已经无法解决问题，必须用<resultMap.../>元素来完成关联映射
tb_clazz 表中之前已经插入了两条数据，再向tb_student表中插入几条测试数据：
insert into tb_student(name, sex, age, clazz_id) values('jack', '男', 22,1);
insert into tb_student(name, sex, age, clazz_id) values('rose', '女', 18,1);
insert into tb_student(name, sex, age, clazz_id) values('tom', '男', 25,2);
insert into tb_student(name, sex, age, clazz_id) values('mary', '女', 20,2);

将Student类的clazz_id属性改为Clazz对象：
public class Student {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Clazz clazz;


StudentMapper.xml如下：
<resultMap id="studentResultMap" type="com.demo.entity.Student">
    <id property="id" column="id"/>
    <result property="name" column="name" />
    <result property="sex" column="sex"/>
    <result property="age" column="age"/>
    <association property="clazz" column="clazz_id" javaType="com.demo.entity.Clazz"
                 select="selectClazzWithId"/>
</resultMap>

<select id="selectClazzWithId" resultType="com.demo.entity.Clazz">
    select * from tb_clazz where id = #{id}
</select>

<select id="selectStudent" resultMap="studentResultMap" parameterType="java.lang.Integer">
    select * from tb_student where id = #{aa}
</select>


测试：
Student s = session.selectOne("com.demo.mapper.StudentMapper.selectStudent", 3);
System.out.println(s);

结果：
==>  Preparing: select * from tb_student where id = ? 
==> Parameters: 3(Integer)
====>  Preparing: select * from tb_clazz where id = ? 
====> Parameters: 1(Integer)
<====      Total: 1
<==      Total: 1
Student{id=3, name='jack', sex='男', age=22, clazz=Clazz{id=1, code='j1601'}}

可以通过结果看出，通过resultMap，成功实现了多表查询，完成了一对一查询，

下面来实现一对多的查询，
Clazz类中增加如下属性：
private List<Student> students;

创建ClazzMapper接口：并提供selectClazz方法
public interface ClazzMapper {
    Clazz selectClazz(int id);
}

创建ClazzMapper.xml文件，相应的信息如下：
<resultMap id="clazzResultMap" type="com.demo.entity.Clazz">
    <id column="id" property="id"/>
    <result column="code" property="code"/>
    <collection property="students" column="id" select="selectStudent" ofType="com.demo.entity.Student"/>
</resultMap>
<select id="selectStudent" resultType="com.demo.entity.Student">
    select * from tb_student where clazz_id=#{id}
</select>
<select id="selectClazz" resultMap="clazzResultMap" >
    select * from tb_clazz where id = #{id}
</select>

测试：
List<Clazz> list = session.selectList("selectClazz",1 );
System.out.println(list);

结果：
==>  Preparing: select * from tb_clazz where id = ? 
==> Parameters: 1(Integer)
====>  Preparing: select * from tb_student where clazz_id=? 
====> Parameters: 1(Integer)
<====      Total: 2
<==      Total: 1
[Clazz{id=1, code='j1601', students=[Student{id=3, name='jack', sex='男', age=22, clazz_id=1}, Student{id=4, name='rose', sex='女', age=18, clazz_id=1}]}]

至此，完成了MyBatis的一对多的查询
