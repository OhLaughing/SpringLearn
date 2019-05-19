package com.demo.controller;


import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public User queryUser(int id) {
        User username = userService.getUser(id);
        System.out.println(username);
        return username;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User user) {
        System.out.println(user);
        return userService.addUser(user);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public void update(@RequestBody User user){
        System.out.println(user);
        System.out.println(userService.updateUser(user));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(int id){
        System.out.println("in delete");
        boolean i = userService.delete(id);
        System.out.println(i);
    }
}
