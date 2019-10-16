<%@ page contentType="text/html; charset=UTF-8" import =" com.ait.sqlmap.util.SimpleMap"%>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<head>
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=gm_eatery_plan_confirm.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="1" >
			<tr>
			<c:if test="${cpnyId!='63000000'}">
				<td align="center" colspan="9"><font size=5>${current_date} 各部门就餐计划</td>
			</c:if>
			<c:if test="${cpnyId=='63000000'}">
				<td align="center" colspan="20"><font size=5>${current_date} 各部门就餐计划</td>
			</c:if>
			</tr>
		</table>
		<table width="100%"  border="1" cellspacing="0" cellpadding="8" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		       <tr bgcolor="#F5F5F5">
		 		<td nowrap="nowrap" align="center" rowspan="2">
		 			部门
		 		</td>	  
			 	
		 		<td  nowrap="nowrap" align="center" colspan="2">
		 			早餐
		 		</td>
		 		<td  nowrap="nowrap" align="center" colspan="2">
		 			午餐
		 		</td>
		 		<td  nowrap="nowrap" align="center" colspan="2">
		 			晚餐
		 		</td>
		 		<td  nowrap="nowrap" align="center" colspan="2">
		 			夜餐
		 		</td>	
		 		<c:if test="${cpnyId=='63000000'}">
		 		<td  nowrap="nowrap" align="center" colspan="15">
		 			班车
		 		</td>
		 		</c:if>
			    </tr>			    
			   <tr>
			   <td  nowrap="nowrap" align="center">
		 			计划人数
		 		</td>
		 		<td  nowrap="nowrap" align="center">
		 			刷卡人数
		 		</td>
		 		<td  nowrap="nowrap" align="center">
		 			计划人数
		 		</td>
		 		<td  nowrap="nowrap"  align="center">
		 			刷卡人数
		 		</td>
		 		<td  nowrap="nowrap"  align="center">
		 			计划人数
		 		</td>
		 		<td  nowrap="nowrap"  align="center">
		 			刷卡人数
		 		</td>
		 		<td  nowrap="nowrap"  align="center">
		 			计划人数
		 		</td>
		 		<td  nowrap="nowrap"  align="center">
		 			刷卡人数
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
		 		<td  nowrap="nowrap" class="info_title_01">
		 			福山<BR>17点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			福山<BR>20点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			福山<BR>21点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			福山<BR>夜班
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山<BR>17点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山<BR>20点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山<BR>21点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山<BR>夜班
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘<BR>17点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘<BR>20点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘<BR>21点
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘<BR>夜班
		 		</td>
		 		</c:if>		  
			    </tr>			    
			    <c:forEach items="${StatisticList}" var="statisticList" varStatus="i">
			 		<tr bgcolor="#F5F5F5">
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.DEPTNAME}				 		
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
				 		<td  nowrap="nowrap" align="center">
				 			${statisticList.LINE_EIGHT}
				 		</td>
				 		<td  nowrap="nowrap" align="center">
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
</body>
</html>