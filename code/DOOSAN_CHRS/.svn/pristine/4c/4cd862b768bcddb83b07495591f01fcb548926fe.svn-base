package com.ait.pa.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.pa.business.PaServices;
import com.ait.pa.cmd.ccplan.CCPlan;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;
import com.ibm.icu.text.SimpleDateFormat;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-04-01
 * 
 */
public class PayPlanCommand implements Command {
	
	private PaServices  paServices = null;
	
	 private static ServiceLocator services;

	public PayPlanCommand(){
		paServices = PaServices.getInstance();
		
		 try {
	            services = ServiceLocator.getInstance();
	        } catch (ServiceLocatorException ex) {
	        }
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		if(!content.equals("") && content.equals("pa_payPlan_01")){			
			returnPage = "/pa_payPlan_01.jsp";
		}else if(!content.equals("") && content.equals("pa_payPlan_02")){			
			returnPage="/pa_payPlan_02.jsp";
		}else if(!content.equals("") && content.equals("pa_payPlan_03")){	
			request.setAttribute("paVersion",this.getVesionNote("PA",admin.getCpnyId()));
			request.setAttribute("peVersion", this.getVesionNote("PE_RG",admin.getCpnyId()));			
			returnPage="/pa_payPlan_03.jsp";
		}else if(!content.equals("") && content.equals("pa_payPlan_04")){
			request.setAttribute("paVersion",this.getVesionNote("PA",admin.getCpnyId()));
			request.setAttribute("peVersion", this.getVesionNote("PE_RG",admin.getCpnyId()));
			returnPage="/pa_payPlan_04.jsp";
		}else if(!content.equals("") && content.equals("payPlan01Serche")){
			returnPage = this.payPlan01Serche(request,admin);
		}else if(!content.equals("") && content.equals("payPlan01Save")){			
		    String flag=this.payPlan01Save(request,admin);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa_payPlan_01.jsp?menu_code=pa0401");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("payPlan02Serche")){
			returnPage = this.payPlan02Serche(request,admin);
		}else if(!content.equals("") && content.equals("payPlan02Save")){
			String flag=this.payPlan02Save(request,admin);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa_payPlan_02.jsp?menu_code=pa0402");
			returnPage="/inc/alertMessage.jsp";			
		}else if(!content.equals("") && content.equals("payPlan03Serche")){	
			request.setAttribute("paVersion",this.getVesionNote("PA",admin.getCpnyId()));
			request.setAttribute("peVersion", this.getVesionNote("PE_RG",admin.getCpnyId()));
			returnPage = this.payPlan03Serche(request,admin);
		}else if(!content.equals("") && content.equals("payPlan03Save")){			
			String flag=this.payPlan03Save(request,admin);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa_payPlan_03.jsp?menu_code=pa0403");
			returnPage="/inc/alertMessage.jsp";				
		}else if(!content.equals("") && content.equals("payPlan04Serche")){
			request.setAttribute("paVersion",this.getVesionNote("PA",admin.getCpnyId()));
			request.setAttribute("peVersion", this.getVesionNote("PE_RG",admin.getCpnyId()));
			returnPage=this.payPlan04Serche(request,admin);
		}else if(!content.equals("") && content.equals("payPlan04Save")){
			String flag=this.payPlan04Save(request,admin);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa_payPlan_04.jsp?menu_code=pa0404");
			returnPage="/inc/alertMessage.jsp";				
		}else if(!content.equals("") && content.equals("pa_payPlan_05")){
			request.setAttribute("paVersion",this.getVesionNote("PA",admin.getCpnyId()));
			request.setAttribute("pbVersion",this.getVesionNote("PB",admin.getCpnyId()));
			request.setAttribute("pcVersion",this.getVesionNote("PC",admin.getCpnyId()));
			request.setAttribute("peVersion",this.getVesionNote("PE_RG",admin.getCpnyId()));
			request.setAttribute("baseMonth", this.getBaseMonth(admin.getCpnyId()));	
			request.setAttribute("planYear", request.getParameter("planYear"));
			returnPage="/pa_payPlan_05.jsp";	
		}else if(!content.equals("") && content.equals("synchronizeCC")){
			CCPlan cc = new CCPlan();
			int flag=cc.productsTempPersonPlanTable(admin.getCpnyId(),request.getParameter("planYear"));
			String errorMsg=(flag!=1?"同步人员计划失败！":"同步人员计划成功！");
			request.setAttribute("errorMsg", errorMsg);					
			request.setAttribute("paVersion",this.getBaseVesion("PA",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
			request.setAttribute("pbVersion",this.getBaseVesion("PB",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
			request.setAttribute("pcVersion",this.getBaseVesion("PC",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
			request.setAttribute("peVersion",this.getBaseVesion("PE_RG",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
			request.setAttribute("baseMonth",this.getBaseMonth(admin.getCpnyId()));		
			request.setAttribute("YYYMM", request.getParameter("baseMonth"));
			request.setAttribute("planYear", request.getParameter("planYear"));
			request.setAttribute("planMonth", this.getPersonPlanMonth(admin.getCpnyId(), request.getParameter("planYear")));
			
			returnPage="/pa_payPlan_05.jsp";	
		}else if(!content.equals("") && content.equals("changeVersion")){			
			request.setAttribute("paVersion",this.getBaseVesion("PA",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
			request.setAttribute("pbVersion",this.getBaseVesion("PB",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
			request.setAttribute("pcVersion",this.getBaseVesion("PC",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
			request.setAttribute("peVersion",this.getBaseVesion("PE_RG",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
			request.setAttribute("baseMonth", this.getBaseMonth(admin.getCpnyId()));		
			request.setAttribute("YYYMM", request.getParameter("baseMonth"));
			request.setAttribute("planYear", request.getParameter("planYear"));
			request.setAttribute("planMonth", this.getPersonPlanMonth(admin.getCpnyId(), request.getParameter("planYear")));
			returnPage="/pa_payPlan_05.jsp";			
		}else if(!content.equals("") && content.equals("synchronizePayPlan")){			
				this.GenerationPayPlanCC(request,admin);
				String errorMsg="生成人件费计划成功！";
				request.setAttribute("errorMsg", errorMsg);	
				request.setAttribute("paVersion",this.getBaseVesion("PA",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
				request.setAttribute("pbVersion",this.getBaseVesion("PB",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
				request.setAttribute("pcVersion",this.getBaseVesion("PC",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
				request.setAttribute("peVersion",this.getBaseVesion("PE_RG",admin.getCpnyId(),request.getParameter("baseMonth"),request.getParameter("planYear")));
				request.setAttribute("baseMonth",this.getBaseMonth(admin.getCpnyId()));		
				request.setAttribute("YYYMM", request.getParameter("baseMonth"));
				request.setAttribute("planYear", request.getParameter("planYear"));
				request.setAttribute("planMonth", this.getPersonPlanMonth(admin.getCpnyId(), request.getParameter("planYear")));
				returnPage="/pa_payPlan_05.jsp?menu_code=pa0405";			
		}else if(!content.equals("") && content.equals("ImportExcel")){
			try {
				return returnPage=this.ImportExcel(request, admin);
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return "error.jsp";
		}
		return returnPage;
	}
	
	public String payPlan01Serche(HttpServletRequest request,AdminBean admin){
		ResultSet result = null;
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtInsert = null;
		String sql="";
		String monts[]={"0","01","02","03","04","05","06","07","08","09","10","11","12"};
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");	
		SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyyMM");	
		
		String sysYear = timeFormatter.format(new Date());
		String baseYearMonth=timeFormatter1.format(new Date());
		
		String sysDate=request.getParameter("datumYear")+"-01-01";
		String sysDate1=request.getParameter("datumYear")+"0101";
		String datumYear=request.getParameter("datumYear");
		String planYear =request.getParameter("planYear");
		
		int yesThisYearRoseMonth=NumberUtil.parseInt(request.getParameter("yesThisYearRoseMonth"));		//年薪制本年度增长月
		int yesNextYearRoseMonth=NumberUtil.parseInt(request.getParameter("yesNextYearRoseMonth"));     //年薪制下年度增长月
		int noThisYearRoseMonth=NumberUtil.parseInt(request.getParameter("noThisYearRoseMonth"));		//非年薪制本年度增长月
		int noNextYearRoseMonth=NumberUtil.parseInt(request.getParameter("noNextYearRoseMonth"));       //非年薪制下年度增长月
		int startMonth=NumberUtil.parseInt(request.getParameter("datumStartMonth"));
		int endMonth=NumberUtil.parseInt(request.getParameter("datumEndMonth"));
		
		// 关键的数值,下面的循环有用到
		int maxPayMonth=this.getPayMonthMax(datumYear,request.getParameter("datumStartMonth"),request.getParameter("datumEndMonth"),admin.getCpnyId());
		int minPayMonth=this.getPayMonthMin(datumYear,request.getParameter("datumStartMonth"),request.getParameter("datumEndMonth"),admin.getCpnyId());
		
		Logger.getLogger(getClass()).debug("maxPayMonth= " + maxPayMonth);
		Logger.getLogger(getClass()).debug("minPayMonth= " + minPayMonth);
		
		double yesThisYearRate=0.00;		
		double yesNextYearRate=0.00;	
		double noThisYearRate=0.00;		
		double noNextYearRate=0.00;
		if(maxPayMonth>=yesThisYearRoseMonth){
			yesThisYearRate=0;
			yesThisYearRoseMonth=maxPayMonth;
		}else{
			yesThisYearRate=NumberUtil.parseDouble(request.getParameter("yesThisYearRate"))/100;
		}
		if(maxPayMonth>=noThisYearRoseMonth){
			noThisYearRate=0;
			noThisYearRoseMonth=maxPayMonth;
		}else{
			noThisYearRate=NumberUtil.parseDouble(request.getParameter("noThisYearRate"))/100;
		}
		yesNextYearRate=NumberUtil.parseDouble(request.getParameter("yesNextYearRate"))/100;	
		noNextYearRate=NumberUtil.parseDouble(request.getParameter("noNextYearRate"))/100;	
		
		//年薪制的推算
		sql+=" select info.deptid,info.deptname,info.join_type_code,info.post_grade_code,info.wages_type_code,";
		
		if(planYear.equals(sysYear)){//做本年度的工资计划
				
					for(int i=1;i<minPayMonth;i++){//从1月到最小月取得平均工资	
						sql+=" get_pa_average('base_pay','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code) as pa"+monts[i]+",";			
					}
				    for(int i=minPayMonth;i<=maxPayMonth;i++){//取最小工资月和工资最大月里面的实际数据
				        sql+=" get_paMonth_average('BASE_PAY', '"+datumYear+monts[i]+"',info.deptid,info.join_type_code, info.post_grade_code, info.wages_type_code) as pa"+monts[i]+",";   	
				    	
				    }
				    for(int i=(maxPayMonth+1);i<=yesThisYearRoseMonth;i++){
				    	sql+=" get_pa_average('BASE_PAY','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code, info.post_grade_code, info.wages_type_code) as pa"+monts[i]+",";   		
				    	
				    }
				    
				    if(maxPayMonth>=yesThisYearRoseMonth){//当本年度增长月小于最大工资月的时候，取增长月到最大工资月之间的平均数据
				    	 for(int i=(yesThisYearRoseMonth+1);i<=12;i++){//取最大工资月到12月之间的平均工资数据
						    	sql+=" get_pa_average('base_pay','"+datumYear+monts[NumberUtil.parseInt(request.getParameter("thisYearRoseMonth"))]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code) as pa"+monts[i]+",";			
						    	
						 }  	
				    	
				    }else{//当本年度增长月大于最大工资月的时候，取增长月到最大工资月之间的平均数据
					    for(int i=(yesThisYearRoseMonth+1);i<=12;i++){//取最大工资月到12月之间的平均工资数据
					    	sql+=" get_pa_average('base_pay','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code)*(1+"+yesThisYearRate+")  as pa"+monts[i]+",";			
					    	
					    }
				    }
				
	   
		}else{//做下年度的工资计划
			
				 if(maxPayMonth>yesThisYearRoseMonth){//当最大工资月大于本年度上涨月时						 
					 for(int i=1;i<=yesNextYearRoseMonth;i++){//
					    sql+=" get_pa_average('base_pay','"+datumYear+monts[NumberUtil.parseInt(request.getParameter("thisYearRoseMonth"))]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code) as pa"+monts[i]+",";			
					    	
					 }  	
				 
				 }else{
					 for(int i=1;i<=yesNextYearRoseMonth;i++){
				    	sql+=" get_pa_average('BASE_PAY', '"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code, info.post_grade_code, info.wages_type_code)*(1+"+yesNextYearRate+") as pa"+monts[i]+",";   		
				    	
				    }
					 
				 }			 
			
			    for(int i=(yesNextYearRoseMonth+1);i<=12;i++){//取最大工资月到12月之间的平均工资数据
			    	sql+=" get_pa_average('base_pay','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code)*(1+"+yesNextYearRate+")*(1+"+yesNextYearRate+") as pa"+monts[i]+",";			
			    	
			    }
			
		}
		 sql=sql.substring(0,sql.length()-1);
	    sql +=    " from (select distinct hr_get_level_deptid(hr.deptid, '3') deptid, "
				+ " hr_get_level_deptname(hr.deptid, '3') deptname,"
				+ " hr.join_type_code,"
				+ " hr.post_grade_code,"
				+ " hr.wages_type_code"
				+ " from hr_employee hr"
				+ " where hr.cpny_id = '"+admin.getCpnyId()+"'"
				+ " and hr.join_type_code is not null"
				+ " and hr.post_grade_code is not null"
				+ " and hr_get_level_deptid(hr.deptid, '3') is not null"
				+ " and (hr.date_left is null or hr.date_left>to_date('"+sysDate+"','YYYY-MM-DD'))"
				+ " and hr.join_type_code <> 'C_12009_1330064'"
				+ " and hr.join_type_code <>'C_12009_1355628'"
				+ " and hr.join_type_code <>'C_12009_1330063' ) info  where info.wages_type_code='C_20020_1' " ;
	    sql+= " union " ;
	    //非年薪的推算
		sql+=" select info.deptid,info.deptname,info.join_type_code,info.post_grade_code,info.wages_type_code,";
		
		if(planYear.equals(sysYear)){//做本年度的工资计划
					
					for(int i=1;i<minPayMonth;i++){//从1月到最小月取得平均工资	
						sql+=" get_pa_average('base_pay','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code) as pa"+monts[i]+",";			
					}
				    for(int i=minPayMonth;i<=maxPayMonth;i++){//取最小工资月和工资最大月里面的实际数据
				       sql+=" get_paMonth_average('BASE_PAY', '"+datumYear+monts[i]+"',info.deptid,info.join_type_code, info.post_grade_code, info.wages_type_code) as pa"+monts[i]+",";   	
				    	
				    }
				    for(int i=(maxPayMonth+1);i<=noThisYearRoseMonth;i++){
				    	 sql+=" get_pa_average('BASE_PAY','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code, info.post_grade_code, info.wages_type_code) as pa"+monts[i]+",";   		
				    	
				    }
				    if(maxPayMonth>=noThisYearRoseMonth){//当本年度增长月小于最大工资月的时候，取增长月到最大工资月之间的平均数据
				    	 for(int i=(noThisYearRoseMonth+1);i<=12;i++){//取最大工资月到12月之间的平均工资数据
						    	sql+=" get_pa_average('base_pay','"+datumYear+monts[NumberUtil.parseInt(request.getParameter("thisYearRoseMonth"))]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code) as pa"+monts[i]+",";			
						    	
						 }  	
				    	
				    }else{//当本年度增长月大于最大工资月的时候，取增长月到最大工资月之间的平均数据
					    for(int i=(noThisYearRoseMonth+1);i<=12;i++){//取最大工资月到12月之间的平均工资数据
					    	sql+=" get_pa_average('base_pay','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code)*(1+"+noThisYearRate+")  as pa"+monts[i]+",";			
					    	
					    }
				    }
	   
		}else{//做下年度的工资计划
			
				 if(maxPayMonth>noThisYearRoseMonth){//当最大工资月大于本年度上涨月时						 
					 for(int i=1;i<=noNextYearRoseMonth;i++){//
					    	sql+=" get_pa_average('base_pay','"+datumYear+monts[NumberUtil.parseInt(request.getParameter("thisYearRoseMonth"))]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code) as pa"+monts[i]+",";			
					    	
					 }  	
				 
				 }else{
					 for(int i=1;i<=noNextYearRoseMonth;i++){
				    	 sql+="get_pa_average('BASE_PAY', '"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code, info.post_grade_code, info.wages_type_code)*(1+"+noThisYearRate+") as pa"+monts[i]+",";   		
				    	
				    }
					 
				 }			 
			
			    for(int i=(noNextYearRoseMonth+1);i<=12;i++){//取最大工资月到12月之间的平均工资数据
			    	sql+=" get_pa_average('base_pay','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code)*(1+"+noThisYearRate+")*(1+"+noNextYearRate+") as pa"+monts[i]+",";			
			    	
			    }
			
		}
		 sql=sql.substring(0,sql.length()-1);
	    sql +=    " from (select distinct hr_get_level_deptid(hr.deptid, '3') deptid, "
				+ " hr_get_level_deptname(hr.deptid, '3') deptname,"
				+ " hr.join_type_code,"
				+ " hr.post_grade_code,"
				+ " hr.wages_type_code"
				+ " from hr_employee hr"
				+ " where hr.cpny_id = '"+admin.getCpnyId()+"'"
				+ " and hr.join_type_code is not null"
				+ " and hr.post_grade_code is not null"
				+ " and hr_get_level_deptid(hr.deptid, '3') is not null"
				+ " and (hr.date_left is null or hr.date_left>to_date('"+sysDate+"','YYYY-MM-DD'))"
				+ " and hr.join_type_code <> 'C_12009_1330064'"
				+ " and hr.join_type_code <>'C_12009_1355628'"
				+ " and hr.join_type_code <>'C_12009_1330063' ) info  where info.wages_type_code='C_20020_2' " ;	
			
			
		
			
			Logger.getLogger(getClass()).debug(sql);			
			String sqlId=this.getSequence(planYear, baseYearMonth, "PA",admin.getCpnyId());
			String deleteSql="delete from T_PY_PAY_PLAN_DETAIL T where t.COM_ORG_ID=? and t.PAY_TYPE='PA' ";
			String insertSql = "  insert into T_PY_PAY_PLAN_DETAIL(PAYPLAN_DETAIL_ID, COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,"
							+ " BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,WAGES_TYPE_CODE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,  "
							+ " PAY_MONTH_04,PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,   "
							+ " PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID,VERSIONNOTE)   "
							+ " values(Py_Pay_Plan_Detail_Seq.Nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";
			try {
				
		    conn.setAutoCommit(false);
			pstmtDelete =conn.prepareStatement(deleteSql);
			pstmtInsert =conn.prepareStatement(insertSql);
			pstmtDelete.setString(1,admin.getCpnyId());
			pstmtDelete.executeUpdate();			
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			while(result.next()){
				pstmtInsert.setString(1, admin.getCpnyId());
				pstmtInsert.setString(2,planYear);
				pstmtInsert.setString(3,baseYearMonth);
				pstmtInsert.setString(4, sqlId);
				pstmtInsert.setString(5, result.getString("DEPTID"));
				pstmtInsert.setString(6, result.getString("DEPTNAME"));
				pstmtInsert.setString(7, result.getString("JOIN_TYPE_CODE"));
				pstmtInsert.setString(8, result.getString("POST_GRADE_CODE"));
				pstmtInsert.setString(9, result.getString("WAGES_TYPE_CODE"));
				pstmtInsert.setString(10,"PA");
				pstmtInsert.setDouble(11, result.getDouble("PA01"));
				pstmtInsert.setDouble(12, result.getDouble("PA02"));
				pstmtInsert.setDouble(13, result.getDouble("PA03"));
				pstmtInsert.setDouble(14, result.getDouble("PA04"));
				pstmtInsert.setDouble(15, result.getDouble("PA05"));
				pstmtInsert.setDouble(16, result.getDouble("PA06"));
				pstmtInsert.setDouble(17, result.getDouble("PA07"));
				pstmtInsert.setDouble(18, result.getDouble("PA08"));
				pstmtInsert.setDouble(19, result.getDouble("PA09"));
				pstmtInsert.setDouble(20, result.getDouble("PA10"));
				pstmtInsert.setDouble(21, result.getDouble("PA11"));
				pstmtInsert.setDouble(22, result.getDouble("PA12"));				
				pstmtInsert.setDouble(23, result.getDouble("PA01")+result.getDouble("PA02")+result.getDouble("PA03")+result.getDouble("PA04")+result.getDouble("PA05")+result.getDouble("PA06")+result.getDouble("PA07")+result.getDouble("PA08")+result.getDouble("PA09")+result.getDouble("PA10")+result.getDouble("PA11")+result.getDouble("PA12"));
				pstmtInsert.setString(24,admin.getAdminID());
				pstmtInsert.setString(25,admin.getCpnyId());
				pstmtInsert.setString(26,request.getParameter("versionNote"));
				pstmtInsert.addBatch() ;
			} 
			pstmtInsert.executeBatch();
			conn.commit();
			}catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				try {
					pstmtDelete.close();
					pstmtInsert.close();
					result.close();
					pstmt.close();					
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
	   //把已经插进去的结果查询出来
		SimpleMap 	parameterObject =new SimpleMap();
		parameterObject.set("cpnyId",admin.getCpnyId());
		parameterObject.set("paItemStr","'PA'");
        List list= paServices.payPlanSerche(parameterObject);
		request.setAttribute("yesThisYearRoseMonth",request.getParameter("yesThisYearRoseMonth"));
		request.setAttribute("yesNextYearRoseMonth",request.getParameter("yesNextYearRoseMonth"));
		request.setAttribute("noThisYearRoseMonth",request.getParameter("noThisYearRoseMonth"));
		request.setAttribute("noNextYearRoseMonth",request.getParameter("noNextYearRoseMonth"));
		request.setAttribute("planYear",request.getParameter("planYear"));
		request.setAttribute("datumYear",request.getParameter("datumYear"));
		request.setAttribute("startMonth",request.getParameter("datumStartMonth"));
		request.setAttribute("endMonth",request.getParameter("datumEndMonth"));
		request.setAttribute("yesThisYearRate",request.getParameter("yesThisYearRate"));
		request.setAttribute("yesNextYearRate",request.getParameter("yesNextYearRate"));
		request.setAttribute("noThisYearRate",request.getParameter("noThisYearRate"));
		request.setAttribute("noNextYearRate",request.getParameter("noNextYearRate"));
		request.setAttribute("versionNote", request.getParameter("versionNote"));
		request.setAttribute("list", list);		
		request.setAttribute("search", "Yes");
			return "/pa_payPlan_01.jsp";
		
	 }
	public  String payPlan01Save(HttpServletRequest request,AdminBean admin) {
		List commitData = new ArrayList();
		SimpleMap 	parameterObject =new SimpleMap();
		parameterObject.set("cpnyId",admin.getCpnyId());
		parameterObject.set("paItem","PA"); 
		commitData.add(parameterObject);
		paServices.payPlanSave(commitData);
		
		return "基本工资推算数据保存成功！";
		
	}
	
	public String payPlan02Serche(HttpServletRequest request,AdminBean admin){
		ResultSet result = null;
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtInsert = null;
		String sql="";
		
		String monts[]={"0","01","02","03","04","05","06","07","08","09","10","11","12"};
		String[]payParm={"RESIDENTIAL_GRANTS","DUTIES_ALLOWANCE","SPECIAL_GRANTS","DUTY_SUBSIDIES","REGIONAL_GRANTS","OTHER_COMPENSATION"};
		String[]payParmName={"PE_RG","PE_DA","PE_SG","PE_DS","PE_RS","PE_OC"};	
		
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");	
		SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyyMM");	
		
		String sysYear = timeFormatter.format(new Date());
		String baseYearMonth=timeFormatter1.format(new Date());
		String sysDate=request.getParameter("datumYear")+"-01-01";
		String sysDate1=request.getParameter("datumYear")+"0101";
		String datumYear=request.getParameter("datumYear");
		String planYear =request.getParameter("planYear");
		
		int startMonth=NumberUtil.parseInt(request.getParameter("datumStartMonth"));
		int endMonth=NumberUtil.parseInt(request.getParameter("datumEndMonth"));
		
		int maxPayMonth=this.getPayMonthMax(datumYear,request.getParameter("datumStartMonth"),request.getParameter("datumEndMonth"),admin.getCpnyId());
		int minPayMonth=this.getPayMonthMin(datumYear,request.getParameter("datumStartMonth"),request.getParameter("datumEndMonth"),admin.getCpnyId());
		
		for (int s = 0; s < payParm.length; s++) {

			sql += " select '" + payParmName[s] + "'as paytype,info.deptid,info.deptname,info.join_type_code,info.post_grade_code,info.wages_type_code,";

			if (planYear.equals(sysYear)) {// 做本年度的工资计划

				for (int i = 1; i < minPayMonth; i++) {// 从1月到最小月取得平均工资
					sql += " get_pa_average('" + payParm[s] + "','" + datumYear + monts[minPayMonth] + "','" + datumYear + monts[maxPayMonth] + "',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code) as pa" + monts[i] + ",";
				}
				for (int i = minPayMonth; i <= maxPayMonth; i++) {// 取最小工资月和工资最大月里面的实际数据
					sql += " get_paMonth_average('" + payParm[s] + "', '" + datumYear + monts[i] + "',info.deptid,info.join_type_code, info.post_grade_code, info.wages_type_code) as pa" + monts[i] + ",";

				}

				for (int i = (maxPayMonth + 1); i <= 12; i++) {// 取最大工资月到12月之间的平均工资数据
					sql += " get_pa_average('" + payParm[s] + "','" + datumYear + monts[minPayMonth] + "','" + datumYear + monts[maxPayMonth] + "',info.deptid,info.join_type_code,info.post_grade_code,info.wages_type_code) as pa" + monts[i] + ",";
				}

			} else {// 做下年度的工资计划

				for (int i = 1; i <= 12; i++) {
					sql += "get_pa_average('" + payParm[s] + "', '" + datumYear + monts[minPayMonth] + "','" + datumYear + monts[maxPayMonth] + "',info.deptid,info.join_type_code, info.post_grade_code, info.wages_type_code) as pa" + monts[i] + ",";

				}

			}
			sql = sql.substring(0, sql.length() - 1);
			sql += " from (select distinct hr_get_level_deptid(hr.deptid, '3') deptid, " 
				+ " hr_get_level_deptname(hr.deptid, '3') deptname," 
				+ " hr.join_type_code,hr.post_grade_code, hr.wages_type_code" 
				+ " from hr_employee hr" + " where hr.cpny_id = '" + admin.getCpnyId() + "'" 
				+ " and hr.join_type_code is not null" 
				+ " and hr.post_grade_code is not null" 
				+ " and hr_get_level_deptid(hr.deptid, '3') is not null" 
				+ " and (hr.date_left is null or hr.date_left>to_date('" + sysDate + "','YYYY-MM-DD'))" 
				+ " and hr.join_type_code <> 'C_12009_1330064'" 
				+ " and hr.join_type_code <>'C_12009_1355628'" 
				+ " and hr.join_type_code <>'C_12009_1330063' ) info "
				+ " union " ;

		}
		sql=sql.substring(0,sql.length()-6);
			
			Logger.getLogger(getClass()).debug(sql);			
			String sqlId=this.getSequence(planYear, baseYearMonth, "PE_RG",admin.getCpnyId());
			String deleteSql="delete from T_PY_PAY_PLAN_DETAIL T where t.COM_ORG_ID=? and t.PAY_TYPE in ('PE_RG','PE_DA','PE_SG','PE_DS','PE_RS','PE_OC') ";
			String insertSql = "  insert into T_PY_PAY_PLAN_DETAIL(PAYPLAN_DETAIL_ID, COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,"
							+ " BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,WAGES_TYPE_CODE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,  "
							+ " PAY_MONTH_04,PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,   "
							+ " PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID,VERSIONNOTE)   "
							+ " values(Py_Pay_Plan_Detail_Seq.Nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";
			try {
				
		    conn.setAutoCommit(false);
			pstmtDelete =conn.prepareStatement(deleteSql);
			pstmtInsert =conn.prepareStatement(insertSql);
			pstmtDelete.setString(1,admin.getCpnyId());
			pstmtDelete.executeUpdate();			
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			while(result.next()){
				pstmtInsert.setString(1, admin.getCpnyId());
				pstmtInsert.setString(2,planYear);
				pstmtInsert.setString(3,baseYearMonth);
				pstmtInsert.setString(4, sqlId);
				pstmtInsert.setString(5, result.getString("DEPTID"));
				pstmtInsert.setString(6, result.getString("DEPTNAME"));
				pstmtInsert.setString(7, result.getString("JOIN_TYPE_CODE"));
				pstmtInsert.setString(8, result.getString("POST_GRADE_CODE"));
				pstmtInsert.setString(9, result.getString("WAGES_TYPE_CODE"));
				pstmtInsert.setString(10,result.getString("PAYTYPE"));
				pstmtInsert.setDouble(11, result.getDouble("PA01"));
				pstmtInsert.setDouble(12, result.getDouble("PA02"));
				pstmtInsert.setDouble(13, result.getDouble("PA03"));
				pstmtInsert.setDouble(14, result.getDouble("PA04"));
				pstmtInsert.setDouble(15, result.getDouble("PA05"));
				pstmtInsert.setDouble(16, result.getDouble("PA06"));
				pstmtInsert.setDouble(17, result.getDouble("PA07"));
				pstmtInsert.setDouble(18, result.getDouble("PA08"));
				pstmtInsert.setDouble(19, result.getDouble("PA09"));
				pstmtInsert.setDouble(20, result.getDouble("PA10"));
				pstmtInsert.setDouble(21, result.getDouble("PA11"));
				pstmtInsert.setDouble(22, result.getDouble("PA12"));				
				pstmtInsert.setDouble(23, result.getDouble("PA01")+result.getDouble("PA02")+result.getDouble("PA03")+result.getDouble("PA04")+result.getDouble("PA05")+result.getDouble("PA06")+result.getDouble("PA07")+result.getDouble("PA08")+result.getDouble("PA09")+result.getDouble("PA10")+result.getDouble("PA11")+result.getDouble("PA12"));
				pstmtInsert.setString(24,admin.getAdminID());
				pstmtInsert.setString(25,admin.getCpnyId());
				pstmtInsert.setString(26,request.getParameter("versionNote"));
				pstmtInsert.addBatch() ;
			} 
			pstmtInsert.executeBatch();
			conn.commit();
			}catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				try {
					pstmtDelete.close();
					pstmtInsert.close();
					result.close();
					pstmt.close();					
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
	   //把已经插进去的结果查询出来
		SimpleMap 	parameterObject =new SimpleMap();
		parameterObject.set("cpnyId",admin.getCpnyId());
		parameterObject.set("paItemStr","'PE_RG','PE_DA','PE_SG','PE_DS','PE_RS','PE_OC'");
        List list= paServices.payPlanSerche(parameterObject);
		request.setAttribute("planYear",request.getParameter("planYear"));
		request.setAttribute("datumYear",request.getParameter("datumYear"));
		request.setAttribute("startMonth",request.getParameter("datumStartMonth"));
		request.setAttribute("endMonth",request.getParameter("datumEndMonth"));
		request.setAttribute("versionNote", request.getParameter("versionNote"));
		request.setAttribute("list", list);
		request.setAttribute("search", "Yes");
			return "/pa_payPlan_02.jsp";
		
	 }
	public  String payPlan02Save(HttpServletRequest request,AdminBean admin) {
		String[]payParmName={"PE_RG","PE_DA","PE_SG","PE_DS","PE_RS","PE_OC"};	
		List commitData = new ArrayList();
		for(int i=0;i<payParmName.length;i++){
			SimpleMap 	parameterObject =new SimpleMap();
			parameterObject.set("cpnyId",admin.getCpnyId());
			parameterObject.set("paItem",payParmName[i]);  
			commitData.add(parameterObject);
		}
		
		paServices.payPlanSave(commitData);
		
		return "补贴推算数据保存成功！";
		
	}
	
	
	public String payPlan03Serche(HttpServletRequest request,AdminBean admin){
		List dataList = new ArrayList();
		ResultSet result = null;
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtInsert = null;
		String sql="";
		
		String monts[]={"0","01","02","03","04","05","06","07","08","09","10","11","12"};
		
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");	
		SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyyMM");	
		
		String sysYear = timeFormatter.format(new Date());
		String baseYearMonth=timeFormatter1.format(new Date());
		String sysDate=request.getParameter("datumYear")+"-01-01";
		String sysDate1=request.getParameter("datumYear")+"0101";
		String datumYear=request.getParameter("datumYear");
		String planYear =request.getParameter("planYear");
		
		int startMonth=NumberUtil.parseInt(request.getParameter("datumStartMonth"));
		int endMonth=NumberUtil.parseInt(request.getParameter("datumEndMonth"));
		
		int maxPayMonth=this.getPayMonthMax(datumYear,request.getParameter("datumStartMonth"),request.getParameter("datumEndMonth"),admin.getCpnyId());
		int minPayMonth=this.getPayMonthMin(datumYear,request.getParameter("datumStartMonth"),request.getParameter("datumEndMonth"),admin.getCpnyId());
		
		
		sql+=" select bg_gu, bg_nm,auth_group_type,pos_type,wages_type_code, grouptype, postype, wagestype,";
		
		
		for(int i=1;i<=12;i++){
			sql+=" decode(auth_group_type,'C_12009_1330065',"+NumberUtil.parseDouble(request.getParameter("BRate_"+i))+", decode(wages_type_code, 'C_20020_1', "+NumberUtil.parseDouble(request.getParameter("FRateAnnualsalary_"+i))+", "+NumberUtil.parseDouble(request.getParameter("FRateNoAnnualsalary_"+i))+")) rate"+monts[i]+",";	
			sql+=" base"+monts[i]+",";
			sql+=" round(decode(auth_group_type,'C_12009_1330065',base"+monts[i]+" * "+NumberUtil.parseDouble(request.getParameter("BRate_"+i))+", decode(wages_type_code, 'C_20020_1', base"+monts[i]+" * "+NumberUtil.parseDouble(request.getParameter("FRateAnnualsalary_"+i))+", base"+monts[i]+" * "+NumberUtil.parseDouble(request.getParameter("FRateNoAnnualsalary_"+i))+")),2) pa"+monts[i]+",";
		}	
		sql=sql.substring(0,sql.length()-1);
		sql+="  from (select t.bg_gu,t.bg_nm,t.auth_group_type, t.pos_type,t.wages_type_code, grouptype.code_name grouptype,postype.code_name postype,wagestype.code_name wagestype,";
			 
		for(int i=1;i<=12;i++){
				sql+=" sum(t.pay_month_"+monts[i]+") base"+monts[i]+",";					
		}	
		  sql=sql.substring(0,sql.length()-1);
		  sql+=   " from py_pay_plan_detail t, sy_code grouptype, sy_code  postype, sy_code wagestype"+
			      " where t.plan_yy = '"+planYear+"'"+
				  "   and t.seq_id = '"+request.getParameter("paVersion")+"'"+
				  "   and t.com_org_id = '"+admin.getCpnyId()+"'"+
				  "   and t.pay_type in ('PA', 'PE_RG')"+
				  "   and t.auth_group_type = grouptype.code_id"+
				  "   and t.pos_type = postype.code_id"+
				  "   and t.wages_type_code = wagestype.code_id"+
				  "  and t.auth_group_type = 'C_12009_1330065'"+
				  "  group by t.bg_gu, t.bg_nm, grouptype.code_name, postype.code_name, wagestype.code_name,t.auth_group_type, t.pos_type, t.wages_type_code"+
				  "  union "+
				  " select t.bg_gu, t.bg_nm,t.auth_group_type,t.pos_type,t.wages_type_code,grouptype.code_name grouptype, postype.code_name postype,wagestype.code_name wagestype,";
		  for(int i=1;i<=12;i++){
				sql+=" t.pay_month_"+monts[i]+" base"+monts[i]+",";					
	      }	
		  sql=sql.substring(0,sql.length()-1);		
		  sql+=   " from py_pay_plan_detail t, sy_code grouptype, sy_code  postype, sy_code wagestype"+
			      "   where t.plan_yy = '"+planYear+"'"+
				  "   and t.seq_id = '"+request.getParameter("paVersion")+"'"+
				  "   and t.com_org_id = '"+admin.getCpnyId()+"'"+
				  "   and t.pay_type = 'PA' "+
				  "   and t.auth_group_type = grouptype.code_id"+
				  "   and t.pos_type = postype.code_id"+
				  "   and t.wages_type_code = wagestype.code_id"+
				  "  and t.auth_group_type<>'C_12009_1330065') ";
			
			Logger.getLogger(getClass()).debug(sql);			
			String sqlId=this.getSequence(planYear, baseYearMonth, "PB",admin.getCpnyId());
			String deleteSql="delete from T_PY_PAY_PLAN_DETAIL T where t.COM_ORG_ID=? and t.PAY_TYPE='PB'";
			String insertSql = "  insert into T_PY_PAY_PLAN_DETAIL(PAYPLAN_DETAIL_ID, COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,"
							+ " BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,WAGES_TYPE_CODE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,  "
							+ " PAY_MONTH_04,PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,   "
							+ " PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID,VERSIONNOTE)   "
							+ " values(Py_Pay_Plan_Detail_Seq.Nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";
			try {
				
		    conn.setAutoCommit(false);
			pstmtDelete =conn.prepareStatement(deleteSql);
			pstmtInsert =conn.prepareStatement(insertSql);
			pstmtDelete.setString(1,admin.getCpnyId());
			pstmtDelete.executeUpdate();			
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();			
			while(result.next()){
				SimpleMap  sm = new SimpleMap();
				sm.set("BG_NM", result.getString("BG_NM"));
				sm.set("WAGESTYPE", result.getString("WAGESTYPE"));
				sm.set("GROUPTYPE", result.getString("GROUPTYPE"));
				sm.set("POSTYPE", result.getString("POSTYPE"));
				sm.set("PAYTYPE", "奖金");
				sm.set("PA1", result.getDouble("PA01"));
				sm.set("PA2", result.getDouble("PA02"));
				sm.set("PA3", result.getDouble("PA03"));
				sm.set("PA4", result.getDouble("PA04"));
				sm.set("PA5", result.getDouble("PA05"));
				sm.set("PA6", result.getDouble("PA06"));
				sm.set("PA7", result.getDouble("PA07"));
				sm.set("PA8", result.getDouble("PA08"));
				sm.set("PA9", result.getDouble("PA09"));
				sm.set("PA10", result.getDouble("PA10"));
				sm.set("PA11", result.getDouble("PA11"));
				sm.set("PA12", result.getDouble("PA12"));
				
				sm.set("BASE1", result.getDouble("BASE01"));
				sm.set("BASE2", result.getDouble("BASE02"));
				sm.set("BASE3", result.getDouble("BASE03"));
				sm.set("BASE4", result.getDouble("BASE04"));
				sm.set("BASE5", result.getDouble("BASE05"));
				sm.set("BASE6", result.getDouble("BASE06"));
				sm.set("BASE7", result.getDouble("BASE07"));
				sm.set("BASE8", result.getDouble("BASE08"));
				sm.set("BASE9", result.getDouble("BASE09"));
				sm.set("BASE10", result.getDouble("BASE10"));
				sm.set("BASE11", result.getDouble("BASE11"));
				sm.set("BASE12", result.getDouble("BASE12"));	
				
				sm.set("RATE1", result.getDouble("RATE01"));
				sm.set("RATE2", result.getDouble("RATE02"));
				sm.set("RATE3", result.getDouble("RATE03"));
				sm.set("RATE4", result.getDouble("RATE04"));
				sm.set("RATE5", result.getDouble("RATE05"));
				sm.set("RATE6", result.getDouble("RATE06"));
				sm.set("RATE7", result.getDouble("RATE07"));
				sm.set("RATE8", result.getDouble("RATE08"));
				sm.set("RATE9", result.getDouble("RATE09"));
				sm.set("RATE10", result.getDouble("RATE10"));
				sm.set("RATE11", result.getDouble("RATE11"));
				sm.set("RATE12", result.getDouble("RATE12"));	
				dataList.add(sm);
				pstmtInsert.setString(1, admin.getCpnyId());
				pstmtInsert.setString(2,planYear);
				pstmtInsert.setString(3,baseYearMonth);
				pstmtInsert.setString(4, sqlId);
				pstmtInsert.setString(5, result.getString("BG_GU"));
				pstmtInsert.setString(6, result.getString("BG_NM"));
				pstmtInsert.setString(7, result.getString("AUTH_GROUP_TYPE"));
				pstmtInsert.setString(8, result.getString("POS_TYPE"));
				pstmtInsert.setString(9, result.getString("WAGES_TYPE_CODE"));
				pstmtInsert.setString(10,"PB");
				pstmtInsert.setDouble(11, result.getDouble("PA01"));
				pstmtInsert.setDouble(12, result.getDouble("PA02"));
				pstmtInsert.setDouble(13, result.getDouble("PA03"));
				pstmtInsert.setDouble(14, result.getDouble("PA04"));
				pstmtInsert.setDouble(15, result.getDouble("PA05"));
				pstmtInsert.setDouble(16, result.getDouble("PA06"));
				pstmtInsert.setDouble(17, result.getDouble("PA07"));
				pstmtInsert.setDouble(18, result.getDouble("PA08"));
				pstmtInsert.setDouble(19, result.getDouble("PA09"));
				pstmtInsert.setDouble(20, result.getDouble("PA10"));
				pstmtInsert.setDouble(21, result.getDouble("PA11"));
				pstmtInsert.setDouble(22, result.getDouble("PA12"));				
				pstmtInsert.setDouble(23, result.getDouble("PA01")+result.getDouble("PA02")+result.getDouble("PA03")+result.getDouble("PA04")+result.getDouble("PA05")+result.getDouble("PA06")+result.getDouble("PA07")+result.getDouble("PA08")+result.getDouble("PA09")+result.getDouble("PA10")+result.getDouble("PA11")+result.getDouble("PA12"));
				pstmtInsert.setString(24,admin.getAdminID());
				pstmtInsert.setString(25,admin.getCpnyId());
				pstmtInsert.setString(26,request.getParameter("versionNote"));
				pstmtInsert.addBatch() ;
			} 
			pstmtInsert.executeBatch();
			conn.commit();
			}catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				try {
					pstmtDelete.close();
					pstmtInsert.close();
					result.close();
					pstmt.close();					
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
	   //把已经插进去的结果查询出来
	    List FRateAnnualsalaryList = new ArrayList();
	    List FRateNoAnnualsalaryList = new ArrayList();
	    List BRateList = new ArrayList();
	    for(int i=1;i<=12;i++){
	    SimpleMap sm1= new SimpleMap ();	
	    SimpleMap sm2= new SimpleMap ();	
	    SimpleMap sm3= new SimpleMap ();	
	    sm1.set("rate", request.getParameter("FRateAnnualsalary_"+i));
	    sm2.set("rate", request.getParameter("FRateNoAnnualsalary_"+i));
	    sm3.set("rate", request.getParameter("BRate_"+i));
	    FRateAnnualsalaryList.add(sm1);	
	    FRateNoAnnualsalaryList.add(sm2);
	    BRateList.add(sm3);
	    }
	
		request.setAttribute("planYear",request.getParameter("planYear"));
		request.setAttribute("datumYear",request.getParameter("datumYear"));
		request.setAttribute("startMonth",request.getParameter("datumStartMonth"));
		request.setAttribute("endMonth",request.getParameter("datumEndMonth"));
		request.setAttribute("versionNote", request.getParameter("versionNote"));
		request.setAttribute("paVersionStr", request.getParameter("paVersion"));
		request.setAttribute("peVersionStr", request.getParameter("peVersion"));
		request.setAttribute("list", dataList);		
		request.setAttribute("FRateAnnualsalaryList", FRateAnnualsalaryList);	
		request.setAttribute("FRateNoAnnualsalaryList", FRateNoAnnualsalaryList);	
		request.setAttribute("BRateList", BRateList);	
		request.setAttribute("search", "Yes");
		return "/pa_payPlan_03.jsp";
		
	 }
	public  String payPlan03Save(HttpServletRequest request,AdminBean admin) {		
		List commitData = new ArrayList();
		
		SimpleMap 	parameterObject =new SimpleMap();
		parameterObject.set("cpnyId",admin.getCpnyId());
		parameterObject.set("paItem","PB");  
		commitData.add(parameterObject);		
		
		paServices.payPlanSave(commitData);
		
		return "奖金推算数据保存成功！";
		
	}
	
	public String payPlan04Serche(HttpServletRequest request,AdminBean admin){
		List dataList = new ArrayList();
		ResultSet result = null;
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtInsert = null;
		String sql="";
		
		String monts[]={"0","01","02","03","04","05","06","07","08","09","10","11","12"};
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");	
		SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyyMM");	
		
		String sysYear = timeFormatter.format(new Date());
		String baseYearMonth=timeFormatter1.format(new Date());
		
		String sysDate=request.getParameter("datumYear")+"-01-01";
		String datumYear=request.getParameter("datumYear");
		String planYear =request.getParameter("planYear");
		
		int maxPayMonth=this.getPayMonthMax(datumYear,request.getParameter("datumStartMonth"),request.getParameter("datumEndMonth"),admin.getCpnyId());
		int minPayMonth=this.getPayMonthMin(datumYear,request.getParameter("datumStartMonth"),request.getParameter("datumEndMonth"),admin.getCpnyId());
			
		
		sql+=" select info.deptid,info.deptname,info.join_type_code,info.post_grade_code,info.wages_type_code,get_code_name(info.join_type_code) GROUPTYPE, get_code_name(info.post_grade_code)POSTYPE , get_code_name(info.wages_type_code) WAGESTYPE,";
		
		if(planYear.equals(sysYear)){//做本年度的工资计划
					
					for(int i=1;i<minPayMonth;i++){//从1月到最小月取得平均工资	
						sql+=" round(get_pamonth_base('"+monts[i]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code,'"+planYear+"','"+request.getParameter("paVersion")+"','"+planYear+"','"+request.getParameter("peVersion")+"')/21.75/8,2) as base"+monts[i]+",";
						sql+="  get_pa_average('TOTAL_WEEKDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*1.5+ get_pa_average('TOTAL_WEEKEND_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*2+get_pa_average('TOTAL_HOLIDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*3 as rate"+monts[i]+",";
						sql+=" round(get_pamonth_base('"+monts[i]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code,'"+planYear+"','"+request.getParameter("paVersion")+"','"+planYear+"','"+request.getParameter("peVersion")+"')/21.75/8,2)*(get_pa_average('TOTAL_WEEKDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*1.5+ get_pa_average('TOTAL_WEEKEND_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*2+get_pa_average('TOTAL_HOLIDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*3)  as pa"+monts[i]+",";
					}
				    for(int i=minPayMonth;i<=maxPayMonth;i++){//取最小工资月和工资最大月里面的实际数据
				       sql+=" '' as  base"+monts[i]+",";
				       sql+=" '' as  rate"+monts[i]+",";
				       sql+=" get_paMonth_average('PERFORMANCE_PAY2','"+datumYear+monts[i]+"', info.deptid, info.join_type_code, info.post_grade_code, info.wages_type_code) +  get_paMonth_average('PERFORMANCE_PAY3','"+datumYear+monts[i]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code) as pa"+monts[i]+",";   	
				    	
				    }				  
				   
				    for(int i=(maxPayMonth+1);i<=12;i++){//取最大工资月到12月之间的平均工资数据
				    	sql+=" round(get_pamonth_base('"+monts[i]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code,'"+planYear+"','"+request.getParameter("paVersion")+"','"+planYear+"','"+request.getParameter("peVersion")+"')/21.75/8,2) as base"+monts[i]+",";
				    	sql+="  get_pa_average('TOTAL_WEEKDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*1.5+ get_pa_average('TOTAL_WEEKEND_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*2+get_pa_average('TOTAL_HOLIDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*3 as rate"+monts[i]+",";
						sql+=" round(get_pamonth_base('"+monts[i]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code,'"+planYear+"','"+request.getParameter("paVersion")+"','"+planYear+"','"+request.getParameter("peVersion")+"')/21.75/8,2)*(get_pa_average('TOTAL_WEEKDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*1.5+ get_pa_average('TOTAL_WEEKEND_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*2+get_pa_average('TOTAL_HOLIDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*3)  as pa"+monts[i]+",";
				    }
	   
		}else{//做下年度的工资计划
			
			for(int i=1;i<=12;i++){
				sql+=" round(get_pamonth_base('"+monts[i]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code,'"+planYear+"','"+request.getParameter("paVersion")+"','"+planYear+"','"+request.getParameter("peVersion")+"')/21.75/8,2) as base"+monts[i]+",";	
				sql+="  get_pa_average('TOTAL_WEEKDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*1.5+ get_pa_average('TOTAL_WEEKEND_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*2+get_pa_average('TOTAL_HOLIDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*3 as rate"+monts[i]+",";
				sql+=" round(get_pamonth_base('"+monts[i]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code,'"+planYear+"','"+request.getParameter("paVersion")+"','"+planYear+"','"+request.getParameter("peVersion")+"')/21.75/8,2)*(get_pa_average('TOTAL_WEEKDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*1.5+ get_pa_average('TOTAL_WEEKEND_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*2+get_pa_average('TOTAL_HOLIDAY_OT','"+datumYear+monts[minPayMonth]+"','"+datumYear+monts[maxPayMonth]+"',info.deptid,info.join_type_code,info.post_grade_code, info.wages_type_code)*3)  as pa"+monts[i]+",";
			}
		    
			
		}
		 sql=sql.substring(0,sql.length()-1);
	    sql +=    " from (select distinct hr_get_level_deptid(hr.deptid, '3') deptid, "
				+ " hr_get_level_deptname(hr.deptid, '3') deptname,"
				+ " hr.join_type_code,"
				+ " hr.post_grade_code,"
				+ " hr.wages_type_code"
				+ " from hr_employee hr"
				+ " where hr.cpny_id = '"+admin.getCpnyId()+"'"
				+ " and hr.join_type_code is not null"
				+ " and hr.post_grade_code is not null"
				+ " and hr_get_level_deptid(hr.deptid, '3') is not null"
				+ " and (hr.date_left is null or hr.date_left>to_date('"+sysDate+"','YYYY-MM-DD'))"
				+ " and hr.join_type_code <> 'C_12009_1330064'"
				+ " and hr.join_type_code <>'C_12009_1355628'"
				+ " and hr.join_type_code <>'C_12009_1330063' ) info ";
				
			
			
		
			
			Logger.getLogger(getClass()).debug(sql);			
			String sqlId=this.getSequence(planYear, baseYearMonth, "PC",admin.getCpnyId());
			String deleteSql="delete from T_PY_PAY_PLAN_DETAIL T where t.COM_ORG_ID=? and t.PAY_TYPE='PA' ";
			String insertSql = "  insert into T_PY_PAY_PLAN_DETAIL(PAYPLAN_DETAIL_ID, COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,"
							+ " BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,WAGES_TYPE_CODE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,  "
							+ " PAY_MONTH_04,PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,   "
							+ " PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID,VERSIONNOTE)   "
							+ " values(Py_Pay_Plan_Detail_Seq.Nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";
			try {
				
				 conn.setAutoCommit(false);
					pstmtDelete =conn.prepareStatement(deleteSql);
					pstmtInsert =conn.prepareStatement(insertSql);
					pstmtDelete.setString(1,admin.getCpnyId());
					pstmtDelete.executeUpdate();			
					pstmt = conn.prepareStatement(sql);
					result=pstmt.executeQuery();			
					while(result.next()){
						SimpleMap  sm = new SimpleMap();
						sm.set("BG_NM", result.getString("DEPTNAME"));
						sm.set("WAGESTYPE", result.getString("WAGESTYPE"));
						sm.set("GROUPTYPE", result.getString("GROUPTYPE"));
						sm.set("POSTYPE", result.getString("POSTYPE"));
						sm.set("PAYTYPE", "加班费");
						sm.set("PA1", result.getDouble("PA01"));
						sm.set("PA2", result.getDouble("PA02"));
						sm.set("PA3", result.getDouble("PA03"));
						sm.set("PA4", result.getDouble("PA04"));
						sm.set("PA5", result.getDouble("PA05"));
						sm.set("PA6", result.getDouble("PA06"));
						sm.set("PA7", result.getDouble("PA07"));
						sm.set("PA8", result.getDouble("PA08"));
						sm.set("PA9", result.getDouble("PA09"));
						sm.set("PA10", result.getDouble("PA10"));
						sm.set("PA11", result.getDouble("PA11"));
						sm.set("PA12", result.getDouble("PA12"));
						
						sm.set("BASE1", result.getDouble("BASE01"));
						sm.set("BASE2", result.getDouble("BASE02"));
						sm.set("BASE3", result.getDouble("BASE03"));
						sm.set("BASE4", result.getDouble("BASE04"));
						sm.set("BASE5", result.getDouble("BASE05"));
						sm.set("BASE6", result.getDouble("BASE06"));
						sm.set("BASE7", result.getDouble("BASE07"));
						sm.set("BASE8", result.getDouble("BASE08"));
						sm.set("BASE9", result.getDouble("BASE09"));
						sm.set("BASE10", result.getDouble("BASE10"));
						sm.set("BASE11", result.getDouble("BASE11"));
						sm.set("BASE12", result.getDouble("BASE12"));	
						
						sm.set("RATE1", result.getDouble("RATE01"));
						sm.set("RATE2", result.getDouble("RATE02"));
						sm.set("RATE3", result.getDouble("RATE03"));
						sm.set("RATE4", result.getDouble("RATE04"));
						sm.set("RATE5", result.getDouble("RATE05"));
						sm.set("RATE6", result.getDouble("RATE06"));
						sm.set("RATE7", result.getDouble("RATE07"));
						sm.set("RATE8", result.getDouble("RATE08"));
						sm.set("RATE9", result.getDouble("RATE09"));
						sm.set("RATE10", result.getDouble("RATE10"));
						sm.set("RATE11", result.getDouble("RATE11"));
						sm.set("RATE12", result.getDouble("RATE12"));	
						dataList.add(sm);
						pstmtInsert.setString(1, admin.getCpnyId());
						pstmtInsert.setString(2,planYear);
						pstmtInsert.setString(3,baseYearMonth);
						pstmtInsert.setString(4, sqlId);
						pstmtInsert.setString(5, result.getString("DEPTID"));
						pstmtInsert.setString(6, result.getString("DEPTNAME"));
						pstmtInsert.setString(7, result.getString("JOIN_TYPE_CODE"));
						pstmtInsert.setString(8, result.getString("POST_GRADE_CODE"));
						pstmtInsert.setString(9, result.getString("WAGES_TYPE_CODE"));
						pstmtInsert.setString(10,"PC");
						pstmtInsert.setDouble(11, result.getDouble("PA01"));
						pstmtInsert.setDouble(12, result.getDouble("PA02"));
						pstmtInsert.setDouble(13, result.getDouble("PA03"));
						pstmtInsert.setDouble(14, result.getDouble("PA04"));
						pstmtInsert.setDouble(15, result.getDouble("PA05"));
						pstmtInsert.setDouble(16, result.getDouble("PA06"));
						pstmtInsert.setDouble(17, result.getDouble("PA07"));
						pstmtInsert.setDouble(18, result.getDouble("PA08"));
						pstmtInsert.setDouble(19, result.getDouble("PA09"));
						pstmtInsert.setDouble(20, result.getDouble("PA10"));
						pstmtInsert.setDouble(21, result.getDouble("PA11"));
						pstmtInsert.setDouble(22, result.getDouble("PA12"));				
						pstmtInsert.setDouble(23, result.getDouble("PA01")+result.getDouble("PA02")+result.getDouble("PA03")+result.getDouble("PA04")+result.getDouble("PA05")+result.getDouble("PA06")+result.getDouble("PA07")+result.getDouble("PA08")+result.getDouble("PA09")+result.getDouble("PA10")+result.getDouble("PA11")+result.getDouble("PA12"));
						pstmtInsert.setString(24,admin.getAdminID());
						pstmtInsert.setString(25,admin.getCpnyId());
						pstmtInsert.setString(26,request.getParameter("versionNote"));
						pstmtInsert.addBatch() ;
					} 
					pstmtInsert.executeBatch();
					conn.commit();
					}catch (Exception e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
						e.printStackTrace();
						Logger.getLogger(getClass()).debug(e.toString());
					} finally {
						try {
							pstmtDelete.close();
							pstmtInsert.close();
							result.close();
							pstmt.close();					
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
			   //把已经插进去的结果查询出来			
				request.setAttribute("planYear",request.getParameter("planYear"));
				request.setAttribute("datumYear",request.getParameter("datumYear"));
				request.setAttribute("startMonth",request.getParameter("datumStartMonth"));
				request.setAttribute("endMonth",request.getParameter("datumEndMonth"));
				request.setAttribute("versionNote", request.getParameter("versionNote"));
				request.setAttribute("paVersionStr", request.getParameter("paVersion"));
				request.setAttribute("peVersionStr", request.getParameter("peVersion"));
				request.setAttribute("list", dataList);			
				request.setAttribute("search", "Yes");
		return "/pa_payPlan_04.jsp";
		
	 }
	public  String payPlan04Save(HttpServletRequest request,AdminBean admin) {		
		List commitData = new ArrayList();
		
		SimpleMap 	parameterObject =new SimpleMap();
		parameterObject.set("cpnyId",admin.getCpnyId());
		parameterObject.set("paItem","PC");  
		commitData.add(parameterObject);		
		
		paServices.payPlanSave(commitData);
		
		return "加班费推算数据保存成功！";
		
	}
	
	
	public  int ExitsPersonPlan(HttpServletRequest request){
		int flag=0;
		PreparedStatement pstmt = null;
		Connection conn = ConnBean.getConn();
		ResultSet rs=null;		
        String sql = "select * from T_PY_PERSON_PLAN t where t.PLAN_YY='"+request.getParameter("planYear")+"' and substr(t.PLAN_BASE_YMD,1,6)='"+request.getParameter("baseMonth")+"'";
        try {
        	pstmt = conn.prepareStatement(sql);
        	rs=pstmt.executeQuery();
        	if(rs.next()){        	
        		flag=1;
        	}
           
       
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs,pstmt, conn);
        }
		return flag;
		
	}
	public String GenerationPayPlanCC(HttpServletRequest request,AdminBean admin){
		
		List dataList = new ArrayList();
		ResultSet result = null;
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtInsert = null;
		String sql="";
		String sumSql="";
		String monts[]={"0","01","02","03","04","05","06","07","08","09","10","11","12"};		
		String planYear =request.getParameter("planYear");
		String baseMonth=request.getParameter("baseMonth");
		String planMonth=request.getParameter("planMonth");
		String paVersion=request.getParameter("paVersion");	
		String pbVersion=request.getParameter("pbVersion");
		String pcVersion=request.getParameter("pcVersion");
		String peVersion=request.getParameter("peVersion");		
		
		sql+=" SELECT  PEDATA.bg_gu, PEDATA.auth_group_type,  PEDATA.pos_type, PEDATA.PAY_TYPE,";
		
					
		for(int i=1;i<=12;i++){
		 sql+=" SUM(PEDATA.pay_month_"+monts[i]+")/DECODE(SUM(DECODE(PEDATA.PAY_MONTH_"+monts[i]+",0,0,1)),0,1,SUM(DECODE(PEDATA.PAY_MONTH_"+monts[i]+",0,0,1))) AS pay_month_"+monts[i]+",";
		}
		sql=sql.substring(0,sql.length()-1);
		sql+=" FROM (select t.bg_gu,t.auth_group_type, t.pos_type,'PE' AS PAY_TYPE,T.WAGES_TYPE_CODE,";
		for(int i=1;i<=12;i++){
		 sql+=" SUM(t.pay_month_"+monts[i]+") AS pay_month_"+monts[i]+",";			
		}
		sql=sql.substring(0,sql.length()-1);
		sql+="   from py_pay_plan_detail t where t.pay_type  like '%PE%'  AND T.PLAN_YY = '"+planYear+"'  AND T.BASE_YYMM = '"+baseMonth+"'   AND T.SEQ_ID = '"+peVersion+"' GROUP  BY t.bg_gu, t.auth_group_type, t.pos_type,T.WAGES_TYPE_CODE "+
              " ) PEDATA GROUP  BY PEDATA.bg_gu, PEDATA.auth_group_type, PEDATA.pos_type,PEDATA.PAY_TYPE union all ";
		
		sql+=" select t.bg_gu, t.auth_group_type, t.pos_type, T.PAY_TYPE,";
		for(int i=1;i<=12;i++){
			 sql+=" SUM(t.pay_month_"+monts[i]+")/DECODE(SUM(DECODE(t.PAY_MONTH_"+monts[i]+",0,0,1)),0,1,SUM(DECODE(t.PAY_MONTH_"+monts[i]+",0,0,1))) AS pay_month_"+monts[i]+",";
		}
		sql=sql.substring(0,sql.length()-1);
		sql+="  from py_pay_plan_detail t  where t.pay_type ='PA'  AND T.PLAN_YY = '"+planYear+"'   AND T.BASE_YYMM = '"+baseMonth+"'  AND T.SEQ_ID = '"+paVersion+"' GROUP  BY t.bg_gu, t.auth_group_type, t.pos_type,T.PAY_TYPE union all ";
		
		sql+=" select t.bg_gu, t.auth_group_type, t.pos_type, T.PAY_TYPE,";
		for(int i=1;i<=12;i++){
			 sql+=" SUM(t.pay_month_"+monts[i]+")/DECODE(SUM(DECODE(t.PAY_MONTH_"+monts[i]+",0,0,1)),0,1,SUM(DECODE(t.PAY_MONTH_"+monts[i]+",0,0,1))) AS pay_month_"+monts[i]+",";
		}
		sql=sql.substring(0,sql.length()-1);
		sql+="  from py_pay_plan_detail t  where t.pay_type ='PB'  AND T.PLAN_YY = '"+planYear+"'   AND T.BASE_YYMM = '"+baseMonth+"'  AND T.SEQ_ID = '"+pbVersion+"' GROUP  BY t.bg_gu, t.auth_group_type, t.pos_type,T.PAY_TYPE union all ";
		sql+=" select t.bg_gu, t.auth_group_type, t.pos_type, T.PAY_TYPE,";
		for(int i=1;i<=12;i++){
			 sql+=" SUM(t.pay_month_"+monts[i]+")/DECODE(SUM(DECODE(t.PAY_MONTH_"+monts[i]+",0,0,1)),0,1,SUM(DECODE(t.PAY_MONTH_"+monts[i]+",0,0,1))) AS pay_month_"+monts[i]+",";
		}
		sql=sql.substring(0,sql.length()-1);
		sql+="  from py_pay_plan_detail t  where t.pay_type ='PC'  AND T.PLAN_YY = '"+planYear+"'   AND T.BASE_YYMM = '"+baseMonth+"'  AND T.SEQ_ID = '"+pcVersion+"' GROUP  BY t.bg_gu, t.auth_group_type, t.pos_type,T.PAY_TYPE ";
		Logger.getLogger(getClass()).debug("sql = " + sql);	
		
		sumSql+=" SELECT INFO.BG_GU, INFO.BG_NM, INFO.ORG_ID, INFO.ORG_FULL_NM, INFO.GROUP_TYPE, INFO.PAY_TYPE,";
		for(int i=1;i<=12;i++){
			sumSql+=" SUM(INFO.PA"+monts[i]+") AS PA"+monts[i]+",";
		}
		sumSql=sumSql.substring(0,sumSql.length()-1);
		sumSql+=" FROM ( SELECT PERSONPLAN.BG_GU, PERSONPLAN.BG_NM, PERSONPLAN.ORG_ID, PERSONPLAN.ORG_FULL_NM, PERSONPLAN.GROUP_TYPE, PERSONPLAN.POS_TYPE,  PAPLAN.PAY_TYPE,";
		for(int i=1;i<=12;i++){
			sumSql+=" PAPLAN.pay_month_"+monts[i]+" * PERSONPLAN.INWON_MONTH_"+monts[i]+" PA"+monts[i]+",";
		}
		sumSql=sumSql.substring(0,sumSql.length()-1);
		sumSql +="  FROM ( "+sql +" )  PAPLAN,";
		sumSql += "  (select * from t_py_person_plan t "
				+ " WHERE T.PLAN_YY = '"+planYear+"'"
				+ "   AND SUBSTR(T.PLAN_BASE_YMD, 0, 6) = '"+planMonth+"' "
				+ "    AND T.COM_ORG_ID = '"+admin.getCpnyId()+"') PERSONPLAN"
				+ " WHERE PAPLAN.BG_GU = PERSONPLAN.BG_GU"
				+ "  AND PAPLAN.auth_group_type = CONCAT('C_12009_', PERSONPLAN.GROUP_TYPE)"
				+ "  AND PAPLAN.pos_type = CONCAT('C_12004_', PERSONPLAN.POS_TYPE))INFO"
				+ " GROUP BY INFO.BG_GU," + "   INFO.BG_NM," + "  INFO.ORG_ID,"
				+ "  INFO.ORG_FULL_NM," + " INFO.GROUP_TYPE,"
				+ " INFO.PAY_TYPE";
		Logger.getLogger(getClass()).debug("sumSql = " + sumSql);				
			
		  String sqlId=this.getResultSequence(planYear, baseMonth, admin.getCpnyId());
			String deleteSql="delete from py_pay_plan_detail_cc T where t.COM_ORG_ID=? and  t.plan_yy=?  and  t.base_yymm =? ";
			String insertSql = "  insert into py_pay_plan_detail_cc(PAYPLAN_DETAIL_CC_ID, COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,"
							+ " BG_GU,BG_NM,ORG_ID,ORG_FULL_NM,AUTH_GROUP_TYPE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,  "
							+ " PAY_MONTH_04,PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,   "
							+ " PAY_MONTH_12,PAY_TOT_MON,MOD_USER_ID,MOD_DATE,MOD_LOC_ID)   "
							+ " values(py_pay_plan_detail_cc_seq.Nextval,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?)";
			try {
				
				 conn.setAutoCommit(false);
					pstmtDelete =conn.prepareStatement(deleteSql);
					pstmtInsert =conn.prepareStatement(insertSql);
					pstmtDelete.setString(1,admin.getCpnyId());
					pstmtDelete.setString(2,planYear);
					pstmtDelete.setString(3,baseMonth);
					pstmtDelete.executeUpdate();			
					pstmt = conn.prepareStatement(sumSql);
					result=pstmt.executeQuery();			
					while(result.next()){
						pstmtInsert.setString(1, admin.getCpnyId());
						pstmtInsert.setString(2,planYear);
						pstmtInsert.setString(3,baseMonth);
						pstmtInsert.setString(4, sqlId);
						pstmtInsert.setString(5, result.getString("BG_GU"));
						pstmtInsert.setString(6, result.getString("BG_NM"));
						pstmtInsert.setString(7, result.getString("ORG_ID"));
						pstmtInsert.setString(8, result.getString("ORG_FULL_NM"));
						pstmtInsert.setString(9, result.getString("GROUP_TYPE"));
						pstmtInsert.setString(10,result.getString("PAY_TYPE"));
						pstmtInsert.setDouble(11, result.getDouble("PA01"));
						pstmtInsert.setDouble(12, result.getDouble("PA02"));
						pstmtInsert.setDouble(13, result.getDouble("PA03"));
						pstmtInsert.setDouble(14, result.getDouble("PA04"));
						pstmtInsert.setDouble(15, result.getDouble("PA05"));
						pstmtInsert.setDouble(16, result.getDouble("PA06"));
						pstmtInsert.setDouble(17, result.getDouble("PA07"));
						pstmtInsert.setDouble(18, result.getDouble("PA08"));
						pstmtInsert.setDouble(19, result.getDouble("PA09"));
						pstmtInsert.setDouble(20, result.getDouble("PA10"));
						pstmtInsert.setDouble(21, result.getDouble("PA11"));
						pstmtInsert.setDouble(22, result.getDouble("PA12"));				
						pstmtInsert.setDouble(23, result.getDouble("PA01")+result.getDouble("PA02")+result.getDouble("PA03")+result.getDouble("PA04")+result.getDouble("PA05")+result.getDouble("PA06")+result.getDouble("PA07")+result.getDouble("PA08")+result.getDouble("PA09")+result.getDouble("PA10")+result.getDouble("PA11")+result.getDouble("PA12"));
						pstmtInsert.setString(24,admin.getAdminID());
						pstmtInsert.setString(25,admin.getCpnyId());
						pstmtInsert.addBatch() ;
					} 
					pstmtInsert.executeBatch();
					conn.commit();
					}catch (Exception e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
						e.printStackTrace();
						Logger.getLogger(getClass()).debug(e.toString());
					} finally {
						try {
							pstmtDelete.close();
							pstmtInsert.close();
							result.close();
							pstmt.close();					
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		return "/pa_payPlan_05.jsp";	
		
		
	}
	/*得到工资的最大月份*/
	private int getPayMonthMax(String thisyear,String startMonth,String endMonth,String cpny_id){
		ResultSet result = null;
		int maxMonth=0;
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql = " select to_number(nvl(substr(nvl(max(t.pa_month),0),5,2),0)) as maxmonth from pa_history t "+
                     " where t.pa_month between '"+thisyear+startMonth+"' and '"+thisyear+endMonth+"'  and t.cpny_id='"+cpny_id+"' ";
		Logger.getLogger(getClass()).debug(sql);
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			if(result.next()){
				maxMonth=result.getInt("maxmonth");
			}
			
		
		} catch (Exception e) {
	
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(result, pstmt, conn);
		}
		
		return maxMonth;
		
	}
	/*得到工资的最小月份*/
	private int getPayMonthMin(String thisyear,String startMonth,String endMonth,String cpny_id){
		ResultSet result = null;
		int minMonth=0;
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql = " select to_number(nvl(substr(nvl(min(t.pa_month),0),5,2),0)) as minMonth from pa_history t "+
                     " where t.pa_month between '"+thisyear+startMonth+"' and '"+thisyear+endMonth+"'  and t.cpny_id='"+cpny_id+"' ";
		Logger.getLogger(getClass()).debug(sql);
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			if(result.next()){
				minMonth=result.getInt("minMonth");
			}
			
		
		} catch (Exception e) {
	
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(result, pstmt, conn);
		}
		
		return minMonth;
		
	}
	/*得到推算表序列号*/
	private String getSequence(String plan_yy,String base_yymm,String pay_type,String cpnyId) {
		String  result = "";
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select  case  when (to_number(nvl(max(t.seq_id),0))+1)<10 then '0'||to_char((to_number(nvl(max(t.seq_id),0))+1))  else to_char((to_number(nvl(max(t.seq_id),0))+1)) end  as nexseq from py_pay_plan_detail t where t.plan_yy='"+plan_yy+"' and t.base_yymm='"+base_yymm+"' and t.pay_type='"+pay_type+"' and t.com_org_id='"+cpnyId+"'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得版本号时出错！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
	}
	/*得到结果表表序列号*/
	private String getResultSequence(String plan_yy,String base_yymm,String cpnyId) {
		String  result = "";
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select  case  when (to_number(nvl(max(t.seq_id),0))+1)<10 then '0'||to_char((to_number(nvl(max(t.seq_id),0))+1))  else to_char((to_number(nvl(max(t.seq_id),0))+1)) end  as nexseq from py_pay_plan_detail_cc t where t.plan_yy='"+plan_yy+"' and t.base_yymm='"+base_yymm+"'  and t.com_org_id='"+cpnyId+"'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得版本号时出错！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
	}

	public List getVesionNote(String pay_type,String cpnyId) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select distinct t.seq_id, t.plan_yy||'年  '||t.versionnote as versionnote from py_pay_plan_detail t where  t.pay_type = '"+pay_type+"'  and t.com_org_id='"+cpnyId+"' ";
		Logger.getLogger(getClass()).debug(sql);

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				SimpleMap sm = new SimpleMap();
				sm.set("seqid", rs.getString("seq_id"));
				sm.set("versionnote", rs.getString("versionnote"));
				list.add(sm);
			
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("得到版本信息时出错！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return list;
	}
	public List getBaseVesion(String pay_type,String cpnyId,String baseMonth,String planYear) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select distinct t.seq_id, t.plan_yy||'年  '||t.versionnote as versionnote from py_pay_plan_detail t where  t.pay_type = '"+pay_type+"'  and t.com_org_id='"+cpnyId+"' and t.base_yymm='"+baseMonth+"' and t.plan_yy='"+planYear+"' ";
		Logger.getLogger(getClass()).debug(sql);

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				SimpleMap sm = new SimpleMap();
				sm.set("seqid", rs.getString("seq_id"));
				sm.set("versionnote", rs.getString("versionnote"));
				list.add(sm);
			
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("得到版本信息时出错！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return list;
	}
	
	public List getBaseMonth(String cpnyId) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select distinct t.base_yymm  yyymm from py_pay_plan_detail t where t.com_org_id='"+cpnyId+"' order by t.base_yymm desc ";
		Logger.getLogger(getClass()).debug(sql);

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				SimpleMap sm = new SimpleMap();
				sm.set("YYYMM", rs.getString("YYYMM"));				
				list.add(sm);
			
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出计划工资月时出错！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return list;
	}
	public List getPersonPlanMonth(String cpnyId,String planYear) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select distinct substr(t.plan_base_ymd,0,6) YYYMM from t_py_person_plan t where t.com_org_id = '"+cpnyId+"'  and t.plan_yy='"+planYear+"' ";
		Logger.getLogger(getClass()).debug(sql);

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				SimpleMap sm = new SimpleMap();
				sm.set("YYYMM", rs.getString("YYYMM"));				
				list.add(sm);
			
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出计划工资月时出错！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return list;
	}
	
	 public String  ImportExcel(HttpServletRequest request,AdminBean admin) throws ServiceLocatorException {
		    List list = new ArrayList();
		    SimpleMap 	parameterObject =new SimpleMap();
			parameterObject.set("cpnyId",admin.getCpnyId());
			parameterObject.set("planYear", request.getParameter("planYear"));
			parameterObject.set("baseMonth", request.getParameter("baseMonth"));    
		    String paType= request.getParameter("paType");
		    if(paType.equals("PA")){
		    	 parameterObject.set("seqId", request.getParameter("paVersion"));
		    	 parameterObject.set("paItemStr","'PA'");
		    	 list= paServices.ImportExcelProcess(parameterObject);
		    }else if(paType.equals("PB")){
		    	 parameterObject.set("seqId", request.getParameter("pbVersion"));
		    	 parameterObject.set("paItemStr","'PB'");
		    	 list= paServices.ImportExcelProcess(parameterObject);
		    }else if(paType.equals("PC")){
		    	 parameterObject.set("seqId", request.getParameter("pcVersion"));
		    	 parameterObject.set("paItemStr","'PC'");
		    	 list= paServices.ImportExcelProcess(parameterObject);
		    }else if(paType.equals("PE")){
		    	 parameterObject.set("seqId", request.getParameter("peVersion"));
		    	 parameterObject.set("paItemStr","'PE_RG','PE_DA','PE_SG','PE_DS','PE_RS','PE_OC'");
		    	 list= paServices.ImportExcelProcess(parameterObject);
		    }else{
		    	 list= paServices.ImportExcelResult(parameterObject);
		    }
	    	
	    
	         // 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				 SimpleMap columns = new SimpleMap();
				 columns.setString("BG", "BG_NM");
				 if(paType.equals("END")){
				 columns.setString("部门全称", "ORG_FULL_NM");				
				 }else{
				 columns.setString("工资类型","WAGESTYPE"); 
				 columns.setString("职级", "POSTYPE");
				 }				
				 columns.setString("职群", "GROUPTYPE");				
				 columns.setString("工资项目", "PAYTYPE");
				 columns.setString("1月", "PAY1");
				 columns.setString("2月", "PAY2");		 
				 columns.setString("3月", "PAY3");
				 columns.setString("4月", "PAY4");	 
				 columns.setString("5月", "PAY5");	
				 columns.setString("6月", "PAY6");
				 columns.setString("7月", "PAY7");
				 columns.setString("8月", "PAY8");
				 columns.setString("9月", "PAY9");
				 columns.setString("10月", "PAY10");
				 columns.setString("11月", "PAY11");
				 columns.setString("12月", "PAY12");

				 ///SimpleMap columnType = new SimpleMap()；

				ExcelParameterBean paramBean = new ExcelParameterBean();
				paramBean.setFileName("PA_PAY_PLAN.xls");
				paramBean.setSheetname("PA_PAY_PLAN");
				paramBean.setReportData(list);
				paramBean.setColumns(columns);
				///paramBean.setColumnTypes(columnType);
				paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
				paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
				paramBean.setInLineHeadContent("");
				paramBean.setInLineHeadMergeSize(columns.size());

				ReportUtil.makeReport(request, paramBean); 
	        
	        return "/inc/commonReport.jsp";
	    }
	 
}



