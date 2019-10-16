package com.ait.ar;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.apache.regexp.RE;

import com.ait.ar.business.ArServices;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class CalMultiTread extends Thread {

	private ArServices services;

	private Thread thread;

	private String startdate;

	private String enddate;

	private String calType;

	private String supervisorId;

	private String deptId;

	private String empId;
	
	private String result;

	private String cnpyId ;

	CalMultiTread() {
		
		super() ;
		this.start();
	}
	
	CalMultiTread(String name) {
		
		super(name) ;
	}
	
	public void calc(){
		
		this.start();
		
	}
	
public void stopCurrentThread(){
		
		thread.stop();
		
	}

	public void run() {

		Connection conn = ConnBean.getConn();
		CallableStatement cs = null;
		String sql = "{CALL AR_DETAIL_CAL(?, ?, ?, ?, ?, ?, ?)}";
		try {
			Logger.getLogger(getClass()).debug(startdate + ", " + enddate + ", " + empId);
			
			cs = conn.prepareCall(sql);
			cs.setString(1, startdate);
			cs.setString(2, enddate);
			cs.setString(3, calType);
			cs.setString(4, cnpyId);
			cs.setString(5, supervisorId);
			cs.setString(6, deptId);
			cs.setString(7, empId);
			cs.registerOutParameter(7, Types.VARCHAR);
			
			if (cs.executeUpdate() == 1)
				if (cs.getString(7) != null && cs.getString(7).length() > 0){
					Logger.getLogger(getClass()).debug(startdate + "日 " +cs.getString(7));
					result = null ; //startdate + " " + cs.getString(7);
				}
			else
				result = startdate + " [计算失败] 未知错误";
			Logger.getLogger(getClass()).debug(result);
		} catch (SQLException e) {
			result = startdate + " [计算失败] " 
					+ this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", "</br>");
			
			Logger.getLogger(getClass()).error(
					this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", ""));
			//e.printStackTrace() ;
		} finally {
			SqlUtil.close(cs, conn);
		}

	}
	
	private String Message(String s, String reg, String to) {
		RE re = new RE(reg);
		while (re.match(s)) {
			s = s.replaceAll(re.getParen(0), to);
		}
		return s;
	}
	
	public void setCalType(String calType) {
		this.calType = calType;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public void setServices(ArServices services) {
		this.services = services;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public String getResult() {
		return result;
	}

	
	public String getStartdate() {
		return startdate;
	}

	public void setCnpyId(String cnpyId) {
		this.cnpyId = cnpyId;
	}

	public String getCnpyId() {
		return cnpyId;
	}

}
