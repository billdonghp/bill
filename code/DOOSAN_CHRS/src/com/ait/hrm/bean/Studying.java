/*
 * @(#)Studying.java 1.0 2006-12-28 上午01:24:31
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.bean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-28 上午01:24:31
 * @version 1.0
 * 
 */
public class Studying {

    private Integer studyNo;

    private String transNo;// 人事令号

    private String transCode;// 发令类型

    private String transCodeName;

    private String enTransCodeName;

    private String korTransCodeName;

    private String empID;

    private String chineseName;

    private String pinyin;

    private String enName;

    private String korName;

    private String deptID;

    private String department;

    private String enDept;

    private String korDept;

    private String postGrade;// 职务等级

    private String enPostGrade;

    private String korPostGrade;

    private String postGradeCode;

    private Double postCoef;// 职务系数

    private String post;// 职务

    private String enPost;

    private String korPost;

    private String postCode;

    private String duty;// 职责

    private String dutyCode;

    private String position;// 职位

    private String enPosition;

    private String korPosition;

    private String positionCode;

    private String postGroup;// 职别

    private String postGroupCode;

    private String postColumnId;// 职列

    private String postColumn;

    private String studyTypeCode;// 留学名

    private String studyType;

    private String enStudyType;

    private String korStudyType;

    private String researchTypeCode;// 研修类型

    private String researchType;

    private String enResearchType;

    private String korResearchType;

    private String institutionName;// 机关

    private String subjectCode;// 专业

    private String subject;//

    private String enSubject;

    private String korSubject;

    private String degreeCode;// 学历

    private String degree;

    private String enDegree;

    private String korDegree;

    private String countryCode;// 国家

    private String country;

    private String enCountry;

    private String korCountry;

    private String studyContents;

    private Double expense;// 费用

    private String startDate;

    private String endDate;

    private String period;// 义务服期

    private String createDate;

    private String createdBy;

    private String updateDate;

    private String updatedBy;

    private Integer orderNo;

    private Integer activity;

    public Studying() {
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Double getPostCoef() {
        return postCoef;
    }

    public void setPostCoef(Double postCoef) {
        this.postCoef = postCoef;
    }

    public String getPostColumn() {
        return postColumn;
    }

    public void setPostColumn(String postColumn) {
        this.postColumn = postColumn;
    }

    public String getPostColumnId() {
        return postColumnId;
    }

    public void setPostColumnId(String postColumnId) {
        this.postColumnId = postColumnId;
    }

    public String getPostGrade() {
        return postGrade;
    }

    public void setPostGrade(String postGrade) {
        this.postGrade = postGrade;
    }

    public String getPostGradeCode() {
        return postGradeCode;
    }

    public void setPostGradeCode(String postGradeCode) {
        this.postGradeCode = postGradeCode;
    }

    public String getPostGroup() {
        return postGroup;
    }

    public void setPostGroup(String postGroup) {
        this.postGroup = postGroup;
    }

    public String getPostGroupCode() {
        return postGroupCode;
    }

    public void setPostGroupCode(String postGroupCode) {
        this.postGroupCode = postGroupCode;
    }

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

    public String getResearchTypeCode() {
        return researchTypeCode;
    }

    public void setResearchTypeCode(String researchTypeCode) {
        this.researchTypeCode = researchTypeCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStudyContents() {
        return studyContents;
    }

    public void setStudyContents(String studyContents) {
        this.studyContents = studyContents;
    }

    public Integer getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(Integer studyNo) {
        this.studyNo = studyNo;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getStudyTypeCode() {
        return studyTypeCode;
    }

    public void setStudyTypeCode(String studyTypeCode) {
        this.studyTypeCode = studyTypeCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransCodeName() {
        return transCodeName;
    }

    public void setTransCodeName(String transCodeName) {
        this.transCodeName = transCodeName;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

	public String getEnResearchType() {
		return enResearchType;
	}

	public void setEnResearchType(String enResearchType) {
		this.enResearchType = enResearchType;
	}

	public String getKorResearchType() {
		return korResearchType;
	}

	public void setKorResearchType(String korResearchType) {
		this.korResearchType = korResearchType;
	}

	public String getEnStudyType() {
		return enStudyType;
	}

	public void setEnStudyType(String enStudyType) {
		this.enStudyType = enStudyType;
	}

	public String getKorStudyType() {
		return korStudyType;
	}

	public void setKorStudyType(String korStudyType) {
		this.korStudyType = korStudyType;
	}

	public String getEnCountry() {
		return enCountry;
	}

	public void setEnCountry(String enCountry) {
		this.enCountry = enCountry;
	}

	public String getKorCountry() {
		return korCountry;
	}

	public void setKorCountry(String korCountry) {
		this.korCountry = korCountry;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getKorName() {
		return korName;
	}

	public void setKorName(String korName) {
		this.korName = korName;
	}

	public String getEnDept() {
		return enDept;
	}

	public void setEnDept(String enDept) {
		this.enDept = enDept;
	}

	public String getKorDept() {
		return korDept;
	}

	public void setKorDept(String korDept) {
		this.korDept = korDept;
	}

	public String getEnPost() {
		return enPost;
	}

	public void setEnPost(String enPost) {
		this.enPost = enPost;
	}

	public String getKorPost() {
		return korPost;
	}

	public void setKorPost(String korPost) {
		this.korPost = korPost;
	}

	public String getEnPosition() {
		return enPosition;
	}

	public void setEnPosition(String enPosition) {
		this.enPosition = enPosition;
	}

	public String getKorPosition() {
		return korPosition;
	}

	public void setKorPosition(String korPosition) {
		this.korPosition = korPosition;
	}

	public String getEnSubject() {
		return enSubject;
	}

	public void setEnSubject(String enSubject) {
		this.enSubject = enSubject;
	}

	public String getEnDegree() {
		return enDegree;
	}

	public void setEnDegree(String enDegree) {
		this.enDegree = enDegree;
	}

	public String getKorDegree() {
		return korDegree;
	}

	public void setKorDegree(String korDegree) {
		this.korDegree = korDegree;
	}

	public String getKorSubject() {
		return korSubject;
	}

	public void setKorSubject(String korSubject) {
		this.korSubject = korSubject;
	}

	public String getEnPostGrade() {
		return enPostGrade;
	}

	public void setEnPostGrade(String enPostGrade) {
		this.enPostGrade = enPostGrade;
	}

	public String getKorPostGrade() {
		return korPostGrade;
	}

	public void setKorPostGrade(String korPostGrade) {
		this.korPostGrade = korPostGrade;
	}

	public String getEnTransCodeName() {
		return enTransCodeName;
	}

	public void setEnTransCodeName(String enTransCodeName) {
		this.enTransCodeName = enTransCodeName;
	}

	public String getKorTransCodeName() {
		return korTransCodeName;
	}

	public void setKorTransCodeName(String korTransCodeName) {
		this.korTransCodeName = korTransCodeName;
	}
    
}
