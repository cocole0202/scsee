package com.scuse.controller;

import com.scuse.dto.ReqAdminEntity;
import com.scuse.dto.Result;
import com.scuse.dto.ReqCandEntity;
import com.scuse.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegistController {

    @Autowired
    RegistService registService;

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public Result AdminRegist(@RequestBody ReqAdminEntity reqAdminEntity){
        return registService.adminRegist(reqAdminEntity);
    }

    @RequestMapping(value = "/cand", method = RequestMethod.POST)
    public Result CandidateRegist(@RequestBody ReqCandEntity reqCandEntity){
        return registService.candRegist(reqCandEntity);
    }

}
