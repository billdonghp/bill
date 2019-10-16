<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<%@ page import="com.ait.gm.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<html>
<head>
<title>就餐计划确认</title>
</head>
<script src="../js/prototype.js"></script>
<script src="../js/gmMuli.js"></script>
<SCRIPT type="text/javascript">
function dateSelectClick()
{
	document.form1.action="/gmControlServlet?operation=eatStatistic&menu_code=${param.menu_code}&content=viewConfirm&eateryType="+$('eateryType').value;
 	document.form1.submit();
}
function eateryTypeCheck(){
	document.form1.action="/gmControlServlet?operation=eatStatistic&menu_code=${param.menu_code}&content=viewConfirm&eateryType="+$('eateryType').value;
 	document.form1.submit();
}
function confirmPlan(){
   if(confirm("确认提交统计数据吗？")){
   　　       
 	document.form1.action="/gmControlServlet?operation=eatStatistic&content=updateFlag&updateFlag=1&menu_code=${param.menu_code}&eateryType="+$('eateryType').value;  	
	document.form1.submit();
 		
  }else{
   return;
  }
}

function reConfirmPlan(){
   if(confirm("确认取消已经提交统计数据吗？")){
   　　       
 	document.form1.action="/gmControlServlet?operation=eatStatistic&content=updateFlag&updateFlag=0&menu_code=${param.menu_code}&eateryType="+$('eateryType').value;
	document.form1.submit();
 		
  }else{
   return;
  }
}

function importExcel(){
  	document.form1.action="/gmControlServlet?operation=eatStatistic&content=viewConfirm&flag=excel&menu_code=${param.menu_code}&eateryType="+$('eateryType').value;
	document.form1.submit();
}


</SCRIPT>
<body>

<form name="form1" method="post" action="">
<input type="hidden" name="menu_code" value="ga0707" />
<input type="hidden" name="listsize" value="${fn:length(StatisticList)}" />
<input type="hidden" name="temp" value="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_none.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP">
		
		<!-- display basic info -->
		<br>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">就餐计划确认
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<c:if test="${errorMsg!=null}">
				<td align="center"><font style="color:red;">${errorMsg}</font></td>
				</c:if>		
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	        <tr>
	        <td nowrap="nowrap" class="info_title_01" ><!--  选择日期-->
	          	选择餐别
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <select name="eateryType" onchange="eateryTypeCheck()">
	          <option value="CH" <c:if test="${eateryType=='CH'}">selected</c:if>>中餐</option>
	          <option value="KO" <c:if test="${eateryType=='KO'}">selected</c:if>>韩餐</option>
	          </select>	       
	          </td> 
	         <td nowrap="nowrap" class="info_title_01" ><!--  选择日期-->
	          	选择日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="selectdate" value="${current_date}" onClick="setday(this);" readonly style="width:70px">	
	          </td>  
	          <td nowrap="nowrap" align="center" class="info_content_00">    
	          <input type="button" value="提交统计数据" onclick="confirmPlan()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	          <input type="button" value="取消提交" onclick="reConfirmPlan()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	          <input type="button" value="Excel导出" onclick="importExcel()">
	          </td>
	        </tr>
	     </table>
	     <br>		
		<table width="100%"  border="1" cellspacing="0" cellpadding="8" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		       <tr bgcolor="#F5F5F5">
		 		<td nowrap="nowrap" class="info_title_01" rowspan="2">
		 			部门
		 		</td>	  
			 	
		 		<td  nowrap="nowrap" class="info_title_01" colspan="2">
		 			早餐&nbsp;<span id="break"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01" colspan="2">
		 			午餐&nbsp;<span id="lunch"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01" colspan="2">
		 			晚餐&nbsp;<span id="supper"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01" colspan="2">
		 			夜餐&nbsp;<span id="dinner"></span>
		 		</td>	
		 		<c:if test="${cpnyId=='63000000'}">
		 		<td  nowrap="nowrap" class="info_title_01" colspan="20">
		 			班车
		 		</td>
		 		</c:if>
			    </tr>			    
			   <tr bgcolor="#F5F5F5">
			   <td  nowrap="nowrap" class="info_title_01">
		 			计划<BR>人数
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			刷卡<BR>人数
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			计划<BR>人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			刷卡<BR>人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			计划<BR>人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			刷卡<BR>人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			计划<BR>人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			刷卡<BR>人数
		 		</td>	
		 		<c:if test="${cpnyId=='63000000'}">
		 		<td  nowrap="nowrap" class="info_title_01">
		 			牟平<BR>17点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			牟平<BR>20点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			牟平<BR>21点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			牟平<BR>夜班
		 		</td>
		 		
		 		<td  nowrap="nowrap" class="info_title_01">
		 			开发区<BR>17点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			开发区<BR>20点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			开发区<BR>21点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			开发区<BR>夜班
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			福山<BR>17点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			福山<BR>20点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			福山<BR>21点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			福山<BR>夜班
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山<BR>17点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			莱山<BR>20点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			莱山<BR>21点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山<BR>夜班
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘<BR>17点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			芝罘<BR>20点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			芝罘<BR>21点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘<BR>夜班
		 		</td>
		 		</c:if>		  
			    </tr>			    
			    <c:forEach items="${StatisticList}" var="statisticList" varStatus="i">
			 		<tr style="color: #666666;">
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.DEPTNAME}
				 		<input type="hidden" name="deptid_${i.index}" value="${statisticList.DEPTID}">
				 		</td>			 		
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.BREAKCOUNTS}
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.BREAKRECORDS}
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.LUNCHCOUNTS}
				 		</td>
				 		<td nowrap="nowrap" align="center">
				 		${statisticList.LUNCHRECORDS}
				 		</td>	
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.SUPPERCOUNTS}
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.SUPPERRECORDS}
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.DINNERCOUNTS}
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.DINNERRECORDS}
				 		</td>	
				 		<c:if test="${cpnyId=='63000000'}">
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_SEVEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_ONE}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_SEVENTEEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_SIX}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_TWO}
				 		</td>
				 		<td  nowrap="nowrap" align="center"">
				 			${statisticList.LINE_EIGHT}
				 		</td>
				 		<td  nowrap="nowrap" align="center"">
				 			${statisticList.LINE_SIXTEEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center"">
				 			${statisticList.LINE_TWELVE}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_THREE}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_NINE}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_EIGHTEEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_THIRTEEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_FOUR}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_TEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_TWENTY}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_FOURTEEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_FIVE}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_ELEVEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_NINETEEN}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_FIFTEEN}
				 		</td>
				 		</c:if>								    			   
				  </tr>
			    </c:forEach>
		 </table>
		</form>

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