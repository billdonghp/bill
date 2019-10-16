/**
 * @Copyright AIT
 * @author qinxd
 * @date 2006-6-23
 */
package com.ait.sysparam;
/**
 * 人事模块参数对象
 * @version 1.0
 */
public class EssSysparam extends Sysparam{

    private final static String default_paramName = "ESS 参数设置";
    private final static String default_description = "ESS 参数设置";
  
    public void initialized(){
    	this.setParamName(default_paramName);
    	this.setDescription(default_description);
    }

	public String toString(){
		return new StringBuffer().append(super.toString())
		                         .append("; otAutoConfirm is ").append(otAutoConfirm)
		                         .append("; leaveAutoConfirm is ").append(leaveAutoConfirm)
		                         .append("; preConfirm is ").append(preConfirm)
		                         .append("; reConfirm is ").append(reConfirm)
		                         .append("; modifiedAfterAffirm is ").append(otModifiedAfterAffirm).toString();
	}

	public static String getDefault_paramName() {
		return default_paramName;
	}

	public static String getDefault_description() {
		return default_description;
	}

	/**
	 * ESS系统是否自动向下一级决裁者发邮件
	 */
	private boolean autoSendMail = true;
	/**
	 * 行政管理是否自动向下一级决裁者发邮件
	 */
	private boolean gaSendMail = true;
	
	/**
	 * 取决裁者时，是否包含自己
	 */
	private boolean containsOwner = true;
	
	/**
	 * 加班最终决裁后是否自动进行人事确认
	 */
	private boolean otAutoConfirm = true;
	/**
	 * 休假最终决裁后是否自动进行人事确认
	 */
	private boolean leaveAutoConfirm = true;

	/**
	 * 人事是否可以提前进行确认
	 */
	private boolean preConfirm = false;
	
	/**
	 * 人事是否可以在确认后重新确认
	 */
	private boolean reConfirm = false;

	/** 
	 * 加班决裁是否可反悔
	 */
	private boolean otModifiedAfterAffirm = true ;
	
	/** 
	 * 休假决裁是否可反悔
	 */
	private boolean leaveModifiedAfterAffirm = true ;
	
	/**
	 * 加班申请是否执行始末时间先后检查
	 */
	private boolean checkOtApplyStartEndTime = true;

	/**
	 * 加班申请是否执行加班时间与加班时间冲突检查
	 */
	private boolean checkOtApplyOtConflict = true;
	
	/**
	 * 加班申请是否执行加班时间与班次时间冲突检查
	 */
	private boolean checkOtApplyShiftConflict = true;
	
	/**
	 * 加班申请是否执行加班时间与休假时间冲突检查
	 */
	private boolean checkOtApplyLeaveConflict = true;
	
	/**
	 * 加班申请是否执行加班时间与值班时间冲突检查
	 */
	private boolean checkOtApplyMatchConflict = true;
	
	/**
	 * 加班修改是否可以通过权限控制
	 */
	private boolean otUpdateControl = true;

	/**
	 * 加班可申请?天前的加班
	 * -1:不检查(可申请任意时间加班)
	 */
	private int otApplyDaysBefore = -1;
	
	/**
	 * 加班可申请?天后的加班
	 * -1:不检查(可申请任意时间加班)
	 */
	private int otApplyDaysAfter = -1;
	
	/**
	 * 加班类型判断标志
	 * -1:不判断(取手工提交值);  0:自动判断与手工提交数据比对;  1:取自动判断值;
	 */
	private int otApplyTypeCheckFlag = 0;

	/**
	 * 加班申请是否执行加班上限检查
	 */
	private boolean otApplyMaxHours = true;

	/**
	 * 休假始末时间前后检查
	 */
	private boolean checkLeaveApplyStartEndTime = true;
	
	/**
	 * 是否执行休假与加班冲突检查
	 */
	private boolean checkLeaveApplyOtConflict = true;
	
	/**
	 * 是否执行休假与休假冲突检查
	 */
	private boolean checkLeaveApplyLeaveConflict = true;
	
	/**
	 * 是否执行休假与值班冲突检查
	 */
	private boolean checkLeaveApplyMatchConflict = true;
	
	/**
	 * 值班始末时间前后检查
	 */
	private boolean checkMatchApplyStartEndTime = true;
	
	/**
	 * 是否执行值班与加班冲突检查
	 */
	private boolean checkMatchApplyOtConflict = true;
	
	/**
	 * 是否执行值班与休假冲突检查
	 */
	private boolean checkMatchApplyLeaveConflict = true;
	
	/**
	 * 是否执行值班与值班冲突检查
	 */
	private boolean checkMatchApplyMatchConflict = true;

	
	public boolean isLeaveAutoConfirm() {
		return leaveAutoConfirm;
	}

	public void setLeaveAutoConfirm(boolean leaveAutoConfirm) {
		this.leaveAutoConfirm = leaveAutoConfirm;
	}

	public boolean isOtAutoConfirm() {
		return otAutoConfirm;
	}

	public void setOtAutoConfirm(boolean otAutoConfirm) {
		this.otAutoConfirm = otAutoConfirm;
	}

	
	public boolean isLeaveModifiedAfterAffirm() {
		return leaveModifiedAfterAffirm;
	}

	public void setLeaveModifiedAfterAffirm(boolean leaveModifiedAfterAffirm) {
		this.leaveModifiedAfterAffirm = leaveModifiedAfterAffirm;
	}

	public boolean isOtModifiedAfterAffirm() {
		return otModifiedAfterAffirm;
	}

	public void setOtModifiedAfterAffirm(boolean otModifiedAfterAffirm) {
		this.otModifiedAfterAffirm = otModifiedAfterAffirm;
	}

	public boolean isPreConfirm() {
		return preConfirm;
	}
	public void setPreConfirm(boolean preConfirm) {
		this.preConfirm = preConfirm;
	}
	public boolean isReConfirm() {
		return reConfirm;
	}
	public void setReConfirm(boolean reConfirm) {
		this.reConfirm = reConfirm;
	}
	
	public boolean isCheckLeaveApplyLeaveConflict() {
		return checkLeaveApplyLeaveConflict;
	}

	public void setCheckLeaveApplyLeaveConflict(boolean checkLeaveApplyLeaveConflict) {
		this.checkLeaveApplyLeaveConflict = checkLeaveApplyLeaveConflict;
	}

	public boolean isCheckLeaveApplyMatchConflict() {
		return checkLeaveApplyMatchConflict;
	}

	public void setCheckLeaveApplyMatchConflict(boolean checkLeaveApplyMatchConflict) {
		this.checkLeaveApplyMatchConflict = checkLeaveApplyMatchConflict;
	}

	public boolean isCheckLeaveApplyOtConflict() {
		return checkLeaveApplyOtConflict;
	}

	public void setCheckLeaveApplyOtConflict(boolean checkLeaveApplyOtConflict) {
		this.checkLeaveApplyOtConflict = checkLeaveApplyOtConflict;
	}

	public boolean isCheckLeaveApplyStartEndTime() {
		return checkLeaveApplyStartEndTime;
	}

	public void setCheckLeaveApplyStartEndTime(boolean checkLeaveApplyStartEndTime) {
		this.checkLeaveApplyStartEndTime = checkLeaveApplyStartEndTime;
	}

	public boolean isCheckMatchApplyLeaveConflict() {
		return checkMatchApplyLeaveConflict;
	}

	public void setCheckMatchApplyLeaveConflict(boolean checkMatchApplyLeaveConflict) {
		this.checkMatchApplyLeaveConflict = checkMatchApplyLeaveConflict;
	}

	public boolean isCheckMatchApplyMatchConflict() {
		return checkMatchApplyMatchConflict;
	}

	public void setCheckMatchApplyMatchConflict(boolean checkMatchApplyMatchConflict) {
		this.checkMatchApplyMatchConflict = checkMatchApplyMatchConflict;
	}

	public boolean isCheckMatchApplyOtConflict() {
		return checkMatchApplyOtConflict;
	}

	public void setCheckMatchApplyOtConflict(boolean checkMatchApplyOtConflict) {
		this.checkMatchApplyOtConflict = checkMatchApplyOtConflict;
	}

	public boolean isCheckMatchApplyStartEndTime() {
		return checkMatchApplyStartEndTime;
	}

	public void setCheckMatchApplyStartEndTime(boolean checkMatchApplyStartEndTime) {
		this.checkMatchApplyStartEndTime = checkMatchApplyStartEndTime;
	}

	public boolean isCheckOtApplyLeaveConflict() {
		return checkOtApplyLeaveConflict;
	}

	public void setCheckOtApplyLeaveConflict(boolean checkOtApplyLeaveConflict) {
		this.checkOtApplyLeaveConflict = checkOtApplyLeaveConflict;
	}

	public boolean isCheckOtApplyMatchConflict() {
		return checkOtApplyMatchConflict;
	}

	public void setCheckOtApplyMatchConflict(boolean checkOtApplyMatchConflict) {
		this.checkOtApplyMatchConflict = checkOtApplyMatchConflict;
	}

	public boolean isCheckOtApplyOtConflict() {
		return checkOtApplyOtConflict;
	}

	public void setCheckOtApplyOtConflict(boolean checkOtApplyOtConflict) {
		this.checkOtApplyOtConflict = checkOtApplyOtConflict;
	}

	public boolean isCheckOtApplyStartEndTime() {
		return checkOtApplyStartEndTime;
	}

	public void setCheckOtApplyStartEndTime(boolean checkOtApplyStartEndTime) {
		this.checkOtApplyStartEndTime = checkOtApplyStartEndTime;
	}

	public int getOtApplyDaysAfter() {
		return otApplyDaysAfter;
	}

	public void setOtApplyDaysAfter(int otApplyDaysAfter) {
		this.otApplyDaysAfter = otApplyDaysAfter;
	}

	public int getOtApplyDaysBefore() {
		return otApplyDaysBefore;
	}

	public void setOtApplyDaysBefore(int otApplyDaysBefore) {
		this.otApplyDaysBefore = otApplyDaysBefore;
	}

	public boolean getOtApplyMaxHours() {
		return otApplyMaxHours;
	}

	public void setOtApplyMaxHours(boolean otApplyMaxHours) {
		this.otApplyMaxHours = otApplyMaxHours;
	}

	public int getOtApplyTypeCheckFlag() {
		return otApplyTypeCheckFlag;
	}

	public void setOtApplyTypeCheckFlag(int otApplyTypeCheckFlag) {
		this.otApplyTypeCheckFlag = otApplyTypeCheckFlag;
	}

	public boolean isCheckOtApplyShiftConflict() {
		return checkOtApplyShiftConflict;
	}

	public void setCheckOtApplyShiftConflict(boolean checkOtApplyShiftConflict) {
		this.checkOtApplyShiftConflict = checkOtApplyShiftConflict;
	}

	public boolean isAutoSendMail() {
		return autoSendMail;
	}

	public boolean isOtUpdateControl() {
		return otUpdateControl;
	}

	public void setOtUpdateControl(boolean otUpdateControl) {
		this.otUpdateControl = otUpdateControl;
	}

	public void setAutoSendMail(boolean autoSendMail) {
		this.autoSendMail = autoSendMail;
	}

	public boolean isGaSendMail() {
		return gaSendMail;
	}

	public void setGaSendMail(boolean gaSendMail) {
		this.gaSendMail = gaSendMail;
	}

	public boolean isContainsOwner() {
		return containsOwner;
	}

	public void setContainsOwner(boolean containsOwner) {
		this.containsOwner = containsOwner;
	}

}
