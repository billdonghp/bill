package com.ait.ga.cmd.inspection;

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

public class ConfrimInspectionCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		try{
			String assetNo[] = request.getParameterValues("assetNo"); 
			for(int i=0;i<assetNo.length;i++){
				
				parameterObject = new SimpleMap();
				parameterObject.setString("adminId", admin.getAdminID());
				parameterObject.setString("assetNo", assetNo[i]);
				services.confirmInspection(parameterObject);
			}
			
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("confirm inspection command Exception. ",e);
		}
		
		return "gaControlServlet?operation=RetrieveInspectionListCmd&menu_code=" + request.getParameter("menu_code");
	}

}
