/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.cmd.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.pa.business.PaDsHrServices;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ibatis.sqlmap.client.SqlMapClient;

public class PaHistoryUpdateCmd extends ArAbstractCommand {
	
	private static final Logger logger = Logger.getLogger(PaHistoryUpdateCmd.class);
	
	
	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putToolbarInfo(request);
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		PaServices paServices = PaServices.getInstance() ;
		
		MessageSource messageSource ;
		SimpleMap parameterObject = new SimpleMap() ;
		List updateColumnList = new ArrayList() ;
		
		StringBuffer updateSql = new StringBuffer(1000) ; 
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId" , admin.getCpnyId()) ;
			parameterObject.set("paramKeys" , parameterObject.keySet().toArray()) ;
			
			updateColumnList = paServices.retrieveUpdatePaTableColumns(parameterObject) ;
			
			updateSql.append("UPDATE " ).append(parameterObject.getString("tableName")).append(" PA SET ") ;
			for (int i = 0 ; i < updateColumnList.size() ; ++i )
			{
				SimpleMap updateColumnMap = (SimpleMap)updateColumnList.get(i) ;
				
				updateSql.append(updateColumnMap.getString("COLUMN_NAME")).append(" = ")
					     .append(parameterObject.getString(updateColumnMap.getString("COLUMN_NAME"))).append(" , ") ;
			}
			updateSql.append("PA_MONTH = '").append(parameterObject.getString("paMonth"))
				     .append("' WHERE PERSON_ID = '").append(parameterObject.getString("personId")).append("'")
				     .append(" AND PA_MONTH = '").append(parameterObject.getString("paMonth")).append("' ") ;
			
			paServices.updatePaHistoryData(updateSql.toString()) ;
			
			if (parameterObject.getString("paCalcFlag") != null && parameterObject.getString("paCalcFlag").length() > 0){
				paServices.callPaHistoryProc(parameterObject) ;
			}
		
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("pa_history data update Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "数据修改成功!!!");
		request.setAttribute("url","/pa/" + parameterObject.getString("url") + "?&key=" + parameterObject.getString("empId") + "&statTypeCode=" + parameterObject.getString("statTypeCode") 
								 + "&paMonth=" + parameterObject.getString("paMonth") + "&supplyFlag=N"
		);

		return "/inc/alertMessage.jsp";
	}
}

