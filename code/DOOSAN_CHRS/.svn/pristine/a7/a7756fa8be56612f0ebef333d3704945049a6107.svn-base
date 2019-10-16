/*
 * @(#)CheckBoxTag.java 1.0 2006-12-11 下午12:00:52
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-11 下午12:00:52
 * @version 1.0
 * 
 */
public class CheckBoxTag extends TagSupport {

	 protected String isChecked = "";
	    protected String checkWhat = "";

	    public int doEndTag() throws JspException {
	        
	        checkWhat = (String) eval("checkWhat", checkWhat, Object.class);
	        if (checkWhat == null || "".equals(checkWhat))
	            checkWhat = "Y";

	        
	        try {
	            if (isChecked != null && isChecked.trim().equals(checkWhat))
	                pageContext.getOut().print("checked");
	        } catch (IOException ex) {
	            throw new JspTagException(ex.getMessage());
	        }
	        return EVAL_PAGE;
	    }

	    /**
	     * @param messageID
	     *            The messageID to set.
	     */
	    public void setIsChecked(String param) {
	        
	        this.isChecked = (String) eval("param", param, Object.class);;
	    }
	    

	    /**
	     * @return Returns the isChecked.
	     */
	    public String getIsChecked() {
	        return isChecked;
	    }

	    public void setCheckWhat(String checkWhat) {
	        this.checkWhat = checkWhat;
	    }

	    
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
}

