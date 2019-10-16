package com.ait.kpa.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.CommandManager;
import com.ait.web.Command;
import com.ait.web.ControlServlet;

public class KpaControlServlet extends ControlServlet {

	private static final long serialVersionUID = -130239423410918445L;

	private CommandManager manager;

	private static final Logger logger = Logger.getLogger(KpaControlServlet.class);

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// load command manager
		manager = CommandManager.getInstance(config.getServletContext().getRealPath("/"), "navigation.kpa.path");

	}

	/**
	 * overide resolveCommand function of AbstractControlServlet
	 */
	protected Command resolveCommand(HttpServletRequest request) throws GlRuntimeException {

		String operation = request.getParameter("operation");

		Object obj = null;

		try {

			obj = manager.getCommand(operation);
		} catch (Exception e) {

			logger.error("Resolve pa command Exception. ");
			throw new GlRuntimeException("Resolve pa command Exception. ", e);
		}

		Command cmd = (Command) obj;

		return cmd;
	}
}
