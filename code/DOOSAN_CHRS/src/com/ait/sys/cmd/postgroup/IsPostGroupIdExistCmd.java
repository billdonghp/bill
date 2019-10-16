/*
 * @(#)IsPostGroupIdExist.java 1.0 2007-9-7 下午04:13:26
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postgroup;
                                                    
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.service.SysService;
import com.ait.web.Command;

public class IsPostGroupIdExistCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SysService syservice = SysService.getInstance();
		String url="";
		String groupid = request.getParameter("groupid")!=null?request.getParameter("groupid"):"";
		String dowhat=request.getParameter("dowhat");
		String menu_code=request.getParameter("menu_code");
		if(dowhat.equals("add"))
		{
			boolean isPostGroupIdExist = syservice.isGroupIdExsit(groupid);
			if (isPostGroupIdExist == true) {
				request.setAttribute("message", "Inputed id is already Exist,Please change another one");
			} else {
				request.setAttribute("groupid", groupid);
			}
			url= "sy/postgroupadd.jsp?menu_code="+menu_code;
		}else
		{
			   boolean isPostGroupIdExist = syservice.isGroupIdExsit(groupid);  
			   String oldid=(String)request.getSession(false).getAttribute("XID");//得到要更改的职群的old id/
			   SimpleMap map = (SimpleMap) syservice.getPostGroupForUpdate(oldid); //重数据库中得到将要更改的记录
			if (isPostGroupIdExist == true) {
				request.setAttribute("message", "Inputed id is already Exist,Please change another one");
				  request.setAttribute("simplemap",map);  
			} else {   
				
			   map.put("POST_GROUP_ID",groupid);//将拥护输入的id设置到map对象中 
			   request.setAttribute("simplemap",map);                                                 	   
			}                               
			url= "sy/postgroupupdate.jsp?menu_code="+menu_code;    
		}
		
		return url;
	}
}
