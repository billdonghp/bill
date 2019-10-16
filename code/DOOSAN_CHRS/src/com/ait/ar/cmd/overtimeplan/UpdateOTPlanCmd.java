
package com.ait.ar.cmd.overtimeplan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class UpdateOTPlanCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MessageSource messageSource ;
		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		try {
//			bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			if(parameterObject.getString("hour")!=""||!parameterObject.getString("hour").equals("")&&
					parameterObject.getString("minute")!=""||!parameterObject.getString("minute").equals("")){
				
				parameterObject.set("applyLimidTime", (parameterObject.getString("hour").length() == 1 ? "0" + parameterObject.getString("hour") : parameterObject.getString("hour"))   
						+ ":" + (parameterObject.getString("minute").length() == 1 ? "0" + parameterObject.getString("minute") : parameterObject.getString("minute")));
			}else{
				parameterObject.set("applyLimidTime", "");
			}
			
			parameterObject.setString("updated_by", admin.getAdminID());
			
			parameterObject.set("start_month", parameterObject.getString("syear")+parameterObject.getString("start_date"));
			parameterObject.set("end_month", parameterObject.getString("eyear")+parameterObject.getString("end_date"));
			
			// update AR_OVERTIME_PLAN
			services.updateOTPlan(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update AR_OVERTIME_PLAN Exception. ", e);
		}
//		修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","arControlServlet?operation=ar_otplan&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}

