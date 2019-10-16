<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="../inc/meta.jsp" %>
<%@ include file="../inc/taglibs.jsp"%>
<head>
<title>图片查看</title>
<script>

</script>
<script language="javascript">
function Delete(fileName){
///alert(fileName);
 if(confirm("确定删除该图片？")){	
	document.form1.action="/"+$('control').value+"?operation="+$('operation').value+"&content="+$('content').value+"&menu_code=${param.menu_code}&key="+$('key').value+"&fileName="+fileName;
	document.form1.submit();	
 }

}

</script>
</head>

<body>
<form name="form1" method="post" action="">
<input type="hidden" name="key" value="${param.key}">
<input type="hidden" name="control" value="${param.control}">
<input type="hidden" name="operation" value="${param.operation}">
<input type="hidden" name="content" value="${param.content}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<c:forEach items="${list}" var="test" varStatus="i">
	<tr align="left">
	<td><img src="${test.PATHADDRESS}">&nbsp;&nbsp;
	<c:if test="${param.isDelete==1}">
	<img src='/images/btn/Delete_little.gif' width='25' height='25' alt='删除该行' align="bottom" onclick="Delete('${test.FILENAME}')"></td>
	</c:if>
	</tr>	
</c:forEach>	
<c:if test="${fn:length(list)==0}">
	<tr align="center">
	<td><font color="red">没有相关图片！</font></td>
	</tr>	
</c:if>	
</table>
</form>
</body>
</html>