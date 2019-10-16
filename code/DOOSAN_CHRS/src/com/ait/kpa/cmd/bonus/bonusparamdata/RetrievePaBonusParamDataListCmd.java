
package com.ait.kpa.cmd.bonus.bonusparamdata;

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

public class RetrievePaBonusParamDataListCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		List bonusParamDataList = new ArrayList() ;
		Object bonusParam = new Object() ;
	    
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			bonusParamDataList = services.retrievePaBonusParamDataList(parameterObject) ;
			bonusParam	= services.retrievePaBonus_param(parameterObject) ;
			
			request.setAttribute("bonusParamDataList", bonusParamDataList);
			request.setAttribute("bonusParam", bonusParam);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus param data Exception. ", e);
		}

		return "/kpa_bonus_param_data.jsp?menu_code=" + request.getParameter("menu_code") ; 
	}
}