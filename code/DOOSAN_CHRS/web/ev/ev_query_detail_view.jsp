<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ev.bean.EvaluateInfo"%>
<%@ page import="com.ait.ev.bean.EvaluateAffirmor"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@ page import="com.ait.ev.bean.EvaluateItem"%>
<%@ page import="com.ait.util.NumberUtil"%>
<jsp:useBean id="evaluateItem" scope="page" class="com.ait.ev.bean.EvaluateItem" />
<jsp:useBean id="pageControl" class="com.ait.ess.bean.PageControl" scope="request" />
<jsp:useBean id="evaluateList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="evaluateInfo" class="com.ait.ev.bean.EvaluateInfo" scope="page" />
<jsp:useBean id="itemList" scope="request" class="java.util.ArrayList" />

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
	document.ApplyForm.content.value="evaluateQueryDetail";
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.submit();
}


function exportExcel()
{
	document.ApplyForm.action = "/evControlServlet";
	document.ApplyForm.content.value = "evaluateQueryDetailExcel";
	document.ApplyForm.submit();
}



</script>

<form name="ApplyForm" action="" method="post">
<input type="hidden" name="applySize" value=<%=evaluateList.size()%> />  
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
	          	
	          </td>
	           <!--    <td nowrap="nowrap" class="info_title_01">
	             部门
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	         
	          <select name="selectDept">
	           <option value="">全部</option>
				<c:forEach items="${deptList}" var="deptList" varStatus="i">
					
					<option value="${deptList.DEPTID}" <c:if test="${deptList.DEPTID==selectDept}">selected</c:if> >
						${deptList.DEPTNAME}
					</option>
				</c:forEach>
			</select></td>-->
	          <td width="10%" class="info_title_01">部门</td>
						<td width="10%" class="info_content_00"><ait:selDept
							name="DeptId" selected="${DeptId}" all="all" supervisorType="ev1"/></td>
	          
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
			    	<td width="10%" class="info_title_01">决裁等级</td>
				<td class="info_content_00">
					<select name="level">
						    <option value="">请选择</option>
						    <option value="1" <c:if test="${level == '1'}">selected</c:if>>1</option>
						    <option value="2" <c:if test="${level == '2'}">selected</c:if>>2</option>
						    <option value="3" <c:if test="${level == '3'}">selected</c:if>>3</option>
						    <option value="4" <c:if test="${level == '4'}">selected</c:if>>4</option>
						</select>
				</td>                                                                                        
		          <td  class="info_title_01"><!-- 关键字 -->
		       <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td>                                                                                                                            
		          <td   class="info_content_00">
		          <input type="text" name="key" value="${key}" /></td>  
		          
		         
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
				<td align="left" class="title1" colspan="10">月别查询
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		  
		   	<td class="info_title_01" >序<br>号</td>
			<td class="info_title_01" nowrap><!--工号  -->职号</td>
		    <td class="info_title_01" nowrap><!--  休假人-->姓名</td>
		    
				
			<td class="info_title_01" nowrap>评价<br>月份
		    
		    </td>	
		    <td class="info_title_01" nowrap>
		    部门
		    </td>
		    <td class="info_title_01" nowrap>
		    班组
		    </td>
		    <td class="info_title_01" nowrap>职位
		    
		    </td>
		     <td class="info_title_01" nowrap>基础分
		    
		    </td>
		    <%
		     	for (int k = 0; k < itemList.size(); k++) {
		     		evaluateItem = (EvaluateItem) itemList.get(k);
		     		
		     		String itemName = evaluateItem.getItemName();
		     %>
							
								<td nowrap="nowrap" class="info_title_01" width="60">
									<%=itemName%></td>

								<%
									
									}
								%>
								
			 <td nowrap="nowrap" class="info_title_01">
				评价总分</td>
			 <!--
			<td nowrap="nowrap" class="info_title_01">
				本次排名</td>	
				
			 <td nowrap="nowrap" class="info_title_01">
				上次排名</td>	
			 <td nowrap="nowrap" class="info_title_01">
				名次变动</td>	 		
			 -->
			  <td nowrap="nowrap" class="info_title_01">
				说明事项</td>		
				
			
			  <td nowrap="nowrap" class="info_title_01">
				决裁人</td>
								
		     
			  <td nowrap="nowrap" class="info_title_01">
				决裁<br>等级</td>
		  
		  
			      
		  </tr>
		<%for(int i=0;i<evaluateList.size();i++){                       
			evaluateInfo = (EvaluateInfo) evaluateList.get(i);
		 
		  
		   
		%>
		  <tr align="center">
		  
		    <td class="info_content_09" ><%= i + 1 %></td>
		    <td class="info_content_09" id="leftnewstd">
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
			<%=NumberUtil.convert(evaluateInfo.getITEM0()) %>
			
			</td>
			 
			  <%
			                         double itemValue = 0;
		       		    
                            for (int k = 0; k < itemList.size(); k++) {
		     		            evaluateItem = (EvaluateItem) itemList.get(k);
		     		            String itemId = evaluateItem.getItemId();
                                if(itemId.equals("item1")){
                                   itemValue = evaluateInfo.getITEM1();
                                }else if(itemId.equals("item2")){
                                   itemValue = evaluateInfo.getITEM2();
                                }else if(itemId.equals("item3")){
                                   itemValue = evaluateInfo.getITEM3();
                                }else if(itemId.equals("item4")){
                                   itemValue = evaluateInfo.getITEM4();
                                }else if(itemId.equals("item5")){
                                   itemValue = evaluateInfo.getITEM5();
                                }else if(itemId.equals("item6")){
                                   itemValue = evaluateInfo.getITEM6();
                                }else if(itemId.equals("item7")){
                                   itemValue = evaluateInfo.getITEM7();
                                }else if(itemId.equals("item8")){
                                   itemValue = evaluateInfo.getITEM8();
                                }else if(itemId.equals("item9")){
                                   itemValue = evaluateInfo.getITEM9();
                                }else if(itemId.equals("item10")){
                                   itemValue = evaluateInfo.getITEM10();
                                }else if(itemId.equals("item11")){
                                   itemValue = evaluateInfo.getITEM11();
                                }else if(itemId.equals("item12")){
                                   itemValue = evaluateInfo.getITEM12();
                                }else if(itemId.equals("item13")){
                                   itemValue = evaluateInfo.getITEM13();
                                }else if(itemId.equals("item14")){
                                   itemValue = evaluateInfo.getITEM14();
                                }else if(itemId.equals("item15")){
                                   itemValue = evaluateInfo.getITEM15();
                                }else if(itemId.equals("item16")){
                                   itemValue = evaluateInfo.getITEM16();
                                }else if(itemId.equals("item17")){
                                   itemValue = evaluateInfo.getITEM17();
                                }else if(itemId.equals("item18")){
                                   itemValue = evaluateInfo.getITEM18();
                                }else if(itemId.equals("item19")){
                                   itemValue = evaluateInfo.getITEM19();
                                }else if(itemId.equals("item20")){
                                   itemValue = evaluateInfo.getITEM20();
                                }else if(itemId.equals("item21")){
                                   itemValue = evaluateInfo.getITEM21();
                                }else if(itemId.equals("item22")){
                                   itemValue = evaluateInfo.getITEM22();
                                }else if(itemId.equals("item23")){
                                   itemValue = evaluateInfo.getITEM23();
                                }else if(itemId.equals("item24")){
                                   itemValue = evaluateInfo.getITEM24();
                                }else if(itemId.equals("item25")){
                                   itemValue = evaluateInfo.getITEM25();
                                }else if(itemId.equals("item26")){
                                   itemValue = evaluateInfo.getITEM26();
                                }else if(itemId.equals("item27")){
                                   itemValue = evaluateInfo.getITEM27();
                                }else if(itemId.equals("item28")){
                                   itemValue = evaluateInfo.getITEM28();
                                }else if(itemId.equals("item29")){
                                   itemValue = evaluateInfo.getITEM29();
                                }else if(itemId.equals("item30")){
                                   itemValue = evaluateInfo.getITEM30();
                                }else if(itemId.equals("item31")){
                                   itemValue = evaluateInfo.getITEM31();
                                }else if(itemId.equals("item32")){
                                   itemValue = evaluateInfo.getITEM32();
                                }else if(itemId.equals("item33")){
                                   itemValue = evaluateInfo.getITEM33();
                                }else if(itemId.equals("item34")){
                                   itemValue = evaluateInfo.getITEM34();
                                }else if(itemId.equals("item35")){
                                   itemValue = evaluateInfo.getITEM35();
                                }else if(itemId.equals("item36")){
                                   itemValue = evaluateInfo.getITEM36();
                                }else if(itemId.equals("item37")){
                                   itemValue = evaluateInfo.getITEM37();
                                }else if(itemId.equals("item38")){
                                   itemValue = evaluateInfo.getITEM38();
                                }else if(itemId.equals("item39")){
                                   itemValue = evaluateInfo.getITEM39();
                                }else{
                                   itemValue = 0;
                                }
		       		        	
		       		        	
		       	%>
		       <td class="info_content_09" nowrap="nowrap">
		      <%=NumberUtil.convert(itemValue)%>
		       </td>
		   
		             <%
		   		    	}
		   		     %>
		      
			   <td class="info_content_09" id="leftnewstd">
			  <%=NumberUtil.convert(evaluateInfo.getTOTAL()) %>
			  </td>
			  <!--
			  <td class="info_content_09" id="leftnewstd">
			    <%=StringUtil.checkNull(evaluateInfo.getPLACE()) %>&nbsp;
			    </td>
			    <td class="info_content_09" id="leftnewstd">
			    <%=StringUtil.checkNull(evaluateInfo.getLAST_MONTH_PLACE()) %>&nbsp;
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
			 -->
			   <td class="info_content_09" id="leftnewstd">
		    <span class=ellipsis_row title='<%=StringUtil.checkNull(evaluateInfo.getREMARK()) %>'><%=StringUtil.checkNull(evaluateInfo.getREMARK()) %></span>
		    </td>
  	          
		       <td class="info_content_09" id="leftnewstd">
			    <%=StringUtil.checkNull(evaluateInfo.getAffirmor_id()) %>&nbsp;
			    </td>
			    
			     <td class="info_content_09" id="leftnewstd">
			    <%=StringUtil.checkNull(evaluateInfo.getAffirm_level()) %>&nbsp;
			    </td>
			 
		 
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
		               parameters="&operation=evaluateQuery&content=evaluateQueryDetail&menu_code=${param.menu_code}&year=${year}&month=${month}&DeptId=${DeptId}&selectPosition=${selectPosition}&key=${key}&level=${level}" 
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
	<input type="hidden" name="content" value="evaluateQueryDetail" />
	<input type="hidden" name="flag" value="" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>" />
	<input type="hidden" name="currentPage" value="${currentPage}" />	
	<input type="hidden" name="ck_all" value="0" />
</form>
</body>
</html>
