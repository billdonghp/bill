package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class VegetableNames implements Command {
	
	private ExpressApplyAndAffirmServices eaaServices=null;
	
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GMDAO services = new GMDAO();
		
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String REPAST_DATE = request.getParameter("REPAST_DATE");
		String MENU_EATTYPE = request.getParameter("MENU_EATTYPE");
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("REPAST_DATE", REPAST_DATE);
		parameterObject.set("MENU_EATTYPE", MENU_EATTYPE);
		parameterObject.set("cpnyId", admin.getCpnyId());
		try{
		response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		//List list = eaaServices.getsendToExpressApplyPage2(parameterObject);
		
		List list = services.gmVegetableByTypeAndDate(parameterObject);
		str.append("<select id='VEGET_ID' name='VEGET_ID' >");
		str.append("<option value=''>请选择</option>");
		for(int i=0;i<list.size();i++){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap = (SimpleMap)list.get(i);
			str.append("<option value='"+ simpleMap.getString("VEGET_ID") +"'>"+ simpleMap.getString("VEGET_NAME") +"</option>");
//			email_expenses
		}
		str.append("</select>");
//		for(int i=0;i<list.size();i++){
//			SimpleMap simpleMap = new SimpleMap();
//			simpleMap = (SimpleMap)list.get(i);
//			str.append("<input id='"+ simpleMap.getString("EXPENSES_TYPE")+"1" +"' name='"+ simpleMap.getString("EXPENSES_TYPE")+"1" +"' type='hidden' value='"+ simpleMap.getString("EMAIL_EXPENSES") +"'>");
////			email_expenses
//		}
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
