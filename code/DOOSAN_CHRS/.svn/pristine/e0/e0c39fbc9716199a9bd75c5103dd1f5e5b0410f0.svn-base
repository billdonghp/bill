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
import com.ait.reports.reportservices.PaReportService;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

public class ExportReport0221Cmd extends ArAbstractCommand {

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
		SimpleDateFormat report0221DateFormat = new SimpleDateFormat("yyyyMM") ;
		
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
			dateStarted.setTime(report0221DateFormat.parse(parameterObject.getString("startMonth"))) ;
			
			Calendar dateEnd = Calendar.getInstance() ;
			dateEnd.setTime(report0221DateFormat.parse(parameterObject.getString("endMonth"))) ;
			
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
			
			// 人员区分人件费统计
			// retrieve attendance record list
			List recordList = new ArrayList() ;
			List paRecordList = service.retrieve630Report0221PaList(parameterObject);
			List bonusRecordList = service.retrieve630Report0221BonusList(parameterObject);
			
			SimpleMap recordMap12 = new SimpleMap() ;
			recordMap12.setNullToInitialize(true) ;
			recordMap12.setString("distinction", "专务") ;
			recordMap12.setString("distinction_PER_SUM", "专务") ;
			recordMap12.setFloat("total", 0) ;
			recordMap12.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap12) ;
			
			SimpleMap recordMap11 = new SimpleMap() ;
			recordMap11.setNullToInitialize(true) ;
			recordMap11.setString("distinction", "常务") ;
			recordMap11.setString("distinction_PER_SUM", "常务") ;
			recordMap11.setFloat("total", 0) ;
			recordMap11.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap11) ;
			
			SimpleMap recordMap1 = new SimpleMap() ;
			recordMap1.setNullToInitialize(true) ;
			recordMap1.setString("distinction", "部长") ;
			recordMap1.setString("distinction_PER_SUM", "部长") ;
			recordMap1.setFloat("total", 0) ;
			recordMap1.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap1) ;
			
			SimpleMap recordMap10 = new SimpleMap() ;
			recordMap10.setNullToInitialize(true) ;
			recordMap10.setString("distinction", "副部长") ;
			recordMap10.setString("distinction_PER_SUM", "副部长") ;
			recordMap10.setFloat("total", 0) ;
			recordMap10.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap10) ;
			
			SimpleMap recordMap2 = new SimpleMap() ;
			recordMap2.setNullToInitialize(true) ;
			recordMap2.setString("distinction", "课长") ;
			recordMap2.setString("distinction_PER_SUM", "课长") ;
			recordMap2.setFloat("total", 0) ;
			recordMap2.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap2) ;
			
			SimpleMap recordMap3 = new SimpleMap() ;
			recordMap3.setNullToInitialize(true) ;
			recordMap3.setString("distinction", "主任") ;
			recordMap3.setString("distinction_PER_SUM", "主任") ;
			recordMap3.setFloat("total", 0) ;
			recordMap3.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap3) ;
			
			SimpleMap recordMap4 = new SimpleMap() ;
			recordMap4.setNullToInitialize(true) ;
			recordMap4.setString("distinction", "职员") ;
			recordMap4.setString("distinction_PER_SUM", "职员") ;
			recordMap4.setFloat("total", 0) ;
			recordMap4.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap4) ;
			
			SimpleMap recordMap5 = new SimpleMap() ;
			recordMap5.setNullToInitialize(true) ;
			recordMap5.setString("distinction", "办公职合计") ;
			recordMap5.setString("distinction_PER_SUM", "办公职合计") ;
			recordMap5.setFloat("total", 0) ;
			recordMap5.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap5) ;
			
			SimpleMap recordMap6 = new SimpleMap() ;
			recordMap6.setNullToInitialize(true) ;
			recordMap6.setString("distinction", "正式工人") ;
			recordMap6.setString("distinction_PER_SUM", "正式工人") ;
			recordMap6.setFloat("total", 0) ;
			recordMap6.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap6) ;
			
			SimpleMap recordMap7 = new SimpleMap() ;
			recordMap7.setNullToInitialize(true) ;
			recordMap7.setString("distinction", "实习工人") ;
			recordMap7.setString("distinction_PER_SUM", "实习工人") ;
			recordMap7.setFloat("total", 0) ;
			recordMap7.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap7) ;
			
			SimpleMap recordMap8 = new SimpleMap() ;
			recordMap8.setNullToInitialize(true) ;
			recordMap8.setString("distinction", "工人合计") ;
			recordMap8.setString("distinction_PER_SUM", "工人合计") ;
			recordMap8.setFloat("total", 0) ;
			recordMap8.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap8) ;
			
			SimpleMap recordMap9 = new SimpleMap() ;
			recordMap9.setNullToInitialize(true) ;
			recordMap9.setString("distinction", "合计(职员 + 工人)") ;
			recordMap9.setString("distinction_PER_SUM", "合计(职员 + 工人)") ;
			recordMap9.setFloat("total", 0) ;
			recordMap9.setInt("total_PER_SUM", 0) ;
			recordList.add(recordMap9) ;
			
			
			// 存储数据
			for(Calendar calendar : dateList){
				String paMonth = report0221DateFormat.format(calendar.getTime()) ;
				String month = months[calendar.get(Calendar.MONTH)] ;
				
				// 循环工资数据,进行累加
				for (int i = 0 ; i < paRecordList.size(); ++i)
				{
					SimpleMap recordMap = (SimpleMap)paRecordList.get(i) ;
					recordMap.setNullToInitialize(true) ;

					if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("专务"))
					{
						recordMap12.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap12.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("常务"))
					{
						recordMap11.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap11.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("部长"))
					{
						recordMap1.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap1.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("副部长"))
					{
						recordMap10.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap10.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("课长"))
					{
						recordMap2.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap2.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("主任"))
					{
						recordMap3.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap3.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("职员"))
					{
						recordMap4.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap4.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("正式工人")) 
					{
						recordMap6.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap6.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("实习工人"))
					{
						recordMap7.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap7.setInt(month + "_PER_SUM", recordMap.getInt("PER_SUM")) ;
					}
				}
				// 循环奖金数据,进行累加
				for (int j = 0 ; j < bonusRecordList.size(); ++j)
				{
					SimpleMap recordMap = (SimpleMap)bonusRecordList.get(j) ;
					recordMap.setNullToInitialize(true) ;
					if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("专务"))
					{
						recordMap12.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap12.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap11.setInt(month + "_PER_SUM", recordMap11.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("常务"))
					{
						recordMap11.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap11.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap11.setInt(month + "_PER_SUM", recordMap11.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("部长"))
					{
						recordMap1.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap1.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap1.setInt(month + "_PER_SUM", recordMap1.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("副部长"))
					{
						recordMap10.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap10.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap10.setInt(month + "_PER_SUM", recordMap10.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("课长"))
					{
						recordMap2.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap2.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap2.setInt(month + "_PER_SUM", recordMap2.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("主任"))
					{
						recordMap3.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap3.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap3.setInt(month + "_PER_SUM", recordMap3.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("职员"))
					{
						recordMap4.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap4.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap4.setInt(month + "_PER_SUM", recordMap4.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("正式工人"))
					{
						recordMap6.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap6.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap6.setInt(month + "_PER_SUM", recordMap6.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
					else if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST").equals("实习工人"))
					{
						recordMap7.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap7.getDouble(month),"0.00"))
								+ Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						//recordMap7.setInt(month + "_PER_SUM", recordMap7.getInt(month + "_PER_SUM") + recordMap.getInt("PER_SUM")) ;
					}
				}
				
				// 不同的汇总
				recordMap5.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap1.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap12.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap11.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap10.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap2.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap3.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap4.getDouble(month),"0.00"))) ;
				
				recordMap5.setInt(month + "_PER_SUM", recordMap1.getInt(month + "_PER_SUM") + recordMap12.getInt(month + "_PER_SUM") 
						+recordMap11.getInt(month + "_PER_SUM") + recordMap10.getInt(month + "_PER_SUM") + recordMap2.getInt(month + "_PER_SUM") 
						+ recordMap3.getInt(month + "_PER_SUM") + recordMap4.getInt(month + "_PER_SUM")) ;
				recordMap8.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap6.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap7.getDouble(month),"0.00"))) ;
				recordMap8.setInt(month + "_PER_SUM", recordMap6.getInt(month + "_PER_SUM") + recordMap7.getInt(month + "_PER_SUM")) ;
				recordMap9.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap5.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap8.getDouble(month),"0.00"))) ;
				recordMap9.setInt(month + "_PER_SUM", recordMap5.getInt(month + "_PER_SUM") + recordMap8.getInt(month + "_PER_SUM")) ;
				
				// 汇总total
				recordMap1.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap1.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap1.getDouble(month),"0.00"))) ;
				recordMap1.setInt("total_PER_SUM", recordMap1.getInt("total_PER_SUM") + recordMap1.getInt(month + "_PER_SUM")) ;
				recordMap12.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap12.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap12.getDouble(month),"0.00"))) ;
				recordMap12.setInt("total_PER_SUM", recordMap12.getInt("total_PER_SUM") + recordMap12.getInt(month + "_PER_SUM")) ;
				recordMap11.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap11.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap11.getDouble(month),"0.00"))) ;
				recordMap11.setInt("total_PER_SUM", recordMap11.getInt("total_PER_SUM") + recordMap11.getInt(month + "_PER_SUM")) ;
				recordMap10.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap10.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap10.getDouble(month),"0.00"))) ;
				recordMap10.setInt("total_PER_SUM", recordMap10.getInt("total_PER_SUM") + recordMap10.getInt(month + "_PER_SUM")) ;
				recordMap2.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap2.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap2.getDouble(month),"0.00"))) ;
				recordMap2.setInt("total_PER_SUM", recordMap2.getInt("total_PER_SUM") + recordMap2.getInt(month + "_PER_SUM")) ;
				recordMap3.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap3.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap3.getDouble(month),"0.00"))) ;
				recordMap3.setInt("total_PER_SUM", recordMap3.getInt("total_PER_SUM") + recordMap3.getInt(month + "_PER_SUM")) ;
				recordMap4.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap4.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap4.getDouble(month),"0.00"))) ;
				recordMap4.setInt("total_PER_SUM", recordMap4.getInt("total_PER_SUM") + recordMap4.getInt(month + "_PER_SUM")) ;
				recordMap5.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap5.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap5.getDouble(month),"0.00"))) ;
				recordMap5.setInt("total_PER_SUM", recordMap5.getInt("total_PER_SUM") + recordMap5.getInt(month + "_PER_SUM")) ;
				recordMap6.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap6.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap6.getDouble(month),"0.00"))) ;
				recordMap6.setInt("total_PER_SUM", recordMap6.getInt("total_PER_SUM") + recordMap6.getInt(month + "_PER_SUM")) ;
				recordMap7.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap7.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap7.getDouble(month),"0.00"))) ;
				recordMap7.setInt("total_PER_SUM", recordMap7.getInt("total_PER_SUM") + recordMap7.getInt(month + "_PER_SUM")) ;
				recordMap8.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap8.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap8.getDouble(month),"0.00"))) ;
				recordMap8.setInt("total_PER_SUM", recordMap8.getInt("total_PER_SUM") + recordMap8.getInt(month + "_PER_SUM")) ;
				recordMap9.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap9.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap9.getDouble(month),"0.00"))) ;
				recordMap9.setInt("total_PER_SUM", recordMap9.getInt("total_PER_SUM") + recordMap9.getInt(month + "_PER_SUM")) ;
			}
			
			
			//薪资类别人件费统计
			// retrieve attendance record list
			List recordList2 = new ArrayList() ;
			List paRecordList2 = service.retrieve630Report0222PaList(parameterObject);
			List bonusRecordList2 = service.retrieve630Report0222BonusList(parameterObject);
			
			SimpleMap recordMap21 = new SimpleMap() ;
			recordMap21.setNullToInitialize(true) ;
			recordMap21.setString("distinction", "工资") ;
			recordMap21.setDouble("total", 0) ;
			recordList2.add(recordMap21) ;
			
			SimpleMap recordMap22 = new SimpleMap() ;
			recordMap22.setNullToInitialize(true) ;
			recordMap22.setString("distinction", "奖金") ;
			recordMap22.setDouble("total", 0) ;
			recordList2.add(recordMap22) ;
			
			SimpleMap recordMap23 = new SimpleMap() ;
			recordMap23.setNullToInitialize(true) ;
			recordMap23.setString("distinction", "办公职合计(A)") ;
			recordMap23.setDouble("total", 0) ;
			recordList2.add(recordMap23) ;
			
			SimpleMap recordMap24 = new SimpleMap() ;
			recordMap24.setNullToInitialize(true) ;
			recordMap24.setString("distinction", "工资") ;
			recordMap24.setDouble("total", 0) ;
			recordList2.add(recordMap24) ;
			
			SimpleMap recordMap25 = new SimpleMap() ;
			recordMap25.setNullToInitialize(true) ;
			recordMap25.setString("distinction", "奖金") ;
			recordMap25.setDouble("total", 0) ;
			recordList2.add(recordMap25) ;
			
			SimpleMap recordMap26 = new SimpleMap() ;
			recordMap26.setNullToInitialize(true) ;
			recordMap26.setString("distinction", "加班费") ;
			recordMap26.setDouble("total", 0) ;
			recordList2.add(recordMap26) ;
			
			SimpleMap recordMap27 = new SimpleMap() ;
			recordMap27.setNullToInitialize(true) ;
			recordMap27.setString("distinction", "工人合计(B)") ;
			recordMap27.setDouble("total", 0) ;
			recordList2.add(recordMap27) ;
			
			SimpleMap recordMap28 = new SimpleMap() ;
			recordMap28.setNullToInitialize(true) ;
			recordMap28.setString("distinction", "合计(A+B)") ;
			recordMap28.setDouble("total", 0) ;
			recordList2.add(recordMap28) ;
			
			// 存储数据
			for(Calendar calendar : dateList){
				String paMonth = report0221DateFormat.format(calendar.getTime()) ;
				String month = months[calendar.get(Calendar.MONTH)] ;
				
				// 循环工资数据,进行累加
				for (int i = 0 ; i < paRecordList2.size(); ++i)
				{
					SimpleMap recordMap = (SimpleMap)paRecordList2.get(i) ;
					if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST_GRADE").equals("职员"))
					{
						recordMap21.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
					}
					
					if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST_GRADE").equals("工人"))
					{
						recordMap24.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
						recordMap26.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("PERFORMANCE_PAY3"),"0.00"))) ;
					}
				}
				// 循环奖金数据,进行累加
				for (int j = 0 ; j < bonusRecordList2.size(); ++j)
				{
					SimpleMap recordMap = (SimpleMap)bonusRecordList2.get(j) ;
					
					if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST_GRADE").equals("职员"))
					{
						recordMap22.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
					}
					
					if (recordMap.getString("PA_MONTH").equals(paMonth) && recordMap.getString("POST_GRADE").equals("工人"))
					{
						recordMap25.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap.getDouble("THIS_ACTUAL_WAGE"),"0.00"))) ;
					}
				}
				
				// 不同的汇总
				recordMap23.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap21.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap22.getDouble(month),"0.00"))) ;
				recordMap27.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap24.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap25.getDouble(month),"0.00"))) ;
				recordMap28.setDouble(month, Double.parseDouble(NumberUtil.formatNumber(recordMap23.getDouble(month),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap27.getDouble(month),"0.00"))) ;
				
				// 汇总total
				recordMap21.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap21.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap21.getDouble(month),"0.00"))) ;
				recordMap22.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap22.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap22.getDouble(month),"0.00"))) ;
				recordMap23.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap23.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap23.getDouble(month),"0.00"))) ;
				recordMap24.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap24.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap24.getDouble(month),"0.00"))) ;
				recordMap25.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap25.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap25.getDouble(month),"0.00"))) ;
				recordMap26.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap26.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap26.getDouble(month),"0.00"))) ;
				recordMap27.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap27.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap27.getDouble(month),"0.00"))) ;
				recordMap28.setDouble("total", Double.parseDouble(NumberUtil.formatNumber(recordMap28.getDouble("total"),"0.00"))
						+ Double.parseDouble(NumberUtil.formatNumber(recordMap28.getDouble(month),"0.00"))) ;
			}
			
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			
			columns.setString("区分", "distinction");
			
			for (int m = 0 ; m < months.length; ++m){
				columns.setString(months[m], months[m]);
			}
			
			columns.setString("合计", "total");
			
			request.setAttribute("year", parameterObject.getString("year")) ;
			//request.setAttribute("dateList", dateList) ;
			request.setAttribute("recordList", recordList) ;
			request.setAttribute("recordList2", recordList2) ;

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ExportReport0221Cmd Exception. ", e);
		}

		return "/reports/pa_report/report0221.jsp" ;
	}
}
