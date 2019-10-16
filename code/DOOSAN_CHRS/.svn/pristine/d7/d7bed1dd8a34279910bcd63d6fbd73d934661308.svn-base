<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;奖励惩戒</title>
</head>
<SCRIPT type="text/javascript">

function Add(){
	
	document.form1.action="hrmControlServlet?menu_code=hr0104&operation=retrieveDataForInsertReward&empID=<c:out value='${empID}'/>";
	document.form1.submit();
 }
 
function Update(){

	document.form1.action="hrmControlServlet?menu_code=hr0104&operation=retrieveDataForUpdateReward&empID=<c:out value='${empID}'/>";
	document.form1.submit();
}

function Delete(){
	
	document.form1.action="hrmControlServlet?menu_code=hr0104&operation=retrieveDataForDeleteReward&empID=<c:out value='${empID}'/>";
	document.form1.submit();
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
		<%@ include file="../inc/hrmrewardtoolbar.jsp"%></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		<%@ include file="../hrm/hrm_view_basic.jsp"%> 
		
		<!-- display 3 level menu -->
		<%@ include file="./inc/hrm_view_toolbar.jsp"%>
		
		<br>
		<!-- display content -->
		<table width="100%" border="0" cellpadding="0">
			<tr height="20">
				<td class="title1"><!--奖励 -->
				<ait:message messageID="display.emp.staff_info.award_discipline.award" module="hrm"/>
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:125;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td class="info_title_01" height="30" nowrap><!--奖励日期 -->
							<ait:message messageID="display.emp.staff_info.award_discipline.award.award_date" module="hrm" />
						</td>
						<td class="info_title_01" nowrap><!--奖励类型 -->
							<ait:message messageID="display.emp.staff_info.award_discipline.award.award_type" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!--种类 -->
							<ait:message messageID="display.mutual.awarder" />
						</td>
						<td class="info_title_01" nowrap><!--人数 -->
							<ait:message messageID="display.mutual.people" />
						</td>
						<td class="info_title_01" nowrap><!--功绩内容 -->
							<ait:message messageID="display.emp.staff_info.award_discipline.award.award_contents" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!--奖励金额 -->
							<ait:message messageID="display.emp.staff_info.award_discipline.award.award_sum" module="hrm" />
						</td>
					</tr>
					<c:forEach items="${rewardList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.rewardDate}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enRewardType}" zhContent="${oneResult.rewardType}" koContent="${oneResult.korRewardType}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enRewardSort}" zhContent="${oneResult.rewardSort}" koContent="${oneResult.korRewardSort}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.rewardAmount}</td>
							<td class="info_content_01" nowrap>${oneResult.rewardContents}</td>
							<td class="info_content_01" nowrap>${oneResult.rewardBonus}</td>
						</tr>
					</c:forEach>
					<c:if test="${rewardCnt<3}">
						<c:forEach var="i" begin="1" end="${3-rewardCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
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
				<td>&nbsp;</td>
			</tr>
			<tr height="20">
				<td class="title1"><!--惩戒 -->
					<ait:message messageID="display.emp.staff_info.award_discipline.discipline" module="hrm"/>
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td class="info_title_01" height="30"><!--惩戒日期 -->
							<ait:message messageID="display.mutual.issued" />
						</td>
						<td class="info_title_01"><!--惩戒类型 -->
							<ait:message messageID="display.mutual.action_type" />
						</td>
						<td class="info_title_01"><!--违纪类型 -->
							<ait:message messageID="display.mutual.action" />
						</td>
						<td class="info_title_01"><!--惩戒事由 -->
							<ait:message messageID="display.mutual.discipline_reason" />
						</td>
						<td class="info_title_01"><!--赦免日期 -->
							<ait:message messageID="display.mutual.returned" />
						</td>
						<td class="info_title_01"><!-- 人事令号 -->
							<ait:message messageID="display.mutual.regulation_no" />
						</td>
					</tr>
					<c:forEach items="${punishMentList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.punishDate}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enPunishType}" zhContent="${oneResult.punishType}" koContent="${oneResult.korPunishType}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enViolationType}" zhContent="${oneResult.violationType}" koContent="${oneResult.korViolationType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.punishResult}</td>
							<td class="info_content_01" nowrap>${oneResult.releaseDate}</td>
							<td class="info_content_01" nowrap>${oneResult.transNo}</td>
						</tr>
					</c:forEach>
					<c:if test="${punishMentListCnt<3}">
						<c:forEach var="i" begin="1" end="${3-punishMentListCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
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

</form>
</body>
</html>
