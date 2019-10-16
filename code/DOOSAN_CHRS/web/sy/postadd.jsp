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
   id=document.PostXForm.postid.value;
    url = "/syControlServlet?operation=ispostidexistcmd&menu_code=${param.menu_code}&postid="+id;             
   location.href = url;                   
}                   
</SCRIPT>        
</head>            
<body>              
<%@ include file="../sy/checkpostisnull.jsp" %>                                                       
 <form action="/syControlServlet" name="PostXForm" >    
 
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
				<td align="left" class="title1" colspan="10"><!-- 岗位职务 -->
			<ait:message messageID="display.mutual.post" ></ait:message>	
				</td>
			</tr>
		</table>     
		 <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">                                                     
			<tr>  
				 <td class="info_title_01" width="20%" height="30"> <!-- 岗位职务ID -->
				 	<ait:message messageID="display.mutual.id" ></ait:message>  
				 </td>  
				 
		      <td class="info_content_00" width="20%" height="30">              
				<input type="text" name="postid" align="left"  size="50"  maxlength="10" value="${postid}" onblur="javascript:check();"/>  
			    <font color="red">${message}</font></td>                                
			</tr>                   
			<tr>                                            
				 <td class="info_title_01" width="15%" height="30"><!-- 岗位职务名 --> 
				 <ait:message messageID="display.name_chinese" ></ait:message>
				 </td>
				 <td class="info_content_00" width="85%" height="30"> 
				 <input type="text" name="postname"  maxlength="50" size="50" />    
				 </td>
			</tr>      
			<tr>          
			   <td class="info_title_01"  height="30"><!--  岗位职务英文名-->
			  <ait:message messageID="display.name_english" ></ait:message>  
			   </td>  
				 <td class="info_content_00"  height="30"> 
				 <input type="text" name="postenname"  maxlength="50"  size="50"  /> 
				 </td>  
			</tr>   
			<tr>                                      
			   <td class="info_title_01"  height="30"><!-- 隶属职群 -->
			  	<ait:message messageID="display.sys.salary.group.job_group" module="sys"></ait:message> 
			   </td>        
			   <td class="info_content_00"  height="30"> 
				<ait:select  name="postgroupid" dataListName="postgrouplist"  selected="${simplemap.POST_GROUP_ID}"/>	         
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

<input name="operation" value="insertpostcmd" type="hidden">    
<input name="menu_code" value="${param.menu_code}" type="hidden">                            
</form>  
</body>
</html>   