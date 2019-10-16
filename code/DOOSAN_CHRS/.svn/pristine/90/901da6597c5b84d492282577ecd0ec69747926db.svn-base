/*
 * @(#)ViewTipInfoCmd.java 1.0 2007-9-17 下午06:08:46
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.tipmessagesearch;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;


public class ViewTipInfoCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
	     HttpServletResponse response) throws ServletException, IOException {
		HrmServices services = HrmServices.getInstance();
		this.putDeptTree(request);
		this.putToolbarInfo(request);
		String url=goWhere(request);
		return url;
	}
	
	public String goWhere(HttpServletRequest request)
	{	
		String url="/hrm/hrm_healthtipview.jsp";
		String gowhere =StringUtil.checkNull(request.getParameter("gowhere"));
		if(gowhere.equals("exporedprobat"))    
			url="/hrm/viewexpiredprobation.jsp";
		else if(gowhere.equals("upgrade"))
			url="/hrm/hrm_upgradeinfo.jsp";            
		return url;
	}
  
	
}

