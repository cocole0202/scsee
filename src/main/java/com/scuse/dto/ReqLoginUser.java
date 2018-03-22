package com.scuse.dto;

import org.springframework.web.bind.annotation.RequestBody;

public class ReqLoginUser {
    String idNum; //手机号、邮箱或身份证号
    String phone;
    String mail;
    String password;

    public ReqLoginUser(){

    }

    public String getIdNum(){
        return idNum;
    }

    public String getPassword(){
        return password;
    }

    public String getPhone(){
        return phone;
    }

    public String getMail(){
        return mail;
    }

    public void setIdNum(String idNum){
        this.idNum = idNum;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

}
