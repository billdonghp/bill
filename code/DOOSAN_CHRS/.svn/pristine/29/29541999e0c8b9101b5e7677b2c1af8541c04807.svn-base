/*
 * @(#)ArVacationCommand.java 1.0 2007-10-22 下午07:59:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.arFactoryTotal;

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
import com.ait.util.StringUtil;

public class ArFactoryTotalCmd extends ArAbstractCommand {

	
	public String execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		 TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
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
			parameterObject.set("supervisor", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			String vac_tp = StringUtil.checkNull(parameterObject.getString("vac_tp")) ;
			
//			 取得数据行数
			List arFactroyTotalList = services.retrieveArFactroyTotalList(parameterObject, currentPage, pageSize) ;
			List result = services.retrieveArFactroyTotalListSum(parameterObject);
			int resultCount = result.size();
			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {

				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			Logger.getLogger(getClass()).error("pageSize=" + pageSize);
			
			request.setAttribute("vac_tp", parameterObject.get("vac_tp"));
			request.setAttribute("deptid", parameterObject.get("deptid"));
			request.setAttribute("key", parameterObject.get("key"));
			request.setAttribute("arFactroyTotalList", arFactroyTotalList);
			request.setAttribute("vac_id", parameterObject.get("vac_id"));
			request.setAttribute("month", parameterObject.get("month"));
			request.setAttribute("menu_code", request.getParameter("menu_code"));
			request.setAttribute("currentListCnt", resultCount);
			request.setAttribute("admin", admin.getAdminID());

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve factoryTotal list by paging Exception. ", e);
		}

		return "/ar_view_factoryTotal.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
