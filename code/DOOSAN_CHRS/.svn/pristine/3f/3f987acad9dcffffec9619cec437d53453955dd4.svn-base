
package com.ait.pa.cmd.bonus.bonusparam;

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

public class UpdatePaBonusParamCmd extends PaAbstractCommand {

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
			parameterObject.setString("cpnyId", admin.getCpnyId()) ;
			
			// validate pa bonus param itmeid
			result = services.validatePaBonusParamItemId(parameterObject) ;
			if (result > 0) {
				//"项目ID或者中文名称重复!!!"
				request.setAttribute("alert", "项目ID或者中文名称重复!!!");
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}
			
			services.updatePaBonusParam(parameterObject) ;

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update pa bonus param Exception. ", e);
		}
		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","/paControlServlet?operation=paBonusParamList&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}