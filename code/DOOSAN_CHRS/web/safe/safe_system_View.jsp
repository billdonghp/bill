<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="Display_information" class="java.util.ArrayList" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>制度管理</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {
	document.form1.action="/safeControlServlet?operation=rules&content=viewAddFile&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {

	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/safeControlServlet?operation=rules&content=viewUpdateFile&menu_code=${param.menu_code}&upload_id="+document.form1.temp.value+"&menu_id="+document.form1.menuId.value+"&fileaddress="+document.form1.fileaddress.value;
	document.form1.submit();
}
function Delete() {
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/safeControlServlet?operation=rules&content=deleteFile&menu_code=${param.menu_code}&upload_id="+document.form1.temp.value;
		document.form1.submit();
	}
}


function Search(){

	document.form1.action="/safeControlServlet?operation=rules&content=rulesAdmin&menu_code=${param.menu_code}";
	document.form1.submit();
}


function band(backColor,textColor,i,fileaddress,menuId)
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
	
	document.form1.fileaddress.value = fileaddress;
	document.form1.menuId.value = menuId;
} 
//function openmyhref(myhreffileId){
//	document.form1.action="/safeControlServlet?operation=rules&content=fileView&menu_code=${param.menu_code}&myhreffileId="+myhreffileId;
//	document.form1.submit();
//}

function openmyhref(path){
	var patrn1 = /.(ppt)$/;
	if(patrn1.test(path)){
		window.open(path, '', 'toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no, ContentType=response.setContentType("application/msexcel")');
	}else{
		window.open(path, '', 'toolbar=yes, menubar=no, scrollbars=no, resizable=yes,location=no, status=no, ContentType=response.setContentType("application/msexcel")');
	}
	
}

</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">
<input type="hidden" name="menuId" value=""/>
<input type="hidden" style="width: 400" name="fileaddress" value="0">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_all_serch.jsp"%>
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

		<table width="100%" border="1" cellspacing="0" cellpadding="10"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
	      	<tr>
	          <td class="info_title_01"><!--  开始日期-->
	          	文件名 
	          </td>
	          <td class="info_content_00">
	          	<input type="text" name="systemName" value=""/>
	          	&nbsp;&nbsp;
	          	
	          </td>
	        </tr>
	    </table>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
		<br>
			<tr>
				<td align="left" class="title1" colspan="10">文件查看</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  
		    <tr height="30">
		    	<td width="10%" class="info_title_01">
		    		文件名
		    	</td>
		    	<td width="10%" class="info_title_01">
		    		所在目录
		    	</td>
		    	<td width="10%" class="info_title_01">
		    		发布时间
		    	</td>
		    	<td width="10%" class="info_title_01">
		    		实施时间
		    	</td>
		    	<%--<td width="10%" class="info_title_01">
		    		修订时间
		    	</td>
		    	--%><td width="10%" class="info_title_01">
		    		服务器文件名字
		    	</td>
		    </tr>
		    <c:forEach items="${Display_information}" var="view" varStatus="i">
			    <tr align="center" onClick="band('#E7F0EF','black',${view.FILEID},'${view.FILEPATH}','${view.MENUID}')">
			    	 <td width="15%" align="left">
				    	<span  style="color:red; cursor:pointer;" onclick="openmyhref('${view.FILEPATH}')">
				    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${view.FILENAME}&nbsp;
				    	</span>
			        	
			        	<!--<a href="#" target="=_blank" name="path" onclick="openmyhref('${view.FILEPATH}')"><font color="red">${view.FILENAME}</font></a>  -->
				     	
				     </td>
			    	 <td width="15%" height="35px">
			    	 	${view.MENU}&nbsp;
<!--			    	 	<input type="hidden" name="menuId" value="${view.MENUID}"/>-->
			    	 </td>
			    	 <td width="15%"   height="35px">
			    	 	${view.CREATE_DATE}&nbsp;
			    	 </td>
			    	 <td width="15%"   height="35px">
			    	 	${view.IMPLEMENTATION_DATE}&nbsp;
			    	 </td><%--
			    	 <td width="15%"   height="35px">
			    	 	${view.UPDATE_DATE}&nbsp;
			    	 </td>
			    	 --%><td width="15%"   height="35px">
			    	 	${view.FILEID}&nbsp;
			    	 </td>
			    </tr>
		    </c:forEach>
		    
		    <tr align="center"></tr>
		 </table>
		</form>
		
		<!-- Page Navigation Start-->
					<ait:page       
		               link="/safeControlServlet"
		               parameters="&operation=rules&content=rulesAdmin&menu_code=${param.menu_code}" 
		               total="${recordCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->
		
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