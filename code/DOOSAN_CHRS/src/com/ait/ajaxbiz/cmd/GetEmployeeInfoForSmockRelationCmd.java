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
public class GetEmployeeInfoForSmockRelationCmd implements Command {

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
		parameterObject.setString("personId", request.getParameter("condition"));
		
		List empList = gaServices.getEmployeeInfoList(parameterObject); 	
    				
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		str.append("<table>");
        if (empList.size() > 0) {           
            Iterator iter = empList.iterator();
            while(iter.hasNext()) {
            	SimpleMap simpleMap = (SimpleMap) iter.next();
            	str.append("<tr>") ;
            	//姓名
            	str.append("<td>") ; 
            	str.append(simpleMap.getString("CHINESENAME")) ;
            	str.append("</td>") ;
            	//性别
            	str.append("<td>") ; 
            	str.append(simpleMap.getString("SEX"));
            	str.append("</td>") ;
            	//课组
            	str.append("<td>") ; 
            	str.append( simpleMap.getString("DEPTNAME") ) ;
            	str.append("</td>") ;
            	//部门
            	str.append("<td>") ; 
            	str.append( simpleMap.getString("FOURTHDEPT") ) ;
            	str.append("</td>") ;
            	//入社时间
            	str.append("<td>") ; 
            	str.append( simpleMap.getString("DATESTARTED") ) ;
            	str.append("</td>") ;
            	
            	str.append("</tr>") ;
            }
        } else {	
        	str.append("<tr><td>") ;
//        	没有你要查询的人员！
        	str.append("n") ;
        	str.append("</td></tr>") ;
        }
        
        str.append("</table>") ;
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}
}
