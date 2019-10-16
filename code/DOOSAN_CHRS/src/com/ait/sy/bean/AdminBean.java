package com.ait.sy.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2034432429142674566L;

	public static final String DEFAULT_LANGUAGE_PREFERENCE = "en";

	public static final String DEFAULT_COUNTRY_PREFERENCE = "US";

	public static final Locale DEFAULT_Locale = new Locale("en", "US");

	private String adminNo ;
	
	private String adminID;
	
	//添加 ad验证登录字段
	
	private String ad_User_Id;

	

	public String getAd_User_Id() {
		return ad_User_Id;
	}

	public void setAd_User_Id(String adUserId) {
		ad_User_Id = adUserId;
	}

	private int adminLevel;

	private String username;

	private String password;

	private String chineseName;
	
	private String pinyin;
	
	private String englishName;
	
	private String koreanname;

	private String loginDeptID;

	private String department;
	
	private String englishdept;
	
	private String kordept;

	private String screenGrantNo;

	private String createDate;

	private String createdBy;

	private String updateDate;

	private String updatedBy;

	private int activity;

	private int orderNo;

	private String deptID;

	private String companyName;

	private String hrTag;

	private String languagePreference = DEFAULT_LANGUAGE_PREFERENCE;

	private String countryPreference = DEFAULT_COUNTRY_PREFERENCE;
	
	private String cpnyId;
	
	private String cpnyName;
	
	private String personId;
	
	private String empDiffCode;
	
	private List CodeType = new ArrayList();	

	public AdminBean() {
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminID() {
		return this.adminID;
	}

	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}

	public int getAdminLevel() {
		return this.adminLevel;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getLoginDeptID() {
		return loginDeptID;
	}

	public void setLoginDeptID(String loginDeptID) {
		this.loginDeptID = loginDeptID;
	}

	public String getScreenGrantNo() {
		return screenGrantNo;
	}

	public void setScreenGrantNo(String screenGrantNo) {
		this.screenGrantNo = screenGrantNo;
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

	public int getOrderNo() {
		return orderNo;
	}

	public int getActivity() {
		return activity;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getHrTag() {
		return hrTag;
	}

	public void setHrTag(String hrTag) {
		this.hrTag = hrTag;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCountryPreference() {
		return countryPreference;
	}

	public void setCountryPreference(String countryPreference) {
		this.countryPreference = countryPreference;
	}

	public String getLanguagePreference() {
		return languagePreference;
	}

	public void setLanguagePreference(String languagePreference) {
		this.languagePreference = languagePreference;
	}

	public Locale getLocale() {
		return new Locale(this.getLanguagePreference(), this.getCountryPreference());
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getKoreanname() {
		return koreanname;
	}

	public void setKoreanname(String koreanname) {
		this.koreanname = koreanname;
	}

	public String getEnglishdept() {
		return englishdept;
	}

	public void setEnglishdept(String englishdept) {
		this.englishdept = englishdept;
	}

	public String getKordept() {
		return kordept;
	}

	public void setKordept(String kordept) {
		this.kordept = kordept;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public String getCpnyId() {
		return cpnyId;
	}

	public void setCpnyId(String cpnyId) {
		this.cpnyId = cpnyId;
	}

	public String getCpnyName() {
		return cpnyName;
	}

	public void setCpnyName(String cpnyName) {
		this.cpnyName = cpnyName;
	}

	public List getCodeType() {
		return CodeType;
	}

	public void setCodeType(List codeType) {
		CodeType = codeType;
	}

	public String getEmpDiffCode() {
		return empDiffCode;
	}

	public void setEmpDiffCode(String empDiffCode) {
		this.empDiffCode = empDiffCode;
	}

}
