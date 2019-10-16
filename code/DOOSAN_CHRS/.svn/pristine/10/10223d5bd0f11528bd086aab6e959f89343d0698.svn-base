/*
 * @(#)UpdatePostCmd.java 1.0 2007-9-8 上午02:03:26
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class UpdatePostCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SysService syservice = SysService.getInstance();
		SimpleMap map = getRquestData(request);
		syservice.updatePost(map);
		return "syControlServlet?operation=viewpostcmd";
	}

	public SimpleMap getRquestData(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		AdminBean ab = (AdminBean) session.getAttribute("admin");
		String chinsename = ab.getChineseName();
		String postid = StringUtil.toCN(request.getParameter("postid"));
		String postname = StringUtil.toCN(request.getParameter("postname"));
		String postenname = StringUtil.toCN(request.getParameter("postenname"));
		String oldpostid = StringUtil.toCN(request.getParameter("oldpostid"));
		String postgroupid = StringUtil.toCN(request.getParameter("postgroupid"));
		SimpleMap map = new SimpleMap();
		map.put("UPDATEDBY", chinsename);
		map.put("POSTID", postid);
		map.put("POSTNAME", postname);
		map.put("POSTENNAME", postenname);
		map.put("OLDPOSTID", oldpostid);
		map.put("POSTGROUPID", postgroupid);
		return map;
	}
}
