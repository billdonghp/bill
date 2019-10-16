/*
 * @(#)UpdatePostLevelCmd.java 1.0 2007-9-9 下午03:11:46
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postlevel;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.util.StringUtil;
import com.ait.web.Command;


public class UpdatePostLevelCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  SysService syservice=SysService.getInstance();
		  
		  SimpleMap map=getRquestData(request);
		     
	      syservice.updatePostLevel(map);    
	      
	      List listdata=syservice.getPostLevelList();
	      
	      request.setAttribute("postlevellist",listdata);
	    
			return "sy/postgradelevel.jsp";         
	}
	  
	public SimpleMap getRquestData(HttpServletRequest request)
	{
		 HttpSession session = request.getSession(false);       
		     
		    AdminBean ab=(AdminBean) session.getAttribute("admin") ;
		    
		    String postlevelid=  StringUtil.toCN(request.getParameter("postlevelid"));
		    String postlevelname=StringUtil.toCN(request.getParameter("postlevelname"));
		    String postlevelenname=StringUtil.toCN(request.getParameter("postlevelenname"));
		    String postgradelevelvalue=StringUtil.toCN(request.getParameter("postgradelevelvalue"));
		    String oldpostlevelid=StringUtil.toCN((String)request.getSession(false).getAttribute("XID"));    
		    SimpleMap map=new SimpleMap();                                 
		    map.put("UPDATEEDBY",ab.getAdminID());    
		    map.put("POSTLEVELID",postlevelid);  
		    map.put("POSTLEVELNAME",postlevelname);          
		    map.put("POSTLEVELENNAME",postlevelenname);
		    map.put("POSTGRADELEVELVALUE",postgradelevelvalue);
		    map.put("OLDPOSTLEVELID",oldpostlevelid); 
		    return map;      
	}
}

