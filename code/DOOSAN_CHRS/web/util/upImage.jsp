<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
                <td height="11"><img src="/manage/images/a_point4.gif" width="11" height="5" align="absmiddle"><font color="4B4B4B"><b>上传图片</b></font></td>
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
					 <td colspan="4" class="font1" style="padding-left:40px"><font color="red">
                  <b>上传文件类型：.xls / .txt/ .doc/ .ppt ,上传文件总合不能大于15M</b></font></td>
				</tr>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="3"></td>
				</tr>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
				</tr>
				<tr>
					<td align=left bgcolor="F2F2F2" width="150" class="font1" style="padding-left:40px"><b>文件1</b></td>
					<td colspan="3" style="padding-left:40px" class="ir"><input type="file" name="file1" class="box" size="50"></td>
				</tr>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
				</tr>
				<tr>
					<td align=left bgcolor="F2F2F2" width="150" class="font1" style="padding-left:40px"><b>文件2</b></td>
					<td colspan="3" style="padding-left:40px" class="ir"><input type="file" name="file2" class="box" size="50"></td>
				</tr>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
				</tr>
				<tr>
					<td align=left bgcolor="F2F2F2" width="150" class="font1" style="padding-left:40px"><b>文件3</b></td>
					<td colspan="3" style="padding-left:40px" class="ir"><input type="file" name="file3" class="box" size="50"></td>
				</tr>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
				</tr>
				<tr>
					<td align=left bgcolor="F2F2F2" width="150" class="font1" style="padding-left:40px"><b>文件4</b></td>
					<td colspan="3" style="padding-left:40px" class="ir"><input type="file" name="file4" class="box" size="50"></td>
				</tr>
				<tr>
				  <td colspan="4" bgcolor="#ABABAB" height="3"></td>
				</tr>
				<tr>
				  <td colspan="4" height="20"></td>
				</tr>
				<tr>
					<td>
					</td>
					<td align="center">
						<input  name="确定"  type="submit" value="确定">
					</td>
					<td>
					  	<input name="重置" type="reset" value="重置"></td>
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
