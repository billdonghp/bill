<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.ait.utils.Upload"%>
<%@ page import="com.ait.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'uploadExcel.jsp' starting page</title>
</head>
<body>
<%
 String status=StringUtil.checkNull(request.getParameter("status"));  
 out.println("status:"+status);
 if("save".equals(status)){
     out.println("ok");
	 Upload up = new Upload(request,response,config.getServletContext());
	 if (up.getdata()) {
	    up.initFileComents();
	    up.disposeData();
	    up.setFilePath("/evs/excel");
	    up.WriteMdata();
	  }
	  out.println("上传成功。");
  }
  out.println("+    :"+request.getContextPath());
%>
<form name="form1" action="/evs/uploadExcel.jsp" method="post" enctype="multipart/form-data"
	onSubmit="return validate()">
<table align="center">
	<tr>
		<td>文件:</td>
		<td><input type=file name='evemps' size=30 class=form><br>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<input type=submit value="上传" ><br>
		<input type="hidden" name="status" value="save"></td>
	</tr>
</table>
</form>
</body>
</html>
<SCRIPT language = "Javascript">
function validate(){
    var field=document.form1.evemps;
	if (field.value.toLowerCase().indexOf("http://") > -1){
        alert("你必须提供你硬盘上的文件上传");
         return false;
	}
    return true;
}
</script>