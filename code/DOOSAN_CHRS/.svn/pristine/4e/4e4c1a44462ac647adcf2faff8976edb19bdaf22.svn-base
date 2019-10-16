package com.ait.kpa.cmd.util;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-04-19
 * 
 */
public class PayCalculationSigns extends PaAbstractCommand {

	private PaServices paServices = null;

	public PayCalculationSigns() {
		paServices = PaServices.getInstance();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		if (!content.equals("")&& content.equals("PayCalculationSignsForSearch")) {// 工资计算表识搜索
			returnPage = this.PayCalculationSignsForSearch(request, admin);
		} else if (!content.equals("")&& content.equals("PayCalculationSignsForUpdate")) {
			returnPage = this.PayCalculationSignsForUpdate(request, admin);
		} else  if(!content.equals("")&& content.equals("PayCalculationSignsPage")){
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			int resultCount=0;
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			returnPage ="/kpa0208.jsp";
		} else{
			returnPage = "error.jsp";
		}
		return returnPage;
	}

	public String PayCalculationSignsForSearch(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			parameterObject=ObjectBindUtil.getData(request);
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
		
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int resultCount = paServices.PayCalculationSignsForSearchNumber(parameterObject);
			List employeeListForFlag=paServices.PayCalculationSignsForSearch(parameterObject,currentPage,pageSize);
			request.setAttribute("deptID", request.getParameter("deptID")!=null?request.getParameter("deptID"):"");
			request.setAttribute("key", request.getParameter("key")!=null?request.getParameter("key"):"");
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("employeeListForFlag", employeeListForFlag);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"PayCalculationSignsForSearch happens Exception. ", e);
		}

		return "/kpa0208.jsp";

	}

	public String PayCalculationSignsForUpdate(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			String[] args = request.getParameterValues("selectC");
			int size=args!=null?args.length:0;
			for (int i=0;i<size;i++) {
				parameterObject= new SimpleMap();
				parameterObject.set("empid",request.getParameter("empid"+args[i]));
				parameterObject.set("flag",request.getParameter("calcFlag"+args[i]));
				paServices.PayCalculationSignsForUpdate(parameterObject);				
			}
			request.setAttribute("deptID", request.getParameter("deptID")!=null?request.getParameter("deptID"):"");
			request.setAttribute("key", request.getParameter("key")!=null?request.getParameter("key"):"");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"PayCalculationSignsForUpdate happens Exception. ", e);
		}
		return this.PayCalculationSignsForSearch(request, admin);
	}

}
