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

public class TypenandshiftDelete extends ArAbstractCommand {

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
			int gm_id = Integer.parseInt(request.getParameter("gm_id"));
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("gm_id", gm_id);
			
			
			
			gm.deleteTypenandshiftDelete(parameterObject);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Gm Delete ", e);
		}

		request.setAttribute("url","/gmControlServlet?operation=GM_AND_SHIFT&menu_code=" + parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
}
