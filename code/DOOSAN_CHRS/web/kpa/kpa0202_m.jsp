<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,com.ait.util.SysCodeParser,com.ait.kpa.Kpaitem"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function Save(){
	var a = new Array();
	//请填写工资项目ID！ 请填写中文名称！请填写英文名称！ 您输入的中文名称字数太长，请在25个字符以内。
	//请填写精度！您输入的精度位数不得多于2个字符。
	a[0] = '<ait:message  messageID="alert.pay.enter_pay_id" module="pay"/>';
	a[1] = '<ait:message  messageID="alert.pay.enter_chinese" module="pay"/>';
	a[2] = '<ait:message  messageID="alert.pay.enter_korea" module="pay"/>';
	a[3] = '<ait:message  messageID="alert.pay.number_length" module="pay"/>';
	a[4] = '<ait:message  messageID="alert.pay.enter_precision" module="pay"/>';
	a[5] = '<ait:message  messageID="alert.pay.precision_length" module="pay"/>';
	if ( CheckForm(a)){
	document.sf.submit();
	}
}
</script>
<script language="JavaScript" src="/kpa/js/pa_item.js"></script>
<body>

<%
String no = request.getParameter("item_no");
Kpaitem paitem = Kpaitem.Detail(no);
request.setAttribute("paitem",paitem);
%>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
			<%@ include file="/kpa/inc/common_toolbar_a.jsp" %>
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
		 <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--计算项目-->
					<ait:message  messageID="display.pay.maintenance.expression.exp" module="pay"/></td>
			</tr>
		</table>
		<table width="100%" height="158" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<form method="post" name="sf" action="<%=menu_code%>_t.jsp?flag=update&bigpage=<%=bigpage%>&strPage=<%=strPage%>&menu_code=<%=menu_code%>&no=<%=no%>" onSubmit="return CheckForm()" >
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--工资项目ID-->
					<ait:message  messageID="display.pay.maintenance.expression.id" module="pay"/><font color="red">*</font> </td>
		<td width="85%" class="info_content_00">
			<input name="item_id" class="box" size="30" maxlength="29" value="${paitem.item_id}">&nbsp;&nbsp;项目ID长度不能大于30个字符,单词之间以_相连</td>
		</tr>
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--工资项目名称-->
					<ait:message  messageID="display.name_chinese" /> <font color="red">*</font></td>
		<td width="85%" class="info_content_00">
			<input name="item_name" class="box" size="30" maxlength="25" value="${paitem.item_name}"></td>
		</tr>
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--工资项目英文名称-->
					<ait:message  messageID="display.name_english" /> </td>
		<td width="85%" class="info_content_00">
			<input name="item_en_name" class="box" size="25" value="${paitem.item_en_name}"></td>
		</tr>
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--工资项目韩文名称-->
					<ait:message  messageID="display.name_korean" /> <font color="red">*</font></td>
		<td width="85%" class="info_content_00">
			<input name="item_kor_name" class="box" size="25" value="${paitem.item_kor_name}"></td>
		</tr>
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--数据类型-->
					<ait:message  messageID="display.mutual.data_type"/> </td>
		<td width="85%" class="info_content_00">
		<select name="datatype">
		<%
			try{
				Vector syscode_vector = SysCodeParser.getCode("DataType");				
				String systype_code = paitem.getDatatype();
				request.setAttribute("syscodevector",syscode_vector);
				request.setAttribute("systypecode",systype_code); %>
				<c:forEach items="${syscodevector}" var="syscodevector">
					<c:choose>
						<c:when test="${syscodevector.code==systypecode}">
							<option value="${syscodevector.code}" selected="selected">
								<ait:content enContent="${syscodevector.enName}" zhContent="${syscodevector.name}" koContent="${syscodevector.korName}"/>
							</option>
						</c:when>
						<c:otherwise>
							<option value="${syscodevector.code}">
								<ait:content enContent="${syscodevector.enName}" zhContent="${syscodevector.name}" koContent="${syscodevector.korName}"/>
							</option>
						</c:otherwise>
					</c:choose>				
				</c:forEach>
		<%	}catch (Exception e){}%>
		  </select></td>
		</tr>
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--项目类型-->
					<ait:message  messageID="display.pay.maintenance.expression.visibility" module="pay"/> </td>
		<td width="85%" class="info_content_00">
		<select name="item_type">
		<%
			try{
				Vector syscode_vector = SysCodeParser.getCode("ItemType");				
				String systype_code = paitem.getItem_type();
				request.setAttribute("syscodevector1",syscode_vector);
				request.setAttribute("systypecode1",systype_code);%>
				<c:forEach items="${syscodevector1}" var="syscodevector">
					<c:choose>
						<c:when test="${syscodevector.code==systypecode1}">
							<option value="${syscodevector.code}" selected="selected">
								<ait:content enContent="${syscodevector.enName}" zhContent="${syscodevector.name}" koContent="${syscodevector.korName}"/>
							</option>
						</c:when>
						<c:otherwise>
							<option value="${syscodevector.code}">
								<ait:content enContent="${syscodevector.enName}" zhContent="${syscodevector.name}" koContent="${syscodevector.korName}"/>
							</option>
						</c:otherwise>
					</c:choose>				
				</c:forEach>
		<%	}catch (Exception e){}%>
		  </select></td>
		</tr>
		<tr>
			<td  height="26" width="15%" class="info_title_01"><!--是否参与补税计算-->
						是否参与补税计算</td>
			<td  width="85%" class="info_content_00">
				<select name="retroactiveRaxCalcMark">
					<c:choose>
				         	<c:when test="${paitem.retroactiveRaxCalcMark == 1}">
				         		<option value="1" selected>是</option>
				         		<option value="0">否</option>
				         	</c:when>
				         	<c:otherwise>
				         		<option value="1">是</option>
				         		<option value="0" selected>否</option>
				         	</c:otherwise>
				    </c:choose>
				</select>
			</td>
		</tr>
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--精度-->
					<ait:message  messageID="display.pay.maintenance.expression.precision" module="pay"/><font color="red">*</font></td>
		<td width="85%" class="info_content_00">
			<input name="pricision" class="box" size="25" value="${paitem.pricision}" maxlength="2"></td>
		</tr>
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--进位-->
					<ait:message  messageID="display.pay.maintenance.expression.carry" module="pay"/></td>
		<td width="85%" class="info_content_00">
			<input name="carry_bit" class="box" size="25" value="${paitem.carry_bit}" maxlength="6"></td>
		</tr>
		<tr>
		<td height="26" width="15%" class="info_title_01"><!--描述-->
					<ait:message  messageID="display.mutual.description"/></td>
		<td width="85%" class="info_content_00">
			<input name="descr" class="box" size="50" value="${paitem.descr}"></td>
		</tr></form>
		</table>

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
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</body>
</html>
