package com.ait.gm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

public class GmMenuSaveCommand extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MessageSource messageSource ;
		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			parameterObject.set("AdminID", admin.getAdminID());
			parameterObject.set("cpnyId", admin.getCpnyId());
			//validate empid and plan_month
			//int result = NumberUtil.parseInt(gm.validateGmMenu(parameterObject));

//			if(result!=0){
//				messageSource = new MessageSource("gm",admin.getLocale(), "UTF-8");
//				request.setAttribute("alert", "该天该餐次已添加!返回重新添加");
//				request.setAttribute("reload","history.back();");
//				return "/inc/alertMessage.jsp";
//			}
					
			// insert GmMenum
			gm.insertGmMenu(parameterObject);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert Gm Exception. ", e);
//			已存在
			
			
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","/gmControlServlet?operation=gm_menu&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}