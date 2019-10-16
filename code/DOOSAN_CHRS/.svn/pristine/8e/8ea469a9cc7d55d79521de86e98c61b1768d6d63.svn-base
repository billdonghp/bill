package com.ait.evs.servlet;

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

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.evs.evs0126.bean.BizEvsCode;
import com.ait.evs.evs0126.bean.EntEvsCode;
import com.ait.sqlmap.util.CodeUtil;
import com.ait.sy.bean.AdminBean; 
import com.ait.util.StringUtil;
import com.ait.utils.Func;

public class Eevs0126Control extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServletContext m_application = null;

	BizEvsCode biz = new BizEvsCode();

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
		AdminBean adminBean = new AdminBean();
		adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
		Crt_Id=((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		BizEvsCode biz = new BizEvsCode();
		String flag = request.getParameter("flag");

		String menu_code = "";
  
		if (request.getParameter("menu_code") != null) {
			menu_code = (request.getParameter("menu_code"));
		}
		EntEvsCode Ent = new EntEvsCode();

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

			/*
			 * if (flag.equals("add")) { //跳转到添加页面a try {
			 * response.sendRedirect("/manage/" + menu_code + "a.jsp?menu_code=" +
			 * menu_code); } catch (Exception e) { System.out.print(e); } }
			 */
			if (flag.equals("view")) { // 跳转到查看页v
				try {
					String no = request.getParameter("no");
					response.sendRedirect("/sy/E" + menu_code + "v.jsp?menu_code=" + menu_code + "&no=" + no);
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
                    Logger.getLogger(getClass()).debug(e.toString());
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
                    Logger.getLogger(getClass()).debug(e.toString());
				}
			}

			if (flag.equals("modify")) { // 跳转到修改页m
				try {
					String no = request.getParameter("no");

					response.sendRedirect("/intra/" + menu_code
							+ "m.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
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

					String basicCode = func.strIsoZp(request.getParameter("basicCode") != null ? request
							.getParameter("basicCode")
							: "");
					String basicName = func.strIsoZp(request
							.getParameter("basicName") != null ? request  
							.getParameter("basicName") : "");
					String basicEnName= func.strIsoZp(request.getParameter("basicEnName") != null ? request
							.getParameter("basicEnName")
							: "");
					String parentCode = request.getParameter("parentCode") != null ? request
							.getParameter("parentCode")
							: "";
					String depth = request.getParameter("depth") != null ? request
							.getParameter("depth")
							: "";
					String cpnyId=request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):"";
					String evscodeflag = null;
					if(depth.equals("1"))
					{
					   evscodeflag = "LE";	
					}
					else if(depth.equals("2"))
					{
					   evscodeflag = "PE";	
					}else{
						evscodeflag = "JC";	
					}
					Ent.setBasicCode(basicCode);
					Ent.setParentCode(parentCode);
					Ent.setBasicName(basicName);  
					Ent.setCreatorID(Crt_Id);
					Ent.setCodeEnName(basicEnName);
					Ent.setCpnyId(cpnyId);
					Ent.setEvsflag(evscodeflag);
					biz.Insert(Ent, depth);
					// refresh code list
					CodeUtil.getInstance().refreshCodes();
					if (parentCode.equals("0")) {
						response.sendRedirect("/evs/" + menu_code
								+ ".jsp?menu_code=" + menu_code);
					} else {
						response
								.sendRedirect("/evs/evs_code_detail.jsp?menu_code="
										+ menu_code + "&code_id=" + parentCode);
					}
				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}
			}               
			
			if (flag.equals("updateSubCode")){
				int codeNo = Integer.parseInt(request.getParameter("codeNo"));
				String codeName = StringUtil.toCN(request.getParameter("codeName"));
				String codeEnName = StringUtil.toCN(request.getParameter("basicEnName"));
				String cpnyId = StringUtil.toCN(request.getParameter("cpnyId"));
				biz.update(codeNo, codeName,codeEnName,cpnyId,Crt_Id);
				// refresh code list
				CodeUtil.getInstance().refreshCodes();
				String parentCode =  request.getParameter("parentCode");
				response.sendRedirect("/evs/evs_code_detail.jsp?menu_code="
						+ menu_code + "&code_id=" + parentCode);
			}

			if (flag.equals("updata")) { // 执行update
				try {
                    Logger.getLogger(getClass()).debug("update");
					String basicCode = request.getParameter("basicCode");
					String basicName = func.strIsoZp(request
							.getParameter("basicName"));
					String codeEnName = func.strIsoZp(request.getParameter("basicEnName"));
					String no= request.getParameter("no");
					String cpny_id = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):"";
					Ent.setBasicCode(basicCode);
					Ent.setBasicName(basicName);
					Ent.setCodeEnName(codeEnName);
					Ent.setModifierID(Crt_Id);
					Ent.setCreatorID(no);
					Ent.setCpnyId(cpny_id);
			
					biz.Update(Ent);
					// refresh code list
					CodeUtil.getInstance().refreshCodes();
					response.sendRedirect("/evs/" + menu_code + ".jsp?menu_code=" + menu_code);

				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}

			}

			if (flag.equals("delete")) { // 执行delete
				//System.out.println("/evs/" + menu_code+ ".jsp");
				try {
					String code_id = request.getParameter("code_id") != null ? request
							.getParameter("code_id")
							: "";
		        //System.out.println(code_id);
					biz.Delete(request.getParameter("no"));
					// refresh code list
					CodeUtil.getInstance().refreshCodes();
					if (!code_id.equals("")) {
						response.sendRedirect("/evs/evs_code_detail.jsp?code_id="
								+ code_id);

					} else {
						response.sendRedirect("/evs/" + menu_code
								+ ".jsp?menu_code=" + menu_code);
					}

				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
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
