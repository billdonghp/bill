<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<head>
<title>
<c:forEach items="${result}" var="test1" varStatus="i">
${test1.viewfilename}
</c:forEach>
</title>

</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
<c:forEach items="${result}" var="test" varStatus="i">
<tr align="center" bgcolor="#FFFFFF">
<td>
<img src="${test.fileaddress}" alt="${test.viewfilename}" height=600>
</td>
</tr>
</c:forEach>
<c:if test="${resultsize==0}"> 
<tr>
<td  class="info_title_01" nowrap="nowrap">没有相关图片！</td>
</tr>
</c:if>
</table>
</body>
</html>