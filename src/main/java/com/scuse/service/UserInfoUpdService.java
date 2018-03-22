package com.scuse.service;

import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.dto.UpdUserEntity;
import com.scuse.entity.*;
import com.scuse.dto.ReqAdminEntity;
import com.scuse.mapper.AdminMapper;
import com.scuse.mapper.CandidateMapper;
import com.scuse.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service("userInfoUpdService")
public class UserInfoUpdService {
    /*
    这一部分完成考生、管理员两种身份用户信息的更改
    更新步骤
    1.使用UpdUserEntity接收数据
    2.获取attribute中的身份信息，并创建用户实体
    3.检查用户输入信息中，phone、mail、id_num是否已存在于其他用户中(admin,candidate两张表)
    3.用户实体接收更新
    5.同步用户实体到数据库
    6.插入review表
     */

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private UserInfoTools userInfoTools;
    @Autowired
    private ReviewMapper reviewMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result EditUserInfo(HttpServletRequest request, UpdUserEntity updUserEntity) {
        try{
            //1.接收身份信息
            String id_s = (String)request.getAttribute("id").toString();
            String type_s = (String)request.getAttribute("type").toString();
            int id = Integer.parseInt(id_s);
            int type = Integer.parseInt(type_s);

            //2.在两个表中检查phone，mail，idNum是否重复
            Admin admin_phone = adminMapper.selectByPhone(updUserEntity.getPhone());
            Admin admin_mail = adminMapper.selectByMail(updUserEntity.getMail());
            Admin admin_idNum = adminMapper.selectByIdNum(updUserEntity.getIdNum());
            Candidate candidate_phone = candidateMapper.selectByPhone(updUserEntity.getPhone());
            Candidate candidate_mail = candidateMapper.selectByMail(updUserEntity.getMail());
            Candidate candidate_idNum = candidateMapper.selectByIdNum(updUserEntity.getIdNum());
            if(admin_phone!=null || admin_mail!=null || admin_idNum!=null || candidate_idNum!=null || candidate_mail!=null || candidate_phone!=null){
                return OpResult.UPD_DUPLI_ERROR; //该手机/邮箱/身份证号已有其他账户，不可更改
            }

            //3.查找数据库中对应实体,并新建一个实体完成对数据库的修改
            if(type==1){ //身份是考生
                System.out.println("考生");
                Candidate candidate = candidateMapper.selectById(id);
                if(candidate==null) //账户不存在
                    return OpResult.LOGIN_EMPTY_ERROR;

                Candidate newCand = new Candidate();
                newCand.setId(candidate.getId());
                newCand.setToken(candidate.getToken());
                newCand.setExpiredDate(candidate.getExpiredDate());
                newCand.setIdNum(updUserEntity.getIdNum());
                newCand.setName(updUserEntity.getName());
                newCand.setPhone(updUserEntity.getPhone());
                newCand.setMail(updUserEntity.getMail());
                if(updUserEntity.getPassword()!=null && updUserEntity.getPassword()!="")
                    newCand.setPassword(userInfoTools.CreateMD5Code(updUserEntity.getPassword()));
                else{
                    newCand.setPassword(candidate.getPassword());
                }
                newCand.setGender(updUserEntity.getGender());
                newCand.setOldName(updUserEntity.getOldName());
                newCand.setEthnic(updUserEntity.getEthnic());
                newCand.setPoliStatus(updUserEntity.getPoliStatus());
                newCand.setAccLoc(updUserEntity.getAccLoc());
                newCand.setOrigin(updUserEntity.getOrigin());
                newCand.setCertType(updUserEntity.getCertType());
                newCand.setCertNum(updUserEntity.getCertNum());
                newCand.setBirthday(updUserEntity.getBirthday());
                newCand.setEducation(updUserEntity.getEducation());
                newCand.setZipCode(updUserEntity.getZipCode());
                newCand.setMailAddr(updUserEntity.getMailAddr());
                newCand.setRecipient(updUserEntity.getRecipient());
                newCand.setCareer(updUserEntity.getCareer());
                newCand.setEmployer(updUserEntity.getEmployer());
                newCand.setWorkAddr(updUserEntity.getWorkAddr());
                newCand.setWorkType(updUserEntity.getWorkType());
                newCand.setExamType(updUserEntity.getExamType());
                newCand.setExamMajor(updUserEntity.getExamMajor());
                newCand.setRegiMeth(updUserEntity.getRegiMeth());
                newCand.setRegiLoc(updUserEntity.getRegiLoc());

                //4.更新数据库
                candidateMapper.updateByPrimaryKeySelective(newCand);

                //检查review表中是否已有记录
                Review review = reviewMapper.selectType(new Review(candidate.getId(),6));
                if(review==null)
                    reviewMapper.insert(new Review(candidate.getId(),6));
                return OpResult.UPD_SUCCESS;
            }

            else if(type==2){ //身份是管理员
                System.out.println("管理员");
                Admin admin = adminMapper.selectById(id);
                if(admin==null)//账户不存在
                    return OpResult.LOGIN_EMPTY_ERROR;
                Admin newAdmin = new Admin();
                newAdmin.setId(admin.getId());
                newAdmin.setToken(admin.getToken());
                newAdmin.setExpiredDate(admin.getExpiredDate());
                newAdmin.setIdNum(updUserEntity.getIdNum());
                newAdmin.setName(updUserEntity.getName());
                newAdmin.setPhone(updUserEntity.getPhone());
                newAdmin.setMail(updUserEntity.getMail());
                if(updUserEntity.getPassword()==null || updUserEntity.getPassword()=="")
                    newAdmin.setPassword(admin.getPassword());
                else
                   newAdmin.setPassword(userInfoTools.CreateMD5Code(updUserEntity.getPassword()));
                newAdmin.setGender(updUserEntity.getGender());
                newAdmin.setJobNum(updUserEntity.getJobNum());
                newAdmin.setLocation(updUserEntity.getLocation());
                newAdmin.setEmployer(updUserEntity.getEmployer());
                newAdmin.setDocUrl(updUserEntity.getDocUrl());
                //4.更新数据库
                adminMapper.updateByPrimaryKeySelective(newAdmin);
                reviewMapper.insert(new Review(admin.getId(),8)); //8是管理员信息更改
                return OpResult.UPD_SUCCESS;
            }
            else{ //出错
                return OpResult.TOKEN_UNKNOW_ERROR;
            }
        }catch (Exception e) {
            return OpResult.UPD_ERROR;
        }
    }
}
