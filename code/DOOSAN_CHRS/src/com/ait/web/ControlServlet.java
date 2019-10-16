package com.ait.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class ControlServlet extends AbstractControlServlet {

	private static final long serialVersionUID = 1L;

	private Map commands;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		commands = new HashMap();
		commands.put("login", LoginCommand.class);
		commands.put("logout", LogoutCommand.class);
		commands.put("changeLang", ChangeLanguageCommand.class);
		commands.put("changeDivision", ChangeDivisionCommand.class);
		commands.put("emailLogin", EmailLoginCommand.class);
		commands.put("PAGE_NOT_FOUND", ErrorCommand.class);
		commands.put("main", MainCommand.class);
	}

	protected String getFolder(String operation) {

		String folder = "";
		if (operation == null || operation.equals(""))
			return "";
		if (operation.startsWith("/ar"))
			folder = "/ar";
		if (operation.startsWith("/sy"))
			folder = "/sy";
		if (operation.startsWith("/pa"))
			folder = "/pa";
		if (operation.startsWith("/ess"))
			folder = "/ess";
		if (operation.startsWith("/ga"))
			folder = "/ga";
		if (operation.startsWith("/gm"))
			folder = "/gm";
		if (operation.startsWith("/safe"))
			folder = "/safe";
		if (operation.startsWith("/ev"))
			folder = "/ev";
		if (operation.startsWith("/evs"))
			folder = "/evs";
		if (operation.startsWith("/pu"))
			folder = "/pu";
		if (operation.startsWith("/kpa"))
			folder = "/kpa";
		if (operation.equals("/CrystalReportViewer.jsp"))
			folder = "/reports";
		if (!operation.startsWith("/"))
			folder = "/";
		return folder;
	}

	/*
	 * protected Command resolveCommand(HttpServletRequest request) throws
	 * ServletException, IOException { String operation =
	 * request.getParameter("operation"); Command cmd = (Command)
	 * commands.get(operation); if (cmd == null) { cmd = (Command)
	 * commands.get("PAGE_NOT_FOUND"); } return cmd; }
	 */
	protected Map getCommandMap() {
		return this.commands;
	}
}
