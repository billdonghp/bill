package com.ait.pu.cmd;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pu.services.RoomSetupServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;



public class RoomSetup implements Command {
	private RoomSetupServices roomSetupServices;

	private SimpleMap parameterObject;

	private String content = null;

	public RoomSetup() {
		roomSetupServices = new RoomSetupServices();
	}

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
			String returnPage = null;
			String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
			content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			if (content.equals("viewRoomSetup")) {
				returnPage = this.viewRoomSetup(request);
			}
			else if (content.equals("addRoomSetup")) {
				returnPage = this.addRoomSetup(request);
			}
			else if (content.equals("saveRoomSetup")) {
				returnPage = this.saveRoomSetup(request);
			}
			else if (content.equals("updateRoomSetup")) {
				returnPage = this.updateRoomSetup(request);
			}
			else if (content.equals("saveUpdateRoomSetup")) {
				returnPage = this.saveUpdateRoomSetup(request);
			}
			else if (content.equals("delRoomSetup")) {
				returnPage = this.delRoomSetup(request);
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

	
	private String viewRoomSetup(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap map = null; 
		Calendar today = Calendar.getInstance();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String systoday = new java.text.SimpleDateFormat("yyyy-MM-dd").format(today.getTime());
		try {
			
			today.setTime(sdf.parse("2008-06-25"));
			today.add(Calendar.MONTH, -1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", cpny_id);
			// retrieve attendance record list
			
			int RoomSetupCount = roomSetupServices.RoomSetupCount(parameterObject);
			request.setAttribute("recordCount", NumberUtil.parseInt(RoomSetupCount)) ;
			
			//得到参观者管理全部显示信息
			List RoomSetupList = roomSetupServices.RoomSetupList(parameterObject, currentPage, pageSize);
			
			request.setAttribute("RoomSetupList", RoomSetupList) ;
			
			
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("cpnyId", cpny_id);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterManagementView error ", e);
		}

			return "/pu_Room_Setup.jsp?menu_code=" + parameterObject.getString("menu_code");

	}
	
	private String addRoomSetup(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap map = null; 
					
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addRoomSetup error ", e);
		}

			request.setAttribute("cpnyId", cpnyId);
			return "/pu_room_setup_add.jsp?menu_code=" + parameterObject.getString("menu_code");

	}
	
	
	private String saveRoomSetup(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap map = null;

		SimpleMap map1 = null;

		String roomname = request.getParameter("roomname");
		String peoples = request.getParameter("peoples");

		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("roomname", roomname);
			parameterObject.set("peoples", peoples);
			parameterObject.set("cpny_id", cpny_id);
			// retrieve attendance record list

			int iSize = NumberUtil.parseInt(request.getParameter("i"));
			
			int kSize = NumberUtil.parseInt(request.getParameter("k"));

			String medicalDate = "";
			
			String confirmorid = "";

			if (iSize >= 0) {
				for (int i = 0; i <= iSize; i++) {
					if(request.getParameter("medicalDate" + i) != null && !request.getParameter("medicalDate" + i).equals(""))
					medicalDate = medicalDate + request.getParameter("medicalDate" + i)+"　";
				}
				
				for (int k = 0; k <= kSize; k++) {
					if(request.getParameter("personId" + k) != null && !request.getParameter("personId" + k).equals(""))
						confirmorid = confirmorid + request.getParameter("personId" + k)+",";
				}
				parameterObject.set("equip", medicalDate);
				parameterObject.set("confirmorid", confirmorid);
				roomSetupServices.saveRoomSetup(parameterObject);
			}
			
			
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			// retrieve attendance record list
			
			int RoomSetupCount = roomSetupServices.RoomSetupCount(parameterObject);
			request.setAttribute("recordCount", NumberUtil.parseInt(RoomSetupCount)) ;
			
			//得到参观者管理全部显示信息
			List RoomSetupList = roomSetupServices.RoomSetupList(parameterObject, currentPage, pageSize);
			
			request.setAttribute("RoomSetupList", RoomSetupList) ;
			
			
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("cpnyId", cpny_id);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterManagementView error ", e);
		}

			return this.viewRoomSetup(request);
	}
	
	private String updateRoomSetup(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		String id = request.getParameter("id");
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("id", id);

			int RoomSetupCount = roomSetupServices.RoomSetupCount(parameterObject);
			List RoomSetup = roomSetupServices.getRoomSetup(parameterObject);
				map = (SimpleMap) RoomSetup.get(0);
				String roomname = map.getString("ROOMNAME");
				String peoples = map.getString("PEOPLES");
				String equip = StringUtil.checkNull(map.getString("EQUIP"));
				String confirmorname = StringUtil.checkNull(map.getString("CONFIRMORNAME"));
				String confirmorid = StringUtil.checkNull(map.getString("CONFIRMORID"));
				List equiplist=null;
				if(!equip.equals("") && equip!=null){
				String[] equipArray = equip.split("　");
				equiplist = Arrays.asList(equipArray); 
				}
				List confirmorlist=null;
				if(!confirmorid.equals("") && confirmorid!=null){
				String[] confirmorArray = confirmorid.split(",");
				confirmorlist = Arrays.asList(confirmorArray); 
				}
				request.setAttribute("roomname", roomname);
				request.setAttribute("peoples", peoples);
				request.setAttribute("RoomSetupCount", RoomSetupCount);
				request.setAttribute("equiplist", equiplist);
//				request.setAttribute("confirmorname", confirmorname);
//				request.setAttribute("confirmorid", confirmorid);
				if(equiplist!=null){
				request.setAttribute("equiplistcount", equiplist.size());
				}else{
				request.setAttribute("equiplistcount",0);
				}
				request.setAttribute("confirmorlist", confirmorlist);
				if(confirmorlist!=null){
				request.setAttribute("confirmorcount", confirmorlist.size());
				}else{
				request.setAttribute("confirmorcount",0);
				}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateRoomSetup error ", e);
		}
		return "/pu_room_setup_update.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String saveUpdateRoomSetup(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();

		String roomname = request.getParameter("roomname");
		String peoples = request.getParameter("peoples");

		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("roomname", roomname);
			parameterObject.set("peoples", peoples);
			// retrieve attendance record list
			
			int iSize = NumberUtil.parseInt(request.getParameter("i"));
			
			int kSize = NumberUtil.parseInt(request.getParameter("k"));

			String medicalDate = "";
			
			String confirmorid = "";

			if (iSize >= 0) {
				for (int i = 0; i < iSize; i++) {
					if(request.getParameter("medicalDate" + i) != null && !request.getParameter("medicalDate" + i).equals(""))
					medicalDate = medicalDate + request.getParameter("medicalDate" + i)+"　";
				}
				for (int k = 0; k < kSize; k++) {
					if(request.getParameter("personId" + k) != null && !request.getParameter("personId" + k).equals(""))
					confirmorid = confirmorid + request.getParameter("personId" + k)+",";
				}
				parameterObject.set("equip", medicalDate);
				parameterObject.set("confirmorid", confirmorid);
				roomSetupServices.saveUpdateRoomSetup(parameterObject);
			}
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			// retrieve attendance record list
			
			int RoomSetupCount = roomSetupServices.RoomSetupCount(parameterObject);
			request.setAttribute("recordCount", NumberUtil.parseInt(RoomSetupCount)) ;
			
			//得到参观者管理全部显示信息
			List RoomSetupList = roomSetupServices.RoomSetupList(parameterObject, currentPage, pageSize);
			
			request.setAttribute("RoomSetupList", RoomSetupList) ;
			
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("saveUpdateRoomSetup error ", e);
		}

			return this.viewRoomSetup(request);
	}
	
	private String delRoomSetup(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		parameterObject = ObjectBindUtil.getData(request);
		String id = request.getParameter("id");

		try {
			// bind parameter
			
			parameterObject.set("supervisor", adminId);
			parameterObject.set("id", id);

			roomSetupServices.delRoomSetup(parameterObject);
			
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			// retrieve attendance record list
			
			int RoomSetupCount = roomSetupServices.RoomSetupCount(parameterObject);
			request.setAttribute("recordCount", NumberUtil.parseInt(RoomSetupCount)) ;
			
			//得到参观者管理全部显示信息
			List RoomSetupList = roomSetupServices.RoomSetupList(parameterObject, currentPage, pageSize);
			
			request.setAttribute("RoomSetupList", RoomSetupList);
			
			
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterManagementView error ", e);
		}
		this.viewRoomSetup(request);
			return "/pu_Room_Setup.jsp?menu_code=" + parameterObject.getString("menu_code");

		}
}