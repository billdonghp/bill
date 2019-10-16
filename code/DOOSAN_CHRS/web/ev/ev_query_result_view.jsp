<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ev.bean.EvaluateInfo"%>
<%@ page import="com.ait.ev.bean.EvaluateAffirmor"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil,com.ait.util.DateUtil" %>
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="pageControl" class="com.ait.ess.bean.PageControl" scope="request" />
<jsp:useBean id="evaluateList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="evaluateInfo" class="com.ait.ev.bean.EvaluateInfo" scope="page" />
<jsp:useBean id="evAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="evAffirmor" class="com.ait.ev.bean.EvaluateAffirmor" scope="page" />

<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#leftnewstd .ellipsis_row{width:40px}
.ellipsis_row{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}
#leftnewstd .ellipsis_row2{width:70px}
.ellipsis_row2{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}
</style>
</head>
<body>

<script language="javascript1.5" type="text/javascript">


function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.action = "/evControlServlet";
	document.ApplyForm.content.value="evaluateQueryResult";
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.submit();
}





function exportExcel()
{
	document.ApplyForm.action = "/evControlServlet";
	document.ApplyForm.content.value = "evaluateQueryResultExcel";
	document.ApplyForm.submit();
}

function exportExcelSum()
{
	document.ApplyForm.action = "/evControlServlet";
	document.ApplyForm.content.value = "evaluateQueryResultExcelSum";
	document.ApplyForm.submit();
}



</script>
<%
   String qryType = request.getAttribute("qryType").toString(); 

%>
<%! String selected(String valueSel, String value){
      return valueSel.equals(value) ? "selected" : "";
    }
 %>
<form name="ApplyForm" action="" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/common_toolbar_search.jsp"%>
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
		            <td nowrap="nowrap" class="info_title_01"><!-- 评价月份-->
	          	评价月份 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	            <ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
	          	-- <ait:date yearName="year1" monthName="month1" yearSelected="${year1}" monthSelected="${month1}" yearPlus="10" />
	          </td> 
	           <td width="10%" class="info_title_01">部门</td>
						<td width="10%" class="info_content_00"><ait:selDept
							name="DeptId" selected="${DeptId}" all="all" supervisorType="ev2"/></td>
	             </td> 
	           <!-- 
	            <td nowrap="nowrap" class="info_title_01">
	             部门
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	         
	          <select name="selectDept">
	           <option value="">请选择</option>
				<c:forEach items="${deptList}" var="deptList" varStatus="i">
					
					<option value="${deptList.DEPTID}" <c:if test="${deptList.DEPTID==selectDept}">selected</c:if> >
						${deptList.DEPTNAME}
					</option>
				</c:forEach>
			</select></td>
	             --> 
	             <td width="10%" class="info_title_01">职位</td>
	             <td width="10%" class="info_content_00">
						
						 <select name="selectPosition">
						    <option value="">请选择</option>
							<c:forEach items="${positionList}" var="positionList" varStatus="i">
								
								<option value="${positionList.AFFIRM_POSITION}" <c:if test="${positionList.AFFIRM_POSITION==selectPosition}">selected</c:if>>
									${positionList.AFFIRM_POSITION_NAME}
								</option>
							</c:forEach>
						</select>
						</td> 
			    	                                                                                
		          <td  class="info_title_01"><!-- 关键字 -->
		       <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td>                                                                                                                            
		          <td   class="info_content_00">
		          <input type="text" name="key" value="${key}" /></td>  
		          <td width="7%" class="info_title_01"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
			  <td width="10%" class="info_content_00">
				     <select name="qryType">
				         <option value="0" <%= selected("0", qryType) %> ><!-- 全部 -->
				   <ait:message messageID="display.ess.approval.select_all" module="ess"></ait:message>      
				         </option>   
				         <option value="1" <%= selected("1", qryType) %>><!-- 已决裁 -->
				            已通过  
				         </option>
				          <option value="3" <%= selected("3", qryType) %>>
				            已否决 
				         </option>   
				          <option value="2" <%= selected("2", qryType) %>><!-- 未决裁 -->
				     <ait:message messageID="display.ess.approval.pending" module="ess"></ait:message>      
				         </option>   
				          <option value="4" <%= selected("4", qryType) %>>
				            未评价
				         </option>              
				     </select>
				  </td>                        
		           <td nowrap="nowrap" class="info_title_01"><!-- 评价月份-->
	          	评价累计<br>导出 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	            <ait:date yearName="yearSum"  yearSelected="${year}"  yearPlus="10" />
	            <ait:image
							src="/images/btn/Excel_Exp.gif" border="0" align="absmiddle"
							onclick="javascript:exportExcelSum();" style="cursor:hand" />
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
				<td align="left" class="title1" colspan="10">区间查询
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		  
		   	<td class="info_title_01" width="20">序号</td>
			<td class="info_title_01" nowrap><!--工号  -->职号</td>
		    <td class="info_title_01" nowrap><!--  休假人-->姓名</td>
		    
				
			<td class="info_title_01" nowrap>评价月份
		    
		    </td>	
		    <td class="info_title_01" nowrap><!-- 休假类型 -->
		    部门
		    </td>
		    <td class="info_title_01" nowrap>
		    班组
		    </td>
		    <td class="info_title_01" nowrap>职位
		    
		    </td>
		   
								
			 <td nowrap="nowrap" class="info_title_01">
				评价总分</td>
			<td nowrap="nowrap" class="info_title_01">
				本次排名</td>	
				
			 <td nowrap="nowrap" class="info_title_01">
				上次排名</td>	
			 <td nowrap="nowrap" class="info_title_01">
				名次变动</td>	 		
	
				
			<td nowrap="nowrap" class="info_title_01">
				决裁情况</td>	 
			
		  
		  
			      
		  </tr>
		<%for(int i=0;i<evaluateList.size();i++){                       
			evaluateInfo = (EvaluateInfo) evaluateList.get(i);
		    evAffirmorList = evaluateInfo.getAffirmorList();
		  
		   
		%>
		  <tr align="center">
		  
		    <td class="info_content_09" ><%= i + 1 %></td>
		    <td class="info_content_09" >
		    <span class=ellipsis_row title='<%=evaluateInfo.getEMPID() %>'><%=evaluateInfo.getEMPID() %></span>
		    </td>
		    <td class="info_content_09" id="leftnewstd">
		    <span class=ellipsis_row title='<%=evaluateInfo.getCHINESENAME() %>'><%=evaluateInfo.getCHINESENAME() %></span>
		    </td>
			<td class="info_content_09" id="leftnewstd">
			<%=evaluateInfo.getMONTH() %>
			<input type="hidden" name="yearMonth_<%=i%>" value="<%=evaluateInfo.getEVALUATE_MONTH() %>	">	
			</td>
			
			<td class="info_content_09" id="leftnewstd">
			<%=evaluateInfo.getFOURTHDEPTNAME() %>
				
			</td>
			<td class="info_content_09" id="leftnewstd">
			<%=evaluateInfo.getDEPTNAME() %>
			<input type="hidden" name="dept_<%=i%>" value="<%=evaluateInfo.getDEPTID() %>	">
				
			</td>
			<td class="info_content_09" id="leftnewstd">
			<%=evaluateInfo.getPOST_CODE_NAME() %>
			<input type="hidden" name="post_<%=i%>" value="<%=evaluateInfo.getPOST_CODE() %>	">	
			</td>

			   <td class="info_content_09" id="leftnewstd">
			    <%=NumberUtil.convert(evaluateInfo.getTOTAL()) %>
			  </td>
			  <td class="info_content_09" id="leftnewstd">
			
			    <%=StringUtil.checkNull(evaluateInfo.getPLACE()) %>&nbsp;/&nbsp;<%=StringUtil.checkNull(evaluateInfo.getAMOUNT()) %>
			    </td>
			    <td class="info_content_09" id="leftnewstd">
			    <%=StringUtil.checkNull(evaluateInfo.getLAST_MONTH_PLACE()) %>&nbsp;/&nbsp;<%=StringUtil.checkNull(evaluateInfo.getLAST_MONTH_AMOUNT()) %>
			    </td>
			       <%  
			           int placeMove = 0;
			           if(NumberUtil.parseInt(evaluateInfo.getLAST_MONTH_PLACE(),0)>0){
			           		 placeMove = NumberUtil.parseInt(evaluateInfo.getPLACE(),0) - NumberUtil.parseInt(evaluateInfo.getLAST_MONTH_PLACE(),0);
			           }
		   		       
		   		   %>
		   
		      
			    
			    <td class="info_content_09" id="leftnewstd">
			    <%=placeMove%>&nbsp;
			    </td>
			  <% if("4".equals(evaluateInfo.getACTIVITY())){ %>
			 <td class="info_content_10" nowrap="nowrap">
	
			    <%	for(int j=0;j<evAffirmorList.size();j++){     
			    evAffirmor = (EvaluateAffirmor) evAffirmorList.get(j);%>       
			  <font color="<%=(String) colorMap.get(String.valueOf(0))%>">
			    	<%=evAffirmor.getAffirmLevel()+ StringUtil.checkNull(evAffirmor.getAffirmorName()) + StringUtil.getString((4 - evAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;")  %>&nbsp;&nbsp;
			    	</font><br>
			    
			    <%}%>
		     </td>
		    <% }else{%>
		     <td class="info_content_10" nowrap="nowrap">
	
			    <%	for(int j=0;j<evAffirmorList.size();j++){            
			    	evAffirmor = (EvaluateAffirmor) evAffirmorList.get(j);%>
			    	<font color="<%=(String) colorMap.get(String.valueOf(evAffirmor.getAffirmFlag()))%>">
			    	<%= StringUtil.checkNull(evAffirmor.getAffirmorName()) + StringUtil.getString((4 - evAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") + StringUtil.checkNull(evAffirmor.getUpdateDate()) %>&nbsp;&nbsp;
		    		<%= statusMap.get(String.valueOf(evAffirmor.getAffirmFlag()))%>
			    	</font><br>
			    
			    <%}%>
		    </td>
             <% }%>
		  </tr>
		<%}%>
		</table>
		            
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(evaluateList) < 6}">
				<c:forEach var="i" begin="1" end="${6-fn:length(evaluateList)}"
					step="1">
					<tr>
						<td class="info_content_09" height="30"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		<ait:page       
		               link="/evControlServlet"
		               parameters="&operation=evaluateQuery&content=evaluateQueryResult&menu_code=${param.menu_code}&year=${year}&month=${month}&year1=${year1}&month1=${month1}&DeptId=${DeptId}&selectPosition=${selectPosition}&key=${key}&qryType=${qryType}" 
		               total="${resultCount}"
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

	<input type="hidden" name="operation" value="evaluateQuery" />
	<input type="hidden" name="content" value="evaluateQueryResult" />
	
	<input type="hidden" name="menu_code" value="<%=menu_code%>" />
	<input type="hidden" name="currentPage" value="${currentPage}" />	
	
</form>
</body>
</html>
