package com.ait.gm.servlet;

import java.io.IOException;

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
public class AddEateryException extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageSource messageSource;
		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		String gmType = request.getParameter("gmtype");
		String Remarks = request.getParameter("Remarks");
		String time = request.getParameter("listDD");
		
		try {
			if(Remarks.equals("")){
				Remarks="无";
			}
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("TIME", time);
			parameterObject.set("gmType", gmType);
			parameterObject.set("Remarks", Remarks);
			parameterObject.set("AdminID", admin.getAdminID());
			//validate empid and plan_month
			
					
			// insert GM
			gm.insertEateryException(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insertEateryException Gm Exception. ", e);
		}
		
		//添加成功
		
//		修改成功
		request.setAttribute("url","/gmControlServlet?operation=eateryException&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}