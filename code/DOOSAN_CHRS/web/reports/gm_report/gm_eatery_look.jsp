<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="eatLookList" class="java.util.ArrayList" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=eateryRecord.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center" style="FONT-FAMILY:宋体;font-size:14pt;">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="8"><b><font size="+2" style="FONT-FAMILY:宋体;font-size:14pt;">就餐刷卡记录</font></b></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" style="FONT-FAMILY:宋体" >
	  
		  <tr>
		  
		    <td class="info_title_01" align="center" nowrap><!-- 职号 -->
		       职号
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--  姓名-->
		       姓名
		    </td>
		    <td class="info_title_01" align="center" nowrap><!-- 部门 -->
		      部门
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--(就餐)卡类别  -->
		     就餐卡类别
		    </td>
		    <td class="info_title_01" align="center" nowrap><!-- 就餐时间 -->
		     就餐时间
		    </td>
		    <td class="info_title_01" align="center" nowrap><!-- 就餐类型 -->
		     就餐类型
		    </td>
		    <td class="info_title_01" align="center" nowrap><!--班次  -->
		     班次
		    </td>  
		    <td class="info_title_01" align="center" nowrap><!--  员工类别-->
			员工类别
		    </td>
		  </tr>
		  <c:forEach items="${eatLookList}" var="all" varStatus="i">
			  <tr align="center">
			    <td align="center">
		    	${all.EMPID}
		    </td>
		    <td align="center">
		    	${all.CHINESENAME}
		    </td>
		    <td align="center">
		    	${all.DEPTNAME}
		    </td>
		   <td align="center">
		    	${all.TYPE_NAME}
		    </td>
		    <td align="center">
		    	${all.RTIME}
		    </td>
		    <td align="center">
		    	${all.EATERYTYPE}
		    </td>
		     <td align="center">
		    	${all.SHIFTNAME}
		    </td>
		    <td align="center">
		    	${all.CODE_NAME}
		    </td>
			  </tr>
		  </c:forEach>
		</table>
		</td>
	</tr>
</table>
</body>
</html>