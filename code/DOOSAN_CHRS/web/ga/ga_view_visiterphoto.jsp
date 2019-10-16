<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<head>
<title>查看扫描文件</title>
<script language="javascript">
function  viewPhoto(address){
//alert(address);
window.open(decodeURIComponent(address),"","resizable=yes,scrollbars,dependent,width=700,height=700");
}
</script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
<c:forEach items="${result}" var="test" varStatus="i">
<tr>
<td  class="info_content_00" nowrap="nowrap">第${i.index+1}张图片</td>
<td  class="info_content_00" nowrap="nowrap"><span onclick="viewPhoto('${test.fileaddress}')" style="color:red;cursor:pointer;" title="查看图片">${test.viewfilename}</span></td>
<td class="info_content_00" nowrap="nowrap"><a href="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=deletePhoto&filename=${test.filename}&documentno=${test.documentno}"><img src='/images/btn/Delete.gif'  alt='删除该照片' align='absmiddle'></a></td>
</tr>
</c:forEach>
<c:if test="${resultsize==0}"> 
<tr>
<td  class="info_title_01" nowrap="nowrap">没有扫描文件！</td>
</tr>
</c:if>
</table>
</body>
</html>