package com.ait.gm.servlet;

import java.io.IOException;
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
import com.ait.util.NumberUtil ;
import com.ait.util.StringUtil ;

public class EateryExceptionView extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap parameterObject;
		String url ="";
		String flag = request.getParameter("flag")!=null?request.getParameter("flag"):"";
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
			//parameterObject.set("planMonth", StringUtil.checkNull(parameterObject.getString("year")) + StringUtil.checkNull(parameterObject.getString("month")));
			parameterObject.set("cpnyId", cpnyId);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//String Todaydate = format.format(Calendar.getInstance().getTime());
			System.out.print(request.getParameter("eateryDate"));
			String eateryDate = request.getParameter("eateryDate")!=null?request.getParameter("eateryDate"):format.format(Calendar.getInstance().getTime());
			//String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			
			
			parameterObject.set("eateryDate", eateryDate);
			//parameterObject.put("endDate", endDate);

			
			
			if(!"excel".equals(flag)){
				// retrieve attendance record list
				List eateryExceptionViewList = services.eateryExceptionViewList(parameterObject, currentPage, pageSize);
				
				Object eateryExceptionViewCount = services.eateryExceptionViewCount(parameterObject);
				
				request.setAttribute("resultList", eateryExceptionViewList);
				request.setAttribute("recordCount", eateryExceptionViewCount) ;
				
			     url = "/gm_eatery_exception_view.jsp?menu_code=" + parameterObject.getString("menu_code");
			}else if("excel".equals(flag)){
			     
				List eateryExceptionExcelList = services.eateryExceptionExcelList(parameterObject);
				request.setAttribute("resultList", eateryExceptionExcelList);
				 url = "/gm_eatery_exception_view_excel.jsp?menu_code=" + parameterObject.getString("menu_code");	
			}
			
			
			
			request.setAttribute("eateryDate", eateryDate);
			//request.setAttribute("endDate", endDate);
			
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve Eatery Exception View List  by paging Exception. ", e);
		}

		return url;
	}
}