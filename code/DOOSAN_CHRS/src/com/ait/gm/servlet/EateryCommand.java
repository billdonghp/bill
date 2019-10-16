package com.ait.gm.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.business.EateryServices;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;
import com.ait.util.StringUtil;

public class EateryCommand implements Command {
	private EateryServices eateryServices;

	private SimpleMap parameterObject;

	private String content = null;
    private GMDAO gm=null;
	public EateryCommand() {
		eateryServices = new EateryServices();
		 gm = new GMDAO();
	}

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			if (content.equals("planconfirm")) {
				returnPage = planConfirm(request);
			}
			else if (content.equals("planstaistic")) {
				returnPage = planstaistic(request);
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

	
	private String planConfirm(HttpServletRequest request) {

		parameterObject = ObjectBindUtil.getData(request);
		String adminNO = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminNo();
		this.putToolbarInfo(request);
		String flag=StringUtil.checkNull(request.getParameter("flag"),"search");
		/*boolean isgroupCompetence = false;
		int eat_flag = 0;
		;
		parameterObject.set("AdminId", adminId);
		String groupCompetence =gm.groupCompetence(parameterObject);
		for(int i=0;i<groupCompetence.split(",").length;i++){
			if(groupCompetence.split(",")[i].equals("12") || groupCompetence.split(",")[i].equals("100")){//对超级用户和食堂管理组实行另一种方案
				isgroupCompetence=true;
				break;
			}
			
		}*/

		try {
			/*// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			String current_date = request.getParameter("selectdate");
			if(current_date == null || current_date.length() == 0)
				current_date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime());
			parameterObject.setString("current_date", current_date);

			List confirmEateryList = eateryServices.confirmEateryList(parameterObject);
			
			SimpleMap confirmEateryMap = new SimpleMap() ;
			for (int i = 0; i < confirmEateryList.size(); i++) {
				confirmEateryMap = (SimpleMap)confirmEateryList.get(i) ;
				parameterObject.setString("EAT_APPLY_EATERY_NO", confirmEateryMap.getString("EAT_APPLY_EATERY")) ;
				confirmEateryMap.set("confirmDeptList", eateryServices.confirmDeptList(parameterObject)) ;
				confirmEateryMap.set("eateryPeopleNum", eateryServices.eateryPeopleNum(parameterObject)) ;
				confirmEateryMap.set("confirmEateryCount", eateryServices.confirmEateryCount(parameterObject)) ;
			}
			
			request.setAttribute("confirmEateryList", confirmEateryList);
			request.setAttribute("current_date", current_date);*/			
			String current_date = request.getParameter("selectdate");
			if(current_date == null || current_date.length() == 0)
				current_date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime());
			parameterObject.setString("current_date", current_date);			
			parameterObject.set("adminNO",adminNO);
			SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");  
			Calendar beforeday = Calendar.getInstance();
			beforeday.setTime(sdf.parse(current_date));
			beforeday.add(beforeday.DATE,-1);	
			parameterObject.setString("before_date", sdf.format(beforeday.getTime()));
			SimpleMap sm = null;
			List allEateryaType = eateryServices.getEatType(parameterObject);
			request.setAttribute("allEateryaType", allEateryaType);
			String sql = "";
			String sql1 = "";
			for(int i=0 ; i<allEateryaType.size() ; i++){
				sm = (SimpleMap) allEateryaType.get(i);
				String gm_type = sm.getString("GM_TYPE");
				String gm_id = sm.getString("GM_ID");
				sql += ",A."+gm_type;
				sql1 += ",NVL(SUM(CASE WHEN G.EAT_CONFIRM_EATERY ="+gm_id+" THEN G.EAT_CONFIRM_PEOPLE END),'0') AS "+gm_type+"";
			}
			parameterObject.setString("sql", sql) ;
			parameterObject.setString("sql1", sql1) ;
			request.setAttribute("current_date", current_date);
			parameterObject.set("adminNO1",adminNO);
			List result = eateryServices.getRseult(parameterObject);
			request.setAttribute("result", result);
			
			List resultTotal = eateryServices.getResultTotal(parameterObject);
			request.setAttribute("resultTotal", resultTotal);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("gm.eatery error ", e);
		}
		if(flag.equals("search")){
			return "/gm_eatery_plan_confirm.jsp?menu_code=" + parameterObject.getString("menu_code");
		}else{
		    return "/reports/gm_report/gm_eatery_plan_confirmExcel.jsp";
		}
		
	}
	
	private String planstaistic(HttpServletRequest request) {
		this.putToolbarInfo(request);

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			int deptNum = Integer.parseInt(request.getParameter("deptNum"));
			String EAT_CONFIRM_DATE = request.getParameter("selectdate");
			if(EAT_CONFIRM_DATE == null || EAT_CONFIRM_DATE.length() == 0)
				EAT_CONFIRM_DATE = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime());
			parameterObject.setString("EAT_CONFIRM_DATE", EAT_CONFIRM_DATE);

			//int eateryNums = Integer.parseInt(request.getParameter("eateryNum"));
			int eateryNums = Integer.parseInt(request.getParameter("allEatType"));
			for(int i=0;i<eateryNums;i++){
				parameterObject.set("eateryno", request.getParameter("eateryno"+i));
				for(int j=0;j<deptNum;j++){
					parameterObject.set("deptid", request.getParameter("deptid"+i+j));
					parameterObject.set("PeopleNum", request.getParameter("PeopleNum"+i+j));
					parameterObject.set("EAT_DATE", StringUtil.checkNull(request.getParameter("EAT_DATE"+i+j),""));
					eateryServices.insertStatistic(parameterObject);
				}
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("gm.eatery error ", e);
		}
		return "gmControlServlet?operation=eatStatistic&menu_code=" + parameterObject.getString("menu_code");
	}
}
