
package com.ait.kpa.cmd.diff.diffparam;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.kpa.KpaDistinct;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrieveDiffParamForUpdateCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		Object diffParam = null ;
	    
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			diffParam = services.retrieveDiff_param(parameterObject) ;
			
			KpaDistinct padistinct = new KpaDistinct();
			Vector v = padistinct.getPaDistinctList();
			
			request.setAttribute("padistinctv",v);
			request.setAttribute("diffParam", diffParam);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus param data for update Exception. ", e);
		}

		return "/kpa_diff_param_update.jsp?menu_code=" + request.getParameter("menu_code") ; 
	}
}