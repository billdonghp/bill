
package com.ait.pa.cmd.bonus.bonusparamdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.PaDistinct;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrievePaBonusParamDataListForInsertCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		Object bonusParam = new Object() ;
	    List bonusParamDataList = new ArrayList() ;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
		    parameterObject.set("cpnyId", admin.getCpnyId());
		    
			bonusParamDataList= services.retrievePaBonusParamDataForInsert(parameterObject) ;
			
			bonusParam = services.retrievePaBonus_param(parameterObject) ;
			
			
			request.setAttribute("bonusParamDataList",bonusParamDataList);
			request.setAttribute("bonusParam", bonusParam);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus param data for insert Exception. ", e);
		}

		return "/pa_bonus_param_data_add.jsp?menu_code=" + request.getParameter("menu_code") ; 
	}
}