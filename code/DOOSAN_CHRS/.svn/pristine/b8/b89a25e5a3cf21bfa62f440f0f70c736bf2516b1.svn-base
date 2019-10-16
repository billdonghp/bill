/*
 * @(#)executeExperience.java 1.0 2007-1-3 下午09:57:41
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author aqing (eqing@ait.net.cn)
 * @Date 2007-1-3 下午09:57:41
 * @version 1.0
 * 
 */
public class ExecuteExperience implements Command {

    /*
         * (non-Javadoc)
         * 
         * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    @SuppressWarnings("unchecked")
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// 发令执行
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String date = format.format(Calendar.getInstance().getTime());
	HrmServices services = HrmServices.getInstance();
	
	try {
	    Logger.getLogger(getClass()).debug("-------------执行发令------------");
	    //调动发令
	    SimpleMap expMap = new SimpleMap();
	    expMap.put("START_DATE_LESS_THAN", date);
	    expMap.put("ACTIVITY", "0");
	    services.batchExeExpInside(expMap);
	    Logger.getLogger(getClass()).debug("-------------调动发令完成------------");
	    // 离职发令
	    SimpleMap resignMap = new SimpleMap();
	    resignMap.put("RESIGN_DATE_LESS_THAN", date);
	    resignMap.put("ACTIVITY", "0");
	    services.batchExeResignation(resignMap);
	    
	    Logger.getLogger(getClass()).debug("-------------退社发令完成------------");
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    throw new GlRuntimeException("执行发令失败！", e);

	}
	return "/index.jsp";
    }

}
