package com.demo.demo;

import com.demo.mapper.StudentMapper;
import com.demo.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 传递多个参数的demo
 * Created by gustaov on 2019/8/11.
 */
public class StudentMulParamDemo {
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
        List<Student> students = studentMapper.findByAddressAndMajor("guangzhou", "math");
        for (Student student : students) {
            System.out.println(student);
        }

        students = studentMapper.findByAddressAndMajor1("guangzhou", "math");
        for (Student student : students) {
            System.out.println(student);
        }

        Map<String, String> map = new HashMap<>();
        map.put("address_1", "guangzhou");
        map.put("major", "math");
        List<Student> students1 = studentMapper.findByMap(map);
        for (Student student : students1) {
            System.out.println(student);
        }

    }
}
