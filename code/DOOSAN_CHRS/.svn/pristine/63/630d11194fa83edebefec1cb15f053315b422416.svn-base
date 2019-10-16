
/*
 * @(#)ViewHireCmd.java 1.0 2006-12-19 下午05:00:18
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.servlet.HrmAbstractCommand;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午05:00:18
 * @version 1.0
 * 
 */
public class ViewHireCmd extends HrmAbstractCommand {

    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	//取得三级菜单及部门树
	try {
	    this.putToolbarInfo(request);                 
	    this.putDeptTree(request);
	    this.putPost(request);
	    this.putPostGrade(request);
	    this.putGradeLevel(request);
	    
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    throw new GlRuntimeException(
		    " ViewHireCmd  request Exception ", e);
	}
	return "/hrm/hrm_dl_addemp.jsp";
    }

}
