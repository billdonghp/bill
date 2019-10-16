<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hrm.bean.Department,org.apache.log4j.Logger,com.ait.hrm.bean.BasicInfo,com.ait.sqlmap.util.SimpleMap,com.ait.ar.bean.Supervisor,com.ait.ar.dao.SupervisorDAO,com.ait.ar.dao.ArDAOFactory,com.ait.ar.ArCalc,java.util.*,com.ait.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>

<html>
<head>

<ait:processBarJs />
<script src="../js/prototype.js"></script>
<script language="javascript" type="text/javascript">

function importAttRecord() {

	 var startDate = $("startDate").value.trim();
     var endDate = $("endDate").value.trim();
     if(startDate == ""||endDate==""){
         alert("请输入导入日期");
         return;
     }
	    document.Em2Form.action="/arControlServlet?operation=importAttRecord&menu_code=ar0213&startDate="+startDate+"&endDate="+endDate;
	
	
	
		beforeSubmit();
		document.Em2Form.fireSubmit();
		afterSubmit();    
	
	
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
			<%@ include file="../inc/common_toolbar_addsavedel.jsp"%>
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
		<%@ include file="../inc/common_toolbar.jsp"%><br>
		
		<!-- display content -->
		<form action="" method="post" name="Em2Form">
			<input type="hidden" name="menu_code" value="<%=StringUtil.checkNull(request.getParameter("menu_code"))%>">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">刷卡导入</td>
			</tr>
		</table>
		 <table width="100%" height="33"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"  style="padding: 2px 2px 2px 2px;">
		  <tr>
		    <tr align="center">
		    <td height="30"  width="18%" class="info_title_01">导入日期</td>
		    <td height="30"  width="28%" align="left">
		    <input onClick="setday(this);" readonly type="text" name="startDate" class="content"  style="width: 100px " value="">
		     
		      至
		    <input onClick="setday(this);" readonly type="text" name="endDate" class="content"  style="width: 100px " value=""></td>
		    <td height="30"  width="20%" ><ait:image src="/images/btn/submit.gif"  border="0" align="absmiddle" onclick="javascript:importAttRecord();" style="cursor:hand" /></td>
		   </tr>
		 
		 <tr>
		    <td colspan="6" align="center" height="30" valign="middle">

			<ait:processBar />
			&nbsp;<br><br>
		    </td>
		  </tr>
		</table>
		<br></form>

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
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
	</div>

<ait:xjos />
</body>
</html>

