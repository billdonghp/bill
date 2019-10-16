/*
 * @(#)RetrieveApplyOTDetailCmd.java 1.0 2007-6-20 下午03:13:21
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ess.cmd.common;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.business.EssServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.web.Command;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-6-20 下午03:13:21
 * @version 1.0
 * 
 */
public class RetrieveApplyOTDetailCmd implements Command {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		EssServices essServices = new EssServices();
		
		String applyOTNo = request.getParameter("otNo");
		
		int count=Integer.parseInt(essServices.getReportType(applyOTNo).toString());

		SimpleMap applyOTDetail= null; 
	    List	applyOTDetail1=null;
	    String chinesename[]=null;
		if (count>1)
			applyOTDetail1 = essServices.getOverTimeApplerList(applyOTNo);
		else
		 applyOTDetail = (SimpleMap)essServices.retrieveApplyOTDetail(applyOTNo);
		 
		 List applyOTAffirmor = (List)essServices.retrieveApplyOTAffirmor(applyOTNo);
		
//		applyOTDetail.setString("APPLY_OT_REMARK", applyOTDetail.getString(
//		"APPLY_OT_REMARK") == null ?null:applyOTDetail.getString("APPLY_OT_REMARK").replaceAll("\r", "<br>"));
//		
		request.setAttribute("applyOTDetail", applyOTDetail);
		request.setAttribute("applyOTAffirmor", applyOTAffirmor);         
		request.setAttribute("size", applyOTAffirmor.size());
		String url="/ess_overtime_apply_detail.jsp";
		if(count==1)
		{
		 chinesename=((String)applyOTDetail.get("APPLYER")).split(","); 
		  if(chinesename.length>1)   
			{                	
			 url="/ess_applybatch_report.jsp";
			}
		}else if(count>1)    
			{
					 request.setAttribute("APPLY_GROUP_NO",((SimpleMap)applyOTDetail1.get(1)).getString("APPLY_GROUP_NO"));
					 request.setAttribute("CREATE_DATE",((SimpleMap)applyOTDetail1.get(1)).getString("CREATE_DATE"));
				     request.setAttribute("applyOTDetail", applyOTDetail1);
					 url="/ess_disApplyerInDisTimeOTReport.jsp";
				 }
	 return url;
 }
}

