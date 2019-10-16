/*
 * @(#)ViewPostCmd.java 1.0 2007-9-8 上午02:03:13
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.post;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.config.ConfigurationException;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.service.SysService;
import com.ait.sy.servlet.SysAbstractCommand;
import com.ait.util.NumberUtil;
import com.ait.web.Command;


public class ViewPostCmd extends SysAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    String url="/error.jsp";    
		try {
			    this.putPost(request);  
			SysService syservice = SysService.getInstance();
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize;
			int pageGroupSize;
			int currentPage = 0;
			int resultCount = 0;
			pageGroupSize = config.getInt("page.style1.pagegroupsize");
			pageSize = config.getInt("page.style1.pagesize");
			String postid = request.getParameter("post_id") != null ? request.getParameter("post_id") : "%";
			currentPage = NumberUtil.parseInt(request.getParameter("currentPage"), 0);
			List list = syservice.getPostList(postid, currentPage, pageSize);
			resultCount = NumberUtil.parseInt(syservice.getPostListCnt(postid).toString(), 0);
			request.setAttribute("postlist", list);
			request.setAttribute("resultCount", resultCount);
			currentPage = NumberUtil.parseInt(request.getParameter("currentPage"), 0);
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
			String menu_code = request.getParameter("menu_code") != null ? request.getParameter("menu_code") : "";
			url = "sy/sypost.jsp?menu_code=" + menu_code + "&post_id=" + postid;   
		        
		} catch (ConfigurationException e) {  
			e.printStackTrace();   
		}                               
		 return url;	  
		}
     
}

