/*
 * @(#)InternalExperience.java 1.0 2006-12-28 上午01:19:42
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.bean;
/**
 * 
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-28 上午01:19:42
 * @version 1.0
 *
 */
public class InternalExperience {

    private Integer internalExperienceNo;

    private String transNo;// 人事令号

    private String transCode;// 发令类型

    private String transCodeName;
    
    private String transCodeEnName;
    
    private String transCodeKorName;

    private String empID;
    
    private String chineseName;
    
    private String pinyin;
    
    private String enName;
    
    private String korName;

    private String deptID;

    private String department;
    
    private String deptEnName;
    
    private String deptKorName;
    
    private String postGroupName;
    
    private String postGradename;
       
    private String postGradeEnName;
       
    private String postGradeKorName;
       
    private String postGradeLevelName;
    
    private String postGradeLevelEnName;
    
    private String postGradeLevelKorName;

    private String postGrade;// 职务等级

    private String postGradeCode;

    private Double postCoef;// 职务系数
  
    private String post;// 职务
    
    private String postEnName;
    
    private String postKorName;

    private String postCode;

    private String duty;// 职责

    private String dutyCode;

    private String position;// 职位
    
    private String positionEnName;
    
    private String positionKorName;

    private String positionCode;

    private String postGroup;// 职别

    private String postGroupEname;

    private String postGroupKorName;

    private String postGroupCode;

    private String postColumnId;// 职列

    private String postColumn;

    private String startDate;// 开始时间 生效时间
    
    private String endDate;

    private String joinTypeCode;// 入社类型

    private String joinType;

    private String joinDate;// 入社日期

    private String reasons;
    
    //  工作状态
    private String status;

    private String statusCode;
    
    private String createdBy;

    private String createDate;

    private String updatedBy;

    private String updateDate;
    
    private Integer activity;
    
    public InternalExperience(){}
    
    
    public String getChineseName() {
        return chineseName;
    }


    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }


    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
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

    public Integer getInternalExperienceNo() {
        return internalExperienceNo;
    }

    public void setInternalExperienceNo(Integer internalExperienceNo) {
        this.internalExperienceNo = internalExperienceNo;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getJoinTypeCode() {
        return joinTypeCode;
    }

    public void setJoinTypeCode(String joinTypeCode) {
        this.joinTypeCode = joinTypeCode;
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

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatusCode() {
        return statusCode;
    }


    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }


	
	public String getPostGradeLevelName() {
		return postGradeLevelName;
	}


	
	public void setPostGradeLevelName(String postGradeLevelName) {
		this.postGradeLevelName = postGradeLevelName;
	}


	
	public String getPostGradename() {
		return postGradename;
	}


	
	public void setPostGradename(String postGradename) {
		this.postGradename = postGradename;
	}


	
	public String getPostGroupName() {
		return postGroupName;
	}


	
	public void setPostGroupName(String postGroupName) {
		this.postGroupName = postGroupName;
	}


	public String getTransCodeEnName() {
		return transCodeEnName;
	}


	public void setTransCodeEnName(String transCodeEnName) {
		this.transCodeEnName = transCodeEnName;
	}


	public String getTransCodeKorName() {
		return transCodeKorName;
	}


	public void setTransCodeKorName(String transCodeKorName) {
		this.transCodeKorName = transCodeKorName;
	}


	public String getDeptEnName() {
		return deptEnName;
	}


	public void setDeptEnName(String deptEnName) {
		this.deptEnName = deptEnName;
	}


	public String getDeptKorName() {
		return deptKorName;
	}


	public void setDeptKorName(String deptKorName) {
		this.deptKorName = deptKorName;
	}


	public String getPostGradeEnName() {
		return postGradeEnName;
	}


	public void setPostGradeEnName(String postGradeEnName) {
		this.postGradeEnName = postGradeEnName;
	}


	public String getPostGradeKorName() {
		return postGradeKorName;
	}


	public void setPostGradeKorName(String postGradeKorName) {
		this.postGradeKorName = postGradeKorName;
	}


	public String getPostGradeLevelEnName() {
		return postGradeLevelEnName;
	}


	public void setPostGradeLevelEnName(String postGradeLevelEnName) {
		this.postGradeLevelEnName = postGradeLevelEnName;
	}


	public String getPostGradeLevelKorName() {
		return postGradeLevelKorName;
	}


	public void setPostGradeLevelKorName(String postGradeLevelKorName) {
		this.postGradeLevelKorName = postGradeLevelKorName;
	}


	public String getPostEnName() {
		return postEnName;
	}


	public void setPostEnName(String postEnName) {
		this.postEnName = postEnName;
	}


	public String getPostKorName() {
		return postKorName;
	}


	public void setPostKorName(String postKorName) {
		this.postKorName = postKorName;
	}


	public String getPositionEnName() {
		return positionEnName;
	}


	public void setPositionEnName(String positionEnName) {
		this.positionEnName = positionEnName;
	}


	public String getPositionKorName() {
		return positionKorName;
	}


	public void setPositionKorName(String positionKorName) {
		this.positionKorName = positionKorName;
	}


	public String getPostGroupEname() {
		return postGroupEname;
	}


	public void setPostGroupEname(String postGroupEname) {
		this.postGroupEname = postGroupEname;
	}


	public String getPostGroupKorName() {
		return postGroupKorName;
	}


	public void setPostGroupKorName(String postGroupKorName) {
		this.postGroupKorName = postGroupKorName;
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


    
    

}
