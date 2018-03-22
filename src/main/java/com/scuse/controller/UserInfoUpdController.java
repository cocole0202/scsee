package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.dto.UpdUserEntity;
import com.scuse.service.UserInfoUpdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/upd")
public class UserInfoUpdController {

    @Autowired
    private UserInfoUpdService userInfoUpdService;

    @RequestMapping(method = RequestMethod.POST)
    public Result UserInfoUpd(HttpServletRequest request, @RequestBody UpdUserEntity updUserEntity){
        return userInfoUpdService.EditUserInfo(request,updUserEntity);
    }
}
