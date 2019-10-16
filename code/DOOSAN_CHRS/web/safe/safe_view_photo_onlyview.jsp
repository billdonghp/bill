<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<head>
<title>查看图片</title>
<script language="javascript">
function  viewPhoto(address){
window.open(decodeURIComponent(address),"","resizable=yes,scrollbars,dependent,width=700,height=700");
}
</script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="1" >
<c:forEach items="${result}" var="test" varStatus="i">
<tr>
<td  class="info_content_00" nowrap="nowrap">第${i.index+1}张图片</td>
<td  class="info_content_00" nowrap="nowrap"><a href="#" onclick="viewPhoto('${test.fileaddress}')" style="color:red" title="查看图片">${test.viewfilename}</a></td>
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