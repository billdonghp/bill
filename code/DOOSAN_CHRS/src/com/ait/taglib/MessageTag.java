package com.ait.taglib;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

import com.ait.i18n.MessageSource;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-7-5 下午08:38:49
 * @version 1.0
 * 
 */
public class MessageTag extends TagSupport {

	public int doEndTag() throws JspException {

		String defaultLang = "zh";
		String defaultCountry = "CN";
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource;

		String message = "";
		Vector param = null;
		
		if (admin == null)
			return EVAL_PAGE;

		messageID = eval("messageID", messageID, Object.class).toString();

		if (module != null)
			module = eval("module", module, Object.class).toString();
		else
			module = "";

		if (link != null) {
			link = eval("link", link, Object.class).toString();
		} else {
			link = "";
		}
 
		if (parameters != null && !parameters.equals("")) {

			parameters = eval("parameters", parameters, Object.class).toString();
			param = changeVector(parameters);
		} 

		if (arg1 != null) {
			arg1 = eval("arg1", arg1, Object.class).toString();
		} else {
			arg1 = "";
		}

		if (arg2 != null) {
			arg2 = eval("arg2", arg2, Object.class).toString();
		} else {
			arg2 = "";
		}

		if (arg3 != null) {
			arg3 = eval("arg3", arg3, Object.class).toString();
		} else {
			arg3 = "";
		}

		if (arg4 != null) {
			arg4 = eval("arg4", arg4, Object.class).toString();
		} else {
			arg4 = "";
		}

		if (language != null && country != null) {

			// get messageSource by local
			if (module != null && !module.equals(""))
				messageSource = new MessageSource(module, new Locale(language, country), "UTF-8");
			else
				messageSource = new MessageSource(new Locale(language, country), "UTF-8");
		} else {

			// get messageSource by local
			if (module != null && !module.equals("")) {

					messageSource = new MessageSource(module, admin.getLocale(), "UTF-8");  
			} else {

					messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			}
				
		}

		// get process result message by specific messageSource
		if (param != null) {
			
			message = messageSource.getMessage(messageID, param);
		} else {

			if (arg4.equals("")) {

				if (arg3.equals("")) {

					if (arg2.equals("")) {

						if (arg1.equals("")) {
							message = messageSource.getMessage(messageID);
						} else {
							message = messageSource.getMessage(messageID, arg1);
						}
					} else {
						message = messageSource.getMessage(messageID, arg1, arg2);
					}
				} else {
					message = messageSource.getMessage(messageID, arg1, arg2, arg3);
				}
			} else {
				message = messageSource.getMessage(messageID, arg1, arg2, arg3, arg4);
			}
		}

		try {

			String lang = language == null ? admin.getLanguagePreference() : language;

			if (!"".equals(link)) {
				pageContext.getOut().print("<a href=\"" + link);
				pageContext.getOut().print("\">");
			}

			if (cnCutLength > 0 && lang.equals("zh") && message.length() > cnCutLength) {
				pageContext.getOut().print("<font title='" + message + "'>");
				pageContext.getOut().print(message.substring(0, cnCutLength) + "..");
				pageContext.getOut().print("</font>");
			} else if (enCutLength > 0 && lang.equals("en") && message.length() > enCutLength) {
				pageContext.getOut().print("<font title='" + message + "'>");
				pageContext.getOut().print(message.substring(0, enCutLength) + "..");
				pageContext.getOut().print("</font>");
			} else if (koCutLength > 0 && lang.equals("ko") && message.length() > koCutLength) {
				pageContext.getOut().print("<font title='" + message + "'>");
				pageContext.getOut().print(message.substring(0, koCutLength) + "..");
				pageContext.getOut().print("</font>");

			} else {
				pageContext.getOut().print(message);
			}

			if (!"".equals(link)) {
				pageContext.getOut().print("</a>");
			}

		} catch (IOException ex) {
			throw new JspTagException(ex.getMessage());
		}

		return EVAL_PAGE;
	}

	protected String messageID = null;

	protected String language = null;

	protected String country = null;

	protected String module = null;

	protected int enCutLength = 0;

	protected int cnCutLength = 0;
	
	protected int koCutLength = 0;

	protected String link = null;

	protected String arg1 = null;

	protected String arg2 = null;

	protected String arg3 = null;

	protected String arg4 = null;

	protected String parameters = null;

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	private Object eval(String attName, String attValue, Class clazz) throws JspException {
		Object obj = ExpressionEvaluatorManager.evaluate(attName, attValue, clazz, this, pageContext);
		if (obj == null) {
			throw new NullAttributeException(attName, attValue);
		} else {
			return obj;
		}
	}
	
	private Vector changeVector(String params) {
		
		Vector paramVector = new Vector();
		String[] paramArray = params.split(",");
		for (int i=0; i<paramArray.length; i++) {
			
			paramVector.addElement(paramArray[i]);
		}
		return paramVector;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setEnCutLength(int enCutLength) {
		this.enCutLength = enCutLength;
	}

	public void setCnCutLength(int cnCutLength) {
		this.cnCutLength = cnCutLength;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}

	public void setArg3(String arg3) {
		this.arg3 = arg3;
	}

	public void setArg4(String arg4) {
		this.arg4 = arg4;
	}

	
	public void setKoCutLength(int koCutLength) {
		this.koCutLength = koCutLength;
	}

}