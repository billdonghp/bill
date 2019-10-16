package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.web.Command;

public class getLineGXjndjoCmd implements Command {

	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		SimpleMap parameterObject = new SimpleMap();
		String pro = StringUtils.trimToEmpty(request.getParameter("pro"));
		String processtpositionObjName=StringUtils.trimToEmpty(request.getParameter("processtpositionObjName"));
		String number = StringUtils.trimToEmpty(request.getParameter("number"));
		parameterObject.put("pro", pro);
		CommonDAO commonDAO = new CommonDAO();
		StringBuffer htmlPosition = new StringBuffer();
		
		try {
		String zyzgdj ="";
		if(pro.trim()!=null&&!pro.equals("0")){
			
			List<SimpleMap> lPosition = commonDAO.retrieveLineByPro(parameterObject);
			
			if(lPosition.size()>0){
				for (SimpleMap p : lPosition) {
					
					zyzgdj = p.getString("DENGJI");
					
				}
				
				
				htmlPosition.append("<input name='skileid' type='text' readonly value='"+zyzgdj+"'>");
				
			}else{
				zyzgdj="NG";
			}
				
		}else{
			zyzgdj="NG";
		}
				
		PrintWriter out = response.getWriter();
		
		out.println(htmlPosition.toString());
		out.close();
		} catch (Exception e) {

			throw new GlRuntimeException("Get employee division Exception.", e);
		}
		return "";
	}

}
