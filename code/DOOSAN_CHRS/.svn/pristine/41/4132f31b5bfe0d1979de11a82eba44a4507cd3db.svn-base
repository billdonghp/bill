package com.ait.ga.cmd.food;

import java.io.IOException;
import java.util.List;

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

public class RetrieveFoodApplyCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		GaServices services = new GaServices();
		SimpleMap parameterObject = null;
		try {
		
			//bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId",	admin.getCpnyId());
			List schemeList = services.getFoodSchemeList(parameterObject);
			
			request.setAttribute("schemeList", schemeList);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieve food scheme list cmd Exception. ",e);
		}
		
		this.putToolbarInfo(request);
		
		return "/ga_food_apply.jsp?menu_code="+ parameterObject.getString("menu_code");
	}
}
