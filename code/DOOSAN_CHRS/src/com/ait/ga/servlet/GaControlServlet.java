package com.ait.ga.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.CommandManager;
import com.ait.web.Command;
import com.ait.web.ControlServlet; 

public class GaControlServlet extends ControlServlet {

	private CommandManager manager;

	private static final Logger logger = Logger
			.getLogger(GaControlServlet.class);

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// load command manager
		manager = CommandManager.getInstance(config.getServletContext()
				.getRealPath("/"), "navigation.ga.path");
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

			logger.error("Resolve ga command Exception. ");
			throw new GlRuntimeException("Resolve ga command Exception. ", e);
		}

		Command cmd = (Command) obj;

		return cmd;
	}

}
