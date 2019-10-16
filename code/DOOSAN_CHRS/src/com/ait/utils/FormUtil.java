package com.ait.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.hrm.bean.EmployeeBean;
import com.ait.sy.bean.SysCodeBean;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ibm.icu.text.SimpleDateFormat;

//import com.ait.util.StringUtil;

public class FormUtil {
	static SysCodeBean scodebean =null;
	/*当派车信息为空时清楚所派车辆的信息*/
	public static List getSysCode(String codeClass){
		List list = new ArrayList();
	    Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql="select t.code_id,t.code_name  from sy_code t where t.parent_code='"+codeClass+"'";
		
		try {
			    stmt = conn.prepareStatement(sql);
			    rs = stmt.executeQuery();
			    while(rs.next()){
			    	SysCodeBean scb = new SysCodeBean();
			    	scb.setCodeId(rs.getString("code_id"));
			    	scb.setCodeName(rs.getString("code_name"));
			    	list.add(scb);			    	
			    }
			
		} catch (Exception e) {			
			
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}
		return list;
	}
	/*得到系统的所有员工*/
	public static List getThisDeptAllEmployee(String adminid){
		List list = new ArrayList();
	    Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql="select t.empid, t.chinesename "+
                   "from hr_employee t "+
                   " where t.deptid =(select t.deptid from hr_employee t where t.empid = '"+adminid+"')";
		
		try {
			    stmt = conn.prepareStatement(sql);
			    rs = stmt.executeQuery();
			    while(rs.next()){
			    	EmployeeBean emplyoeebean = new EmployeeBean();
			    	emplyoeebean.setEmpID(rs.getString("EmpID"));
			    	emplyoeebean.setChineseName(rs.getString("ChineseName"));
			        list.add(emplyoeebean) ;  	
			    }
			
		} catch (Exception e) {			
			
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}
		return list;
	}
	

	public static String BoolSelect (String language,String selectName, boolean selected) {
		String options = "<select name=\"" + selectName + "\">";
		options += "<option value=\"1\"" + (selected?" selected":"") + ">是</option>";
		options += "<option value=\"0\"" + (selected?"":" selected") + ">否</option>";
		options += "</select>";
		if(language.equals("en"))
		{
			options = "<select name=\"" + selectName + "\">";
			options += "<option value=\"1\"" + (selected?" selected":"") + ">Yes</option>";
			options += "<option value=\"0\"" + (selected?"":" selected") + ">No</option>";
			options += "</select>";
		}
		return options;
	}  
	public static String gaVoitureState (String selectName, String selected, List codeclass) {
		
		String options = "<select name=\"" + selectName + "\">";
		for(int i=0;i<codeclass.size();i++){
			scodebean=(SysCodeBean)codeclass.get(i);
		options += "<option value=\""+scodebean.getCodeId()+"\" "+(selected.equals(scodebean.getCodeId())?"selected":"")+" >"+scodebean.getCodeName()+"</option>";		
		}
		options += "</select>";		
		return options;
	}  
	public static String gaVoitureUseState (String selectName, String selected,List codeclass) {
		
		String options = "<select name=\"" + selectName + "\">";
		for(int i=0;i<codeclass.size();i++){
			scodebean=(SysCodeBean)codeclass.get(i);
		options += "<option value=\""+scodebean.getCodeId()+"\" "+(selected.equals(scodebean.getCodeId())?"selected":"")+" >"+scodebean.getCodeName()+"</option>";		
		}
		options += "</select>";		
		return options;
	}      
	
	public static String BoolSelectEn (String selectName, boolean selected) {
		String options = "<select name=\"" + selectName + "\">";
		options += "<option value=\"1\"" + (selected?" selected":"") + ">YES</option>";
		options += "<option value=\"0\"" + (selected?"":" selected") + ">NO</option>";
		options += "</select>";
		return options;
	}

	public static String IntInput (String inputName, int value) {
		String input = "";
		input += "<input type=\"text\" name=\"";
		input += inputName;
		input += "\" value=\"";
		input += String.valueOf(value);     
		input += "\" style=\"width:40px;\" />";   
		return input;
	}

	public static String DoubleInput (String inputName, double value) {
		String input = "";
		input += "<input type=\"text\" name=\"";
		input += inputName;
		input += "\" value=\"";
		input += String.valueOf(value);
		input += "\" style=\"width:60px;\" />";
		return input;
	}

	public static String StringInput (String inputName, String value) {
		String input = "";
		input += "<input type=\"text\" name=\"";
		input += inputName;
		input += "\" value=\"";
		input += value;
		input += "\" style=\"width:140px;\" />";
		return input;
	}
	/**
	 * Copyright: AIT (c) Company: AIT
	 * 
	 * @author Administrator (yangxiaohui@ait.net.cn)
	 * @Date 2008-3-28
	 * 
	 */
	/*制作定单号用来插入数据库*/
	public static String getApplyDocumentid(String pkcoumeName,String tableName,String flag){
		ResultSet result = null;
		String doucmentno1="";		
		Connection conn = ConnBean.getConn();
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		String sql = "select max(substr("+pkcoumeName+","+(flag.length()+7)+")) as document_no  from "+tableName+" where substr("+pkcoumeName+", "+(flag.length()+1)+", 6) = '"+timeFormatter.format(d).substring(2, 8)+"'";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			if(result.next() && (result.getInt("document_no")!=0)){
				if(result.getInt("document_no")<9){
					doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+"0"+(result.getInt("document_no")+1)).toString();
				}else{
					doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+(result.getInt("document_no")+1)).toString();
				}

			}else{
				
				doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+"01").toString();
			}
			
		
		} catch (Exception e) {	
			e.printStackTrace();		
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		
		return doucmentno1;
		
	}
	/*
	 * 随机生成  设施维修申请编号值
	 */
	public static String getApplyDocumentidFa(String pkcoumeName,String tableName,String flag){
		ResultSet result = null;
		String doucmentno1="";		
		Connection conn = ConnBean.getConn();
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd");
		try{
			int  radom = (int)(Math.random()*10000);
//			System.out.println(radom);
			doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+radom).toString();
			
		} catch (Exception e) {	
			e.printStackTrace();		
		}
		
		return doucmentno1;
		
	}
	
	
	/**
	 * Copyright: AIT (c) Company: AIT
	 * 
	 * @author Administrator (zhangji@ait.net.cn)
	 * @Date 2012-9-17
	 * 
	 */
	/*制作定单号用来插入数据库*/
	public static String getApplyDocumentidNew(String pkcoumeName,String tableName,String flag){
		ResultSet result = null;
		String doucmentno1="";		
		Connection conn = ConnBean.getConn();
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		String sql = "select max(TO_NUMBER(substr("+pkcoumeName+","+(flag.length()+7)+"))) as document_no  from "+tableName+" where substr("+pkcoumeName+", "+(flag.length()+1)+", 6) = '"+timeFormatter.format(d).substring(2, 8)+"'";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			if(result.next() && (result.getInt("document_no")!=0)){
				if(result.getInt("document_no")<9){
					doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+"0"+(result.getInt("document_no")+1)).toString();
				}else{
					doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+(result.getInt("document_no")+1)).toString();
				}

			}else{
				
				doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+"01").toString();
			}
			
		
		} catch (Exception e) {	
			e.printStackTrace();		
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		
		return doucmentno1;
		
	}

	public static String getAllEmployeeListForSelect (String selectName,String adminid) {
		List emplyoeeList = getThisDeptAllEmployee(adminid);
		
		String options = "<select name=\"" + selectName + "\">";
		options += "<option value=\"\">请选择</option>";
		for(int i=0;i<emplyoeeList.size();i++){
			EmployeeBean eb = new EmployeeBean();
			eb=(EmployeeBean)emplyoeeList.get(i);
		options += "<option value=\""+eb.getEmpID()+"\">"+eb.getChineseName()+"</option>";
		}
		options += "</select>";
		
		return options;
	}  
		
	
}
