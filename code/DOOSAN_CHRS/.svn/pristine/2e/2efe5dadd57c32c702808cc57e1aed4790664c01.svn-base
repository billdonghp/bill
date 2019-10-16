<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../inc/taglibs.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="../inc/meta.jsp"%>
<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<!--<link href="../css/default.css" rel="stylesheet" type="text/css">-->
<title>Insert title here</title>
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>
<SCRIPT language=JavaScript src="../js/postgrade.js"></SCRIPT>                   
</head>                          
<script language="javascript">
MENU_CODE='${param.menu_code}';
</script>                                                                                                                         
<body>                                     

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
		<%@ include file="../inc/sy_common_toolbar.jsp" %> 
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

		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 职级 -->
				<ait:message messageID="display.mutual.post_grade" ></ait:message>
				</td>
			</tr>
		</table>                                              
		 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr>
		      <td  height="30"  class="info_title_01"><!-- 职级编号 -->
		      <ait:message messageID="display.mutual.no" ></ait:message>  
		      </td>
			  <td  height="30"  class="info_title_01"><!-- 职级ID -->
			  <ait:message messageID="display.mutual.id" ></ait:message>
			  </td>
			  <td  height="30" class="info_title_01"><!-- 职级名 -->
			  <ait:message messageID="display.name_chinese" ></ait:message>
			  </td>
		      <td  height="30"  class="info_title_01"><!-- 职级英文名 -->
		      <ait:message messageID="display.name_english" ></ait:message>
		      </td>
		      <td  height="30"  class="info_title_01"><!-- 参数 -->    
		     <ait:message messageID="display.mutual.parameter" ></ait:message> 
		      </td>
			  <td  height="30"  class="info_title_01"><!--  创建日期-->
			  <ait:message messageID="display.mutual.creation_date" ></ait:message>
			  </td>
			  <td  height="30"  class="info_title_01"><!-- 创建者 -->
			<ait:message messageID="display.mutual.created_by" ></ait:message>  
			  </td>    
			</tr>              
			<c:forEach items="${postgradelist}" var="VCtr" varStatus="status"> 
			<tr bgcolor="#FFFFFF" onClick="HighLightTR('#E7F0EF','black','${VCtr.POST_GRADE_ID}')">
				<td height="30" align="center" style="color: #666666;" nowrap><c:out value="${VCtr.POST_GRADE_NO}"/></td>  
				<td align="center" style="color: #666666;"nowrap>${VCtr.POST_GRADE_ID}</td>              
				<td align="center" style="color: #666666;"nowrap><c:out value="${VCtr.POST_GRADE_NAME}"/></td>
				<td align="center" style="color: #666666;"nowrap><c:out value="${VCtr.POST_GRADE_EN_NAME}"/></td>
				<td align="center" style="color: #666666;"nowrap><c:out value="${VCtr.POST_GRADE_VALUE}"/></td>
				<td align="center" style="color: #666666;"nowrap><c:out value="${VCtr.CREATE_DATE}"/></td>    
				<td align="center" style="color: #666666;"nowrap><ait:content enContent="${VCtr.CREATED_BY_PINYIN}" zhContent="${VCtr.CREATED_BY_NAME}" koContent="${VCtr.CREATED_BY_KORNAME}"/>&nbsp;</td>      
			</tr>      
			</c:forEach> 
			<c:if test="${fn:length(postgradelist) < 9}">
				<c:forEach var="i" begin="1" end="${9-fn:length(postgradelist)}"
					step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
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
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
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