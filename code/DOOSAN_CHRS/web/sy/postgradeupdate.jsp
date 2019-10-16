<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../inc/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/default.css"> 
<SCRIPT language=JavaScript>
function check()
{
   id=document.PostXForm.gradeid.value;
    url = "/syControlServlet?operation=ispostgradeidexistcmd&postgradeid="+id+"&dowhat=update&menu_code=${param.menu_code}";               
   location.href = url;                   
}                          
</SCRIPT>             
</head>               
<body>                     
<%@ include file="../sy/checkpostgradeisnull.jsp" %>   
<form action="/syControlServlet"  name="PostXForm" onsubmit="return cheknull();">            
<input name="operation" value="updatepostgradecmd" type="hidden">                       
<input name="menu_code" value="${param.menu_code}" type="hidden">                   

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
				<td align="left" class="title1" colspan="10"><!-- 职级 -->
				<ait:message messageID="display.mutual.post_grade" ></ait:message>
				</td>
			</tr>
		</table>
		<TABLE width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr>
				<td class="info_title_01" width="15%" height="30"><!--职级编号  -->
			 <ait:message messageID="display.mutual.no" ></ait:message>
				</td>
				<td class="info_content_00" width="85%" height="30"> 
				<input type="text" name="gradeno" align="left"  size="50"  maxlength="10" value="${simplemap.POST_GRADE_NO}" readonly="readonly"/>    
			    </td>      
			</tr>                                       
			<tr>     
				 <td class="info_title_01"  height="30"><!-- 职级ID --> 
				<ait:message messageID="display.mutual.id" ></ait:message>
				 </td>  
				 <td class="info_content_00" height="30"> 
				<input type="text" name="gradeid" align="left"  size="50"  maxlength="10" onblur="return check();" value="${simplemap.POST_GRADE_ID}"  />    
			    <font color="red">${message}</font></td>     
			</tr>                        
			<tr>                                                               
				 <td class="info_title_01"  height="30"><!-- 职级名 --> 
				 <ait:message messageID="display.name_chinese" ></ait:message>
				 </td>      
				 <td class="info_content_00"  height="30"> 
				 <input type="text" name="postgradename"  maxlength="50" size="50"  value="${simplemap.POST_GRADE_NAME}"/>       
				 </td>
			</tr>
			<tr>           
			   <td class="info_title_01"  height="30"><!--职级英文名  -->
			   <ait:message messageID="display.name_english" ></ait:message>
			   </td>      
				 <td class="info_content_00"  height="30"> 
				 <input type="text" name="postgradeenname"  maxlength="50"  size="50"  value="${simplemap.POST_GRADE_EN_NAME}"/> 
				 </td>
			</tr> 
			<tr>           
			   <td class="info_title_01"  height="30"><!-- 参数 -->
			   <ait:message messageID="display.mutual.parameter" ></ait:message>
			   </td>      
				 <td class="info_content_00"  height="30"> 
				 <input type="text" name="postgradevalue"  maxlength="50"  size="50"  value="${simplemap.POST_GRADE_VALUE}"/> 
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