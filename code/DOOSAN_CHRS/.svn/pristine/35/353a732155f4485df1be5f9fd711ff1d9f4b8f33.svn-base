<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.*" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap" %>
<jsp:useBean id="itemDAO" scope="page" class="com.ait.ar.dao.ArItemBean" />
<jsp:useBean id="shiftDAO" scope="page" class="com.ait.ar.dao.ArShift010Bean" />
<%@ include file="../inc/taglibs.jsp"%>
<%
	ArrayList itemList=null;
	ArItem info=null;
	itemList=itemDAO.getItemList();
	request.setAttribute("itemList",itemList);
	List shiftList = shiftDAO.retrieveShiftList();
	request.setAttribute("shiftList",shiftList);
%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="form1" method="post">
<table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr bgcolor="#F5F5F5">
    <td  height="26" width="80" class="info_title_01"><!-- 班次号-->
				<ait:message messageID="display.att.view.individual.code.shift" module="ar"/></td>
    <td width="80" class="info_title_01" nowrap><!-- 班次名称-->
				<ait:message messageID="display.att.view.individual.code.shift_name" module="ar"/></td>
    <td width="150" class="info_title_01"><!-- 班次代码-->
				<ait:message messageID="display.att.view.individual.code.shift_code" module="ar"/></td>
	
  <%if(shiftList!=null){%>
	  <c:forEach items="${shiftList}" var="shiftList">
	  <% // for(int i=0;i<shiftList.size();i++){
	     // SimpleMap map =(SimpleMap)shiftList.get(i);
	  %>
  <tr>
    <td class="info_content_01">${shiftList.SHIFT_NO}</td>
    <td class="info_content_01">
    	<ait:content enContent="${shiftList.SHIFT_EN_NAME}" zhContent="${shiftList.SHIFT_NAME}" koContent="${shiftList.SHIFT_KOR_NAME}"/>
    </td>
    <td class="info_content_01">
    	<ait:content enContent="${shiftList.SHIFT_SHORT_EN_NAME}" zhContent="${shiftList.SHIFT_SHORT_NAME}"/>
    </td>
  </tr>
  <%//} %></c:forEach>
  <%} %>
</table>
<br>
<table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr bgcolor="#F5F5F5">
    <td  height="26" width="80" class="info_title_01"><!-- 项目名称-->
				<ait:message messageID="display.att.view.individual.code.item" module="ar"/></td>
    <td width="80" class="info_title_01" nowrap="nowrap"><!-- 项目代码-->
				<ait:message messageID="display.att.view.individual.code.item_code" module="ar"/></td>
    <td width="150" class="info_title_01"><!-- 项目组-->
				<ait:message messageID="display.att.view.individual.code.item_group" module="ar"/></td>
  </tr>
  <%if(itemList!=null){%>
	  <c:forEach items="${itemList}" var="itemList"> 
   <% //for(int i=0;i<itemList.size();i++){
    //  info=(ArItem)itemList.get(i);
  %>
  <tr>
    <td class="info_content_01">
    	<ait:content enContent="${itemList.itemEnName}" zhContent="${itemList.itemName}" koContent="${itemList.itemKorName}"/>
    </td>
    <td class="info_content_01">${itemList.shortName}</td>
    <td class="info_content_01">
    	<ait:content enContent="${itemList.itemGroupEnName}" zhContent="${itemList.itemGroupName}" koContent="${itemList.itemGroupKorName}"/>
    </td>
  </tr>
  <%//}%></c:forEach>
  <%} %>
</table>
</form>
</body>
</html>
