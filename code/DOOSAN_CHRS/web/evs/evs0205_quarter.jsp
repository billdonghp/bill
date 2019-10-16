<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价&gt;评价进行&gt;季度评价</title>
<style type="text/css">
  <!--
    .style1 {color: #FF00FF}
  -->
</style>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%
	int index = 0;
%>
<%@include file="inc/evstoolbar_0205.jsp"%>
<%@include file="inc/evs_nav.jsp"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="20" height="30">&nbsp;</td>
        <td width="100" align="left"><c:out value="${evsEmp.evEmpName}"/>[<c:out value="${evsEmp.evEmpID}" />]</td>
        <td width="400" align="right">当前评价状态[<font color="#0770FF"><c:out value="${evsEmp.evCurrentProcessName}" /></font>]</td>
        <!-- 季度评价不显示分数 -->
        <td align="right">
	        <form action="/evsControlServlet?menu_code=evs0205&operation=retrieveEvaluate&EmpID=${empID}" method="POST" name="evs0205">
		        <select name="Period" onChange="evs0205.submit();">
		          <option value="">评价期间</option>
		          <c:forEach items="${periodList}" var="oneResult">
		          	  <c:choose>
				          <c:when test="${oneResult.evPeriodID == period}">
				          	<option value="${oneResult.evPeriodID}" selected>${oneResult.evPeriodName }</option>
				          </c:when>
				          <c:otherwise>
				          	<option value="${oneResult.evPeriodID}">${oneResult.evPeriodName }</option>
				          </c:otherwise>
			          </c:choose>
		          </c:forEach>
		        </select>
	        </form>
        </td>
    </tr>
</table>

<script language="JavaScript">

document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-173) + ';\">')

</script>

<table width="100%"  >
<tr><td>
<form action="/evs/evs0205_t.jsp" method="POST" name="Evs0205">
  <input type="hidden" name="menu_code" value="evs0205"/>	
  <input type="hidden" name="EmpID" value="${empID}"/>
  <input type="hidden" name="Period" value="${period}"/>
  <input type="hidden" name="Action" value="save"/>
  <input type="hidden" name="Process" value="${evsEmp.evCurrentProcessID}"/>
  
  <c:forEach items="${itemList}" var="oneResult" varStatus="i" >
	  	<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" 
	  		   bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr >
		    <td align="center" width="60" bgcolor="#F5F5F5" nowrap>目标类别</td>
		    <td align="center"  width="30" bgcolor="#F5F5F5" nowrap>序号</td>
		    
		    <!-- 评价列名称 -->
		    <c:forEach items="${columnNameList[i.index]}" var="twoResult" varStatus="j">
			    <c:forEach items="${twoResult}" var="threeResult">
			    	<td align="center" bgcolor="#F5F5F5"><c:out value="${threeResult}"/></td>
			    </c:forEach>
		    </c:forEach> 
		    
		    <!-- 季度评价不显示比重 -->
		    
		    <c:if test="${operate == 'EVOPERATE010'}">
		    	<td align="center" bgcolor="#F5F5F5">内容</td>
		    </c:if>
		    
		  </tr>
		  
		  <tr>
		    <td align="center" rowspan="${fn:length(oneResult) + 1}" bgcolor="#F5F5F5"><c:out value="${itemVector[i.index].ItemName}"/></td>

			<c:forEach items="${oneResult}" var="detailMap" varStatus="k">
				<% index += 1; %>
				<tr>
				    <td align="center">
				    	${k.count}<input type="hidden" name="detail_<%= index %>" value="${detailMap.detailSequence}">
				    </td>
				    
				    <!-- 评价细则内容：季度评价不显示比重和评价分数 -->
				    <c:forEach items="${detailMap.detailContent}" var="oneContent" varStatus="m">
				    	<c:if test="${m.count != fn:length(detailMap.detailContent)}">
				    		<!-- 细则项目名称不转行 -->
					    	<td align="left" 
					    	<c:if test="${operate == 'EVOPERATE010'}">
					    		style="width: 120px; word-wrap: break-word"
					    	</c:if>
					    	<c:if test="${m.index == 0}">
					    		nowrap
					    	</c:if>
					    	 >
					    		&nbsp;<a id="Column${m.count}" >${oneContent}</a>
					    	</td>
				    	</c:if>
				    </c:forEach>
				    
				    <!-- 评价意见输入 -->
				    <c:if test="${operate == 'EVOPERATE010'}">
				    	<td align="left">&nbsp;
					    	<TEXTAREA name="idea_<%= index %>" 
					    	cols="70" rows="5" cols="8" rows="2" 
					    	<c:if test="${isOverPeriod}">
					    		disabled="disabled"
					    	</c:if> >${detailMap.currentIdea}
					    	</TEXTAREA>
				    	</td>
					</c:if>
				</tr>
  			</c:forEach>
  		</tr>
	  </table>
	  <tr><td heigth="1">&nbsp;</td></tr>
  </c:forEach>
  <input type="hidden" name="count" value="<%=index+1%>"/>
  </form>
  </td>
  </tr>
  </table>
  </body>
  <script language="JavaScript">
  
		function CheckProp()
		{	
			return 100;
		}
		
  </script>
  </html>

