package com.scuse.entity;

import java.util.Date;

public class Major {
    private Integer id;

    private String code;

    private String custCode;

    private String name;

    private Integer credit;

    private Boolean approve;

    private Date apprTime;

    private String apprCode;

    private String level;

    private String type;

    private String examMeth;

    private String codeType;

    private String nature;

    private Date startTime;

    private String startDoc;

    private Boolean unified;

    private String mainClg;

    private String subject;

    private Date endTime;

    private String endDoc;

    private String endInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode == null ? null : custCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public Date getApprTime() {
        return apprTime;
    }

    public void setApprTime(Date apprTime) {
        this.apprTime = apprTime;
    }

    public String getApprCode() {
        return apprCode;
    }

    public void setApprCode(String apprCode) {
        this.apprCode = apprCode == null ? null : apprCode.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getExamMeth() {
        return examMeth;
    }

    public void setExamMeth(String examMeth) {
        this.examMeth = examMeth == null ? null : examMeth.trim();
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartDoc() {
        return startDoc;
    }

    public void setStartDoc(String startDoc) {
        this.startDoc = startDoc == null ? null : startDoc.trim();
    }

    public Boolean getUnified() {
        return unified;
    }

    public void setUnified(Boolean unified) {
        this.unified = unified;
    }

    public String getMainClg() {
        return mainClg;
    }

    public void setMainClg(String mainClg) {
        this.mainClg = mainClg == null ? null : mainClg.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndDoc() {
        return endDoc;
    }

    public void setEndDoc(String endDoc) {
        this.endDoc = endDoc == null ? null : endDoc.trim();
    }

    public String getEndInfo() {
        return endInfo;
    }

    public void setEndInfo(String endInfo) {
        this.endInfo = endInfo == null ? null : endInfo.trim();
    }
}