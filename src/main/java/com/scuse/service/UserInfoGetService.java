package com.scuse.service;

import com.scuse.dto.Error;
import com.scuse.dto.Result;
import com.scuse.entity.Admin;
import com.scuse.entity.Candidate;
import com.scuse.mapper.AdminMapper;
import com.scuse.mapper.CandidateMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service("userInfoGetService")
public class UserInfoGetService {
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private AdminMapper adminMapper;

    /*
    根据输入id获取admin用户
     */
    public Result adminGetInfoById(@RequestParam int index){
        Admin admin = adminMapper.selectById(index);
        admin.setExpiredDate(null);
        admin.setPassword(null);
        admin.setToken(null);
        return new Result(new Error(0,"获取用户信息成功"),admin);
    }

    /*
    获取全部admin用户
     */
    public Result adminGetInfoAll(){
        List<Admin> list = adminMapper.getAll();
        for(Admin walk: list){
            walk.setToken(null);
            walk.setPassword(null);
            walk.setExpiredDate(null);
        }
        return new Result(new Error(0,"获取全部用户信息成功"),list);
    }

    /*
    根据输入id获取candidate用户
     */
    public Result candGetInfoById(@RequestParam int index){
        Candidate candidate = candidateMapper.selectById(index);
        candidate.setExpiredDate(null);
        candidate.setToken(null);
        candidate.setPassword(null);
        return new Result(new Error(0,"获取用户信息成功"),candidate);
    }

    /*
    获取全部candidate用户
     */
    public Result candGetInfoAll(){
        List<Candidate> list = candidateMapper.getAll();
        for(Candidate walk:list){
            walk.setToken(null);
            walk.setPassword(null);
            walk.setExpiredDate(null);
        }
        return new Result(new Error(0,"获取全部用户信息成功"),list);
    }

}
