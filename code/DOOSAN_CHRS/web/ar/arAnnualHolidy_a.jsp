<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<script language="javascript">
function Save()
{
	document.form1.action="/arControlServlet?operation=ar_add&content=annual&menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.fireSubmit(); 
}
function SearchEmp(condition,id){
	if(condition!=''){
		var inputBox = document.getElementById(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		
		while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
		
		layer=document.getElementById("showsearch");
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;
		layer.style.visibility='visible';
		document.emp_list.location.href = "/inc/ArSearchEmployee.jsp?content="+encodeURIComponent(condition)+"&id=" + id;
		
	} else { 
		document.all.showsearch.style.visibility='hidden';
	}
}
</script>
</head>
<body>

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
		<form name="form1" method="post" action="">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">年假</td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01">工号</td>
		      <td width="85%" class="info_content_00">
		      <input onKeyUp="SearchEmp(this.value,this.id)" id="empID" name="empID" type="text" size="10" maxlength="10" required /></td>
		    </tr>
		    <tr>
		      <td class="info_title_01">总年休假</td>
		      <td class="info_content_00"><input name="yearDays" type="text" size="10" maxlength="10" required numeric/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01">活跃</td>
		      <td class="info_content_00"><input type="radio" name="activity" value="1" checked />
		        是
		        <input type="radio" name="activity" value="0" />
		        否</td>
		    </tr>
		    <tr>
		      <td class="info_title_01">年假时间</td>
		      <td class="info_content_00"><select name="holidyDate" class="pamonth">
		          	<%for (int i=-4;i<=4;i++){%>
		       				<%if(Integer.parseInt(request.getParameter("year")) == (Integer.parseInt(request.getParameter("year")) + i)) {%>
		          			<option value="<%=Integer.parseInt(request.getParameter("year")) + i%>" selected><%=Integer.parseInt(request.getParameter("year"))+ i %></option>
		          			<%}else {%>
		          			<option value="<%=Integer.parseInt(request.getParameter("year")) + i%>"><%=Integer.parseInt(request.getParameter("year"))+ i %></option>
		          	<%}} %></td>
		    </tr>
		  </table>
		</form>

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
<div id='showsearch' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
	<iframe width="370" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0" name="emp_list" ></iframe>
</div>
<ait:xjos />
</html>
