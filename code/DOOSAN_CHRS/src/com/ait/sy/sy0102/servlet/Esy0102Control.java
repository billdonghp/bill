package com.ait.sy.sy0102.servlet;

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

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.sy.sy0102.bean.Biz;
import com.ait.sy.sy0102.bean.Ent;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.utils.Func;

public class Esy0102Control extends HttpServlet {
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

    @SuppressWarnings("unchecked")
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
	AdminBean admin = (AdminBean) session.getAttribute("admin");
	Func func = new Func();

	String Crt_Id = admin.getAdminID();
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
            String key=request.getParameter("key")!=null?request.getParameter("key"):"";
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
			    + bigpage +"&key="+java.net.URLEncoder.encode(key,"UTF-8")+ "&strPage=" + strPage);   

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
		    String deptId = StringUtil.toCN(request
			    .getParameter("deptId"));
		    String deptName = StringUtil.toCN(request
			    .getParameter("deptName"));
		    String deptEnName = StringUtil.toCN(request
				    .getParameter("deptEnName"));
		    String parentDeptId = StringUtil.toCN(request.getParameter("parentDeptId")!=null?request.getParameter("parentDeptId"):"POSCO"); 
		    String createdDate = request.getParameter("createdDate");
		    Ent.setDeptcode(deptId);
		    Ent.setDeptName(deptName);
		    Ent.setDeptEnName(deptEnName);
		    Ent.setParentDeptNo(parentDeptId);      
		    Ent.setCreatorID(Crt_Id);                
		    Ent.setCreatedDate(createdDate);            
		    biz.Insert(Ent);   

		    response.sendRedirect("/sy/" + menu_code
			    + ".jsp?menu_code=" + menu_code);

		} catch (Exception e) {
		    System.out.print(e);
		}
	    }

	    if (flag.equals("updata")) { // 执行update
		try {
		    String deptCode = StringUtil.toCN(request
			    .getParameter("deptId"));
		    String deptName = StringUtil.toCN(request
			    .getParameter("deptName"));
		    String deptEnName = StringUtil.toCN(request
				    .getParameter("deptEnName"));
		    String endEddate = StringUtil.checkNull(request
			    .getParameter("endEddate"));
		    String deptNo = request.getParameter("no");
		    String parentDeptNo = StringUtil.toCN(request
			    .getParameter("parentDeptId"));
		    Ent.setDeptID("deptId");
		    Ent.setDeptName(deptName);
		    Ent.setEndEddate(endEddate);
		    Ent.setParentDeptNo(parentDeptNo);
		    Ent.setModifierID(Crt_Id);
		    Ent.setDeptID(deptNo);
		    Ent.setDeptEnName(deptEnName);
		    
		    
		    
		    String message= biz.Update(Ent);
		    response.sendRedirect("/sy/" + menu_code  + ".jsp?menu_code=" + menu_code+"&message="+message);
		} catch (Exception e) {
		    Logger.getLogger(getClass()).debug(e.toString());
		    e.printStackTrace();
		}

	    }

	    if (flag.equals("updataDeptOrder")) { // 执行update
		try {
		    Logger.getLogger(this.getClass()).debug("updataDeptOrder");
		    List lDept = new ArrayList();
		    String[] deptId = request.getParameterValues("deptId");
		    if (deptId != null) {
			for (int i = 0, j = deptId.length; i < j; i++) {
			    Ent ent = new Ent();
			    ent.setDeptID(deptId[i]);
			    ent.setOrderNo(NumberUtil.parseInt(request
				    .getParameter("orderNo_" + deptId[i]), 1));
			    Logger.getLogger(this.getClass()).debug(ent);
			    lDept.add(ent);
			}
		    }
		    biz.updateOrder(lDept);
		    response.sendRedirect("/sy/" + menu_code
			    + ".jsp?menu_code=" + menu_code);
		} catch (Exception e) {
		    Logger.getLogger(getClass()).debug(e.toString());
		    e.printStackTrace();
		}
       
    }                                     

	    if (flag.equals("delete")) { // 执行delete    
		try {

		    biz.Delete(request.getParameter("no"));       
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
