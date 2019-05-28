package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by gustaov on 2019/5/28.
 */
@Data
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
}
