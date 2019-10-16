<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.kpa.Kpaitem,com.ait.util.StringUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" src="/js/slide_menu.js"></script>
<SCRIPT language="JavaScript" src="/kpa/js/patable.js"></SCRIPT>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../kpa/inc/common_toolbar_all.jsp"%>
			
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
		<%@ include file="../kpa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--计算项目-->
					<ait:message  messageID="display.pay.maintenance.expression.exp" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center"> 
			<td  nowrap class="info_title_01"><!--编号-->
					<ait:message  messageID="display.mutual.no"/></td>
			<td  nowrap class="info_title_01"><!--工资项目ID-->
					<ait:message  messageID="display.pay.maintenance.expression.id" module="pay"/></td>
			<td  nowrap class="info_title_01"><!--奖金项目名称-->
					<ait:message  messageID="alert.pay.prizeItemName" module="pay"/>
					</td>
			<td  nowrap class="info_title_01"><!--描述-->
					<ait:message  messageID="display.mutual.description"/></td>
			<td  nowrap  class="info_title_01"><!--数据类型-->
					<ait:message  messageID="display.mutual.data_type"/></td>
			<td  nowrap  class="info_title_01"><!--计算顺序-->
					<ait:message  messageID="display.pay.maintenance.expression.sequence" module="pay"/></td>
			<td  nowrap class="info_title_01"><!--项目类型-->
					<ait:message  messageID="display.pay.maintenance.expression.visibility" module="pay"/></td>
			<td  nowrap  class="info_title_01"><!--精度-->
					<ait:message  messageID="display.pay.maintenance.expression.precision" module="pay"/></td>
			<td  nowrap  class="info_title_01"><!--进位-->
					<ait:message  messageID="display.pay.maintenance.expression.carry" module="pay"/></td>
		  </tr>
				<c:forEach items="${bonusItemList}" var="vlist" varStatus="">
				  <tr align="center" onclick="band('#F4F1E7','black','${vlist.ITEM_NO}')" > 
					<td height="30" >${vlist.ITEM_NO}&nbsp;</td>
					<td align="center" style="color: #666666;">${vlist.ITEM_ID}&nbsp;</td>
					<td align="center" style="color: #666666;">
						<ait:content enContent="${vlist.ITEM_NAME}" zhContent="${vlist.item_name}" koContent="${vlist.ITEM_KOR_NAME}"/>
						&nbsp;</td>
					<td align="left" style="color: #666666;">${vlist.DESCR}&nbsp;</td>
					<td align="center" style="color: #666666;">
					<ait:content enContent="${vlist.DATATYPE}" zhContent="${vlist.DATATYPE_NAME}" koContent="${vlist.DATATYPE}"/>
					&nbsp;
					</td>
					<td align="center" style="color: #666666;">${vlist.CALCU_ORDER}&nbsp;</td>
					<td align="center" style="color: #666666;">
					<ait:content enContent="${vlist.ITEM_TYPE}" zhContent="${vlist.ITEM_TYPE_NAME}" koContent="${vlist.ITEM_TYPE}"/>
					&nbsp;</td>
					<td align="center" style="color: #666666;">${vlist.PRICISION}&nbsp;</td>
					<td align="center" style="color: #666666;">${vlist.CARRY_BIT}&nbsp;</td>
				  </tr>
				</c:forEach>
		  <tr align="center">
		    <td height="26" colspan="9" class="info_content_01">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0">
			      <tr>
				      <td>
				      <ait:page       
			               link="/paControlServlet"
			               parameters="&operation=pa_bonus_item&menu_code=${param.menu_code}&search=${search}" 
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
			            </td>
			        <form action="/paControlServlet?operation=pa_bonus_item&menu_code=${param.menu_code}" name='search' method="post">
			          <td width="200" class="info_content_01">
			            <input type="text" name="search" class="content" style="width:94px " value="${search}">
			            <ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="Search();" style="cursor:hand" />
			          </td>
			        </form>
			      </tr>
			    </table>
		    </td>
		  </tr>
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
<script language="JavaScript" type="text/JavaScript">
var ID;
ID='';
function Add(){
	location.href="kpa/kpa_bonus_item_add.jsp?menu_code=${param.menu_code}&currentPage=${currentPage}&search="+encodeURIComponent(document.search.search.value);
}
function Update(){
	if (ID!=''){
	location.href="/kpaControlServlet?operation=pa_bonus_item_forUpdate&menu_code=${param.menu_code}&currentPage=${currentPage}&item_no="+ID + "&search="+encodeURIComponent(document.search.search.value);
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
}
function Delete(){
	if (ID!=""){
	//"删除后,相关信息也将被清空!\n\n      确定要删除吗?"
		if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>') )	{
		location.href="/kpaControlServlet?operation=pa_bonus_item_delete&menu_code=${param.menu_code}&currentPage=${currentPage}&item_no="+ID + "&search="+encodeURIComponent(document.search.search.value);
		}
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
}
function Search(){
location.href='/kpaControlServlet?operation=pa_bonus_item&flag=list&currentPage=${currentPage}&menu_code=${param.menu_code}&search='+encodeURIComponent(document.search.search.value);
}
function band(backColor,textColor,i,a_itemName)
{
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;
	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	try{ChangeTextColor(el,textColor);}catch(e){;}
	preEl = el;
	ID=i;
	itemName=a_itemName;
}

</script>
</body>
</html>