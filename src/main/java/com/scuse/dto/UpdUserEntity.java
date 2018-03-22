package com.scuse.dto;

import java.util.Date;

/*
用于接收用户可以更改的信息
不包括id，token，epiredDate等信息
 */
public class UpdUserEntity {

    private String oldName;
    private String ethnic;
    private String poliStatus;
    private String accLoc;
    private String origin;
    private String certType;
    private String certNum;
    private Date birthday;
    private String education;
    private Integer zipCode;
    private String mailAddr;
    private String recipient;
    private String career;
    private String workAddr;
    private String workType;
    private String examType;
    private String examMajor;
    private String regiMeth;
    private String regiLoc;
    private String idNum;
    private String name;
    private String phone;
    private String mail;
    private String password;
    private Boolean gender;
    private String jobNum;
    private String location;
    private String employer;
    private String docUrl;

    public UpdUserEntity(){

    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum == null ? null : jobNum.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer == null ? null : employer.trim();
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl == null ? null : docUrl.trim();
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName == null ? null : oldName.trim();
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic == null ? null : ethnic.trim();
    }

    public String getPoliStatus() {
        return poliStatus;
    }

    public void setPoliStatus(String poliStatus) {
        this.poliStatus = poliStatus == null ? null : poliStatus.trim();
    }

    public String getAccLoc() {
        return accLoc;
    }

    public void setAccLoc(String accLoc) {
        this.accLoc = accLoc == null ? null : accLoc.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum == null ? null : certNum.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr == null ? null : mailAddr.trim();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career == null ? null : career.trim();
    }

    public String getWorkAddr() {
        return workAddr;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr == null ? null : workAddr.trim();
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType == null ? null : examType.trim();
    }

    public String getExamMajor() {
        return examMajor;
    }

    public void setExamMajor(String examMajor) {
        this.examMajor = examMajor == null ? null : examMajor.trim();
    }

    public String getRegiMeth() {
        return regiMeth;
    }

    public void setRegiMeth(String regiMeth) {
        this.regiMeth = regiMeth == null ? null : regiMeth.trim();
    }

    public String getRegiLoc() {
        return regiLoc;
    }

    public void setRegiLoc(String regiLoc) {
        this.regiLoc = regiLoc == null ? null : regiLoc.trim();
    }
}
