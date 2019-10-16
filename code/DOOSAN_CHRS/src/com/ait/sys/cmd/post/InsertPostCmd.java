/*
 * @(#)InsertPostCmd.java 1.0 2007-9-8 上午02:01:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.post;

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


public class InsertPostCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	     SysService syservice=SysService.getInstance();
	    
	      SimpleMap simplemap=getRquestData(request);
	   
            syservice.insertPost(simplemap);   
	                 
            return "syControlServlet?operation=viewpostcmd";       
	}                     
	
	public SimpleMap getRquestData(HttpServletRequest request)
	{
		 HttpSession session = request.getSession(false);
		    
		    AdminBean ab=(AdminBean) session.getAttribute("admin") ;
		  
		    String chinsename=ab.getChineseName();
		    String postid=  StringUtil.toCN(request.getParameter("postid"));
		    String postname=StringUtil.toCN(request.getParameter("postname"));
		    String postenname=StringUtil.toCN(request.getParameter("postenname"));
		    String postgroupid=request.getParameter("postgroupid");
		    SimpleMap map=new SimpleMap();                                 
		    map.put("CREATEDBY",chinsename);    
		    map.put("POSTID",postid);                                
		    map.put("POSTNAME",postname);                       
		    map.put("POSTENNAME",postenname);                   
		    map.put("POSTGROUPID",postgroupid);                                          
		    return map;                  
	} 
	
}

