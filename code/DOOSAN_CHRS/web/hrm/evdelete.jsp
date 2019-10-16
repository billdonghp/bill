<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;个人信息</title>
<script language="javascript" src="../js/hr_delete_append.js"></script>
<SCRIPT type="text/javascript">
function Save(){

	if (!CheckForm('<ait:message  messageID="alert.emp.staff_info.basic_info.please_select_item_to_delete" module="hrm"/>')){
		return;
	}
	//"确认要删除信息吗"
	if(confirm('<ait:message  messageID="alert.mutual.are_you_sure_you_want_to_delete_this_message"/>')) {
	
		document.form1.action = "hrmControlServlet?menu_code=hr0103&operation=deleteevaluatecmd&empID=<c:out value='${empID}'/>";
		document.form1.submit();  
	}
 }
                                 
</SCRIPT>

</head>
<body>
<form name="form1" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">

		  <%@ include file="../inc/hr_deletetoolbar.jsp"%></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<%@ include file="../hrm/hrm_add_basic.jsp"%> 
    <table width="100%" border="0" cellpadding="5">
  
			<tr height="20">
				<td class="title1"><!-- 评价信息 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info" module="hrm"/>	
						</td>
			</tr>

			<tr height="198">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expresszion(this.offsetParent.scrollTop);">
						<td class="info_title_01" nowrap><!-- 选择 -->
							<ait:message  messageID="display.mutual.select"/>	
						</td> 
						<td class="info_title_01" height="30" nowrap><!--评价时期 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info.eval_Period" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!--评价类型 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info.eval_type" module="hrm"/>	
						</td>    
						<td class="info_title_01" nowrap><!--1次评价分数 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info.1st_mark" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!--1次评价意见 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info.1st_opinion" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!--2次评价分数 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info.2nd_mark" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!--2次评价意见 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info.2nd_opinion" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!--调整分数 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info.regulate_mark" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!--最终等级 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info.ultimate_grade" module="hrm"/>	
						</td>
					</tr>
					<c:forEach items="${evaluateList}" var="oneResult"
						varStatus="i">
						 <tr>              
							<td class="info_content_01" height="30" nowrap><input
								type="checkbox" value="${i.count}" name="append_${i.count}"
								id="append_${i.count}"></td>   
							<td class="info_content_02" height="30" nowrap>${oneResult.EV_PERIOD}</td>
							<td class="info_content_02" nowrap>
								<ait:content enContent="${oneResult.EN_EV_TYPE_NAME}" zhContent="${oneResult.EV_TYPE_NAME}" koContent="${oneResult.KOR_EV_TYPE_NAME}"/>
							</td>
							<td class="info_content_02" nowrap>${oneResult.FIR_MARK}</td>   
							<td class="info_content_02">${oneResult.FIR_OPINION}</td>
							<td class="info_content_02" nowrap>${oneResult.SEC_MARK}</td>
							<td class="info_content_02">${oneResult.SEC_OPINION}</td>
							<td class="info_content_02" nowrap>${oneResult.EV_MARK}</td>
							<td class="info_content_02" nowrap>${oneResult.EV_GRADE}</td>
							<input type="hidden" name="ev_period_id_${i.count}"    
								value="${oneResult.EV_PERIOD_ID}" />  
						 </tr>                    
					</c:forEach>	                      
					<c:if test="${evaluateListCnt<3}">  
						<c:forEach var="i" begin="1" end="${3-evaluateListCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
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
               
<input type="hidden" name="evaluateListCnt" id="evaluateListCnt"
	value="${evaluateListCnt }">
</form>          
</body>
</html>
