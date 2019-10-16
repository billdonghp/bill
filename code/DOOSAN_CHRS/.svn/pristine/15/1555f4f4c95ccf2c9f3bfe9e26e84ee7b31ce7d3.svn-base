<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssArShift"%>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean id="essArDAO" class="com.ait.ess.dao.EssArDAO" scope="page" />
<jsp:useBean id="list" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essArShift" class="com.ait.ess.bean.EssArShift" scope="page" />

<%
	list = essArDAO.getEmpShift(StringUtil.checkNull(request.getParameter("empid")), StringUtil.checkNull(request.getParameter("otdate")));
	if (list.size()>0)
		essArShift = (EssArShift) list.get(0);
%>
<html>
<head>
<title>查看员工班次</title>
<link rel="stylesheet" type="text/css" href="/css/default.css">
</head>
<body>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<tr height="30" align="center" bgcolor="#F5F5F5">
<td class="info_title_01"><!-- 员工号 -->
<ait:message messageID="display.mutual.emp_id" module="ess"></ait:message>
</td>
<td class="info_title_01"><!--  姓名-->
  <ait:message messageID="display.mutual.name" module="ess"></ait:message>
</td>
<td class="info_title_01"><!-- 日期 -->
<ait:message messageID="display.mutual.date"  module="ess"></ait:message>
</td>
<td class="info_title_01"><!-- 班次 -->
<ait:message messageID="display.ess.attendance_request.ot_request.shift"  module="ess"></ait:message>
</td>
</tr>
<tr height="30" align="center">

<td><%=StringUtil.checkNull(essArShift.getEmpId()) %>&nbsp;</td>
<td><ait:content enContent='<%=StringUtil.checkNull(essArShift.getChineseNamePinyin()) %>' zhContent='<%=StringUtil.checkNull(essArShift.getChineseName()) %>'></ait:content>&nbsp;</td>
<td><%=StringUtil.checkNull(essArShift.getArDate()) %>&nbsp;</td>
<td><ait:content enContent='<%=StringUtil.checkNull(essArShift.getShiftEnName()) %>' zhContent='<%=StringUtil.checkNull(essArShift.getShiftName()) %>' koContent='<%=StringUtil.checkNull(essArShift.getShiftKorName()) %>'></ait:content>&nbsp;</td>
</tr>
<%for(int i=0;i<list.size();i++) {         
	essArShift = (EssArShift) list.get(i);%>
	<tr height="30" align="center">
	<td colspan="4"><%=essArShift.getFromTime() %>&nbsp;～&nbsp;<%=essArShift.getToTime() %></td>
	</tr>
<%} %>
</table>
</body>
</html>
