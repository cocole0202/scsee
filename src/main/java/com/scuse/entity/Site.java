package com.scuse.entity;

public class Site {
    private Integer id;

    private String addr;

    private Integer candLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Integer getCandLimit() {
        return candLimit;
    }

    public void setCandLimit(Integer candLimit) {
        this.candLimit = candLimit;
    }
}