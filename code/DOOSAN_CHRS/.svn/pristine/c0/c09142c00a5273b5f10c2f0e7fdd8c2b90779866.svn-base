package com.ait.sy.sy0104.servlet;

/**
 * <p>Title: AIT  HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.sy.sy0104.bean.Biz;
import com.ait.sy.sy0104.bean.Ent;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.utils.Func;

public class Esy0104Control extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServletContext m_application = null;

	Biz biz = new Biz();

	String Crt_Id; // 登录号

	String Crt_Man; // 登录姓名

	String suser1; //

	// private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	// Initialize global variables
	public void init(ServletConfig config) throws ServletException {
		m_application = config.getServletContext();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true); // 产生session
		Func func = new Func();
		// UserEntity userEntity = new UserEntity();
		// userEntity = (UserEntity) session.getAttribute("userEntity");
		AdminBean admin = new AdminBean();
		admin = (AdminBean) session.getAttribute("admin");
		String Crt_Id = "";
		if (admin != null) {
			Crt_Id = admin.getAdminID();
			Crt_Man = admin.getUsername();
		}
//		String Crt_Id = "";
		
//		Crt_Id = "aaron_781203";
		Biz biz = new Biz();
		String flag = request.getParameter("flag");
		String menu_code = "";
		if (request.getParameter("menu_code") != null) {
			menu_code = (request.getParameter("menu_code"));
		}
		Ent Ent = new Ent();
		if (flag != null) {
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
					Logger.getLogger(getClass()).debug(request.getParameter("key"));
					String key = StringUtil.checkNull(request.getParameter("key"));
					String where = StringUtil.checkNull(request.getParameter("where"));
					if (!key.equals("") && !where.equals("")) {
						response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code + "&bigpage=" + bigpage + "&strPage=" + strPage + "&key=" + java.net.URLEncoder.encode(key,"UTF-8") + "&where=" + where);
					} else {
						response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code + "&bigpage=" + bigpage + "&strPage=" + strPage);
					}
				} catch (Exception e) {
					Logger.getLogger(getClass()).debug(e.toString());
					e.printStackTrace();
				}
			}
			if (flag.equals("view")) { // 跳转到查看页v
				try {
					String no = request.getParameter("no");
					response.sendRedirect("/intra/" + menu_code + "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
			if (flag.equals("next")) { // 跳转到下一条查看页v
				try {
					menu_code = request.getParameter("menu_code");
					int no = Integer.parseInt(request.getParameter("no"));
					no = func.next("board_mn", "no", no);
					response.sendRedirect("/intra/" + menu_code + "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
			if (flag.equals("back")) { // 跳转到上一条查看页v
				try {
					menu_code = request.getParameter("menu_code");
					int no = Integer.parseInt(request.getParameter("no"));
					no = func.back("board_mn", "no", no);
					response.sendRedirect("/intra/" + menu_code + "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
			if (flag.equals("modify")) { // 跳转到修改页m
				try {
					String no = request.getParameter("no");
					response.sendRedirect("/sy/" + menu_code + "_m.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
			if (flag.equals("search")) { // search
			} // search end
			if (flag.equals("insert")) { // 执行insert
				try {
					String empID = request.getParameter("empID");
					//String userID = request.getParameter("userID");
					//String passWord = request.getParameter("passWord");
					String[] dp = request.getParameterValues("dp");
					String[] radio = request.getParameterValues("radio");
					String str = "";
					for (int i = 0; i < radio.length; i++) {
						str += radio[i].toString() + ",";
					}
					int length = str.lastIndexOf(",");
					str = str.substring(0, length);
					// Ent.setScreenGrantno(request.getParameter("loginDeptID"));
					Ent.setEmpID(empID);
					//Ent.setUserID(userID);
					//Ent.setPassWord(passWord);
					Ent.setScreenGrantno(str);
					Ent.setCreatorID(Crt_Id);
					biz.Insert(Ent);
					biz.addGrantDeptid(biz.getAdminNo(empID), dp);
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code);
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			if (flag.equals("updata")) { // 执行update
				try {
					int no = NumberUtil.parseInt(request.getParameter("no"));
					String empID = request.getParameter("empID");
					//String userID = request.getParameter("userID");
					//String passWord = request.getParameter("passWord");
					String[] dp = request.getParameterValues("dp");
					String btr = "";
					Enumeration f = request.getParameterNames();
					while (f.hasMoreElements()) {
						String name = (String) f.nextElement();
						if (name.substring(0, 2).equals("dp")) {
							btr = btr + request.getParameter(name).trim() + ",";
						}
					}
					String[] radio = request.getParameterValues("radio");
					String str = "";
					for (int i = 0; i < radio.length; i++) {
						str += radio[i].toString() + ",";
					}
					int length = str.lastIndexOf(",");
					str = str.substring(0, length);
					Ent.setEmpID(empID);
					//Ent.setUserID(userID);
					//Ent.setPassWord(passWord);
					Ent.setScreenGrantno(str);
					Ent.setCreatorID(Crt_Id);
					Ent.setLoginDeptID(btr);
					Ent.setLoginNo(no);
					Ent.setModifierID(Crt_Id);
					biz.deleteAdminDept(Integer.toString(no));
					if(dp!=null)
					biz.addGrantDeptid(Integer.toString(no), dp);
					biz.Update(Ent);
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code);
				} catch (Exception e) {
					e.printStackTrace();
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
			
			if (flag.equals("updataafftypid")) { // 执行update
				try {
					int no = NumberUtil.parseInt(request.getParameter("no"));
					String empID = request.getParameter("key");
					String affirmtypeId = request.getParameter("affirmtypeId");
					String[] dp = request.getParameterValues("dp");
					String btr = "";
					Enumeration f = request.getParameterNames();
					while (f.hasMoreElements()) {
						String name = (String) f.nextElement();
						if (name.substring(0, 2).equals("dp")) {
							btr = btr + request.getParameter(name).trim() + ",";
						}
					}
					biz.deleteAdminTypeDept(Integer.toString(no),affirmtypeId);
					if(dp!=null){
					biz.addGrantTypeDeptid(empID, dp,affirmtypeId);
					}
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code);
				} catch (Exception e) {
					e.printStackTrace();
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
			
			if (flag.equals("delete")) { // 执行delete
				try {
					biz.deleteAdminDept(request.getParameter("no"));
					biz.Delete(Integer.parseInt(request.getParameter("no")));
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code);
				} catch (Exception e) {
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
		}
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Process the HTTP Put request
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	// Process the HTTP Delete request
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	// Clean up resources
	public void destroy() {
	}
}
