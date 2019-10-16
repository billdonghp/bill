
package com.ait.kpa.cmd.bonus.bonusparam;

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

public class InsertPaBonusParamCmd extends PaAbstractCommand {

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
			result = services.validatePaBonusParamItemId(parameterObject) ;
			if (result > 0) {
				//"项目ID或者中文名称重复!!!"
				request.setAttribute("alert", "项目ID或者中文名称重复!!!");
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}
			
			services.insertPaBonusParam(parameterObject) ;

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert pa bonus param Exception. ", e);
		}
		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","/kpaControlServlet?operation=pa_bonus_param&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}