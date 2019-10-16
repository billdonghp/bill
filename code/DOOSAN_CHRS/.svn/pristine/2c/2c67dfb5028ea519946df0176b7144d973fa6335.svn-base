package com.ait.ev.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.CommandManager;
import com.ait.web.Command;
import com.ait.web.ControlServlet;

public class EvControlServlet extends ControlServlet {

	private CommandManager manager;

	private static final Logger logger = Logger
			.getLogger(EvControlServlet.class);

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// load command manager
		manager = CommandManager.getInstance(config.getServletContext()
				.getRealPath("/"), "navigation.ev.path");

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

			logger.error("Resolve ev command Exception. ");
			throw new GlRuntimeException("Resolve ev command Exception. ", e);
		}

		Command cmd = (Command) obj;

		return cmd;
	}
}
