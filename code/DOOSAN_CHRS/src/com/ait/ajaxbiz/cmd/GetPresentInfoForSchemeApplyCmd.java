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

public class GetPresentInfoForSchemeApplyCmd implements Command{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
    	GaServices gaServices = new GaServices();
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("cpnyId", admin.getCpnyId());
		parameterObject.setString("schemeNo", request.getParameter("condition"));
		
		List presentInfoList = gaServices.getSchemeDetaiList(parameterObject);
    	
		response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		float totalI = 0;
		str.append("<table>");
        if (presentInfoList.size() > 0) {           
            Iterator iter = presentInfoList.iterator();
            while(iter.hasNext()) {
            	SimpleMap simpleMap = (SimpleMap) iter.next();
            	str.append("<tr>") ;
            	
            	str.append("<td width='10%'>") ; 
            	str.append("礼品名称:<span style='color:#FF0000'>"+simpleMap.getString("PRESENT_NAME")+"</span>") ;
            	str.append("</td>") ;
         
            	str.append("<td width='10%'>") ; 
            	str.append("品牌:<span style='color:#FF0000'>"+simpleMap.getString("BRAND")+"</span>") ;
            	str.append("</td>") ;
          
            	str.append("<td width='10%'>") ; 
            	str.append("规格:<span style='color:#FF0000'>"+simpleMap.getString("SPECIFIC")+"</span>");
            	str.append("</td>") ;
            
            	str.append("<td width='10%'>") ; 
            	str.append("单价:<span style='color:#FF0000'>"+simpleMap.getString("UNIT_PRICE")+"</span>"+"元") ;
            	str.append("</td>") ;
            	
            	str.append("<td width='10%'>") ; 
            	str.append("数量:<span style='color:#FF0000'>"+simpleMap.getString("QUENTITY")+"</span>"+simpleMap.getString("UNIT")) ;
            	str.append("</td>") ;
            	
            	str.append("<td width='10%'>") ; 
            	str.append("价格:<span style='color:#FF0000'>"+simpleMap.getString("TOTAL_PRICE")+"</span>"+"元") ;
            	str.append("</td>") ;
        	
            	str.append("</tr>") ;
            	
            	totalI += Float.parseFloat(simpleMap.getString("TOTAL_PRICE"));

            }
        } 
        totalI = (float)(Math.round(totalI*100))/100;

        str.append("<div style='visibility: hidden;'>");
        str.append(totalI+" 元");
        str.append("</div>");
        str.append("</table>") ;
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}

}
