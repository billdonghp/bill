<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean id="expressConfirmInfoList" class="java.util.ArrayList" scope="request"/>
<%@ page import="com.ait.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=ExpressManagerReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="13"><b><font size="+2">历史记录查询报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01">
				序号</td>
			  <td nowrap="nowrap" class="info_title_01">
				寄件日期</td> 
			 <td nowrap="nowrap" class="info_title_01">
				申请日期</td>    
		      <td nowrap="nowrap" class="info_title_01">
				申请部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
			  <td nowrap="nowrap" class="info_title_01">
				发件人</td>
			  <td nowrap="nowrap" class="info_title_01">
				寄达城市</td>
		      <td nowrap="nowrap" class="info_title_01">
				收件单位全称</td>
			  <td nowrap="nowrap" class="info_title_01">
				收件人</td>	
			  <td nowrap="nowrap" class="info_title_01">
				快件种类</td>	
			 <td nowrap="nowrap" class="info_title_01">
				邮件号</td>
		      <td nowrap="nowrap" class="info_title_01">
				邮件内容 </td>
			  <td nowrap="nowrap" class="info_title_01">
				邮件费 </td>		  	 	     
		    </tr>
		 <c:forEach items="${expressConfirmInfoList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${i.index+1}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.MYDATE} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.MYAPPLYDATE} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.APPLYOR}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CHINESENAME}</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CITYSENDTO}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.POSTADDRESS}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.SENDTOPERSON} </td>	
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CODE_NAME} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.POSTNUMBER} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.POSTDESCRIPTION} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.COSTS}</td>
		            
			 </tr>	
		</c:forEach>
		    <tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01" colspan="12">
				总     计</td>
			  <td nowrap="nowrap" class="info_title_01">
				${sumCost }</td>    
		      	  	 	     
		    </tr>		
		</form>		
		 </table>
		</td>
	</tr>
</table>
</body>
</html>