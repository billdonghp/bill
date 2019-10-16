package com.ait.sy.sy0105.servlet;

/**
 * <p>Title: AIT  HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.sy0105.bean.Biz;
import com.ait.sy.sy0105.bean.Ent;
import com.ait.util.StringUtil;
import com.ait.utils.Func;

public class Esy0105Control extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServletContext m_application = null;

	Biz biz = new Biz();

	String Crt_Id; //登录号

	String Crt_Man; //登录姓名

	String suser1; //

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	//Initialize global variables
	public void init(ServletConfig config) throws ServletException {
		m_application = config.getServletContext();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Esy0105Control</title></head>");
		out.println("<body>");
		out.println("<p>The servlet Esy0105Control has received a GET. This is the reply.</p>");
		out.println("</body></html>");
		HttpSession session = request.getSession(true); //产生session
		Func func = new Func(session);
		AdminBean admin = new AdminBean();
		admin = (AdminBean) session.getAttribute("admin");
		String Crt_Id = "";
		if (admin != null) {
			Crt_Id = admin.getAdminID();
			Crt_Man = admin.getUsername();
		}
		Biz biz = new Biz();
		String flag = request.getParameter("flag");
		String menu_code = "";
		if (request.getParameter("menu_code") != null) {
			menu_code = (request.getParameter("menu_code"));
		}
		Ent Ent = new Ent();
		//Ent.setCreateUser(LogEmpName); //以管理员session的EmpName作为记录的CreateUser
		// Ent.setModifyUser(LogEmpName); //以管理员session的EmpName作为记录的ModifyUser
		if (flag != null) {
			//set value to mtrrsrventity
			if (flag.equals("list")) { //跳转到列表页面
				try {
					String bigpage = request.getParameter("bigpage");
					if (bigpage == null) {
						bigpage = "";
					}
					String strPage = request.getParameter("strPage");
					if (strPage == null) {
						strPage = "";
					}
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code + "&bigpage=" + bigpage + "&strPage=" + strPage);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			/* if (flag.equals("add")) { //跳转到添加页面a
			 try {
			 response.sendRedirect("/manage/" + menu_code + "a.jsp?menu_code=" +
			 menu_code);
			 }
			 catch (Exception e) {
			 System.out.print(e);
			 }
			 }
			 */
			if (flag.equals("view")) { //跳转到查看页v
				try {
					String no = request.getParameter("no");
					response.sendRedirect("/sy/E" + menu_code + "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			if (flag.equals("next")) { //跳转到下一条查看页v
				try {
					menu_code = request.getParameter("menu_code");
					int no = Integer.parseInt(request.getParameter("no"));
					no = func.next("board_mn", "no", no);
					response.sendRedirect("/intra/" + menu_code + "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			if (flag.equals("back")) { //跳转到上一条查看页v
				try {
					menu_code = request.getParameter("menu_code");
					int no = Integer.parseInt(request.getParameter("no"));
					no = func.back("board_mn", "no", no);
					response.sendRedirect("/intra/" + menu_code + "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			if (flag.equals("modify")) { //跳转到修改页m
				try {
					String no = request.getParameter("no");
					response.sendRedirect("/intra/" + menu_code + "m.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			/*
			 if (flag.equals("replygo")) { //跳转到修改页m
			 try {
			 String no = request.getParameter("no");

			 response.sendRedirect("/intra/" + menu_code + "r.jsp?menu_code=" +
			 menu_code + "&no=" + no);
			 }
			 catch (Exception e) {
			 System.out.print(e);
			 }
			 }
			 */
			if (flag.equals("search")) { //search
			} //search end
			if (flag.equals("insert")) { //执行insert
				try {
					int rowcount = Integer.parseInt(request.getParameter("rowcount"));
					String screenGrantNo = request.getParameter("screenGrantNo");
					String screenGrantName = func.strIsoZp(request.getParameter("screenGrantName"));
					String screenGrantEnName = func.strIsoZp(request.getParameter("screenGrantEnName")); 
					String cpnyId=request.getParameter("cpnyId");
					Ent.setScreenGrantNo(screenGrantNo);
					Ent.setScreenGrantName(screenGrantName);
					Ent.setScreenGrantEnName(screenGrantEnName);     
					Ent.setCpnyID(cpnyId);
					Ent.setCreatorID(Crt_Id);
					biz.Insertname(Ent);
					Vector menu_vector = biz.menulist();           
					for (int i = 0; i < rowcount; i++) {
						String screenCode = request.getParameter("screenCode" + Integer.toString(i)) != null ? request.getParameter("screenCode" + Integer.toString(i)) : "";
						String selectr = request.getParameter("RFore" + Integer.toString(i)) != null ? request.getParameter("RFore" + Integer.toString(i)) : "";
						String insertr = request.getParameter("RInsert" + Integer.toString(i)) != null ? request.getParameter("RInsert" + Integer.toString(i)) : "";
						String updater = request.getParameter("RUpdate" + Integer.toString(i)) != null ? request.getParameter("RUpdate" + Integer.toString(i)) : "";
						String deleter = request.getParameter("RDelete" + Integer.toString(i)) != null ? request.getParameter("RDelete" + Integer.toString(i)) : "";
						String code = request.getParameter("code" + Integer.toString(i)) != null ? request.getParameter("code" + Integer.toString(i)) : "";
						Ent.setScreenCode(screenCode);
						Ent.setSelectr(selectr);
						Ent.setInsertr(insertr);
						Ent.setUpdater(updater);
						Ent.setDeleter(deleter);
						Ent.setCreatorID(Crt_Id);
						Ent.setCode(code);
						biz.Insert(Ent, menu_vector);
					}
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			if (flag.equals("update")) { //执行update
				try {
					int rowcount = Integer.parseInt(request.getParameter("rowcount"));
					String screenGrantNo = request.getParameter("screenGrantNo");
					String screenGrantNo_O = request.getParameter("screenGrantNo_O");
					String screenGrantName = StringUtil.toCN(request.getParameter("screenGrantName"));
					Ent.setScreenGrantNo_O(screenGrantNo_O);
					Ent.setScreenGrantNo(screenGrantNo);
					Ent.setScreenGrantName(screenGrantName);
					List entList = new ArrayList();
					for (int i = 0; i < rowcount; i++) {
						String screenCode = request.getParameter("screenCode" + Integer.toString(i)) != null ? request.getParameter("screenCode" + Integer.toString(i)) : "";
						int selectr = request.getParameter("RFore" + Integer.toString(i)) != null ? 1 : 0;
						int insertr = request.getParameter("RInsert" + Integer.toString(i)) != null ? 1 : 0;
						int updater = request.getParameter("RUpdate" + Integer.toString(i)) != null ? 1 : 0;
						int deleter = request.getParameter("RDelete" + Integer.toString(i)) != null ? 1 : 0;
						String code = request.getParameter("code" + Integer.toString(i)) != null ? request.getParameter("code" + Integer.toString(i)) : "";
						com.ait.sy.sy0101.bean.Ent screenEnt = new com.ait.sy.sy0101.bean.Ent();
						screenEnt.setMenuCode(screenCode);
						Logger.getLogger(getClass()).debug(screenCode + "-" + selectr + "-" + insertr + "-" + updater + "-" + deleter);
						screenEnt.setInsertr(insertr);
						screenEnt.setSelectr(selectr);
						screenEnt.setUpdater(updater);
						screenEnt.setDeleter(deleter);
						screenEnt.setCode(code);
						screenEnt.setCreatorID(Crt_Id);
						entList.add(screenEnt);
					}
					biz.Updata(screenGrantNo_O, entList);
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code);
				} catch (Exception e) {
					e.printStackTrace();
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
			if (flag.equals("delete")) { //执行delete
				try {
					biz.Delete(request.getParameter("no"));
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		}
	}

	//Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	//Process the HTTP Put request
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	//Process the HTTP Delete request
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	//Clean up resources
	public void destroy() {
	}
}
