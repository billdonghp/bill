
package com.ait.pa.cmd.bonus.bonusitem;

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

public class UpdatePaBonusItemCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		MessageSource messageSource ;
		int result = 0 ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			// validate pa bonus param itmeid
			result = services.validatePaBonusItemId(parameterObject) ;
			if (result > 0) {
				//"与其它输入项目ID重复!!!"
				request.setAttribute("alert", "与其它计算项目ID重复或者与其它输入项目ID开头重复!!!");
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}
			
			services.updatePaBonusItem(parameterObject) ;

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update pa bonus item Exception. ", e);
		}
		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","/paControlServlet?operation=pa_bonus_item&menu_code=" + parameterObject.getString("menu_code") + "&currentPage=" + parameterObject.getString("currentPage") + "&search=" + parameterObject.getString("search") );

		return "/inc/alertMessage.jsp";
	}
}