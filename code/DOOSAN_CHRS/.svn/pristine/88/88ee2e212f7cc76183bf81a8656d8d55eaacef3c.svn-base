<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="fileview" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>制度查看</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
 
function openmyhref(myhrefaddress){
	var patrn1 = /.(ppt)$/;
	if(patrn1.test(myhrefaddress)){
	window.open("../"+myhrefaddress,"",'toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no, ContentType=response.setContentType("application/msexcel")');
	}else{
	window.open("../"+myhrefaddress,"",'toolbar=yes, menubar=no, scrollbars=no, resizable=yes,location=no, status=no, ContentType=response.setContentType("application/msexcel")');
	}
}


</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">制度查看</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			    <tr height="30">
		    	<td width="10%" class="info_title_01">
		    		文件名
		    	</td>
		    	<td width="10%" class="info_title_01">
		    		所在目录
		    	</td>
		    	<td width="10%" class="info_title_01">
		    		发布时间
		    	</td>
		    	<td width="10%" class="info_title_01">
		    		实施时间
		    	</td>
		    	<%--<td width="10%" class="info_title_01">
		    		修订时间
		    	</td>
		    --%></tr>
		    <c:forEach items="${fileview}" var="view" varStatus="i">
			    <tr align="center">
			    	 <td width="15%" align="left">
				    	 <span  style="color:red; cursor:pointer;" onClick="openmyhref('${view.FILEPATH}');">
				    		&nbsp;&nbsp;&nbsp;&nbsp; ${view.FILENAME}&nbsp;
				    	 </span>
			    	 </td>
			    	 <td width="15%">
			    	 	${view.MENU}&nbsp;
			    	 	<input type="hidden" name="menuId" value="${view.MENUID}"/>
			    	 </td>
			    	 <td width="15%">
			    	 	${view.CREATE_DATE}&nbsp;
			    	 </td>
			    	 <td width="15%">
			    	 	${view.IMPLEMENTATION_DATE}&nbsp;
			    	 </td>
			    	 <%--<td width="15%">
			    	 	${view.UPDATE_DATE}&nbsp;
			    	 </td>
			    --%></tr>
		    </c:forEach>
		    <tr align="center"></tr>
		 </table>
		</form>
		
</body>
</html>