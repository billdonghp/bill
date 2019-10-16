/*
 * @(#)ExportResignInfo.java 1.0 2007-12-8 下午08:03:45
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

public class ExportInfo extends HrmAbstractCommand {


    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
    	  String url="";
	try {
		
	      String empid=(String)request.getParameter("empid");
	      String type=(String)request.getParameter("type");
	      SimpleMap sMap = new SimpleMap();
	      sMap.set("empid",empid);
	    HrmServices services = HrmServices.getInstance();
	    
	    List list =null;
	    if(type.equals("resign"))
	    {
	    	list= services.getResignInfo(sMap);
	    	url="/hrm/exportresignreport.jsp";
	    }
	    else if (type.equals("reward"))
	    {
	    	list= services.getRewardInfo(sMap);
	    	url="/hrm/exportrewardreport.jsp";  
	    }
	    else if (type.equals("training"))
	    {
	    	list= services.getSdutyingInfo(sMap);
	    	url="/hrm/exporttrainingreport.jsp";
	    }
	    else if (type.equals("dispatch"))
	    {
	    	list= services.getDispatchInfo(sMap);
	    	url="/hrm/exportdispatchreport.jsp";
	    }
	    else if (type.equals("publishment"))
	    {
	    	list= services.getPulishInfo(sMap);
	    	url="/hrm/exportpulishreport.jsp";
	    }
	    else if (type.equals("plurality"))
	    {
	    	list= services.getPluralityInfo(sMap);
	    	url="/hrm/exportpluralityreport.jsp";
	    }
	    else if (type.equals("suspend"))
	    {
	    	list= services.getSuspensionInfo(sMap);
	    	url="/hrm/exportsuspensionreport.jsp";
	    }
	    else if (type.equals("upgrade"))
	    {
	    	list= services.getExperienceInsideInfo(sMap);
	    	url="/hrm/exportupgradereport.jsp";
	    }
	    request.setAttribute("NUMBER",((SimpleMap)list.get(0)).getString("TRANS_NO"));
    	request.setAttribute("CREATE_DATE",((SimpleMap)list.get(0)).getString("CREATE_DATE"));
    	request.setAttribute("CHINESENAME",((SimpleMap)list.get(0)).getString("CHINESENAME"));
	    request.setAttribute("list",list);

	} catch (Exception e) {
	    e.printStackTrace();
	    Logger.getLogger(this.getClass()).error(e.toString());
	}
	return url;
    }

}




