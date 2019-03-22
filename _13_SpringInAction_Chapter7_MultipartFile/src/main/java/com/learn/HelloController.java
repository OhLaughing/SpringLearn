package com.learn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gustaov on 2019/3/22.
 */
@Controller
@RequestMapping("/")
public class HelloController {
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
