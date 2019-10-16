/*
 * @(#)SelectDeptTag.java 1.0 2006-12-27 下午03:33:35
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.EvsEmp;
import com.ait.hrm.business.HrmServices;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

/**
 * 
 * Copyright: AIT (c) Company: AIT
 * 
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-27 下午03:33:35
 * @version 1.0
 * 
 */
public class SelectEvsDeptTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -115060950241451913L;

	protected String name = null;

	protected String selectEvDeptId = null;

	protected String onChange = null;

	protected String filling = null;

	protected String all = null;

	protected boolean disabled = false;

	protected String style = null;

	protected String separator = null;
	
	protected String evPeriodId = null ;

	protected String level = null;

	private HrmServices services = HrmServices.getInstance();

	/**
	 * tag body
	 */
	@Override
	public int doEndTag() throws JspException {
		
		name = eval("name", name, Object.class).toString();

		if (selectEvDeptId != null) {
			selectEvDeptId = eval("selectEvDeptId", selectEvDeptId, Object.class).toString();
		} else {
			selectEvDeptId = "";
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
			separator = "&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		
		if (evPeriodId != null) {
			evPeriodId = eval("evPeriodId", evPeriodId, Object.class).toString().toUpperCase();
		} else {
			throw new GlRuntimeException("Select department tag process Exception.");
		}
		
		if (level != null){
			level = eval("level",level,Object.class).toString();
		}else{
			level = "";
		}

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String language = ((AdminBean) request.getSession(false).getAttribute("admin")).getLocale().getLanguage();

		try {
			//EvsEmp evsEmp = new EvsEmp();
			
			List lEvsDept = EvsEmp.getEvEmpDeptList1(evPeriodId);

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
				writer.print(" class=\"" + style + "\">");
			} else {
				writer.print(" class=\"input_select_short\">");
			}
			if (!"".equals(all)) {
				writer.print("<option value=''>");
				if (language.equals("en"))
					writer.print("Select");
				else
					writer.print("部门");
				writer.print("</option> ");
			}

			int lEvsDeptSize=lEvsDept.size();
			Hashtable dept_h=new Hashtable();
            for ( int i = 0 ; i < lEvsDeptSize; i++ )
            {
            	dept_h = (Hashtable) lEvsDept.get(i);
            	int deptLevel = NumberUtil.parseInt(dept_h.get("deptLevel")) ;
            	
				writer.print("<option   value=\"" + dept_h.get("deptId").toString() + "\"");
				if (dept_h.get("deptId").toString().equals(selectEvDeptId)) {
					writer.print(" selected  class=\"selected\"");
				}
				if(deptLevel == 1){
					writer.print(" class=\"selectLevel1\"");
				}else if(deptLevel == 2){
					writer.print(" class=\"selectLevel2\"");
				}
				writer.print(">");
				String deptName = dept_h.get("deptName").toString() + " ["+StringUtils.trimToEmpty(dept_h.get("deptEnName").toString())+"] ";
				
				for (int j = 0; j < deptLevel; j++) {
					deptName = separator + deptName;
				}
				writer.print(deptName);
				writer.print("</option>");

			}

			writer.print("</select>");
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

	public void setSelectEvDeptId(String selectEvDeptId) {
		this.selectEvDeptId = selectEvDeptId;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public void setEvPeriodId(String evPeriodId) {
		this.evPeriodId = evPeriodId;
	}
}
