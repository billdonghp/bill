package com.ait.ess.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.web.Command;

public class EssContorlServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Commands接口集合 封装到MAP
	 */
	protected Map commands;

	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		super.init(arg0);
		commands = new HashMap();
	}

	/**
	 * 处理请求
	 * 
	 * @param request请求对象
	 * @param response响应对象
	 * @throws ServletException
	 * @throws IOException
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 取得Command处理对象
		 */
		Command cmd = resolveCommand(request);
		/**
		 * 处理完毕Command 返回结果
		 */
		String next = cmd.execute(request, response);
		/**
		 * 得到转发路径根目录
		 */
		String dispatchAdd = getFolder(next) + next;
		Logger.getLogger(getClass()).debug("dispatch address is: " + dispatchAdd);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatchAdd);

		/**
		 * 执行转发 传递request response组件
		 */
		dispatcher.forward(request, response);
	}

	private Command resolveCommand(HttpServletRequest request) throws ServletException, IOException {
		/**
		 * 根据表单元素 确定给哪个Command处理
		 */
		String command = request.getParameter("command");
		/**
		 * 根据条件 取得处理对象Command
		 */
		Command cmd = (Command) commands.get(command);
		if (cmd == null) {
			cmd = (Command) commands.get("PAGE_NOT_FOUND");
		}
		return cmd;
	}

	private String getFolder(String operation) {
		String folder = "";
		if (operation.equals(""))
			folder = "";
		if (operation.startsWith("/ar"))
			folder = "/ar";
		if (operation.startsWith("/sy"))
			folder = "/sy";
		if (operation.startsWith("/pa"))
			folder = "/pa";
		if (operation.startsWith("/hr"))
			folder = "/hr";
		if (operation.startsWith("/ev"))
			folder = "/ev";
		if (operation.startsWith("/ess"))
			folder = "/ess";
		if (folder == null)
			folder = "";
		// will deine more folder matching later if required
		return folder;
	}

	/**
	 * 处理GET请求
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 处理请求
		 */
		processRequest(request, response);
	}

	/**
	 * 处理POST请求
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 处理请求
		 */
		processRequest(request, response);
	}

}
