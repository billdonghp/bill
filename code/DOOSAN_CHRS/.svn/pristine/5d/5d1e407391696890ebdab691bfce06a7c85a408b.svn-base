<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="com.ait.reports.reportservices.PaReportService" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title></title></head>


 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=kpa01_rpt_IndividualsWage.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 

		  		String year = request.getParameter("paYear") ;
		  		String emp = request.getParameter("emp");
		  		
		  		
		  		SimpleMap parameterObject = new SimpleMap() ;
		  		System.out.println(year+emp);	
		  		parameterObject.set("YEAR",year);
		  		parameterObject.set("EMPID",emp);
				PaReportService service = new PaReportService() ;	
				//得到中文名/韩文名
				//报表上
				List endDataList1 = new ArrayList() ;
				List endDataList2 = new ArrayList() ;
				List endDataList3 = new ArrayList() ;
				endDataList1 = service.retrieveInfoOnList(parameterObject);//调整前
				//名字
				
				endDataList2 = service.retrieveAchievementbyEmpId(parameterObject);//成果奖
				endDataList3 = service.retrieveInfoOnListEnd(parameterObject);//调整后
				request.setAttribute("bodyDataList1",endDataList1) ;	
				request.setAttribute("bodyDataList2",endDataList2) ;	
				request.setAttribute("bodyDataList3",endDataList2) ;	
				
				//报表下
				List endDataList4 = new ArrayList() ;
				List endDataList5 = new ArrayList() ;//first  
				List endDataList6 = new ArrayList(); 
				List endDataList7 = new ArrayList();//second
				List endDataList8 = new ArrayList();//年记
				endDataList4 = service.retrieveInfoUnderList(parameterObject);				
				endDataList5 = service.retrieveInfoFirstYearSum(parameterObject);
				endDataList6 = service.retrieveInfoUnderSecondList(parameterObject);
				endDataList7 = service.retrieveInfoSecondYearSum(parameterObject);
				endDataList8 = service.retrieveInfoYearSum(parameterObject);//年记合计
				request.setAttribute("bodyDataList4",endDataList5) ;
				request.setAttribute("bodyDataList5",endDataList5) ;
				request.setAttribute("bodyDataList6",endDataList6) ;
				request.setAttribute("bodyDataList7",endDataList7) ;
				request.setAttribute("bodyDataList8",endDataList8) ;
				
%>
	<body><br>

		<table border="1" align="center">
		  <tr align="left">
		  	<td><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		  	<td><%=emp %></td>
		  </tr>
		</table> <br> 
		<table border="1" cellspacing="0" cellpadding="0">
		  <tr align="center">
		  	<td colspan="7" ><%=year %><!-- 年个人别工资表 --><ait:message  messageID="didisplay.pay.yearSingleSalary" module="pay"/></td>
		  </tr>
		  <tr>
		    <td align="center"><!-- 区分=구분 --><ait:message  messageID="display.pay.distin" module="pay"/></td>
		    <td align="center"><!-- 韩国年薪  --><ait:message  messageID="display.pay.korSalary" module="pay"/></td>
		    <td align="center"><!-- 总纳税额 --><ait:message  messageID="display.pay.taxTotal" module="pay"/></td>
		    <td align="center"><!-- 海外补贴 --><ait:message  messageID="display.pay.OverseasSubsidies" module="pay"/></td>
		    <td align="center"><!-- 合计 --><ait:message  messageID="display.pay.total" module="pay"/></td>
		    <td align="center"><!-- 月固定工资 --><ait:message  messageID="display.pay.monthlyFixSalary" module="pay"/></td>		   
		    <td align="center"><!-- 适用期间 적용기간--><ait:message  messageID="display.pay.applicable" module="pay"/></td>	
		    
		    </tr>
			<c:forEach items="${bodyDataList1}" var="oneResult" varStatus="i">
			<tr>
				<td align="center">
					<table border="1" cellspacing="0" cellpadding="0">
						<tr><td colspan="2"><!-- 调整前 --><ait:message  messageID="display.pay.beforeAdjustment" module="pay"/></td>
							<td>
								<table border="1" cellspacing="0" cellpadding="0">
									<tr><td>${oneResult.月}</td></tr>
								</table>
							</td>
						</tr>
					</table>
				</td>				
				<td align="center">${oneResult.韩国年薪}</td>
				<td align="center">${oneResult.总纳税额}</td>
				<td align="center">${oneResult.海外补贴}</td>
				<td align="center">${oneResult.合计}</td>
				<td align="center">${oneResult.月固定工资}</td>
				<td align="center">${oneResult.适用期间}</td>	
			</tr>							
			</c:forEach>
			<c:forEach items="${bodyDataList2}" var="oneResult" varStatus="i">
			<tr>
				<td align="center" ><ait:content enContent="${oneResult.成果奖CN}" zhContent="${oneResult.成果奖CN}" koContent="${oneResult.成果奖KR}"/></td>
				<td align="center">${oneResult.应发成果奖}</td>
				<td align="center">${oneResult.成果奖税}</td>
				<td align="center"></td>
				<td align="center">${oneResult.实发成果奖}</td>	
				<td align="center"></td>
				<td align="center"></td>
			</tr>
			</c:forEach>
		    <c:forEach items="${bodyDataList3}" var="oneResult" varStatus="i">
			<tr>
				<td align="center">
					<table border="1" cellspacing="0" cellpadding="0">
						<tr><td colspan="2"><!-- 调整后 --><ait:message  messageID="display.pay.afterAdjustment" module="pay"/></td>
							<td>
								<table border="1" cellspacing="0" cellpadding="0">
									<tr><td>${oneResult.月}</td></tr>
								</table>
							</td>
						</tr>
					</table>
				</td>				
				<td align="center">${oneResult.韩国年薪}</td>
				<td align="center">${oneResult.总纳税额}</td>
				<td align="center">${oneResult.海外补贴}</td>
				<td align="center">${oneResult.合计}</td>
				<td align="center">${oneResult.月固定工资}</td>
				<td align="center">${oneResult.适用期间}</td>	
			</tr>							
			</c:forEach>		
		</table>
		<br><br>
		
		
		<table border="1" cellspacing="0" cellpadding="0">
			  <tr>
			    <td align="center" colspan="2"><!-- 区分=구분 --><ait:message  messageID="display.pay.distin" module="pay"/></td>
			    <td align="center" colspan="4">	
			    	<table border="1" cellspacing="0" cellpadding="0">
			    		<tr><td align="center" colspan="4"><!-- 支付 Table --><ait:message  messageID="display.pay.toPay" module="pay"/></td></tr>
			    		<tr>
							<td align="center"><!-- 合计 --><ait:message  messageID="display.pay.total" module="pay"/>(A)</td>
							<td align="center"><!-- 月支付年薪 중국지급월봉--><ait:message  messageID="display.pay.tomonthofYearsalary" module="pay"/></td>
							<td align="center"><!-- 月支付海外补贴 월간해외수당--><ait:message  messageID="display.pay.overseas_to_pay_subsidies" module="pay"/></td>
							<td align="center"><!-- 其它支付 --><ait:message  messageID="display.pay.otherPayments" module="pay"/></td>
			    		</tr>
			    	</table>		    	   
			    </td>			
			    
			   	<td align="center"><!-- 失误令 실수령 --><ait:message  messageID="display.pay.mistakes" module="pay"/></td>
				<td align="center"><!-- 差异 차이 --><ait:message  messageID="display.pay.difference" module="pay"/>(B-A)</td>
				<td align="center"><!-- 调整 --><ait:message  messageID="display.pay.adjustment" module="pay"/></td>
			  </tr>
			<c:forEach items="${bodyDataList4}" var="oneResult" varStatus="i">
			<tr>
				<td align="center" >${oneResult.月}</td>
				<td align="center" ><!-- 已执行 --><ait:message  messageID="display.pay.implemented" module="pay"/></td>
				<td align="center">${oneResult.合计A}</td>
				<td align="center">${oneResult.月支付年薪}</td>
				<td align="center">${oneResult.月海外补贴}</td>
				<td align="center">${oneResult.其它支付合计}</td>	
				<td align="center">${oneResult.失误令}</td>
				<td align="center">${oneResult.差异}</td>
				<td align="center">${oneResult.调整}</td>
			</tr>
			</c:forEach>			    
			<c:forEach items="${bodyDataList5}" var="oneResult" varStatus="i">
			<tr>
				<td align="center" colspan="2"><!-- 上半年 --><ait:message  messageID="display.pay.firstYear" module="pay"/></td>
				<td align="center">${oneResult.合计A}</td>
				<td align="center">${oneResult.月支付年薪}</td>
				<td align="center">${oneResult.月海外补贴}</td>
				<td align="center">${oneResult.其它支付合计}</td>
				<td align="center">${oneResult.失误令}</td>
				<td align="center">${oneResult.差异}</td>
				<td align="center">${oneResult.调整}</td>
			</tr>
			</c:forEach>		
			<c:forEach items="${bodyDataList6}" var="oneResult" varStatus="i">
			<tr>
				<td align="center" >${oneResult.月}</td>
				<td align="center" ><!-- 已执行 --><ait:message  messageID="display.pay.implemented" module="pay"/></td>
				<td align="center">${oneResult.合计A}</td>
				<td align="center">${oneResult.月支付年薪}</td>
				<td align="center">${oneResult.月海外补贴}</td>
				<td align="center">${oneResult.其它支付合计}</td>	
				<td align="center">${oneResult.失误令}</td>
				<td align="center">${oneResult.差异}</td>
				<td align="center">${oneResult.调整}</td>
			</tr>
			</c:forEach>	
			<c:forEach items="${bodyDataList7}" var="oneResult" varStatus="i">
			<tr>
				<td align="center" colspan="2"><!-- 下半年 --><ait:message  messageID="display.pay.downYear" module="pay"/></td>
				<td align="center">${oneResult.合计A}</td> 
				<td align="center">${oneResult.月支付年薪}</td>
				<td align="center">${oneResult.月海外补贴}</td>
				<td align="center">${oneResult.其它支付合计}</td>	
				<td align="center">${oneResult.失误令}</td>
				<td align="center">${oneResult.差异}</td>
				<td align="center">${oneResult.调整}</td>
			</tr>
			</c:forEach>	
			<c:forEach items="${bodyDataList8}" var="oneResult" varStatus="i">
			<tr>
				<td align="center" colspan="2"><!-- 年记	 --><ait:message  messageID="display.pay.totYear" module="pay"/></td>
				<td align="center">${oneResult.合计A}</td> 
				<td align="center">${oneResult.月支付年薪}</td>
				<td align="center">${oneResult.月海外补贴}</td>
				<td align="center">${oneResult.其它支付合计}</td>	
				<td align="center"> </td>
				<td align="center"> </td>
				<td align="center"> </td>
			</tr>
			</c:forEach>	
			</table>
	</body>
</html>
