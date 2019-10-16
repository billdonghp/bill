/*
 * @(#)a.java 1.0 2006-12-11 下午05:33:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ait.sqlmap.util.CodeUtil;
import com.ait.sqlmap.util.CodeUtilNotDsDy;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-11 下午05:33:58
 * @version 1.0
 * 
 */
public class CodeClassSelectTag extends TagSupport {

	protected String codeClass = null;

	protected String name = null;

	protected String selected = null;

	protected String onChange = null;

	protected String filling = null;

	protected String all = null;

	protected boolean disabled = false;

	protected String style = null;

	protected String remove = null;
	
	protected String cpnyId;
	
	protected String appoint;

	public int doEndTag() throws JspException {

		codeClass = eval("codeClass", codeClass, Object.class).toString();
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
			name = codeClass;
		}

		if (style == null || style.equals("")) {
			style = codeClass;
		}

		if (remove == null) {
			remove = "无";
		}
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		String language = ((AdminBean) request.getSession(false).getAttribute("admin")).getLocale().getLanguage();
		
		if (codeClass.equals("CheckBankInfor") || codeClass.equals("SealType") || codeClass.equals("PresentType") || codeClass.equals("PresentType2") || codeClass.equals("PresentName") || codeClass.equals("PresentName2") || codeClass.equals("PresentMarried")||codeClass.equals("DRIVER_NAME")){
			cpnyId = eval("cpnyId", cpnyId, Object.class).toString();
		}else{
			cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		}
		
		SimpleMap codeClassData = null;
		if(cpnyId.equals("78000000")){
			codeClassData = CodeUtilNotDsDy.getInstance().getCodeClass(codeClass);
		}else{
			codeClassData = CodeUtil.getInstance().getCodeClass(codeClass);
		}
		if (codeClassData == null) {

			Logger.getLogger(this.getClass()).error("Can not find code class : " + codeClass);
			return EVAL_PAGE;
		}

		Iterator it = codeClassData.keySet().iterator();

		JspWriter writer = pageContext.getOut();

		try {
			
			if(codeClassData.size() == 1) {
				
				Object key = it.next();
				SimpleMap record = ((SimpleMap) codeClassData.get(key));
				writer.print(record.getString("CODE_NAME"));
				writer.print("<input type='hidden' name='" + name + "' value='" + record.getString("CODE_ID") + "'?");
				return EVAL_PAGE;
			}
			
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
				Object key = it.next();
				SimpleMap record = ((SimpleMap) codeClassData.get(key));
				if (!record.getString("CODE_ID").equals("无") 
						&& !record.getString("CODE_ID").equals(remove)
						&& (record.getString("CPNY_ID") == null || record.getString("CPNY_ID").equals(cpnyId))
						&& (record.getString("R_CPNY_ID") == null || ("," + record.getString("R_CPNY_ID").trim() + ",").indexOf(","+cpnyId+",") == -1)
				) {

					writer.print("<option value=\"" + record.getString("CODE_ID") + "\"");

					if (record.getString("CODE_ID").equals(selected)) {
						writer.print(" selected ");
					}
					writer.println(">");
					if (language.equals("zh"))
						writer.print(record.getString("CODE_NAME"));
					else
						writer.print(record.getString("CODE_EN_NAME"));
					writer.println("</option>");
				}

			}

			writer.println("</select>");
		} catch (IOException ex) {
			throw new JspTagException(ex.getMessage());
		}

		return EVAL_PAGE;
	}

	/**
	 * @param deduct
	 *            The deduct to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	private Object eval(String attName, String attValue, Class clazz) throws JspException {
		Object obj = ExpressionEvaluatorManager.evaluate(attName, attValue, clazz, this, pageContext);
		if (obj == null) {
			//throw new NullAttributeException(attName, attValue);
			return "";
		} else {
			return obj;
		}
	}

	/**
	 * @param codeClass
	 *            The codeClass to set.
	 */
	public void setCodeClass(String codeClass) {
		this.codeClass = codeClass;
	}

	/**
	 * @param selected
	 *            The selected to set.
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}

	/**
	 * @param onChange
	 *            The onChange to set.
	 */
	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public void setFilling(String filling) {
		this.filling = filling;
	}
  
	public void setAll(String allOption) {
		this.all = allOption;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setRemove(String remove) {
		this.remove = remove;
	}

	public void setCpnyId(String cpnyId) {
		this.cpnyId = cpnyId;
	}
	
	public void setAppoin(String appoint) {
		this.appoint = appoint;
	}
}
