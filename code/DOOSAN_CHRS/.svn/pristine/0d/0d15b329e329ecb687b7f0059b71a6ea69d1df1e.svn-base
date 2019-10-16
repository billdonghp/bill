
package com.ait.kpa.cmd.diff.diffformular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;

public class RetrieveDiffFormularCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		List diffFormularList = new ArrayList() ;
	    
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			diffFormularList = services.retrieveDiffFormularList(parameterObject) ;			
			
			request.setAttribute("diffFormularList", diffFormularList);
			request.setAttribute("pa_item_no", parameterObject.getString("pa_item_no"));

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa diff formular Exception. ", e);
		}

		return "/kpa_diff_formular_data.jsp" ; 
	}
}