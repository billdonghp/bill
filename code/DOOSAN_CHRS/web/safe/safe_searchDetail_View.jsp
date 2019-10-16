<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<jsp:useBean id="EMPID" class="java.lang.String" scope="request"/>
<jsp:useBean id="CHINESENAME" class="java.lang.String" scope="request"/>
<jsp:useBean id="DEPTNAME" class="java.lang.String" scope="request"/>
<jsp:useBean id="JOB_POSITION" class="java.lang.String" scope="request"/>
<jsp:useBean id="DISEASE" class="java.lang.String" scope="request"/>
<jsp:useBean id="JOB_DISEASE_TYPE" class="java.lang.String" scope="request"/>
<jsp:useBean id="DATE_STARTED" class="java.lang.String" scope="request"/>
<jsp:useBean id="START_TIME" class="java.lang.String" scope="request"/>


<!--<jsp:useBean id="allMedicalInformation" class="java.util.ArrayList" scope="request"/>
--><html>
<head>
<script src="../js/prototype.js"></script>
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<script language="javascript">

function selectMenu(orderFlag){
	positionInfo.location.href = "/safeControlServlet?operation=jobHealth&content=Jump&menu_code=${param.menu_code}&orderFlag="+orderFlag+"&EMPID="+document.form1.person_id.value;
}

</script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_return.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> <br>
		<form name="form1" method="post" action="">
		<input type="hidden" name="flag" value="0"/>
		<input type="hidden" name="temp" value="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">历次体检查看详情</td>
			</tr>
		</table>
		<table id="operateTable" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="11%" class="info_title_01">
		      		职业健康编号
		      	</td>
      		    <td class="info_content_00">
      		    	${documentno}
	            </td>
			</tr>
			<tr>
				<td width="11%" class="info_title_01">
		      		职号
		      	</td>
      		    <td class="info_content_00">
      		    ${EMPID}
      		    <input type="hidden" name="EMPID" value="${EMPID}"/>
      		    <input type="hidden" name="person_id" value="${PERSON_ID}"/>
	            </td>
			</tr>
			<tr>
				<td width="11%" class="info_title_01">
		      		姓名
		      	</td>
      		    <td class="info_content_00">
      		    	${CHINESENAME}
	            </td>
			</tr>
			<tr>
				<td width="11%" class="info_title_01">
		      		部门
		      	</td>
      		    <td class="info_content_00">
       				${DEPTNAME}
	            </td>
			</tr>
			<tr>
				<td width="11%" class="info_title_01">
		      		出生日期
		      	</td>
      		    <td class="info_content_00">
       				${birth_ymd}
	            </td>
			</tr>
			<tr>
				<td width="11%" class="info_title_01">
		      		入社日期
		      	</td>
      		    <td class="info_content_00">
       				${DATE_STARTED}
	            </td>
			</tr>
		</table>	
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			>	
			<tr align="center">
				<td align="center" class="info_title_01" width="11%">从事岗位顺序</td>
				<td width="92%">&nbsp;</td>
			</tr>
			<tr align="center">
		    	 <td align="left" width="11%">
			    	 <select name="CHANGE_POSITION_ORDER_FLAG" size="20" style="width: 140px;" onchange="selectMenu(this.value)">
				    	 <c:forEach items="${getPositionOrder}" var="view" varStatus="i">
							 <option value="${view.POSITION_DIS_DISTYPE_ID}">
								 ${view.CODE_NAME}
							 </option>
						  </c:forEach>
			    	 </select>
		    	 </td>
		    	 <td width="92%">
		    	 <iframe width="100%" height="300" marginwidth="0" marginheight="0" frameborder="0" name="positionInfo"></iframe>
		    	 </td>
		    </tr>
		</table>	
			
			<!--<tr>
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
							<font color="blue" size="2">体检异常处理结果:</font>
			<textarea name="remark" style=" height: 40px;width:300px " readonly="readonly" type="_moz"  
 				onfocus="this.style.height='70px'" onblur="this.style.height='20px';">${all.MEDICAL_RESULT}</textarea>
						</td>
					</tr>
				</c:forEach>
			-->
		</form>
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
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</body>
</html>
