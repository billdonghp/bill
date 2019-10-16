<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="SuperTopMenu" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>制度查看</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function openmyhref(menuId){
	document.form1.action="/safeControlServlet?operation=rules&content=Jump&menu_code=${param.menu_code}&menuId="+menuId;
	document.form1.submit();
}


function Search(){
	systemFile.location.href =encodeURI("/safeControlServlet?operation=rules&content=SearchSystem&menu_code=${param.menu_code}&systemname="+document.form1.systemName.value);
	//document.form1.submit();
}

function openmyhref(myhrefaddress){
	window.open("../"+myhrefaddress,"",'toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no, ContentType=response.setContentType("application/msexcel")');
}

function selectMenu(systemId){
	systemFile.location.href = "/safeControlServlet?operation=rules&content=Jump&menu_code=${param.menu_code}&systemId="+systemId;
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
			
			<%@ include file="../inc/common_toolbar_search.jsp"%>
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
	          	&nbsp;&nbsp;&nbsp;	          	
	          </td>
	        </tr>
	    </table>
<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">制度查看</td>
			</tr>
		</table>
		
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  
		    <tr height="30">
		    	<td width="5%" class="info_title_01" align="center">
		    		目录名称
		    	</td>
		    	<td width="10%" class="info_title_01">
		    		制度
		    	</td>
		    </tr>
		    
			    <tr align="center">
			    	 <td width="5%" align="left">
				    	 <select name="menuInfo" size="20" style="width: 300px;" onchange="selectMenu(this.value)">
					    	 <c:forEach items="${Menu_information}" var="view" varStatus="i">
								 <option value="${view.systemId}">
									 <c:forEach begin="2" end="${view.LEVEL}" step="1">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 </c:forEach>
									 &nbsp;${view.SYSTEM_NAME}&nbsp;
								 </option>
							  </c:forEach>
				    	 </select>
			    	 </td>
			    	 <td width="15%">
			    	 <iframe width="100%" height="300" marginwidth="0" marginheight="0" frameborder="0" name="systemFile"></iframe>
			    	 </td>
			    </tr>
		    
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