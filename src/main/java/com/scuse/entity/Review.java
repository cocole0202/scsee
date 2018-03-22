package com.scuse.entity;

public class Review {
    private Integer id;

    private Integer type;

    public Review(){

    }

    public Review(int id, int type){
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}