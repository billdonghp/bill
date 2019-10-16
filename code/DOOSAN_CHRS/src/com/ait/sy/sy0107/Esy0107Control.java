package com.ait.sy.sy0107;

/**
 * <p>Title: AIT HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
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
import javax.servlet.http.HttpSession;

import com.ait.sy.sy0106.bean.Biz;
import com.ait.sy.sy0106.bean.Ent;
import com.ait.utils.Func;

public class Esy0107Control extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServletContext m_application = null;

	Biz biz = new Biz();

	String Crt_Id; // 登录号

	String Crt_Man; // 登录姓名

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

		out.println("<html>");
		out.println("<head><title>a03control</title></head>");
		out.println("<body>");
		out
				.println("<p>The servlet a03control has received a GET. This is the reply.</p>");
		out.println("</body></html>");
		HttpSession session = request.getSession(true); // 产生session

		Func func = new Func(session);

		// UserEntity userEntity = new UserEntity();
		// userEntity = (UserEntity) session.getAttribute("userEntity");

		String Crt_Id = "";
		Crt_Id = "aaron_781203";
		Biz biz = new Biz();
		String flag = request.getParameter("flag");

		String menu_code = "";

		if (request.getParameter("menu_code") != null) {
			menu_code = (request.getParameter("menu_code"));
		}

		Ent Ent = new Ent();

		// Ent.setCreateUser(LogEmpName); //以管理员session的EmpName作为记录的CreateUser
		// Ent.setModifyUser(LogEmpName); //以管理员session的EmpName作为记录的ModifyUser

		if (flag != null) {
			// set value to mtrrsrventity

			if (flag.equals("list")) { // 跳转到列表页面
				try {

					String bigpage = request.getParameter("bigpage");
					if (bigpage == null) {
						bigpage = "";
					}
					String strPage = request.getParameter("strPage");
					if (strPage == null) {
						strPage = "";
					}

					response.sendRedirect("/sy/" + menu_code
							+ ".jsp?menu_code=" + menu_code + "&bigpage="
							+ bigpage + "&strPage=" + strPage);

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
