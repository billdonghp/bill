package com.ait.evs.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.CommandManager;
import com.ait.web.Command;
import com.ait.web.ControlServlet;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-05-07 上午10:30:56
 * @version 1.0
 * 
 */
public class EvsControlServlet extends ControlServlet {

	private CommandManager manager;

	private static final Logger logger = Logger
			.getLogger(EvsControlServlet.class);

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// load command manager
		manager = CommandManager.getInstance(config.getServletContext()
				.getRealPath("/"), "navigation.evs.path");

	}

	/**
	 * overide resolveCommand function of AbstractControlServlet
	 */
	protected Command resolveCommand(HttpServletRequest request)
			throws GlRuntimeException {

		String operation = request.getParameter("operation");

		Object obj = null;

		try {

			obj = manager.getCommand(operation);
		} catch (Exception e) {

			logger.error("Resolve evs command Exception. ");
			throw new GlRuntimeException("Resolve evs command Exception. ", e);
		}

		Command cmd = (Command) obj;

		return cmd;
	}

}
