package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ga.business.GaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author wangbin
 * @Date 2009-07-24
 * @version 1.0
 * 
 */
public class GetSmockRelationUnitCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
    	GaServices gaServices = new GaServices();
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		String cpnyId=admin.getCpnyId();
		parameterObject.setString("cpnyId", cpnyId);
		parameterObject.setString("smockName", request.getParameter("condition"));
		
		List empList = gaServices.getSmockRelationUnitList(parameterObject);
    				
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		str.append("<table>");
        if (empList.size() > 0) {           
            Iterator iter = empList.iterator();
            if(iter.hasNext()) {
            	SimpleMap simpleMap = (SimpleMap) iter.next();
            	str.append("<tr>") ;
            	//单位
            	str.append("<td>") ; 
            	str.append(simpleMap.getString("UNITNAME")) ;
            	str.append("</td>") ;
            	//尺寸类型CODE
            	str.append("<td>") ; 
            	str.append(simpleMap.getString("SIZETYPE"));
            	str.append("</td>") ;
            	
            	str.append("</tr>") ;
            }
        }
        else {
        	str.append("<tr>") ;
        	str.append("<td>") ; 
        	str.append("请先添加工作服信息!") ;
        	str.append("</td>") ;
        	str.append("<td>") ;
        	str.append("</td>") ;
        	str.append("</tr>") ;
		}
        
        str.append("</table>") ;
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}
}
