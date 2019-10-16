/*
 * @(#)DeletePostGroupCmd.java 1.0 2007-9-6 下午11:59:09
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postgroup;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.sy.service.SysService;
import com.ait.web.Command;

public class DeletePostGroupCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SysService syservice = SysService.getInstance();
		String id = request.getParameter("postgroupid");
		syservice.deleteCascade(id);
		List listdata = syservice.getPostGroup();
		request.setAttribute("postgrouplist", listdata);
		return "sy/postgroup.jsp";
	}
}
