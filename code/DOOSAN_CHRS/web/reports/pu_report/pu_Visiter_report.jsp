<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean id="allVisiterManagementInformation" class="java.util.ArrayList" scope="request"/>
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
	"attachment; filename=VisiterReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="15"><b><font size="+2">来访人员统计报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
<!--			  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
			  <td nowrap="nowrap" class="info_title_01" style="width: 60px">
				单位</td>
		      <td nowrap="nowrap" class="info_title_01">
				职务</td>-->
		      <td nowrap="nowrap" class="info_title_01">
				申请人</td>
			  <td nowrap="nowrap" class="info_title_01" style="width: 100px">
				来访日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				到达时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				离开时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				访问单位</td>
			  <td nowrap="nowrap" class="info_title_01">
				主要客人姓名/职务</td>
			  <td nowrap="nowrap" class="info_title_01">
				访问人数</td>
			  <td nowrap="nowrap" class="info_title_01">
				访问目的</td>
			  <td nowrap="nowrap" class="info_title_01">
				接待部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				接待人员</td>
		    </tr>
		    <c:forEach items="${allVisiterManagementInformation}" var="all" varStatus="i">
			    <tr align="center">
			    	<td align="center">
			    		&nbsp;${all.GA_VISITER_APPLY_ID}
				      </td>
<!--				      <td align="center">
				      &nbsp;${all.VISITER_NAME}
				      </td>
				      <td align="center" >
				      		&nbsp;${all.VISITER_COMPANY}
				      </td>
				      <td align="center">
				      		&nbsp;${all.VISITER_DUTY}
				      </td> -->
				      <td align="center">
				      		&nbsp;${all.CHINESENAME1}
				      </td>
				       <td align="center" class="info_content_01">
				      		&nbsp;${all.VISITER_DATE}
				      </td>
				      <td align="center" class="info_content_01">
				      		&nbsp;${all.VISITER_COME_TIME}
				      </td>
				      <td align="center" class="info_content_01">
				      		&nbsp;${all.VISITER_END_TIME}
				      </td>
				      <td align="center">
				      		 &nbsp;${all.VISITER_COMPANY}
				      </td>
				      <td align="center">
				      		${all.VISITER_NAME}
				      </td>
				      <td align="center">
				      		${all.VISIT_COUNT}
				      </td>
				      <td align="center">
				      		&nbsp;${all.VISITER_OBJECTIVE}
				      </td>
				     
				      <td align="center" >
				      		&nbsp; ${all.DEPTNAME}
				      </td>
				      <td align="center" >
				      		&nbsp; ${all.PLAY_APPLYCHINESENAME}
				      </td>
			    </tr>
		    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		</td>
	</tr>
</table>
</body>
</html>