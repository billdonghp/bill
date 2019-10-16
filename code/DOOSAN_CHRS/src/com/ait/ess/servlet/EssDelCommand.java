package com.ait.ess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ess.business.EssServices;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.util.NumberUtil;
import com.ait.web.Command;

public class EssDelCommand implements Command {

	private EssServices essServices;
	
//	private ArServices arServices;

	private String adminId; // 当前登录者ID

	public EssDelCommand() {
		essServices = new EssServices();
//		arServices = new ArServices();
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = StringUtil.checkNull(request.getParameter("content"));
		adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug("content : " + content);

		/* 员工删除加班申请 */
		if (content.equals("otview"))
			return this.delOvertimeInfo(request, response);
		/* 员工删除休假申请 */
		else if (content.equals("leaveview"))
			return this.delLeaveInfo(request, response);
		/* 员工删除加班上限申请 */
		else if (content.equals("ottoplimitview"))
			return this.delOvertimeTopLimitInfo(request, response);
		else
			return "/error.jsp";
	}

	/**
	 * 加班信息删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String delOvertimeInfo(HttpServletRequest request, HttpServletResponse response) {
		String adminId = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		try {
			String[] otNo = request.getParameterValues("otNo") ; 
			
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			String flag = StringUtil.checkNull(request.getParameter("flag"));// 判断是否从首页连接过来的
			String deptID = StringUtil.checkNull(request.getParameter("deptID"), "Z000000");
			
			essServices.setAdminId(adminId);
			
			for (int i = 0 ; i < otNo.length ; ++ i)
			{
				int no = NumberUtil.parseInt(otNo[i]);
				essServices.delApply(no, "OtApply") ;
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(getClass()).error(ex.toString());
		}

		return "essControlServlet?operation=view&content=otview&menu_code=ess0607";
	}

	/**
	 * 休假信息删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String delLeaveInfo(HttpServletRequest request, HttpServletResponse response) {
		AdminBean admin = ((AdminBean) request.getSession().getAttribute("admin")) ;
		try {		
			String[] leaveNo = request.getParameterValues("leaveNo") ; 
			
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			String deptID = StringUtil.checkNull(request.getParameter("deptID"), "Z000000");
			String flag = StringUtil.checkNull(request.getParameter("flag"));

			essServices.setAdminId(admin.getAdminID());
			for (int i = 0 ; i < leaveNo.length ; ++ i)
			{
				int no = NumberUtil.parseInt(leaveNo[i]);
				essServices.delApply(no, "LeaveApply");
				if (admin.getCpnyId().equals("63000000"))
					essServices.delApplyH9Info(no);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
			return "/error.jsp";
		}
		return "essControlServlet?operation=view&content=leaveview&menu_code=ess0608";
	}

	/**
	 * 加班上限信息删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String delOvertimeTopLimitInfo(HttpServletRequest request, HttpServletResponse response) {
		String adminId = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		try {
			int leaveNo = Integer.parseInt(StringUtil.checkNull(request.getParameter("otTopLimitNo"), "0"));
			String arMonth = StringUtil.checkNull(request.getParameter("arMonth"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			String deptID = StringUtil.checkNull(request.getParameter("deptID"), "Z000000");
			String flag = StringUtil.checkNull(request.getParameter("flag"));

			essServices.setAdminId(adminId);
			essServices.delApply(leaveNo, "OtTopLimitApply");

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
			return "/error.jsp";
		}
		return "essControlServlet?operation=view&content=ottoplimitview&menu_code=ess0613";
	}
}
