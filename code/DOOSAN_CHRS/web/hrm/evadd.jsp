<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;评价信息</title>
</head>
<script language="javascript" src="../js/evadd.js"></script>

<SCRIPT type="text/javascript">
        
function Save(){ 
	if(CheckForm('<ait:message  messageID="alert.emp.staff_info.evaluation_info.evaluation_period_must_be_entered" module="hrm"/>')) 
	{
	  document.form1.action="hrmControlServlet?menu_code=hr0103&operation=insertevaluatecmd&empID=<c:out value='${empID}'/>";
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
				<td class="title1"><!-- 评价信息 -->
							<ait:message  messageID="display.emp.staff_info.evaluation_info" module="hrm"/>	
						</td>
			</tr>
			<tr height="135">
				<td>

				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
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
					<c:forEach items="${evaluateList}" var="oneResult">
						<tr>
							<td class="info_content_02" height="30" nowrap>${oneResult.EV_PERIOD}</td>
							<td class="info_content_02" nowrap>
								<ait:content enContent="${oneResult.EN_EV_TYPE_NAME}" zhContent="${oneResult.EV_TYPE_NAME}" koContent="${oneResult.KOR_EV_TYPE_NAME}"/>
							</td>
							<td class="info_content_02" nowrap>${oneResult.FIR_MARK}</td>
							<td class="info_content_02" nowrap>${oneResult.FIR_OPINION}</td>
							<td class="info_content_02" nowrap>${oneResult.SEC_MARK}</td>
							<td class="info_content_02" nowrap>${oneResult.SEC_OPINION}</td>
							<td class="info_content_02" nowrap>${oneResult.EV_MARK}</td>
							<td class="info_content_02" nowrap>${oneResult.EV_GRADE}</td> 
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="evPeriod_${i}" id="evPeriod_${i}" /></td>	
							<td class="info_content_01" nowrap>	
							<ait:codeClass codeClass="EvaluateType" name="evType_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"  
								class="content" name="firMark_${i}" id="firMark_${i}" 
								onpropertychange='if(/[^\-?\d*\.?\d{0,2}]/.test(this.value))   this.value=this.value.replace(/[^\-?\d*\.?\d{0,2}]/,"")'/></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="firOpinion_${i}" id="firOpinion_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="secMark_${i}"
								id="secMark_${i}" onpropertychange='if(/[^\-?\d*\.?\d{0,2}]/.test(this.value))   this.value=this.value.replace(/[^\-?\d*\.?\d{0,2}]/,"")'/></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="secOpinion_${i}" id="secOpinion_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="evMark_${i}"
								id="evMark_${i}" onpropertychange='if(/[^\-?\d*\.?\d{0,2}]/.test(this.value))   this.value=this.value.replace(/[^\-?\d*\.?\d{0,2}]/,"")'/></td>      
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="evGrade_${i}"
								id="evGrade_${i}" /></td>  
						</tr>
						<input type="hidden" name="empID_${i}"              
								value="${empID}"  id="empID_${i}">         
					</c:forEach>
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
<input type="hidden" name="evSign" id="evSign"></form>  
</body>
<ait:xjos />
</html>
