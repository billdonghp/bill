
package com.ait.pa.cmd.bonus.bonusformular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;

public class RetrievePaBonusFormularCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		List bonusFormularList = new ArrayList() ;
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpnyId", admin.getCpnyId());
			bonusFormularList = services.retrievePaBonusFormularList(parameterObject) ;			
			
			request.setAttribute("bonusFormularList", bonusFormularList);
			request.setAttribute("pa_item_no", parameterObject.getString("pa_item_no"));

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus formular Exception. ", e);
		}

		return "/pa_bonus_formular_data.jsp" ; 
	}
}