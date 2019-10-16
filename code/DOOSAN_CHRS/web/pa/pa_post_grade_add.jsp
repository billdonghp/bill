<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../inc/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/default.css">                        
</head>                                                 
<body>    
<%@ include file="../pa/checkpostgradeisnull.jsp" %>
<script language="javascript">
  function changePostGradeName(postGrade){ 
		if (postGrade.value == '')
		{
			document.getElementById("gradename").innerHTML = "" ;
			document.PostXForm.postgradename.value = "" ;
		}
		else
		{
			document.getElementById("gradename").innerHTML = postGrade.options[postGrade.selectedIndex].text ;
			document.PostXForm.postgradename.value = postGrade.options[postGrade.selectedIndex].text ;
		}
  }  
</script>                                                   
 <form action="/paControlServlet" name="PostXForm" >   
 <input name="operation" value="insertpostgradecmd" type="hidden">        
<input name="menu_code" value="${param.menu_code}" type="hidden"> 
<input name="postgradename" type="hidden" value="" />

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>  
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
		
		<!-- display content -->
		 <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--  职级-->
				<ait:message messageID="display.mutual.post_grade" ></ait:message>
				</td>
			</tr>
		</table>
		 <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr>  
				 <td class="info_title_01" width="15%" height="30"> <!-- 所属年份 -->所属年份</td>  
				 <td class="info_content_00" width="85%" height="30"><ait:date yearName="year" /> </td>     
			</tr>
			<tr>  
				 <td class="info_title_01" width="15%" height="30"> <!-- 职级ID  -->
				<ait:message messageID="display.mutual.id" ></ait:message>
				 </td>  
				 <td class="info_content_00" width="85%" height="30"> 
				 	<ait:codeClass codeClass="PostGradeCode" name="gradeid" onChange="changePostGradeName(this)"/>
			    </td>     
			</tr>       
			<tr>                               
				 <td class="info_title_01" height="30"> <!-- 职级名 -->
				 <ait:message messageID="display.name_chinese" ></ait:message>
				 </td>
				 <td id="gradename" class="info_content_00" height="30">&nbsp;				 
				 </td>
			</tr>
			<tr>     
			   <td class="info_title_01" height="30"><!--  初始级号-->
			    初始级号
			   </td>  
				 <td class="info_content_00" height="30"> 
				 <input type="text" name="postgradefirstno"  maxlength="50"  size="50"/> 
				 </td>
			</tr> 
			<tr>     
			   <td class="info_title_01" height="30"><!--  初始金额 -->
			    初始金额
			   </td>  
				 <td class="info_content_00" height="30"> 
				 <input type="text" name="postgradefirstmoney"  maxlength="50"  size="50"/> 
				 </td>
			</tr> 
			<tr>     
			   <td class="info_title_01" height="30"><!-- 参数 -->
			   <ait:message messageID="display.mutual.parameter" ></ait:message>
			   </td>  
				 <td class="info_content_00" height="30"> 
				 <input type="text" name="postgradevalue"  maxlength="50"  size="50"/> 
				 </td>
			</tr>                        
		</table>

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
</form>  
</body>
</html>