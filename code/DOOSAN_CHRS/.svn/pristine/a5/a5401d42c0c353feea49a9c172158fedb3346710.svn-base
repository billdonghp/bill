<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
	
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>人事信息&gt;附加信息</title>
</head>
<body>
<form name="form1" method="post">
<%@ include file="./inc/esstoolbar_null.jsp"%>
<%@ include file="../ess/ess_view_basic.jsp"%>
<%@ include file="./inc/ess_view_toolbar.jsp"%>


<table width="100%" border="0" cellpadding="0">
  <tr>
    <td class="title1">附加信息</td>
  </tr>
  <tr height="135">
    <td>
	<div style="overflow:auto; width:100%; height:125;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
      <tr style="position: relative; top: expression(this.offsetParent.scrollTop);">
        <td height="30" class="info_title_01" nowrap>发生日期</td>
        <td class="info_title_01" nowrap>信息类型</td>
        <td class="info_title_01" nowrap>详细内容</td>
        <td class="info_title_01" nowrap>登记者</td>
      </tr>
        <c:forEach items="${appendInfoList}" var="oneResult">
        <tr>
          <td class="info_content_01" height="30" nowrap>${oneResult.eventDate}</td>
          <td class="info_content_01" nowrap>${oneResult.infoType}</td>
          <td class="info_content_01" nowrap>${oneResult.detatls}</td>
          <td class="info_content_01" nowrap>${oneResult.register}</td>
        </tr>
        </c:forEach>
        <c:if test="${appendInfoCnt<3}">
		 <c:forEach var="i" begin="1" end="${3-appendInfoCnt}" step="1">
	        <tr>
		        <td class="info_content_01" height="30" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
	        </tr>
         </c:forEach>
        </c:if>
    </table>
	</div></td>
  </tr>
  <tr height="1">
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td class="title1">离职信息</td>
  </tr>
  <tr height="135">
    <td>
	<div style="overflow:auto; width:100%; height:125;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
      <tr style="position: relative; top: expression(this.offsetParent.scrollTop);">
        <td width="12%" height="30" nowrap class="info_title_01">人事令号</td>
        <td width="14%" nowrap class="info_title_01">离职类型</td>
        <td width="15%" nowrap class="info_title_01">离职原因</td>
        <td width="15%" nowrap class="info_title_01">离职日期</td>
        <td width="29%" nowrap class="info_title_01">离职事由</td>
        <td width="15%" nowrap class="info_title_01">跳槽公社</td>
      </tr>
       <c:forEach items="${resignationList}" var="oneResult">
        <tr>
          <td class="info_content_01" height="30" nowrap>${oneResult.transNo}</td>
          <td class="info_content_01" nowrap>${oneResult.resignType}</td>
          <td class="info_content_01" nowrap>${oneResult.resignReason}</td>
          <td class="info_content_01" nowrap>${oneResult.resignDate}</td>
          <td class="info_content_01" nowrap>${oneResult.resignDesc}</td>
          <td class="info_content_01" nowrap>${oneResult.newCompany}</td>
        </tr>
        </c:forEach>
        <c:if test="${resignationCnt<3}">
		 <c:forEach var="i" begin="1" end="${3-resignationCnt}" step="1">
	        <tr>
		        <td class="info_content_01" height="30" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
        		<td class="info_content_01" nowrap></td>
	        </tr>
         </c:forEach>
        </c:if>
    </table>
	</div></td>
  </tr>
</table>
</form>
</body>
</html>
