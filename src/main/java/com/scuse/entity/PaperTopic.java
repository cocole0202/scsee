package com.scuse.entity;

public class PaperTopic {
    private Integer paperId;

    private Integer typeId;

    private Integer number;

    private Integer scores;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }
}