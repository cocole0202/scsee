package com.scuse.dto;

public class ReturnUserInfo {
    String idNum; //手机号、邮箱或身份证号
    String phone;
    String mail;
    String token;

    public ReturnUserInfo(){

    }

    public String getIdNum(){
        return idNum;
    }

    public String getPhone(){
        return phone;
    }

    public String getMail(){
        return mail;
    }

    public String getToken() { return token;}

    public void setIdNum(String idNum){
        this.idNum = idNum;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public void setToken(String token) { this.token = token; }

}
