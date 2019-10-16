/*
 * @(#)RetrieveEvaluateCmd.java 1.0 2007-5-7 下午03:22:07
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.evs.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.EvsColumn;
import com.ait.evs.EvsEmp;
import com.ait.evs.EvsItemDetail;
import com.ait.evs.EvsPeriod;
import com.ait.evs.EvsScore;
import com.ait.evs.business.EvsServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-5-7 下午03:22:07
 * @version 1.0
 * 
 */
public class RetrieveEvaluate0305Cmd implements Command {

	private static final Logger logger = Logger
			.getLogger(RetrieveEvaluate0305Cmd.class);

	private EvsPeriod evsPeriod = new EvsPeriod();

	private EvsScore scoreOp = new EvsScore();

	private EvsItemDetail evsItemDetail = new EvsItemDetail();

	private EvsColumn evsColumn = new EvsColumn();

	private EvsScore evsScore = new EvsScore();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    
		EvsEmp evsEmp = new EvsEmp();
		
		String adminID = ((AdminBean) request.getSession(false).getAttribute(	"admin")).getAdminID();
		String empID = null;
		String period = null;
		
		EvsServices services = EvsServices.getInstance();
		SimpleMap parameterObject;
		SimpleMap simpleMap = new SimpleMap(); 
		simpleMap = ObjectBindUtil.getData(request);
			
		List searchCount;
		String countemp = "1";
		String craftid = request.getParameter("craftid");	
		String skileid = request.getParameter("skileid");
		try {

			// 取得员工ID和评价区间
			  empID = request.getParameter("EmpID") == null ? adminID
					: request.getParameter("EmpID");
			  period = request.getParameter("Period") == null ? evsPeriod
					.getCurrentEvPeriod() : request.getParameter("Period");
			//根据员工的ID和评价区间取员工数据
			 simpleMap.put("empID", empID);
			 simpleMap.put("period", period);
			 
			 
			 if(adminID!=null&&(adminID.equals("1464498")||adminID.equals("1464086")||adminID.equals("9999901")
					 ||adminID.equals("1466731")||adminID.equals("12884220")||adminID.equals("4529845"))){
				 simpleMap.put("adminid", "");
				}else{
					simpleMap.put("adminid", adminID);
				}
			 
			 searchCount =  services.getEvsCountEmp(simpleMap);
			 
		} catch (Exception e) {

			logger.error("Retrieve evaluate Exception." + e.toString());
			throw new GlRuntimeException("Retrieve evaluate Exception.", e);
		}
		logger.debug("evaluate type : " + evsEmp.getEvTypeID());  
		
		
		request.setAttribute("searchCount", searchCount); 

		request.setAttribute("craftid", craftid); 
		request.setAttribute("skileid", skileid); 
		request.setAttribute("countemp", countemp); 
		
	return "/evs0305_eval.jsp";
	}
}
