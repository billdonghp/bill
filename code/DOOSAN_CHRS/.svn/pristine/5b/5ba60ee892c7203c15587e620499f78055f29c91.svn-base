package com.ait.ga.cmd.festivalpresent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class UpdateSchemeActivityCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		SimpleMap parameterObject = null;
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		try {
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			services.updateSchemeActivity(parameterObject);
			
		
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			new GlRuntimeException(
					"update festival scheme activity data Exception. ",e);
		}
		this.putToolbarInfo(request);
		return "gaControlServlet?operation=retrieveFestivalScheme&menu_code="+parameterObject.getString("menu_code");

	}

}
