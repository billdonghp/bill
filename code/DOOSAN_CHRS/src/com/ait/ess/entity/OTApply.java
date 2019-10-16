package com.ait.ess.entity;

import java.util.List;

/*
 * modify by lvhongbin at 2005-11-07
 * */

public class OTApply {
    private String otDate = null;

    private String otApplyDate = null;

    private String otStartTime = null;

    private String otEndTime = null;

    private String otDeductTime = null;

    private String anotherDayCode = null;

    private String otWorkContent = null;

    private String otApplySEQ = null;

    private double otLength = 0;

    private String empID = null;

    private String chineseName = null;

    private String postName = null;

    private int activity = 0;

    private String otType = null;

    private String otTypeCode = null;

    private String updatedBy = null;

    private String updateDate = null;

    private String createdBy = null;

    private String createDate = null;

    private int otNextDays = 0;
    
    private String menuCode = null;

    // private String format = "yyyy-MM-dd HH:mm:ss.SSS";

    private List affirmStatusList = null;

    public OTApply() {
        // constructor
    }

    // ot Date
    public void setOtDate(String a_otDate) {
        otDate = a_otDate;
    }

    public String getOtDate() {
        return otDate;
    }

    // ot Apply Date
    public void setOtApplyDate(String a_otApplyDate) {
        otApplyDate = a_otApplyDate;
    }

    public String getOtApplyDate() {
        return otApplyDate;
    }

    // ot Start Time
    public void setOtStartTime(String a_otStartTime) {
        otStartTime = a_otStartTime;
    }

    public String getOtStartTime() {
        return otStartTime;
    }

    // ot End Time
    public void setOtEndTime(String a_otEndTime) {
        otEndTime = a_otEndTime;
    }

    public String getOtEndTime() {
        return otEndTime;
    }

    // otDeductTime
    public String getOtDeductTime() {
        return otDeductTime;
    }

    public void setOtDeductTime(String a_otDeductTime) {
        otDeductTime = a_otDeductTime;
    }

    // another Day Code
    public String getAnotherDayCode() {
        return anotherDayCode;
    }

    public void setAnotherDayCode(String a_anotherDayCode) {
        anotherDayCode = a_anotherDayCode;
    }

    // ot Work Content
    public String getOtWorkContent() {
        return otWorkContent;
    }

    public void setOtWorkContent(String a_otWorkContent) {
        otWorkContent = a_otWorkContent;
    }

    // otApplySEQ
    public String getOtApplySEQ() {
        return otApplySEQ;
    }

    public void setOtApplySEQ(String a_otApplySEQ) {
        otApplySEQ = a_otApplySEQ;
    }

    // empID
    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String a_empID) {
        empID = a_empID;
    }

    // chineseName
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String a_chineseName) {
        chineseName = a_chineseName;
    }

    // postName
    public String getPostName() {
        return postName;
    }

    public void setPostName(String a_postName) {
        postName = a_postName;
    }

    // activity
    public int getActivity() {
        return activity;
    }

    public void setActivity(int a_activity) {
        activity = a_activity;
    }

    // otType
    public String getOtType() {
        return otType;
    }

    public void setOtType(String a_otType) {
        otType = a_otType;
    }

    // otTypeCode
    public String getOtTypeCode() {
        return otTypeCode;
    }

    public void setOtTypeCode(String a_otTypeCode) {
        otTypeCode = a_otTypeCode;
    }

    // updatedBy
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String a_updatedBy) {
        updatedBy = a_updatedBy;
    }

    // updateDate
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String a_updateDate) {
        updateDate = a_updateDate;
    }

    // createdBy
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String a_createdBy) {
        createdBy = a_createdBy;
    }

    // createDate
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String a_createDate) {
        createDate = a_createDate;
    }

    // otNextDays
    public int getOtNextDays() {
        return otNextDays;
    }

    public void setOtNextDays(int a_otNextDays) {
        otNextDays = a_otNextDays;
    }

    // affirmStatusList
    public void setAffirmStatusList(List list) {
        affirmStatusList = list;
    }

    public List getAffirmStatusList() {
        return affirmStatusList;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public double getOtLength() {
        return otLength;
    }

    public void setOtLength(double otLength) {
        this.otLength = otLength;
    }
}
