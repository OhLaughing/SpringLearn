package com.learn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gustaov on 2019/3/13.
 */
@Controller
public class HomeController {
    @RequestMapping("hello")
    public String hello() {
        return "home";
    }
}
