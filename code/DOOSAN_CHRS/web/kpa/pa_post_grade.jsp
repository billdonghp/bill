<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../inc/taglibs.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="../inc/meta.jsp"%>
<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<title>号奉维护</title>
<script language=JavaScript> 
MENU_CODE='${param.menu_code}';  
var msg = new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
                   
</script>
<SCRIPT language=JavaScript src="../js/postgrade.js"></SCRIPT>                   
</head>                                                                                                                                                
<body>                                     

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_all.jsp"%>
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
		<form name="form1" action="/paControlServlet">
		<input type="hidden" name="operation" value="viewpostgradecmd">
		<input type="hidden" name="menu_code" value="${param.menu_code}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" ><!-- 职级 -->
				<ait:message messageID="display.mutual.post_grade" ></ait:message>
				</td>
				<td align="right" ><!-- 所属年份 -->所属年份 :
				<select name="postgradeYear"> 
					<c:forEach items="${postgradeYearList}" var="oneResult">
						<option value="${oneResult.POST_GRADE_YEAR}" <c:if test="${oneResult.POST_GRADE_YEAR == postgradeYear}">selected</c:if> >${oneResult.POST_GRADE_YEAR}</option>
					</c:forEach>
				</select>
				</td>
				<td align="center">
			    <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
				</td>    
			</tr>
		</table>                                              
		 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr>
		      <td  height="30"  class="info_title_01"><!-- 职级编号 -->
		      <ait:message messageID="display.mutual.no" ></ait:message>  
		      </td>
		      <td  height="30"  class="info_title_01"><!-- 所属年份 -->所属年份</td>    
			  <td  height="30"  class="info_title_01"><!-- 职级ID -->
			  <ait:message messageID="display.mutual.id" ></ait:message>
			  </td>
			  <td  height="30" class="info_title_01"><!-- 职级名 -->
			  <ait:message messageID="display.name_chinese" ></ait:message>
			  </td>
		      <td  height="30"  class="info_title_01"><!-- 号奉初始级号 -->
			  初始级号
		      </td>
		      <td  height="30"  class="info_title_01"><!-- 号奉初始金额 -->
			  初始金额
		      </td>
		      <td  height="30"  class="info_title_01"><!-- 参数 -->    
		     <ait:message messageID="display.mutual.parameter" ></ait:message> 
		      </td>
			  <td  height="30"  class="info_title_01"><!--  创建日期-->
			  <ait:message messageID="display.mutual.creation_date" ></ait:message>
			  </td>
			</tr>              
			<c:forEach items="${postgradelist}" var="VCtr" varStatus="status"> 
			<tr bgcolor="#FFFFFF" onClick="HighLightTR('#F4F1E7','black','${VCtr.POST_GRADE_ID}','${VCtr.POST_GRADE_YEAR}')">
				<td height="30" align="center" style="color: #666666;" nowrap>${status.index + 1}</td>
			    <td align="center" style="color: #666666;" nowrap>${VCtr.POST_GRADE_YEAR}</td>    
				<td align="center" style="color: #666666;" nowrap>${VCtr.POST_GRADE_ID}</td>              
				<td align="center" style="color: #666666;" nowrap>${VCtr.POST_GRADE_NAME}</td>
				<td align="center" style="color: #666666;" nowrap>${VCtr.POST_GRADE_FIRST_NO}</td>
				<td align="center" style="color: #666666;" nowrap>${VCtr.POST_GRADE_FIRST_MONEY}</td>
				<td align="center" style="color: #666666;" nowrap>${VCtr.POST_GRADE_VALUE}</td>
				<td align="center" style="color: #666666;" nowrap>${VCtr.CREATE_DATE}</td>        
			</tr>      
			</c:forEach>
		</table> 
		</form>               
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
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</body>
</html>