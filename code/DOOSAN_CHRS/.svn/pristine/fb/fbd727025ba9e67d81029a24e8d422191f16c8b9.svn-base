package com.ait.ga.servlet;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;

public class GaUpdateCommand implements Command {
	private GaServices gaServices;

	private SimpleMap parameterObject;

	private String content = null;

	public GaUpdateCommand() {
		gaServices = new GaServices();
	}

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
		content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			if (content.equals("visitmodify")) {
				returnPage = visitModify(request);
			}
		} else {
			Logger.getLogger(getClass()).error("get content parameter fail!");
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("return Page : " + returnPage);
		return returnPage;
	}

	public void putToolbarInfo(HttpServletRequest request) throws GlRuntimeException {

		try {
			SimpleMap map = new SimpleMap();

			// get paramter from request object         
			List toolMenuList = null;
			List menuentList = null;
			ToolMenu toolmenu = new ToolMenu();
			MenuBiz menubiz = new MenuBiz();
			String menu_code = request.getParameter("menu_code");
			String operation = request.getParameter("operation");
			AdminBean admin = (AdminBean) (request.getSession().getAttribute("admin"));
			String rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";

			// get process popedom list
			toolMenuList = menubiz.toolMenuList(menu_code, rodeLevel);
			menuentList = menubiz.thirdmenulist(menu_code, rodeLevel);

			int selectMenu = 0;
			int insertMenu = 0;
			int updateMenu = 0;
			int deleteMenu = 0;
			for (int i = 0; i < toolMenuList.size(); i++) {

				toolmenu = (ToolMenu) toolMenuList.get(i);

				if (toolmenu.getSelect() == 1) {

					selectMenu = 1;
				}
				if (toolmenu.getInsertr() == 1) {

					insertMenu = 1;
				}
				if (toolmenu.getUpdate() == 1) {

					updateMenu = 1;
				}
				if (toolmenu.getDelect() == 1) {

					deleteMenu = 1;
				}
			}
			// save insert,update,delete popedom
			map.setInt("selectMenu", selectMenu);
			map.setInt("insertMenu", insertMenu);
			map.setInt("updateMenu", updateMenu);
			map.setInt("deleteMenu", deleteMenu);
			// save MenuEnt object list
			map.set("menuentList", menuentList);
			// save menu code
			map.setString("menu_code", menu_code);
			map.setString("operation", operation);

			request.setAttribute("toolbarInfo", map);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ArAbstractCommand put toolbar information to request Exception.", e);
		}
	}

	
	private String visitModify(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			String[] ids = request.getParameterValues("id");
			for (int i=0;i<ids.length;i++){
				int id = parameterObject.getInt("id_"+ids[i]);
				String visitor_name = parameterObject.getString("visitor_name_"+ids[i]);
				String visitor_company = parameterObject.getString("visitor_company_"+ids[i]);
				String visitor_idcard = parameterObject.getString("visitor_idcard_"+ids[i]);
				String visitor_auto = parameterObject.getString("visitor_auto_"+ids[i]);
				String visited_empid = parameterObject.getString("visited_empid_"+ids[i]);
				String visited_name = parameterObject.getString("visited_name_"+ids[i]);
				String visit_reason = parameterObject.getString("visit_reason_"+ids[i]);
				String in_time = parameterObject.getString("in_time_"+ids[i]);
				//String in_confirm = parameterObject.getString("in_confirm_"+ids[i]);
				String out_time = parameterObject.getString("out_time_"+ids[i]);
				if (out_time.equals("1"))
					out_time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new GregorianCalendar().getTime());
				String out_confirm = parameterObject.getString("out_confirm_"+ids[i]);
				if (out_confirm.equals("1"))
					out_confirm = adminId;
				String out_cardid = parameterObject.getString("out_cardid_"+ids[i]);
				String brief = parameterObject.getString("brief_"+ids[i]);
	
				parameterObject.setInt("ID", id);
				parameterObject.setString("VISITOR_NAME", visitor_name);
				parameterObject.setString("VISITOR_COMPANY", visitor_company);
				parameterObject.setString("VISITOR_IDCARD", visitor_idcard);
				parameterObject.setString("VISITOR_AUTO", visitor_auto);
				parameterObject.setString("VISITED_EMPID", visited_empid);
				parameterObject.setString("VISITED_NAME", visited_name);
				parameterObject.setString("VISIT_REASON", visit_reason);
				parameterObject.setString("IN_TIME", in_time);
				//parameterObject.setString("IN_CONFIRM", in_confirm);
				parameterObject.setString("OUT_TIME", out_time);
				parameterObject.setString("OUT_CONFIRM", out_confirm);
				parameterObject.setString("OUT_CARDID", out_cardid);
				parameterObject.setString("BRIEF", brief);
				parameterObject.setString("UPDATE_USER", adminId);
				parameterObject.setString("OPERATION_USER", adminId);
	
				gaServices.visitRecordUpdate(parameterObject);
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("general visit record add by paging Exception. ", e);
		}

		return "gaControlServlet?operation=view&content=visitlist&menu_code=" + parameterObject.getString("menu_code");
	}
}
