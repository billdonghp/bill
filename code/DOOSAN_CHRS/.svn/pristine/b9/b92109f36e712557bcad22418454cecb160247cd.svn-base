
package com.ait.pa.cmd.bonus.bonusresult;

import java.io.IOException;

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

public class PaBonusBalanceCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance() ;
		SimpleMap parameterObject ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin") ;
		MessageSource messageSource ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("message", "") ;
			
			services.callPaBonusConfirm(parameterObject) ;
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("call PA_BONUS_CONFIRM Exception. ", e);
		}
		//添加成功
		//messageSource = new MessageSource(admin.getLocale(), "UTF-8"); messageSource.getMessage("alert.mutual.save_successfully")
		request.setAttribute("alert", parameterObject.getString("message")) ;
		request.setAttribute("url","/pa/pa_bonus_result.jsp?menu_code=" + parameterObject.getString("menu_code")) ;

		return "/inc/alertMessage.jsp";
	}
}