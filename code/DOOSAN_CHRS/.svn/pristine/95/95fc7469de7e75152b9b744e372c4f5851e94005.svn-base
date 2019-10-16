package com.ait.reports.servlet.ar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDetailBack;
import com.ait.ar.bean.ArItem;
import com.ait.ar.business.ArServices;
import com.ait.ar.dao.ArItemBean;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.ess.dao.EssArDAO;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.business.ReportServices;
import com.ait.i18n.MessageSource;
import com.ait.reports.reportservices.ArReportService;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.ViewOptionUtil;

public class ArReportCmd extends ArAbstractCommand {
	
	private ArReportService service = null ;

	private AdminBean admin = null ;
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reportType = request.getParameter("reportType") ;
		String url = "" ;
		service = new ArReportService() ;
		
		admin = (AdminBean)request.getSession().getAttribute("admin") ;
		
		// 每个报表都有自己单独的函数
		if (reportType.equals("report0101"))
		{
			url = this.report0101(request, response) ;
		}
		else if(reportType.equals("report0102"))
		{
			url = this.report0102(request, response) ;
		} 
		else if(reportType.equals("report0103"))
		{
			url = this.report0103(request, response) ;
		}
		else if(reportType.equals("report0104"))
		{
			url = this.report0104(request, response) ;
		}
		else if(reportType.equals("report0105"))
		{
			url = this.report0105(request, response) ;
		}
		else if(reportType.equals("report0106"))
		{
			url = this.report0106(request, response) ;
		}
		else if(reportType.equals("report0107"))
		{
			url = this.report0107(request, response) ;
		}
		else if(reportType.equals("report01287"))
		{
			url = this.report01287(request, response) ;
		}
		else if(reportType.equals("report0108"))
		{
			url = this.report0108(request, response) ;
		}
		else if(reportType.equals("report0109"))
		{
			url = this.report0109(request, response) ;
		}
/*		else if(reportType.equals("report0001"))
		{
			url = this.report0001(request, response) ;
		}*/
		else if(reportType.equals("report0110"))
		{
			url = this.report0110(request, response) ;
		}
		else if(reportType.equals("report0111"))
		{
			url = this.report0111(request, response) ;
		}
		else if(reportType.equals("report0112"))
		{
			url = this.report0112(request, response) ;
		}
		else if(reportType.equals("report0113"))
		{
			url = this.report0113(request, response);
		}
		else if(reportType.equals("report0114"))
		{
			url = this.reprot0114(request, response);
		}
		else if(reportType.equals("report0115"))
		{
			url = this.reprot0115(request, response);
		}
		else if(reportType.equals("report0116"))
		{
			url = this.reprot0116(request, response);
		}
		else if(reportType.equals("report0117"))
		{
			url = this.reprot0117(request, response);
		}
		else if(reportType.equals("report0118"))
		{
			url = this.reprot0118(request, response);
		}
		else if(reportType.equals("report0119"))
		{
			url = this.report0119(request, response);
		}
		else if(reportType.equals("report0120"))
		{
			url = this.report0120(request, response);
		}
		else if(reportType.equals("report0121"))
		{
			url = this.report0121(request, response);
		}
		else if(reportType.equals("report0123"))
		{
			url = this.report0123(request, response);
		}
		else if(reportType.equals("report0124"))
		{
			url = this.report0124(request, response);
		}
		else if(reportType.equals("report0125"))
		{
			url = this.report0125(request, response);
		}
		else if(reportType.equals("report0126"))
		{
			url = this.report0126(request, response);
		}
		else if(reportType.equals("report0127"))
		{
			url = this.report0127(request, response);
		}
		return url;
	}
	
	public String report0101(HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/reports/ar_report/report0101.jsp" ;
	}
	
	public String report0102(HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/reports/ar_report/report0102.jsp" ;
	}
	
	public String report0105(HttpServletRequest request, HttpServletResponse response) {
		
		SimpleMap parameterObject = new SimpleMap() ;
		String arMonth = "" ;
		List<Calendar> arDateList = new ArrayList<Calendar>() ;
		List arDataList = new ArrayList() ;
		SimpleDateFormat report0105DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		SimpleDateFormat report0105DateFormat2 = new SimpleDateFormat("yyyy/MM/dd") ;
		SimpleDateFormat report0105DateFormat3 = new SimpleDateFormat("yyyyMMdd") ;
		
		StringBuffer groupBySql = new StringBuffer(500) ;
		StringBuffer selectSql = new StringBuffer(500) ;
		StringBuffer sumAllSql = new StringBuffer(500) ;
		
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		
		try{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0105DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0105DateFormat.parse(dateEnd)) ;
			
			// 取得月份
			report0105DateFormat = new SimpleDateFormat("yyyyMM") ;
			arMonth = report0105DateFormat.format(arDateStarted.getTime()) ;
					
			// 循环得到日期
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(arDateStarted.getTime()) ;
			
			dateStarted = report0105DateFormat2.format(temp.getTime()) ;
			dateEnd = report0105DateFormat2.format(arDateEnd.getTime()) ;

			arDateList.add(temp) ;
			while(!arDateStarted.equals(arDateEnd))
			{	
				temp = Calendar.getInstance() ; 
				arDateStarted.add(Calendar.DAY_OF_YEAR, 1) ;
				temp.setTime(arDateStarted.getTime()) ;
				
				arDateList.add(temp) ;
			}
			
			// 循环得到两段sql
			
			sumAllSql.append(",SUM(") ;
			
			for(Calendar calendarT : arDateList)
			{
				String dateStr = report0105DateFormat3.format(calendarT.getTime()) ;
				
				groupBySql.append(",DECODE(T.AR_DATE_STR,'") ; 
				groupBySql.append(report0105DateFormat2.format(calendarT.getTime())) ;
				groupBySql.append("', T.FROM_TIME, NULL) AS \"") ;
				groupBySql.append(dateStr) ;
				groupBySql.append("_FROM_TIME\" ") ;
				
				groupBySql.append(",DECODE(T.AR_DATE_STR,'") ; 
				groupBySql.append(report0105DateFormat2.format(calendarT.getTime())) ;
				groupBySql.append("', T.TO_TIME, NULL) AS \"") ;
				groupBySql.append(dateStr) ;
				groupBySql.append("_TO_TIME\" ") ;
				
				groupBySql.append(",DECODE(T.AR_DATE_STR,'") ; 
				groupBySql.append(report0105DateFormat2.format(calendarT.getTime())) ;
				groupBySql.append("', T.QUANTITY, 0) AS \"") ;
				groupBySql.append(dateStr) ;
				groupBySql.append("_QUANTITY\" ") ;
				
				selectSql.append(",MAX(\"") ; 
				selectSql.append(dateStr) ;
				selectSql.append("_FROM_TIME\") AS \"") ;
				selectSql.append(dateStr) ;
				selectSql.append("_FROM_TIME\" ") ;
				
				selectSql.append(",MAX(\"") ; 
				selectSql.append(dateStr) ;
				selectSql.append("_TO_TIME\") AS \"") ;
				selectSql.append(dateStr) ;
				selectSql.append("_TO_TIME\" ") ;
				
				selectSql.append(",SUM(\"") ; 
				selectSql.append(dateStr) ;
				selectSql.append("_QUANTITY\") AS \"") ;
				selectSql.append(dateStr) ;
				selectSql.append("_QUANTITY\" ") ;
				
				sumAllSql.append("\"") ;
				sumAllSql.append(dateStr) ;
				sumAllSql.append("_QUANTITY\" + ") ;

			}
			sumAllSql.append(" 0 ) AS SUM_ALL") ;
			
			parameterObject.setString("groupBySql", groupBySql.toString()) ;
			parameterObject.setString("selectSql", selectSql.toString()) ;
			parameterObject.setString("sumAllSql", sumAllSql.toString()) ;
			
			parameterObject.setString("dateStarted", dateStarted) ;
			parameterObject.setString("dateEnd", dateEnd) ;
			parameterObject.setString("arMonth", arMonth) ;
			parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.setString("supervisorId", this.admin.getAdminID());
			arDataList = this.service.retrieveReport0105Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
			
		request.setAttribute("arDateList", arDateList) ;
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0105.jsp" ;
	}
	
	

	
	public String report0106(HttpServletRequest request, HttpServletResponse response) {
		
		SimpleMap parameterObject = new SimpleMap() ;
		String arMonth = "" ;
		List<Calendar> arDateList = new ArrayList<Calendar>() ;
		List arDataList = new ArrayList() ;
		SimpleDateFormat report0106DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		
		try{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0106DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0106DateFormat.parse(dateEnd)) ;
			
			// 取得月份
			report0106DateFormat = new SimpleDateFormat("yyyyMM") ;
			arMonth = report0106DateFormat.format(arDateStarted.getTime()) ;
			
			// 循环得到日期
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(arDateStarted.getTime()) ;
			arDateList.add(temp) ;
			
			while(!arDateStarted.equals(arDateEnd))
			{	
				temp = Calendar.getInstance() ; 
				arDateStarted.add(Calendar.DAY_OF_YEAR, 1) ;
				temp.setTime(arDateStarted.getTime()) ;
				
				arDateList.add(temp) ;
			}
			
			parameterObject.set("arMonth", arMonth) ;
			parameterObject.set("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.set("supervisorId", this.admin.getAdminID());
			arDataList = this.service.retrieveReport0106Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
			
		request.setAttribute("arDateList", arDateList) ;
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0106.jsp" ;
	}
	
	public String report0107(HttpServletRequest request, HttpServletResponse response) {
		
		SimpleDateFormat report0107DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		List arDataList = new ArrayList() ;
		List arDataList1 = new ArrayList() ;
		List arDataList2 = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		
		String arMonth = request.getParameter("arMonth") ;
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		String tableName = request.getParameter("tableName") ;
		
		try{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0107DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0107DateFormat.parse(dateEnd)) ;
			
			report0107DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;;
			
			parameterObject.set("arDateStarted", report0107DateFormat.format(arDateStarted.getTime())) ;
			parameterObject.set("arDateEnd", report0107DateFormat.format(arDateEnd.getTime())) ;
			parameterObject.set("tableName", tableName) ;
			parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.setString("supervisorId",this.admin.getAdminID());
			
			arDataList = this.service.retrieveReport0107Data(parameterObject) ;
			arDataList1 = this.service.retrieveReport0107Data1(parameterObject) ;
			arDataList2 = this.service.retrieveReport0107Data2(parameterObject) ;
			
			request.setAttribute("arDataList", arDataList) ;
			request.setAttribute("arDataList1", arDataList1) ;
			request.setAttribute("arDataList2", arDataList2) ;
		}
		catch(Exception e){
			e.printStackTrace() ;
		}
		
		return "/reports/ar_report/report0107.jsp" ;
	}
	
	public String report01287(HttpServletRequest request, HttpServletResponse response) {
		
		SimpleDateFormat report01287DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		List arDataList = new ArrayList() ;
		List arDataList1 = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		
		//String arMonth = request.getParameter("arMonth") ;
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		String workType = request.getParameter("workType") ;
		
		try{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report01287DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report01287DateFormat.parse(dateEnd)) ;
			
			report01287DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;;
			
			parameterObject.set("arDateStarted", report01287DateFormat.format(arDateStarted.getTime())) ;
			parameterObject.set("arDateEnd", report01287DateFormat.format(arDateEnd.getTime())) ;
			parameterObject.set("workType", workType) ;
			parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.setString("supervisorId",this.admin.getAdminID());
			if(workType.equals("1")){
				arDataList1 = this.service.retrieveReport01287Data1(parameterObject) ;
				request.setAttribute("arDataList1", arDataList1) ;
			}else{
				arDataList = this.service.retrieveReport01287Data(parameterObject) ;
				request.setAttribute("arDataList", arDataList) ;
			}
			
		}
		catch(Exception e){
			e.printStackTrace() ;
		}

		if(workType.equals("1")){
		return "/reports/ar_report/report012871.jsp" ;
		}else{
			return "/reports/ar_report/report01287.jsp" ;
		}
	}
	
	public String report0111(HttpServletRequest request, HttpServletResponse response) {
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		SimpleDateFormat report0111DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute(
		"admin"));
		String arDateStr = request.getParameter("arDate") ;
		String arMonth = request.getParameter("arMonth") ;
		String dateEnd = request.getParameter("dateEnd");
		
		try{
			Calendar arDate = Calendar.getInstance() ;
			arDate.setTime(report0111DateFormat.parse(arDateStr)) ;
			
			Calendar arEndDate = Calendar.getInstance() ;
			arEndDate.setTime(report0111DateFormat.parse(dateEnd)) ;
			
			report0111DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
				
			parameterObject.set("arDate", report0111DateFormat.format(arDate.getTime())) ;
			parameterObject.set("dateEnd", dateEnd);
			parameterObject.setString("supervisorId", admin.getAdminID()) ;
			parameterObject.set("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.setString("person_id", admin.getAdminID());
			
			arDataList = this.service.retrieveReport0111Data(parameterObject) ;
			
			request.setAttribute("arDataList", arDataList) ;
			request.setAttribute("arStartDate", arDate) ;
			request.setAttribute("arEndDate", arEndDate) ;
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}

		return "/reports/ar_report/report0111.jsp" ;
	}
	
	public String report0103(HttpServletRequest request, HttpServletResponse response) {
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		SimpleDateFormat report0103DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		
		try
		{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0103DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0103DateFormat.parse(dateEnd)) ;
			
			report0103DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
			
			parameterObject.set("arDateStarted", report0103DateFormat.format(arDateStarted.getTime())) ;
			parameterObject.set("arDateEnd", report0103DateFormat.format(arDateEnd.getTime())) ;
			parameterObject.set("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.set("supervisorId",this.admin.getAdminID());
			
			arDataList = this.service.retrieveReport0103Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0103.jsp" ;
	}
	
	public String report0104(HttpServletRequest request, HttpServletResponse response) {
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		SimpleDateFormat report0104DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		
		try
		{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0104DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0104DateFormat.parse(dateEnd)) ;
			
			report0104DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
			
			parameterObject.set("arDateStarted", report0104DateFormat.format(arDateStarted.getTime())) ;
			parameterObject.set("arDateEnd", report0104DateFormat.format(arDateEnd.getTime())) ;
			parameterObject.set("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.set("supervisorId",this.admin.getAdminID());
			
			arDataList = this.service.retrieveReport0104Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0104.jsp" ;
	}
	
	public String report0108(HttpServletRequest request, HttpServletResponse response) {
		
		List<Calendar> arDateList = new ArrayList<Calendar>() ;
		SimpleMap arDateMap = new SimpleMap() ;
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		String arMonth = request.getParameter("arMonth") ;
		
		parameterObject.setString("arMonth", arMonth) ;
		parameterObject.setString("supervisorId", admin.getAdminID()) ;
		parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
		parameterObject.setString("deptId", request.getParameter("deptId")) ;
		parameterObject.setString("itemGroupCode", "other") ;
		
//		arDateMap = this.service.retrieveReport0108ArDate(parameterObject) ;
//		String dateStarted = arDateMap.getString("START_DATE") ;
//		String dateEnd = arDateMap.getString("END_DATE") ;
		
		String dateStarted = request.getParameter("dateStarted") ;
		String dateEnd = request.getParameter("dateEnd") ;
		parameterObject.set("dateStarted", dateStarted);
		parameterObject.set("dateEnd", dateEnd);
		
		try{
			SimpleDateFormat report0108DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
			
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0108DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0108DateFormat.parse(dateEnd)) ;
					
			// 循环得到日期
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(arDateStarted.getTime()) ;

			arDateList.add(temp) ;
			while(!arDateStarted.equals(arDateEnd))
			{	
				temp = Calendar.getInstance() ; 
				arDateStarted.add(Calendar.DAY_OF_YEAR, 1) ;
				temp.setTime(arDateStarted.getTime()) ;
				
				arDateList.add(temp) ;
			}
			
			arDataList = this.service.retrieveReport0108Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		request.setAttribute("arDateList", arDateList) ;
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0108.jsp" ;
	}
	
	public String report0109(HttpServletRequest request, HttpServletResponse response) {
			
		List<Calendar> arDateList = new ArrayList<Calendar>() ;
		SimpleMap arDateMap = new SimpleMap() ;
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		String arMonth = request.getParameter("arMonth") ;
		
		parameterObject.setString("arMonth", arMonth) ;
		parameterObject.setString("supervisorId", admin.getAdminID()) ;
		parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
		parameterObject.setString("deptId", request.getParameter("deptId")) ;
		parameterObject.setString("itemGroupCode", "ot") ;
		
//		arDateMap = this.service.retrieveReport0108ArDate(parameterObject) ;
//		String dateStarted = arDateMap.getString("START_DATE") ;
//		String dateEnd = arDateMap.getString("END_DATE") ;
		
		String dateStarted = request.getParameter("dateStarted") ;
		String dateEnd = request.getParameter("dateEnd") ;
		parameterObject.set("dateStarted", dateStarted);
		parameterObject.set("dateEnd", dateEnd);
		
		try{
			SimpleDateFormat report0109DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
			
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0109DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0109DateFormat.parse(dateEnd)) ;
					
			// 循环得到日期
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(arDateStarted.getTime()) ;

			arDateList.add(temp) ;
			while(!arDateStarted.equals(arDateEnd))
			{	
				temp = Calendar.getInstance() ; 
				arDateStarted.add(Calendar.DAY_OF_YEAR, 1) ;
				temp.setTime(arDateStarted.getTime()) ;
				
				arDateList.add(temp) ;
			}
			
			arDataList = this.service.retrieveReport0108Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		request.setAttribute("dateStarted", dateStarted) ;
		request.setAttribute("dateEnd", dateEnd) ;
		request.setAttribute("arDateList", arDateList) ;
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0109.jsp" ;
	}
	
/*	public String report0001(HttpServletRequest request, HttpServletResponse response) {
		
		List<Calendar> arDateList = new ArrayList<Calendar>() ;
		SimpleMap arDateMap = new SimpleMap() ;
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		String arMonth = request.getParameter("arMonth") ;
		
		parameterObject.setString("arMonth", arMonth) ;
		parameterObject.setString("supervisorId", admin.getAdminID()) ;
		parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
		parameterObject.setString("deptId", request.getParameter("deptId")) ;
		parameterObject.setString("itemGroupCode", "ot") ;
		
		String dateStarted = request.getParameter("dateStarted") ;
		String dateEnd = request.getParameter("dateEnd") ;
		String jiaType = request.getParameter("jiaType") ;
		parameterObject.set("dateStarted", dateStarted);
		parameterObject.set("dateEnd", dateEnd);
		
		try{
			SimpleDateFormat report0001DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
			
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0001DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0001DateFormat.parse(dateEnd)) ;
					
			// 循环得到日期
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(arDateStarted.getTime()) ;

			arDateList.add(temp) ;
			while(!arDateStarted.equals(arDateEnd))
			{	
				temp = Calendar.getInstance() ; 
				arDateStarted.add(Calendar.DAY_OF_YEAR, 1) ;
				temp.setTime(arDateStarted.getTime()) ;
				
				arDateList.add(temp) ;
			}
			if(jiaType.equals("ping")){
				
			arDataList = this.service.retrieveReport0001Data(parameterObject) ;
			}else{
				arDataList = this.service.retrieveReport0001_Data(parameterObject) ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		request.setAttribute("dateStarted", dateStarted) ;
		request.setAttribute("dateEnd", dateEnd) ;
		request.setAttribute("arDateList", arDateList) ;
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0001.jsp" ;
	}
	*/
	public String report0110(HttpServletRequest request, HttpServletResponse response) {
		
		List<Calendar> arDateList = new ArrayList<Calendar>() ;
		SimpleMap arDateMap = new SimpleMap() ;
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		String arMonth = request.getParameter("arMonth") ;
		
		parameterObject.setString("arMonth", arMonth) ;
		parameterObject.setString("supervisorId", admin.getAdminID()) ;
		parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
		parameterObject.setString("deptId", request.getParameter("deptId")) ;
		parameterObject.setString("itemGroupCode", "ot") ;
		
//		arDateMap = this.service.retrieveReport0108ArDate(parameterObject) ;
//		String dateStarted = arDateMap.getString("START_DATE") ;
//		String dateEnd = arDateMap.getString("END_DATE") ;
		
		String dateStarted = request.getParameter("dateStarted") ;
		String dateEnd = request.getParameter("dateEnd") ;
		parameterObject.set("dateStarted", dateStarted);
		parameterObject.set("dateEnd", dateEnd);
		
		try{
			SimpleDateFormat report0110DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
			
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0110DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0110DateFormat.parse(dateEnd)) ;
					
			// 循环得到日期
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(arDateStarted.getTime()) ;

			arDateList.add(temp) ;
			while(!arDateStarted.equals(arDateEnd))
			{	
				temp = Calendar.getInstance() ; 
				arDateStarted.add(Calendar.DAY_OF_YEAR, 1) ;
				temp.setTime(arDateStarted.getTime()) ;
				
				arDateList.add(temp) ;
			}
			
			arDataList = this.service.retrieveReport0110Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		request.setAttribute("arDateList", arDateList) ;
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0110.jsp" ;
	}
	
	public String report0112(HttpServletRequest request, HttpServletResponse response) {
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		SimpleDateFormat report0112DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute("admin"));
		
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		
		try{
			ArrayList list = new ArItemBean().getItemList();
			
			StringBuffer selectSql = new StringBuffer(1000) ;
			
			selectSql.append("AR_DETAIL.EMPID AS PERSONID,MAX(C.EMPID) AS EMPID,") ;
			selectSql.append("MAX(C.CHINESENAME) AS CHINESENAME,") ;
			selectSql.append("MAX(D.DEPTNAME) AS DEPTNAME,") ;
			selectSql.append("SUM(CASE WHEN AR_ITEM_NO IN (35, 20,52,53,24,58,69) THEN QUANTITY ELSE 0 END) AS \"WORK\"") ;
			for (int i = 0 ; i < list.size() ; ++ i)
			{
				ArItem item = (ArItem)list.get(i);
				selectSql.append(",SUM(DECODE(AR_ITEM_NO," + item.getItemNo() + ",QUANTITY,0)) AS \"" + item.getItemId() + "\"") ;
			}
			
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0112DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0112DateFormat.parse(dateEnd)) ;
			
			report0112DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;;

			parameterObject.set("sql",selectSql.toString());
			parameterObject.set("arDateStarted", report0112DateFormat.format(arDateStarted.getTime())) ;
			parameterObject.set("arDateEnd", report0112DateFormat.format(arDateEnd.getTime())) ;
			parameterObject.setString("deptId", request.getParameter("deptId")) ;
			parameterObject.setString("supervisorId", admin.getAdminID()) ;
			parameterObject.set("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.setString("person_id", admin.getAdminID());
			
			arDataList = this.service.retrieveReport0112Data(parameterObject) ;
			
			
			ArItem info = new ArItem();
            info.setItemId("WORK");
            info.setItemName("正常出勤");
            list.add(info) ;
			
			request.setAttribute("itemList", list);
			request.setAttribute("arDataList", arDataList) ;
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}

		return "/reports/ar_report/report0112.jsp" ;
	}
	
	public String report0113(HttpServletRequest request, HttpServletResponse response) {
	
		try{
			String startDate = request.getParameter("arDateStarted");
			String endDate = request.getParameter("arDateEnd");
			
			ArReportService reportService = new ArReportService();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("arDateStarted", startDate);
			parameterObject.setString("arDateEnd", endDate);
			parameterObject.setString("deptId", request.getParameter("deptId"));
			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("supervisorId",admin.getAdminID());
			
			List recordList = reportService.retrieveReport0113Data(parameterObject);
			request.setAttribute("recordList", recordList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/reports/ar_report/report0113.jsp";
	}
	
	public String reprot0114(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String startDate = request.getParameter("arDateStarted");
			String endDate = request.getParameter("arDateEnd");
			
			ArReportService reportService = new ArReportService();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("startDate", startDate);
			parameterObject.setString("endDate", endDate);
			parameterObject.setString("supervisorId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			
			List recordList = reportService.retrieveReport0114Data1(parameterObject);
			request.setAttribute("recordList", recordList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/reports/ar_report/report0114.jsp";
	}
	
	public String reprot0115(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String startDate = request.getParameter("arDateStarted");
			String endDate = request.getParameter("arDateEnd");
			
			ArReportService reportService = new ArReportService();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("startDate", startDate);
			parameterObject.setString("endDate", endDate);
			parameterObject.setString("supervisorId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			
			List recordList = reportService.retrieveReport0115Data(parameterObject);
			request.setAttribute("recordList", recordList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/reports/ar_report/report0115.jsp";
	}
	
	public String reprot0116(HttpServletRequest request, HttpServletResponse response){
		
		List detailList = null;
		List specialItemList = null;
		List overTimeList = null;
		List basicList = null;
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		
		try{
			String personId = request.getParameter("personId");
			String deptId = request.getParameter("deptId");
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			String arMonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
			String ar_month_str = arMonth;
			
			HrmServices hrmServices = HrmServices.getInstance();
			ArServices arServices = new ArServices();
			SimpleMap parameter = new SimpleMap();
			parameter.setString("empId",personId);
			parameter.setString("deptId", deptId);
			parameter.setString("cpnyId", admin.getCpnyId());
			parameter.setString("ADMINID", admin.getAdminID());
			parameter.setString("SUPERVISOR_ID", admin.getAdminID());
			
			if(!personId.equals("")){
				SimpleMap map = new SimpleMap();
				basicList = (List)hrmServices.retrieveBasicInfoMap(parameter);
				map = (SimpleMap)basicList.get(0);
					
				map.setString("empID", map.getString("PERSONID"));
				map.setString("ar_month_str", ar_month_str);
				detailList = arServices.retrieveAttDetailForView(map);
				// retrieve special item data
				specialItemList = arServices.retrieveSpecialItemForViewForView(map);
				//retrieve over time data
				overTimeList = arServices.retrieveOverTimeByView(map);
				map.set("detailList", detailList);
				map.set("specialItemList", specialItemList);
				map.set("overTimeList", overTimeList);
					
			}else {
				SimpleMap map = new SimpleMap();
				basicList = hrmServices.retrieveEmpMapList(parameter);
				Iterator iter = basicList.iterator();
				for (;iter.hasNext();) {					
					map = (SimpleMap)iter.next();
					map.setString("empID",map.getString("PERSONID"));
					map.setString("ar_month_str", ar_month_str);
					detailList = arServices.retrieveAttDetailForView(map);
					specialItemList = arServices.retrieveSpecialItemForViewForView(map);
					overTimeList = arServices.retrieveOverTimeByView(map);
					map.set("detailList", detailList);
					map.set("specialItemList", specialItemList);
					map.set("overTimeList", overTimeList);
				}
				
			}
				
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("basicList", basicList);

		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/reports/ar_report/report0116.jsp";
	}
	
	public String reprot0117(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String yearMonth = request.getParameter("yearMonth");
			
			
			ArReportService reportService = new ArReportService();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("yearMonth", yearMonth);
			parameterObject.setString("supervisorId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			request.setAttribute("yearMonth", yearMonth);
		
			
			List recordList = reportService.retrieveReport0117Data(parameterObject);
			
			request.setAttribute("recordList", recordList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/reports/ar_report/report0117.jsp";
	}
	
	public String reprot0118(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String yearMonth = request.getParameter("yearMonth");
			
			
			ArReportService reportService = new ArReportService();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("yearMonth", yearMonth);
			parameterObject.setString("supervisorId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			request.setAttribute("yearMonth", yearMonth);
		
			
			List recordList = reportService.retrieveReport0118Data(parameterObject);
			
			request.setAttribute("recordList", recordList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/reports/ar_report/report0118.jsp";
	}
	
	public String report0119(HttpServletRequest request, HttpServletResponse response) {
		String yearMonth = request.getParameter("yearMonth");
		request.setAttribute("yearMonth", yearMonth);
		
		return "/reports/ar_report/report0119.jsp" ;
		
	}
	

	public String report0120(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String yearMonthStart = request.getParameter("yearMonthStart");
			String yearMonthEnd = request.getParameter("yearMonthEnd");
			
			
			ArReportService reportService = new ArReportService();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("yearMonthStart", yearMonthStart);
			parameterObject.setString("yearMonthEnd", yearMonthEnd);
			parameterObject.setString("supervisorId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			request.setAttribute("yearMonthStart", yearMonthStart);
			request.setAttribute("yearMonthEnd", yearMonthEnd);
		
			
			List recordList = reportService.retrieveReport0120Data(parameterObject);
			
			request.setAttribute("recordList", recordList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/reports/ar_report/report0120.jsp";
	}
	
	
public String report0121(HttpServletRequest request, HttpServletResponse response) {
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		SimpleDateFormat report0121DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		
		try
		{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0121DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0121DateFormat.parse(dateEnd)) ;
			
			report0121DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
			
			parameterObject.set("arDateStarted", report0121DateFormat.format(arDateStarted.getTime())) ;
			parameterObject.set("arDateEnd", report0121DateFormat.format(arDateEnd.getTime())) ;
			parameterObject.set("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.set("supervisorId",this.admin.getAdminID());
			
			arDataList = this.service.retrieveReport0121Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0121.jsp" ;
	}


	public String report0123(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String yearMonthStart = request.getParameter("yearMonthStart");
			String yearMonthEnd = request.getParameter("yearMonthEnd");
			
			
			ArReportService reportService = new ArReportService();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("yearMonthStart", yearMonthStart);
			parameterObject.setString("yearMonthEnd", yearMonthEnd);
			parameterObject.setString("supervisorId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			request.setAttribute("yearMonthStart", yearMonthStart);
			request.setAttribute("yearMonthEnd", yearMonthEnd);
		
			
			List recordList = reportService.retrieveReport0123Data(parameterObject);
			
			request.setAttribute("recordList", recordList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/reports/ar_report/report0123.jsp";
	}
	
	
public String report0124(HttpServletRequest request, HttpServletResponse response) {
		
		SimpleMap parameterObject = new SimpleMap() ;
		String arMonth = "" ;
		List<Calendar> arMonthList = new ArrayList<Calendar>() ;
		List arDataList = new ArrayList() ;
		
		SimpleDateFormat report0124DateFormat = new SimpleDateFormat("yyyyMM") ;
		
		StringBuffer groupBySql = new StringBuffer(500) ;
		StringBuffer selectSql = new StringBuffer(500) ;
		
		
		String yearMonthStart = request.getParameter("yearMonthStart");
		String yearMonthEnd = request.getParameter("yearMonthEnd");
		
		try{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0124DateFormat.parse(yearMonthStart)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0124DateFormat.parse(yearMonthEnd)) ;
			
					
			// 循环得到月份
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(arDateStarted.getTime()) ;
			
			yearMonthStart = report0124DateFormat.format(temp.getTime()) ;
			yearMonthEnd = report0124DateFormat.format(arDateEnd.getTime()) ;

			arMonthList.add(temp) ;
			while(!arDateStarted.equals(arDateEnd))
			{	
				temp = Calendar.getInstance() ; 
				arDateStarted.add(Calendar.MONTH, 1) ;
				temp.setTime(arDateStarted.getTime()) ;
				System.out.println(arDateStarted.getTime());
				
				arMonthList.add(temp) ;
			}
			
			// 循环得到两段sql
			
			for(Calendar calendarT : arMonthList)
			{
				String dateStr = report0124DateFormat.format(calendarT.getTime()) ;
				
				groupBySql.append(",DECODE(T.AR_MONTH,'") ; 
				groupBySql.append(report0124DateFormat.format(calendarT.getTime())) ;
				groupBySql.append("', T.TOTAL_WEEKDAY_OT, 0) AS \"") ;
				groupBySql.append(dateStr) ;
				groupBySql.append("_WEEKDAY\" ") ;
				
				groupBySql.append(",DECODE(T.AR_MONTH,'") ; 
				groupBySql.append(report0124DateFormat.format(calendarT.getTime())) ;
				groupBySql.append("', T.TOTAL_WEEKEND_OT, 0) AS \"") ;
				groupBySql.append(dateStr) ;
				groupBySql.append("_WEEKEND\" ") ;
				
				groupBySql.append(",DECODE(T.AR_MONTH,'") ; 
				groupBySql.append(report0124DateFormat.format(calendarT.getTime())) ;
				groupBySql.append("', T.TOTAL_HOLIDAY_OT, 0) AS \"") ;
				groupBySql.append(dateStr) ;
				groupBySql.append("_HOLIDAY\" ") ;
				
				selectSql.append(",SUM(\"") ; 
				selectSql.append(dateStr) ;
				selectSql.append("_WEEKDAY\") AS \"") ;
				selectSql.append(dateStr) ;
				selectSql.append("_WEEKDAY\" ") ;
				
				selectSql.append(",SUM(\"") ; 
				selectSql.append(dateStr) ;
				selectSql.append("_WEEKEND\") AS \"") ;
				selectSql.append(dateStr) ;
				selectSql.append("_WEEKEND\" ") ;
				
				selectSql.append(",SUM(\"") ; 
				selectSql.append(dateStr) ;
				selectSql.append("_HOLIDAY\") AS \"") ;
				selectSql.append(dateStr) ;
				selectSql.append("_HOLIDAY\" ") ;
				
				
			}
		

			parameterObject.setString("groupBySql", groupBySql.toString()) ;
			parameterObject.setString("selectSql", selectSql.toString()) ;
			
			parameterObject.setString("yearMonthStart", yearMonthStart) ;
			parameterObject.setString("yearMonthEnd", yearMonthEnd) ;
			//parameterObject.setString("arMonth", arMonth) ;
			parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.setString("supervisorId", this.admin.getAdminID());
			arDataList = this.service.retrieveReport0124Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
			
		
		request.setAttribute("arMonthList", arMonthList) ;
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0124.jsp" ;
	}

	public String report0125(HttpServletRequest request, HttpServletResponse response) {
	    
		EssArDAO essArDAO = new EssArDAO();
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		SimpleDateFormat report0125DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		
		String dateStarted = request.getParameter("arDateStarted") ;
		String dateEnd = request.getParameter("arDateEnd") ;
		
		try
		{
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0125DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0125DateFormat.parse(dateEnd)) ;
			
			report0125DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
			
			parameterObject.set("arDateStarted", report0125DateFormat.format(arDateStarted.getTime())) ;
			parameterObject.set("arDateEnd", report0125DateFormat.format(arDateEnd.getTime())) ;
			parameterObject.set("cpnyId", this.admin.getCpnyId()) ;
			parameterObject.set("supervisorId",this.admin.getAdminID());
			
			arDataList = this.service.retrieveReport0125Data(parameterObject) ;
			
			for (int i = 0; i < arDataList.size(); i++) {
				ArDetailBack arDetail = (ArDetailBack) arDataList.get(i);
				arDetail.setAffirmorList((ArrayList) essArDAO.getArModifyAffirmorList(arDetail.getPkNo()));	
			}	
			
			
			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");
			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (arDataList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}
			
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
				
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		request.setAttribute("arDataList", arDataList) ;
		
		return "/reports/ar_report/report0125.jsp" ;
	}
	
	
public String report0126(HttpServletRequest request, HttpServletResponse response) {
		
		List<Calendar> arDateList = new ArrayList<Calendar>() ;
		SimpleMap arDateMap = new SimpleMap() ;
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		//String arMonth = request.getParameter("arMonth") ;
		
		
		String yearMonth = request.getParameter("yearMonth");
		
		
		
		parameterObject.setString("arMonth", yearMonth) ;
		parameterObject.setString("supervisorId", admin.getAdminID()) ;
		parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
		parameterObject.setString("deptId", request.getParameter("deptId")) ;
		parameterObject.setString("itemGroupCode", "other") ;
		
		SimpleMap  arDate = service.retrieveReportArDateStartAndEnd(parameterObject) ;
	    
		
		
		String dateStarted = arDate.getString("S_DATE");
		String dateEnd = arDate.getString("E_DATE");
		
		parameterObject.set("dateStarted", dateStarted);
		parameterObject.set("dateEnd", dateEnd);
		
		try{
			SimpleDateFormat report0126DateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
			
			// 格式化日期
			Calendar arDateStarted = Calendar.getInstance() ;
			arDateStarted.setTime(report0126DateFormat.parse(dateStarted)) ;
			
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateEnd.setTime(report0126DateFormat.parse(dateEnd)) ;
					
			// 循环得到日期
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(arDateStarted.getTime()) ;

			arDateList.add(temp) ;
			while(!arDateStarted.equals(arDateEnd))
			{	
				temp = Calendar.getInstance() ; 
				arDateStarted.add(Calendar.DAY_OF_YEAR, 1) ;
				temp.setTime(arDateStarted.getTime()) ;
				
				arDateList.add(temp) ;
			}
			
			arDataList = this.service.retrieveReport0126Data(parameterObject) ;
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		request.setAttribute("arDateList", arDateList) ;
		request.setAttribute("arDataList", arDataList) ;
		request.setAttribute("yearMonth", yearMonth);
		
		return "/reports/ar_report/report0126.jsp" ;
	}


	public String report0127(HttpServletRequest request, HttpServletResponse response) {
		
		List arDataList = new ArrayList() ;
		SimpleMap parameterObject = new SimpleMap() ;
		
		String dateStarted = request.getParameter("dateStarted");
		String dateEnd = request.getParameter("dateEnd");
		String arTime =  request.getParameter("arTime");
		
		parameterObject.setString("dateStarted", dateStarted);
		parameterObject.setString("dateEnd", dateEnd);
		parameterObject.setString("arTime", arTime) ;
		parameterObject.setString("supervisorId", admin.getAdminID()) ;
		parameterObject.setString("cpnyId", this.admin.getCpnyId()) ;
		parameterObject.setString("deptId", request.getParameter("deptId")) ;
		
		arDataList = this.service.retrieveReport0127Data(parameterObject) ;
	    

		request.setAttribute("arDataList", arDataList) ;
		request.setAttribute("dateStarted", dateStarted) ;
		request.setAttribute("dateEnd", dateEnd) ;
		request.setAttribute("arTime", arTime) ;

		
		return "/reports/ar_report/report0127.jsp" ;
	}
    
}











