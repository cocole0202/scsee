package com.scuse.entity;

import java.util.Date;

public class Batch {
    private Integer id;

    private Date examTime;

    private Integer candLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public Integer getCandLimit() {
        return candLimit;
    }

    public void setCandLimit(Integer candLimit) {
        this.candLimit = candLimit;
    }
}