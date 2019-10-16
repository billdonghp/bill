/*
 * @(#)ProcessBarTag.java 1.0 2007-10-27 上午09:24:03
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
public class ProcessBarTag extends TagSupport {

	public int doEndTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		try {
			pageContext.getOut().print("<span id=\"waitingInfo\" style=\"display:none\">" + "<table align=\"center\"><tr><td align=\"center\">");

			if (admin.getLanguagePreference().equals("zh")) {

				pageContext.getOut().print("数据后台处理中,请不要离开本页或关闭本窗口,等待...<p>");

			} else {
				pageContext.getOut().print("Data processing,Don't leave this page,waiting for done...<p>");

			}

			pageContext.getOut().print(
					"<div style=\"font-size:2pt;padding:2px;border:solid black 1px;width:110px\">" + "<span id=\"progress1\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress2\">&nbsp; &nbsp;&nbsp; &nbsp;</span>"
							+ "<span id=\"progress3\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress4\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress5\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress6\">&nbsp; &nbsp;&nbsp; &nbsp;</span>"
							+ "<span id=\"progress7\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress8\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress9\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress10\">&nbsp; &nbsp;&nbsp; &nbsp;</span>"
							+ "<span id=\"progress11\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress12\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress13\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "<span id=\"progress14\">&nbsp; &nbsp;&nbsp; &nbsp;</span>"
							+ "<span id=\"progress15\">&nbsp; &nbsp;&nbsp; &nbsp;</span>" + "</div></td></tr></table></span>");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}