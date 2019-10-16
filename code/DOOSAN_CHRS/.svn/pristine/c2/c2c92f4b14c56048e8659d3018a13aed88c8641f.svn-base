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
	import com.ait.hrm.bean.BasicInfo;
	import com.ait.hrm.business.HrmServices;
	import com.ait.i18n.MessageSource;
	import com.ait.sqlmap.util.SimpleMap;
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
	public class GaVoitureSearch implements Command {

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
			BookingVoitureCmd  bvc = new BookingVoitureCmd();			
			String date = request.getParameter("date");
			String date1 = request.getParameter("date1");
			List voitureList = bvc.getVoitureList(request.getParameter("condition"),date,date1 ,admin.getCpnyId());	
	    				
	    	response.setContentType("text/xml;charset=UTF-8");
	    	response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			StringBuffer str = new StringBuffer(5000) ;
			str.append("<table width='100%' border='0' cellpadding='0' cellspacing='1' class='dr_d'>") ;
			str.append("<tr onclick='layerClose();' style='position: relative; top: expression(this.offsetParent.scrollTop);'  title='"+message.getMessage("alert.mutual.click_close")+"'>") ;			
			str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
            //车辆编号
			str.append("车辆编号") ;
			str.append("</td>") ;
			str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
			// 名称
			str.append("名称") ;
			str.append("</td>") ;
			str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
			// 型号
			str.append("型号") ;
			str.append("</td>") ;
			str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
			// 牌号
			str.append("牌号") ;
			str.append("</td>") ;
			str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
			// 座位数
			str.append("座位数") ;
			str.append("</td>") ;
			// 大小
			str.append("<td style='display:none'>") ; 
	    	str.append(voitureList.size()) ;
	    	str.append("</td>") ;
			str.append("</tr>") ;
			
	        if (voitureList.size() > 0) {           
	            Iterator iter = voitureList.iterator();
	            String temp =  request.getParameter("temp");
	            while(iter.hasNext()) {
	            	VoitureBean vb=(VoitureBean)iter.next();
	            	str.append("<tr  onClick='updateValue(this,"+temp+");'>") ;
                     //车辆编号
	            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
	            	str.append(vb.getVOITURE_ID()) ;
	            	str.append("</td>") ;
	            	// 工号
	            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
	            	str.append(vb.getVoiture_Brand()) ;
	            	str.append("</td>") ;
	            	// 姓名
	            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
	            	str.append( vb.getVoiture_Model()!=null?vb.getVoiture_Model():"" ) ;
	            	str.append("</td>") ;
	            	// 拼音
	            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
	            	str.append(vb.getVoiture_Number()!=null?vb.getVoiture_Number():"") ;
	            	str.append("</td>") ;
	            	// 部门
	            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
	            	str.append(vb.getSeats()!=null?vb.getSeats():"" ) ;
	            	str.append("</td>") ;
	            	
	            	str.append("</tr>") ;	            }
	          
	        } else {	
	        	str.append("<tr><td colspan='5' align='center'>") ;
	        	// 没有你要查询的人员！
	        	str.append("没有您所查询的车辆，或者车辆都在使用！") ;
	        	str.append("</td></tr>") ;
	        }
	        
	        str.append("</table>") ;
	    	out.println(str.toString());
	        out.close();
	      
	        return "" ;
		}
	}


