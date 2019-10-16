package com.ait.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateControl extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	// Initialize global variables
	public void init(ServletConfig config) throws ServletException {

	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		// out.println("<html>");
		// out.println("<head><title>Update</title></head>");
		// out.println("<body bgcolor=\"#ffffff\">");
		// out.println("<p>The servlet has received a " + request.getMethod() +
		// ". This is the reply.</p>");
		// out.println("</body></html>");

		Func func = new Func();
		String flag = request.getParameter("flag");
		String menu_code = "";
		if (request.getParameter("menu_code") != null) {
			menu_code = (request.getParameter("menu_code"));
		}

		if (flag != null) {

			if (flag.equals("update")) {
				try {
					String statsValue = request.getParameter("statsValue");
					String no = request.getParameter("no");
					func.updateActive(statsValue, menu_code, no);
					out
							.write("<script language=javascript>alert(\"update okay^^\");self.history.back(1);document.location.reload(1)</script>");
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		}
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Process the HTTP Put request
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// Process the HTTP Delete request
	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	// Clean up resources
	public void destroy() {
	}
}
