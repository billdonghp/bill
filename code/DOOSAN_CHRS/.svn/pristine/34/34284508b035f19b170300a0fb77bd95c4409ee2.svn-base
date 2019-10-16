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
import com.ait.util.DateUtil;

public class GmVegetableUpdateCommand extends ArAbstractCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject = null;

		try {

			// bind parameter
			String VEGET_ID = request.getParameter("VEGET_ID");
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("VEGET_ID", VEGET_ID);

			// retrieve OT Plan by No
			SimpleMap result = new SimpleMap();
			result = (SimpleMap) gm.gmGmVegetableById(parameterObject);

	
//			String VEGET_ID = result.getString("VEGET_ID");
//			String VEGET_NAME = result.getString("VEGET_NAME");
//			String MATERIAL = result.getString("MATERIAL");
//			String METHOD = result.getString("METHOD");
//			String LOGIN_DATE = result.getString("LOGIN_DATE");
//			String DEVELOP = result.getString("DEVELOP");
//
//			String gmfd[] = gmfromdate.split(":");
//
//			request.setAttribute("xs", gmfd[0]);
//			request.setAttribute("fz", gmfd[1]);
//
//			String gmtd[] = gmtodate.split(":");
//			request.setAttribute("xse", gmtd[0]);
//			request.setAttribute("fze", gmtd[1]);
//
//			List listMM = DateUtil.getTimePerMMList();
//
//			List listHH = DateUtil.getTimePerHourList();
//
//			request.setAttribute("listMM", listMM);
//			request.setAttribute("listHH", listHH);
//			
//			request.setAttribute("gmtype", gmtype);
//			request.setAttribute("gm_id", result.getString("GM_ID"));
			request.setAttribute("result", result);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve OT Plan by No Exception. ",
					e);
		}

		return "/gm_vegetable_modify.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
}
