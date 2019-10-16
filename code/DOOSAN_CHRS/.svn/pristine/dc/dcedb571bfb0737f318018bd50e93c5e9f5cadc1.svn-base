<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="allInformation" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="total" class="java.lang.String" scope="request" />
<jsp:useBean id="StartTime" class="java.lang.String" scope="request" />
<jsp:useBean id="EndTime" class="java.lang.String" scope="request" />

<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function Search(){
//重新搜索时应重置当前页数
	document.ApplyForm.action = "/gmControlServlet?operation=waste&content=monthClearingSearch1&menu_code=${param.menu_code}&flag=1";
	document.ApplyForm.submit();
}


function ImportExcel() {
	document.ApplyForm.action="/gmControlServlet?operation=waste&content=monthClearingSearch1&menu_code=${param.menu_code}&flag=2";
	document.ApplyForm.submit();	
} 
</script> 
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
%>
<form name="ApplyForm" action="" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
<!--			<%@ include file="../inc/common_toolbar_search.jsp"%>-->
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
			      <table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
				      	<tr>
				          <td width="10%" class="info_title_01"><!--  开始日期-->
				          	开始日期
				          </td>
				          <td width="15%" class="info_content_00">
				          	<input type="text" style="width:70px" name="StartTime" value="${StartTime}" onClick="setday(this);" readonly="readonly">
				          </td>
				          <td width="10%" class="info_title_01"><!--结束日期  -->
				             结束日期
				          </td>
				          <td width="15%" class="info_content_00">
				          	<input type="text" style="width:70px" name="EndTime" value="${EndTime}" onClick="setday(this);" readonly="readonly">
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
							<option value="${view.CODE_ID}" <c:if test="${wasteType==view.CODE_ID}">selected</c:if>>
								${view.CODE_NAME}
							</option> 						
 						</c:forEach>
 					</select>
 			  </td>
 			  <td nowrap="nowrap" align="center" class="info_content_00">
				        <ait:image src="/images/btn/Search.gif" align="absmiddle"
         				onclick="javascript:Search();" style="cursor:hand" />	         
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
	       废品名称
	    </td>
	    <td class="info_title_01" nowrap>
	       客户单位
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
	       销售金额（元）
	    </td>
	    <td class="info_title_01" nowrap>
	       登记日期
	    </td>
	    
	  </tr>
	  <c:forEach items="${allInformation}" var="all" varStatus="i">
		  <tr align="center" style="height: 30px">
		 	<td align="center" style="color: #666666;">
		    	${all.WASTE_TYPE}&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">
		    	${all.COMPANY_CUSTOMERS1 }&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">
		    	${all.WASTE_UNITS}&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">
		    	${all.WASTE_UNITPRICE}&nbsp;元
		    </td>
		    <td align="center" style="color: #666666;">
		      	${all.WASTE_NUMBER}&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">
		      	${all.WASTE_TOTAL}&nbsp;元
		    </td>
		    <td align="center" style="color: #666666;">
		      	 ${all.CREATE_DATE }&nbsp;
		    </td>
		  </tr>
	   </c:forEach>
	  <tr style="height: 30px">
	  	<td align="center" style="color: #666666;">
	  		合计
	  	</td>
	  	<td align="center" style="color: #666666;">
	  		&nbsp;
	  	</td>
	  	<td align="center" style="color: #666666;">
	  		&nbsp;
	  	</td>
	  	<td align="center" style="color: #666666;">
	  		&nbsp;
	  	</td>
	  	<td align="center" style="color: #666666;">
	  		&nbsp;
	  	</td>
	  	<td align="center" style="color: #666666;">
	  		${total}&nbsp;元
	  	</td>
	  	<td align="center" style="color: #666666;">
	  		&nbsp;
	  	</td>
	  </tr>
	</table>
		
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
