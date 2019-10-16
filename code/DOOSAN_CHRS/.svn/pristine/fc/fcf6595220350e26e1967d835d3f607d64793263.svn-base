/*
 * @(#)CheckEmpCommand.java 1.0 2006-12-19 下午05:00:18
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午05:00:18
 * @version 1.0
 * 
 */
public class CheckEmpCommand extends HrmAbstractCommand {

    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	//工号
	String empId = "";
	empId = request.getParameter("empId");
	HrmServices services=HrmServices.getInstance();
	BasicInfo basic = (BasicInfo) services.retrieveBasicInfo(empId);
	if(null!=basic){
	    request.setAttribute("msg", "工号	"+empId+"	已被使用 !");
	}
	//JS提示
	//request.setAttribute("alert", empId+"已存在");
	//跳转的新页
	//request.setAttribute("url", "/hrmControlServlet?operation=viewHire&menu_code=hr0201");
	
	return "/inc/alertMessage.jsp";
    }

}
