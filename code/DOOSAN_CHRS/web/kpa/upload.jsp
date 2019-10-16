<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.*,com.ait.util.StringUtil"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function send(target){
document.param_data.submit();
}
</script>
	
<body>
<% 
	String param_no = StringUtil.checkNull(request.getParameter("param_no"));
%> <a href="javascript:send('insert');" ><img src="/images/btn/save1.gif" width="67" height="24" border="0" align="absmiddle"></a></div>
<table border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="param_data" action="param_data_u.jsp" method="post" target="param_date">
  <tr align="center">
    <td height="30" align="center" bgcolor="#F5F5F5" width="300">数据</td>
  </tr><input name="param_no"  type="hidden" value="<%=param_no%>">
 <tr align="center" >
   <td  align="center"><textarea name="return_string" class="content" style="width:290px; height:400px "></textarea></td>
 </tr>
</form>
</table>
</body>
</html>