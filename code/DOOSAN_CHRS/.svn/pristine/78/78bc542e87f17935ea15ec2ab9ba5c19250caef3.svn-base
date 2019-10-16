package com.ait.ar.bean;

import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarDay {

    private GregorianCalendar calendarDay = new GregorianCalendar();

    private int shiftNo = 0;        //班次号

    private int dayTypeId = 1;      //日历类型:1平时,2周末,3节假日
    
    private int calendarType = 0;   //日历来源:0为取日历,1为取排班

    private int overTypeId = 1;     //加班类型:1平时,2周休,3节假日

    private int workDayFlag = 1;    //应工作标志:0休息,1工作
    
    private int lockFlag = 0 ; 		// 考勤锁标示: 0 解锁, 1 锁
    
    private String arDateStr = "" ; // 考勤日期(YYYY/MM/DD)

    public int getDayTypeId() {
        return dayTypeId;
    }

    public void setDayTypeId(int dayTypeId) {
        this.dayTypeId = dayTypeId;
    }

    public int getOverTypeId() {
        return overTypeId;
    }

    public void setOverTypeId(int overTypeId) {
        this.overTypeId = overTypeId;
    }

    public int getShiftNo() {
        return shiftNo;
    }

    public void setShiftNo(int shiftNo) {
        this.shiftNo = shiftNo;
    }

    public int getWorkDayFlag() {
        return workDayFlag;
    }

    public void setWorkDayFlag(int workDayFlag) {
        this.workDayFlag = workDayFlag;
    }

    public GregorianCalendar getCalendarDay() {
        return calendarDay;
    }

    public void setCalendarDay(GregorianCalendar calendarDay) {
        this.calendarDay = calendarDay;
    }

    public void setCalendarDay(Date calendarDay) {
        this.calendarDay.setTime(calendarDay);
    }

    public int getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(int calendarType) {
        this.calendarType = calendarType;
    }

	public int getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(int lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getArDateStr() {
		return arDateStr;
	}

	public void setArDateStr(String arDateStr) {
		this.arDateStr = arDateStr;
	}

}
