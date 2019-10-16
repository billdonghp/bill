package com.ait.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.CommandManager;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-12-18 下午05:11:30
 * @version 1.0
 * 
 */
public class AjaxControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CommandManager manager;

	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		String webPath = config.getServletContext().getRealPath("/");
		Logger.getLogger(this.getClass()).debug("web path :" + webPath);

		Configuration.setConfFileLocation(config.getServletContext().getInitParameter("em2"));
		Configuration.setConfFileLocation(webPath);

		//load command manager
		manager = CommandManager.getInstance(webPath, "navigation.ajaxbiz.path");
	}

	/**
	 * Process request
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Command cmd = this.resolveCommand(request);
			cmd.execute(request, response);

		} catch (Exception e) {

			Logger.getLogger(this.getClass()).error("AjaxControlServlet process request Exception: " + e);

		}

	}

	/**
	 * Resolve command by XML mapping
	 */
	protected Command resolveCommand(HttpServletRequest request) throws GlRuntimeException {
		String operation = request.getParameter("operation");
		Object obj = null;
		try {
			obj = manager.getCommand(operation);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("Resolve hrm command Exception. ");
			throw new GlRuntimeException("Resolve hrm command Exception. ", e);
		}
		Command cmd = (Command) obj;
		return cmd;
	}

	/**
	 * get request
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	/**
	 * post request
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
