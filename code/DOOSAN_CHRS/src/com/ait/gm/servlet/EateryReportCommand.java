package com.ait.gm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * 
 * @author yangxiaohui (yangxiaohui@ait.net.cn)
 * @Date 2008-8-10
 * 
 */
public class EateryReportCommand implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content=request.getParameter("content");
		String returnPage="";
		if(content.equals("EateryYearCountsExcel")){
			returnPage=this.EateryYearCountsExcel(request, response);
		}else if(content.equals("EateryCountsExcel")){
			returnPage=this.EateryCountsExcel(request, response);
		}else if(content.equals("EateryTotalByDeptExcel")){
			returnPage=this.EateryTotalByDeptExcel(request, response);
		}else if(content.equals("EateryMonthConsumeExcel")){
			returnPage=this.EateryMonthConsumeExcel(request, response);
		}else{
			returnPage="/error.jsp";
		}
		return returnPage;
	}
	public String EateryYearCountsExcel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			
		
		
		String decodesql="";
		String sunsql="";
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.clear();
		SimpleMap eateryType = new SimpleMap();
		if(cpnyId.equals("78000000")){
			eateryType.set("break", "26");
			eateryType.set("lunch", "40");
			eateryType.set("supper", "42");
			eateryType.set("dinner", "43");
			parameterObject.set("break", "26");
			parameterObject.set("lunch", "40");
			parameterObject.set("supper", "42");
			parameterObject.set("dinner", "43");
		}else if(cpnyId.equals("63000000")){
			parameterObject.set("lunch","46");
			parameterObject.set("supper","44");
			parameterObject.set("dinner","47");
			parameterObject.set("break","45");
			eateryType.set("lunch","46");
			eateryType.set("supper","44");
			eateryType.set("dinner","47");
			eateryType.set("break","45");
		}else if(cpnyId.equals("61000000")){
			parameterObject.set("lunch", "52");
			parameterObject.set("supper","53");
			parameterObject.set("dinner","55");	
			parameterObject.set("break","54");    
			eateryType.set("lunch","52");
			eateryType.set("supper","53");
			eateryType.set("dinner","55");
			eateryType.set("break","54");
		}else{
			
			Logger.getLogger(getClass()).error("该公司的就餐类型不存在!");
		}
			
		
		try {
			
			String year = request.getParameter("year");	
			parameterObject.set("adminId", adminId);
			parameterObject.set("cpnyId", cpnyId);
			parameterObject.set("year", year);
			for(int i=1;i<13;i++){
				String columname="";
				if(i<10){
					columname="0"+i;
					
				}else{
					columname=StringUtil.checkNull(i);
				}
				
				decodesql=decodesql+"decode(v.MONTH,'"+columname+"',decode(v.EateryTYPEID,'"+eateryType.getString("break")+"',count(v.EMPID),0),0) as \""+columname+"break\", "+
	                      "decode(v.MONTH,'"+columname+"',decode(v.EateryTYPEID,'"+eateryType.getString("lunch")+"',count(v.EMPID),0),0) as \""+columname+"lunch\","+
	                      " decode(v.MONTH,'"+columname+"',decode(v.EateryTYPEID,'"+eateryType.getString("supper")+"',count(v.EMPID),0),0) as \""+columname+"supper\","+
	                      "decode(v.MONTH,'"+columname+"',decode(v.EateryTYPEID,'"+eateryType.getString("dinner")+"',count(v.EMPID),0),0) as \""+columname+"dinner\","+
	                      " decode(v.MONTH,'"+columname+"',count(v.EMPID),0) as \""+columname+"allcount\",";	
				
				sunsql=sunsql+" sum(\""+columname+"break\") \""+columname+"break\","+
	                          "sum(\""+columname+"lunch\") \""+columname+"lunch\", "+
	                          "sum(\""+columname+"supper\") \""+columname+"supper\","+
	                          "sum(\""+columname+"dinner\") \""+columname+"dinner\","+
	                          "sum(\""+columname+"allcount\") \""+columname+"allcount\",";
				
			}
			parameterObject.set("decodesql", decodesql);
			parameterObject.set("sunsql", sunsql);			
			List result = services.getResult(parameterObject);
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}
		return "/reports/gm_report/YearEateryPeople.jsp";
	}
  public String EateryCountsExcel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
		
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.clear();		
		if(cpnyId.equals("78000000")){			
			parameterObject.set("break", "26");
			parameterObject.set("lunch", "40");
			parameterObject.set("supper", "42");
			parameterObject.set("dinner", "43");
		}else if(cpnyId.equals("63000000")){
			parameterObject.set("lunch","46");
			parameterObject.set("supper","44");
			parameterObject.set("dinner","47");
			parameterObject.set("break","45");			
		}else if(cpnyId.equals("61000000")){
			parameterObject.set("lunch", "52");
			parameterObject.set("supper","53");
			parameterObject.set("dinner","55");	
			parameterObject.set("break","54");    
		}else{

			Logger.getLogger(getClass()).error("该公司的就餐类型不存在!");
		}		
		
		try {
			
			String StartDate = request.getParameter("StartDate");	
			String EndDate = request.getParameter("EndDate");	
			parameterObject.set("adminId", adminId);
			parameterObject.set("cpnyId", cpnyId);
			parameterObject.set("StartDate", StartDate);
			parameterObject.set("EndDate", EndDate);
		
			List result = services.eateryCountsExcel(parameterObject);
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}
		return "/reports/gm_report/EateryCountsExcel.jsp";
	}
  public String EateryTotalByDeptExcel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
		
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.clear();		
		if(cpnyId.equals("78000000")){			
			parameterObject.set("break", "26");
			parameterObject.set("lunch", "40");
			parameterObject.set("supper", "42");
			parameterObject.set("dinner", "43");
		}else if(cpnyId.equals("63000000")){
			parameterObject.set("lunch","46");
			parameterObject.set("supper","44");
			parameterObject.set("dinner","47");
			parameterObject.set("break","45");			
		}else if(cpnyId.equals("61000000")){
			parameterObject.set("lunch", "52");
			parameterObject.set("supper","53");
			parameterObject.set("dinner","55");	
			parameterObject.set("break","54");    
		}else{

			Logger.getLogger(getClass()).error("该公司的就餐类型不存在!");
		}		
		
		try {
			
			String StartDate = request.getParameter("StartDate");	
			String EndDate = request.getParameter("EndDate");	
			parameterObject.set("adminId", adminId);
			parameterObject.set("cpnyId", cpnyId);
			parameterObject.set("StartDate", StartDate);
			parameterObject.set("EndDate", EndDate);
		
			List result = services.eateryTotalByDeptExcel(parameterObject);
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve EateryTotalByDep list by paging Exception. ", e);
		}
		return "/reports/gm_report/EateryTotalByDeptExcel.jsp";
	}
    public String EateryMonthConsumeExcel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
    	List result = new ArrayList();
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.clear();		
		if(cpnyId.equals("78000000")){			
			parameterObject.set("break", "26");
			parameterObject.set("lunch", "40");
			parameterObject.set("supper", "42");
			parameterObject.set("dinner", "43");
		}else if(cpnyId.equals("63000000")){
			parameterObject.set("lunch","46");
			parameterObject.set("supper","44");
			parameterObject.set("dinner","47");
			parameterObject.set("break","45");			
		}else if(cpnyId.equals("61000000")){
			parameterObject.set("lunch", "52");
			parameterObject.set("supper","53");
			parameterObject.set("dinner","55");	
			parameterObject.set("break","54");    
		}else{

			Logger.getLogger(getClass()).error("该公司的就餐类型不存在!");
		}		
		
		try {
			
			String StartDate = request.getParameter("StartDate");	
			String EndDate = request.getParameter("EndDate");	
			String Rate = request.getParameter("Rate");
			String floatingNum = request.getParameter("floatingNum");
			parameterObject.set("Rate", Rate);
			parameterObject.set("floatingNum", floatingNum);
			parameterObject.set("adminId", adminId);
			parameterObject.set("cpnyId", cpnyId);
			parameterObject.set("StartDate", StartDate);
			parameterObject.set("EndDate", EndDate);
			if(cpnyId.equals("78000000")){
				result = services.EateryMonthConsumeExcelDICC(parameterObject);
			}else if(cpnyId.equals("63000000")){
				result = services.EateryMonthConsumeExcelDISD(parameterObject);
			}else{
				result=null;
			}		
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}
		return "/reports/gm_report/EateryMonthConsumeExcel.jsp";
	 }
}