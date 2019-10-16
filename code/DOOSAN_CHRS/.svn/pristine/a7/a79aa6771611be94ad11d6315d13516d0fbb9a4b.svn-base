<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.ar.bean.ArItem"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArItemBean">
</jsp:useBean>
<jsp:useBean id="daolist" scope="page" class="com.ait.ar.dao.ArbcdaBean">
</jsp:useBean>
<%
	ArrayList list = null;
	ArItem info = null;
	list = dao.getItemList();
	request.setAttribute("list",list);
	int itemNo = 0;
	ArItem infob = null;
	ArrayList listb = null;
	String check = null;
	/*删除未正确添加遗留的session*/
	if (session.getAttribute("vector") != null) {
		session.removeAttribute("vector");
	}
	if (session.getAttribute("aritem") != null) {
		session.removeAttribute("aritem");
	}
	if (request.getParameter("itemNo_") != null) {
		itemNo = Integer.parseInt(request.getParameter("itemNo_"));
		listb = daolist.getList(itemNo);
	}
	request.setAttribute("itemNo",itemNo);
	request.setAttribute("listb",listb);
%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">

<script language="javascript">
function band(backColor,textColor,i,g,t,p)
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
	//try{ChangeTextColor(el,textColor);}catch(e){;}
	preEl = el;
	document.form1.hitemNo.value=i;
    document.form1.hgroupNo.value=g;
    document.form1.tablename.value=t;
    document.form1.hParamNo.value=p;
}

function ChangeTextColor(a_obj,a_color)
{
	for (i=0;i<a_obj.cells.length;i++){
	a_obj.cells(i).style.color=a_color;
	}
}
function showList(id)
{
	if(id=="")
	return;
        document.form1.action="arbcdalist.jsp?menu_code=<%=request.getParameter("menu_code")%>&itemNo_="+id;
        document.form1.submit();
}
function Update()
{
	if(document.form1.itemNo.value==null||document.form1.hitemNo.value==0||document.form1.hgroupNo.value==0||document.form1.tablename.value==0||document.form1.hParamNo.value==0)
	{// "请选择修改项目"
	    alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
		return;
	}

	var groupNo = document.form1.hgroupNo.value;
	var itemNo = document.form1.itemNo.value;
	var paramNo = document.form1.hParamNo.value ;
	document.form1.action="aritem_param_m.jsp?itemNo_="+itemNo+"&menu_code=<%=request.getParameter("menu_code")%>&groupNo="+groupNo + "&paramNo=" + paramNo;
	document.form1.submit();
}
function Delete()
{
	if(document.form1.hitemNo.value==0||document.form1.hgroupNo.value==0||document.form1.tablename.value==0)
	{//"请选择删除项目"
	    alert('<ait:message  messageID="alert.att.select_item_deleted" module="ar"/>');
		return;
	}//"确定要删除吗?"
	  if(!confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>'))
	{
		return;
	}

	var itemNo=document.form1.hitemNo.value;
	var groupNo=document.form1.hgroupNo.value;
	var paramNo = document.form1.hParamNo.value ;
	document.form1.action="/ar/aritem_param_t.jsp?operation=DELETE&menu_code=<%=request.getParameter("menu_code")%>&itemNo="+itemNo+"&ar_group_No="+groupNo + "&paramNo=" + paramNo;
	document.form1.submit();

}

function Add()
{
	if(isNaN(document.form1.itemNo.value))
	{//"请先选择考勤项目"
		alert('<ait:message  messageID="alert.att.select_attendance_item" module="ar"/>');
		return;
	}

	var itemNo=document.form1.itemNo.value;
	document.form1.action="aritem_param_a.jsp?itemNo_="+itemNo+"&menu_code=<%=request.getParameter("menu_code")%>&bc=1";
	document.form1.submit();
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
		<%@ include file="../inc/common_toolbar_all.jsp"%>
		
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
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
		<tr>
			<td>
			<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
				<tr>
					<td width="15%" class="info_title_01"><!-- 考勤项目-->
					<ait:message  messageID="display.att.setting.parameter.attendance_items" module="ar"/> </td>
					<td width="75%" class="info_content_00">
						<select name="itemNo" onchange="showList(this.value)">
							<option value=""><!-- 全部-->
					<ait:message  messageID="display.mutual.all"/></option>							
							<c:forEach items="${list}" var="list">
								<c:choose>
									<c:when test="${list.itemNo==itemNo}">
										<option value="${list.itemNo}" selected="selected">
											<ait:content enContent="${list.itemEnName}" zhContent="${list.itemName}" koContent="${list.itemKorName}"/>
										</option>
									</c:when>
									<c:otherwise>
										<option value="${list.itemNo}">
											<ait:content enContent="${list.itemEnName}" zhContent="${list.itemName}" koContent="${list.itemKorName}"/>
										</option>									
									</c:otherwise>
								</c:choose>							
							</c:forEach>						
						</select>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%> 
		
		<!-- display content -->
		<br>
		<form name="form1" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 项目参数-->
					<ait:message  messageID="display.att.setting.parameter.parameter" module="ar"/></td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="10"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr bgcolor="#F5F5F5">
			    <td class="info_title_01" nowrap="nowrap">所属法人</td>
			    <td class="info_title_01" nowrap="nowrap">员工区分名称</td>
			    <td class="info_title_01" nowrap="nowrap">工资类型</td>
			    <td class="info_title_01" nowrap="nowrap">班次名称</td>
				<td class="info_title_01" nowrap="nowrap"><!-- 组名称-->
					<ait:message  messageID="display.att.setting.parameter.team" module="ar"/></td>
				<td class="info_title_01" nowrap="nowrap"><!-- 是否参考刷卡-->
					<ait:message  messageID="display.att.setting.parameter.refer_to_punchcard" module="ar"/></td>
				<td class="info_title_01" nowrap="nowrap"><!-- 是否参考申请项-->
					<ait:message  messageID="display.att.setting.parameter.refer_to_request" module="ar"/></td>
				<td class="info_title_01" nowrap="nowrap"><!-- 有效日期类型-->
					<ait:message  messageID="display.att.setting.parameter.time_range" module="ar"/></td>
				<td class="info_title_01" nowrap="nowrap"><!-- 活跃性-->
					<ait:message  messageID="display.mutual.active"/></td>
			</tr>
			<c:forEach items="${listb}" var="listb">			
			<tr	onClick="band('#E7F0EF','black','${listb.itemNo}','${listb.ar_group_no}','ar_item_card','${listb.arParamNo}')" nowrap="nowrap">
			     <td align="center" style="color: #666666;">${listb.cpnyName}</td>
				<td align="center" style="color: #666666;">${listb.stat_type}</td>
				<td align="center" style="color: #666666;">${listb.wages_type}</td>
				<td align="center" style="color: #666666;">
				<c:choose>
					<c:when test="${listb.shiftName == null}">
						<!-- 默认-->
					<ait:message  messageID="display.mutual.default"/>
					</c:when>
					<c:otherwise>
					${listb.shiftName}
					</c:otherwise>
				</c:choose>		
				</td>
				<td align="center" style="color: #666666;">
				<c:choose>
					<c:when test="${listb.ar_group_no_name == null}">
						<!-- 默认-->
					<ait:message  messageID="display.mutual.default"/>
					</c:when>
					<c:otherwise>
					<ait:content enContent="${listb.ar_group_no_en_name }"  zhContent="${listb.ar_group_no_name }"></ait:content>
					</c:otherwise>
				</c:choose>				
				</td>
				<td align="center" style="color: #666666;">
				<c:choose>
					<c:when test="${listb.card_flag == '0'}">
						<!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/>
					</c:when>
					<c:otherwise>
						<!-- 是-->
					<ait:message  messageID="display.mutual.yes"/>
					</c:otherwise>
				</c:choose>
				</td>
				<td align="center" style="color: #666666;">
				<c:choose>
					<c:when test="${listb.apply_flag == '0'}">
						<!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/>
					</c:when>
					<c:otherwise>
						<!-- 是-->
					<ait:message  messageID="display.mutual.yes"/>
					</c:otherwise>
				</c:choose>				
				</td>
				<td align="center" style="color: #666666;">
				<c:forTokens items="${listb.date_type}" delims="," var="token">
					<c:if test="${token=='1'}"><!-- 平常-->
					<ait:message  messageID="display.mutual.workday"/></c:if>
					<c:if test="${token=='2'}"><!-- 周末-->
					<ait:message  messageID="display.mutual.weekend"/></c:if>
					<c:if test="${token=='3'}"><!-- 节假日-->
					<ait:message  messageID="display.mutual.holiday"/></c:if>		
				</c:forTokens>		
				</td>
				<td align="center" style="color: #666666;"><img src="/images/a_${listb.activity}.gif"></td>
			</tr>
			</c:forEach>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:forEach var="i" begin="1" end="6"
				step="1">
				<tr>
					<td class="info_content_01" height="30"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="hitemNo" value="0" /> 
		<input type="hidden" name="hgroupNo" value="0" /> 
		<input type="hidden" name="tablename" value="0" />
		<input type="hidden" name="hParamNo" value="0" /> 
		<input type="hidden" name="itemNo" value="<%=request.getParameter("itemNo_") %>" />
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
</html>
