/*
 * @(#)InsertResignCmd.java 1.0 2006-12-19 下午05:05:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.Resignation;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午05:05:54
 * @version 1.0
 * 
 */
public class InsertResignCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    //添加离职信息
		String msg="保存成功!";
		
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.save_successfully");
		try {
		    String[] check;
		    check = request.getParameterValues("selectedTag");
		    List<Resignation> lResignation = new ArrayList<Resignation>();
		    if (null != check) {
			for (int i = 0, j = check.length; i < j; i++) {
			    Resignation bean = new Resignation();
			    ObjectBindUtil.setFormBean(request, bean, "_" + check[i]);
			    lResignation.add(bean);
			 }
		    }
		    HrmServices services = HrmServices.getInstance();
		    services.insertResignation(lResignation);
		    
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(Calendar.getInstance().getTime());
		    SimpleMap resignMap = new SimpleMap(); 
			resignMap.put("RESIGN_DATE_LESS_THAN", date);  
			resignMap.put("ACTIVITY", "0");         
			services.batchExeResignation(resignMap);   
			Logger.getLogger(getClass()).debug("-------------退社发令完成------------");
		    
		    
		} catch (Exception e) {  
		    Logger.getLogger(getClass()).error(e.toString());
		    msg="保存失败";
		    throw new GlRuntimeException("您已经对该员工发过离职令！", e); 
		}
		//提示
		request.setAttribute("alert", msg);
		//跳转的新页
		request.setAttribute("url", "/hrmControlServlet?operation=viewResign&menu_code="+request.getParameter("menu_code"));
		return "/inc/alertMessage.jsp";
		//return "/hrm/hrm_dl_upgrade.jsp";
	    }

}

