package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.ait.commons.dao.CommonDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.web.Command;

public class getLineProcess2Cmd implements Command {

	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		SimpleMap parameterObject = new SimpleMap();
		String lineid = StringUtils.trimToEmpty(request.getParameter("lineid"));
		String processtpositionObjName=StringUtils.trimToEmpty(request.getParameter("processtpositionObjName"));
		String number = StringUtils.trimToEmpty(request.getParameter("number"));
		parameterObject.put("lineid", lineid);
		CommonDAO commonDAO = new CommonDAO();
		List<SimpleMap> lPosition = commonDAO.retrieveLineByProcess(parameterObject);
		StringBuffer htmlPosition = new StringBuffer();
		if(lineid.equals("请选择")||lPosition.size()==0){
		
		htmlPosition.append("<select name='"+processtpositionObjName+"'>");
		htmlPosition.append("<option value='请选择'>请选择</option></select>");
		}else{
			htmlPosition.append("<select name='"+processtpositionObjName+"' onChange='fillProcessJcoent2(this.value,"+number+");'>");
			for (SimpleMap p : lPosition) {
				htmlPosition.append("<option value='" + p.getString("PROCESSID")+"'");
				htmlPosition.append(">");
				htmlPosition.append(p.getString("PROCESSNAME"));
				htmlPosition.append("</option>");
			}
			htmlPosition.append("</select>");
		}
		PrintWriter out = response.getWriter();
		out.println(htmlPosition.toString());
		out.close();
		return "";
	}

}
