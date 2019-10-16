<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<head>
<title>上传扫描文件</title>
<script>
<%if(request.getParameter("Directive")!=null && request.getParameter("Directive")!=""){%>
window.close();
<%}%>

</script>
<script language="javascript">
function submitImp(){ 
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=uploadVisiterDocuments&documentno="+encodeURIComponent(document.form1.documentno.value);
	document.form1.submit();	
}
	var type = null;
	var tableUtil = new Object();	
tableUtil.appendRow = function(){
       var i = Number(document.form1.isVarNum.value);		
		var nTr = document.getElementById('operateTable').insertRow();
		
		td = nTr.insertCell() ;		
		td.innerHTML ="<input type='file' name='jpgUpload"+i+"' value='' style='width:400px' title='上传'> <input type='hidden' name='numberArray"+i+"'  value='"+i+"'/>  <a href='#' onClick='tableUtil.deleteRow("+i+");'><img src='/images/left_menubullet_sub_m.gif' width='15' height='20' alt='删除该行' align='absmiddle'></a>";
		document.form1.isVarNum.value = Number(document.form1.isVarNum.value) + 1;		
	}
	
	tableUtil.deleteRow = function(temp){		
		var tbody = document.getElementById('operateTable').tBodies[0];	
		var deleteFlag=document.form1["numberArray"+temp];	
	    tbody.removeChild(deleteFlag.parentNode.parentNode);
	}

</script>
</head>

<body>
<form name="form1" method="post" action="" enctype="multipart/form-data">
<input type="hidden" name="isVarNum" value="0">	
<input type="hidden" name="documentno" value="<%=request.getParameter("documentno")%>">
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d" align="left">
	<tr>
		<td>
		<table id="operateTable" width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d" align="left">
			<tr>
				<td style="width: 500px" align="center"><font size="3" style="font-weight:900">上传扫描文件</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
				<a href='#' onclick="tableUtil.appendRow();"><img src='/images/btn/continueAdd.gif' alt='继续添加' align='absmiddle'></a>
				</td>
			</tr>
			<tr>
				<td><input type="file" name="jpgUpload" value="" style="width:400px" title="上传">&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d" align="left">
			<tr>
				<td><input id="submitButton" type="button" value="上传扫描文件"	onclick="submitImp()" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>