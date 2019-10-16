	package com.ait.ajaxbiz.cmd;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.Iterator;
	import java.util.List;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import com.ait.ga.bean.VoitureBean;
import com.ait.ga.cmd.visit.BookingVoitureCmd;
import com.ait.gm.bean.GmBean;
import com.ait.gm.servlet.GmAddCommand;
	import com.ait.i18n.MessageSource;
	import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

	/**
	 * Copyright: AIT (c) Company: AIT
	 * 
	 * @author yangxiaohui (yangxiaohui@ait.net.cn)
	 * @Date 2008-2-21 
	 * @version 1.0
	 * 
	 */
	public class GmShiftSearch implements Command {

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
			GmAddCommand  gc = new GmAddCommand();			
			List gmType = gc.gmType();	
	    				
	    	response.setContentType("text/xml;charset=UTF-8");
	    	response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			StringBuffer str = new StringBuffer(5000) ;
			str.append("<table width='100%' border='0' cellpadding='0' cellspacing='1' class='dr_d'>") ;
			str.append("<tr onclick='layerClose();' style='position: relative; top: expression(this.offsetParent.scrollTop);'  title='"+message.getMessage("alert.mutual.click_close")+"'>") ;			
			str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
			
			str.append("班次编号") ;
			str.append("</td>") ;
			str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
            
			str.append("班次") ;
			str.append("</td>") ;
			
			
			// 大小
			str.append("<td style='display:none'>") ; 
	    	str.append(gmType.size()) ;
	    	str.append("</td>") ;
			str.append("</tr>") ;
			
	        if (gmType.size() > 0) {           
	            Iterator iter = gmType.iterator();
	          
	            while(iter.hasNext()) {
	            	GmBean gb=(GmBean)iter.next();
	            	str.append("<tr  onClick='updateValue(this);'>") ;
	            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
	            	str.append(gb.getGm_id_pk());
	            	str.append("</td>") ;
	            	
	            	
                     //班次
	            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
	            	str.append(gb.getGm_eatery_type());
	            	str.append("</td>") ;
	            	
	            	str.append("</tr>") ;	            }
	          
	        } else {	
	        	str.append("<tr><td colspan='5' align='center'>") ;
	        	// 没有你要查询的人员！
	        	str.append("没有您所查询的班次") ;
	        	str.append("</td></tr>") ;
	        }
	        
	        str.append("</table>") ;
	    	out.println(str.toString());
	        out.close();
	      
	        return "" ;
		}
	}


