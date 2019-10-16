<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<jsp:useBean id="allWasteInformation" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="wasteTypeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allCompanyCustomers" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="addViewJumpWasteViewFlag" class="java.lang.String" scope="request" />
<jsp:useBean id="seq" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function search(){

	document.ApplyForm.action="/gmControlServlet?operation=waste&content=wasteView1&menu_code=${param.menu_code}&flag=1";
	document.ApplyForm.submit();
}

function Add() {
	document.ApplyForm.action="/gmControlServlet?operation=waste&content=addWasteView1&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Update() {

	if(document.ApplyForm.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.ApplyForm.action="/gmControlServlet?operation=waste&content=updateWasteView1&menu_code=${param.menu_code}&wasteId="+document.ApplyForm.temp.value;
	document.ApplyForm.submit();
}
function Delete() {
	if(document.ApplyForm.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.ApplyForm.action="/gmControlServlet?operation=waste&content=deleteWasteBasicInformation1&menu_code=${param.menu_code}&wasteId=" + document.ApplyForm.temp.value;
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

function ImpExcel(applyno){
document.ApplyForm.action="/xlsReportServlet?operation=crystal&xlsKey=saleWastes_excel&applyno="+applyno;
document.ApplyForm.submit();
}

function ImportExcel() {
	document.ApplyForm.action="/gmControlServlet?operation=waste&content=wasteView1&menu_code=${param.menu_code}&flag=2";
	document.ApplyForm.submit();	
}
</script> 
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
%>
<form name="ApplyForm" action="/gmControlServlet" method="post">
<input type="hidden" name="addViewJumpWasteViewFlag" value="${addViewJumpWasteViewFlag}"/>
<input type="hidden" name="seq" value="${seq}"/>
<input type="hidden" name="temp" value=""/>
<input type="hidden" name="temp1" value="${content1 }"/>
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
	      	  <td nowrap="nowrap" class="info_title_01">
		         客户公司
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <ait:codeClass name="Company_Customers" codeClass="Company_Customers" all="all" selected="${Company_Customers}"/>
 			  </td>
		      <td nowrap="nowrap" class="info_title_01">
		         废品类别
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
					<select name="wasteType">
						<option value="">
								请选择类别
						</option> 
	 					<c:forEach items="${wasteTypeList}" var="view" varStatus="i">
							<option value="${view.CODE_NAME}" <c:if test="${wasteType==view.CODE_NAME}">selected</c:if>>
								${view.CODE_NAME}
							</option> 						
 						</c:forEach>
 					</select>
 			  </td>
	          <td nowrap="nowrap" class="info_title_01">
		         编号
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          		<input type="text" name="NO" value="${NO}" style="width:80px"/>
	          </td>
	          </tr>
	          <tr>
	          <td nowrap="nowrap" class="info_title_01"><!--  开始日期-->
	          	开始日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<input type="text" style="width:70px" name="StartTime" value="${StartTime}" onClick="setday(this);" readonly="readonly">
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--结束日期  -->
	             结束日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<input type="text" style="width:70px" name="EndTime" value="${EndTime}" onClick="setday(this);" readonly="readonly">
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	&nbsp;
	          </td>
	          <td nowrap="nowrap" align="center" class="info_content_00">	         
	          	<ait:image src="/images/btn/Search.gif" align="absmiddle"
         				onclick="javascript:search();" style="cursor:hand" />	         
	          &nbsp;&nbsp;&nbsp;
          		<img src="/images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImportExcel()"/>
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
					废品查看
				</td>
			</tr>
		</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  <tr>
	    <td class="info_title_01" nowrap>
	      编号
	    </td>
	    <td class="info_title_01" nowrap>
	       登记日期
	    </td>
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
	       数量
	    </td>
	    <td class="info_title_01" nowrap>
	       金额
	    </td>
	    <td class="info_title_01" nowrap>
	       客户公司名称
	    </td>
	    <td class="info_title_01" nowrap>
	       支付方式
	    </td>
	    <td class="info_title_01" nowrap>
	       搬出人员
	    </td>
	    <td class="info_title_01" nowrap>
	       车辆号
	    </td>
	    <td class="info_title_01" nowrap>
	       记录者
	    </td>
	    <td class="info_title_01" nowrap>
	       备注
	    </td>
	    <td class="info_title_01" nowrap>
	       搬出理由
	    </td>
	    <td class="info_title_01" nowrap>
	       搬出证
	    </td>
	  </tr>
	  <c:forEach items="${allWasteInformation}" var="all" varStatus="i">
		  <tr align="center" onClick="band('#E7F0EF','black',${all.WASTE_ID});" >
		    <td nowrap="nowrap" style="color: #666666;">
		    	&nbsp;${all.WASTE_NO}
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.CREATE_DATE}
		    </td>
		 	<td nowrap="nowrap">
		    	&nbsp;${all.WASTE_TYPE}
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.WASTE_UNITS}
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.WASTE_UNITPRICE}&nbsp;元 
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.WASTE_NUMBER}
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.WASTE_TOTAL}&nbsp;元
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;
		    	<c:choose>
		    		<c:when test="${all.COMPANY_CUSTOMERS != null}">
		    		${all.COMPANY_CUSTOMERS}
		    		</c:when>
		    		<c:otherwise>
		    		${all.COMPANY_OTHER}
		    		</c:otherwise>
		    	</c:choose>
		    	
		    	
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.REVENUE_APPROACH }
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.OUT_STAFF }
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.CAR_NO }
		    </td>
		    <td nowrap="nowrap">
		    	&nbsp;${all.CREATE_BY }
		    </td>
		    <td nowrap="nowrap"  width='200' style='word-break:break-all' align="left">
			    ${all.REMARK} &nbsp;
		    </td>
		    <td nowrap="nowrap">
				&nbsp;${all.CITEREASONS} 
		    </td>
		    <td nowrap="nowrap">
				      		<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImpExcel(${all.WASTE_ID});"/>
		    </td>
		  </tr>		 
	  </c:forEach>
	  <input type="hidden" name="currentPage" value="${currentPage}">
	</table>
	
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=waste&content=wasteView1&menu_code=${param.menu_code}&wasteType=${wasteType}&StartTime=${StartTime}&EndTime=${EndTime}&NO=${NO}&Company_Customers=${Company_Customers}"
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
