<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>   
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;附加信息</title>
</head>
<script language="javascript" src="../js/hr_add_append.js"></script>
<SCRIPT type="text/javascript">

function Save(){
	// 发生日期为必输项
	if(CheckForm('<ait:message  messageID="alert.emp.staff_info.additional_info.date_must_be_entered" module="hrm"/>')) {

		document.form1.action="hrmControlServlet?menu_code=hr0110&operation=insertAppendInfo&empID=<c:out value='${empID}'/>";
		document.form1.fireSubmit();
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
		<%@ include file="./inc/hrtoolbar_a.jsp"%></td>
		
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

				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td width="10%" height="30" class="info_title_01" nowrap><!-- 发生日期 -->
							<ait:message  messageID="display.emp.staff_info.additional_info.occurrence_date" module="hrm"/>	
						</td>
						<td width="10%" class="info_title_01" nowrap><!-- 信息类型 -->
							<ait:message  messageID="display.emp.staff_info.additional_info.info_type" module="hrm"/>	
						</td>
						<td width="70%" class="info_title_01" nowrap><!-- 详细内容 -->
							<ait:message  messageID="display.emp.staff_info.additional_info.specific_narrative" module="hrm"/>	
						</td>
						<td width="10%" class="info_title_01" nowrap><!-- 登记者 -->
							<ait:message  messageID="display.emp.staff_info.additional_info.register" module="hrm"/>	
						</td>
					</tr>
					<c:forEach items="${appendInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.eventDate}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enInfoType}" zhContent="${oneResult.infoType}" koContent="${oneResult.korInfoType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.detatls}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.registerPinYin}" zhContent="${oneResult.register}" koContent="${oneResult.korRegister}"/>
							</td>
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" height="30" nowrap><input
								type="text" style="width:90;" class="content"
								name="eventDate_${i}" id="eventDate_${i}"   
								onClick="setday(this);" date mask="9999-99-99" /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="infoTypeCode_${i}" codeClass="OtherInfoCode" all="ALL"
								filling="style='width:90;' " /></td>
							<td class="info_content_01" nowrap><input type="text"
								name="detatls_${i}" id="detatls_${i}" size="100" /></td>
							<td class="info_content_01" nowrap>${adminName}</td>
							<input type="hidden" name="createdBy_${i}" value="${adminId}">
							<input type="hidden" name="registerID_${i}" value="${adminId}">
							<input type="hidden" name="empID_${i}" value="${empID}" />
						</tr>
					</c:forEach>
				</table>
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
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
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
<input type="hidden" name="appendSign" id="appendSign">
</form>
</body>
<ait:xjos />
</html>
