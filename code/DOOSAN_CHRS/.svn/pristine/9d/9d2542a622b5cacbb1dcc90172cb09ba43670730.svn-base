/*
 * @(#)InsertPostGroupCmd.java 1.0 2007-9-6 下午11:58:21
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postgroup;

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


public class InsertPostGroupCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      SysService syservice=SysService.getInstance();
    
      SimpleMap map=getRquestData(request);
     
      syservice.insertPostGroup(map);
      
      List listdata=syservice.getPostGroup(); 
      
      String menu_code=(String) request.getParameter("menu_code");
      
      request.setAttribute("postgrouplist",listdata);                       
    
		return "sy/postgroup.jsp?menu_code="+menu_code;                   
	}
	
	
	public SimpleMap getRquestData(HttpServletRequest request)
	{
		 HttpSession session = request.getSession(false);
		    
		    AdminBean ab=(AdminBean) session.getAttribute("admin") ;

		    String groupid=  StringUtil.toCN(request.getParameter("groupid"));
		    String postgroupname=StringUtil.toCN(request.getParameter("postgroupname"));
		    String postgroupenname=StringUtil.toCN(request.getParameter("postgroupenname"));
		    SimpleMap map=new SimpleMap();                                 
		    map.put("CREATEDBY",ab.getAdminID());    
		    map.put("POSTGROUPID",groupid);
		    map.put("POSTGROUPNAME",postgroupname);          
		    map.put("POSTGROUPENNAME",postgroupenname);       
		    return map;    
	}
}

