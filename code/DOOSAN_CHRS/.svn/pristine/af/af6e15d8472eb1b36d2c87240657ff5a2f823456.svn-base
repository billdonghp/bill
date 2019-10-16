<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<SCRIPT language="JavaScript" src="/pa/js/patable.js"></SCRIPT>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../pa/inc/common_toolbar_all.jsp"%>
			
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
		<form name="form1" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!--查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
					 <td width="20%" class="info_title_01"><!--项目标号,项目名称 -->项目ID或项目名称</td>
			          <td width="25%" class="info_content_00"><input id="key" name="key" type="text" size="15" value="${key}" >
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/></td>
			    </tr>
			</table>
	      </td>
		</tr>
		</table>
		</form>
		<!-- display 3 level menu -->
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--输入项目-->
					<ait:message  messageID="display.pay.maintenance.setting.item_setting" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center"> 
			<td width="8%" nowrap class="info_title_01"><!--编号-->
					<ait:message  messageID="display.mutual.no"/></td>
			<td width="20%" nowrap class="info_title_01"><!--参数ID-->
					<ait:message  messageID="display.pay.maintenance.setting.add.parameter_name" module="pay"/></td>
			<td width="20%" nowrap class="info_title_01"><!--参数名称 -->
					<ait:message  messageID="display.name_chinese" /></td>
			<td width="15%" nowrap class="info_title_01">所属法人</td>
			<td nowrap class="info_title_01"><!--描述-->
				<ait:message  messageID="display.mutual.description"/></td>
			</td>
		  </tr> 
		<c:forEach items="${paParamItimList}" var="vlist">
		  <tr align="center" onclick="HighLightTR('#E7F0EF','black','${vlist.PARAM_NO}')" > 
			<td height="25" align="center" style="color: #666666;" >${vlist.PARAM_NO}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.PARAM_ID}&nbsp;</td>
			<td align="center" style="color: #666666;">${vlist.PARAM_NAME}&nbsp;</td>
			<td align="center" style="color: #666666;"> <c:if test="${vlist.CPNY_NAME eq NULL || vlist.CPNY_NAME eq '' }">公共&nbsp;</c:if>
														<c:if test="${vlist.CPNY_NAME ne ''}">${vlist.CPNY_NAME}&nbsp;</c:if></td>
			<td align="left" style="color: #666666;">${vlist.DESCR}&nbsp;</td>
		  </tr>
		</c:forEach>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(paParamItimList) < 8}">
				<c:forEach var="i" begin="1" end="${8-fn:length(paParamItimList)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		<!-- Page Navigation Start-->
		        <ait:page       
		               link="/paControlServlet"
		               parameters="&operation=paParamList&menu_code=${param.menu_code}&key=${key}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->
		
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
<script language="JavaScript" type="text/JavaScript">
var ID;
ID='';
function Add(){
	location.href="/pa/pa_bonus_param_item_add.jsp?menu_code=${param.menu_code}";
}
function Update(){
	if (ID!=''){
		location.href="/paControlServlet?operation=pa_bonus_param_forUpdate&menu_code=${param.menu_code}&PARAM_NO="+ID;
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
}
function Delete(){
	/*
	if (ID!=""){
	//"删除后,相关信息也将被清空!\n\n      确定要删除吗?"
		if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>') )	{
		location.href="<%=menu_code%>_t.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>&flag=del&no="+ID;
		}
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
	*/
}
function Search(){
	document.form1.action="/paControlServlet?operation=paBonusParamList&menu_code=${param.menu_code}";
	document.form1.submit();
}

</script>
</body>
</html>