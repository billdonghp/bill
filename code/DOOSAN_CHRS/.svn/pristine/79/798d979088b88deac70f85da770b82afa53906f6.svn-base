package com.ait.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ait.commons.dao.CommonDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class EmpDiffTag  extends TagSupport{
	
	CommonDAO commonDAO=new CommonDAO();
	
	protected String name=null;
	
	protected String cpnyDiff=null;
	
	protected String selected=null;
	
	protected String onChange=null;
	
	protected String all=null;
	
	public String getCpnyDiff() {
		return cpnyDiff;
	}
	public void setCpnyDiff(String cpnyDiff) {
		this.cpnyDiff = cpnyDiff;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOnChange() {
		return onChange;
	}
	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public int doEndTag() throws JspException {

		name = eval("name", name, Object.class).toString();		
		if (selected != null) {
			selected = eval("selected", selected, Object.class).toString();
		} else {
			selected = "";
		}
		if (cpnyDiff != null) {
			cpnyDiff = eval("cpnyDiff", cpnyDiff, Object.class).toString();
		} else {
			cpnyDiff = "";
		}
		if (all != null) {
			all = eval("all", all, Object.class).toString();
		} else {
			all = "";
		}

		if (onChange != null) {
			onChange = eval("onChange", onChange, Object.class).toString();
		} else {
			onChange = "";
		}
		if (name == null || name.equals("")) {
			name = name;
		}
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		String language = ((AdminBean) request.getSession(false).getAttribute("admin")).getLocale().getLanguage();
		SimpleMap sm= new SimpleMap();
		sm.set("cpnyDiff", cpnyDiff);
		List empDiffData = commonDAO.EmpDiffTag(sm);

		if (empDiffData == null) {

			Logger.getLogger(this.getClass()).error("Can not find employee different data : " + empDiffData);
			return EVAL_PAGE;
		}
		JspWriter writer = pageContext.getOut();

		try {

			if(empDiffData.size() == 1) {
				
				SimpleMap record = (SimpleMap) empDiffData.get(0);
				writer.print(record.getString("NAME"));
				writer.print("<input type='hidden' name='" + name + "' value='" + record.getString("EMPDIFFCODE") + "'>");
				return EVAL_PAGE;
			}

			writer.print("<select id=\"" + name + "\" name=\"" + name + "\"");
			if (!"".equals(onChange)) {
				writer.print(" onChange=\"");
				writer.print(onChange);
				writer.print("\" ");
			}
			writer.print(" > ");
			if (!"".equals(all)) {
				writer.print("<option value=''>");
					writer.print("请选择");
				writer.print("</option> ");
			}


			for (int i=0;i<empDiffData.size();i++) {
				SimpleMap key =(SimpleMap) empDiffData.get(i);			
				
					writer.print("  <option value=\"" +key.get("EMPDIFFCODE") + "\"");
					if (key.get("EMPDIFFCODE").equals(selected)) {
						writer.print(" selected ");
					}
					writer.println(">");	
					writer.print(key.get("NAME"));
					writer.println("</option>");

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
			//throw new NullAttributeException(attName, attValue);
			return "";
		} else {
			return obj;
		}
	}

}
