<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="com.ait.reports.reportservices.ArReportService" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

<%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0126.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
%>

	<body>
		<table width="600">
		  <tr align="center">
		  	<td colspan="${16 + fn:length(arDateList)}" rowspan="2">
		  	<b><font size="+2">${fn:substring(yearMonth, 0, 4)}年${fn:substring(yearMonth, 4, 6)}月 考勤明细表</font></td></b>
		  	</td>
		  </tr>
		 </table>
		<table width="600">
		 <tr align="left"><td colspan="30"><b><font size="+1">部门：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 所属课：</font></b></td></tr>
		</table>
		<table width="600" border="1" align="center">

		   <tr align="center">
		    <td rowspan="2">序号</td>
		  	<td rowspan="2">部门</td>
		  	<td rowspan="2">所属</td>
		  	<td rowspan="2">职号</td>
		  	<td rowspan="2">姓名</td>
		  	<td colspan="${fn:length(arDateList)}" ><b>每日出勤情况</b></td>
		  	<td colspan="3"><b>加班情况</b></td>
		  	<td rowspan="2">夜班<br>次数</td>
		  	<td colspan="6"><b>缺勤合计</b></td>
		  	<td rowspan="2">员工确认</td>
		  </tr>	
		  <tr align="center">
		  	
<%		
		ArReportService service = new ArReportService() ;
		String week[] = {"无","日","一","二","三","四","五","六"} ;
		
		String color[] = {"","#CCCCCC","","","","","","#CCCCCC"} ;		
	
		List arDateList = (List)request.getAttribute("arDateList") ;
		for (int i = 0; i < arDateList.size(); ++i)
		{
			Calendar calendar = (Calendar)arDateList.get(i) ;
%>
		<td valign="middle" style="font-size:8pt; font-style: normal; font-family: 宋体" bgcolor=<%=color[calendar.get(Calendar.DAY_OF_WEEK)]%> ><%= calendar.get(Calendar.DAY_OF_MONTH)%><br><%= week[calendar.get(Calendar.DAY_OF_WEEK)] %></td>
<%		}	%>
        <td>平日<br>加班</td>
        <td>休息日<br>加班</td>
        <td>加班<br>小计</td>
        <td>事假</td>
        <td class="info_title_01">病假</td>
        <td>旷工</td>
        <td >迟到/<br>早退</td>
        <td>放休</td>
        <td>其他假</td>
         
		  </tr>
<%
		List arDataList = (List)request.getAttribute("arDataList") ; 
		
		for (int i = 0 ; i < arDataList.size(); ++i)
		{
			SimpleMap arData = (SimpleMap)arDataList.get(i) ;
			
			
%>
		<tr align="center">				
		            <td ><%= i+1 %></td>
					<td ><%= StringUtil.checkNull(arData.getString("OFFICENAME")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("DEPTNAME")) %></td>			  
					<td ><%= StringUtil.checkNull(arData.getString("EMPID")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("CHINESENAME")) %></td>

<%				

				List personDataList = new ArrayList() ;
				
				personDataList = service.retrieveReport0126Data2(arData) ;
				
				
				SimpleDateFormat report0126DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
				for (int j = 0 ; j < arDateList.size(); ++j)
				{
					Calendar calendar = (Calendar)arDateList.get(j) ;
					String dateStr = report0126DateFormat.format(calendar.getTime()) ;
%>			<td style="font-size:8pt; font-style: normal; font-family: 宋体">	<%
					
						int k = 0 ;
						String itemName = "" ;
						String quantity = "" ;
						for (int m = 0 ; m < personDataList.size() ; ++m)
						{
							SimpleMap personData = (SimpleMap)personDataList.get(m) ;
							
							if (dateStr.equals(personData.getString("AR_DATE_STR"))){
								if (k == 0){
									itemName += personData.getString("ITEM_NAME") ;
									quantity += personData.getString("QUANTITY") ;
									++k ;
								}else{
									itemName += ", " + personData.getString("ITEM_NAME") ;
									quantity += ", " + personData.getString("QUANTITY") ;
								}
							}
						}
						
						if (k == 0){
							out.println("&nbsp;") ;
						}else{
							out.println(itemName + "<br>" + quantity) ;
						}
					
%>
			</td>
<%				}
%>
 			        <td ><%= StringUtil.checkNull(arData.getString("JIABAN1")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("JIABAN2")) %></td>			  
					<td ><%= StringUtil.checkNull(arData.getString("JIABANHEJI")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("YEBAN")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("SHIJIA")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("BINGJIABINGXIU")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("KUANGGONG")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("CHIDAOZAOTUI")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("FANGJIA")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("QITA")) %></td>
					<td >&nbsp;</td>

			</tr>
<%
			
		}
%>
		  
		</table>
		
		<table width="1024">
		  <tr><td colspan="30"></td></tr>
		  <tr><td colspan="30"></td></tr>
		  <tr><td width="5%">备注:</td><td colspan="29">1、“平”，平日加班； “休”，休息日加班； “节”，节假日加班</td></tr>
		  <tr><td width="5%">&nbsp;</td><td colspan="30">2、“病”，病假；“事”，事假；“迟”，迟到(次)；“早”，早退(次)；“旷”，旷工；“哺”，哺乳假；“产”，产假；“检”，产检假；“年”，年休假；“工”，工伤假；“丧”，丧假；“婚”，婚假；“带”，带薪病假</td></tr>
		  <tr><td width="5%">&nbsp;</td><td colspan="30">3、其他假 = 哺乳假+产假+产检假+年休假+工伤假+丧假+婚假+结婚登记假+护理假</td></tr>
		  <tr><td colspan="30"></td></tr>
		  <tr><td colspan="30"></td></tr>
		  <tr><td colspan="30"><b><font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门考勤担当：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 课长：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 部门长：</font></b></td></tr>	
		  <tr><td colspan="30"><b><font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;月&nbsp;&nbsp;日</font></b><b><font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font></b><b><font size="+1">年&nbsp;&nbsp;月&nbsp;&nbsp;日</font></b></td></tr>			
		</table>
	</body>
</html>

