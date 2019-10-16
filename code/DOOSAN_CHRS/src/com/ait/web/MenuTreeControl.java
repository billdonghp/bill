/*
 * @(#)MenuTreeControl.java 1.0 2007-8-22 下午03:25:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ait.sqlmap.util.CommandManager;

public class MenuTreeControl extends ControlServlet {

	/**
	 * Constructor of the object.    
	 */
	public MenuTreeControl() {
		super();
	}

	private static final long serialVersionUID = -3396728684990581508L;

	private CommandManager manager;

	private String url = "/";

	// private Map commands; 
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		manager = CommandManager.getInstance(config.getServletContext().getRealPath("/"), "navigation.menu.path");
		url = config.getServletContext().getRealPath("/");
	}

	/**
	 * overide resolveCommand function of AbstractControlServlet
	 */
	protected Command resolveCommand(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.setAttribute("url", url);
		String operation = request.getParameter("operation");
		Object obj = manager.getCommand(operation);
		Command cmd = (Command) obj;
		return cmd;
	}

	protected String getFolder(String operation) {
		return "";
	}
}
