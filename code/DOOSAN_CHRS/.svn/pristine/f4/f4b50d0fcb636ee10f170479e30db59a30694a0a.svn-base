/*
 * @(#)RetrieveApplyLeaveDetailCmd.java 1.0 2007-6-20 下午03:14:05
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ess.cmd.common;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ess.business.EssServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-6-20 下午03:14:05
 * @version 1.0
 * 
 */
public class RetrieveApplyLeaveDetailCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		EssServices essServices = new EssServices();

		String applyLeaveNo = request.getParameter("leaveNo");
		
		int count=Integer.parseInt(essServices.getLeaveReportType(applyLeaveNo).toString());

		SimpleMap applyLeaveDetail= null; 
	    List	applyLeaveDetail1=null;
	    String chinesename[]=null;
		if (count>1)
			applyLeaveDetail1= essServices.getLeaveApplerList(applyLeaveNo);
		else
	      applyLeaveDetail = (SimpleMap) essServices
				.retrieveApplyLeaveDetail(applyLeaveNo);
		                                                       
		SimpleMap smap = new SimpleMap();                   
		smap.setString("LEAVE_NO",applyLeaveNo);
		smap.setString("TYPE", "LeaveApply");
		
		List applyAffirmor = (List)essServices.retrieveApplyAffirmor(smap);
		
//		applyLeaveDetail.setString("LEAVE_REASON", applyLeaveDetail.getString(
//				"LEAVE_REASON") == null ? null : applyLeaveDetail.getString("LEAVE_REASON").replaceAll("\r", "<br>"));

		request.setAttribute("applyLeaveDetail", applyLeaveDetail);
		request.setAttribute("applyAffirmor", applyAffirmor);
		request.setAttribute("size", applyAffirmor.size());
		String url="/ess_leave_apply_detail.jsp";
		if(count==1)
		{
		 chinesename=((String)applyLeaveDetail.get("APPLYER")).split(","); 
		  if(chinesename.length>1)   
			{                	
			 url="/ess_leave_applybatch_report.jsp";
			}
		}else if(count>1)    
			{
					 request.setAttribute("APPLY_GROUP_NO",((SimpleMap)applyLeaveDetail1.get(1)).getString("APPLY_GROUP_NO"));
					 request.setAttribute("CREATE_DATE",((SimpleMap)applyLeaveDetail1.get(1)).getString("CREATE_DATE"));
				     request.setAttribute("applyLeaveDetail", applyLeaveDetail1);
					 url="/ess_disApplyerInDisTimeLeaveReport.jsp";
				 }                 
	 return url;
	}

}
