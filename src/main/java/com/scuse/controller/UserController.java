package com.scuse.controller;

import com.scuse.entity.User;
import com.scuse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jaho on 2018/3/16.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user){
        return userService.add(user);
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("id") int id){
        return userService.get(id);
    }

}
