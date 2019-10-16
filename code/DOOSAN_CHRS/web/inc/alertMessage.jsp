<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div style="margin-left:5px;margin-right:2px;text-align:left;font-family: 宋体;height: 10;color: #FF0000;font-size: 12px;">
<%  
if(request.getAttribute("msg") != null) {%>
  <%=request.getAttribute("msg")%>
<%}%>
</div>
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
if(request.getAttribute("url") != null) {
	out.println("window.location.href=\""+request.getAttribute("url")+"\"");
}
%>
</script>
<%  
if(request.getAttribute("lCode") != null) {%>
  <%=request.getAttribute("lCode")%>
<%}%>