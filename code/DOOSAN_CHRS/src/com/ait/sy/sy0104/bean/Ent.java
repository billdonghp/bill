package com.ait.sy.sy0104.bean;

public class Ent {
	public Ent() {
	}

	public int loginNo = 0;
	public String empID;
	public String userID;
	public String passWord;
	public String loginDeptID;
	public String screenGrantno;
	public String createDate;
	public String creatorID;
	public String modifyDate;
	public String modifierID;
	public String grantEnName;
	public String departMentEnName;
	public String chinisenamePingYing;
	public int activity = 0;
	public int orderNo = 0;
	private String chineseName;
	private String grantName;
	private String departMent;
	private String grantNo;
	private String duty;
	private String cpnyId;
	private String cpnyname;
	//担当确认
	private String person_id;
	private String comfirm_cpnyid;
	private String affirm_type_id;
	private String code_name;

	
	
	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String codeName) {
		code_name = codeName;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String personId) {
		person_id = personId;
	}

	public String getComfirm_cpnyid() {
		return comfirm_cpnyid;
	}

	public void setComfirm_cpnyid(String comfirmCpnyid) {
		comfirm_cpnyid = comfirmCpnyid;
	}

	public String getAffirm_type_id() {
		return affirm_type_id;
	}

	public void setAffirm_type_id(String affirmTypeId) {
		affirm_type_id = affirmTypeId;
	}

	public String getCpnyId() {
		return cpnyId;
	}

	public void setCpnyId(String cpnyId) {
		this.cpnyId = cpnyId;
	}

	public String getCpnyname() {
		return cpnyname;
	}

	public void setCpnyname(String cpnyname) {
		this.cpnyname = cpnyname;
	}

	public String getLoginDeptID() {
		return loginDeptID;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public int getLoginNo() {
		return this.loginNo;
	}

	public void setLoginNo(int loginNo) {
		this.loginNo = loginNo;
	}

	public String getEmpID() {
		return this.editNull(empID);
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getLoginDeptid() {
		return this.editNull(loginDeptID);
	}

	public void setLoginDeptID(String loginDeptID) {
		this.loginDeptID = loginDeptID;
	}

	public String getScreenGrantno() {
		return this.editNull(screenGrantno);
	}

	public void setScreenGrantno(String screenGrantno) {
		this.screenGrantno = screenGrantno;
	}

	public String getUserID() {
		return this.editNull(userID);
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassWord() {
		return this.editNull(passWord);
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCreateDate() {
		return this.editNull(createDate);
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatorID() {
		return this.editNull(creatorID);
	}

	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}

	public String getModifyDate() {
		return this.editNull(modifyDate);
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifierID() {
		return this.editNull(modifierID);
	}

	public void setModifierID(String modifierID) {
		this.modifierID = modifierID;
	}

	public int getActivity() {
		return this.activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public int getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String editNull(String s) {
		if (s == null || s.equals("")) {
			return "";
		} else {
			return s;
		}
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getGrantName() {
		return grantName;
	}

	public void setGrantName(String grantName) {
		this.grantName = grantName;
	}

	public String getDepartMent() {
		return departMent;
	}

	public void setDepartMent(String departMent) {
		this.departMent = departMent;
	}

	public String getGrantNo() {
		return grantNo;
	}

	public void setGrantNo(String grantNo) {
		this.grantNo = grantNo;
	}

	public String getDepartMentEnName() {
		return departMentEnName;
	}

	public void setDepartMentEnName(String departMentEnName) {
		this.departMentEnName = departMentEnName;
	}

	public String getGrantEnName() {
		return grantEnName;
	}

	public void setGrantEnName(String grantEnName) {
		this.grantEnName = grantEnName;
	}

	public String getChinisenamePingYing() {
		return chinisenamePingYing;
	}

	public void setChinisenamePingYing(String chinisenamePingYing) {
		this.chinisenamePingYing = chinisenamePingYing;
	}

}
