<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap,java.util.*,com.ait.util.StringUtil" %>
<jsp:useBean id="cdc" class="com.ait.web.ChangeDivisionCommand"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"></jsp:useBean>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>帮助信息</title>
</head>
<body>
<% 
List list = new ArrayList();
list=cdc.getHelpInfo(request);
%>
<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
</tr> 
<tr align="center">
<td nowrap="nowrap" COLSPAN = "2" class="info_title_01">使用系统中出现问题，请联系以下人员:</td>
</tr>
<%if(list!=null && list.size()!=0){ %>
<%
	dataMap=(SimpleMap)list.get(0); %>
<tr align="center">
 <td nowrap="nowrap" class="info_title_01">联系人</td>
 <td nowrap="nowrap" class="info_content_01" ><%=dataMap.get("CHINESENAME") %>&nbsp; </td>
</tr> 
<tr align="center">
<td nowrap="nowrap" class="info_title_01">电话</td>
<td nowrap="nowrap" class="info_content_01" ><%=dataMap.get("TEL")  %>&nbsp;</td>
</tr> 
<tr align="center">
<td nowrap="nowrap" class="info_title_01">邮箱</td>
<td nowrap="nowrap" class="info_content_01" ><%=dataMap.get("EMAIL")  %>&nbsp;</td>
</tr>
<%} %>

</table>
</body>
</html>