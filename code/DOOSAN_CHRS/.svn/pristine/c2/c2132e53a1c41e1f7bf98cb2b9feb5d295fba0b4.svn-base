<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
	<%@ page import="java.util.Locale"%>
	<%@ include file="./inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>DICC 人事系统</title>
<link href="./css/login.css" rel="stylesheet" type="text/css" />
<%	
	String msg1 = "用户名和密码不能为空";
	String msg2 = "用户名不能为空";
	String msg3 = "密码不能为空";
	String msg4 = "请输入用户名和密码登录系统";
	Locale locale = request.getLocale(); 
/*	
	if(!locale.getLanguage().equals("zh")){
		msg1 = "User ID and password cannot be empty.";
		msg2 = "User ID cannot be empty.";
		msg3 = "Password cannot be empty.";
		msg4 = "Please enter User ID and Password";
	}
*/
	
%>
<script language='javascript' type="text/javascript">
function focusInput(){
	//document.getElementById('username').focus()
	
}   

function onSubmit(){
	var username = document.getElementById('username')
    var pwd = document.getElementById('password') 
  	var msg = ''
    if(username.value.replace(/(^\s*)|(\s*$)/g,"").length == 0)
    	if(pwd.value.replace(/(^\s*)|(\s*$)/g,"").length == 0)
    		msg='<%=msg1%>'
    	else 
    		msg='<%=msg2%>'
    else
    	if(pwd.value.replace(/(^\s*)|(\s*$)/g,"").length == 0)	
    		msg='<%=msg3%>'
   
    if(msg!=''){
    	alert(msg); 
    }else{
		regist.submit()    
	}
}
       
</script>
</head>
<body onload="focusInput();">
<form name="regist" action="/controlServlet" method="post">
<input type="hidden" name="operation" value="login">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="middle" ><table width="631" height="443" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td background="images/login_r2_c2.jpg"><table width="631" height="428" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="58" height="226">&nbsp;</td>
            <td width="294" valign="bottom"></td>
            <td width="279">&nbsp;</td>
          </tr>
          <tr>
            <td height="54">&nbsp;</td>
            <td align="left" valign="top"><table width="0" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td height="10" valign="middle" class="zuozi">此用户登录信息存在问题，请关闭此页面，并及时与信息部取得联系！</td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
