package com.scuse.entity;

public class Room {
    private Integer id;

    private Integer siteId;

    private String addr;

    private Integer candLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
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