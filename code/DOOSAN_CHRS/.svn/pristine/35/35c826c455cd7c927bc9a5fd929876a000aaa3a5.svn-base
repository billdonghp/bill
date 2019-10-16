package com.ait.gm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.config.ConfigurationException;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class SearhEatLook extends ArAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pageSize = 0;
		this.putToolbarInfo(request);
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap parameterObject = new SimpleMap();
		String page="";

		String StartTime = request
				.getParameter("listDDS")
				+ " "
				+ request.getParameter("listHHS")
				+ ":"
				+ request.getParameter("listMMS");
		
		

		String EndTime = request
				.getParameter("listDDE")
				+ " "
				+ request.getParameter("listHHE")
				+ ":"
				+ request.getParameter("listMME");
		if(StartTime.length()!=16){
			StartTime = "";
		}
		
		if(EndTime.length()!=16){
			EndTime = "";
		}	
		
		String flag = StringUtil.checkNull(request.getParameter("flag"),"1");
		
		String empName = request.getParameter("empName");
		
		String empID = request.getParameter("empID");
		
		String gm_type = request.getParameter("gm_type");
		
		String card_type = request.getParameter("card_type");
		
		String deptID = request.getParameter("deptID");
		
		parameterObject.setString("STARTTIME", StartTime);
		parameterObject.setString("ENDTIME", EndTime);
		parameterObject.setString("empName", empName);
		parameterObject.setString("empID", empID);
		parameterObject.setString("GM_TYPE", gm_type);
		parameterObject.setString("CARD_TYPE", card_type);
		parameterObject.setString("DEPTID", deptID);
		parameterObject.setString("ADMINID", adminId);
		parameterObject.setString("cpnyId", cpnyId);
		/* paging logic */
		UserConfiguration config = UserConfiguration
				.getInstance("/system.properties");		
		int pageGroupSize=0;
		try {
			pageSize = config.getInt("page.style1.pagesize");
		
		pageGroupSize = config.getInt("page.style1.pagegroupsize");
		} catch (ConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int currentPage = 0;
		// if has currentpage set value into currentPage
		if (request.getParameter("currentPage") != null
				&& !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
		
		
		
		if(flag.equals("1")){
		try {			
			// 取得数据行数
			Object resultCount = services.eatLookCount(parameterObject);

			List eatLookList = services.eatLookList(parameterObject,currentPage, pageSize);
			
			List listMM = DateUtil.getTimeMMList();

			List listHH = DateUtil.getTimePerHourList();

			List gmType = services.gmType(parameterObject);

			List cardType = services.cardType(parameterObject);

			request.setAttribute("gmType", gmType);
			request.setAttribute("listMM", listMM);
			request.setAttribute("listHH", listHH);
			request.setAttribute("cardType", cardType);
			request.setAttribute("eatLookList", eatLookList);
			request.setAttribute("resultCount", NumberUtil.parseInt(resultCount));
			
			// paging parameter
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" by paging Exception. ", e);
		}		

		
		   page= "/gm_Search_Eat_Look.jsp?menu_code=" + request.getParameter("menu_code");
		}
		if(flag.equals("2")){
			List eatLookList = services.eatLookList(parameterObject,0, 1000000);
			request.setAttribute("eatLookList", eatLookList);
			page= "/reports/gm_report/gm_eatery_look.jsp";
		}	
		request.setAttribute("listDDS", request.getParameter("listDDS"));
		request.setAttribute("listHHS", request.getParameter("listHHS"));
		request.setAttribute("listMMS", request.getParameter("listMMS"));
		request.setAttribute("listDDE", request.getParameter("listDDE"));
		request.setAttribute("listHHE", request.getParameter("listHHE"));
		request.setAttribute("listMME", request.getParameter("listMME"));
		
		request.setAttribute("empName", request.getParameter("empName"));
		request.setAttribute("empID", request.getParameter("empID"));
		request.setAttribute("gm_type", gm_type);
		request.setAttribute("card_type", card_type);
		request.setAttribute("deptID", deptID);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		return page;
	}
}
