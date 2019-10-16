<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=eatConfirmListSum.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center" style="FONT-FAMILY:宋体;font-size:14pt;">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="19"><b><font size="+2" style="FONT-FAMILY:宋体;font-size:14pt;">DISD 就餐和班车人员统计汇总表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" style="FONT-FAMILY:宋体" >
	  
		  <tr>
		  
		    <td class="info_title_01" align="center" nowrap><!-- 职号 -->
		      部门
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--  姓名-->
		       课组
		    </td>
		     <td class="info_title_01" align="center" nowrap><!-- 职号 -->
		      姓名
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--  姓名-->
		      职号
		    </td>
		    <td class="info_title_01" align="center" nowrap><!-- 就餐日期 -->
		     就餐日期
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--(餐次  -->
		     餐次
		    </td>
		    <td class="info_title_01" align="center" nowrap><!-- 就餐类型 -->
		     就餐类型
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--就餐人数  -->
		     就餐人数
		    </td>
		      <td class="info_title_01" align="center" nowrap><!--  开发区-->
			牟平17点
		    </td>  
		    <td class="info_title_01" align="center" nowrap><!--  开发区-->
			牟平20点
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--  开发区-->
			牟平21点
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--  开发区-->
			牟平夜班
		    </td>
		     <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			开发区17点
		    </td>
		   
		    <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			开发区20点
		    </td>
		     <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			开发区21点
		    </td>
		     <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			开发区夜班
		    </td>
		     <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			福山17点
		    </td>
		      <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			福山20点
		    </td>
		      <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			福山21点
		    </td>
		      <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			福山夜班
		    </td>
		    
		     <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			莱山17点
		    </td>
		     <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			莱山20点
		    </td>
		     <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			莱山21点
		    </td>
		     <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			 莱山夜班
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			芝罘17点
		    </td>
		      <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			芝罘20点
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			芝罘21点
		    </td>
		      <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			芝罘夜班
		    </td>
		  </tr>
		  <c:forEach items="${list}" var="all" varStatus="i">
			 <tr>		  
		    <td align="center" >
		        ${all.FOURDEPTNAME}
		    </td>
		    <td  align="center" >
		        ${all.DEPTNAME}
		    </td>
		    <td align="center" >
		       ${all.CHINESENAME}
		    </td>
		    <td  align="center" >
		        ${all.EMPIDNO}
		    </td>
		    <td  align="center" >
		     ${all.COUNT_DATE}
		    </td>
		    <td  align="center" >
		     ${all.COUNTM_TYPE}
		    </td>
		    <td  align="center" >
		     ${all.FOODTYPE}
		    </td>
		    <td  align="center" >
		     ${all.COUNT_NUM}
		    </td>  
		    <td  align="center" >
			 ${all.LINE_SEVEN}
		    </td>
		     <td  align="center" >
			 ${all.LINE_ONE}
		    </td>
		     <td  align="center" >
			${all.LINE_SEVENTEEN}
			</td>
		     <td  align="center" >
			 ${all.LINE_SIX}
		    </td>
		     <td  align="center" >
			 ${all.LINE_TWO}
		    </td>
		    <td  align="center" >
			 ${all.LINE_EIGHT}
		    </td>
		    <td  align="center" >
			 ${all.LINE_SIXTEEN}
		    </td>
		    <td  align="center" >
			 ${all.LINE_TWELVE}
		    </td>
		     <td  align="center" >
			 ${all.LINE_THREE}
		    </td>
		     <td  align="center" >
			 ${all.LINE_NINE}
		    </td>
		     <td  align="center" >
			 ${all.LINE_EIGHTEEN}
		    </td>
		     <td  align="center" >
			 ${all.LINE_THIRTEEN}
		    </td>
		    <td  align="center" >
			 ${all.LINE_FOUR}
		    </td>
		    <td  align="center" >
			 ${all.LINE_TEN}
		    </td>
		    <td  align="center" >
			 ${all.LINE_TWENTY}
		    </td>
		    <td  align="center" >
			 ${all.LINE_FOURTEEN}
		    </td>
		    <td  align="center" >
			 ${all.LINE_FIVE}
		    </td>
		    <td  align="center" >
			 ${all.LINE_ELEVEN}
		    </td>
		     <td  align="center" >
			 ${all.LINE_NINETEEN}
		    </td>
		     <td  align="center" >
			 ${all.LINE_FIFTEEN}
		    </td>
		  </tr>
		  </c:forEach>
		</table>
		</td>
	</tr>
</table>
</body>
</html>