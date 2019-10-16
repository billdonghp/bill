<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;培训信息</title>
</head>
<script language="javascript" src="../js/hr_delete_training.js"></script>
<SCRIPT type="text/javascript">

function Save(){
	
	if (!CheckForm('<ait:message  messageID="alert.emp.staff_info.basic_info.please_select_item_to_delete" module="hrm"/>')){
		return;
	}

	//"确认要删除信息吗"
	if(confirm('<ait:message  messageID="alert.mutual.are_you_sure_you_want_to_delete_this_message"/>')) {
	

		document.form1.action="hrmControlServlet?menu_code=hr0106&operation=deleteTraining&empID=<c:out value='${empID}'/>";
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
				<td width="50%" class="title1"><!-- 社内教育 -->
					<ait:message  messageID="display.emp.staff_info.training.internal_education" module="hrm"/>
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
						<td class="info_title_01" nowrap><!-- 课程名 -->
							<ait:message  messageID="display.mutual.course_title"/>
						</td>
						<td class="info_title_01" nowrap><!-- 课程类型 -->
							<ait:message  messageID="display.mutual.course_type"/>
						</td>
						<td class="info_title_01" nowrap><!-- 开始日期 -->
							<ait:message  messageID="display.mutual.start_date"/>
						</td>
						<td class="info_title_01" nowrap><!-- 结束日期 -->
							<ait:message  messageID="display.mutual.end_date"/>
						</td>
						<td class="info_title_01" nowrap>
						<p><!-- 分数 -->
							<ait:message  messageID="display.mutual.mark"/>
						</p>
						</td>
						<td class="info_title_01" nowrap><!-- 名次 -->
							<ait:message  messageID="display.mutual.rank"/>
						</td>
					</tr>
					<c:forEach items="${trainingInsideList}" var="oneResult"
						varStatus="i">
						<tr>
							<td class="info_content_01" height="30" nowrap><input
								type="checkbox" value="${i.count}"
								name="trainingInside_${i.count}" id="trainingInside_${i.count}"></td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enCourse}" zhContent="${oneResult.course}" koContent="${oneResult.korourse}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enCourseType}" zhContent="${oneResult.courseType} " koContent="${oneResult.korCourseType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.startDate}</td>
							<td class="info_content_01" nowrap>${oneResult.endDate}</td>
							<td class="info_content_01" nowrap>${oneResult.mark}</td>
							<td class="info_content_01" nowrap>${oneResult.place}</td>
							<input type="hidden" name="trainInNo_${i.count}"
								value="${oneResult.trainInNo}">
						</tr>
					</c:forEach>
					<c:if test="${trainingInsideCnt<3}">
						<c:forEach var="i" begin="1" end="${3-trainingInsideCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
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
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 社外培训 -->
					<ait:message  messageID="display.emp.staff_info.training.external_training" module="hrm"/>
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
						<td class="info_title_01" nowrap><!-- 课程名 -->
							<ait:message  messageID="display.mutual.course_title"/>
						</td>
						<td class="info_title_01" nowrap><!-- 课程类型 -->
							<ait:message  messageID="display.mutual.course_type"/>
						</td>
						<td class="info_title_01" nowrap><!-- 开始日期 -->
							<ait:message  messageID="display.mutual.start_date"/>
						</td>
						<td class="info_title_01" nowrap><!-- 结束日期 -->
							<ait:message  messageID="display.mutual.end_date"/>
						</td>
						<td class="info_title_01" nowrap><!-- 培训机关 -->
							<ait:message  messageID="display.emp.staff_info.training.external_training.training_org" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap>
						<p><!-- 分数 -->
							<ait:message  messageID="display.mutual.mark"/>
						</p>
						</td>
						<td class="info_title_01" nowrap><!-- 名次 -->
							<ait:message  messageID="display.mutual.rank"/>
						</td>
					</tr>
					<c:forEach items="${trainingOutsideList}" var="oneResult"
						varStatus="i">
						<tr>
							<td class="info_content_01" height="30" nowrap><input
								type="checkbox" value="${i.count}"
								name="trainingOutside_${i.count}"
								id="trainingOutside_${i.count}"></td>
							<td class="info_content_01" nowrap>${oneResult.course}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enCourseType}" zhContent="${oneResult.courseType} " koContent="${oneResult.korCourseType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.startDate}</td>
							<td class="info_content_01" nowrap>${oneResult.endDate}</td>
							<td class="info_content_01" nowrap>${oneResult.institutionName}</td>
							<td class="info_content_01" nowrap>${oneResult.mark}</td>
							<td class="info_content_01" nowrap>${oneResult.place}</td>
							<input type="hidden" name="trainOutNo_${i.count}"
								value="${oneResult.trainOutNo}" />
						</tr>
					</c:forEach>
					<c:if test="${trainingOutsideCnt<3}">
						<c:forEach var="i" begin="1" end="${3-trainingOutsideCnt}"
							step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
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
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 研修 -->
					<ait:message  messageID="display.emp.staff_info.training.work_training" module="hrm"/>
				</td>
			</tr>
			<tr height="135">
				<td colspan="2">
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<!-- 
        <td height="30" class="info_title_01" nowrap></td>
         -->
						<td class="info_title_01" nowrap><!-- 研修类型 -->
							<ait:message  messageID="display.emp.staff_info.training.work_training.training_type" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 期间 -->
							<ait:message  messageID="display.mutual.period"/>
						</td>
						<td class="info_title_01" nowrap><!-- 国家 -->
							<ait:message  messageID="display.mutual.country"/>
						</td>
						<td class="info_title_01" nowrap><!-- 研修机关 -->
							<ait:message  messageID="display.emp.staff_info.training.work_training.training_org" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 研修内容 -->
							<ait:message  messageID="display.emp.staff_info.training.work_training.training_narrative" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 义务服期 -->
							<ait:message  messageID="display.mutual.voluntary_work"/>
						</td>
						<td class="info_title_01" nowrap><!-- 费用(元) -->
							<ait:message  messageID="display.mutual.expense"/>
						</td>
						<td class="info_title_01" nowrap><!-- 人事令号 -->
							<ait:message  messageID="display.mutual.regulation_no"/>
						</td>
					</tr>
					<c:forEach items="${studyList}" var="oneResult" varStatus="i">
						<tr>
							<!-- 
          <td class="info_content_01" height="30" nowrap><input type="checkbox" value="${i.count}" name="studying_${i.count}" id="studying_${i.count}" ></td>
           -->
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enResearchType}" zhContent="${oneResult.researchType}" koContent="${oneResult.korResearchType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.startDate} 至
							${oneResult.endDate}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enCountry}" zhContent="${oneResult.country}" koContent="${oneResult.korCountry}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.institutionName}</td>
							<td class="info_content_01" nowrap>${oneResult.studyContents}</td>
							<td class="info_content_01" nowrap>${oneResult.period}</td>
							<td class="info_content_01" nowrap>${oneResult.expense}</td>
							<td class="info_content_01" nowrap>${oneResult.transNo}</td>
							<input type="hidden" name="activity_${i.count}" value="1" />
							<input type="hidden" name="studyNo_${i.count}"
								value="${oneResult.studyNo}" />
						</tr>
					</c:forEach>
					<c:if test="${studyListCnt<3}">
						<c:forEach var="i" begin="1" end="${3-studyListCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>

								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
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
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 留学 -->
					<ait:message  messageID="display.emp.staff_info.training.study_abroad" module="hrm"/>
				</td>
			</tr>
			<tr height="135">
				<td colspan="2">
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<!-- 
        <td height="30" class="info_title_01" nowrap></td>
        -->
						<td class="info_title_01" nowrap><!-- 留学名 -->
							<ait:message  messageID="display.mutual.title"/>
						</td>
						<td class="info_title_01" nowrap><!-- 期间 -->
							<ait:message  messageID="display.mutual.period"/>
						</td>
						<td class="info_title_01" nowrap><!-- 国家 -->
							<ait:message  messageID="display.mutual.country"/>
						</td>
						<td class="info_title_01" nowrap><!-- 留学机关 -->
							<ait:message  messageID="display.emp.staff_info.training.study_abroad.study_org" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 专业 -->
							<ait:message  messageID="display.mutual.major"/>
						</td>
						<td class="info_title_01" nowrap><!-- 义务服期 -->
							<ait:message  messageID="display.mutual.voluntary_work"/>
						</td>
						<td class="info_title_01" nowrap><!-- 费用(元) -->
							<ait:message  messageID="display.mutual.expense"/>
						</td>
						<td class="info_title_01" nowrap><!-- 人事令号 -->
							<ait:message  messageID="display.mutual.regulation_no"/>
						</td>
					</tr>
					<c:forEach items="${study2List}" var="oneResult" varStatus="i">
						<tr>
							<!-- 
          <td class="info_content_01" height="30" nowrap><input type="checkbox" value="${i.count}" name="studying_${i.count + studyListCnt}" id="studying_${i.count + studyListCnt}" ></td>
          -->
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enStudyType}" zhContent="${oneResult.studyType}" koContent="${oneResult.korStudyType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.startDate} 至
							${oneResult.endDate}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enCountry}" zhContent="${oneResult.country}" koContent="${oneResult.korCountry}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.institutionName}</td>
							<td class="info_content_01" nowrap>${oneResult.subject}</td>
							<td class="info_content_01" nowrap>${oneResult.period}</td>
							<td class="info_content_01" nowrap>${oneResult.expense}</td>
							<td class="info_content_01" nowrap>${oneResult.transNo}</td>
							<input type="hidden" name="activity_${i.count + studyListCnt}"
								value="1" />
							<input type="hidden" name="studyNo_${i.count + studyListCnt}"
								value="${oneResult.studyNo}" />
						</tr>
					</c:forEach>
					<c:if test="${study2ListCnt<3}">
						<c:forEach var="i" begin="1" end="${3-study2ListCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>

								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
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

<input type="hidden" name="trainingInsideCnt" id="trainingInsideCnt"
	value="${trainingInsideCnt }"> <input type="hidden"
	name="trainingOutsideCnt" id="trainingOutsideCnt"
	value="${trainingOutsideCnt }"> <input type="hidden"
	name="studyListCnt" id="studyListCnt" value="${studyListCnt }">
<input type="hidden" name="study2ListCnt" id="study2ListCnt"
	value="${study2ListCnt }"></form>
</body>
</html>
