package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class ChangeMeetRoomList implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource message = new MessageSource(admin.getLocale(), "UTF-8");
		SimpleMap simpleMap = new SimpleMap();
		StringBuffer str = new StringBuffer(5000) ;
		String TypeId = request.getParameter("TypeId");
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("ADMINID", admin.getAdminID()) ;
		parameterObject.setString("CONDITION", request.getParameter("condition"));
		parameterObject.setString("TypeId", TypeId) ;
		try
    	{
		ExpressApplyAndAffirmServices eaaServices = new ExpressApplyAndAffirmServices();
		simpleMap = (SimpleMap)eaaServices.meetList(parameterObject) ;
		String num = simpleMap.getString("VISITER_PEOPLE_NUM");
		String purpro = simpleMap.getString("VISITER_OBJECTIVE");
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		str.append("<input id = 'bookdate2' type='hidden' name='bookdate2' value='"+ simpleMap.getString("VISITER_DATE") +"'>");
		str.append("<input id = 'starttime1' type='hidden' name='starttime1' value='"+ simpleMap.getString("VISITER_COME_TIME") +"'>");
		str.append("<input id = 'endtime1' type='hidden' name='endtime1' value='"+ simpleMap.getString("VISITER_END_TIME") +"'>");
		
		str.append("<input id = 'purpro' type='hidden' name='endtime2' value='"+ purpro +"'>");
		str.append("<input id = 'num' type='hidden' name='endtime2' value='"+ num +"'>");
		
		out.println(str.toString());
		out.close();
		
		// TODO Auto-generated method stub
    	}catch(Exception e)
    	{
    		e.printStackTrace() ;
    	}
		return "";
	}

}
