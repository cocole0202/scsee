package com.scuse.entity;

public class Ticket {
    private Integer id;

    private Integer candId;

    private Boolean valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCandId() {
        return candId;
    }

    public void setCandId(Integer candId) {
        this.candId = candId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}