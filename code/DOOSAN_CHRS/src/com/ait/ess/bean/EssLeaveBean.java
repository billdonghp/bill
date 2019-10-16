package com.ait.ess.bean;

import java.util.ArrayList;
import java.util.List;

public class EssLeaveBean {

	private Integer leaveNo; // 休假申请序号

	private String empId; // 休假员工号

	private String chineseName; // 休假人姓名

	private String chinesePinYin;

	private String enPositon;

	private String deptEnName;

	private String enPost;

	private String leaveTypeEnName;

	private String koreanname;

	private String korPositon;

	private String deptKorName;

	private String KorPost;

	private String leaveTypeKorName;

	private String deptId; // 部门名称

	private String deptName; // 休假人部门名称

	private String fourthDeptId; //部门编号

	private String fourthDeptName; //部门名

	private String position;//职位

	private String post;//职务

	private String postGradeCode; //职级

	private String leaveFromDate; // 休假开始日期

	private String leaveToDate; // 休假结束日期

	private String leaveFromTime; // 休假开始时间

	private String leaveToTime; // 休假结束时间

	private Integer activity; // 人事决裁结果

	private Integer opFlag = -1; // 可操作状态 -1 不可操作; 0 可通过/否决; 1 可通过; 2 可否决

	private String createDate; // 申请日期

	private String leaveTypeCode; // 休假类型代码

	private String leaveTypeName; // 休假类型名称

	private String ApllyTypeName; // 申请类型名称

	private String leaveReason; // 休假原因

	private ArrayList AffirmorList; // 决裁者列表

	private Integer affirmorCount; // 决裁者数量

	private String remark;

	private Double annual; // 年假天数

	private String leaveThisMonth; // 本月可用倒休天数

	private String leaveNextMonth; // 下月可用倒休天数

	private String createdBy;

	private int applyGroupSeq;

	private String affirmData; // 决裁列表

	private String ck; // 选择判断标示

	private Integer affirm_flag; // 当前决裁者的决裁状况

	private Integer next_flag; // 下一个决裁者的决裁状况

	private Integer up_flag; // 上一个决裁者的决裁状况

	private Integer maxLevel_flag; // 当前决裁者是否为最高级别表示

	private Integer affirmLevel; // 当前决裁者的决裁级别

	private String person_id;

	private String applyLeaveType;
	
	private Integer del_flag=0;//1 可操作，0不可操作
	
	private Integer max_affirm_flag;

	private List vactionList = new ArrayList();

	private Integer fileUploadFlag ; //文件上传标示
	
	private String business ; //担当业务
	
	private String agentEmpId ; //代理人职号
	
	private String agentName ; //代理人姓名
	
	private String agentPosition ; //代理人职位
	
	private String agentOfficePhone ; //代理人办公室电话
	
	private String agentCellphone ; //代理人手机
	
	private String otherBusiness ; //其他事项
	
	private String contactMode ; //联系方式	

	private String sts ; //是否正常出入大门
	
	private String cpny ; //从哪个大门出

	private String crechineseName ;
	
	public String getCpny() {
		return cpny;
	}

	public void setCpny(String cpny) {
		this.cpny = cpny;
	}

	public String getCrechineseName() {
		return crechineseName;
	}

	public void setCrechineseName(String crechineseName) {
		this.crechineseName = crechineseName;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public Integer getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(Integer del_flag) {
		this.del_flag = del_flag;
	}

	public String getApplyLeaveType() {
		return applyLeaveType;
	}

	public void setApplyLeaveType(String applyLeaveType) {
		this.applyLeaveType = applyLeaveType;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ArrayList getAffirmorList() {
		return AffirmorList;
	}

	public void setAffirmorList(ArrayList affirmorList) {
		AffirmorList = affirmorList;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getLeaveToTime() {
		return leaveToTime;
	}

	public void setLeaveToTime(String leaveToTime) {
		this.leaveToTime = leaveToTime;
	}

	public String getLeaveFromTime() {
		return leaveFromTime;
	}

	public void setLeaveFromTime(String leaveFromTime) {
		this.leaveFromTime = leaveFromTime;
	}

	public Integer getLeaveNo() {
		return leaveNo;
	}

	public void setLeaveNo(Integer leaveNo) {
		this.leaveNo = leaveNo;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}

	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(Integer opFlag) {
		this.opFlag = opFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getAffirmorCount() {
		return affirmorCount;
	}

	public void setAffirmorCount(Integer affirmorCount) {
		this.affirmorCount = affirmorCount;
	}

	public Double getAnnual() {
		return annual;
	}

	public void setAnnual(Double annual) {
		this.annual = annual;
	}

	public String getLeaveFromDate() {
		return leaveFromDate;
	}

	public void setLeaveFromDate(String leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}

	public String getLeaveToDate() {
		return leaveToDate;
	}

	public void setLeaveToDate(String leaveToDate) {
		this.leaveToDate = leaveToDate;
	}

	/**
	 * @return the positon
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param positon the positon to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the post
	 */
	public String getPost() {
		return post;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * @return the leaveNextMonth
	 */
	public String getLeaveNextMonth() {
		return leaveNextMonth;
	}

	/**
	 * @param leaveNextMonth the leaveNextMonth to set
	 */
	public void setLeaveNextMonth(String leaveNextMonth) {
		this.leaveNextMonth = leaveNextMonth;
	}

	/**
	 * @return the leaveThisMonth
	 */
	public String getLeaveThisMonth() {
		return leaveThisMonth;
	}

	/**
	 * @param leaveThisMonth the leaveThisMonth to set
	 */
	public void setLeaveThisMonth(String leaveThisMonth) {
		this.leaveThisMonth = leaveThisMonth;
	}

	public String getApllyTypeName() {
		return ApllyTypeName;
	}

	public void setApllyTypeName(String apllyTypeName) {
		ApllyTypeName = apllyTypeName;
	}

	public String getChinesePinYin() {
		return chinesePinYin;
	}

	public void setChinesePinYin(String chinesePinYin) {
		this.chinesePinYin = chinesePinYin;
	}

	public String getEnPositon() {
		return enPositon;
	}

	public void setEnPositon(String enPositon) {
		this.enPositon = enPositon;
	}

	public String getEnPost() {
		return enPost;
	}

	public void setEnPost(String enPost) {
		this.enPost = enPost;
	}

	public String getLeaveTypeEnName() {
		return leaveTypeEnName;
	}

	public void setLeaveTypeEnName(String leaveTypeEnName) {
		this.leaveTypeEnName = leaveTypeEnName;
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

	public String getKoreanname() {
		return koreanname;
	}

	public void setKoreanname(String koreanname) {
		this.koreanname = koreanname;
	}

	public String getKorPositon() {
		return korPositon;
	}

	public void setKorPositon(String korPositon) {
		this.korPositon = korPositon;
	}

	public String getKorPost() {
		return KorPost;
	}

	public void setKorPost(String korPost) {
		KorPost = korPost;
	}

	public String getLeaveTypeKorName() {
		return leaveTypeKorName;
	}

	public void setLeaveTypeKorName(String leaveTypeKorName) {
		this.leaveTypeKorName = leaveTypeKorName;
	}

	public int getApplyGroupSeq() {
		return applyGroupSeq;
	}

	public void setApplyGroupSeq(int applyGroupSeq) {
		this.applyGroupSeq = applyGroupSeq;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCk() {
		return ck;
	}

	public void setCk(String ck) {
		this.ck = ck;
	}

	public Integer getAffirm_flag() {
		return affirm_flag;
	}

	public void setAffirm_flag(Integer affirm_flag) {
		this.affirm_flag = affirm_flag;
	}

	public Integer getNext_flag() {
		return next_flag;
	}

	public void setNext_flag(Integer next_flag) {
		this.next_flag = next_flag;
	}

	public Integer getAffirmLevel() {
		return affirmLevel;
	}

	public void setAffirmLevel(Integer affirmLevel) {
		this.affirmLevel = affirmLevel;
	}

	public String getFourthDeptId() {
		return fourthDeptId;
	}

	public void setFourthDeptId(String fourthDeptId) {
		this.fourthDeptId = fourthDeptId;
	}

	public String getFourthDeptName() {
		return fourthDeptName;
	}

	public void setFourthDeptName(String fourthDeptName) {
		this.fourthDeptName = fourthDeptName;
	}

	public String getPostGradeCode() {
		return postGradeCode;
	}

	public void setPostGradeCode(String postGradeCode) {
		this.postGradeCode = postGradeCode;
	}

	public Integer getUp_flag() {
		return up_flag;
	}

	public void setUp_flag(Integer up_flag) {
		this.up_flag = up_flag;
	}

	public String getAffirmData() {
		return affirmData;
	}

	public void setAffirmData(String affirmData) {
		this.affirmData = affirmData;
	}

	public Integer getMaxLevel_flag() {
		return maxLevel_flag;
	}

	public void setMaxLevel_flag(Integer maxLevel_flag) {
		this.maxLevel_flag = maxLevel_flag;
	}

	public List getVactionList() {
		return vactionList;
	}

	public void setVactionList(List vactionList) {
		this.vactionList = vactionList;
	}

	public int getMax_affirm_flag() {
		return max_affirm_flag;
	}

	public void setMax_affirm_flag(Integer max_affirm_flag) {
		if(max_affirm_flag == null || max_affirm_flag.equals("")){
			max_affirm_flag = 0;
		}
		this.max_affirm_flag = max_affirm_flag;
	}

	public Integer getFileUploadFlag() {
		return fileUploadFlag;
	}

	public void setFileUploadFlag(Integer fileUploadFlag) {
		this.fileUploadFlag = fileUploadFlag;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getAgentEmpId() {
		return agentEmpId;
	}

	public void setAgentEmpId(String agentEmpId) {
		this.agentEmpId = agentEmpId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentPosition() {
		return agentPosition;
	}

	public void setAgentPosition(String agentPosition) {
		this.agentPosition = agentPosition;
	}

	public String getAgentOfficePhone() {
		return agentOfficePhone;
	}

	public void setAgentOfficePhone(String agentOfficePhone) {
		this.agentOfficePhone = agentOfficePhone;
	}

	public String getAgentCellphone() {
		return agentCellphone;
	}

	public void setAgentCellphone(String agentCellphone) {
		this.agentCellphone = agentCellphone;
	}

	public String getOtherBusiness() {
		return otherBusiness;
	}

	public void setOtherBusiness(String otherBusiness) {
		this.otherBusiness = otherBusiness;
	}

	public String getContactMode() {
		return contactMode;
	}

	public void setContactMode(String contactMode) {
		this.contactMode = contactMode;
	}

	



}
