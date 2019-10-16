/*
 * @(#)MenuTreeCommand.java 1.0 2007-8-22 下午04:50:31
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.sy.bean.AdminBean;
import com.ait.utils.MenuTree;   

public class MenuTreeCommand implements Command {
	
	private boolean flag=false;

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		AdminBean admin = (AdminBean) session.getAttribute("admin");

		String fileurl = (String) session.getAttribute("url");

		MenuTree mt = new MenuTree(); 
		
		String filename = fileurl+"skin\\xmltree\\data.xml";
		
		if(flag==false)
		{
	     flag = mt.createXML(filename, admin.getScreenGrantNo());
		}
		return "/MenuTree.jsp";
	}

}
