<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="listMM" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listHH" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="totalVisiter" class="java.lang.String" scope="request" />
<jsp:useBean id="Visiter_Annather" class="java.lang.String" scope="request" />
<jsp:useBean id="Visiter_Korea" class="java.lang.String" scope="request" />
<jsp:useBean id="Visiter_Chinese" class="java.lang.String" scope="request" />

<jsp:useBean id="totalVisiterNum" class="java.lang.String" scope="request" />
<jsp:useBean id="Visiter_AnnatherNum" class="java.lang.String" scope="request" />
<jsp:useBean id="Visiter_KoreaNum" class="java.lang.String" scope="request" />
<jsp:useBean id="Visiter_ChineseNum" class="java.lang.String" scope="request" />


<jsp:useBean id="all_Visiter_Type" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="totalPeopleTypeNum" class="java.lang.String" scope="request" />
<jsp:useBean id="totalPeopleNum" class="java.lang.String" scope="request" />

<jsp:useBean id="StartDate" class="java.lang.String" scope="request" />
<jsp:useBean id="EndDate" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=VisiterTotalReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="10"><b><font size="+2">${StartDate } -- ${EndDate}来访人员汇总表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					来访人员国别统计
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		    <td class="info_title_01" align="center" nowrap>
		      来访人员国别
		    </td>
		    <td class="info_title_01" align="center" nowrap>
		      中国
		    </td>
		    <td class="info_title_01" align="center" nowrap>
		     韩国
		    </td>
		    <td class="info_title_01" align="center" nowrap>
		     其他国家
		    </td>
		    <td class="info_title_01" align="center" nowrap>
		     合计
		    </td>
		  </tr>
			  <tr align="center">
			    <td align="center">
			    	人数
			    </td>
			    <td align="center">
			    	${Visiter_Chinese}
			    </td>
			   <td align="center">
			   		${Visiter_Korea}
			    </td>
			    <td align="center">
			    	${Visiter_Annather}
			    </td>
			    <td align="center">
			    	${totalVisiter}
			    </td>
			  </tr>
			  <tr align="center">
			    <td align="center">
			    	来访次数
			    </td>
			    <td align="center">
			    	${Visiter_ChineseNum}
			    </td>
			   <td align="center">
			   		${Visiter_KoreaNum}
			    </td>
			    <td align="center">
			    	${Visiter_AnnatherNum}
			    </td>
			    <td align="center">
			    	${totalVisiterNum}
			    </td>
			  </tr>
		</table>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					来访人员性质别统计
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  
		  <tr>
	  		<td class="info_title_01" align="center" nowrap>
		    	来访人员性质
		    </td>
		  <c:forEach items="${all_Visiter_Type}" var="allType" varStatus="i">
		   
		    <td class="info_title_01" align="center" nowrap>
		      	${allType.CODE_NAME}
		    </td>
		    </c:forEach>
	   
	    	<td class="info_title_01" align="center" nowrap>
		    	合计
		    </td>
		  </tr>
			  <tr align="center">
				    <td align="center" align="center">
				    	人数
				    </td>
				    <c:forEach items="${all_Visiter_Type}" var="allType" varStatus="j">
				    	<c:forEach items="${allType.all_Visiter_Type_Num}" var="allNum" varStatus="k">
						    <td align="center" align="center">
						    	&nbsp;${allNum.NUM}
						    </td>
				    	</c:forEach> 
				    </c:forEach>
				    <td align="center">
				    	&nbsp;${totalPeopleTypeNum}
				    </td>
			  </tr>
			  
			  <tr align="center">
				    <td align="center">
				    	来访次数
				    </td>
				   
			    	<c:forEach items="${all_Visiter_Type}" var="allType" varStatus="n">
			    		<c:forEach items="${allType.all_Visiter_Type_VisiterNum}" var="allNum" varStatus="m">
						    <td align="center">
						    	&nbsp;${allNum.NUM}
						    </td>
				    	</c:forEach> 
			    	</c:forEach>
			    	
				    <td align="center">
				    &nbsp;${totalPeopleNum}
				    </td>
			  </tr>
			  
		</table>
		</td>
	</tr>
</table>
</body>
</html>