package com.ait.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.SysConstants;
import com.ait.core.config.Configuration;
import com.ait.core.db.jdbc.ConnectionAdapter;
import com.ait.core.db.jdbc.JdbcTemplate;
import com.ait.core.exception.GlRuntimeException;

public abstract class AbstractControlServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String webPath = config.getServletContext().getRealPath("/");
		Logger.getLogger(this.getClass()).debug("web path :" + webPath);
		 Configuration.setConfFileLocation(config.getServletContext().getInitParameter("em2"));   
		Configuration.setConfFileLocation(webPath);
	}                    
                                                                                           
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forwardAddress = null;
		try {     
			Command cmd = this.resolveCommand(request);
			forwardAddress = cmd.execute(request, response);
			
			forwardAddress = getFolder(forwardAddress) + forwardAddress;
			Logger.getLogger(this.getClass()).debug("return url: " + forwardAddress);

			// 统一提交或回滚
			if (JdbcTemplate.isUsed()) {
				ConnectionAdapter connection = (ConnectionAdapter) JdbcTemplate
						.getTemplate().getConnection(false);
				if (connection != null) {
					if (connection.isRollbacked())
						connection.realRollback();
					else
						connection.realCommit();
				}

			}

		} catch (Exception ex) {
			// 统一回滚
			try {
				if (JdbcTemplate.isUsed()) {
					ConnectionAdapter connection = (ConnectionAdapter) JdbcTemplate
							.getTemplate().getConnection(false);
					if (connection != null)
						connection.realRollback();
				}
			} catch (SQLException sex) {
				request.setAttribute(SysConstants.ExceptionParamName, sex);
				sex.printStackTrace();
			}
			request.setAttribute(SysConstants.ExceptionParamName, ex);
			forwardAddress = "/sys/errorpage.jsp";
			ex.printStackTrace();
		} finally {
			// 统一关闭
			try {
				if (JdbcTemplate.isUsed()) {
					JdbcTemplate.getTemplate().close();
				}
			} catch (SQLException sex) {
				request.setAttribute(SysConstants.ExceptionParamName, sex);
				forwardAddress = "/sys/errorpage.jsp";
				sex.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(forwardAddress);
		dispatcher.forward(request, response);
	}

	protected String getFolder(String operation) {
		Logger.getLogger(this.getClass()).debug("operation : " + operation);
		String folder = "";
		if (operation == null || operation.equals(""))
			folder = "";
		if (operation.startsWith("/ar"))
			folder = "/ar";
		if (operation.startsWith("/sy"))
			folder = "/sy";
		if (operation.startsWith("/pa"))
			folder = "/pa";
		if (operation.startsWith("/hr"))
			folder = "/hr";
		if (operation.startsWith("/hrm"))
			folder = "/hrm";
		if (operation.startsWith("/evs"))
			folder = "/evs";
		if (operation.startsWith("/ess"))
			folder = "/ess";
		if (operation.startsWith("/gm"))
			folder = "/gm";
		if (operation.startsWith("/pu"))
			folder = "/pu";
		if (operation.startsWith("/safe"))
			folder = "/safe";
		if (operation.startsWith("/ga"))
			folder = "/ga";
		if (operation.startsWith("/kpa"))
			folder = "/kpa";
		if (folder == null)
			folder = "";
		return folder;
	}

	protected Command resolveCommand(HttpServletRequest request)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		Map cmds = getCommandMap();
		Object obj = cmds.get(operation);
		Command cmd;
		if (obj instanceof Command)
			cmd = (Command) obj;
		else if (obj instanceof Class) {
			int i = 0;
			Class[] intfs = ((Class) obj).getInterfaces();
			for (; i < intfs.length && intfs[i] != Command.class; i++)
				;
			if (i == intfs.length)
				throw new GlRuntimeException(operation + " Command "
						+ ((Class) obj).getName() + " Class 未实现Command接口");
			try {
				cmd = (Command) (((Class) obj).newInstance());
			} catch (Exception ex) {
				throw new GlRuntimeException(operation + " Command "
						+ ((Class) obj).getName()
						+ " Class 实例化异常，检查Command注册或者其构造函数", ex);
			}
		} else
			throw new GlRuntimeException("没有找到" + operation
					+ " Command，检查提交的request");
		return cmd;

	}

	protected Map getCommandMap() {
		return null;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
