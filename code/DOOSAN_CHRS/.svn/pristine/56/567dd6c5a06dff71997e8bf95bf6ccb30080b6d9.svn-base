<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="../inc/meta.jsp"%>
<script src="../js/prototype.js"></script>
<head>
<title>上传图片</title>
<script>
<%if(request.getParameter("Directive")!=null && request.getParameter("Directive")!=""){%>
window.close();
<%}%>

</script>
<script language="javascript">
function checkPotosExits(){
   var temp = document.form1.jpgUpload.value;
   if(temp!=null && temp!=""){
       if((temp.indexOf(".jpg")==-1) && (temp.indexOf(".gif")==-1) && (temp.indexOf(".JPG")==-1) && (temp.indexOf(".GIF")==-1)){ 
        return true;
       }   
   }else{
     return false;
   }
}
function submitImp(){
 if(checkPotosExits()){
	alert("只能上传jpg或者gif的图片！");
	return;
	}else{
	document.form1.action="/gaControlServlet?operation=securityEnvironment&content=uploadImp&documentno="+encodeURIComponent(document.form1.documentno.value)+"&jpgUpload="+encodeURIComponent(document.form1.jpgUpload.value);
	document.form1.submit();	
	}

}
 
</script>
</head>

<body>
<form name="form1" method="post" action="" enctype="multipart/form-data">
<input type="hidden" name="documentno"
	value="<%=request.getParameter("documentno")%>">
<table width="100%" border="1" cellspacing="0" cellpadding="5"
	bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">

	<tr>
		<td nowrap="nowrap" class="info_title_01">现场照片</td>
		<td><input type="file" name="jpgUpload" value=""
			style="width:400px" title="上传">&nbsp;&nbsp;&nbsp;</td>
		<td><input id="submitButton" type="button" value="上传"
			onclick="submitImp()" /></td>
	</tr>
</table>
</form>
</body>
</html>
