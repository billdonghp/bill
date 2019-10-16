package com.ait.pa.cmd.house;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class HousingFundRatioCmd extends PaAbstractCommand {
	private PaServices services = PaServices.getInstance();
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		
		String content = request.getParameter("content");
		if(content!=null && content.equals("view")){
 			return this.QueryList(request, admin);
		}else if(content!=null && content.equals("save")){
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("ADMINID", admin.getAdminID());
			parameterObject.set("month", parameterObject.getString("YEAR")+parameterObject.getString("MONTH"));
			if(parameterObject.get("seqno") != null && !parameterObject.get("seqno").equals("")){
				services.updatePaHouing(parameterObject);
				
			}else{
				services.inertPaHouing(parameterObject);
			}
			return this.QueryList(request, admin);
//			return "/pa_housing_fund_ratio.jsp?YEAR1="+parameterObject.getString("YEAR1")+"&MONTH1="+parameterObject.getString("MONTH1")+"YEAR2="+parameterObject.getString("YEAR2")+"&MONTH2="+parameterObject.getString("MONTH2")+"deptID="+parameterObject.getString("deptID")+"person_id="+parameterObject.getString("key");
		}else if(content!=null && content.equals("edit")){
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			String date = parameterObject.getString("month");
			parameterObject.set("date", date);
			//取出页面数据
			List list = services.aHouingList(parameterObject);
			SimpleMap simpleMap = new SimpleMap();
			if(!list.isEmpty()){
				simpleMap = (SimpleMap)list.get(0);
				request.setAttribute("EMPID", simpleMap.get("EMPID"));
				request.setAttribute("CHINESENAME", simpleMap.get("CHINESENAME"));
				request.setAttribute("DEPTNAME", simpleMap.get("DEPTNAME"));
				request.setAttribute("ZG", simpleMap.get("ZG"));
				request.setAttribute("ZC", simpleMap.get("ZC"));
				request.setAttribute("SY", simpleMap.get("SY"));
			}
			
			parameterObject.set("ADMINID", admin.getAdminID());
			request.setAttribute("falg", parameterObject.get("falg"));
			request.setAttribute("YEAR", date.substring(0, 4));
			request.setAttribute("MONTH", date.substring(4, 6));
			request.setAttribute("date", date);
			request.setAttribute("seqno", parameterObject.get("seqno"));
			request.setAttribute("person_id", parameterObject.get("person_id1"));
			request.setAttribute("deptID", parameterObject.getString("deptID"));
			request.setAttribute("YEAR1", parameterObject.getString("YEAR1"));
			request.setAttribute("YEAR2", parameterObject.getString("YEAR2"));
			request.setAttribute("MONTH1", parameterObject.getString("MONTH1"));
			request.setAttribute("MONTH2", parameterObject.getString("MONTH2"));
			request.setAttribute("key", parameterObject.get("key"));
			return "/pa_housing_fund_ratioAdd.jsp";
		}else if(content!=null && content.equals("del")){
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("ADMINID", admin.getAdminID());
			parameterObject.set("month", parameterObject.getString("YEAR")+parameterObject.getString("MONTH"));
			services.delPaHouing(parameterObject);
			
			return this.QueryList(request, admin);
		}else if(content != null && content.equals("housingViewExcel")){
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("MM");
			String date1 = format1.format(Calendar.getInstance().getTime());
			String date2 = format2.format(Calendar.getInstance().getTime());
			String startDate = (parameterObject.getString("YEAR1")!=null?parameterObject.getString("YEAR1"):date1)+(parameterObject.getString("MONTH1")!=null?parameterObject.getString("MONTH1"):date2);
			String endDate = (parameterObject.getString("YEAR2")!=null?parameterObject.getString("YEAR2"):date1)+(parameterObject.getString("MONTH2")!=null?parameterObject.getString("MONTH2"):date2);
			
			parameterObject.set("cpny_id", admin.getCpnyId());
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("cpny_id", admin.getCpnyId());
			
			List listhouse = services.paHousingFundListExcel(parameterObject);
			request.setAttribute("listhouse", listhouse);
			
			return "/reports/pa_report/pa_housing_fund_report.jsp";
		}
		else{
			return "/error.jsp";
		}
		
	}
	public String QueryList(HttpServletRequest request ,AdminBean admin){
		SimpleMap parameterObject = new SimpleMap();
		parameterObject = ObjectBindUtil.getData(request);
		int pageSize = 20;
		int pageGroupSize = 10;
		int currentPage = 0;
		int resultCount = 0;
		if (request.getParameter("currentPage") != null
				&& !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM");
		String date1 = format1.format(Calendar.getInstance().getTime());
		String date2 = format2.format(Calendar.getInstance().getTime());
		String startDate = (parameterObject.getString("YEAR1")!=null?parameterObject.getString("YEAR1"):date1)+(parameterObject.getString("MONTH1")!=null?parameterObject.getString("MONTH1"):date2);
		String endDate = (parameterObject.getString("YEAR2")!=null?parameterObject.getString("YEAR2"):date1)+(parameterObject.getString("MONTH2")!=null?parameterObject.getString("MONTH2"):date2);
		parameterObject.set("startDate", startDate);
		parameterObject.set("endDate", endDate);
		parameterObject.set("cpny_id", admin.getCpnyId());
		
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		
		List listhouse = services.paHousingFundList(parameterObject,currentPage,pageSize);
		int countHousing = services.housingCount(parameterObject);
		
		request.setAttribute("listhouse", listhouse);
		request.setAttribute("resultCount", countHousing);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		
		request.setAttribute("YEAR1", startDate.substring(0, 4));
		request.setAttribute("MONTH1", startDate.substring(4, 6));
		request.setAttribute("YEAR2", endDate.substring(0, 4));
		request.setAttribute("MONTH2", endDate.substring(4, 6));
		request.setAttribute("person_id", parameterObject.getString("person_id"));
		request.setAttribute("key", parameterObject.getString("key"));
		request.setAttribute("deptID", parameterObject.getString("deptID"));
		
		// TODO Auto-generated method stub
		
		return "/pa_housing_fund_ratio.jsp";
	}
}
