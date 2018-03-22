package com.scuse.dto;

public class ReqReviewEntity {
    private Integer id;

    private Integer type;

    public ReqReviewEntity(){

    }

    public ReqReviewEntity(int id, int type){
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
