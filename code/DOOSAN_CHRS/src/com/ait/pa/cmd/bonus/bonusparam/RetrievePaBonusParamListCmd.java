
package com.ait.pa.cmd.bonus.bonusparam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrievePaBonusParamListCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		AdminBean admin=(AdminBean)request.getSession(true).getAttribute("admin");
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		List bonusParamList = new ArrayList() ;
	    
		try {
			// bind parameter
			
			String pamonth = request.getParameter("pamonth") ;
			// 取日期参数
			java.util.Calendar now = java.util.Calendar.getInstance();
			String year = String.valueOf(now.get(now.YEAR));
			String month = String.valueOf(now.get(now.MONTH)+1);
			month = (month.length() > 1 ? month : ("0" + month) ) ;
			if (pamonth == null || pamonth.length() == 0) {
				pamonth = year + month ;
			}else{
				year = pamonth.substring(0,4);
				month = pamonth.substring(4,6);
			}
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpnyId", admin.getCpnyId());
			parameterObject.set("pamonth", pamonth);
			bonusParamList = services.retrievePaBonus_paramList(parameterObject) ;
			
			request.setAttribute("bonusParamList", bonusParamList);
			request.setAttribute("statTypeCode", parameterObject.getString("statTypeCode")) ;
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("pamonth", pamonth);
			request.setAttribute("year",year);
			request.setAttribute("month",month);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus param item Exception. ", e);
		}

		return "/pa_bonus_param.jsp?menu_code=" + request.getParameter("menu_code") ; 
	}
}