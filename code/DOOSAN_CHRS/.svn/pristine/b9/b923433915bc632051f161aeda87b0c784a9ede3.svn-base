package com.ait.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8810103358414279214L;

	/**
	 * Constructor of the object.
	 */
	public ReportServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String xlsKey = request.getParameter("xlsKey")!=null?request.getParameter("xlsKey"):"";	
		String condition = request.getParameter("condition")!=null?request.getParameter("condition"):"";
		String from = request.getParameter("from")!=null?request.getParameter("from"):"";
		String to = request.getParameter("to")!=null?request.getParameter("to"):"";
		RequestDispatcher rd = null;
		
		if(!xlsKey.equals("")) {
			if(xlsKey.equals("empNowContrast")){
				
				rd = this.getServletContext().getRequestDispatcher("/hrm/report/re_"+xlsKey+".jsp?countMonth="+condition);
			}else if(xlsKey.equals("dimission")){
				rd = this.getServletContext().getRequestDispatcher("/hrm/report/re_"+xlsKey+".jsp?from="+from+"&to="+to);
				
			}else{
				rd = this.getServletContext().getRequestDispatcher("/hrm/report/re_"+xlsKey+".jsp");
				
			}
			rd.forward(request, response);  
		}  
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
