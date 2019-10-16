package com.ait.gm.cmd;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EDU.oswego.cs.dl.util.concurrent.CountDown;

import com.ait.gm.business.ExpressMangerServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class CarManagerCmd implements Command {
	private SimpleMap parameterObject;
	private ExpressMangerServices expressMangerServices;
	public CarManagerCmd(){
		expressMangerServices = new ExpressMangerServices();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		String returnStr = "";
		if(content.equals("View_carManager") && content!=null){		
			returnStr = this.View_carManager(request,admin);
			return returnStr;
		}else if(content.equals("Add_CarManager") && content!=null){
			returnStr = this.Add_carManager(request,admin);
			return returnStr;
		}else if(content.equals("Save_CarManager") && content!=null){
			returnStr = this.Save_CarManager(request, admin);
			return returnStr;
		}
		else{
			return "/error.jsp";
		}
	}

	public String View_carManager(HttpServletRequest request,AdminBean admin){
		try {
			UserConfiguration config = UserConfiguration
			.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.put("cpny_id", admin.getCpnyId());
			int carListInt = expressMangerServices.getCarManagerInt(parameterObject);
			List carList = expressMangerServices.getCarManager(parameterObject, currentPage, pageSize);
			request.setAttribute("carListInt", carListInt);
			request.setAttribute("carList", carList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/gmCarManagerView.jsp?menu_code=ga0808";
	}
	
	public String Add_carManager(HttpServletRequest request,AdminBean admin){
		try {
			parameterObject = ObjectBindUtil.getData(request);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/gmCarManagerAdd.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	public String Save_CarManager(HttpServletRequest request,AdminBean admin){
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.put("ADMINID", admin.getAdminID());
			parameterObject.put("cpny_id", admin.getCpnyId());
			expressMangerServices.saveCarManager(parameterObject);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "保存失败！");
		}
		this.View_carManager(request, admin);
		return "/gmCarManagerView.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
