
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.i18n.MessageSource;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class PaSearchBonusParamConfigureCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource message = new MessageSource(admin.getLocale(), "UTF-8"); 
		String language = admin.getLanguagePreference() ;
		PaServices services = PaServices.getInstance();
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		//parameterObject.setString("ADMINID", admin.getAdminID()) ;

		parameterObject.setString("CPNY_ID", admin.getCpnyId());
		parameterObject.setString("key", request.getParameter("key"));
		
		List<SimpleMap> paParamConfigureList = services.retrievePaBonusParamList(parameterObject);	    	
    	int listSize = paParamConfigureList.size() ;
		
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		str.append("<table width='100%' border='0' cellpadding='0' cellspacing='1' class='dr_d'>") ;
		str.append("<tr onclick='layerClose();' style='position: relative; top: expression(this.offsetParent.scrollTop);'  title='"+message.getMessage("alert.mutual.click_close")+"'>") ;
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		//工号
		str.append("项目ID") ;
		str.append("</td>") ;
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		//姓名
		str.append("项目名称") ;
		str.append("</td>") ;
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		//ID
		str.append("法人区分") ;
		str.append("</td>") ;
		//大小
		str.append("<td style='display:none'>") ; 
    	str.append(listSize) ;
    	str.append("</td>") ;
		str.append("</tr>") ;
		
		
		
        if (listSize > 0) {           
            Iterator iter = paParamConfigureList.iterator();
            while(iter.hasNext()) {
            	SimpleMap paramConfigure = (SimpleMap) iter.next();
            	str.append("<tr style='cursor:hand' onClick='updateValue(this);'>") ;
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append(paramConfigure.getString("PARAM_ID")) ;
            	str.append("</td>") ;
            	//姓名
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append(paramConfigure.getString("PARAM_NAME")) ;
            	str.append("</td>") ;
            	//ID
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append(paramConfigure.getString("CPNY_NAME")) ;
            	str.append("</td>") ;
            	str.append("</tr>") ;
            }
        } else {	
        	str.append("<tr><td colspan='3' align='center'>") ;
//        	没有你要查询的人员！
        	str.append("没有您要查找的数据!") ;
        	str.append("</td></tr>") ;
        }
        
        str.append("</table>") ;
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}
}
