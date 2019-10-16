/*
 * @(#)ViewUpgradeInfoFieldCmd.java 1.0 2007-9-17 下午03:50:17
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.tipmessagesearch;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.config.ConfigurationException;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.web.Command;


public class ViewUpgradeInfoFieldCmd extends HrmAbstractCommand{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		    	this.putDeptTree(request);
		    	this.putToolbarInfo(request);
				HrmServices services = HrmServices.getInstance();
				SimpleMap map=new SimpleMap();
				String deptid=request.getParameter("DEPTID")!=null?request.getParameter("DEPTID"):"Z000000";
				String sDate=request.getParameter("sDate");
				String eDate=request.getParameter("eDate");             
				String empSort=request.getParameter("joinTypeCode");
				String key=StringUtil.toCN(request.getParameter("EMPIDORCHINESENAME"));
				map.put("deptid",deptid);
				map.put("sDate",sDate);
				map.put("eDate",eDate);
				map.put("key",key);                                  
				map.put("empSort",empSort);     
				
				request.setAttribute("deptid",deptid);
				request.setAttribute("sDate",sDate);            
				request.setAttribute("eDate",eDate);
				request.setAttribute("key",key);               
				request.setAttribute("empSort",empSort);   
				
			   UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			   int pageSize = config.getInt("page.style1.pagesize");
			   int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			   int currentPage = 0;          
			   int resultCount = 0;                 
			   currentPage = NumberUtil.parseInt(request.getParameter("currentPage"), 0);
			   List listdata=services.getUpgradeInfoFieldByCondition(map,currentPage,pageSize);
			   resultCount = NumberUtil.parseInt(services.getUpgradeInfoFieldCNT(map).toString(), 0);      
			                                   
		  	   request.setAttribute("listdata",listdata);                   
			   request.setAttribute("resultCount", resultCount);                 
			   request.setAttribute("currentPage", currentPage);     
			   request.setAttribute("pageSize", pageSize );                    
			   request.setAttribute("pageGroupsize", pageGroupSize);
			                                                                        
			   
			} catch (ConfigurationException e) {          
				 e.printStackTrace();
				    Logger.getLogger(this.getClass()).error(e.toString());
			}	
			return "/hrm/hrm_upgradeinfo.jsp";
			
	}
}

