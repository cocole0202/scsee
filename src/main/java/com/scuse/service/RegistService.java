package com.scuse.service;

import com.scuse.dto.OpResult;
import com.scuse.dto.ReqAdminEntity;
import com.scuse.dto.Result;
import com.scuse.entity.*;
import com.scuse.dto.ReqCandEntity;
import com.scuse.mapper.AdminMapper;
import com.scuse.mapper.CandidateMapper;
import com.scuse.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("registService")
public class RegistService {

    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private UserInfoTools userInfoTools;

    /*
    注册流程：
    1.检查该用户是否已经注册（admin和candidate表中）
    2.若未注册，则生成token, expiredTime等信息，并插入表中
    3.添加review表中等待审核

    token生成规则：
    1.16位MD5算法加密
    2.phone+idNum+mail+password
    3.以确保admin，candidate两张表中的token唯一
     */

    //管理员注册,REVIEW表中type=7
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result adminRegist(ReqAdminEntity reqAdminEntity) {
        try{
            System.out.println("管理员注册");
            //通过phone,mail,idNum检查该用户是否已经存在
            Admin admin_phone = adminMapper.selectByPhone(reqAdminEntity.getPhone());
            Admin admin_mail = adminMapper.selectByMail(reqAdminEntity.getMail());
            Admin admin_idNum = adminMapper.selectByIdNum(reqAdminEntity.getIdNum());

            Candidate candidate_phone = candidateMapper.selectByPhone(reqAdminEntity.getPhone());
            Candidate candidate_mail = candidateMapper.selectByMail(reqAdminEntity.getMail());
            Candidate candidate_idNum = candidateMapper.selectByIdNum(reqAdminEntity.getIdNum());

            if(admin_phone!=null || admin_mail!=null || admin_idNum!=null || candidate_idNum!=null || candidate_mail!=null || candidate_phone!=null){ //该用户已存在
                return OpResult.REG_EXIST_ERROR;
            }
            //检查phone，idNum，password三个字段是否为空
            if(reqAdminEntity.getPhone()==null || reqAdminEntity.getIdNum()==null || reqAdminEntity.getMail()==null || reqAdminEntity.getPassword()==null){
                return OpResult.REG_EMPTY_ERROR;
            }

            //该用户未注册,生成token
            String token = userInfoTools.getATokenStr();
            System.out.println("token:"+token);

            //获取一个月之后的DATE对象
            Calendar afterAMounth = Calendar.getInstance();
            afterAMounth.setTime(new Date());
            afterAMounth.add(Calendar.DAY_OF_MONTH, 30);// 有效期30天
            Date expiredDate = afterAMounth.getTime();

            Admin newAdmin = new Admin();
            int newID;
            try{
                newID = adminMapper.getMaxID()+1;
            }catch (Exception e){
                newID = 1;
            }
            //自动分配最大的id
            newAdmin.setId(newID);
            newAdmin.setIdNum(reqAdminEntity.getIdNum());
            newAdmin.setName(reqAdminEntity.getName());
            newAdmin.setPhone(reqAdminEntity.getPhone());
            newAdmin.setMail(reqAdminEntity.getMail());
            newAdmin.setPassword(userInfoTools.CreateMD5Code(reqAdminEntity.getPassword()));
            newAdmin.setGender(reqAdminEntity.getGender());
            newAdmin.setToken(token);
            newAdmin.setExpiredDate(expiredDate);
            newAdmin.setJobNum(reqAdminEntity.getJobNum());
            newAdmin.setLocation(reqAdminEntity.getLocation());
            newAdmin.setEmployer(reqAdminEntity.getEmployer());
            newAdmin.setDocUrl(reqAdminEntity.getDocUrl());

            //插入数据库
            adminMapper.insertSelective(newAdmin);

            //查询分配的ID
            Admin getAdmin = adminMapper.selectByPhone(reqAdminEntity.getPhone());
            reviewMapper.insert(new Review(getAdmin.getId(),7));
            return OpResult.REG_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return OpResult.REG_ERROR;
        }
    }

    //考生注册,review表中type=1
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result candRegist(ReqCandEntity reqCandEntity) {
        try{
            System.out.println("考生注册");

            //通过phone,mail,idNum检查该用户是否已经存在
            Admin admin_phone = adminMapper.selectByPhone(reqCandEntity.getPhone());
            Admin admin_mail = adminMapper.selectByMail(reqCandEntity.getMail());
            Admin admin_idNum = adminMapper.selectByIdNum(reqCandEntity.getIdNum());

            Candidate candidate_phone = candidateMapper.selectByPhone(reqCandEntity.getPhone());
            Candidate candidate_mail = candidateMapper.selectByMail(reqCandEntity.getMail());
            Candidate candidate_idNum = candidateMapper.selectByIdNum(reqCandEntity.getIdNum());

            if(admin_phone!=null || admin_mail!=null || admin_idNum!=null || candidate_idNum!=null || candidate_mail!=null || candidate_phone!=null){ //该用户已存在
                return OpResult.REG_EXIST_ERROR;
            }

            //检查phone，idNum，password三个字段是否为空
            if(reqCandEntity.getPhone()==null || reqCandEntity.getIdNum()==null || reqCandEntity.getMail()==null || reqCandEntity.getPassword()==null){
                return OpResult.REG_EMPTY_ERROR;
            }

            //该用户未注册,生成token
            String token = userInfoTools.getATokenStr();
            System.out.println("token:"+token);

            //获取一个月之后的DATE对象
            Calendar afterAMounth = Calendar.getInstance();
            afterAMounth.setTime(new Date());
            afterAMounth.add(Calendar.DAY_OF_MONTH, 30);// 有效期30天
            Date expiredDate = afterAMounth.getTime();

            Candidate newCandidate = new Candidate();
            int newID;
            try{
                newID = candidateMapper.getMaxID()+1;
            }catch (Exception e){
                newID = 1;
            }
            //自动分配最大的id
            newCandidate.setId(newID);
            newCandidate.setToken(token);
            newCandidate.setExpiredDate(expiredDate);
            newCandidate.setIdNum(reqCandEntity.getIdNum());
            newCandidate.setName(reqCandEntity.getName());
            newCandidate.setPhone(reqCandEntity.getPhone());
            newCandidate.setMail(reqCandEntity.getMail());
            newCandidate.setPassword(userInfoTools.CreateMD5Code(reqCandEntity.getPassword()));
            newCandidate.setGender(reqCandEntity.getGender());
            newCandidate.setOldName(reqCandEntity.getOldName());
            newCandidate.setEthnic(reqCandEntity.getEthnic());
            newCandidate.setPoliStatus(reqCandEntity.getPoliStatus());
            newCandidate.setAccLoc(reqCandEntity.getAccLoc());
            newCandidate.setOrigin(reqCandEntity.getOrigin());
            newCandidate.setCertType(reqCandEntity.getCertType());
            newCandidate.setCertNum(reqCandEntity.getCertNum());
            newCandidate.setBirthday(reqCandEntity.getBirthday());
            newCandidate.setEducation(reqCandEntity.getEducation());
            newCandidate.setZipCode(reqCandEntity.getZipCode());
            newCandidate.setMailAddr(reqCandEntity.getMailAddr());
            newCandidate.setRecipient(reqCandEntity.getRecipient());
            newCandidate.setCareer(reqCandEntity.getCareer());
            newCandidate.setEmployer(reqCandEntity.getEmployer());
            newCandidate.setWorkAddr(reqCandEntity.getWorkAddr());
            newCandidate.setWorkType(reqCandEntity.getWorkType());
            newCandidate.setExamType(reqCandEntity.getExamType());
            newCandidate.setExamMajor(reqCandEntity.getExamMajor());
            newCandidate.setRegiMeth(reqCandEntity.getRegiMeth());
            newCandidate.setRegiLoc(reqCandEntity.getRegiLoc());

            //插入数据库
            candidateMapper.insertSelective(newCandidate);
            //查询分配的ID
            Candidate getCand= candidateMapper.selectByPhone(reqCandEntity.getPhone());
            reviewMapper.insertSelective(new Review(getCand.getId(),1));
            return OpResult.REG_SUCCESS;
        }catch (Exception e) {
            return OpResult.REG_ERROR;
        }
    }

}
