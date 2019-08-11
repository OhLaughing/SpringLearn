package com.demo.mapper;

import com.demo.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by gustaov on 2019/8/11.
 */
public interface StudentMapper {
    int add(Student student);

    List<Student> findByAddressAndMajor1(String address, String major);

    List<Student> findByAddressAndMajor(@Param("address_1") String address, @Param("major") String major);

    List<Student> findByMap(Map<String, String> mapInfo);
}
