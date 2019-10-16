/*
 * @(#)SelectTag.java 1.0 2006-12-25 上午11:26:21
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-25 上午11:26:21
 * @version 1.0
 * 
 */
public class SelectTag extends TagSupport {

	protected String dataListName = null;

	protected String name = null;

	protected String selected = null;

	protected String onChange = null;

	protected String filling = null;

	protected String all = null;

	protected boolean disabled = false;

	protected String style = null;

	/**
	 * tag body
	 */
	public int doEndTag() throws JspException {

		dataListName = eval("dataList", dataListName, Object.class).toString();
		name = eval("name", name, Object.class).toString();

		if (selected != null) {
			selected = eval("selected", selected, Object.class).toString();
		} else {
			selected = "";
		}

		if (onChange != null) {
			onChange = eval("onChange", onChange, Object.class).toString();
		} else {
			onChange = "";
		}
		if (filling != null) {
			filling = eval("onChange", filling, Object.class).toString();
		} else {
			filling = "";
		}

		if (all != null) {
			all = eval("all", all, Object.class).toString();
		} else {
			all = "";
		}

		if (name == null || name.equals("")) {
			name = dataListName;
		}

		if (style == null || style.equals("")) {
			style = dataListName;
		}

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String language;
		if (admin == null) {
			language = "zh";
		} else {
			language = admin.getLocale().getLanguage();
		}

		List dataList = (List) request.getAttribute(dataListName);

		if (dataList == null) {

			Logger.getLogger(this.getClass()).error("Can not find data list : " + dataListName);
			return EVAL_PAGE;
		}

		Iterator it = dataList.iterator();

		JspWriter writer = pageContext.getOut();

		try {

			writer.print("<select id=\"" + name + "\" name=\"" + name + "\"");
			if (!"".equals(onChange)) {
				writer.print(" onChange=\"");
				writer.print(onChange);
				writer.print("\" ");
			}
			if (!"".equals(filling)) {
				writer.print(" ");
				writer.print(filling);
				writer.print(" ");
			}

			if (disabled) {
				writer.print(" disabled");
			}

			if (!"".equals(style)) {
				writer.println(" class=\"" + style + "\">");
			} else {
				writer.println(" class=\"input_select_short\">");
			}
			if (!"".equals(all)) {
				writer.print("<option value=''>");
				if (language.equals("zh"))
					writer.print("请选择");
				else
					writer.print("Select");  
				writer.print("</option> ");
			}

			while (it.hasNext()) {

				SimpleMap record = (SimpleMap) it.next();

				if (!record.getString("ID").equals("无")) {

					writer.print("<option value=\"" + record.getString("ID") + "\"");

					if (record.getString("ID").equals(selected)) {
						writer.print(" selected ");
					}
					writer.println(">");
					if (language.equals("zh"))
						writer.print(record.getString("NAME"));
					else
						writer.print(record.getString("EN_NAME"));
					writer.println("</option>");
				}

			}

			writer.println("</select>");
		} catch (IOException ex) {
			throw new JspTagException(ex.getMessage());
		}

		return EVAL_PAGE;
	}

	private Object eval(String attName, String attValue, Class clazz) throws JspException {
		Object obj = ExpressionEvaluatorManager.evaluate(attName, attValue, clazz, this, pageContext);
		if (obj == null) {
			// throw new NullAttributeException(attName, attValue);
			return "";
		} else {
			return obj;
		}
	}

	public void setAll(String all) {
		this.all = all;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setFilling(String filling) {
		this.filling = filling;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setDataListName(String dataListName) {
		this.dataListName = dataListName;
	}
}
