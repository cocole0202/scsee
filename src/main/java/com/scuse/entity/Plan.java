package com.scuse.entity;

import java.util.Date;

public class Plan {
    private Integer id;

    private Integer mjrId;

    private Integer crsId;

    private Integer batId;

    private Date examTime;

    private Integer hours;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMjrId() {
        return mjrId;
    }

    public void setMjrId(Integer mjrId) {
        this.mjrId = mjrId;
    }

    public Integer getCrsId() {
        return crsId;
    }

    public void setCrsId(Integer crsId) {
        this.crsId = crsId;
    }

    public Integer getBatId() {
        return batId;
    }

    public void setBatId(Integer batId) {
        this.batId = batId;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}