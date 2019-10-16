<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>Translate information</title>
</head>
<body>
<form name="form1" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<%@ include file="./inc/esstoolbar_null.jsp"%>
		
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		<%@ include file="../ess/ess_view_basic.jsp"%>
		
		<!-- display 3 level menu -->
		<%@ include file="./inc/ess_view_toolbar.jsp"%>
		
		<br>
		<!-- display content -->
		<table width="100%" border="0" cellpadding="0">
			<tr height="20">
				<td class="title1"><!-- 社内经历 -->
					<ait:message  messageID="display.ess.staff_info.promotion.experience_in_company" module="ess"/>	
				</td>
			</tr>
			<tr height="132">
				<td>
				<div style="overflow:auto; width:100%; height:125;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td class="info_title_01" height="30" nowrap><!-- 实施日期 -->
							<ait:message  messageID="display.ess.staff_info.promotion.implement_date" module="ess"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 人事令类型 -->
							<ait:message  messageID="display.mutual.regulation_type"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 部门 -->
							<ait:message  messageID="display.mutual.dept"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 岗位职务 -->
							<ait:message  messageID="display.mutual.post"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 职位 -->
							<ait:message  messageID="display.mutual.position"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 人事令号 -->
							<ait:message  messageID="display.mutual.regulation_no"/>	
						</td>
					</tr>
					<c:forEach items="${expInsideList}" var="oneResult">
						<tr>
							<td class="info_content_02" height="30" nowrap>${oneResult.startDate}</td>
							<td class="info_content_02" nowrap>
								<ait:content enContent="${oneResult.transCodeEnName}" zhContent="${oneResult.transCodeName}" koContent="${oneResult.transCodeKorName}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.deptEnName}" zhContent="${oneResult.department}" koContent="${oneResult.deptKorName}"/>
							</td>
							<td class="info_content_02" nowrap>
								<ait:content enContent="${oneResult.postEnName}" zhContent="${oneResult.post}" koContent="${oneResult.postKorName}"/>
							</td>
							<td class="info_content_02" nowrap>
								<ait:content enContent="${oneResult.positionEnName}" zhContent="${oneResult.position}" koContent="${oneResult.positionKorName}"/>
							</td>
							<td class="info_content_02" nowrap>${oneResult.transNo}</td>
						</tr>
					</c:forEach>
					<c:if test="${expInsideListCnt<3}">
						<c:forEach var="i" begin="1" end="${3-expInsideListCnt}" step="1">
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
				<td colspan="2">&nbsp;</td>
			</tr>
			<!-- 去除岗位职务等级 升级 模块的显示 -->
			<!-- 
  <tr height="20">
    <td class="title1">岗位职务等级 升级</td>
  </tr>
  <tr height="132">
    <td valign="top">
	<div style="overflow:auto; width:100%; height:125;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d" >
        <tr style="position: relative; top: expression(this.offsetParent.scrollTop);">
          <td class="info_title_01" height="30">实施日期</td>
          <td class="info_title_01">升级类型</td>
          <td class="info_title_01">岗位职务</td>
          <td class="info_title_01">人事令号</td>
        </tr>
       <c:forEach items="${upGradeList}" var="oneResult">
        <tr>
          <td class="info_content_02" height="30" nowrap>${oneResult.startDate}</td>
          <td class="info_content_02" nowrap>${oneResult.transCodeName}</td>
          <td class="info_content_02" nowrap>${oneResult.post}</td>
          <td class="info_content_02" nowrap>${oneResult.transNo}</td>
        </tr>
        </c:forEach>
        <c:if test="${upGradeListCnt<3}">
		 <c:forEach var="i" begin="1" end="${3-upGradeListCnt}" step="1">
	        <tr>
		        <td class="info_content_01" height="30" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
	        </tr>
         </c:forEach>
        </c:if>
      </table>
	  </div></td>
  </tr>
   -->
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
