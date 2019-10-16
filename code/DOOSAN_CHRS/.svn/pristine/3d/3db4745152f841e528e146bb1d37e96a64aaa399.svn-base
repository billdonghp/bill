package com.ait.gm.servlet;

import java.io.IOException;
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

public class GmViewCommand extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
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
			parameterObject.set("supervisor", adminId);
			parameterObject.set("planMonth", StringUtil.checkNull(parameterObject.getString("year")) + StringUtil.checkNull(parameterObject.getString("month")));
			parameterObject.set("cpnyId", cpnyId);

			// retrieve attendance record list
			List gmViewList = services.gmViewList(parameterObject, currentPage, pageSize);
			Object gmViewCount = services.gmViewCount(parameterObject);
			
			request.setAttribute("gmViewList", gmViewList);
			request.setAttribute("recordCount", NumberUtil.parseInt(gmViewCount)) ;

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}

		return "/gm0100.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}