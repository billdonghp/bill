package com.ait.gm.servlet;

import java.io.IOException;
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
import com.ait.sy.bean.AdminBean;

public class GmAndShift extends ArAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject = null;
		SimpleMap map = null;
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			// retrieve attendance record list
			List gmType =  gm.gmType(parameterObject);
			
			for(int i=0;i<gmType.size();i++){
				map = (SimpleMap)gmType.get(i) ;
				map.set("gmTypeIds", gm.shiftlook(map));
				map.set("timeGmType", gm.timeGmType1(map));
			}
			
			request.setAttribute("gmType", gmType);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("typeandshift by No Exception. ",
					e);
		}
		
		return "gm/typeandshift.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

}
