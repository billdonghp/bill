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
function band(backColor,textColor,PRODUCTSNO,SPECIFICATIONNO,PRODUCTIONNAME,L,W ,H ,SUMPRICE)	{
		
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
		$('PRODUCTSNO').value=PRODUCTSNO;
		$('SPECIFICATIONNO').value=SPECIFICATIONNO;
		$('PRODUCTIONNAME').value=PRODUCTIONNAME;
		$('L').value=L;
		$('W').value=W;
		$('H').value=H;
		$('SUMPRICE').value=SUMPRICE;
 } 
function Add(){
	
	document.form1.action="/gm/gm_woodporductionSet.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
	}
	
function Update(){	
	if($('PRODUCTSNO').value==""||$('PRODUCTSNO').value==null){
		alert("请选择项目！");
	}else{		
	document.form1.action="/gm/gm_woodporductionSet.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
	}	
	
}

function Delete(){

	if($('PRODUCTSNO').value==""||$('PRODUCTSNO').value==null){
		alert("请选择项目！");
	}else{
		if(confirm('确定删除吗？此操作将清除相关信息')){		
		document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=deleteWoodProduction";
		document.form1.submit();
		}
	}	

}
function Search(){
    document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=woodProductionList";
	document.form1.submit();
}

</SCRIPT>

<body>

<form name="form1" method="post" action="">
<input type="hidden" name="PRODUCTSNO" value="">
<input type="hidden" name="SPECIFICATIONNO" value="">
<input type="hidden" name="PRODUCTIONNAME" value="">
<input type="hidden" name="L" value="">
<input type="hidden" name="W" value="">
<input type="hidden" name="H" value="">
<input type="hidden" name="SUMPRICE" value="">
	
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
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		    <tr align="center" bgcolor="#F5F5F5">
		    <td class="info_title_01"  align="center">
		    制品名称
		    </td>
		    <td class="info_content_00"  align="center">
		    <input type="text" name="productionName" value="${productionName}">
		    </td>
		    <td class="info_title_01"  align="center">
		    编制品号
		    </td>
		    <td class="info_content_00"  align="center">
		   <input type="text"  name="specificationNo" value="${specificationNo}">
		    </td>		   
		    <td class="info_content_00"  align="center">
		    <ait:image src="/images/btn/Search.gif" align="right"  onclick="Search()"/>
		    </td>
		    </tr>

		 </table>
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				木制品信息
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
				序号</td>
			 <td nowrap="nowrap" class="info_title_01">
				编制品号</td> 		      
		      <td nowrap="nowrap" class="info_title_01">
				制品名称</td>
			  <td nowrap="nowrap" class="info_title_01">
				制品长</td>
			  <td nowrap="nowrap" class="info_title_01">
				制品寛</td>
			  <td nowrap="nowrap" class="info_title_01">
				制品高</td>
			  <td nowrap="nowrap" class="info_title_01">
				总价格</td>
			 <td nowrap="nowrap" class="info_title_01">
				定义日期</td>			
		    <c:forEach items="${woodProductsList}" var="test" varStatus="i">		   
		
		    <tr onClick="band('#E7F0EF','black','${test.PRODUCTSNO}','${test.SPECIFICATIONNO}','${test.PRODUCTIONNAME}','${test.L}','${test.W}','${test.H}','${test.SUMPRICE}')" height="25">
		     <td align="center" style="color: #666666;" >${i.index+1}</td>
		     <td align="center" style="color: #666666;" >${test.SPECIFICATIONNO}</td>
             <td align="center" style="color: #666666;" >${test.PRODUCTIONNAME}</td>
             <td align="center" style="color: #666666;" >${test.L}</td>
             <td align="center" style="color: #666666;" >${test.W}</td>
             <td align="center" style="color: #666666;" >${test.H}</td>
             <td align="center" style="color: #666666;" >${test.SUMPRICE}</td>
             <td align="center" style="color: #666666;" >${test.CREATE_DATE}</td>
             </tr>		  
		</c:forEach>
		 </table>
		 <ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=wpCommand&content=woodProductionList&menu_code=${param.menu_code}&productionName=${productionName}&specificationNo=${specificationNo}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
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
