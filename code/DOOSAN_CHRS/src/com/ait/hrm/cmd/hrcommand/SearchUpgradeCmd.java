/*
 * @(#)SearchUpgradeCmd.java 1.0 2007-1-5 下午01:49:54
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

import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.util.NumberUtil;   
/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author aqing (eqing@ait.net.cn)
 * @Date 2007-1-5 下午01:49:54
 * @version 1.0
 * 
 */
public class SearchUpgradeCmd extends HrmAbstractCommand {

    /* (non-Javadoc)
     * @see com.ait.hrm.servlet.HrmAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	try {
	    this.putBasicInfo(request);
	    this.putToolbarInfo(request);
	    this.putDeptTreeByAdmin(request);
	    SimpleMap sMap = new SimpleMap();
	    sMap = ObjectBindUtil.getData(request);

	    UserConfiguration config = UserConfiguration.getInstance("/system.properties");
	    int pageSize = config.getInt("page.style1.pagesize");
	    int pageGroupSize = config.getInt("page.style1.pagegroupsize");
	    int currentPage = 0;
	    int resultCount = 0;    

	    currentPage = NumberUtil.parseInt(request.getParameter("currentPage"), 0);
	      
	    HrmServices services = HrmServices.getInstance();
	       
	    List expInsideList = services.getExpInside(sMap, currentPage,pageSize);
	    resultCount = NumberUtil.parseInt(services.getExpInsideCnt(sMap).toString(), 0);    
	    request.setAttribute("expInsideList", expInsideList);
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
	    e.printStackTrace();
	    Logger.getLogger(this.getClass()).error(e.toString());
	}
	return "/hrm/hrm_dl_search_upgrade.jsp";
    }

}

