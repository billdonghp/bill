
package com.ait.kpa.cmd.diff.diffparamdata;

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
import com.ait.kpa.PaParam;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;

public class RetrieveDiffParamDataListCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		PaParam paParam = new PaParam();
		SimpleMap parameterObject = new SimpleMap() ;
		List diffParamDataList = new ArrayList() ;
		Object diffParam = new Object() ;
	    
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			diffParamDataList = services.retrieveDiffParamDataList(parameterObject) ;
			diffParam	= services.retrieveDiff_param(parameterObject) ;
			String PARAM_NO = request.getParameter("param_no");
			String paramDefaultValue = paParam.getDiffDefaultValue(PARAM_NO);
			
			request.setAttribute("diffParamDataList", diffParamDataList);
			request.setAttribute("diffParam", diffParam);
			request.setAttribute("paramDefaultValue", paramDefaultValue);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus param data Exception. ", e);
		}

		return "/kpa_diff_param_data.jsp?menu_code=" + request.getParameter("menu_code") ; 
	}
}