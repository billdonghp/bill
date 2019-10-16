/*
 * @(#)DateTag.java 1.0 2006-12-11 下午01:14:31
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ait.util.DateUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-11 下午01:14:31
 * @version 1.0
 * 
 */
public class DateTag extends TagSupport {

	protected String yearName = null;

	protected String monthName = null;

	protected String yearSelected = null;

	// DateUtil.getToday("yyyy");

	protected String monthSelected = null;

	// DateUtil.getToday("MM");

	protected String yearMinus = null;

	protected String yearPlus = null;

	protected String onChange = null;

	protected boolean monthSelectedOnly = false;

	public int doEndTag() throws JspException {

		if (yearName != null) {
			yearName = eval("yearName", yearName, Object.class).toString();
		} else {
			yearName = "YYYY";
		}
		if (monthName != null) {
			monthName = eval("monthName", monthName, Object.class).toString();
		}
		// else {
		// monthName = "MM";
		// }
		if (yearSelected != null && !yearSelected.equals("")) {
			Object object = eval("yearSelected", yearSelected, Object.class);
			if (object == null || object.equals("")) {
				yearSelected = DateUtil.getToday("yyyy");
			} else {
				yearSelected = object.toString();
			}
		} else {
			yearSelected = DateUtil.getToday("yyyy");
			;
		}
		if (monthSelected != null && !monthSelected.equals("")) {
			Object object = eval("monthSelected", monthSelected, Object.class);
			if (object == null || object.equals("")) {
				monthSelected = DateUtil.getToday("MM");
			} else {
				monthSelected = object.toString();
			}
		} else {
			monthSelected = DateUtil.getToday("MM");
		}
		if (yearMinus != null) {
			yearMinus = eval("yearMinus", yearMinus, Object.class).toString();
		} else {
			yearMinus = "8";
		}
		if (yearPlus != null) {
			yearPlus = eval("yearPlus", yearPlus, Object.class).toString();
		} else {
			yearPlus = "1";
		}
		if (onChange != null) {
			onChange = eval("onChange", onChange, Object.class).toString();
		} else {
			onChange = "";
		}

		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		JspWriter writer = pageContext.getOut();

		try {
			// YYYY
			writer.print("<select name=\"" + yearName + "\"");

			if (!"".equals(onChange)) {
				writer.print(" onChange=\"");
				writer.print(onChange);
				writer.print("\" ");
			}

			writer.println(" class=\"input_select_short\">");

			int i = 0;
			int minus = Integer.parseInt(yearMinus);
			int plus = Integer.parseInt(yearPlus);

			for (i = 0 - minus; i <= plus; i++) {

				writer.print("<option value=\""
						+ (Integer.parseInt(DateUtil.getToday("yyyy")) + i)
						+ "\"");

				if (("" + (Integer.parseInt(DateUtil.getToday("yyyy")) + i))
						.equals(yearSelected)) {
					writer.print(" selected ");
				}
				writer.println(">");
				writer.print(""
						+ (Integer.parseInt(DateUtil.getToday("yyyy")) + i));
				writer.println("</option>");

			}
			// 适应 9999 缺省选中
			if (yearSelected.equals("9999")) {
				writer.print("<option vlaue=\"9999\" selected>9999</option>");
			}
			writer.println("</select>");
			// writer.println("&nbsp;");

			if (monthName != null) {
				writer.print("<select name=\"" + monthName + "\"");
				if (!"".equals(onChange)) {
					writer.print(" onChange=\"");
					writer.print(onChange);
					writer.print("\" ");
				}
				writer.println(" class=\"input_select_short\">");

				for (int j = 1; j <= 12; j++) {

					String tmp = "";
					if (j < 10) {
						tmp = "0";
					}
					if (!(tmp + j).equals(monthSelected) && monthSelectedOnly) {
						continue;
					}
					writer.print("<option value=\"" + tmp + j + "\"");
					if ((tmp + j).equals(monthSelected)) {
						writer.print(" selected ");
					}
					writer.println(">");
					writer.print(tmp + j);
					writer.println("</option>");

				}
				writer.println("</select>");
			}
			
		} catch (IOException ex) {
			throw new JspTagException(ex.getMessage());
		}

		return EVAL_PAGE;
	}

	/**
	 * @param deduct
	 *            The deduct to set.
	 */
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	private Object eval(String attName, String attValue, Class clazz)
			throws JspException {
		Object obj = ExpressionEvaluatorManager.evaluate(attName, attValue,
				clazz, this, pageContext);
		// if (obj == null) {
		// throw new NullAttributeException(attName, attValue);
		// } else {
		// return obj;
		// }
		return obj;
	}

	/**
	 * @param monthName
	 *            The codeClass to set.
	 */
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	/**
	 * @param selected
	 *            The selected to set.
	 */
	public void setYearSelected(String yearSelected) {
		this.yearSelected = yearSelected;
	}

	/**
	 * @param onChange
	 *            The onChange to set.
	 */
	public void setMonthSelected(String monthSelected) {
		this.monthSelected = monthSelected;
	}

	public void setYearMinus(String yearMinus) {
		this.yearMinus = yearMinus;
	}

	public void setYearPlus(String yearPlus) {
		this.yearPlus = yearPlus;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public void setMonthSelectedOnly(boolean monthSelectedOnly) {
		this.monthSelectedOnly = monthSelectedOnly;
	}

}
