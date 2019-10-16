package com.ait.gm.servlet;

import java.io.IOException;
import java.text.ParseException;
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
import com.ibm.icu.text.SimpleDateFormat;

public class MonthEateryPeople extends ArAbstractCommand {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;
		
		SimpleMap map = null; 
		SimpleMap map1 = null;		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String year1 = request.getParameter("year1");
		String month1 = request.getParameter("month1");
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("DATE", year+"-"+month);
			
/*			List allEateryType = services.getAllEateryType();
			request.setAttribute("allEateryType", allEateryType);
			String sqlEateryType = "";
			String sqlEateryMoney = "";
			String sqlEaterySingleMoney = "";
			for(int i=0 ; i<allEateryType.size() ; i++){
				map = (SimpleMap) allEateryType.get(i);
				String gmType = map.getString("GM_TYPE");
				String gmTypeNo = map.getString("GM_ID");
				sqlEateryType += "+B."+gmType+"刷卡金额";
				sqlEateryMoney += "A."+gmType+"刷卡人数,A."+gmType+"单价,A."+gmType+"刷卡人数 * A."+gmType+"单价 AS "+gmType+"刷卡金额,";
				sqlEaterySingleMoney += "COUNT(CASE WHEN G.GM_ID = "+gmTypeNo+" THEN A.EMPID END) AS "+gmType+"刷卡人数,";
			}
			
			sqlEateryMoney += " A.加餐数量, A.加餐单价, A.加餐数量 * A.加餐单价 AS 加餐金额";
			
			parameterObject.set("sqlEateryMoney", sqlEateryMoney);
			parameterObject.set("sqlEaterySingleMoney", sqlEaterySingleMoney);
			
			List allCardType = services.getAllCardType();
			request.setAttribute("allCardType", allCardType);
			String cardTypeSql = "";
			String cardTypeMoneySql = "";
			String cardTypeSingleMoneySql = "";
			for(int i=0 ; i<allCardType.size() ; i++){
				map1 = (SimpleMap) allCardType.get(i);
				String type_name = map1.getString("TYPE_NAME");
				String type_no = map1.getString("TYPE_NO");
				cardTypeSql += "+B."+type_name;
				cardTypeMoneySql += "A."+type_name+",";
				cardTypeSingleMoneySql += "COUNT(CASE WHEN A.CARD_TYPE = "+type_no+" THEN A.EMPID END) AS "+type_name+",";
				
			}
			
			parameterObject.set("Sql1", cardTypeSql+sqlEateryType);
			parameterObject.set("cardTypeMoneySql", cardTypeMoneySql);
			parameterObject.set("cardTypeSingleMoneySql", cardTypeSingleMoneySql);*/
			List allCardType = services.getAllCardType();
			request.setAttribute("allCardType", allCardType);
			List allEateryType = services.getAllEateryType();
			request.setAttribute("allEateryType", allEateryType);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar today = Calendar.getInstance();
			String yearmoth=year+"-"+month;			
			today.setTime(sdf.parse(yearmoth));			
			today.add(Calendar.MONTH, -1);			
			parameterObject.set("areaStart", sdf.format(today.getTime())+"-21");			
			parameterObject.set("areaEnd",year+"-"+month+"-20");
			List result = services.resultMonth(parameterObject);
			
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}
		String flag=StringUtil.checkNull(request.getParameter("flag"));
		if(flag.equals("company")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar today = Calendar.getInstance();
			String yearmoth1=year1+"-"+month1;	
			String yearmoth=year+"-"+month;			
			try {
				today.setTime(sdf.parse(yearmoth1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			today.add(Calendar.MONTH, -1);			
			 parameterObject.set("areaStart1", sdf.format(today.getTime())+"-21");			
			 parameterObject.set("areaEnd1",year+"-"+month+"-20");
             List company = services.company(parameterObject);			
			 request.setAttribute("company", company);
			 return "/reports/gm_report/MonthEateryCompany.jsp?area="+year1+"-"+month1;
		}else{
		return "/reports/gm_report/MonthEateryPeople.jsp?area="+year+"-"+month;
		}
	}
}