package com.scuse.controller;

import com.scuse.dto.ReqLoginUser;
import com.scuse.dto.Result;
import com.scuse.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Result Login(@RequestBody ReqLoginUser reqLoginUser){
        return loginService.login(reqLoginUser);
    }

}
