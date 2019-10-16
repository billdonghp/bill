<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script language="javascript">
function Save()
{ 
var msg='<ait:message  messageID="alert.att.select_card" module="ar"/>';
	if (document.form1.CARD_NO.value == '' )
	{
		alert(msg) ;           
		document.form1.CARD_NO.focus() ;
		return ;
	}
	
	document.form1.action="/arControlServlet?operation=insertVisitCard&menu_code=${param.menu_code}";
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
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
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
					<td align="left" class="title1" colspan="10"><!--访问关系-->
					<ait:message  messageID="display.att.maintenance.visit.visitor_card" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01" align="center"><!-- 卡号-->
					<ait:message  messageID="display.mutual.card_no"/></td>
		      <td width="75%" class="info_content_00">
		      <select name="CARD_NO">
		      	<option value="">Please select</option>
		      	<c:forEach items="${result}" var="result">
		      		<option value="${result.ID}">${result.ID}</option>
		      	</c:forEach>		      
		      </select>
		      <!--<ait:select name="CARD_NO" dataListName="result"/>-->
		      </td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01" align="center"><!-- 访问者名称-->
					<ait:message  messageID="display.att.maintenance.visit.name" module="ar"/></td>
		      <td width="75%" class="info_content_00"><ait:codeClass name="NAME" codeClass="VisitCpyName" /></td>
		    </tr>
		     <tr>
		      <td width="15%" class="info_title_01" align="center"><!-- 负责人-->
					<ait:message  messageID="display.att.maintenance.visit.principal" module="ar"/></td>			  		            
		      <td width="75%" class="info_content_00">
		      <input type="text" size="10" maxlength="10" name="PRINCIPAL" required />
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01" align="center"><!-- 访问人数 -->
					<ait:message  messageID="display.att.maintenance.visit.people" module="ar"/>
		      <td width="75%" class="info_content_00"><input type="text" size="10" maxlength="10" name="VISITOR_AMONT" required numeric/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 开始日期-->
					<ait:message  messageID="display.mutual.start_date"/></td>
		      <td class="info_content_00">
		      	<input type="text" size="10" maxlength="10" name="FROM_DATE" onClick="setday(this);" required/>&nbsp;
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 结束日期-->
					<ait:message  messageID="display.mutual.end_date"/></td>
		      <td class="info_content_00">
		      	<input type="text" size="10" maxlength="10" name="TO_DATE" onClick="setday(this);" />&nbsp;
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 备注-->
					<ait:message  messageID="display.mutual.notes"/></td>
		      <td class="info_content_00"><textarea name="REMARK" cols="30" rows="3"></textarea></td>
		    </tr>
		  </table>
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
