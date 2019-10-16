/*
 * @(#)TemplateCommand.java 1.0 2006-12-6 上午11:54:51
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.template.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.sqlmap.template.content.TemplateContent;
import com.ait.sqlmap.util.NavigationMapper;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-6 上午11:54:50
 * @version 1.0
 * 
 */
public class TemplateCommand implements Command {

	/* 
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = new TemplateContent().transfer(request, response);
		
		// test read navigation.xml file
//		String webPath = "C:/AADEV/workspace/LSFC/web/";
//		NavigationMapper mapper = new NavigationMapper(webPath);
//		Object object = mapper.getMapObjectByMapParam("template");
//		TemplateCommand command = (TemplateCommand)object;
//		System.out.println("value : " + command.getName());
		
 
		return url;
	}
	
	public String getName() {
		return "hello!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
	}

}
