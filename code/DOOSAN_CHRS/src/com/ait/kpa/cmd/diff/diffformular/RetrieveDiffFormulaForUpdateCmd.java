
package com.ait.kpa.cmd.diff.diffformular;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrieveDiffFormulaForUpdateCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		Object diffFormular = null ;
	    
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			diffFormular = services.retrieveDiffFormular(parameterObject) ;
			
			request.setAttribute("diffFormular", diffFormular);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa diff formular data for update Exception. ", e);
		}

		return "/kpa_diff_formular_data_update.jsp" ; 
	}
}