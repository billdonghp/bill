/*
 * @(#)ViewDispatchCmd.java 1.0 2006-12-19 下午04:58:42
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
import com.ait.sy.business.SyCodeServices;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午04:58:42
 * @version 1.0
 * 
 */
public class ViewDispatchCmd extends HrmAbstractCommand {

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
	    
	    List list=null;
	    HrmServices services = HrmServices.getInstance();
	    
	    if (request.getParameter("DEPTID") != null) {

		String transCode = StringUtil.checkNull(request.getParameter("transCode"));
		request.setAttribute("transCode", transCode);
		// 期满本职
		if (transCode.equals(SyCodeServices.getExpireRehabCode())) {
		    this.putDispatchEmpList(request);
		    // 派遣
		} else {
			 list = services.retrieveEmpListIncumbency(sMap, currentPage,pageSize);
	    	  resultCount = NumberUtil.parseInt(services.retrieveEmpListIncumbencyCNT(sMap).toString(), 0);
		}
	    }             
	    SimpleMap map=new SimpleMap();      
	    map.put("tablename","HR_DISPATCH");
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
	    throw new GlRuntimeException(" ViewDispatchCmd request Exception.",
		    e);
	}
	return "/hrm/hrm_dl_dispatch.jsp";
    }

    private void putDispatchEmpList(HttpServletRequest request) {
	SimpleMap sMap = new SimpleMap();
	String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
	sMap = ObjectBindUtil.getData(request);
	sMap.put("ADMINID", adminId);
	sMap.put("ACTIVITY", "0");
	HrmServices services = HrmServices.getInstance();
	List list = services.getDispatch(sMap);
	request.setAttribute("emp_list", list);
	request.setAttribute("empSize", list.size());
    }

}
