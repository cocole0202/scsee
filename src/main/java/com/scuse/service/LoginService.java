package com.scuse.service;

import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.ReqLoginUser;
import com.scuse.dto.Result;
import com.scuse.dto.ReturnUserInfo;
import com.scuse.entity.Admin;
import com.scuse.entity.Candidate;
import com.scuse.entity.Review;
import com.scuse.mapper.AdminMapper;
import com.scuse.mapper.CandidateMapper;
import com.scuse.mapper.ReviewMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service("loginService")
public class LoginService {

    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    public Result login(ReqLoginUser reqLoginUser){

        String idNum = reqLoginUser.getIdNum();
        String phone = reqLoginUser.getPhone();
        String mail = reqLoginUser.getMail();
        String password = reqLoginUser.getPassword();
        Admin admin = null;
        Candidate candidate = null;

        if(idNum!=null){
            System.out.println("1通过身份证号登录");
            //1.查表并判断身份，得到用户身份信息
            admin = adminMapper.selectByIdNum(idNum);
        }
        else if(phone!=null){
            System.out.println("2通过手机号登录");
            admin = adminMapper.selectByPhone(phone);
        }
        else if(mail!=null){
            System.out.println("3通过邮箱登录");
            admin = adminMapper.selectByMail(mail);
        }
        else{ //三个字段都为空，出错。
            System.out.println("字段为空");
            return OpResult.LOGIN_ERROR;
        }

        if(admin!=null) //该用户身份为管理员
        {
            System.out.println("是管理员");
            //2.检查review表，是否通过审核
            Review review = reviewMapper.selectType(new Review(admin.getId(),7)); //管理员审核类型为7
            if(review!=null){ //未通过审核
                return OpResult.LOGIN_REVIEW_ERROR;
            }

            //3.检查密码是否正确
            if(!admin.getPassword().equals(password))
                return OpResult.WRONG_PASS_ERROR;

            //4.通过审核，返回对象
            ReturnUserInfo returnUserInfo = new ReturnUserInfo();
            returnUserInfo.setPhone(admin.getPhone());
            returnUserInfo.setToken(admin.getToken());
            returnUserInfo.setMail(admin.getMail());
            returnUserInfo.setIdNum(admin.getIdNum());

            //5.返回登录成功消息，以及对象
            //返回前应该更新数据库中有效日期
            Calendar afterAMounth = Calendar.getInstance();
            afterAMounth.setTime(new Date());
            afterAMounth.add(Calendar.DAY_OF_MONTH, 30);// 有效期30天
            Date newDate = afterAMounth.getTime();
            admin.setExpiredDate(newDate);
            adminMapper.updateByPrimaryKeySelective(admin);
            System.out.println("日期更新成功！");

            return new Result(new Error(0,"登录成功"),returnUserInfo);
        }
        else //该用户身份为考生或为空
        {
            if(idNum!=null){
                System.out.println("通过身份证号登录");
                candidate = candidateMapper.selectByIdNum(idNum);
            }
            else if(phone!=null){
                System.out.println("通过手机号登录");
                candidate = candidateMapper.selectByPhone(phone);
            }
            else if(mail!=null){
                System.out.println("通过邮箱登录");
                candidate = candidateMapper.selectByMail(mail);
            }
            else{ //三个字段都为空，出错。
                System.out.println("字段为空");
                return OpResult.LOGIN_ERROR;
            }

            if(candidate!=null) //用户身份为考生
            {
                System.out.println("是考生");
                //2.检查review表,是否通过审核
                Review review = reviewMapper.selectType(new Review(candidate.getId(),1)); //考生审核类型为1
                if(review!=null){ //未通过审核
                    return OpResult.LOGIN_REVIEW_ERROR;
                }

                //3.检查密码是否正确
                if(!candidate.getPassword().equals(password))
                    return OpResult.WRONG_PASS_ERROR;

                //4.通过审核，返回对象
                ReturnUserInfo returnUserInfo = new ReturnUserInfo();
                returnUserInfo.setIdNum(candidate.getIdNum());
                returnUserInfo.setMail(candidate.getMail());
                returnUserInfo.setPhone(candidate.getPhone());
                returnUserInfo.setToken(candidate.getToken());

                //返回前应该更新数据库中有效日期
                Calendar afterAMounth = Calendar.getInstance();
                afterAMounth.setTime(new Date());
                afterAMounth.add(Calendar.DAY_OF_MONTH, 30);// 有效期30天
                Date newDate = afterAMounth.getTime();
                candidate.setExpiredDate(newDate);
                candidateMapper.updateByPrimaryKeySelective(candidate);
                System.out.println("日期更新成功！");

                //5.返回登录成功消息，以及对象
                return new Result(new Error(0,"登录成功"),returnUserInfo);
            }
            else //用户信息不存在，出错。
            {
                return OpResult.LOGIN_EMPTY_ERROR;
            }
        }
    }
}
