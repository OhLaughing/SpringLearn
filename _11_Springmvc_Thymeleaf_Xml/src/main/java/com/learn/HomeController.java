package com.learn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gustaov on 2019/3/17.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping("home")
    public String home() {
        return "home";
    }

    @RequestMapping("test")
    public String test() {
        return "test";
    }
}
