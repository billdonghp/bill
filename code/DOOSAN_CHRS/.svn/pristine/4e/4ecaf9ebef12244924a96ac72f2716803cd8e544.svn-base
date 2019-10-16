package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class ExpressNames implements Command {
	
	private ExpressApplyAndAffirmServices eaaServices=null;
	
	public ExpressNames(){
		eaaServices = new ExpressApplyAndAffirmServices();
	}
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String citySent = request.getParameter("citySent");
		String cityarrive = request.getParameter("cityarrive");
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("citySent", citySent);
		parameterObject.set("cityarrive", cityarrive);
		parameterObject.set("cpny_id", admin.getCpnyId());
		try{
		response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		List list = eaaServices.getsendToExpressApplyPage2(parameterObject);
		str.append("<select id='expressType_0' name='expressType_0' class='ExpressType' onchange='expeses()'>");//expressType_0 原来为 expressType 因批量申请而改
		str.append("<option value=''>请选择</option>");
		for(int i=0;i<list.size();i++){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap = (SimpleMap)list.get(i);
			str.append("<option value='"+ simpleMap.getString("EXPENSES_TYPE") +"'>"+ simpleMap.getString("EXPENSES_TYPE_NAME") +"</option>");
//			email_expenses
		}
		str.append("</select>");
		for(int i=0;i<list.size();i++){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap = (SimpleMap)list.get(i);
			str.append("<input id='"+ simpleMap.getString("EXPENSES_TYPE")+"1" +"' name='"+ simpleMap.getString("EXPENSES_TYPE")+"1" +"' type='hidden' value='"+ simpleMap.getString("EMAIL_EXPENSES") +"'>");
//			email_expenses
		}
//		
		out.println(str.toString());
        out.close();
		}catch(Exception e)
    	{
    		e.printStackTrace() ;
    	}
		return "";
	}

}
