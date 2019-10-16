/*
 * @(#)ViewBaseInfoCmd.java 1.0 2015-04-14 下午16:36:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.dao.present.PresentDAO;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class CheckAccountCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		try {
			String outStr = "";
			String flag = StringUtil.checkNull(request.getParameter("type"));
			SimpleMap conMap=new SimpleMap();
			PresentDAO checkDAO = new PresentDAO();
			
			
			// 银行名称，账号联动
			if (flag.equals("CHECKBANK_TYPE")) {
				conMap.put("COLUMN_ID", StringUtil.checkNull(request.getParameter("id")));
				List<SimpleMap> lCode = checkDAO.retrieveCheckAccountColumn(conMap);
				for(SimpleMap data:lCode){
					outStr += data.getString("ACCOUNT")+ ";";
				}
			} 
			// 职等,职称联动
//			else if (flag.equals("postPosition")) {
//				conMap.put("POST_CODE", StringUtil.checkNull(request.getParameter("id")));
//				List<SimpleMap> lPosition = commonDAO.retrievePositionListByPost(conMap);
//				for(SimpleMap data:lPosition){
//					outStr += data.getString("CODE_ID") + "," + data.getString("CODE_NAME") + ";";
//				}
//				//评价期间流程
//			} 
			response.getWriter().print(StringUtils.removeEnd(outStr, ";"));
			request.setAttribute("outStr", outStr);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve gaCheckAccount by PARAM_NO Exception. ", e);
		}
		return "";
	}
	
}
