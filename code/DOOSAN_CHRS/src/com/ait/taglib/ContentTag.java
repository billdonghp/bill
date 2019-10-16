package com.ait.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-7-5 下午08:38:49
 * @version 1.0
 * 
 */
public class ContentTag extends TagSupport {

	public int doEndTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String language = admin.getLanguagePreference();

		enContent = eval("enContent", enContent, Object.class).toString();

		if (zhContent != null && !zhContent.equals(""))
			zhContent = eval("zhConetnt", zhContent, Object.class).toString();
		else
			zhContent = "";

		if (koContent != null && !koContent.equals(""))
			koContent = eval("koContent", koContent, Object.class).toString();
		else
			koContent = "";

		if (link != null) {
			link = eval("link", link, Object.class).toString();
		} else {
			link = "";
		}

		try {

			if (!"".equals(link)) {
				pageContext.getOut().print("<a href=\"" + link);
				pageContext.getOut().print("\">");
			}

			if (language.equals("zh") && !zhContent.equals("")) {
				
				if (cnCutLength > 0 && zhContent.length() > cnCutLength) {
					pageContext.getOut().print("<font title='" + zhContent + "'>");
					pageContext.getOut().print(zhContent.substring(0, cnCutLength) + "..");
					pageContext.getOut().print("</font>");
				}
				else {
					pageContext.getOut().print(zhContent);
				}
			} else if (language.equals("ko") && !koContent.equals("")) {

				if (koCutLength > 0 && koContent.length() > koCutLength) {
					pageContext.getOut().print("<font title='" + koContent + "'>");
					pageContext.getOut().print(koContent.substring(0, koCutLength) + "..");
					pageContext.getOut().print("</font>");
				} else {
					pageContext.getOut().print(koContent);
				}

			} else {

				if (enCutLength > 0 && enContent.length() > enCutLength) {
					pageContext.getOut().print("<font title='" + enContent + "'>");
					pageContext.getOut().print(enContent.substring(0, enCutLength) + "..");
					pageContext.getOut().print("</font>");
				} else {
					pageContext.getOut().print(enContent);
				}
			}

			if (!"".equals(link)) {
				pageContext.getOut().print("</a>");
			}

		} catch (IOException ex) {
			throw new JspTagException(ex.getMessage());
		}

		return EVAL_PAGE;
	}

	protected String enContent = null;

	protected String zhContent = null;

	protected String koContent = null;

	protected int enCutLength = 0;

	protected int cnCutLength = 0;
	
	protected int koCutLength = 0;
	
	protected String link = null;

	public void setEnContent(String enContent) {
		this.enContent = enContent;
	}

	public void setKoContent(String koContent) {
		this.koContent = koContent;
	}

	public void setZhContent(String zhContent) {
		this.zhContent = zhContent;
	}

	
	public void setCnCutLength(int cnCutLength) {
		this.cnCutLength = cnCutLength;
	}

	
	public void setEnCutLength(int enCutLength) {
		this.enCutLength = enCutLength;
	}

	
	public void setKoCutLength(int koCutLength) {
		this.koCutLength = koCutLength;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private Object eval(String attName, String attValue, Class clazz) throws JspException {
		Object obj = ExpressionEvaluatorManager.evaluate(attName, attValue, clazz, this, pageContext);
		if (obj == null) {
			throw new NullAttributeException(attName, attValue);
		} else {
			return obj;
		}
	}

}