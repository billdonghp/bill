package com.ait.gm.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

public class GmAndShiftSave extends ArAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		String gm_id=request.getParameter("gm_id");
		String SHIFT_NAMEs[]=request.getParameterValues("SHIFT_NO");
		SimpleMap parameterObject = null;
		SimpleMap map = null;
		
		
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			parameterObject.set("STARTTIME", parameterObject.getString("listHHS") +":"+ parameterObject.getString("listMMS"));
			parameterObject.set("ENDTIME", parameterObject.getString("listHHE") +":"+ parameterObject.getString("listMME"));
			
			parameterObject.set("AdminID", adminId);
			
			parameterObject.set("gm_id", gm_id);
			
			List<SimpleMap> parameterList = new ArrayList<SimpleMap>() ;
			
			for (int i = 0 ; i < SHIFT_NAMEs.length ; i ++)
			{
				map = new SimpleMap() ;
				map.putAll(parameterObject);
				map.setString("SHIFT_NO", SHIFT_NAMEs[i]) ;
				parameterList.add(map) ;
				
			}
			
			gm.ShiftAndGmtypeAddCheckNo(parameterList,true);
			
			
			// retrieve attendance record list
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("typeandshiftSAVE by No Exception. ",
					e);
		}
		request.setAttribute("url","gm/typeandshift.jsp?&menu_code=" + parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";

	}

}
