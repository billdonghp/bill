package com.ait.sy.sy0106.servlet;

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

import org.apache.log4j.Logger;

import com.ait.sy.sy0106.bean.Biz;
import com.ait.sy.sy0106.bean.Ent;
import com.ait.utils.Func;

public class Esy0106control extends HttpServlet {
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

					response.sendRedirect("/intra/" + menu_code
							+ ".jsp?menu_code=" + menu_code + "&bigpage="
							+ bigpage + "&strPage=" + strPage);

				} catch (Exception e) {
					System.out.print(e);
				}
			}

			/*
			 * if (flag.equals("add")) { //跳转到添加页面a try {
			 * response.sendRedirect("/manage/" + menu_code + "a.jsp?menu_code=" +
			 * menu_code); } catch (Exception e) { System.out.print(e); } }
			 */
			if (flag.equals("view")) { // 跳转到查看页v
				try {
					String no = request.getParameter("no");
					response.sendRedirect("/intra/" + menu_code
							+ "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}
			}

			if (flag.equals("next")) { // 跳转到下一条查看页v
				try {
					menu_code = request.getParameter("menu_code");
					int no = Integer.parseInt(request.getParameter("no"));
					no = func.next("board_mn", "no", no);
					response.sendRedirect("/intra/" + menu_code
							+ "v.jsp?menu_code=" + menu_code + "&no=" + no);

				} catch (Exception e) {
					System.out.print(e);
				}
			}

			if (flag.equals("back")) { // 跳转到上一条查看页v
				try {
					menu_code = request.getParameter("menu_code");
					int no = Integer.parseInt(request.getParameter("no"));
					no = func.back("board_mn", "no", no);
					response.sendRedirect("/intra/" + menu_code
							+ "v.jsp?menu_code=" + menu_code + "&no=" + no);

				} catch (Exception e) {
					System.out.print(e);
				}
			}

			if (flag.equals("modify")) { // 跳转到修改页m
				try {
					String no = request.getParameter("no");

					response.sendRedirect("/intra/" + menu_code
							+ "m.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			/*
			 * if (flag.equals("replygo")) { //跳转到修改页m try { String no =
			 * request.getParameter("no");
			 * 
			 * response.sendRedirect("/intra/" + menu_code + "r.jsp?menu_code=" +
			 * menu_code + "&no=" + no); } catch (Exception e) {
			 * System.out.print(e); } }
			 */

			if (flag.equals("search")) { // search


			} // search end

			if (flag.equals("insert")) { // 执行insert
				try {

					String cnpyID = request.getParameter("cnpyID");
					String empID = request.getParameter("empID");
					String decTypeID = request.getParameter("decTypeID");
					String decLevelID = request.getParameter("decLevelID");
					String activity = request.getParameter("activity");
					String decFlag = request.getParameter("decFlag");

					Ent.setCnpyID(cnpyID);
					Ent.setEmpID(empID);
					Ent.setDecTypeID(decTypeID);
					Ent.setDecLevelID(decLevelID);
					Ent.setActivity(Integer.parseInt(activity));
					Ent.setDecFlag(Integer.parseInt(decFlag));
					Ent.setCreatorID(Crt_Id);

					// Ent.setUse_Flag("1");
					biz.Insert(Ent);

					response.sendRedirect("/jsp/E" + menu_code
							+ ".jsp?menu_code=" + menu_code);

				} catch (Exception e) {
					System.out.print(e);
				}
			}

			if (flag.equals("updata")) { // 执行update
				try {

					String cnpyID = request.getParameter("cnpyID");
					String empID = request.getParameter("empID");
					String decTypeID = request.getParameter("decTypeID");
					String decLevelID = request.getParameter("decLevelID");
					String activity = request.getParameter("activity");
					String orderNo = request.getParameter("orderNo");
					String decFlag = request.getParameter("decFlag");

					Ent.setCnpyID(cnpyID);
					Ent.setEmpID(empID);
					Ent.setDecTypeID(decTypeID);
					Ent.setDecLevelID(decLevelID);
					Ent.setActivity(Integer.parseInt(activity));
					Ent.setOrderNo(Integer.parseInt(orderNo));
					Ent.setDecFlag(Integer.parseInt(decFlag));
					Ent.setModifierID(Crt_Id);
					Ent.setGrantEmpNo(Integer.parseInt(request
							.getParameter("no")));
					biz.Update(Ent);
					response.sendRedirect("/jsp/E" + menu_code
							+ ".jsp?menu_code=" + menu_code);

				} catch (Exception e) {
					System.out.print(e);
				}

			}

			if (flag.equals("delete")) { // 执行delete
				try {

					biz.Delete(Integer.parseInt(request.getParameter("no")));
					response.sendRedirect("/jsp/E" + menu_code
							+ ".jsp?menu_code=" + menu_code);
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
