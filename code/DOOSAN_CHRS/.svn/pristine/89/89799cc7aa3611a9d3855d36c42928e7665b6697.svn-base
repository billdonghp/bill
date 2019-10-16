
package com.ait.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ait.commons.dao.CommonDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-11-5 下午08:38:49
 * @version 1.0
 * 
 */
public class HistoryTag extends TagSupport {

	private CommonDAO dao;
	
    public int doEndTag() throws JspException {
    	
    	dao = new CommonDAO();
    	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String language = admin.getLanguagePreference();
		
		SimpleMap parameterObject = new SimpleMap();
		if (request.getParameter("menu_code") == null || request.getParameter("menu_code").equals("")) {
			
			parameterObject.setString("menu_code", request.getAttribute("menu_code")==null?"":request.getAttribute("menu_code").toString());
		} else {
			parameterObject.setString("menu_code", request.getParameter("menu_code"));
		}
		
        try {
        	
        	
        	List<SimpleMap> list = dao.retrieveHistoryMenu(parameterObject);
            JspWriter writer = pageContext.getOut();
            int i = 0;
            for (SimpleMap map : list) {
            	i++;
            	writer.println("<font color=\"#6060FF\">");
            	if (language.equals("zh")) {
            		
            		writer.println(map.getString("MENU_INTRO"));
            	} else if (language.equals("ko")) {
            		writer.println(map.getString("MENU_KOR_INTRO"));
            	} else {
            		writer.println(map.getString("MENU_EN_INTRO"));
            	}
            	if (i < list.size())
            		writer.println(" > ");
            	writer.println("</font>");
            
            }

        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }

        return EVAL_PAGE;
    }

}