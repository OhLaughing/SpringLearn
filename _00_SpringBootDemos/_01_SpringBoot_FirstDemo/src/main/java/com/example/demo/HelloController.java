package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gustaov on 2019/4/12.
 */
@RestController
@RequestMapping("demo")
public class HelloController {
    @RequestMapping("hello")
    public String hello() {
        return "_01_SpringBoot_FirstDemo";
    }
}
