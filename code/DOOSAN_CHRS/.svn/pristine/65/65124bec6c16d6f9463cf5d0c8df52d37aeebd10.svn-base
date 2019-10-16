<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.ait.utils.*"%>
<%@ page import="java.io.*"%> 
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
	Func func= new Func();
	String File1Name = request.getParameter("File1Name");
    String File1Url = request.getParameter("File1Url");
	String File2Name = request.getParameter("File2Name");
    String File2Url = request.getParameter("File2Url");
	String File3Name = request.getParameter("File3Name");
    String File3Url = request.getParameter("File3Url");
	String File4Name = request.getParameter("File4Name");
    String File4Url = request.getParameter("File4Url");
%>
<html>
<head>
<title>上传图片</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr> 
		<td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td width="27">&nbsp;</td>
          <td height="30" class="admin1" width="711">&nbsp;</td>
          <td width="32">&nbsp;</td>
        </tr>
        <tr> 
          <td height="5"><img src="/manage/images/0.gif" width="27" height="5"></td>
          <td height="5" valign="top" bgcolor="#E1E1E1" width="711"> <img src="/manage/images/0.gif" width="5" height="5"></td>
          <td height="5" valign="top"><img src="/manage/images/0.gif" width="5" height="5"></td>
        </tr>
        <tr> 
          <td>&nbsp;</td>
          <td valign="top"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="20"></td>
              </tr>
              <tr> 
                <td height="11"><img src="/manage/images/a_point4.gif" width="11" height="5" align="absmiddle"><font color="4B4B4B"><b>请复制以下路径到您需要插入图片的地方。</b></font></td>
              </tr>
              <tr> 
                <td height="11"></td>
              </tr>
              <!-- add -->
              <tr> 
                <td>

			<table width="100%">
			<form enctype="multipart/form-data" method = "post" name="sf" action="/upImage?flag=insert">
                <tr>
					 <td colspan="4" class="font1" style="padding-left:40px"><font color="red">&nbsp;</font></td>
				</tr>
				<tr> 
				  <td colspan="4" bgcolor="#ABABAB" height="3"></td>
				</tr>
				<% if (!File1Url.equals("")) { %>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
				</tr>
				<tr>
					<td align=left bgcolor="F2F2F2" width="150" class="font1" style="padding-left:40px"><b>图片1：<%=File1Name%></b></td>
					<td colspan="3" style="padding-left:40px" class="font1">/upload/files/<%=File1Url%></td>
				</tr>
				<% } %>
				<% if (!File2Url.equals("")) { %>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
				</tr>
				<tr>
					<td align=left bgcolor="F2F2F2" width="150" class="font1" style="padding-left:40px"><b>图片2：<%=File2Name%></b></td>
					<td colspan="3" style="padding-left:40px" class="font1">/upload/files/<%=File2Url%></td>
				</tr>
				<% } %>
				<% if (!File3Url.equals("")) { %>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
				</tr>
				<tr>
					<td align=left bgcolor="F2F2F2" width="150" class="font1" style="padding-left:40px"><b>图片3：<%=File3Name%></b></td>
					<td colspan="3" style="padding-left:40px" class="font1">/upload/files/<%=File3Url%></td>
				</tr>
				<% } %>
				<% if (!File4Url.equals("")) { %>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
				</tr>
				<tr>
					<td align=left bgcolor="F2F2F2" width="150" class="font1" style="padding-left:40px"><b>图片4：<%=File4Name%></b></td>
					<td colspan="3" style="padding-left:40px" class="font1">/upload/files/<%=File4Url%></td>
				</tr>
				<% } %>
				<tr> 
				  <td colspan="4" bgcolor="#ABABAB" height="3"></td>
				</tr>
				<tr> 
				  <td colspan="4" height="20"></td>
				</tr>
				<tr>
					<td>
					</td>
					<td align="center">&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
				</tr>
			  </form>
			</table>

				</td>
              </tr>
              <!-- add -->
            </table>
          </td>
          <td valign="bottom">&nbsp;</td>
        </tr>
      </table> 
	</td>
	</tr>
</table>
</body>
</html>
