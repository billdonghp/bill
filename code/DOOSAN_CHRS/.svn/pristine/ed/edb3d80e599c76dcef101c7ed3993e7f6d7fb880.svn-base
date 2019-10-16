package com.ait.gm.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil ;
import com.ait.util.StringUtil ;

public class eatLook extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap parameterObject;
		///SimpleMap map = null;
		String flag = StringUtil.checkNull(request.getParameter("flag"),"1");
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpnyId", cpnyId);
			// retrieve attendance record list
			Object eatLookCount = 0;
			
			
			
			List listMM=DateUtil.getTimeMMList();
			
			List listHH=DateUtil.getTimePerHourList();

			
			List gmType =  services.gmType(parameterObject);
			
			List cardType = services.cardType(parameterObject);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String Todaydate = format.format(Calendar.getInstance().getTime());
			
			
			Calendar c = Calendar.getInstance() ;
			c.add(Calendar.DATE, -1) ;
			String YestedateDate = format.format(c.getTime());
			
			request.setAttribute("Todaydate", Todaydate);
			request.setAttribute("YestedateDate",YestedateDate);
			
			request.setAttribute("gmType", gmType);
			request.setAttribute("listMM",listMM);
			request.setAttribute("listHH",listHH);
			request.setAttribute("cardType",cardType);
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;


			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}
		if(flag.equals("1")){
			return "gm/eatLook.jsp?menu_code=" + parameterObject.getString("menu_code");
		}
		if(flag.equals("2")){
			return "/reports/gm_report/gm_eatery_look.jsp";
		}
		return flag;
	}
}
