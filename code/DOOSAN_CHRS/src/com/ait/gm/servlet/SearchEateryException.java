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
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class SearchEateryException extends ArAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		SimpleMap parameterObject = new SimpleMap();
		
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			String listDDS = request.getParameter("listDDS");
			
			String listDDE = request.getParameter("listDDE");
			

			parameterObject.setString("listDDS", listDDS);
			parameterObject.setString("listDDE", listDDE);
			parameterObject.setString("supervisor", adminId);
			parameterObject.set("cpnyId", cpnyId);

			// 取得数据行数
			int resultCount = services.LookEateryExceptionCount(parameterObject);

			List LookList = services.LookEateryExceptionList(parameterObject,currentPage, pageSize);
			
			request.setAttribute("LookList", LookList);
			request.setAttribute("recordCount", NumberUtil
					.parseInt(resultCount));
			List gmType = services.Gm_Type(parameterObject);
			
			request.setAttribute("gmType",gmType);
			request.setAttribute("listDDS", listDDS);
			request.setAttribute("listDDE", listDDE);
			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" by paging Exception. ", e);
		}

		return "gm/gm_EateryException.jsp?menu_code=" + request.getParameter("menu_code");
	}
}
