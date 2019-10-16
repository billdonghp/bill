<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.i18n.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.io.*"%>  
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>密码修改</title>
<style type="text/css">
<!--
.gray {	font-family: "宋体";
	font-size: 12px;
	line-height: 18px;
	color: #666666;
	text-decoration: none;
}
-->
</style>        
</head>

<script language="JavaScript" type="text/JavaScript">
<!--
function check()
{    
 var msg=new Array('<ait:message  messageID="alert.ess.password.key_in_user" module="ess"/>',
                      '<ait:message  messageID="alert.ess.password.modify_password" module="ess"/>',
                      '<ait:message  messageID="alert.ess.password.shorten_password" module="ess"/>',
                      '<ait:message  messageID="alert.ess.password.blank_space_invalid" module="ess"/>',
                      '<ait:message  messageID="alert.ess.password.without_chinese" module="ess"/>',
                      '<ait:message  messageID="alert.ess.password.invalid_password" module="ess"/>',
                      '<ait:message  messageID="alert.ess.password.new_password_empty" module="ess"/>',
                      '<ait:message  messageID="alert.ess.password.not_match" module="ess"/>'
 );
	username = document.frmUserInfo.username.value;
	if (username == "")
		{                    
		alert(msg[0]);  
		document.frmUserInfo.username.focus();               
		return false;  
		}
    if (document.frmUserInfo.oldPassword.value == "")  
    {  
       alert(msg[1]);  
        document.frmUserInfo.oldPassword.focus();
        return false;  
    }
    if (document.frmUserInfo.newPassword.value.length >16)  
    {  
       alert(msg[2]);  
        document.frmUserInfo.newPassword.focus();  
        return false;  
    }
	if(document.frmUserInfo.newPassword.value.indexOf(" ")>-1) {
       alert(msg[3]);  
        document.frmUserInfo.newPassword.focus();  
		return false;
	}
	for(var k=0;k<document.frmUserInfo.newPassword.value.length;k++){
		if(document.frmUserInfo.newPassword.value.charCodeAt(k)>127) {
	        alert(msg[4]);  
	        document.frmUserInfo.newPassword.focus();  
			return false;
		}
	}
    for (newPasswordIndex=0; newPasswordIndex<document.frmUserInfo.newPassword.value.length; newPasswordIndex++)  
    {  
        cCheck = document.frmUserInfo.newPassword.value.charAt(newPasswordIndex);
        if ( cCheck==' ' || cCheck==':' || cCheck=='\'' || cCheck=='"' )  
        {  
          alert(msg[5]);   
            document.frmUserInfo.newPassword.focus();  
            return false;  
        }  
    }
    if (document.frmUserInfo.newPassword.value == "")  
    {  
       alert(msg[6]);   
        document.frmUserInfo.newPassword.focus();  
        return false;  
    }
    strPassword = document.frmUserInfo.newPassword.value;
	if (document.frmUserInfo.newPassword.value != document.frmUserInfo.newPasswordVerify.value)
		{
		alert(msg[7]);  
		document.frmUserInfo.newPassword.focus();
		return false;
		}

return true;          
}

function Save(){

	check();
	document.frmUserInfo.submit();
}
//-->
</script>

<%                                                              
	String adminID = admin.getAdminID();
	String username = request.getParameter("username")!=null?request.getParameter("username"):admin.getUsername();
	String password = admin.getPassword();
	String check = request.getParameter("check_update")!=null?request.getParameter("check_update"):"0";
	int check_update = Integer.parseInt(check);
%>                                                                                        
<body>                                                                                                                                  
<form action="/ess/ess_secret_action.jsp" onSubmit="return check();" name="frmUserInfo">                                                                                                                     
<table width="100%" border="0" cellspacing="0" cellpadding="0">                              
	<tr>                             
		<td width="15"></td>                                                                                  
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ess/inc/ess_toolbar_a.jsp"%>
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
				<td align="left" class="title1" colspan="10"><!-- 密码修改 -->
				 <ait:message messageID="display.ess.password.modification" module="ess"></ait:message>
				</td>
			</tr>
		</table>
		<input name="menu_code" value="<%=menu_code%>" type="hidden">
		<TABLE align="center" width="66%" height="40" border="0" cellpadding="0" cellspacing="0" background="images/img03.gif">
		  <TR>
		    <TD width="49" align="left"><STRONG><FONT color="#BFB901"><!--  --></FONT></STRONG></TD>
		    <TD width="10">&nbsp;</TD>
		    <TD width="586" ><font color=#FF0000 size="3"><!-- 说明:输入原密码和新密码，密码最长16位 -->
		    <ait:message messageID="display.ess.password.attention" module="ess"></ait:message>
		    </font>
		    </TR>
		</TABLE>
		<table align="center" width="66%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
		    <td width="31%" height="30"  class="info_title_01"><!-- 员工号 -->
		   <ait:message messageID="display.mutual.emp_id_2" module="ess"></ait:message>   
		    </td>
		    <td width="69%" height="30"  align="center"><%=adminID%>&nbsp;<input type="hidden" name="empid" value="<%=adminID%>"></td>
		    </tr>
		   <tr>
			<td width="31%" height="30"  class="info_title_01"><!--  用户名-->
			 <ait:message messageID="display.ess.password.user" module="ess"></ait:message>
			</td>
		    <td width="69%" height="30"  align="center"><%=username%>
		    <input type="hidden" name="username" value="<%=username%>">
		    </td>
		    </tr>           
		   <tr>
		     <td height="30"  class="info_title_01"><!-- 旧密码 -->
		      <ait:message messageID="display.ess.password.original_password" module="ess"></ait:message>
		     </td>
		     <td height="30"  align="center"><input type="password" name="oldPassword"></td>
		    </tr>
		   <tr>
		     <td height="30"  class="info_title_01"><!-- 密码 -->
		      <ait:message messageID="display.ess.password.password" module="ess"></ait:message>
		     </td>
		     <td height="30"  align="center"><input type="password" name="newPassword"></td>
		    </tr>
		   <tr>              
		     <td height="30"  class="info_title_01"><!--密码确认  -->
		    <ait:message messageID="display.ess.password.retype" module="ess"></ait:message>  
		     </td>
		     <td height="30"  align="center"><input type="password" name="newPasswordVerify"></td>
		    </tr>
		  
		</table>
		</form>
		<% 
			if (check_update == -3){
		%>
			<font color="#FF0000" size="2"><i><!-- 修改未成功，输入的原密码不正确！ -->
			<ait:message messageID="alert.ess.password.fail_to_modify" module="ess"></ait:message>
			</i></font>
		<%}%>
		
		<% 
			if (check_update == -2){
		%>
			<font color="#FF0000" size="2"><i><!--修改未成功，此用户名已经被使用，请重新选择用户名！  -->
				<ait:message messageID="alert.ess.password.user_in_use" module="ess"></ait:message>
			</i></font>
		<%}%>
		
		<%     
			if (check_update == 1){
		%>
			<font color="#00CC00" size="2"><b><i><!--  用户名和密码修改成功，建议使用新用户名和密码重新登录！-->
				<ait:message messageID="alert.ess.password.login_again" module="ess"></ait:message>
			</i></b></font>
		<%}%>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:forEach var="i" begin="1" end="5"
				step="1">
				<tr>
					<td class="info_content_01" height="30"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
				</tr>
			</c:forEach>
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

</body>
</html>
