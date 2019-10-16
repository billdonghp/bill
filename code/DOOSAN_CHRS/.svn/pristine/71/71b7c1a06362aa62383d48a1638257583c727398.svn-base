package com.ait.ga.cmd.festivalpresent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ConfirmFestivalPresentCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;

		
		try {

			// bind affirm parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			if (parameterObject.getString("op").equals("batch")) {
				
				// bind batch affirm parameter
				String rowNo[] = request.getParameterValues("rowNo");
				SimpleMap map = new SimpleMap();
				if(request.getParameter("AFFIRM_FLAG") == "1" || request.getParameter("AFFIRM_FLAG").equals("1")){
					map.setInt("confirmFlag", 1);
					map.setString("personId", admin.getPersonId());
					for(int i=0; i<rowNo.length; i++){
						map.setString("applyNo", parameterObject.getString("applyNo_"+rowNo[i]));
						map.setString("AFFIRM_OPITION", parameterObject.getString("opinion_"+rowNo[i]));
						services.updateFestivalPresentApplyForConfirm(map);
					}
				}else {
					map.setInt("confirmFlag",2);
					map.setString("personId", admin.getPersonId());
					for(int i=0; i<rowNo.length; i++){
						map.setString("applyNo", parameterObject.getString("applyNo_"+rowNo[i]));
						map.setString("AFFIRM_OPITION", parameterObject.getString("opinion_"+rowNo[i]));
						services.updateFestivalPresentApplyForConfirm(map);
					}
				}
				
			} else {
				
				if(request.getParameter("AFFIRM_FLAG") == "1" || request.getParameter("AFFIRM_FLAG").equals("1")){
					
					parameterObject.setInt("confirmFlag", 1);
					parameterObject.setString("personId", admin.getPersonId());
					services.updateFestivalPresentApplyForConfirm(parameterObject);
				}
				else {
					parameterObject.setInt("confirmFlag", 2);
					parameterObject.setString("personId", admin.getPersonId());
					services.updateFestivalPresentApplyForConfirm(parameterObject);
				}
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
			throw new GlRuntimeException("Confirm festival present Exception. ", e);
		}
		
		request.setAttribute("alert", "确认成功!");
		request.setAttribute("url", "/gaControlServlet?operation=retrieveFestivalPresentConfirm&flag=ConfirmType001&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}
