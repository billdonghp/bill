/*
 * @(#)EmailLoginCommand.java 1.0 2007-7-2 下午01:37:02
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.util.GetPassword;
import com.ait.util.GetUserName;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-7-2 下午01:37:02
 * @version 1.0
 * 
 */
public class EmailLoginCommand implements Command {

	private SysService sysService;

	private GetPassword getPassword;

	public EmailLoginCommand() {

		sysService = SysService.getInstance();
		getPassword = new GetPassword();
	}

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetUserName getUserName = new GetUserName();
		String userName="";
		try {
			userName = getUserName.getDecodeUserNameErp(request.getParameter("a"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		String menu_code = request.getParameter("menu_code");

		if (userName == null || password == null || userName.equals("") || password.equals("") || userName.equals("null") || password.equals("null")) {

			return "/loginfailed.jsp";
		}

		try {
			userName = getPassword.getDecodePasswdErp(userName);
			password = getPassword.getDecodePasswdErp(password);

			AdminBean admin = sysService.login(userName, password);
			if (admin == null) {
				return "/loginfailed.jsp";
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("admin", admin);
			String url = "essControlServlet?operation=view&content=" + content + "&menu_code=" + menu_code;
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return "/loginfailed.jsp";
		}
	}
}
