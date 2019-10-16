package com.ait.pu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.servlet.GmControlServlet;
import com.ait.sqlmap.util.CommandManager;
import com.ait.web.Command;
import com.ait.web.ControlServlet;
/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-20
 * 
 */
public class PuControlServlet extends ControlServlet {

	private CommandManager manager;

	private static final Logger logger = Logger
			.getLogger(GmControlServlet.class);

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// load command manager
		manager = CommandManager.getInstance(config.getServletContext()
				.getRealPath("/"), "navigation.pu.path");

	}


	protected Command resolveCommand(HttpServletRequest request)
			throws GlRuntimeException {

		String operation = request.getParameter("operation");

		Object obj = null;

		try {

			obj = manager.getCommand(operation);
		} catch (Exception e) {

			logger.error("Resolve pu command Exception. ");
			throw new GlRuntimeException("Resolve pu command Exception. ", e);
		}

		Command cmd = (Command) obj;

		return cmd;
	}

}
