/*
 * @(#)DirectionCmd.java 1.0 2007-1-23 下午01:54:13
 * 
 * Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.common;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author aqing (eqing@ait.net.cn)
 * @Date 2007-1-23 下午01:54:13
 * @version 1.0
 * 
 */
public class ViewEmpByDeptCmd extends HrmAbstractCommand {

    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	try {
	    SimpleMap sMap = new SimpleMap();
	    sMap = ObjectBindUtil.getData(request);
	    this.putDeptTreeByAdmin(request);
	    Set s = sMap.keySet();
	    Iterator iter = s.iterator();
	    while (iter.hasNext()) {
		String key = (String) iter.next();
		request.setAttribute(key, sMap.get(key));
	    }
	    if (request.getParameter("DEPTID") != null) {
		this.putEmpListByAdmin(request);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    Logger.getLogger(this.getClass()).error(e.toString());
	}
	return "/hrm/inc/hrm_SearchEmpByDept.jsp";
    }

}
