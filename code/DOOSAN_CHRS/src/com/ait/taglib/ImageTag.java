/*
 * @(#)ImageTag.java 1.0 2007-9-4 下午02:18:48
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

import com.ait.sy.bean.AdminBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-4 下午02:18:48
 * @version 1.0
 * 
 */
public class ImageTag extends TagSupport {

	public int doEndTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String language = ((AdminBean) request.getSession(false).getAttribute("admin")).getLocale().getLanguage();

		src = eval("src", src, Object.class).toString();
		if (language.equals("en")) {

			int index = src.lastIndexOf(".");
			src = src.substring(0, index) + "_en" + src.substring(index);
		}

		if (name != null)
			name = eval("name", name, Object.class).toString();
		else
			name = "";
		
		if (width != null)
			width = eval("width", width, Object.class).toString();
		else
			width = "";

		if (height != null) {
			height = eval("height", height, Object.class).toString();
		} else {
			height = "";
		}

		if (border != null) {
			border = eval("border", border, Object.class).toString();
		} else {
			border = "";
		}

		if (align != null) {
			align = eval("align", align, Object.class).toString();
		} else {
			align = "";
		}

		if (style != null) {
			style = eval("style", style, Object.class).toString();
		} else {
			style = "";
		}

		if (title != null) {
			title = eval("title", title, Object.class).toString();
		} else {
			title = "";
		}

		if (enTitle != null) {
			enTitle = eval("enTitle", enTitle, Object.class).toString();
		} else {
			enTitle = "";
		}
		
		if (onclick != null) {
			onclick = eval("onclick", onclick, Object.class).toString();
		} else {
			onclick = "";
		}

		if (link != null) {
			link = eval("link", link, Object.class).toString();
		} else {
			link = "";
		}
		
		if (usemap != null) {
			usemap = eval("usemap", usemap, Object.class).toString();
		} else {
			usemap = "";
		}

		try {

			if (!"".equals(link)) {
				pageContext.getOut().print("<a href=\"" + link);
				pageContext.getOut().print("\">");
			}
			pageContext.getOut().print("<img src=\"" + src + "\"");
			if (!"".equals(name)) {
				pageContext.getOut().print(" name=\"" + name + "\"");
			}
			if (!"".equals(width)) {
				pageContext.getOut().print(" width=\"" + width + "\"");
			}
			if (!"".equals(height)) {
				pageContext.getOut().print(" height=\"" + height + "\"");
			}
			if (!"".equals(border)) {
				pageContext.getOut().print(" border=\"" + border + "\"");
			}
			if (!"".equals(align)) {
				pageContext.getOut().print(" align=\"" + align + "\"");
			}
			if (!"".equals(style)) {
				pageContext.getOut().print(" style=\"" + style + "\"");
			}
			if (!"".equals(title) && language.equals("zh")) {
				pageContext.getOut().print(" title=\"" + title + "\"");
			}
			if (!"".equals(enTitle) && language.equals("en")) {
				pageContext.getOut().print(" title=\"" + enTitle + "\"");
			}
			if (!"".equals(onclick)) {
				pageContext.getOut().print(" onclick=\"" + onclick + "\" ");
			}
			if (!"".equals(usemap)) {
				pageContext.getOut().print(" usemap=\"" + usemap + "\" ");
			}
			pageContext.getOut().print(">");
			if (!"".equals(link)) {
				pageContext.getOut().print("</a>");
			}

		} catch (IOException ex) {
			throw new JspTagException(ex.getMessage());
		}

		return EVAL_PAGE;
	}

	private Object eval(String attName, String attValue, Class clazz) throws JspException {
		Object obj = ExpressionEvaluatorManager.evaluate(attName, attValue, clazz, this, pageContext);
		if (obj == null) {
			throw new NullAttributeException(attName, attValue);
		} else {
			return obj;
		}
	}

	protected String src = null;
	
	protected String name = null;

	protected String width = null;

	protected String height = null;

	protected String border = null;

	protected String align = null;

	protected String style = null;

	protected String title = null;

	protected String enTitle = null;

	protected String onclick = null;

	protected String link = null;
	
	protected String usemap = null;

	
	public void setUsemap(String usemap) {
		this.usemap = usemap;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setEnTitle(String enTitle) {
		this.enTitle = enTitle;
	}

	
	public void setName(String name) {
		this.name = name;
	}

}
