<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>级别详情</title>
</head>
<body>
<% 
String cpnyId = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();

String vistterLevelInfo ="";
		
if(cpnyId.equals("78000000")){
		}else if(cpnyId.equals("63000000")){
		}else if(cpnyId.equals("61000000")){
			vistterLevelInfo ="<strong>VIP</strong>：<br>"+ 
			"1.大型集团CEO<br>"+
			"2.政府机关重要领导 （市长、书记等等）<br>"+
			"<strong>A级：</strong><br>"+
			"1.政府机关副书记、副市长以上<br>"+
			"2.银行、税务，海关等副行长、副局长以上<br>"+
			"3.其他总经理指定的贵宾<br>"+
			"<strong>B级：</strong><br>"+
			"1.政府机关主任以上干部、银行、税务，海关等主任以上级别<br>"+
			"2.代理商、外协主管以上干部、关系单位或其他公司访问客人<br>"+
			"<strong>C级：</strong><br>"+
			"1.其他一般访问客人<br>";
		}else if(cpnyId.equals("59000000")){			
		}else if(cpnyId.equals("60000000")){	
		}else{
			vistterLevelInfo="";
		}		

        
%>
<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
 <tr align="left" bgcolor="#00CC00">
    <td nowrap="nowrap" class="info_title_01">
				级别详情</td>

</tr>

<tr align="left">
 <td nowrap="nowrap" class="info_content_07" ><%=vistterLevelInfo %></td>
 
</tr>

</table>
</body>
</html>