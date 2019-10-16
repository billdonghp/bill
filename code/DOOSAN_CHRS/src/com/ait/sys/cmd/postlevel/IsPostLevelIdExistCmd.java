/*
 * @(#)IsPostLevelIdExistCmd.java 1.0 2007-9-11 下午05:03:29
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postlevel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.service.SysService;
import com.ait.web.Command;

public class IsPostLevelIdExistCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SysService syservice = SysService.getInstance();
		String postlevelid = request.getParameter("postlevelid")!=null?request.getParameter("postlevelid"):"";
		String url = "";
		String dowhat = request.getParameter("dowhat");
		if (dowhat.equals("add")) {
			boolean isPostLevelIdExist = syservice.isPostLevelIdExsit(postlevelid);
			if (isPostLevelIdExist == true) {
				request.setAttribute("message", "Inputed id is already Exist,Please change another one");
			} else {
				request.setAttribute("postlevelid", postlevelid);
			}                    
			url = "sy/postgradeleveladd.jsp";
		} else {
			boolean isPostLevelIdExist = syservice.isPostLevelIdExsit(postlevelid);  
			String oldid = (String)request.getSession().getAttribute("XID");//得到要更改的职群的old id
			SimpleMap map = (SimpleMap) syservice.getPostLevelById(oldid); //重数据库中得到将要更改的记录
			if (isPostLevelIdExist == true) {
				request.setAttribute("message", "Inputed id is already Exist,Please change another one");
				request.setAttribute("simplemap", map);
			} else {
				map.put("POST_GRADE_LEVEL_ID", postlevelid);//将拥护输入的id设置到map对象中   
				request.setAttribute("simplemap", map);
			}
			url = "sy/postgradelevelupdate.jsp";
		}  
		return url;
	}
}
