/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.cmd.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.pa.business.PaDsHrServices;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ibatis.sqlmap.client.SqlMapClient;

public class PaOpenProgressCmd extends ArAbstractCommand {
	
	private static final Logger logger = Logger.getLogger(PaOpenProgressCmd.class);
	
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//"http://dghr.corp.doosan.com/dic_login.jsp" ; //"http://172.16.225.240:9080/dic_login.jsp" 
	
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
		List paDataList = new ArrayList() ;
		String url = "" ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			//测试用，使用完，要删除
			//parameterObject.set("TABLE_NAME", "T_PA_RESULT") ; //PA_HISTORY
			
			
			// 开始循环,交换数据
			parameterObject.set("CPNYID", admin.getCpnyId());
			insertDsHrDatas(parameterObject,admin) ;
			
			if (parameterObject.getString("TABLE_NAME").equals("PA_BONUS_HISTORY"))
			{
				// 奖金数据
				url = "/pa/pa_bonus_result.jsp?menu_code=" ;
			}
			else
			{					
				// 工资数据
				url = "/pa/pa0205.jsp?menu_code=" ;
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("pa openProgress Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "数据传送成功!!!");
		request.setAttribute("url",url + parameterObject.getString("menu_code") + "&year" + parameterObject.getString("year") + "&month" + parameterObject.getString("month") + "&payOpenType" + parameterObject.getString("payOpenType") );

		return "/inc/alertMessage.jsp";
	}
	
	/**
	 * insert data to DSHR
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	private void insertDsHrDatas(SimpleMap parameterObject,AdminBean admin) throws GlRuntimeException
	{
		SqlMapClient sqlmapClient = null ;
		//Korea
		PaDsHrServices paDsHrServices = PaDsHrServices.getInstance() ;
		//China
		PaServices paServices = PaServices.getInstance() ;
		

		List dsHrColumnsList = new ArrayList() ;
		List alertDsHrColumnsList = new ArrayList() ;
		//List alertDsHrCommentsList = new ArrayList() ;		
		List insertDataColumnsList = new ArrayList() ;
		List insertDataList = new ArrayList() ;
		
		SimpleMap tempMap = new SimpleMap() ;
		SimpleMap columnMap = new SimpleMap() ;
		SimpleMap dataMap = new SimpleMap() ;
		SimpleMap insertDsHrDataMap = new SimpleMap() ;
		
		StringBuffer column = null ;
		StringBuffer data = null ;
		String dsHrPaHistoryColumns = "''" ;
		
		try {
			// 得到 韩国 工资历史表是否存在
			int checkDsHrTable = paDsHrServices.checkDsHrTable(parameterObject) ;
			
			// 表不存在，创建表
			if (checkDsHrTable == 0)
			{
				paDsHrServices.createDsHrTable(parameterObject) ;
			}
			
			// 得到 韩国 工资历史表的列信息
			dsHrColumnsList= paDsHrServices.retrieveDsHrColumns(parameterObject) ;
			for (int i = 0 ; i < dsHrColumnsList.size() ; ++i)
			{
				tempMap = (SimpleMap)dsHrColumnsList.get(i) ;
				dsHrPaHistoryColumns = dsHrPaHistoryColumns + ",'" + tempMap.get("COLUMN_NAME") + "'" ;
			}
			parameterObject.set("dsHrPaHistoryColumns", dsHrPaHistoryColumns) ;
			
			// 得到 韩国 没有的列信息
			alertDsHrColumnsList = paServices.retrieveAlterDsHrColumns(parameterObject) ;
			//不知道，为什么，取不出来
			//alertDsHrCommentsList = paServices.retrieveAlterDsHrComments(parameterObject) ;
			// 跟新 韩国 表的列信息
			paDsHrServices.alterDsHrColumns(alertDsHrColumnsList) ;
			//paDsHrServices.alterDsHrColumns(alertDsHrCommentsList) ;
			
			
			// 得到所有的列信息
			insertDataColumnsList = paServices.retrieveInsertDataColumns(parameterObject) ;
			// 得到所有的数据
			//insertDataList = paServices.retrieveInsertDsHrDatas(parameterObject) ;
			
			sqlmapClient = SQLMapConfigManager.getInstance().getSqlMapClient("dshr");
			
			sqlmapClient.startTransaction() ;
				
			insertDsHrDataMap.set("TABLE_NAME", parameterObject.getString("TABLE_NAME")) ;
			insertDsHrDataMap.set("PA_MONTH", parameterObject.getString("PA_MONTH")) ;
			insertDsHrDataMap.set("CPNYID", admin.getCpnyId());
	
			// 删除以前插入的数据
			sqlmapClient.delete("pa.dshr.deleteDsHrDatas",insertDsHrDataMap) ;	
			
			//int insertDataSize = insertDataList.size() ;
			int insertDataColumsSize = insertDataColumnsList.size() ;
			int num = 0 ;
			column = new StringBuffer(5000) ;
			/*for (int j = 0 ; j < insertDataSize ; ++ j)
			{
				
				data = new StringBuffer(5000) ;
				dataMap = (SimpleMap)insertDataList.get(j) ;*/
				for (int i = 0 ; i < insertDataColumsSize ; ++ i)
				{
					columnMap = (SimpleMap)insertDataColumnsList.get(i) ;

					column.append(columnMap.getString("COLUMN_NAME")).append(", ") ;
					
					/*if (dataMap.get(columnMap.getString("COLUMN_NAME")) == null || columnMap.getString("DATA_TYPE").equals("NUMBER"))
					{
						data.append(dataMap.getString(columnMap.getString("COLUMN_NAME"))).append(", ") ;
					}
					else if (columnMap.getString("DATA_TYPE").equals("DATE"))
					{
						data.append(" TO_DATE(SUBSTR('").append(dataMap.getString(columnMap.getString("COLUMN_NAME"))).append("',0,10),'YYYY-MM-DD'), ") ;
					}
					else
					{
						data.append(" '").append(dataMap.getString(columnMap.getString("COLUMN_NAME").trim())).append("', ") ;
					}*/
				}
				
				insertDsHrDataMap.set("insertColumn", column.substring(0, column.lastIndexOf(","))) ;
				insertDsHrDataMap.set("selectColumn", column.substring(0, column.lastIndexOf(",")).replace("PERSON_ID", " GET_KO_PERSONID(PERSON_ID) AS PERSON_ID")) ;
				//insertDsHrDataMap.set("data", data.substring(0, data.lastIndexOf(","))) ;				
				
				sqlmapClient.insert("pa.dshr.insertDsHrDatas",insertDsHrDataMap) ;				
			
			
			sqlmapClient.commitTransaction() ;
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert data to DSHR Exception. ", e);
		} finally {
	
			try {
				sqlmapClient.endTransaction() ;
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
}

