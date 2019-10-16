<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../inc/taglibs.jsp"%>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<div style="margin-left:5px;margin-right:2px;text-align:left;font-family: 宋体;height: 10;color: #FF0000;font-size: 12px;">

			<c:if test="${fn:length(errorList) > 0}">
			<font color="red" size="5"> 未导入信息如下:</font></br>
			<table width="80%" 　border="0" cellpadding="0" cellspacing="1" class="dr_d">
				<tr>
					<td  class="info_title_01" >
<!--					工号-->
					<ait:message messageID="display.mutual.emp_id"></ait:message>
					</td>
					<td  class="info_title_01" >
					银行帐号
					</td>
	
				</tr>
				<c:forEach items="${errorList}" var="errEmp">
				<tr>
					<td class="info_content_01">${errEmp.empId}</td>
					<td class="info_content_01">${errEmp.payBank}</td>
					
				</tr>
				</c:forEach>
			</table>
			</c:if>
<c:if test="${fn:length(errorList) == 0}">
<script>
<%  
if(request.getAttribute("alert") != null) {%>
  alert("<%=request.getAttribute("alert")%>");
<%}%>
<%
if(request.getAttribute("reload") != null) {%>
    <%=request.getAttribute("reload")%>;
<%}%>
<%  
if(request.getAttribute("parent") != null) {%>
  parent.location="/controlServlet?operation=logout";
<%}%>
<%  
if(request.getAttribute("url") != null) {
	out.println("window.location.href=\""+request.getAttribute("url")+"\"");
}
%>
</script>
</c:if>