package com.learn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gustaov on 2019/3/28.
 */
@Controller
public class HelloController {
    @RequestMapping("/hello1")
    public String hello1() {
        return "hello1";
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return "hello2";
    }

    @RequestMapping("/loginPage")
    public String login() {
        return "login";
    }
}
