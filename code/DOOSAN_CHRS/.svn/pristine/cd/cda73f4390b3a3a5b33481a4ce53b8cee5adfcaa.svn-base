package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ga.business.GaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class GetPresetnInfoForInsertFestivalSchemeCmd implements Command{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
    	GaServices gaServices = new GaServices();
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("cpnyId", admin.getCpnyId());
		parameterObject.setString("seqNo", request.getParameter("condition"));
		
		List presentInfoList = gaServices.selectFestivalPresentList(parameterObject); 	
    	
		response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		str.append("<table>");
        if (presentInfoList.size() > 0) {           
            Iterator iter = presentInfoList.iterator();
            while(iter.hasNext()) {
            	SimpleMap simpleMap = (SimpleMap) iter.next();
            	str.append("<tr>") ;
         
            	str.append("<td>") ; 
            	str.append(simpleMap.getString("BRAND")) ;
            	str.append("</td>") ;
          
            	str.append("<td>") ; 
            	str.append(simpleMap.getString("SPECIFIC"));
            	str.append("</td>") ;
            
            	str.append("<td>") ; 
            	str.append( simpleMap.getString("UNIT") ) ;
            	str.append("</td>") ;
            
            	str.append("<td>") ; 
            	str.append( simpleMap.getString("UNIT_PRICE") ) ;
            	str.append("</td>") ;
        	
            	str.append("</tr>") ;

            }
        } 
        
        str.append("</table>") ;
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}

}
