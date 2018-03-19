package com.scuse.entity;

import java.util.Date;

public class Purchase {
    private Integer bookId;

    private Integer value;

    private Date purTime;

    private Integer total;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getPurTime() {
        return purTime;
    }

    public void setPurTime(Date purTime) {
        this.purTime = purTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}