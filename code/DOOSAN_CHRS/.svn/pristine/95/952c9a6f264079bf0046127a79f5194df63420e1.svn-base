<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="gmViewList" class="java.util.ArrayList" scope="request" />
<%@ page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>就餐异常查看</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function Searchsubmit(){
 	document.form1.action="/gmControlServlet?operation=eateryExceptionView&menu_code=${param.menu_code}";
 	document.form1.submit();
}

function ImportExcel(){
 	document.form1.action="/gmControlServlet?operation=eateryExceptionView&menu_code=${param.menu_code}&flag=excel";
 	document.form1.submit();
}




function band(backColor,textColor,i)
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
} 


</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">
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
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	        <tr>
	         <td nowrap="nowrap" class="info_title_01" ><!--  开始日期-->
	          	就餐日期 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="eateryDate" value="${eateryDate}" onClick="setday(this);" readonly style="width:70px">
	          </td>
	         

			 <td nowrap="nowrap" class="info_title_01">
				<img src="../images/btn/Search.gif" onClick="Searchsubmit()">
			</td>
			<td nowrap="nowrap" class="info_title_01">
				<img src="/images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImportExcel()"/>
			</td>
	          
	        </tr>
	        </table>
		
		<br>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">就餐异常查看</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      <td class="info_title_01" nowrap>部门</td>
		      <td class="info_title_01" nowrap>姓名</td>
		      <td class="info_title_01" nowrap>职号</td>
		      <td class="info_title_01" nowrap>餐别</td>
		      <td class="info_title_01" nowrap>就餐时间</td>
		      <td class="info_title_01" nowrap>考勤时间</td>
		     
		      
		    </tr>
		<c:forEach items="${resultList}" var="oneResult" varStatus="i">
		    <tr  height="30">
			  
			      <td align="center" style="color: #666666;">${oneResult.DEPTNAME}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.CHINESENAME}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.EMPID}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.EATERYTYPE}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.EATERYTIME}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.MACTIME}&nbsp;</td>	
		    </tr>
	

		    </c:forEach>
		    <tr align="center"> </tr>
		 </table>

	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=eateryExceptionView&menu_code=${param.menu_code}&eateryDate=${eateryDate}" 
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