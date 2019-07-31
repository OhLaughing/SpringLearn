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
        User username = userService.find(id);
        System.out.println(username);
        return username;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User user) {
        System.out.println(user);
        return userService.add(user);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public void update(@RequestBody User user){
        System.out.println(user);
        System.out.println(userService.update(user));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(int id){
        System.out.println("in delete");
        int i = userService.delete(id);
        System.out.println(i);
    }
}
