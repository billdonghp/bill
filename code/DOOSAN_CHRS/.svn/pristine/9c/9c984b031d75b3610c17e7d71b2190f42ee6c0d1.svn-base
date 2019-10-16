<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<jsp:useBean id="basicInformation" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="wasteType" class="java.util.ArrayList" scope="request"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function search(){
//重新搜索时应重置当前页数
	document.ApplyForm.action="/gmControlServlet?operation=waste&content=BasicInformation&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}

function Add() {
	document.ApplyForm.action="/gmControlServlet?operation=waste&content=addWasteBasicInformationView&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Update() {

	if(document.ApplyForm.temp.value=="") {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.ApplyForm.action="/gmControlServlet?operation=waste&content=updateWasteBasicInformationView&menu_code=${param.menu_code}&wasteId="+document.ApplyForm.temp.value;
	document.ApplyForm.submit();
}
function Delete() {
	if(document.ApplyForm.temp.value=="") {
		// "请选择修改项目"
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.ApplyForm.action="/gmControlServlet?operation=waste&content=deleteWaste&menu_code=${param.menu_code}&wasteId=" + document.ApplyForm.temp.value;
		document.ApplyForm.submit();
	}
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
	document.ApplyForm.temp.value=i;
} 
</script> 
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
%>
<form name="ApplyForm" action="/gmControlServlet" method="post">
<input type="hidden" name="temp" value=""/>
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
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
			<tr>
		      	<td width="10%" class="info_title_01"><!--  开始日期-->
		          	适用开始日期
		          </td>
		          <td width="15%" class="info_content_00">
		          	<input type="text" style="width:80px" name="StartTime" value="${StartTime }" onClick="setday(this);" readonly="readonly">
		          </td>
		          <td width="10%" class="info_title_01"><!--结束日期  -->
		              适用结束日期
		          </td>
		          <td width="15%" class="info_content_00">
		          	<input type="text" style="width:80px" name="EndTime" value="${EndTime }" onClick="setday(this);" readonly="readonly">
		          </td>
		          <td width="10%" class="info_title_01"><!--结束日期  -->
		              废品名称
		          </td>
		          <td width="15%" class="info_content_00">
		          	<select name="waste_name">
						<option value="">
								请选择
						</option>
	 					<c:forEach items="${wasteType}" var="view" varStatus="i">
	 						<c:choose>
							<c:when test="${view.CODE_NAME == waste_nameID}">
								<option value="${waste_nameID}" selected>${waste_nameID}</option>
							</c:when>
							<c:otherwise>
								<option value="${view.CODE_NAME}">
									${view.CODE_NAME}
								</option>
							</c:otherwise>
							</c:choose>
 						</c:forEach>
 					</select>
		          </td>
		         <td width="15%" class="info_content_00">
	          		<ait:image src="/images/btn/Search.gif" align="absmiddle"
	         				onclick="javascript:search();" style="cursor:hand" />
		         </td>
	         </tr>
		  </table>
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
					基本信息
				</td>
			</tr>
		</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  <tr>
	    <td class="info_title_01" nowrap>
	       废品名称
	    </td>
	    <td class="info_title_01" nowrap>
	       单位
	    </td>
	    <td class="info_title_01" nowrap>
	       单价
	    </td>
	    <td class="info_title_01" nowrap>
	       适用日期
	    </td>
	    <td class="info_title_01" nowrap>
	       起案根据号
	    </td>
	    <td class="info_title_01" nowrap>
	       登记日期
	    </td>
	    <td class="info_title_01" nowrap>
	      	登记人
	    </td>
	  </tr>
	  <c:forEach items="${basicInformation}" var="all" varStatus="i">
		  <tr align="center" onClick="band('#E7F0EF','black',${all.WASTE_SET_ID});" height="30" >
		    <td align="center" style="color: #666666;">
		    	&nbsp;${all.WASTE_SET_NAME}
		    </td>
		    <td align="center" style="color: #666666;">
		    	&nbsp;${all.WASTE_SET_UNITS}
		    </td>
		    <td align="center" style="color: #666666;">
		    	&nbsp;${all.WASTE_SET_UNITPRICE}&nbsp;元
		    </td>
		    <td align="center" style="color: #666666;">
		    	&nbsp;${all.WASTE_SET_APPLICABLE_DATE}
		    </td>
		    <td align="center" style="color: #666666;">
		    	&nbsp;${all.WASTE_SET_QI_AN_ACCORDING_NO}
		    </td>
		    <td align="center" style="color: #666666;">
		    	&nbsp;${all.WASTE_SET_REGISTRATION_DATE}
		    </td>
		    <td align="center" style="color: #666666;">
		    	&nbsp;${all.WASTE_SET_BY}
		    </td>
		  </tr>
	  </c:forEach>
	   <c:if test="${fn:length(basicInformation) < 10}">
				<c:forEach var="i" begin="1" end="${10-fn:length(basicInformation)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
					</tr>
				</c:forEach>
			</c:if>
	</table>
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=waste&content=BasicInformation&menu_code=${param.menu_code}&wasteName=${wasteName}&StartTime=${StartTime}&EndTime=${EndTime}&passCardNumber=${passCardNumber}"
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
</form>
</body>
</html>
