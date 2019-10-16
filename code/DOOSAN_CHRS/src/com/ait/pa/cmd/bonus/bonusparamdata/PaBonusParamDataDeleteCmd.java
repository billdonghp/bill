
package com.ait.pa.cmd.bonus.bonusparamdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

public class PaBonusParamDataDeleteCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance() ;
		SimpleMap parameterObject ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin") ;
		MessageSource messageSource ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			services.deletePaBonusParamData(parameterObject) ;
			

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("delete pa bonus param Exception. ", e);
		}
		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","/paControlServlet?operation=pa_bonus_param_data&configureNo=" + request.getParameter("configureNo")
				+"&pamonth=" + request.getParameter("pamonth") + "&statTypeCode=" + request.getParameter("statTypeCode")
		);

		return "/inc/alertMessage.jsp";
	}
}