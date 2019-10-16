package com.ait.gm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class EateryPeople extends ArAbstractCommand {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;
		SimpleMap map = null;
		
		String flag = StringUtil.checkNull(request.getParameter("flag"), "1");
				
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		
		try {
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("startdate", startdate);
			parameterObject.set("enddate", enddate);
			// retrieve attendance record list

			String sql1 = "";
			String sql2 = "";
			String sql3 = "";
			String eateryEmpNum = "";
			String eateryEmpTotalNum = "";
			String eateryProvisionalEmpNum = "";
			String eateryProvisionalEmpTotalNum = "";
			String eateryNum  = "";
			String eateryProvisionalNum = "";
			String Provisional = "";
			
			List allEateryType = services.getAllEateryType();
			request.setAttribute("allEateryType", allEateryType);
			request.setAttribute("allEateryTypeSize", allEateryType.size()+"");
			for(int i=0 ; i<allEateryType.size() ; i++){
				map = (SimpleMap) allEateryType.get(i);
				String eateryType = map.getString("GM_TYPE");
				eateryEmpNum += "sum(Z."+eateryType+"人数) "+eateryType+"人数, ";
				eateryEmpTotalNum += "+sum(Z."+eateryType+"人数)";
				
				
				eateryProvisionalEmpNum += "sum(NVL(X."+eateryType+",0)) AS 临时"+eateryType+",";
				eateryProvisionalEmpTotalNum += "+sum(NVL(X."+eateryType+",0))";
				
				eateryNum += ",COUNT(CASE WHEN G.GM_TYPE = '"+eateryType+"' AND GC.TYPE_NO <> 2 THEN A.EMPID END) AS "+eateryType+"人数";
				eateryProvisionalNum += ",COUNT(CASE WHEN G.GM_TYPE = '"+eateryType+"' AND GC.TYPE_NO = 2 THEN A.EMPID END) AS 临时"+eateryType+"人数";
			
				Provisional += ",COUNT(CASE WHEN GE.GM_TYPE = '"+eateryType+"' THEN A.CARD_NO END) AS "+eateryType;
			}
			eateryEmpTotalNum += " AS 员工就餐合计,";
			eateryProvisionalEmpTotalNum += " AS 客人就餐合计";
			
			sql1 = eateryEmpNum+eateryEmpTotalNum+eateryProvisionalEmpNum+eateryProvisionalEmpTotalNum;
			parameterObject.set("sql1", sql1);
			
			sql2 = eateryNum+eateryProvisionalNum;
			parameterObject.set("sql2", sql2);
			
			sql3 = Provisional;
			parameterObject.set("sql3", sql3);
			
			List result = services.resultEateryNum(parameterObject);
			request.setAttribute("result", result);
			if(startdate!=null && enddate!=null && !startdate.equals("") && !enddate.equals("")){
			    request.setAttribute("startdate", startdate);
			    request.setAttribute("enddate", enddate);
			}else{
				Calendar today = Calendar.getInstance();
				request.setAttribute("startdate", new java.text.SimpleDateFormat("yyyy-MM-dd").format(today.getTime()));
				request.setAttribute("enddate", new java.text.SimpleDateFormat("yyyy-MM-dd").format(today.getTime()));	
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}
		if(flag.equals("1")){			
			return "gm/eateryPeople.jsp?menu_code=" + parameterObject.getString("menu_code");
		}else if(flag.equals("2")){
			return "/reports/gm_report/eateryPeopleReport.jsp?startdate="+request.getParameter("startdate")+"&enddate="+request.getParameter("enddate");
		}
		return flag;
	}
}