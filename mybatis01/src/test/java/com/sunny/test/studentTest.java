package com.sunny.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunny.mapper.StudentMapper;
import com.sunny.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class studentTest {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    SqlSessionFactory sqlSessionFactory;
    @Before
    public void before(){
        // 从 XML 中构建 SqlSessionFactory
        String resource = "mybatis.xml";
        InputStream inputStream = null;
        try {
            //将xml构建成输入流
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //构建全局 sqlSessionFactory： 将全局配置文件和所有 mapper加载到 Configuration
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // mybatis在调用 getMapper 时就会给我们创建 Jdk的 动态代理

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper);
        Student student = mapper.getStudent(1);
        System.out.println(student);
    }

    /**
     * 日志级别
     *  trace<debug<info<warn<error
     *  1      2     3     4    5
     *  logback.xml中没有设置级别的话，默认使用 debug级别，
     *   反之用xml中设置
     */
    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List students = mapper.queryStudent(new Student());
        System.out.println(students);
    }

    @Test
    public void test03(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // mybatis在调用 getMapper 时就会给我们创建 Jdk的 动态代理

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Long studentTotal = mapper.getStudentTotal();
        System.out.println(studentTotal);
    }

    /**
     * 一级缓存：默认开启(可以设置关闭)
     * 作用域:基于 sqlsession
     * 生效情况: 同一个 sqlSession中(同一个会话)
     * 缓存是在查询完的时候进行的，缓存的实现类 PerpetualCache，存储在 PerpetualCache中 名称为cache的Map中
     * key 为 sqlid+sql
     * 失效情况：
     *   1：不同的 sqlSession，缓存失效
     *   2：同一个 sqlSession中，同一个sql，查询语句不同,缓存也会失效
     *   3：同一个sqlSession中,同一个sql，但中间执行了增删改 ，缓存也会失效
     *   4：同一个sqlSession，执行手动清除缓存， sqlSession.clearCache();
     *
     */
    @Test
    public void testcache(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        /*System.out.println("==========读取缓存中的 （sql只执行了一次）==============");
        List<Student> students = mapper.queryStudent(null); //查库,查完会立马加到缓存中
        List<Student> students2 = mapper.queryStudent(null);//查的是缓存中的

        System.out.println("==========不同的sqlSession==============");
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        StudentMapper mapper2 = sqlSession2.getMapper(StudentMapper.class);
        mapper2.queryStudent(null);
        System.out.println("==========同一个 sqlSession中，同一个sql，查询语句不同,缓存也会失效==============");

        List<Student> students11 = mapper.queryStudent(new Student(1, null));
        List<Student> students12 = mapper.queryStudent(new Student(1,"张三"));*/

        System.out.println("============同一个sqlSession中,同一个sql，但中间执行了增删改 ，缓存也会失效（这个效果需要单独执行下可看出）==================");
        List<Student> students21 = mapper.queryStudent(null); //查库
        mapper.insetStudent("王五");
        sqlSession.commit();
        List<Student> students22 = mapper.queryStudent(null);//

    }

    /**
     * 二级缓存:
     *  特性 :二级缓存也是默认开启的,，没有实现; 配置<setting name="cacheEnabled" value="true"/>
     *  作用域 基于全局范围，是在整个应用中的
     *  实现二级缓存时，用到的 JavaBean需要实现 序列化 public class Student implements Serializable
     *  XML映射的 mapper需要 配置 <cache></cache>
     *  缓存是在事务提交（commit/sqlSession关闭时）的时候进行的，缓存的实现类 PerpetualCache，存储在 PerpetualCache中 名称为cache的Map中
     *  但这个map中会多包一层,第一层map 的 key为映射文件的命名空间,在第一层map中会再有一层map,它的map的key是
     *  sqlid+sql,第二层的key是与一级缓存是一样的
     *  取法：先从二级缓存中取，二级缓存中没有再从一级缓存中取
     *
     *  失效:
     *  1: 同一个命名空间中执行了增删改,XML的 sql中默认配置 flushCache="true",则该sql的二级缓存会清空
     *  flushCache="false",表示不会清空;注意设置false可能会导致出现脏读:例如sql查询为2条，新增一条后没有将
     *  二级缓存失效，后面再查该条sql的数据时，实际数据时3条，但还是从二级缓存中只取出2条；
     *  2: <cache-ref></cache-ref> 引用namespace所指定的 mapper映射文件的缓存机制，执行增删改会清空指定 namespace 的缓存
     *  种种情况基本在 联合查询中，但 cache-ref只能指定一个 mapper的命名空间，可能会造成数据脏读，所有基本二级缓存不会
     *  开启
     *
     *
     */
    @Test
    public void testSecondCache(){
        /*SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println("====同一个sqlSession中相同sql是从 一级缓存中取的=====");
        List<Student> students11 = mapper.queryStudent(null);
        List<Student> students12 = mapper.queryStudent(null);*/

       /* System.out.println("=====不同的sqlSession查询是从二级缓存中取到====");
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        StudentMapper mapper2 = sqlSession2.getMapper(StudentMapper.class);
        List<Student> students21 = mapper2.queryStudent(null);
        sqlSession2.commit();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        StudentMapper mapper3 = sqlSession3.getMapper(StudentMapper.class);
        List<Student> students22 = mapper3.queryStudent(null);*/

        System.out.println("=====失效:同一个命名空间中执行了增删改====");
        SqlSession sqlSession4 = sqlSessionFactory.openSession();
        StudentMapper mapper4 = sqlSession4.getMapper(StudentMapper.class);
        List<Student> students41 = mapper4.queryStudent(null);
        mapper4.insetStudent("王五1");
        sqlSession4.commit();
        SqlSession sqlSession5 = sqlSessionFactory.openSession();
        StudentMapper mapper5 = sqlSession5.getMapper(StudentMapper.class);
        List<Student> students25 = mapper5.queryStudent(null);
    }

    @Test
    public void testQueryPageStudent(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // mybatis在调用 getMapper 时就会给我们创建 Jdk的 动态代理
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        PageHelper.startPage(1,5);
        List<Student> students = mapper.queryPageStudent();
        PageInfo page = new PageInfo(students);
        System.out.println(students);
        System.out.println(page.getTotal());
        System.out.println(students.size());
    }

    @Test
    public void testQueryPageParamStudent(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // mybatis在调用 getMapper 时就会给我们创建 Jdk的 动态代理
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map map = new HashMap();
        map.put("name","张三");
        List<Student> students = mapper.queryPageParamStudent(map,1,10);
        PageInfo pageInfo = new PageInfo(students);
        System.out.println("size : "+students.size()+",total :"+ pageInfo.getTotal());
    }
}
