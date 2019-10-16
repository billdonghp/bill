package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.business.EssServices;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class EssSearchLeaveAffirmDataCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource message = new MessageSource(admin.getLocale(), "UTF-8"); 
		EssSysparam essSysparam= (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
		String leaveLength = "0" ;
		int affirmMaxLevel = 0 ;
		EssServices essServices = new EssServices() ;
		EssAffirmor essAffirmor = new EssAffirmor();
		List affirmList = new ArrayList() ;
		List deptList =  new ArrayList() ;
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("EMPID", request.getParameter("empId")) ;
		parameterObject.setString("STARTDATE", request.getParameter("leaveFromDate") + " " + request.getParameter("leaveFromTime")) ;
		parameterObject.setString("ENDDATE", request.getParameter("leaveToDate") + " " + request.getParameter("leaveToTime")) ;
		parameterObject.setString("LEAVETYPE", request.getParameter("leaveTypeCode"));
		parameterObject.setString("CPNY_ID", admin.getCpnyId());
		leaveLength = essServices.retrieveApplyLeaveLength(parameterObject) ;
		parameterObject.setString("LEAVELENGTH", leaveLength);		
	    affirmMaxLevel =  essServices.retrieveLeaveAffirmLevel(parameterObject) ;	
	    if(!admin.getCpnyId().equals("60000000")){
		affirmList = essServices.getAffirmorList(parameterObject.getString("EMPID"), "LeaveApply", affirmMaxLevel,essSysparam.isContainsOwner()) ;    			
	    }else{
		affirmList = essServices.getAffirmorListDIY(parameterObject.getString("EMPID"), "LeaveApply", affirmMaxLevel,essSysparam.isContainsOwner()) ;    			
	    }
		response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(500) ;
		
		String leaveTypeCode = request.getParameter("leaveTypeCode");
		
		
		
		for(int i = 0 ; i < affirmList.size() ; ++i)
		{
			essAffirmor = (EssAffirmor)affirmList.get(i) ;
			if(!(("B1".equals(leaveTypeCode)||"B3".equals(leaveTypeCode))&&essAffirmor.getAffirmorId().equals("4388429"))){//DIY 出差B1,培训B3决裁者排除4388429
				str.append(essAffirmor.getAffirmLevel()) ;
				str.append(".") ;
				str.append(essAffirmor.getAffirmorName()) ;
				str.append(";") ;
				
			}
			
			
			
		}
		
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}
}
