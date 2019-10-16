package com.ait.utils;

/**
 * <p>Title: AIT  HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhotoControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ServletContext m_application = null;

	String LogID; // 登录名

	String LogEmpName; // 登录姓名

	int LogCmpnID; // 公司号

	int LogDeptID; // 部门号

	String suser1; //

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	// Initialize global variables
	public void init(ServletConfig config) throws ServletException {
		m_application = config.getServletContext();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();

		String Crt_Id = "";// admin.getEmpID();

		// String menu_code = request.getParameter("menu_code");
		String flag = request.getParameter("flag");
		if (flag != null) {
			// set value to mtrrsrventity
			String phtoPath = "/upload/photo";
			if (flag.equals("insert")) { // 执行insert
				Photo Photo = new Photo();
				try {
					byte[] image = null;
					uploadpic up = new uploadpic(request, response,
							m_application);

					if (up.getdata()) {
						String empID = request.getParameter("empID");
						// BLOB blob=null;
						String fileName = "";
						// String fileUrl = "";
						up.initFileComents();
						up.disposeData();
						if (Photo.checkPhoto(empID) == 3
								|| Photo.checkPhoto(empID) == 1)
							;
						up.deletefile("", fileName);
						up.setFilePath(phtoPath);
						up.WriteMdata();
						String[] filenames = up.getFilenames();
						// String[] oldfilenames = up.getOldFileNames();

						// fileName = oldfilenames[0];

						fileName = filenames[0];
						fileName = "/upload/photo/" + fileName;
						// if(Photo.checkPhoto(empID)!=0)

						Photo.delPhoto(empID);
						Photo.insert(empID, fileName);
						image = up.getFileBytes();
						Photo.insertBin(empID, image, Crt_Id);
					}
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("alert('更新成功');");
					out.println("self.close();");
					out.println("</SCRIPT>");
				} catch (Exception e) {
					System.out.print(e);
				}
			}

			if (flag.equals("delete")) { // 执行delete
				try {
					String empID = request.getParameter("empID");
					Photo Photo = new Photo();
					Photo.delPhoto(empID);
					File file1 = new File(phtoPath + "/" + empID + ".*");
					file1.delete();
					response.sendRedirect("/util/photoChange.jsp?empid="
							+ empID);
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
