package com.scuse.entity;

public class Course {
    private Integer id;

    private String name;

    private String code;

    private String engName;

    private Integer credit;

    private String nature;

    private String examMeth;

    private String paperMeth;

    private String outline;

    private Integer bookId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName == null ? null : engName.trim();
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
    }

    public String getExamMeth() {
        return examMeth;
    }

    public void setExamMeth(String examMeth) {
        this.examMeth = examMeth == null ? null : examMeth.trim();
    }

    public String getPaperMeth() {
        return paperMeth;
    }

    public void setPaperMeth(String paperMeth) {
        this.paperMeth = paperMeth == null ? null : paperMeth.trim();
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline == null ? null : outline.trim();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}