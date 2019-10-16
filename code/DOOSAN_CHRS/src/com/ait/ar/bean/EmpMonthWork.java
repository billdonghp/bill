package com.ait.ar.bean;

public class EmpMonthWork {
    private String description;

    private int day;

    private int hours;

    private String empID;

    private int month;

    private int starHour;

    public EmpMonthWork() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getStarHour() {
        return starHour;
    }

    public void setStarHour(int starHour) {
        this.starHour = starHour;
    }

}
