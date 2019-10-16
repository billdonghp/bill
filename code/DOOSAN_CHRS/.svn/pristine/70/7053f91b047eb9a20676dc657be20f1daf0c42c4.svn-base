<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="Menu_information" class="java.util.ArrayList" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>目录修改</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {
	document.form1.action="/safeControlServlet?operation=rules&content=menuSet&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {

	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/safeControlServlet?operation=rules&content=viewUpdateMenu&menu_code=${param.menu_code}&menuid="+document.form1.temp.value+"&TopId="+document.form1.TopmenuId.value+"&TopName="+document.form1.TopmenuName.value;
	document.form1.submit();
}
function Delete() {
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择删除项目");
		return;
	}
	document.form1.action="/safeControlServlet?operation=rules&content=deleteMenu&menu_code=${param.menu_code}&menuid="+document.form1.temp.value;
	document.form1.submit();
}


function band(backColor,textColor,i,TopmenuId,TopmenuName)
{
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
	document.form1.temp.value=i;
	
	document.form1.TopmenuId.value = TopmenuId;
	document.form1.TopmenuName.value = TopmenuName;
	
} 
 function openmyhref(myhrefaddress){
 window.open("../"+myhrefaddress,"");
 }

</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">

<input type="hidden" name="TopmenuId" value="">
<input type="hidden" name="TopmenuName" value="">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			
			
			
			<%@ include file="../inc/common_toolbar_all.jsp"%>
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
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">目录查看</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  
		    <tr height="30">
		    	<td width="10%" class="info_title_01" align="center">
		    		目录名称
		    	</td>
		    	<!--<td width="10%" class="info_title_01">
		    		上级目录
		    	</td>
		    	--><td width="10%" class="info_title_01">
		    		发布时间
		    	</td>
		    	<%--<td width="10%" class="info_title_01">
		    		修订时间
		    	</td>
		    	--%><td width="10%" class="info_title_01">
		    		目录显示顺序
		    	</td>
		    	<td width="10%" class="info_title_01">
		    		显示状态
		    	</td>
		    </tr>
		    <c:forEach items="${Menu_information}" var="view" varStatus="i">
		      <c:if test="${view.LEVEL==1}">
			    <tr align="center">
			  </c:if>
			  <c:if test="${view.LEVEL!=1}">
			   <tr align="center" onClick="band('#E7F0EF','black',${view.SYSTEM_ID},'${view.topMenuId}','${view.topMenu}')">
			  </c:if>
			    	 <td width="15%" align="left">
				    	 <c:forEach begin="2" end="${view.LEVEL}" step="1">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 </c:forEach>
				    	 &nbsp;&nbsp;${view.SYSTEM_NAME}&nbsp;
			    	 </td>
			    	 <!--<td width="15%">
				    	 ${view.topMenu}&nbsp;
			    	 </td>
			    	 --><td width="15%">
			    	 	${view.CREATE_DATE}&nbsp;
			    	 </td><%--
			    	 <td width="15%">
			    	 	${view.UPDATE_DATE}&nbsp;
			    	 </td>
			    	 --%><td width="15%">
			    	  <c:if test="${view.LEVEL!=1}">			    	  
			    	 	${view.ORDERNO}		    	
			    	  </c:if>
			    	 &nbsp;
			    	 </td>
			    	 <td width="15%">
			    	 	<c:if test="${view.ACTIVITY eq 1}">
			    	 		显示
			    	 	</c:if>
			    	 	<c:if test="${view.ACTIVITY eq 0}">
			    	 		不显示
			    	 	</c:if>
			    	 </td>
			    </tr>
		    </c:forEach>
		    
		    <tr align="center"></tr>
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
</html>