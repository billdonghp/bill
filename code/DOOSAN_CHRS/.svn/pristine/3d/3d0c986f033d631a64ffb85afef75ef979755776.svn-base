/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.reports.servlet.pa;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.reports.reportservices.PaReportService;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ExportReport0222Cmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	private PaReportService service = null;

	private AdminBean admin = null;

	private SimpleMap parameterObject = null;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service = new PaReportService();
		admin = (AdminBean) request.getSession(false).getAttribute("admin");
		
		List<Calendar> dateList = new ArrayList<Calendar>() ;
		String months[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"} ;
		SimpleDateFormat report0222DateFormat = new SimpleDateFormat("yyyyMM") ;
		
		/*
		 * 	SELECT T.POST_CODE, MAX(SY.CODE_NAME)
			  FROM HR_EMPLOYEE T, SY_CODE SY
			 WHERE T.POST_CODE = SY.CODE_ID(+)
			   AND T.CPNY_ID = '63000000'
			 GROUP BY T.POST_CODE
		*/
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("startMonth", parameterObject.getString("year") + "01");
			parameterObject.setString("endMonth", parameterObject.getString("year") + "12");
			
			// 格式化日期
			Calendar dateStarted = Calendar.getInstance() ;
			dateStarted.setTime(report0222DateFormat.parse(parameterObject.getString("startMonth"))) ;
			
			Calendar dateEnd = Calendar.getInstance() ;
			dateEnd.setTime(report0222DateFormat.parse(parameterObject.getString("endMonth"))) ;
			
			// 循环得到日期
			Calendar temp = Calendar.getInstance() ; 
			temp.setTime(dateStarted.getTime()) ;

			dateList.add(temp) ;
			while(!dateStarted.equals(dateEnd))
			{	
				temp = Calendar.getInstance() ; 
				dateStarted.add(Calendar.MONTH, 1) ;
				temp.setTime(dateStarted.getTime()) ;
				
				dateList.add(temp) ;
			}
			
			// retrieve attendance record list
			List recordList = new ArrayList() ;
			List paRecordList = service.retrieve630Report0222PaList(parameterObject);
			List bonusRecordList = service.retrieve630Report0222BonusList(parameterObject);
			
			SimpleMap recordMap1 = new SimpleMap() ;
			recordMap1.setNullToInitialize(true) ;
			recordMap1.setString("distinction", "工资") ;
			recordMap1.setFloat("total", 0) ;
			recordList.add(recordMap1) ;
			
			SimpleMap recordMap2 = new SimpleMap() ;
			recordMap2.setNullToInitialize(true) ;
			recordMap2.setString("distinction", "奖金") ;
			recordMap2.setFloat("total", 0) ;
			recordList.add(recordMap2) ;
			
			SimpleMap recordMap3 = new SimpleMap() ;
			recordMap3.setNullToInitialize(true) ;
			recordMap3.setString("distinction", "办公职合计(A)") ;
			recordMap3.setFloat("total", 0) ;
			recordList.add(recordMap3) ;
			
			SimpleMap recordMap4 = new SimpleMap() ;
			recordMap4.setNullToInitialize(true) ;
			recordMap4.setString("distinction", "工资") ;
			recordMap4.setFloat("total", 0) ;
			recordList.add(recordMap4) ;
			
			SimpleMap recordMap5 = new SimpleMap() ;
			recordMap5.setNullToInitialize(true) ;
			recordMap5.setString("distinction", "奖金") ;
			recordMap5.setFloat("total", 0) ;
			recordList.add(recordMap5) ;
			
			SimpleMap recordMap6 = new SimpleMap() ;
			recordMap6.setNullToInitialize(true) ;
			recordMap6.setString("distinction", "加班费") ;
			recordMap6.setFloat("total", 0) ;
			recordList.add(recordMap6) ;
			
			SimpleMap recordMap7 = new SimpleMap() ;
			recordMap7.setNullToInitialize(true) ;
			recordMap7.setString("distinction", "工人合计(B)") ;
			recordMap7.setFloat("total", 0) ;
			recordList.add(recordMap7) ;
			
			SimpleMap recordMap8 = new SimpleMap() ;
			recordMap8.setNullToInitialize(true) ;
			recordMap8.setString("distinction", "合计(A+B)") ;
			recordMap8.setFloat("total", 0) ;
			recordList.add(recordMap8) ;
			
			// 存储数据
			for(Calendar calendar : dateList){
				String paMonth = report0222DateFormat.format(calendar.getTime()) ;
				String month = months[calendar.get(Calendar.MONTH)] ;
				
				// 循环工资数据,进行累加
				for (int i = 0 ; i < paRecordList.size(); ++i)
				{
					SimpleMap recordMap = (SimpleMap)paRecordList.get(i) ;
					if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST_GRADE").equals("职员"))
					{
						recordMap1.setFloat(month, recordMap.getFloat("THIS_ACTUAL_WAGE")) ;
					}
					
					if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST_GRADE").equals("工人"))
					{
						recordMap4.setFloat(month, recordMap.getFloat("THIS_ACTUAL_WAGE")) ;
						recordMap6.setFloat(month, recordMap.getFloat("PERFORMANCE_PAY3")) ;
					}
				}
				// 循环奖金数据,进行累加
				for (int j = 0 ; j < bonusRecordList.size(); ++j)
				{
					SimpleMap recordMap = (SimpleMap)bonusRecordList.get(j) ;
					if (recordMap.getString("PA_MONTH").equals("paMonth") && recordMap.getString("POST_GRADE").equals("职员"))
					{
						recordMap2.setFloat(month, recordMap.getFloat("THIS_ACTUAL_WAGE")) ;
					}
					
					if (recordMap.getString("PA_MONTH").equals("paMonth") && recordMap.getString("POST_GRADE").equals("工人"))
					{
						recordMap5.setFloat(month, recordMap.getFloat("THIS_ACTUAL_WAGE")) ;
					}
				}
				
				// 不同的汇总
				recordMap3.setFloat(month, recordMap1.getFloat(month) + recordMap2.getFloat(month)) ;
				recordMap7.setFloat(month, recordMap4.getFloat(month) + recordMap5.getFloat(month)) ;
				recordMap8.setFloat(month, recordMap3.getFloat(month) + recordMap7.getFloat(month)) ;
				
				// 汇总total
				recordMap1.setFloat("total", recordMap1.getFloat("total") + recordMap1.getFloat(month)) ;
				recordMap2.setFloat("total", recordMap2.getFloat("total") + recordMap2.getFloat(month)) ;
				recordMap3.setFloat("total", recordMap3.getFloat("total") + recordMap3.getFloat(month)) ;
				recordMap4.setFloat("total", recordMap4.getFloat("total") + recordMap4.getFloat(month)) ;
				recordMap5.setFloat("total", recordMap5.getFloat("total") + recordMap5.getFloat(month)) ;
				recordMap6.setFloat("total", recordMap6.getFloat("total") + recordMap6.getFloat(month)) ;
				recordMap7.setFloat("total", recordMap7.getFloat("total") + recordMap7.getFloat(month)) ;
				recordMap8.setFloat("total", recordMap8.getFloat("total") + recordMap8.getFloat(month)) ;
			}
			
			SimpleMap tempMap = new SimpleMap();
			
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			
			columns.setString("区分", "distinction");
			
			for (int m = 0 ; m < months.length; ++m){
				columns.setString(months[m], months[m]);
			}
			
			columns.setString("合计", "total");

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("Report0222.xls");
			paramBean.setSheetname("Report0222");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			// LSFC考勤刷卡记录表
			paramBean.setInLineHeadContent("");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export pa_detail_factory record report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
}
