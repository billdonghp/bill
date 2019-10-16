/*
 * @(#)ReportServices.java 1.0 2007-9-28 上午01:24:06
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.business;
import java.util.List;
import com.ait.hrm.dao.common.DeptDAO;

public class ReportServices {
	
	private DeptDAO deptDAO;
	public ReportServices()
	{
		deptDAO=new DeptDAO();
	}
	
	public   List getGrantDept(String adminId) {
		return deptDAO.getGrantDept(adminId);  
	}

}

