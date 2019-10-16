
package com.ait.kpa.cmd.bonus.bonusformular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

public class UpdatePaBonusItemCalcuOrderCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		MessageSource messageSource ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
//			 update daily formular calcu_order 
			String calcu_Order = services.retrievePaBonusItemCalcuOrder(parameterObject).toString() ;
			parameterObject.setString("CALCU_ORDER", calcu_Order) ;
			String action = parameterObject.getString("flag") ;
			if (action.equals("up"))
			{
				services.updatePaBonusItemCalcuOrderUp(parameterObject);
			}
			else if (action.equals("down"))
			{
				services.updatePaBonusItemCalcuOrderDown(parameterObject);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update pa bonus item calcuOrder Exception. ", e);
		}
		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","/paControlServlet?operation=pa_bonus_formular&menu_code=" + parameterObject.getString("menu_code") );

		return "/inc/alertMessage.jsp";
	}
}