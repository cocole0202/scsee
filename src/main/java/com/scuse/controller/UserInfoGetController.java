package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.service.UserInfoGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserInfoGetController {

    @Autowired
    private UserInfoGetService userInfoGetService;

    @ResponseBody
    @RequestMapping(value="cand/get/{id}")
    public Result getCandById(@PathVariable("id") int id){
        return userInfoGetService.candGetInfoById(id);
    }

    @ResponseBody
    @RequestMapping(value = "cand/get/all")
    public Result getAllCand(){
        return userInfoGetService.candGetInfoAll();
    }

    @ResponseBody
    @RequestMapping(value="admin/get/{id}")
    public Result getAdminById(@PathVariable("id") int id){
        return userInfoGetService.adminGetInfoById(id);
    }

    @ResponseBody
    @RequestMapping(value = "admin/get/all")
    public Result getAllAdmin(){
        return userInfoGetService.adminGetInfoAll();
    }


}
