package com.ait.ar.bean;

public class ArDetail {
	private String empID;

	private String empName;

	private String empPinyin;

	private String enEmpName;

	private String korEmpName;
	
	private String deptName;
	
	private String enDept;
	
	private String korDept;

	private String itemName;

	private String enItemName;

	private String korItemName;
	  
	private String positionName;

	private String enPositionName;

	private String korPositionName;
	
	private String itemShortName;

	private String shiftName;

	private String enShiftName;

	private String korShiftName;
	
	private String shiftShortName;
	
	private String shiftShortEnName;

	private Integer itemNo;

	private Integer shiftNo;

	private Integer pkNo;

	private String fromTime;

	private String toTime;

	private String ar_date_str;
	
	private Integer date_day;
	
	private Double quantity;

	private String isLock;
	
	private String isNight ;

	private String unit;
	
	private String unitName;
	
	private String unitEnName;
	
	private String unitKoName;

	private String ar_month_str;

	private Integer date_type;
	
	// daily status 0:unlock 1:lock
	private Integer status;
	
	private Integer update_flag ;
	
	private String createBy ;
	
	private String createDate ;
	
	private String operation ;
	
	private int arFlag;
	
	private int flag;
	
	private String deptId;
	
	private String remark ;
	
	private String abnormalFlag;

	public ArDetail() {
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public Integer getShiftNo() {
		return shiftNo;
	}

	public void setShiftNo(Integer shiftNo) {
		this.shiftNo = shiftNo;
	}

	public Integer getPkNo() {
		return pkNo;
	}

	public void setPkNo(Integer pkNo) {
		this.pkNo = pkNo;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getAr_date_str() {
		return ar_date_str;
	}

	public void setAr_date_str(String ar_date_str) {
		this.ar_date_str = ar_date_str;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAr_month_str() {
		return ar_month_str;
	}

	public void setAr_month_str(String ar_month_str) {
		this.ar_month_str = ar_month_str;
	}

	public Integer getDate_type() {
		return date_type;
	}

	public void setDate_type(Integer date_type) {
		this.date_type = date_type;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getItemShortName() {
		return itemShortName;
	}

	public void setItemShortName(String itemShortName) {
		this.itemShortName = itemShortName;
	}

	public String getShiftShortName() {
		return shiftShortName;
	}

	public void setShiftShortName(String shiftShortName) {
		this.shiftShortName = shiftShortName;
	}

	public Integer getDate_day() {
		return date_day;
	}

	public void setDate_day(Integer date_day) {
		this.date_day = date_day;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public String getUnitEnName() {
		return unitEnName;
	}

	
	public void setUnitEnName(String unitEnName) {
		this.unitEnName = unitEnName;
	}

	
	public String getUnitKoName() {
		return unitKoName;
	}

	
	public void setUnitKoName(String unitKoName) {
		this.unitKoName = unitKoName;
	}

	
	public String getUnitName() {
		return unitName;
	}

	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getEmpPinyin() {
		return empPinyin;
	}

	public void setEmpPinyin(String empPinyin) {
		this.empPinyin = empPinyin;
	}

	public String getEnEmpName() {
		return enEmpName;
	}

	public void setEnEmpName(String enEmpName) {
		this.enEmpName = enEmpName;
	}

	public String getKorEmpName() {
		return korEmpName;
	}

	public void setKorEmpName(String korEmpName) {
		this.korEmpName = korEmpName;
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

	public String getEnItemName() {
		return enItemName;
	}

	public void setEnItemName(String enItemName) {
		this.enItemName = enItemName;
	}

	public String getKorItemName() {
		return korItemName;
	}

	public void setKorItemName(String korItemName) {
		this.korItemName = korItemName;
	}

	public String getEnShiftName() {
		return enShiftName;
	}

	public void setEnShiftName(String enShiftName) {
		this.enShiftName = enShiftName;
	}

	public String getKorShiftName() {
		return korShiftName;
	}

	public void setKorShiftName(String korShiftName) {
		this.korShiftName = korShiftName;
	}

	public String getShiftShortEnName() {
		return shiftShortEnName;
	}

	public void setShiftShortEnName(String shiftShortEnName) {
		this.shiftShortEnName = shiftShortEnName;
	}

	
	public String getEnPositionName() {
		return enPositionName;
	}

	
	public void setEnPositionName(String enPositionName) {
		this.enPositionName = enPositionName;
	}

	
	public String getKorPositionName() {
		return korPositionName;
	}

	
	public void setKorPositionName(String korPositionName) {
		this.korPositionName = korPositionName;
	}

	
	public String getPositionName() {
		return positionName;
	}

	
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getUpdate_flag() {
		return update_flag;
	}

	public void setUpdate_flag(Integer update_flag) {
		this.update_flag = update_flag;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getIsNight() {
		return isNight;
	}

	public void setIsNight(String isNight) {
		this.isNight = isNight;
	}
	public int getArFlag() {
		return arFlag;
	}

	public void setArFlag(int arFlag) {
		this.arFlag = arFlag;
	}
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAbnormalFlag() {
		return abnormalFlag;
	}

	public void setAbnormalFlag(String abnormalFlag) {
		this.abnormalFlag = abnormalFlag;
	}

}
