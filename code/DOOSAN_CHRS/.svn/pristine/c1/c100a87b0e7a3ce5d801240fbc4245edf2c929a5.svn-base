package com.ait.ga.cmd.food;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ApplyFoodCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;

		try {

			// bind apply parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			
			
			// bind apply detail parameter
			int size = parameterObject.getInt("maxRowNum");
			SimpleMap map = new SimpleMap();
			map.setString("applyDate", request.getParameter("applyDate"));
			map.setString("adminId", admin.getAdminID());
			map.setString("cpnyId", admin.getCpnyId());
			for (int i=0; i<=size; i++) {
				
				map.setString("deptId", request.getParameter("deptId"+i));
				map.setString("schemeName", request.getParameter("schemeName"+i));
				map.setString("quentity", request.getParameter("quentity"+i));
				map.setString("remark", request.getParameter("remark"+i));
				services.insertDataForApplyFood(map);
			}
			
			MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			String msg = messageSource.getMessage("alert.mutual.apply_successfully");
			
			request.setAttribute("alert", msg);
			request.setAttribute("url", "/gaControlServlet?operation=retrieveFoodApply&menu_code=" + parameterObject.getString("menu_code"));
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Apply food Exception. ", e);
		}

		return "/inc/alertMessage.jsp";
	}
}
