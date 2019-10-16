/*
 * @(#)ViewContractByInsertCmd.java 1.0 2006-12-19 下午04:26:26
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.contract;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.EmployeeBean;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午04:26:26
 * @version 1.0
 * 
 */
public class ViewContractByInsertCmd extends HrmAbstractCommand {

	private static final Logger logger = Logger.getLogger(ViewContractByInsertCmd.class);

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			this.putDeptTree(request);  
			this.putToolbarInfo(request);  
			
			HrmServices services = HrmServices.getInstance();
			EmployeeBean employeeBean = new EmployeeBean();
			String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
			String empID = request.getParameter("empID") != null ? request.getParameter("empID") : adminId;
			SimpleMap simpleMap = new SimpleMap();
			simpleMap = ObjectBindUtil.getData(request);
			 Set s=simpleMap.keySet();
			    Iterator iter=s.iterator();
			    while(iter.hasNext()){             
				String key = (String)iter.next();
				request.setAttribute(key, simpleMap.get(key));
			    }               
			 UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			    int pageSize = config.getInt("page.style1.pagesize");
			    int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			    int currentPage = 0;
			    int resultCount = 0;              
			    currentPage = NumberUtil.parseInt(request.getParameter("currentPage"), 0); 
				
			 List list = services.searchEmpWithoutContact(simpleMap,currentPage,pageSize);
			  
		      resultCount = NumberUtil.parseInt(services.searchEmpWithoutContactCNT(simpleMap).toString(), 0);
		      
		      request.setAttribute("searchEmpWithoutContract", list);
			    request.setAttribute("resultCount", resultCount);           
			    request.setAttribute("currentPage", currentPage );                 
			    request.setAttribute("pageSize", pageSize );
			    request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("The information Exception when running the ViewContractByInsertCmd. ", e);
		}
		return "/hrm/hrm_add_cont.jsp";
	}
}
