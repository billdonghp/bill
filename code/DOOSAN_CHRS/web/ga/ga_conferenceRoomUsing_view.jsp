<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap,java.util.*,com.ait.ga.servlet.ConferenceRoomCommand,com.ait.util.StringUtil,com.ait.sy.bean.AdminBean" %>
<jsp:useBean id="crc" class="com.ait.ga.servlet.ConferenceRoomCommand"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"></jsp:useBean>

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>会议室使用情况</title>
</head>
<body>
<% 
List list = new ArrayList();
list=crc.getAllUsingConfirmConferenceRoom(request);
%>
<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
 <tr align="center" bgcolor="#00CC00">
    <td nowrap="nowrap" class="info_title_01">
				所属法人</td>
    <td nowrap="nowrap" class="info_title_01">
				会议室</td>
	<td nowrap="nowrap" class="info_title_01">
				时间段</td>
    <td nowrap="nowrap" class="info_title_01">
				预订部门</td>
	 <td nowrap="nowrap" class="info_title_01">
				预定人员</td> 			   
	<td nowrap="nowrap" class="info_title_01">
				会议主题</td>

</tr>
<%if(list!=null && list.size()!=0){ %>
<%for(int i=0;i<list.size();i++){ 
	dataMap=(SimpleMap)list.get(i); %>
<tr align="center">
 <td nowrap="nowrap" class="info_content_01" ><%=dataMap.get("CPNY_LOCATION") %>&nbsp; </td>
 <td nowrap="nowrap" class="info_content_01" ><%=dataMap.get("ROOMNAME") %>&nbsp; </td>
 <td nowrap="nowrap" class="info_content_01" ><%=dataMap.get("FROMTIME")  %> 至 <%=dataMap.get("ENDTIME")  %> </td>
 <td nowrap="nowrap" class="info_content_01" ><%=dataMap.get("DEPTNAME")  %>&nbsp;</td>
 <td nowrap="nowrap" class="info_content_01" ><%=dataMap.get("CHINESENAME")  %>&nbsp;</td>
 <td nowrap="nowrap" class="info_content_01" ><%=StringUtil.checkNull(dataMap.get("PURPOSEOFUSE"))%>&nbsp;</td>
</tr>
<%} %>
<%} %>
</table>
</body>
</html>