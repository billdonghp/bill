/*
 * @(#)ChangeLanguageCommand.java 1.0 2007-9-14 下午03:57:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.util.StringUtil ;

import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-14 下午03:57:49
 * @version 1.0
 * 
 */
public class ChangeLanguageCommand implements Command {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AdminBean admin = (AdminBean)request.getSession(true).getAttribute("admin");
		String language = StringUtil.checkNull(request.getParameter("lang"),"zh");
		String country = null;
		
       if (language.equals("zh")) {
			
			language = "zh";
			country = "CN";
		} else if (language.equals("en")) {
			
			language = "en";
			country = "US";
		}else {
			
			language = "ko";
			country = "KO";
		}

		admin.setLanguagePreference(language);
		admin.setCountryPreference(country);
		request.getSession(true).setAttribute("country", country);
		request.getSession(true).setAttribute("language", language);
		request.getSession(true).setAttribute("admin", admin);
		return "sys/errorpage.jsp";
	}

}

