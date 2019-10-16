package com.ait.ar.dao.shiftset;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArScheduleBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class Add implements Command {
	private String returnR = null;

	public Add() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HrmServices hrmServices = HrmServices.getInstance() ;
		
		String str = request.getParameter("shift_no");
		String emp_str = request.getParameter("empID") ;
		ArScheduleBean add = new ArScheduleBean();
		// 得到开始时间
		String from_date = request.getParameter("from_date");
		// 得到结束时间
		String to_date = request.getParameter("to_date");
		String type = StringUtil.checkNull(request.getParameter("type"));
		String shiftID = StringUtil.checkNull(request.getParameter("empID"));
		String personID = StringUtil.checkNull(request.getParameter("person_id"));
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin") ;
		
		ArrayList values = new ArrayList();
		values.add(type);
		values.add(shiftID);
		values.add("to_date('" + from_date + "','yyyy-mm-dd')");
		values.add("to_date('" + to_date + "','yyyy-mm-dd')");
		values.add(StringUtil.toCN(str));
		values.add(admin.getAdminID());
		
		try {
			
			if(type.equals("emp_batch"))
			{
				String empids[] = emp_str.split(",") ;
				values.set(0, "employee") ;
				for(int i = 0 ; i < empids.length; i ++)
				{
					values.set(1, empids[i]) ;
					add.addGroupShift(values);
				}
				
			}else{
				boolean addFlag = false ;
				SimpleMap parameterObject = new SimpleMap() ;
				if (personID == null || personID.length() == 0)
				{
					parameterObject.setString("empId", shiftID) ;
					parameterObject.setString("cnpyId", admin.getCpnyId()) ;
					parameterObject.setString("supervisorId", admin.getAdminID()) ;
					
					personID = hrmServices.retrieveHrPersonId(parameterObject) ;
					
					if (personID != null && personID.length() > 0)
					{
						addFlag = true ;
					}
				}
				else
				{
					addFlag = true ;
				}
				
				if (addFlag){
					values.set(1, personID) ;
					add.addGroupShift(values);
				}
			}
			
			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Set employee shift pattern Exception. " + e.toString());
			throw new GlRuntimeException(
					"Set employee shift pattern Exception. ", e);
		}

		if (type.equalsIgnoreCase("group"))
			returnR = "ar/groupshift_a.jsp?shiftID=" + shiftID;
		else if (type.equalsIgnoreCase("department"))
			returnR = "ar/deptshift_a.jsp";
		else if (type.equalsIgnoreCase("emp_batch"))
			returnR = "ar/emp_batchshift_a.jsp";
		else
			returnR = "ar/empshift_a.jsp";
		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;

	}
}
