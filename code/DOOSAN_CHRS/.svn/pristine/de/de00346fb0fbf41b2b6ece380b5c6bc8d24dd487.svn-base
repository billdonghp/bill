/*
 * @(#)ViewPostCmd.java 1.0 2007-9-8 上午02:03:13
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.essovertime;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.config.ConfigurationException;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.sy.servlet.SysAbstractCommand;
import com.ait.util.NumberUtil;
import com.ait.web.Command;


public class ViewEssOvertimeParamCmd extends SysAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    String url="/error.jsp";
	    SysService syservice = SysService.getInstance();
	    AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		SimpleMap parameterObject;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpnyId", admin.getCpnyId());
			
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			int resultCount = syservice.getEssOvertimeParamListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize) && resultCount != 0) {

				currentPage = resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize;
			}
			
			List essOvertimeParmList = syservice.getEssOvertimeParamList(parameterObject, currentPage, pageSize);
			
			
			request.setAttribute("essOvertimeParmList", essOvertimeParmList) ;
			request.setAttribute("CONDITION_TYPE", parameterObject.get("CONDITION_TYPE")) ;
			

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		        
		} catch (ConfigurationException e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve ess leave parm list Exception. ", e);
		}
		
		return "sy/sy_view_essovertime.jsp" ;	  
	}
     
}

