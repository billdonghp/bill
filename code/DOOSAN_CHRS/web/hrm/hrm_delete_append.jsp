<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;附加信息</title>
</head>
<script language="javascript" src="../js/hr_delete_append.js"></script>
<SCRIPT type="text/javascript">

function Save(){

	if (!CheckForm('<ait:message  messageID="alert.emp.staff_info.basic_info.please_select_item_to_delete" module="hrm"/>')){
		return;
	}
	//"确认要删除信息吗"
	if(confirm('<ait:message  messageID="alert.mutual.are_you_sure_you_want_to_delete_this_message"/>')) {
	
		document.form1.action="hrmControlServlet?menu_code=hr0110&operation=deleteAppendInfo&empID=<c:out value='${empID}'/>";
		document.form1.submit();
	}
 }

</SCRIPT>
<body>
<form name="form1" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<%@ include file="../inc/hr_deletetoolbar.jsp"%></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		<%@ include file="../hrm/hrm_add_basic.jsp"%> 
		
		<!-- display content -->
		<table width="100%" border="0" cellpadding="0">
			<tr>
				<td class="title1"><!-- 附加信息 -->
					<ait:message  messageID="display.emp.staff_info.additional_info" module="hrm"/>	
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td height="30" class="info_title_01" nowrap></td>
						<td class="info_title_01" nowrap><!-- 发生日期 -->
							<ait:message  messageID="display.emp.staff_info.additional_info.occurrence_date" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 信息类型 -->
							<ait:message  messageID="display.emp.staff_info.additional_info.info_type" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 详细内容 -->
							<ait:message  messageID="display.emp.staff_info.additional_info.specific_narrative" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 登记者 -->
							<ait:message  messageID="display.emp.staff_info.additional_info.register" module="hrm"/>	
						</td>
					</tr>
					<c:forEach items="${appendInfoList}" var="oneResult" varStatus="i">
						<tr>
							<td class="info_content_01" height="30" nowrap><input
								type="checkbox" value="${i.count}" name="append_${i.count}"
								id="append_${i.count}"></td>
							<td class="info_content_01" nowrap>${oneResult.eventDate}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enInfoType}" zhContent="${oneResult.infoType}" koContent="${oneResult.korInfoType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.detatls}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.registerPinYin}" zhContent="${oneResult.register}" koContent="${oneResult.korRegister}"/>
							</td>
							<input type="hidden" name="additionalNo_${i.count}"
								value="${oneResult.additionalNo}" />           
						</tr>                                                                                 
					</c:forEach>
					<c:if test="${appendInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-appendInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				</div>
				</td>
			</tr>
			<tr height="1">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 离职信息 -->
					<ait:message  messageID="display.emp.staff_info.additional_info.dimission_info" module="hrm"/>	
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td width="12%" height="30" nowrap class="info_title_01"><!-- 人事令号 -->
							<ait:message  messageID="display.mutual.regulation_no"/>	
						</td><!-- 
						<td width="14%" nowrap class="info_title_01">离职类型</td>
						<td width="15%" nowrap class="info_title_01">离职原因</td> -->
						<td width="15%" nowrap class="info_title_01"><!-- 离职日期 -->
							<ait:message  messageID="display.mutual.leaving_date"/>	
						</td>
						<td width="29%" nowrap class="info_title_01"><!-- 离职事由 -->
							<ait:message  messageID="display.mutual.dimission_reason"/>	
						</td><!--
						<td width="15%" nowrap class="info_title_01">跳槽公社</td> -->
					</tr>
					<c:forEach items="${resignationList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.transNo}</td><!--
							<td class="info_content_01" nowrap>${oneResult.resignType}</td>
							<td class="info_content_01" nowrap>${oneResult.resignReason}</td> -->
							<td class="info_content_01" nowrap>${oneResult.resignDate}</td>
							<td class="info_content_01" nowrap>${oneResult.resignDesc}</td><!--
							<td class="info_content_01" nowrap>${oneResult.newCompany}</td> -->
						</tr>
					</c:forEach>
					<c:if test="${resignationCnt<3}">
						<c:forEach var="i" begin="1" end="${3-resignationCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td><!--
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td> -->
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td><!--
								<td class="info_content_01" nowrap></td> -->
							</tr>
						</c:forEach>
					</c:if>
				</table>
				</div>
				</td>
			</tr>
		</table>


		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="30">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

<input type="hidden" name="appendInfoCnt" id="appendInfoCnt"
	value="${appendInfoCnt }"></form>
</body>
</html>
