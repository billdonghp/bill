/*
 * @(#)ViewContractCmd.java 1.0 2006-12-19 下午04:55:33
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.contract;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @Date 2006-12-19 下午04:55:33
 * @version 1.0   
 * 
 */
public class ViewContractCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			this.putDeptTree(request);
			this.putToolbarInfo(request);

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
			    HrmServices services = HrmServices.getInstance();                                                     
			    List list = services.searchViewContract(simpleMap,currentPage,pageSize);
			  
		      resultCount = NumberUtil.parseInt(services.searchViewContraCnt(simpleMap).toString(), 0);
			    request.setAttribute("searchViewContractList", list);
			    request.setAttribute("resultCount", resultCount);           
			    request.setAttribute("currentPage", currentPage );                 
			    request.setAttribute("pageSize", pageSize );
			    request.setAttribute("pageGroupsize", pageGroupSize);
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/hrm/hrm_view_cont.jsp";
	}

}
