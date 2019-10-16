package com.ait.sy.sy0101.servlet;

/**
 * <p>Title: AIT  HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.sy.sy0101.bean.Biz;
import com.ait.sy.sy0101.bean.Ent;
import com.ait.util.StringUtil;
import com.ait.utils.Func;

public class Esy0101Control extends HttpServlet {
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
		HttpSession session = request.getSession(true); // 产生session

		Func func = new Func();

//		 UserEntity userEntity = new UserEntity();
//		 userEntity = (UserEntity) session.getAttribute("userEntity");
		
		
		AdminBean adminBean = new AdminBean();
		adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
		Crt_Id=((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		//Crt_Id = "aaron_781203";
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
//					if (request.getParameter("key") != null
//							&& request.getParameter("where") != null) {
//						String key = request.getParameter("key");
//						String where = request.getParameter("where");
//						response.sendRedirect("/sy/" + menu_code
//								+ ".jsp?menu_code=" + menu_code + "&bigpage="
//								+ bigpage + "&strPage=" + strPage + "&key="
//								+ key + "&where=" + where);
//
//					} else {
//						response.sendRedirect("/sy/" + menu_code         
//								+ ".jsp?menu_code=" + menu_code + "&bigpage="
//								+ bigpage + "&strPage=" + strPage);
//					}             
					if (request.getParameter("key")!= null&&!request.getParameter("key").equals("")&&
							!request.getParameter("where").equals("")&& request.getParameter("where") != null) {
						String key =java.net.URLDecoder.decode(request.getParameter("key"),"UTF-8");    
						String where = request.getParameter("where");
						response.sendRedirect("/sy/" + menu_code
								+ ".jsp?menu_code=" + menu_code + "&bigpage="   
								+ bigpage + "&strPage=" + strPage + "&key="
								+ java.net.URLEncoder.encode(key,"UTF-8")  + "&where=" + where);
                             
					} else {                
						response.sendRedirect("/sy/" + menu_code                        
								+ ".jsp?menu_code=" + menu_code + "&bigpage="
								+ bigpage + "&strPage=" + strPage);
					}

				} catch (Exception e) {
					System.out.print(e);
				}
			}

			if (flag.equals("view")) { // 跳转到查看页v
				try {

					String no = request.getParameter("no");

					response.sendRedirect("/intra/" + menu_code
							+ "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
					System.out.print(e);
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
					response.sendRedirect("/sy/" + menu_code
							+ "_m.jsp?menu_code=" + menu_code + "&no=" + no);

				} catch (Exception e) {
					System.out.print(e);
				}
			}

			if (flag.equals("search")) { // search
			} // search end

			if (flag.equals("insert")) { // 执行insert
				try {

					String menuCode = request.getParameter("menuCode");
					String menuIntro = func.strIsoZp(request
							.getParameter("menuIntro"));
					String menuEnIntro = func.strIsoZp(request
							.getParameter("menuEnIntro"));
					String menuParentCode = request
							.getParameter("menuParentCode");
					String menuUrl = request.getParameter("menu_Url");

					int depth = biz.getUpLevel("sy_menu", "depth", "menu_code",
							menuParentCode);  
					Ent.setMenuCode(menuCode);
					Ent.setMenuIntro(menuIntro);
					Ent.setMenuParentCode(menuParentCode);
					Ent.setMenuUrl(menuUrl);
					Ent.setCreatorID(Crt_Id);
					Ent.setDepth(depth);
                    Ent.setMenuEnIntro(menuEnIntro);
					biz.Insert(Ent);
					response.sendRedirect("/sy/" + menu_code + ".jsp?menu_code=" + menu_code);
				} catch (Exception e) {
					System.out.print(e);
				}
			}  

			if (flag.equals("updata")) { // 执行update
				try {
					
					
					String menuCode = StringUtil.checkNull(request.getParameter("menuCode"));
					String menuIntro = StringUtil.checkNull(func.strIsoZp(request.getParameter("menuIntro")));
					String menuEnIntro = StringUtil.checkNull(func.strIsoZp(request.getParameter("menuEnIntro")));
					String menuParentCode = StringUtil.checkNull(request.getParameter("menuParentCode"));
					String menuUrl = StringUtil.checkNull(request.getParameter("menu_Url"));
					String type = StringUtil.checkNull(request.getParameter("type"));
					
					String url = "/sy/" + menu_code + ".jsp?menu_code=" + menu_code ;
										
					Ent.setMenuCode(menuCode);
					Ent.setMenuIntro(menuIntro);
					Ent.setMenuParentCode(menuParentCode);   
					Ent.setMenuUrl(menuUrl);           
					Ent.setCreatorID(Crt_Id);				
					Ent.setMenuNo(Integer.parseInt(request.getParameter("no")));
					Ent.setMenuEnIntro(menuEnIntro);

					if(type.equals("updateActive"))
					{
						String y = StringUtil.checkNull(request.getParameter("bigpage"));
						String x = StringUtil.checkNull(request.getParameter("strPage"));
						String statsValue = StringUtil.checkNull(request.getParameter("statsValue"));
						biz.updateActive(statsValue, Ent.getMenuNo()) ;
						
						url = url + "&bigpage="+y+"&strPage="+x ;
					}
					else
					{
						int depth = biz.getUpLevel("sy_menu", "depth", "menu_code", menuParentCode);//bbbbbbbb
						Ent.setDepth(depth);
						biz.Update(Ent);
					}
					
					response.sendRedirect(url);
				} catch (Exception e) {
					e.printStackTrace() ;
					Logger.getLogger(getClass()).debug(e.toString());
				}
			}
        
			if (flag.equals("delete")) { // 执行delete                        
				try {
					if(biz.searchInScreenGrant(Integer.parseInt(request.getParameter("no"))))
					{
                     biz.DeleteMenuCodeCascade(Integer.parseInt(request.getParameter("no")));
                   } 
					  biz.Delete(Integer.parseInt(request.getParameter("no")));
					  response.sendRedirect("/sy/" + menu_code
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
