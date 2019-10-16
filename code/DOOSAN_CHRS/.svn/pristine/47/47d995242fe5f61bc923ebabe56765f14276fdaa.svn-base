package com.ait.gm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class EateryPeopleView extends ArAbstractCommand {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO services = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;
		
		try {
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			// retrieve attendance record list

			List gmId = services.gmId(parameterObject);
				
			request.setAttribute("gmId", gmId);
			
				List allEatType = services.Gm_Type(parameterObject);
				request.setAttribute("allEatType", allEatType);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}

		return "gm/eateryPeople.jsp?menu_code=" + parameterObject.getString("menu_code");
		
	}
}