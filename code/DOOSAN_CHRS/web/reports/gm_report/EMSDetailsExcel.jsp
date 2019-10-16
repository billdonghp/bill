<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean id="EMSDetailsExcelList" class="java.util.ArrayList" scope="request"/>
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
	"attachment; filename=EMSDetailsExcel.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="1" align="center" cellpadding="0"
	cellspacing="0" align="center" style="FONT-FAMILY:宋体">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="14"><b><font style="FONT-FAMILY:宋体;font-size:14pt;"><%=startdate.substring(0,4)%>年<%=startdate.substring(5,7)%>月<%=startdate.substring(8,10)%>日--<%=enddate.substring(0,4)%>年<%=enddate.substring(5,7)%>月<%=enddate.substring(8,10)%>日 快件月结算明细表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="FONT-FAMILY:宋体">
		    <tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01">
				序号</td>
			  <td nowrap="nowrap" class="info_title_01">
				寄件日期</td>    
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
			  <td nowrap="nowrap" class="info_title_01">
				分摊费 </td>
			  <td nowrap="nowrap" class="info_title_01">
				合计 </td>
			   <td nowrap="nowrap" class="info_title_01">
				备注 </td>			  	 	     
		    </tr>
		 <c:forEach items="${EMSDetailsExcelList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${i.index+1}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.MYDATE} </td>
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
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.COSTSHARING} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.SUMCOSTS}</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.NOTE}</td>		            		            
			 </tr>	
		</c:forEach>		    	
		</form>		
		 </table>
		</td>
	</tr>
</table>
</body>
</html>