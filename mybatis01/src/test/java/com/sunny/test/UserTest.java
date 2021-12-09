package com.sunny.test;

import com.sunny.mapper.RoleMapper;
import com.sunny.mapper.UserMapper;
import com.sunny.pojo.RoleUserDto;
import com.sunny.pojo.UserRoleDto;
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
import java.util.List;

public class UserTest {

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

    /**
     * 多对一
     */
    @Test
    public void testQueryUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper);
        List<UserRoleDto> userDtos = mapper.queryUser();
        System.out.println(userDtos);
    }

    /**
     * 一对多
     */
    @Test
    public void testRoleMapper(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        List<RoleUserDto> roleUserDtos = mapper.queryRole();
        System.out.println(roleUserDtos);
    }

    /**
     * 嵌套查询
     */
    @Test
    public void testRoleMapper2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        List<RoleUserDto> roleUserDtos = mapper.queryRole2();
        System.out.println(roleUserDtos);
    }

}
