/*
 * @(#)ChangeLanguageCommand.java 1.0 2007-9-14 下午03:57:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.util.GetUserName;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2009-6-11 下午03:57:49
 * @version 1.0
 * 
 */
public class ChangeDivisionCommand implements Command {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SysService service = SysService.getInstance();
		GetUserName util = new GetUserName();
		String empId = "";
		String cpnyId = "";
		List empDiff = null;
		SimpleMap result = new SimpleMap();
		
		try {
			empId = util.getDecodeUserNameErp(request.getParameter("a"));
			
			cpnyId = util.getDecodeUserNameErp(request.getParameter("b"));
			
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("adminid", empId);
			parameterObject.setString("cpnyId", cpnyId);

			empDiff = service.getEmpDiff(parameterObject);
			result = (SimpleMap) service.getGrantValidate(parameterObject);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		request.setAttribute("empDiff", empDiff);
		request.setAttribute("empDiffSize", empDiff.size());
		request.setAttribute("grantFlag", result.getInt("CNT"));
		request.setAttribute("a", empId);
		request.setAttribute("b", cpnyId);
		request.setAttribute("administrator", "ic9999999");
		request.setAttribute("chineseName", result.getString("CHINESENAME"));
		return "/login.jsp";
	}
	
	/*主页面帮助信息*/
	public List getHelpInfo(HttpServletRequest request){
		List list=null;
		SysService service = SysService.getInstance();
	
		try{
			HttpSession session = request.getSession(true);
			AdminBean admin = (AdminBean) session.getAttribute("admin");
			SimpleMap parameterObject =  new SimpleMap();	
			parameterObject.set("cpnyId", admin.getCpnyId());
	
			list=service.getHelpInfo(parameterObject);
		
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getHelpInfo happens Exception. ", e);
		}
	
		return list;
		
	  }

}
