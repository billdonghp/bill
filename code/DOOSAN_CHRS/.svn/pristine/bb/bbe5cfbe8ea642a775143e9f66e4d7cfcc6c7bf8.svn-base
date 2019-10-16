/*
 * @(#)InsertHireCmd.java 1.0 2006-12-19 下午05:01:17
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.Contract;
import com.ait.hrm.bean.EmployeeBean;
import com.ait.hrm.bean.PersonalInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午05:01:17
 * @version 1.0
 * 
 */
public class InsertHireCmd extends HrmAbstractCommand {

    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String msg = "保存成功!";
	
	
	HttpSession session = request.getSession(true);
	AdminBean admin = (AdminBean) session.getAttribute("admin");
	MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
	msg = messageSource.getMessage("alert.mutual.save_successfully");
	try {
	    // this.putToolbarInfo(request);
	    // this.putPost(request);
	    // this.putDeptTree(request);
		
	    EmployeeBean emp = new EmployeeBean();
	    PersonalInfo personal = new PersonalInfo();
	    Contract contract = new Contract();

	    ObjectBindUtil.setFormBean(request, emp);
	    ObjectBindUtil.setFormBean(request, contract);
	    ObjectBindUtil.setFormBean(request, personal);

	    HrmServices services = HrmServices.getInstance();

	    services.insertEmployee(emp, personal);
	    if (null != contract.getEmpID()
		    && null != contract.getContractStartDate()
		    && null != contract.getContractEndDate()) {
		services.insertContract(contract);
	    }

	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    msg = "添加员工失败!";
	    throw new GlRuntimeException("添加员工失败!", e);
	}
	// 提示
	request.setAttribute("alert", msg);
	// 跳转的新页
	request.setAttribute("url",
		"/hrmControlServlet?operation=viewHire&menu_code=hr0201");
	return "/inc/alertMessage.jsp";

	// return "/hrm/hrm_dl_addemp.jsp";
    }

}
