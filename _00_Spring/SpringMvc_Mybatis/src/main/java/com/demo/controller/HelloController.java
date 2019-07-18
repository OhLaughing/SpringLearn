package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gustaov on 2019/5/16.
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
        return "hello";
    }

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return "this is test";
    }
}
