/*
 * @(#)ViewBaseInfoCmd.java 1.0 2006-12-22 下午01:03:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author wangliwei (wangliwei@ait.net.cn)
 * @Date 2006-12-22 下午01:03:53
 * @version 1.0
 * 
 */
public class HrmSearchEmployeeInfoCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource message = new MessageSource(admin.getLocale(), "UTF-8"); 
		String language = admin.getLanguagePreference() ;
    	HrmServices services = HrmServices.getInstance();
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		String cpnyId=admin.getCpnyId();
		parameterObject.setString("cpnyId", cpnyId);
		parameterObject.setString("ADMINID", admin.getAdminID()) ;
		parameterObject.setString("CONDITION", request.getParameter("condition"));
		
		List empList = services.retrieveEmpList(parameterObject);	    	
    				
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		str.append("<table width='100%' border='0' cellpadding='0' cellspacing='1' class='dr_d'>") ;
		str.append("<tr onclick='layerClose();' style='position: relative; top: expression(this.offsetParent.scrollTop);'   title='"+message.getMessage("alert.mutual.click_close")+"'> ") ;
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		//工号
		str.append(message.getMessage("display.mutual.emp_id")) ;
		str.append("</td>") ;
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		//姓名
		str.append(message.getMessage("display.mutual.name")) ;
		str.append("</td>") ;
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		//ID
		str.append("ID") ;
		str.append("</td>") ;
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		//部门
		str.append(message.getMessage("display.mutual.department")) ;
		str.append("</td>") ;
		//大小
		str.append("<td style='display:none'>") ; 
    	str.append(empList.size()) ;
    	str.append("</td>") ;
		str.append("</tr>") ;
		
        if (empList.size() > 0) {           
            Iterator iter = empList.iterator();
            while(iter.hasNext()) {
            	BasicInfo basic = (BasicInfo) iter.next();
            	String id = "\""+request.getParameter("id").toString()+"\"";
            	str.append("<tr  onClick='updateValue(this,"+id+");'>") ;
            	
            	//工号
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append(basic.getEmpID()) ;
            	str.append("</td>") ;
            	//姓名
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append( language.equals("zh")? basic.getChineseName() : basic.getPinyinName() ) ;
            	str.append("</td>") ;
            	//ID
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append(basic.getPersonId()) ;
            	str.append("</td>") ;
            	//部门
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append( language.equals("zh")? basic.getDepartment() : basic.getEnglishDept() ) ;
            	str.append("</td>") ;
            	//岗位职务
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap' style='display:none'>") ; 
            	str.append( language.equals("zh")? basic.getPost() : basic.getEnglishPost() ) ;
            	str.append("</td>") ;
            	//职位
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap' style='display:none'>") ; 
            	str.append( language.equals("zh")? basic.getPosition() : basic.getEnglishPosition() ) ;
            	str.append("</td>") ;
            	//身份证号
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap' style='display:none'>") ; 
            	str.append( basic.getIdCard()) ;
            	str.append("</td>") ;
            	//入社日期            	
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap' style='display:none'>") ; 
            	str.append(  basic.getJoinDate() ) ;
            	str.append("</td>") ;
            	//离职日期
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap' style='display:none'>") ; 
            	str.append( basic.getDateLeft() ) ;
            	str.append("</td>") ;
            	
            	str.append("</tr>") ;
            }
        } else {	
        	str.append("<tr><td colspan='4' align='center'>") ;
//        	没有你要查询的人员！
        	str.append(message.getMessage("display.mutual.the_employee_you_searched_is_not_available")) ;
        	str.append("</td></tr>") ;
        }
        
        str.append("</table>") ;
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}
}
