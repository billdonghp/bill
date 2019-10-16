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
	window.open("../"+myhrefaddress,"");
}


function Search(){
	document.form1.action="/safeControlServlet?operation=rules&content=SearchSystem&menu_code=${param.menu_code}";
	document.form1.submit();
}

</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_none.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="1" cellspacing="0" cellpadding="10"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
	      	<tr>
	          <td class="info_title_01"><!--  开始日期-->
	          	文件名 
	          </td>
	          <td class="info_content_00">
	          	<input type="text" name="systemName" value=""/>
	          </td>
	          <td class="info_content_00">
	          	<img src="../images/btn/Search.gif" onclick="Search();">
	          </td>
	        </tr>
	    </table>
	    <br>

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
		    --%>
		       </tr>
		    <c:forEach items="${fileview}" var="view" varStatus="i">
			    <tr align="center">
			    	 <td width="15%">
				    	 <span  style="color:red; cursor:pointer;" onClick="openmyhref('${view.FILEPATH}');">
				    		 ${view.FILENAME}
				    	 </span>
			    	 </td>
			    	 <td width="15%">
			    	 	${view.MENU}
			    	 	<input type="hidden" name="menuId" value="${view.MENUID}"/>
			    	 </td>
			    	 <td width="15%">
			    	 	${view.CREATE_DATE}
			    	 </td>
			    	 <td width="15%">
			    	 	${view.IMPLEMENTATION_DATE}
			    	 </td><%--
			    	 <td width="15%">
			    	 	${view.UPDATE_DATE}
			    	 </td>
			    --%></tr>
		    </c:forEach>
		    <tr align="center"></tr>
		 </table>
		</form>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</body>
</html>