package com.ait.sy.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.servlet.EssControlServlet;
import com.ait.sqlmap.util.CommandManager;
import com.ait.web.Command;
import com.ait.web.ControlServlet;

public class SyControlServlet extends ControlServlet {

	private static final long serialVersionUID = 1L;

	private CommandManager manager;

	private static final Logger logger = Logger.getLogger(EssControlServlet.class);

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		manager = CommandManager.getInstance(config.getServletContext().getRealPath("/"), "navigation.sy.path");
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
			logger.error("Resolve sys command Exception. ");
			throw new GlRuntimeException("Resolve sys command Exception. ", e);
		}
		Command cmd = (Command) obj;
		return cmd;
	}
}
