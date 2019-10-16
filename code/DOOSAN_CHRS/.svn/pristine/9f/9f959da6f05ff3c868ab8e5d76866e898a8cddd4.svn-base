<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="positionInfo" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>制度查看</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">岗位信息</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			    <tr>
				<td width="15%" class="info_title_01">岗位</td>
				<td class="info_content_00">
					${JOB_POSITION}
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">职业危害</td>
				<td class="info_content_00">
					${DISEASE}
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">职业病类型</td>
				<td class="info_content_00">
					${JOB_DISEASE_TYPE}
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">从事本岗时间</td>
				<td class="info_content_00">
					${DATE_STARTED}
				</td>
			</tr>
				<c:forEach items="${allMedicalInformation}" var="all" varStatus="i">
					<tr>
						<td width="15%" class="info_title_01">体检结果</td>
						<td class="info_content_00">
							<font color="blue" size="2">体检时间:</font>${all.MEDICAL_YEAR}&nbsp;&nbsp;
							<font color="blue" size="2">体检状态:</font>${all.MEDICAL_STATE}&nbsp;&nbsp;
							<font color="blue" size="2">体检结果:</font>${all.MEDICAL_FLAG}&nbsp;&nbsp;
							<c:if test="${all.MEDICAL_FLAG=='正常'}">
								<font color="blue" size="2">备注:</font>
							    <textarea name="remarkText" style=" height: 40px;width:300px " readonly="readonly" type="_moz"  
				 				onfocus="this.style.height='70px'" onblur="this.style.height='20px';">${all.MEDICAL_REMARK}</textarea>
							</c:if>
							<c:if test="${all.MEDICAL_FLAG=='异常'}">
								<font color="blue" size="2">体检异常处理结果:</font>
							    <textarea name="remark" style=" height: 40px;width:300px " readonly="readonly" type="_moz"  
				 				onfocus="this.style.height='70px'" onblur="this.style.height='20px';">${all.MEDICAL_RESULT}</textarea>
							</c:if>
							
							
						</td>
					</tr>
				</c:forEach>
		    <tr align="center"></tr>
		 </table>
		</form>
		
</body>
</html>