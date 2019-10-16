/*
 * @(#)SelectDeptTag.java 1.0 2006-12-27 下午03:33:35
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

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.Department;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.ApplicationContext;

/**
 * 
 * Copyright: AIT (c) Company: AIT
 * 
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-27 下午03:33:35
 * @version 1.0
 * 
 */
public class SelectDeptTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -115060950241451913L;

	protected String dataListName = null;

	protected String name = null;

	protected String selected = null;

	protected String onChange = null;

	protected String filling = null;

	protected String all = null;

	protected boolean disabled = false;

	protected String style = null;

	protected String separator = null;
	
	protected String includeOverdue = null;
	
	protected String supervisorType = null ;
	
	protected String deptLevel;

	private HrmServices services = HrmServices.getInstance();

	/**
	 * tag body
	 */
	public int doEndTag() throws JspException {

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

		if (separator != null) {
			separator = eval("separator", separator, Object.class).toString();
		} else {
			separator = "&nbsp;&nbsp;";
		}
		
		if (includeOverdue != null) {
			includeOverdue = eval("includeOverdue", includeOverdue, Object.class).toString();
		} else {
			includeOverdue = "";
		}
		
		if (supervisorType != null) {
			supervisorType = eval("supervisorType", supervisorType, Object.class).toString();
		} else {
			supervisorType = "hr";
		}

		if (deptLevel != null) {
			deptLevel = eval("deptLevel", deptLevel, Object.class).toString();
		} else {
			deptLevel = "";
		}
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();		
		String language = ((AdminBean) request.getSession(false).getAttribute("admin")).getLocale().getLanguage();
		AdminBean adminBean =ApplicationContext.getTheadLocal();
		String cpnyId=adminBean.getCpnyId();

		try {
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.set("cpnyId", cpnyId);
			parameterObject.set("deptLevel", deptLevel);
			if (supervisorType == null || supervisorType.equals("ar"))
			{
				parameterObject.set("SUPERVISOR_ID", adminId) ;
			}
			else if (supervisorType.equals("hr"))
			{
				parameterObject.set("ADMIN_ID", adminId) ;
			}
			else if(supervisorType.equals("pa"))
			{
				// 暂时无操作
			}else if(supervisorType.equals("ev1"))				
			{
				parameterObject.set("PERSON_ID", adminId) ;
				String empid = services.retrieveEvAffirmAdmin(parameterObject);
				
				if(empid ==null ){
					parameterObject.set("EV_ADMIN_ID", adminId);
				}else{
					parameterObject.set("EV_ADMIN_ID", "");//设置管理人员可以查询全部配置过的部门
					parameterObject.set("EV_ALL", "EV_ALL");
				}
			}
			else if(supervisorType.equals("ev2"))
			{
				parameterObject.set("PERSON_ID", adminId) ;
				String empid = services.retrieveEvAffirmAdmin(parameterObject);
				if(empid ==null){
					parameterObject.set("EV_ADMIN_ID", adminId);
				}else{
					parameterObject.set("EV_ADMIN_ID", "");//设置管理人员可以查询全部部门
					parameterObject.set("EV_ALL", "");
				}
			}
			else
			{
				parameterObject.set("SUPERVISOR_ID", adminId) ;
			}
			
			if (includeOverdue.equals("Y")) {
				parameterObject.set("END_DATE", "Y");
			}

			List dataList = services.retrieveDeptTree(parameterObject);

			Iterator it = dataList.iterator();

			JspWriter writer = pageContext.getOut();

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
				if (language.equals("en"))
					writer.print("Select");
				else
					writer.print("请选择");
				writer.print("</option> ");
			}

			while (it.hasNext()) {

				Department dept = (Department) it.next();
				writer.print("<option value=\"" + dept.getDeptID() + "\"");
				if (dept.getDeptID().equals(selected)) {
					writer.print(" selected ");
				}
				writer.println(">");
				String deptName;
				if (language.equals("zh"))
					deptName = dept.getDeptName();
				else
					deptName = dept.getDeptEnName();
				
				for (int i = 0; i < dept.getDeptLevel().intValue(); i++) {
					deptName = separator + deptName;
				}
				writer.print(deptName);
				writer.println("</option>");

			}

			writer.println("</select>");
		} catch (IOException ex) {

			throw new JspTagException(ex.getMessage());
		} catch (Exception e) {

			throw new GlRuntimeException("Select department tag process Exception.", e);
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

	public void setSeparator(String separator) {
		this.separator = separator;
	}
	
	public void setIncludeOverdue(String includeOverdue) {
		this.includeOverdue = includeOverdue;
	}

	public String getSupervisorType() {
		return supervisorType;
	}

	public void setSupervisorType(String supervisorType) {
		this.supervisorType = supervisorType;
	}

	
	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}
}
