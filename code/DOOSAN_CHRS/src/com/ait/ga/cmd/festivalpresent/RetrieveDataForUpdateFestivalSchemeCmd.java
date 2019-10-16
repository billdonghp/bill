package com.ait.ga.cmd.festivalpresent;

import java.io.IOException;
import java.util.List;

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

public class RetrieveDataForUpdateFestivalSchemeCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		Object result = null;
		List recordDetaiList = null;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("personId",admin.getPersonId());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			List recordList = services.selectFestivalSchemeList(parameterObject);
			if(recordList.size()>0){
				result = recordList.get(0);
				recordDetaiList = services.selectFestivalSchemeDetaiList(parameterObject);
			}
			
			List presentNameList = services.getFestivalPresentName(parameterObject);
			request.setAttribute("result", result);
			request.setAttribute("recordDetaiList", recordDetaiList);
			request.setAttribute("presentNameList", presentNameList);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve data for update festival scheme Exception. ", e);
		}

		return "/ga_modify_festival_scheme.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
