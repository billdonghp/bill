/*
 * @(#)CodeServlet.java 1.0 2006-12-21 下午09:56:01
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.util.AjaxXmlBuilder;
import com.ait.util.StringUtil;
import com.ait.util.SysCodeParser;
import  com.ait.sy.bean.AdminBean;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-21 下午09:56:01
 * @version 1.0
 * 
 */
public class CodeServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 7391900627552395393L;

    public void init(ServletConfig config) throws ServletException {
	super.init(config);
    }

    public void processRequest(HttpServletRequest request,
	    HttpServletResponse response) throws IOException {
	doIt(request, response);
    }

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }

    private void doIt(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	String xml = null;
	try {
	    xml = getXmlContent(req, resp);
	} catch (Exception ex) {
	    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
		    "不能创建一个返回信息");
	    return;
	}
	// 转换到 xml，使用编码
	resp.setContentType("text/xml; charset=UTF-8");
	resp.setHeader("Cache-Control", "no-cache");
	PrintWriter pw = resp.getWriter();
	pw.write(xml);
	pw.close();
    }

    /**
     * 每个方法必须实现，返回指定XML数据
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getXmlContent(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	List list = this.getList(request);
	// Create xml schema
	return new AjaxXmlBuilder().addItems(list, "name", "code").toString();
    }

    private List getList(HttpServletRequest request) {
	String parentCode = StringUtil.checkNull(request.getParameter("pc"));
	 AdminBean admin=(AdminBean)request.getSession().getAttribute("admin");
	 String language= admin.getLanguagePreference();
	List<HashMap> lCode = SysCodeParser.getCodeByLaguage(parentCode,language);                   
	return lCode;
    }
}  
