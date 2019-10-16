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

public class getProcessJcoentCmd implements Command {

	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		SimpleMap parameterObject = new SimpleMap();
		String processid = StringUtils.trimToEmpty(request.getParameter("processid"));
		String JcoentpositionObjName=StringUtils.trimToEmpty(request.getParameter("jcoentpositionObjName"));
		parameterObject.put("processid", processid);
		CommonDAO commonDAO = new CommonDAO();
		List<SimpleMap> lPosition = commonDAO.retrieveProcessByJcoent(parameterObject);
		StringBuffer htmlPosition = new StringBuffer();
		
		if(processid.equals("请选择")||lPosition.size()==0){
		
		htmlPosition.append("<select name='"+JcoentpositionObjName+"'>");
		htmlPosition.append("<option value='请选择'>请选择</option></select>");
		}else{
			htmlPosition.append("<select name='"+JcoentpositionObjName+"'>");
			for (SimpleMap p : lPosition) {
				htmlPosition.append("<option value='" + p.getString("JCOENTID")+"'");
				htmlPosition.append(">");
				htmlPosition.append(p.getString("JCOENTNAME"));
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
