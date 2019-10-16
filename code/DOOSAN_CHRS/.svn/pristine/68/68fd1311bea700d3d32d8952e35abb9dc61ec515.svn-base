<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.gm.bean.*,java.util.*" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="woodProductsList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="wp" class="com.ait.gm.bean.WoodProducts"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>木制品基本信息登录</title>
</head>

<SCRIPT type="text/javascript">
function band(backColor,textColor,temp)	{
		
		var t;
		 if(typeof(preEl)!='undefined')
		 {
		 preEl.bgColor=orgBColor;
	
		 try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
		 }
		 var el = event.srcElement;
		 el = el.parentElement;
		 orgBColor = el.bgColor;
		 orgTColor = el.style.color;
		 el.bgColor=backColor;
		 try{ChangeTextColor(el,textColor);}catch(e){;}
		 preEl = el;
		 document.form1.tempforselect.value=temp;
	} 
function Add(){
	
	document.form1.action="/gmControlServlet?menu_code=ga0502&operation=wpCommand&content=addSealproductionSetPageView";
	document.form1.submit();
	}
	
function Update(){
	if(document.form1.tempforselect.value==""||document.form1.tempforselect.value==null){
		alert("请选择项目！");
	}else{		
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=getSealproductionObject&seqId="+$('tempforselect').value;
	document.form1.submit();
	}	
	
}

function Delete(){

	if(document.form1.tempforselect.value==""||document.form1.tempforselect.value==null){
		alert("请选择项目！");
	}else{
	if(confirm('确定删除吗？此操作将清除相关信息')){		
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=deleteSealproductionSet";
	document.form1.submit();
	}
	}	

}


</SCRIPT>

<body>

<form name="form1" method="post" action="">
<input type="hidden" name="tempforselect" value="">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/gm_toolbar_all.jsp"%>
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
		<br>	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				制品查看页面
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>		      
		      <td nowrap="nowrap" class="info_title_01">
				名称</td>
			  <td nowrap="nowrap" class="info_title_01">
				单价</td>
			 <td nowrap="nowrap" class="info_title_01">
				详细信息</td>
			<td nowrap="nowrap" class="info_title_01">
				定义日期</td>
			 </tr>
		    <c:forEach items="${woodProductsList}" var="test" varStatus="i">		   
		
		    <tr onClick="band('#E7F0EF','black','${test.WOODPRODUCTS_ID}')" height="25">
		     <td align="center" style="color: #666666;" >${i.index+1}</td>
             <td align="center" style="color: #666666;" >${test.PRODUCTS_NAME}</td>
             <td align="center" style="color: #666666;" >${test.MEASUREMENT_UNIT_PRICE}</td>
             <td align="center" style="color: #666666;" >${test.NOTES}&nbsp;</td>
             <td align="center" style="color: #666666;" >${test.CREATE_DATE}</td>
             </tr>		  
		</c:forEach>
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
</form>
</body>
<ait:xjos />
</html>
