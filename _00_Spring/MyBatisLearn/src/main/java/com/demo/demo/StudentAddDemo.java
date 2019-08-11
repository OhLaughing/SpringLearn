package com.demo.demo;

import com.demo.mapper.StudentMapper;
import com.demo.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取自动生成主键的方法
 * Created by gustaov on 2019/8/11.
 */
public class StudentAddDemo {
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
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student s = new Student();
        s.setName("james");
        s.setMajor("computer");
        int i = studentMapper.add(s);
        System.out.println(i);
        System.out.println(s);
    }
}
