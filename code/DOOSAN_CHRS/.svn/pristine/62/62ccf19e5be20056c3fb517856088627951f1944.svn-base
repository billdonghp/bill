/*
 * @(#)ListingTag.java 1.0 2006-12-11 下午12:12:39
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-11 下午12:12:39
 * @version 1.0
 * 
 */
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.DateUtil;

public class ListingTag extends TagSupport {

	String align;

	String style;

	String width;

	String span;

	String entityName;

	String value;

	String link;

	String target;

	String linkName;

	protected int cnCutLength = 0;

	public int doEndTag() throws JspException {
		try {
			align = (String) eval("align", align, Object.class);
			if (align == null || "".equals(align))
				align = "center";

			style = (String) eval("style", style, Object.class);
			if (style == null || "".equals(style))
				style = "";

			width = (String) eval("width", width, Object.class);
			if (width == null || "".equals(width))
				width = "";

			span = (String) eval("span", span, Object.class);
			if (span == null || "".equals(span))
				span = "1";

			entityName = (String) eval("entityname", entityName, Object.class);
			if (entityName == null || "".equals(entityName))
				entityName = "";

			value = (String) eval("value", value, Object.class);
			if (value == null || "".equals(value))
				value = "";

			link = (String) eval("link", link, Object.class);
			if (link == null || "".equals(link))
				link = "";

			target = (String) eval("target", target, Object.class);
			if (target == null || "".equals(target))
				target = "";

			if (linkName != null && !"".equals(linkName)) {
				Integer linkNameInt = (Integer) eval("linkName", linkName,
						Object.class);
				if (linkNameInt == null) {
					linkName = "";
				} else
					linkName = linkNameInt + "";
			} else
				linkName = "";

			JspWriter out = pageContext.getOut();
			StringBuffer sbuf = new StringBuffer();
			sbuf.append("<td align='" + align + "'");
			if (!"".equals(style))
				sbuf.append(" class='" + style + "'");
			if (!"".equals(width))
				sbuf.append(" width='" + width + "'");

			sbuf.append(">");

			for (int i = 0; i < Integer.parseInt(span); i++) {
				sbuf.append("&nbsp;");
			}

			sbuf.append(makeContent(link, entityName, value, target));
			sbuf.append("</td>");
			out.print(sbuf);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * 
	 * @param attName
	 * @param attValue
	 * @param clazz
	 * @return Attribute Object
	 */
	private Object eval(String attName, String attValue, Class clazz) {
		Object obj;
		try {
			obj = ExpressionEvaluatorManager.evaluate(attName, attValue, clazz,
					this, pageContext);
		} catch (JspException e) {
			return null;
		}
		return obj;

	}

	/**
	 * 
	 * @param link
	 * @param entityName
	 * @param value
	 * @return Html String
	 */
	public String makeContent(String link, String entityName, String value,
			String target) throws JspException {
		StringBuffer tmp = new StringBuffer();
		String output = null;

		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		if ("".equals(link)) {
			if (!"".equals(entityName) && !"".equals(value)) {

				if (pageContext.getAttribute(entityName) instanceof SimpleMap) {

					output = ((SimpleMap) pageContext.getAttribute(entityName))
							.getString(value);
				} else {
					output = this.getBeanProperty(pageContext
							.getAttribute(entityName), value);
				}

				if (output == null || "".equals(output))
					output = "";
				// cut start
				if (cnCutLength > 0 && output.length() > cnCutLength) {
					output = "<font title='" + output + "'>"
							+ output.substring(0, cnCutLength) + "..</font>";
				} else {

				}
				// cut end

				tmp.append(output);
			} else
				tmp.append(value);

		} else {
			tmp.append("<a href=");
			tmp.append(link);
			if (!"".equals(target))
				tmp.append(" target='" + target + "'");
			if (!"".equals(linkName))
				tmp.append(" name='" + "a" + linkName + "'");

			tmp.append(">");
			if (!"".equals(entityName) && !"".equals(value)) {

				if (pageContext.getAttribute(entityName) instanceof SimpleMap) {

					output = ((SimpleMap) pageContext.getAttribute(entityName))
							.getString(value);
				} else {
					output = this.getBeanProperty(pageContext
							.getAttribute(entityName), value);
				}

				if (output == null || "".equals(output))
					output = "";

				// cut start
				if (cnCutLength > 0 && output.length() > cnCutLength) {
					output = "<font title='" + output + "'>"
							+ output.substring(0, cnCutLength) + "..</font>";
				} else {

				}
				// cut end

				tmp.append(output);
			} else
				tmp.append(value);
			tmp.append("</a>");

		}
		return tmp.toString();
	}

	/**
	 * get property value of bean object
	 * 
	 * @param bean
	 * @param value
	 * @return String
	 */
	private String getBeanProperty(Object bean, String value) {

		if (value == null || value.equals("")) {
			return null;
		}
		String result = null;
		Class c = bean.getClass();

		// get method list
		Method[] ms = c.getMethods();

		for (int i = 0; i < ms.length; i++) {
			String name = ms[i].getName();
		}
		// matching request parameter to method
		for (int i = 0; i < ms.length; i++) {
			// get method name
			String name = ms[i].getName();

			// get method of start with 'set'
			if (name.startsWith("get")) {

				// jump logic if method name no rule
				if (name.length() == 3) {
					continue;
				}
				// get return type for the method
				Class cc = ms[i].getReturnType();
				// bean getter must have a return type
				if (!cc.getName().equals("void")
						&& !cc.getName().equals("java.lang.Void")) {
					// get getter method return type
					String type = cc.getName();
					try {
						// get property name of the method
						String prop = Character.toLowerCase(name.charAt(3))
								+ name.substring(4);

						if (prop.equalsIgnoreCase(value)) {
							if (type.equals("java.lang.String")) {
								result = ms[i].invoke(bean).toString();
							} else if (type.equals("int")
									|| type.equals("java.lang.Integer")) {
								result = ms[i].invoke(bean).toString();
							} else if (type.equals("long")
									|| type.equals("java.lang.Long")) {
								result = ms[i].invoke(bean).toString();
							} else if (type.equals("boolean")
									|| type.equals("java.lang.Boolean")) {
								result = ms[i].invoke(bean).toString();
							} else if (type.equals("java.util.Date")) {
								result = DateUtil.formatDate((Date) ms[i]
										.invoke(bean), "yyyy-MM-dd");
							}
							return result;
						}
					} catch (Exception e) {

						Logger.getLogger(ObjectBindUtil.class).error(
								"ObjectBindUtil.getBeanProperty(Object bean, String value) Exception: "
										+ e.toString());
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param align
	 *            The align to set.
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @param entityName
	 *            The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * @param link
	 *            The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @param span
	 *            The span to set.
	 */
	public void setSpan(String span) {
		this.span = span;
	}

	/**
	 * @param style
	 *            The style to set.
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param width
	 *            The width to set.
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @param target
	 *            The target to set.
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public void setCnCutLength(int cnCutLength) {
		this.cnCutLength = cnCutLength;
	}

}
