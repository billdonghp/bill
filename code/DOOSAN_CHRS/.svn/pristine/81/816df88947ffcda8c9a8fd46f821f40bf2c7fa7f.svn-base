<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价&gt;评价进行&gt;个人评价</title>
<style type="text/css">
  <!--
    .style1 {color: #FF00FF}
  -->
</style>
</head>
 <script language="JavaScript">
		
		function setMark(curVal,startIndex,length) {
			
			i = 0;
			while(i < length) {
				
				index = startIndex + i;
				temp = "mark_" + index;
				document.getElementById(temp).value = curVal;
				i ++;
			}  
		}
		
  </script>
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
        <td width="150" align="right">&nbsp;当前得分[<font color="#0770FF"><c:out value="${evsEmp.evMark}" /></font>]</td>
        <td width="150" align="right">&nbsp;最终等级[<font color="#0770FF"><c:out value="${evsEmp.evGradeName}" /></font>]</td>
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
		    
		    <!-- 评价列名称（业绩不显示目标设定） -->  
		    <c:forEach items="${columnNameList[i.index]}" var="twoResult" varStatus="j">
			    <c:forEach items="${twoResult}" var="threeResult">
			    	<c:if test="${!(itemVector[i.index].ItemID == 'EVITEM002' and threeResult == '目标设定')}">
			    		<td align="center" bgcolor="#F5F5F5" nowrap><c:out value="${threeResult}"/></td>	
			    	</c:if>
			    </c:forEach>
		    </c:forEach> 
		    
		    <!-- 担当业务和综合意见不显示比重 -->
		    <c:if test="${itemVector[i.index].ItemID != 'EVITEM001' and itemVector[i.index].ItemID != 'EVITEM006'}">
		    	 <td align="center" width="30" bgcolor="#F5F5F5" bgcolor="#F5F5F5" nowrap>比重%</td>
		    </c:if>
		   
		    <!-- 不显示内容输入(综合意见除外) -->
	   		<c:if test="${(itemVector[i.index].ItemID == 'EVITEM001' and operate == 'EVOPERATE001') or 
		    		      (itemVector[i.index].ItemID == 'EVITEM006' and operate == 'EVOPERATE009') or
		    		      (itemVector[i.index].ItemID == 'EVITEM006' and operate == 'EVOPERATE006') }">
		    	<c:choose> 
		    		<c:when test="${operate == 'EVOPERATE001'}">
		    			<td align="center" bgcolor="#F5F5F5" nowrap>内容</td>
		    		</c:when>
		    		<c:otherwise>
		    			<td align="center" bgcolor="#F5F5F5" nowrap>意见</td>
		    		</c:otherwise>  
		    	</c:choose>    
		    </c:if>
		    
		    <!-- 担当业务、综合意见不显示打分 -->
		    <c:if test="${itemVector[i.index].ItemID != 'EVITEM001' and itemVector[i.index].ItemID != 'EVITEM006'}">
			    <c:if test="${operate == 'EVOPERATE003' or operate == 'EVOPERATE005' or operate == 'EVOPERATE009' or operate == 'EVOPERATE006'}" >
			    	<td align="center"  width="25" bgcolor="#F5F5F5" nowrap>打分</td>
			    </c:if>
			</c:if>  
		  </tr>
		  
		  <tr>
		    <td align="center" rowspan="${fn:length(oneResult) + 1}" bgcolor="#F5F5F5"><c:out value="${itemVector[i.index].ItemName}"/></td>

			<c:forEach items="${oneResult}" var="detailMap" varStatus="k">
				<% index += 1; %>
				<tr 
				<c:if test="${operate == 'EVOPERATE001' and itemVector[i.index].ItemID == 'EVITEM002'}">
					onClick="HighLightTR('#99CCFF','black','${detailMap.detailSequence}')" 
				</c:if> >
				
				    <td align="center">
				    	${k.count}<input type="hidden" name="detail_<%= index %>" value="${detailMap.detailSequence}">
				    </td>
				    
				    <!-- 评价细则内容：担当业务和综合意见不显示比重（业绩不显示目标设定,二次评价不显示细则分数） -->
				    <c:forEach items="${detailMap.detailContent}" var="oneContent" varStatus="m">
				    	<c:if test="${itemVector[i.index].ItemID != 'EVITEM001' 
				    	 		  and itemVector[i.index].ItemID != 'EVITEM006'
				    	 		  and !(columnNameList[i.index][m.index] == '二次评价分数' 
								         and fn:length(oneResult) > 1)
								  and !(itemVector[i.index].ItemID == 'EVITEM002' 
								  		 and columnNameList[i.index][m.index] == '目标设定'
								  		 and fn:length(detailMap.detailContent) != m.count)}">
				    		<!-- 细则项目名称不转行 -->
					    	<td align="left" 
					    	<c:if test="${m.index == 0}">
					    		nowrap
					    	</c:if>
					    	 >
					    		&nbsp;<a id="Column${m.count}" >${oneContent}</a>
					    	</td>
				    	</c:if>
				    	
				    	<c:if test="${m.count != fn:length(detailMap.detailContent) 
				    			and (itemVector[i.index].ItemID == 'EVITEM001' or itemVector[i.index].ItemID == 'EVITEM006')}">
				    		<!-- 细则项目名称不转行 -->
					    	<td align="left" 
					    	<c:if test="${operate == 'EVOPERATE001'}">
					    		style="width: 120px; word-wrap: break-word"
					    	</c:if>
					    	<c:if test="${m.index == 0}">
					    		nowrap
					    	</c:if>
					    	 >
					    		&nbsp;<a id="Column${m.count}" >${oneContent}</a>
					    	</td>
				    	</c:if>
				    	
				    	<!-- 二次评价多个详细项目时，只显示单一分数 -->
						<c:if test="${columnNameList[i.index][m.index] == '二次评价分数' 
								         and fn:length(oneResult) > 1
								         and k.count == 1 }">

								<td align="center" rowspan="${fn:length(oneResult) + 1}">
									${oneContent}
				  				</td>
						</c:if>
						
				    </c:forEach>
				    
				    <!-- 不显示内容输入(目标设定流程除外) --> 
		    		<c:if test="${(itemVector[i.index].ItemID == 'EVITEM001' and operate == 'EVOPERATE001') or 
				    		      (itemVector[i.index].ItemID == 'EVITEM006' and operate == 'EVOPERATE009') or
				    		      (itemVector[i.index].ItemID == 'EVITEM006' and operate == 'EVOPERATE006') }">
				    	<td align="left" <c:if test="${operate != 'EVOPERATE001'}">style="width: 20px; word-wrap: break-word"</c:if> >&nbsp;
				    	<TEXTAREA name="idea_<%= index %>" 
				    	cols="65" rows="5"
				    	<c:if test="${isOverPeriod}">
				    		disabled="disabled"
				    	</c:if> >${detailMap.currentIdea}</TEXTAREA>
				    	</td>
				    </c:if>
					
					<!-- 担当业务、综合意见不显示打分 -->
		    		<c:if test="${itemVector[i.index].ItemID != 'EVITEM001' 
		    				  and itemVector[i.index].ItemID != 'EVITEM006'}">
						<!-- 评价分数输入：分数输入 -->
						<c:if test="${operate == 'EVOPERATE009'
						   		   or (operate == 'EVOPERATE006' and fn:length(oneResult) == 1) }">
							<td align="center" style="width: 20px; word-wrap: break-word">&nbsp;
								<TEXTAREA name="mark_<%= index %>" cols="8" rows="2" 
							    	<c:if test="${isOverPeriod}">
							    		disabled="disabled"
							    	</c:if> >${detailMap.evMarkDefault}
							    </TEXTAREA>
							</td>
						</c:if>
						
						<!-- 二次评价多个详细项目时，使用隐藏字段存储详细项目的分数 -->
						<c:if test="${operate == 'EVOPERATE006' and fn:length(oneResult) > 1}">
							<input type="hidden" name="mark_<%= index %>" value="${detailMap.evMarkDefault}" >
							
							<!-- 二次评价多个详细项目时，只评价一次分数 -->
							<c:if test="${k.count == 1 }">
								<td align="center" rowspan="${fn:length(oneResult) + 1}">
				  					<TEXTAREA name="mark_all_${k.count}" cols="4" rows="5" onChange="setMark(this.value,<%= index %>,${fn:length(oneResult)});"
								    	<c:if test="${isOverPeriod}">
								    		disabled="disabled"
								    	</c:if> >${detailMap.evMarkDefault}
									</TEXTAREA>
				  				</td>
							</c:if>
						</c:if>
  			
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

