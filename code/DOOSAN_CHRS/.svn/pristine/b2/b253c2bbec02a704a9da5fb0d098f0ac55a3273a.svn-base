<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArItem,com.ait.sy.bean.AdminBean"%>
<%@ page import="java.util.*" %>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArItemBean">
</jsp:useBean>
<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session.getAttribute("admin");
	int itemNo=0;
	ArItem info=null;
	if(request.getParameter("no") !=null && request.getParameter("no").length() > 0){
	  itemNo=Integer.parseInt(request.getParameter("no"));
	  info=dao.getArItemInfo(itemNo);
	  request.setAttribute("arItem",info) ;
	}
%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<script language="javascript">

function Save()
{
	if(document.form1.itemId.value.length > 30)
	{
		alert("项目ID长度不能大于30个字符");
		document.form1.itemId.focus() ;
		return ;
	}
	document.form1.action="/arControlServlet?operation=aritemupdate&menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.fireSubmit();
}
</script>
</head>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="/inc/common_toolbar_a.jsp"%>
			
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

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<form name="form1" method="post" action="">
		   <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!-- 考勤项目-->
					<ait:message  messageID="display.att.setting.items.attendance_items" module="ar"/>	</td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		   <tr>
		      <td width="15%" class="info_title_01" align="center">所属法人</td>
		      <td width="75%" class="info_content_00">${arItem.cpnyName}</td>
		    </tr> 
		    <tr>
		      <td width="15%" class="info_title_01" align="center"><!-- 项目ID-->
					<ait:message  messageID="display.att.setting.items.id" module="ar"/></td>
		      <td width="75%" class="info_content_00">
		      <input name="itemId" type="text" size="30" maxlength="29" value="${arItem.itemId}"  required >&nbsp;&nbsp;项目ID长度不能大于30个字符,单词之间以_相连</td>
		    </tr> 
		    <tr>
		      <td width="15%" class="info_title_01"><!-- 全名称-->
					<ait:message  messageID="display.att.setting.items.edit.name_chinese" module="ar"/></td>
		      <td width="75%" class="info_content_00"><input name="itemName" type="text" size="30" maxlength="25" value="${arItem.itemName}" required>&nbsp;<c:if test="${error == 'error'}"> <FONT color="red">添加失败,${arItem.itemName},与其他项目的开始文字重复</FONT> </c:if></td>
		    </tr>
		    <!-- 英文名称-->
		    <!--  
		    <tr>
		      <td class="info_title_01">
					<ait:message  messageID="display.att.setting.items.edit.name_english" module="ar"/></td>
		      <td class="info_content_00"><input name="itemEnName" type="text" size="10" maxlength="20" value="" required /></td>
		    </tr>
		     -->
		     <!-- 韩文名称-->
		    <!-- 
		    <tr>
		      <td class="info_title_01">
					<ait:message  messageID="display.att.setting.items.edit.name_korean" module="ar"/></td>
		      <td class="info_content_00"><input name="itemKorName" type="text" size="10" maxlength="20" value="" /></td>
		    </tr>
		    -->
		    <tr>
		      <td class="info_title_01"><!-- 简称-->
					<ait:message  messageID="display.att.setting.items.abbreviation" module="ar"/></td>
		      <td class="info_content_00"><input name="shortName" type="text" size="30" maxlength="25" value="${arItem.shortName}" required></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 项目组-->
					<ait:message  messageID="display.att.setting.items.group" module="ar"/></td>
		      <td class="info_content_00"><ait:codeClass codeClass="ArItemGroup" name="itempGroupCode" selected="${arItem.itemGroupCode}" /></td>
		    </tr>
		    <tr>
		       <td class="info_title_01"><!-- 活跃-->
					<ait:message  messageID="display.mutual.active"/></td>
		      <td class="info_content_00"><input type="radio" name="activity" value="1" <c:if test="${arItem.activity == 1}"> checked</c:if>  />
		        <!--是-->
					<ait:message  messageID="display.mutual.yes"/>
		        <input type="radio" name="activity" value="0" <c:if test="${arItem.activity == 0}"> checked</c:if>/>
		       <!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 描述-->
					<ait:message  messageID="display.mutual.description"/></td>
		      <td class="info_content_00"><pre><textarea name="description" cols="30" rows="3">${arItem.description}</textarea></pre></td>
		    </tr>
		  </table>
		  <input type="hidden" name="itemNo" value="${arItem.itemNo}">
		</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="10">
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
<ait:xjos />
</html>
