
package com.ait.kpa.cmd.diff.diffitem;

import java.io.IOException;

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

public class InsertDiffItemCmd extends PaAbstractCommand {

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
			result = services.validateDiffItemId(parameterObject) ;
			if (result > 0) {
				//"与其它输入项目ID重复!!!"
				request.setAttribute("alert", "与其它计算项目ID重复或者与其它输入项目ID开头重复!!!");
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}
			
			services.insertDiffItem(parameterObject) ;

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert pa bonus item Exception. ", e);
		}
		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","/kpaControlServlet?operation=kpa_diff_item&menu_code=" + parameterObject.getString("menu_code") + "&currentPage=" + parameterObject.getString("currentPage") + "&search=" + parameterObject.getString("search") );

		return "/inc/alertMessage.jsp";
	}
}