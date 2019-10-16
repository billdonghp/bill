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
		response.setHeader("Content-Disposition", "attachment; filename=kpa01_rpt_tax.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		String make="不包含成果奖";
			
		String year = request.getParameter("year");
		SimpleMap parameterObject = new SimpleMap() ;
		PaReportService service = new PaReportService() ;				
		List endDataList = new ArrayList() ;
		parameterObject.set("YEAR",year) ;		  		
		if(request.getParameter("make")!=null){
			System.out.println("1");
			make="yesoResul";
			//parameterObject.set("ACHIEVEMENT","BonusType02");
			endDataList = service.retrieveKpaTaxListAndAchievement(parameterObject) ;
		} else {
			//parameterObject.set("ACHIEVEMENT","!BonusType02");
			endDataList = service.retrieveKpaTaxList(parameterObject) ;
			System.out.println("2");
		}
		
		
		
		request.setAttribute("bodyDataList",endDataList) ;	

%>

	<body>
  
		<table border="1" cellspacing="0" cellpadding="0">
			 <td colspan="13" >
		  		&nbsp;&nbsp;&nbsp;&nbsp;<font size="5">▣ '<%=year %><!-- 年中国海外住在员工资表 --><ait:message  messageID="display.pay.massageSpayroll" module="pay"/>(DICI,DICC,DIY)
				<%if(make.equalsIgnoreCase("yesoResul")) {%>
				<ait:message  messageID="display.pay.yesResul" module="pay"/>
				<%}else{ %>
				<ait:message  messageID="display.pay.noResul" module="pay"/>	<%} %>	단위 :  원
		  		</font>
		  	</td>
		  <tr>
		    <td align="center"><!-- 区分 --><ait:message  messageID="display.mutual.no"/></td>
		    <td align="center"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		    <td align="center"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		    <td align="center" colspan="5">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="5"><ait:message  messageID="display.pay.AnnualSalaryContent" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 韩国年薪 한국연봉 --><ait:message  messageID="display.pay.korSalary" module="pay"/>(￦)</td>
						<td align="center"><!-- 成果奖 성과급 --><ait:message  messageID="display.pay.Resul" module="pay"/></td>
						<td align="center"><!-- 年所得总额 년소득총액 --><ait:message  messageID="display.pay.totalIncome" module="pay"/></td>
						<td align="center"><!-- 外国工作扣除 --><ait:message  messageID="display.pay.overseasDeduction" module="pay"/></td>
						<td align="center"><!-- 纳税标准 --><ait:message  messageID="display.pay.standardTax" module="pay"/></td>
		    		</tr>
		    	</table>		    	   
		    </td>			
		    <td align="center" colspan="3">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="3">월세액계산</td></tr>
		    		<tr>
						<td align="center"><!-- 月纳税标准 --><ait:message  messageID="display.pay.standardTaxMonth" module="pay"/></td>
						<td align="center"><!-- 抚养 --><ait:message  messageID="display.pay.dependency" module="pay"/></td>
						<td align="center"><!-- 月纳税额 --><ait:message  messageID="display.pay.taxMonth" module="pay"/>  </td>
		    		</tr>
		    	</table>		    
		    </td>
		    <td align="center" colspan="2">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="2"><!-- 纳税额(年) --><ait:message  messageID="display.pay.taxYear" module="pay"/> </td></tr>
		    		<tr>
						<td align="center"><!-- 总纳税额 --><ait:message  messageID="display.pay.taxTotal" module="pay"/></td>
						<td align="center"><!-- 负担率 --><ait:message  messageID="display.pay.fDanL" module="pay"/></td>
		    		</tr>
		    	</table>		    
		    </td>
		  </tr>

			<c:forEach items="${bodyDataList}" var="oneResult" varStatus="i">
			<tr>
				<td align="center">${oneResult.RANK}</td>
				<td align="center">${oneResult.职位}</td>
				<td align="center">${oneResult.姓名}</td>
				<td align="center">${oneResult.韩国年薪}</td>
				<td align="center">${oneResult.成果奖}</td>
				<td align="center">${oneResult.年所得总额}</td>
				<td align="center">${oneResult.外国工作扣除}</td>				
				<td align="center">${oneResult.纳税标准}</td>
				<td align="center">${oneResult.月纳税标准}</td>				
				<td align="center">${oneResult.抚养}</td>
				<td align="center">${oneResult.月纳税额}</td>
				<td align="center">${oneResult.总纳税额}</td>				
				<td align="center">${oneResult.负担率}</td>
			</tr>							
			</c:forEach>
	</body>
</html>
