package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ga.bean.AssetInfo;
import com.ait.ga.business.GaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author wangbin
 * @version 1.0
 */

public class GaSearchInspectionCmd implements Command{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		GaServices services = new GaServices();
		
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("cpnyId", admin.getCpnyId());
		parameterObject.setString("assetNo", request.getParameter("condition"));
		List tempList = services.searchAssetList(parameterObject);
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000);
		
		str.append("<table width='100%' border='0' cellpadding='0' cellspacing='1' class='dr_d'>") ;
		str.append("<tr onclick='layerClose();' style='position: relative; top: expression(this.offsetParent.scrollTop);'  title='点击标题关闭窗口'>") ;
		
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		str.append("序号") ;
		str.append("</td>") ;
		
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		str.append("资产号") ;
		str.append("</td>") ;
		
		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
		str.append("资产名") ;
		str.append("</td>") ;
//		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
//		//ID
//		str.append("ID") ;
//		str.append("</td>") ;
//		str.append("<td  height='25' class='info_title_01' nowrap='nowrap'>") ;
//		//部门
//		str.append(message.getMessage("display.mutual.department")) ;
//		str.append("</td>") ;
		//大小
		str.append("<td style='display:none'>") ; 
    	str.append(tempList.size());
    	str.append("</td>") ;
		str.append("</tr>") ;
		
        if (tempList.size() > 0) {           
            Iterator iter = tempList.iterator();
            while(iter.hasNext()) {
            	AssetInfo assetInfo = (AssetInfo)iter.next();
            	str.append("<tr  onClick='updateValueA(this);'>") ;
//            	序号
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append(assetInfo.getASSET_SEQ_NO()) ;
            	str.append("</td>") ;
            	//资产号
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append(assetInfo.getASSET_NO()) ;
            	str.append("</td>") ;
            	//资产名
            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
            	str.append(assetInfo.getASSET_NAME()) ;
            	str.append("</td>") ;
//            	//拼音
//            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
//            	str.append(basic.getPersonId()) ;
//            	str.append("</td>") ;
//            	//部门
//            	str.append("<td height='25' class='info_search_02' nowrap='nowrap'>") ; 
//            	str.append( language.equals("zh")? basic.getDepartment() : basic.getEnglishDept() ) ;
//            	str.append("</td>") ;
//            	
            	str.append("</tr>") ;
            }
        } else {	
        	str.append("<tr><td colspan='4' align='center'>") ;
//        	没有你要查询的人员！
        	str.append("没有你要查询的资产！") ;
        	str.append("</td></tr>") ;
        }
        str.append("</table>") ;
    	out.println(str.toString());
        out.close();

		return "";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
