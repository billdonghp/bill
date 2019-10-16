package com.ait.utils;

/**
 * <p>Title: AIT  HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT CHEM</p>
 * @author AIT
 * @version 1.0
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class upImage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServletContext m_application = null;

	public String File1Name = "";

	public String File1Url = "";

	public String File2Name = "";

	public String File2Url = "";

	public String File3Name = "";

	public String File3Url = "";

	public String File4Name = "";

	public String File4Url = "";

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	// Initialize global variables
	public void init(ServletConfig config) throws ServletException {
		m_application = config.getServletContext();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>UpImage</title></head>");
		out.println("<body>");
		out
				.println("<p>The servlet UpImage has received a GET. This is the reply.</p>");
		out.println("</body></html>");

		Func func = new Func();
		String flag = request.getParameter("flag");

		if (flag != null) {

			if (flag.equals("insert")) { // 执行insert
				try {
					Upload up = new Upload(request, response, m_application);
					if (up.getdata()) {

						up.initFileComents();
						up.disposeData();
						up.setFilePath("/upload/files");
						up.WriteMdata();
						String[] filenames = up.getFilenames();
						String[] oldfilenames = up.getOldFileNames();

						File1Name = func.isoStr(oldfilenames[0]);
						File1Url = func.isoStr(filenames[0]);
						File2Name = func.isoStr(oldfilenames[1]);
						File2Url = func.isoStr(filenames[1]);
						File3Name = func.isoStr(oldfilenames[2]);
						File3Url = func.isoStr(filenames[2]);
						File4Name = func.isoStr(oldfilenames[3]);
						File4Url = func.isoStr(filenames[3]);

					}
					response.sendRedirect("/util/upImageInfo.jsp?"
							+ "File1Name=" + File1Name + "&File1Url="
							+ File1Url + "&File2Name=" + File2Name
							+ "&File2Url=" + File2Url + "&File3Name="
							+ File3Name + "&File3Url=" + File3Url
							+ "&File4Name=" + File4Name + "&File4Url="
							+ File4Url);
				} catch (Exception e) {
					System.out.print(e);
				}
			}

			if (flag.equals("add")) { // 执行delete
				try {
					response.sendRedirect("/util/upImage.jsp");
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
