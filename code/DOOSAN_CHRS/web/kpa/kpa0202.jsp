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
<SCRIPT language="JavaScript" src="/pa/js/patable.js"></SCRIPT>
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
			<td  nowrap class="info_title_01"><!--工资项目名称-->
					<ait:message  messageID="display.pay.maintenance.expression.name" module="pay"/></td>
			<td  nowrap class="info_title_01"><!--描述-->
					<ait:message  messageID="display.mutual.description"/></td>
			<td  nowrap  class="info_title_01"><!--数据类型-->
					<ait:message  messageID="display.mutual.data_type"/></td>
			<td  nowrap  class="info_title_01"><!--计算顺序-->
					<ait:message  messageID="display.pay.maintenance.expression.sequence" module="pay"/></td>
			<td  nowrap  class="info_title_01"><!--补税计算标示-->
					<ait:message  messageID="display.pay.maintenance.expression.calcflag" module="pay"/></td>		
			<td  nowrap class="info_title_01"><!--项目类型-->
					<ait:message  messageID="display.pay.maintenance.expression.visibility" module="pay"/></td>
			<td  nowrap  class="info_title_01"><!--精度-->
					<ait:message  messageID="display.pay.maintenance.expression.precision" module="pay"/></td>
			<td  nowrap  class="info_title_01"><!--进位-->
					<ait:message  messageID="display.pay.maintenance.expression.carry" module="pay"/></td>
		  </tr>
		<%	Kpaitem paitem =new Kpaitem();
			Vector vlist = new Vector();
			String searchsql="";
			if (!search.equals("")) {
			    searchsql = " where item_name like '%"+func.isoStr(search)+"%' or LOWER(item_en_name) like LOWER('%"+func.isoStr(search)+"%')";
			}
			pc.setRowCount(" kpa_item " + searchsql);
			vlist = paitem.List(searchsql,pc);
			request.setAttribute("vlist",vlist);
			if (vlist.size()!=0){
				int listNo = 1 ;%> 
				<c:forEach items="${vlist}" var="vlist">
				  <tr align="center" onclick="band('#F4F1E7','black','${vlist.pa_item_no}')" > 
					<td height="30" >${vlist.pa_item_no}&nbsp;</td>
					<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.item_id}&nbsp;</td>
					<td align="center" style="color: #666666;">
						<ait:content enContent="${vlist.item_en_name}" zhContent="${vlist.item_name}" koContent="${vlist.item_kor_name}"/>
					&nbsp;</td>
					<td align="left" style="color: #666666;">${vlist.descr}&nbsp;</td>
					<td align="center" style="color: #666666;">
						<ait:content enContent="${vlist.data_type_en_desc}" zhContent="${vlist.data_type_desc}" koContent="${vlist.data_type_kor_desc}"/>
					&nbsp;</td>
					<td align="center" style="color: #666666;">${vlist.calcu_order}&nbsp;</td>
					<td align="center" style="color: #666666;">&nbsp;
						<c:choose>
				         	<c:when test="${vlist.retroactiveRaxCalcMark == 1}">
				         		是
				         	</c:when>
				         	<c:otherwise>
				         		否
				         	</c:otherwise>
				         </c:choose>&nbsp;
					</td>
					<td align="center" style="color: #666666;">
						<ait:content enContent="${vlist.item_type_en_desc}" zhContent="${vlist.item_type_desc}" koContent="${vlist.item_type_kor_desc}"/>&nbsp;</td>
					<td align="center" style="color: #666666;">${vlist.pricision}&nbsp;</td>
					<td align="center" style="color: #666666;">${vlist.carry_bit}&nbsp;</td>
				  </tr>
				</c:forEach>
		  <%}%> 
		  <tr align="center">
		    <td height="26" colspan="10" class="info_content_01">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0">
			      <tr>
			        <td align="center" class="info_content_01"><%@ include file="/inc/page.jsp"%></td>
			        <form action="<%=menu_code%>.jsp?menu_code=<%=menu_code%>" name='search' method="post">
			          <td width="200" class="info_content_01">
			            <input type="text" name="search" class="content" style="width:94px " value="<%=func.isoStr(search)%>">
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
location.href="<%=menu_code%>_a.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>";
}
function Update(){
	if (ID!=''){
	location.href="<%=menu_code%>_m.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>&item_no="+ID;
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
		location.href="<%=menu_code%>_t.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>&flag=del&no="+ID;
		}
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
}
function Search(){
location.href='<%=menu_code%>.jsp?flag=list&bigpage=<%=bigpage%>&strPage=<%=strPage%>&menu_code=<%=menu_code%>&search='+encodeURIComponent(document.search.search.value);
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