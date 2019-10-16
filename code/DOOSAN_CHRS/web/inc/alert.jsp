<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
<title></title>
</head>
<body bgcolor="#FFFFFF" aLink="#000000" link="#000000" text="#000000" topMargin="0" vLink="#000000">
<script>
<%  
if(request.getAttribute("alert") != null) {%>
  alert("<%=request.getAttribute("alert")%>");
<%}%>
<%  
if(request.getAttribute("reload") != null) {%>
    <%=request.getAttribute("reload")%>;
<%} else {%>
	location.replace(encodeURI("<%=request.getAttribute("href")%>"));
<%}%>
</script>
</body>

</html>