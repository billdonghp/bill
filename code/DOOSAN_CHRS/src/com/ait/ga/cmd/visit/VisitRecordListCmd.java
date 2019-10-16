package com.ait.ga.cmd.visit;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ga.business.GaServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;

import com.ait.util.StringUtil;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wanggang@ait.net.cn)
 * @Date 2008-1-25
 * 
 */
public class VisitRecordListCmd extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.putToolbarInfo(request);
		GaServices services = new GaServices();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;

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
			//parameterObject.set("supervisor", adminId);
			// visit record list
			List visitList = services.visitRecordList(parameterObject, currentPage, pageSize);
			Object visitCount = services.visitRecordListCount(parameterObject);

			request.setAttribute("visitList", visitList);
			request.setAttribute("visitCount", Integer.parseInt(visitCount.toString()));

			// search parameter
			request.setAttribute("CARD_TYPE", parameterObject.getString("CARD_TYPE"));
			request.setAttribute("R_TIME", parameterObject.getString("R_TIME"));
			request.setAttribute("DEPTID", parameterObject.getString("DEPTID"));
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("REPAST_TYPE", parameterObject.getString("REPAST_TYPE"));
			request.setAttribute("INSERT_BY", parameterObject.getString("INSERT_BY"));

			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("general visit record list by paging Exception. ", e);
		}

		return "/ga_view_visit.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
