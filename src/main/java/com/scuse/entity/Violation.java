package com.scuse.entity;

public class Violation {
    private Integer candId;

    private Integer tickId;

    private Integer roomId;

    private Integer degree;

    public Integer getCandId() {
        return candId;
    }

    public void setCandId(Integer candId) {
        this.candId = candId;
    }

    public Integer getTickId() {
        return tickId;
    }

    public void setTickId(Integer tickId) {
        this.tickId = tickId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }
}