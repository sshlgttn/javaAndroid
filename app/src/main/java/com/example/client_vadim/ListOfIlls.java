package com.example.client_vadim;

public class ListOfIlls {
    private Long id;

    private Integer num;

    private String department_name;

    private Integer day;
    private Integer month;
    private Integer year;

    private String patienName;

    private Integer isBenefit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPatienName() {
        return patienName;
    }

    public void setPatienName(String patienName) {
        this.patienName = patienName;
    }

    public Integer getIsBenefit() {
        return isBenefit;
    }

    public void setIsBenefit(Integer isBenefit) {
        this.isBenefit = isBenefit;
    }
}
