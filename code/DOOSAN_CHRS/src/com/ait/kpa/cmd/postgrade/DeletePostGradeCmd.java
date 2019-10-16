/*
 * @(#)DeletePostGradeCmd.java 1.0 2007-9-7 上午12:02:47
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.postgrade;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.pa.business.PaServices;
import com.ait.sy.service.SysService;
import com.ait.web.Command;


public class DeletePostGradeCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PaServices paService = PaServices.getInstance();
		String id = request.getParameter("postgradeid");
		
		paService.deletePostGrade(id);
		
		return "paControlServlet?operation=viewpostgradecmd&menu_code=" + request.getParameter("menu_code");   
	}
}

