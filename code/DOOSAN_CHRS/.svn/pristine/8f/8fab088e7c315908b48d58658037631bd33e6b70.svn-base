/*
 * @(#)ViewPunishCmd.java 1.0 2006-12-23 下午05:38:12
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
              
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-23 下午05:38:11
 * @version 1.0
 * 
 */
public class ViewPunishCmd extends HrmAbstractCommand {

    /* (non-Javadoc)
     * @see com.ait.hrm.servlet.HrmAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	try {
	    this.putDeptTreeByAdmin(request);
	    this.putToolbarInfo(request);
	    SimpleMap sMap = new SimpleMap();
	    sMap = ObjectBindUtil.getData(request);
		String adminId = ((AdminBean) request.getSession(false)  
				.getAttribute("admin")).getAdminID();
		sMap.put("ADMINID", adminId);
		  UserConfiguration config = UserConfiguration.getInstance("/system.properties");
		    int pageSize = config.getInt("page.style3.pagesize");
		    int pageGroupSize = config.getInt("page.style3.pagegroupsize");
		    int currentPage = 0;
		    int resultCount = 0;    

		    currentPage = NumberUtil.parseInt(request.getParameter("currentPage"), 0);
		      
		    HrmServices services = HrmServices.getInstance();
		         
		    List list=null;
		    if (request.getParameter("DEPTID") != null) {
		    	  list = services.retrieveEmpListIncumbency(sMap, currentPage,pageSize);
		    	  resultCount = NumberUtil.parseInt(services.retrieveEmpListIncumbencyCNT(sMap).toString(), 0);
			}
		    
		    SimpleMap map=new SimpleMap();      
		    map.put("tablename","HR_PUNISHMENT");
		    String lasttransno=services.getLastTransNO(map);   
		    request.setAttribute("lasttransno", lasttransno);
		    request.setAttribute("emp_list", list);
		    request.setAttribute("resultCount", resultCount);                    
		    request.setAttribute("currentPage", currentPage);
		    request.setAttribute("pageSize", pageSize);
		    request.setAttribute("pageGroupsize", pageGroupSize);  
		    
		    Set s=sMap.keySet();                                          
		    Iterator iter=s.iterator();          
		    while(iter.hasNext()){             
			String key = (String)iter.next();
			request.setAttribute(key, sMap.get(key));
		    }   
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    throw new GlRuntimeException(" ViewPunishCmd request Exception.", e);
	}
	return "/hrm/hrm_dl_punishment.jsp";
    }

}
