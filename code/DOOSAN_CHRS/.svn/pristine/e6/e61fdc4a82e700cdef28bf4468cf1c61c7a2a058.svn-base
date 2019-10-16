/*
 * @(#)ProcessBarJsTag.java 1.0 2007-10-27 上午09:24:03
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ait.sy.bean.AdminBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-10-27 上午09:24:03
 * @version 1.0
 * 
 */
public class ProcessBarJsTag extends TagSupport {

	public int doEndTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		try {
			if (admin.getLanguagePreference().equals("zh")) {

				pageContext.getOut().print("<SCRIPT src=\"/js/process_bar_chinese.js\" language=\"JScript\"></SCRIPT>");

			} else {
				pageContext.getOut().print("<SCRIPT src=\"/js/process_bar_english.js\" language=\"JScript\"></SCRIPT>");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}