package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ga.business.GaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class GaAssetTestCmd implements Command{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		GaServices services = new GaServices();
		SimpleMap parameterObject = new SimpleMap();
		
		response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
    	parameterObject.setString("assetNo",request.getParameter("condition"));
    	parameterObject.setString("cpnyId", admin.getCpnyId());
		PrintWriter out = response.getWriter();
		String str = "n" ;
		
		try {
			
			int result = services.selectAssetTest(parameterObject);
			
			if(result != 0)
			{
				str = "" ;
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace() ;
		}
    				
    	out.println(str);
        out.close();
        
        return "" ;
	}
}
