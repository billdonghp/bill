<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="gmViewList" class="java.util.ArrayList" scope="request" />
<%@ page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>菜品意见</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function Searchsubmit(){
 	document.form1.action="/gmControlServlet?operation=gm_vegetable_opinion&menu_code=${param.menu_code}";
 	document.form1.submit();
}

function ImportExcel(){
 	document.form1.action="/gmControlServlet?operation=gm_vegetable_opinion&menu_code=${param.menu_code}&flag=excel";
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
	          	开始时间 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="startDate" value="${startDate}" onClick="setday(this);" readonly style="width:70px">
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--  开始日期-->
	          	结束时间
	          </td>
	          
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="endDate" value="${endDate}" onClick="setday(this);" readonly style="width:70px">
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
				<td align="left" class="title1" colspan="10">菜品意见</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      
		      <td class="info_title_01" nowrap>填写日期</td>
		      <td class="info_title_01" nowrap>就餐日期</td>
		      <td class="info_title_01" nowrap>餐次</td>
		      <td class="info_title_01" nowrap>菜品名称</td>
		      <td class="info_title_01" nowrap>原料构成</td>
		      <td class="info_title_01" nowrap>制作方法</td>
		      <td class="info_title_01" nowrap>开发担当</td>
		      <td class="info_title_01" nowrap>卫生</td>
		      <td class="info_title_01" nowrap>新鲜度</td>
		      <td class="info_title_01" nowrap>口味</td>
		      <td class="info_title_01" nowrap>制作方法</td>
		      <td class="info_title_01" nowrap>营养</td>
		      <td class="info_title_01" nowrap>其他</td>
		      <td class="info_title_01" nowrap>综合评分</td>
		      <td class="info_title_01" nowrap>填写人</td>
		      <td class="info_title_01" nowrap>所属部门</td>
		      
		    </tr>
		<c:forEach items="${resultList}" var="oneResult" varStatus="i">
		    <tr onClick="band('#E7F0EF','black',${oneResult.OPINION_ID})" height="30">
			  
			      <td align="center" style="color: #666666;">${oneResult.FILL_DATE}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.REPAST_DATE}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.MENU_EATTYPE_NAME}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.VEGET_NAME}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.MATERIAL}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.METHOD}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.MAKER}&nbsp;</td>
			       <td align="center" style="color: #666666;">${oneResult.SANITATION}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.GREENNESS}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.TASTE}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.METHODNAME}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.ALIMENTATION}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.OTHER}&nbsp;</td>
			       <td align="center" style="color: #666666;">${oneResult.GRADE}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.CHINESENAME}&nbsp;</td>
			      <td align="center" style="color: #666666;">${oneResult.DEPTNAME}&nbsp;</td>
			      
		      	
		    </tr>
	

		    </c:forEach>
		    <tr align="center"> </tr>
		 </table>

	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=gm_vegetable_opinion&menu_code=${param.menu_code}" 
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