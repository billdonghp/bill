<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean id="viewRoomInfoList" class="java.util.ArrayList" scope="request"/>
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
	"attachment; filename=ConferenceRoomReport.xls");
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
			<!--<tr align="center">
				<td align="center" colspan="13"><b>申请信息</b></td>
				<td align="center" colspan="2"><b>调整前信息</b></td>
			</tr>
			--><tr align="center" bgcolor="#F5F5F5">
	<tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_title_01">
				开始日期</td>
		      <td nowrap="nowrap" class="info_title_01">
				开始时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				结束日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				结束时间</td>
              <td nowrap="nowrap" class="info_title_01">
				申请部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
		      <td nowrap="nowrap" class="info_title_01">
				与会人员</td>
		      <td nowrap="nowrap" class="info_title_01">
				与会领导</td>
		      <td nowrap="nowrap" class="info_title_01">
				与会人数</td>
		      <td nowrap="nowrap" class="info_title_01">
				会议室 </td>	    
		      <td nowrap="nowrap" class="info_title_01">
				设备 </td>
			  <td width="10%" align="center" class="info_title_01">
				会议主题</td>
      		  <!--<td align="center" class="info_title_01" style="font:bold">
				调整前时间</td>
			  <td align="center" class="info_title_01" style="font:bold">
				调整前会议室</td>	
          --></tr>	
           <c:forEach items="${viewRoomInfoList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.CONFERENCENO}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.BOOKDATE}<%--<br>
		     		 <c:choose>
						<c:when test="${varTest.BOOKDATE!=varTest.BEFORE_BOOKDATE}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKDATE}</font>
						</c:when>
					</c:choose>
		      --%></td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.BOOKSTARTTIME}
		      		<%--<c:choose>
						<c:when test="${varTest.BOOKSTARTTIME!=varTest.BEFORE_BOOKSTARTTIME}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKSTARTTIME}</font>
						</c:when>
					</c:choose>
		      --%></td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.ENDDATE}
		      		<%--<c:choose>
						<c:when test="${varTest.BOOKSTARTTIME!=varTest.BEFORE_BOOKSTARTTIME}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKSTARTTIME}</font>
						</c:when>
					</c:choose>${varTest.ENDDATE}
		      --%></td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.BOOKENDTIME}
		      		<%--<c:choose>
						<c:when test="${varTest.BOOKENDTIME!=varTest.BEFORE_BOOKENDTIME}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKENDTIME}</font>
						</c:when>
					</c:choose>
		      --%></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CHINESENAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.PEOPLECLASS}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.PEOPLECLASS_UP} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.BOOKNUMBER} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.ROOMNAME}
		      						
					<%--<c:choose>
						<c:when test="${varTest.CONFERENCEROOM!=varTest.BEFORE_CONFERENCEROOM}">
							<br>原信息：<font color="red">${varTest.BEFORE_ROOMNAME}</font>
						</c:when>
					</c:choose>
								
			  --%></td>
			  <td nowrap="nowrap" class="info_content_00">${varTest.EQUIPS}</td>
			  <td width="10%" class="info_content_00" align="center">${varTest.PURPOSEOFUSE}</td>
			  <!--<td nowrap="nowrap" class="info_content_00">
					<c:choose>
						<c:when test="${varTest.BOOKDATE!=varTest.BEFORE_BOOKDATE or varTest.BOOKSTARTTIME!=varTest.BEFORE_BOOKSTARTTIME or varTest.BOOKENDTIME!=varTest.BEFORE_BOOKENDTIME}">
							<font color="red">${varTest.BEFORE_BOOKDATE} ${varTest.BEFORE_BOOKSTARTTIME} 至 ${varTest.BEFORE_BOOKENDTIME}</font>
						</c:when>
					</c:choose>
			  </td>
			  <td width="10%" class="info_content_00" align="center">
			  		<c:choose>
						<c:when test="${varTest.ROOMNAME!=varTest.BEFORE_ROOMNAME}">
							<font color="red">${varTest.BEFORE_ROOMNAME}</font>
						</c:when>
					</c:choose>
			  </td>
		    --></tr>
		    
		   </c:forEach>
		  </table>
		</td>
	</tr>
</table>
</body>
</html>