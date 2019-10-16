package com.ait.ar.cmd.overtimeplan;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil ;
import com.ait.util.StringUtil ;

public class RetrieveOTPlanListCmd extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
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
			parameterObject.set("supervisor", admin.getAdminID());
			parameterObject.set("cnyId", admin.getCpnyId());
			parameterObject.set("planMonth", StringUtil.checkNull(parameterObject.getString("year")) + StringUtil.checkNull(parameterObject.getString("month")));

			// retrieve attendance record list
			List otPlanList = services.retrieveOTPlanRecordList(parameterObject, currentPage, pageSize);
			Object recordCount = services.retrieveOTPlanRecordListCount(parameterObject);
		
			request.setAttribute("otPlanList", otPlanList);
			request.setAttribute("recordCount", NumberUtil.parseInt(recordCount)) ;

			// search parameter
			request.setAttribute("year", parameterObject.getString("year"));
			request.setAttribute("month", parameterObject.getString("month"));
			request.setAttribute("deptid", parameterObject.getString("deptid"));
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("personId", parameterObject.getString("personId"));

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}

		return "/ar_view_otplan.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
