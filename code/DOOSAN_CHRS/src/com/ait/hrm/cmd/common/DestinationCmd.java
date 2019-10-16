/*
 * @(#)DirectionCmd.java 1.0 2007-1-23 下午01:54:13
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.common;

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
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Hue (sunhui@ait.net.cn)
 * @Date 2007-1-23 下午01:54:13
 * @version 1.0
 * 
 */
public class DestinationCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
			SimpleMap sMap = new SimpleMap();
		    sMap = ObjectBindUtil.getData(request);
		    
		    Set s=sMap.keySet();
		    Iterator iter=s.iterator();
		    while(iter.hasNext()){
			String key = (String)iter.next();
			request.setAttribute(key, sMap.get(key));
		    }
			this.putDeptTreeByAdmin(request);
			this.putToolbarInfo(request);
			
//			if (request.getParameter("DEPTID") != null) {
//				this.putEmpListByAdmin(request);
//			}
			
			sMap = ObjectBindUtil.getData(request);
//			sMap.put("ADMINID", admin.getAdminID());
			sMap.put("cpnyId", admin.getCpnyId());
			
			HrmServices services = HrmServices.getInstance();

			List list = services.retrieveEmpList(sMap);

			request.setAttribute("emp_list", list);
			request.setAttribute("empSize", list.size());
			
		}catch(Exception e){
			e.printStackTrace();
		    Logger.getLogger(this.getClass()).error(e.toString());
		}
		String core;
		core = request.getParameter("destination");
		String url;
		url = "/hrm/"+core+".jsp";
		
		return url;
	}

}

