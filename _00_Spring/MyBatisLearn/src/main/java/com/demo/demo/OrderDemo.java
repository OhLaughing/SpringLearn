package com.demo.demo;

import com.demo.mapper.RoleMapper;
import com.demo.pojo.Info;
import com.demo.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 通常情况#{} 和 ${} 要选择#{}， 但是在order by的场景中要用$
 * Created by gustaov on 2019/6/7.
 */
@Slf4j
public class OrderDemo {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = null;
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        RoleMapper roleMapper = session.getMapper(RoleMapper.class);
        Info info = new Info();
        info.setOrderName("role_name");
        info.setDesc(true);
        List<Role> roleList = roleMapper.selectRoles(info);
        for (Role role : roleList) {
            System.out.println(role);
        }
    }
}
