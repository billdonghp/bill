<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean id="EMSStatisticsExcel" class="java.util.ArrayList" scope="request"/>
<%@ page import="com.ait.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
    String startdate= StringUtil.checkNull(request.getParameter("startdate"));
    String enddate= StringUtil.checkNull(request.getParameter("enddate"));
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=EMSStatisticsExcel.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center" style="FONT-FAMILY:宋体">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="27"><b><font style="FONT-FAMILY:宋体;font-size:14pt;"><%=startdate.substring(0,4)%>年 快件发送情况统计表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="FONT-FAMILY:宋体">
		    <tr align="center" bgcolor="#F5F5F5">   
		      <td nowrap="nowrap" class="info_title_01" rowspan="2">
				部门</td>
			<c:forEach begin="1" end="12" step="1" varStatus="i">
			  <td nowrap="nowrap" class="info_title_01" colspan="2">
				${i.index}月</td>
			</c:forEach>
			 <td nowrap="nowrap" class="info_title_01" colspan="2">
				合计</td>	  	 	     
		    </tr>
		    <tr align="center">
		    <c:forEach begin="1" end="12" step="1" varStatus="i">
			  <td nowrap="nowrap" class="info_title_01">
				件数</td>
			  <td nowrap="nowrap" class="info_title_01">
				费用</td>
			</c:forEach>
			  <td nowrap="nowrap" class="info_title_01">
				件数</td>
			  <td nowrap="nowrap" class="info_title_01">
				费用</td>			    
		    </tr>
		 <c:forEach items="${EMSStatisticsExcel}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DEPTNAME}</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS1}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS1}</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS2}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS2}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS3}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS3}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS4}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS4}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS5}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS5}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS6}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS6}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS7}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS7}</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS8}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS8}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS9}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS9}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS10}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS10}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS11}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS11}</td>	   
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COUNTS12}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.COSTS12}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.SUMCOUNTS}</td>		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.SUMCOSTS}</td>	   	         
			 </tr>	
		</c:forEach>		    	
		</form>		
		 </table>
		</td>
	</tr>
</table>
</body>
</html>