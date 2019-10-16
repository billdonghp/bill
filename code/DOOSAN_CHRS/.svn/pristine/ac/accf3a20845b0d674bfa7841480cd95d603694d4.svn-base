<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>刻章决裁情况</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
 function search(){
 document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=wpAffirm&content=sealproductionAffirmInfo";
 document.form1.submit(); 
 }
 
function showMemo(val) {
     var memo = $('opition_'+val).innerHTML;
	var html = "<div style='background-color:#FFFFFF;height: 100%;text-align: left;'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 450
				  , height: 200
				 , shadow: true
				 , closable: true
				  , layout : 'fit'
				 , html : html
				 ,resizable : true
				 ,autoScroll:true
				 ,plain : true	
				 ,title : '决裁意见'
			});
		editDlg.setPosition(400,200);
		editDlg.show();	
} 
function viewFile(fileAddress){
window.open(fileAddress,"","menubar=no, toolbar=no, scrollbars=no, status=no, resizable=yes, location=no, directories=no, copyhistory=no" );
}
</SCRIPT>

<body>
<form name="form1" method="post" action="">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gatoolbar_none.jsp"%>
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
		          <td  class="info_title_01" nowrap="nowrap"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00" nowrap="nowrap"><input type="text" name="startDate" style="width:70px" value="${startDate}" readonly onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" readonly onClick="setday(this);" /></td>                                                                                                                      
		           
		       	  <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="3">全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未通过</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
				  </td>                
		        </tr> 
		        <tr>
		        <td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/>
		         </td>
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}" title="输入姓名或者职号"/></td>                   
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
				刻章情况
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		        <td nowrap="nowrap" class="info_title_01">
				申请类型</td>		     
		      <td nowrap="nowrap" class="info_title_01">
				职号</td>
			  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
			  <td nowrap="nowrap" class="info_title_01">
				部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请日期</td>	
		      <td nowrap="nowrap" class="info_title_01">
				希望完成日期</td>		      
		      <td nowrap="nowrap" class="info_title_01">
				制品</td>
			  <td nowrap="nowrap" class="info_title_01">
				用途</td>			 
			  <td nowrap="nowrap" class="info_title_01">
				决裁情况</td>	
			  <td nowrap="nowrap" class="info_title_01">
				确认情况</td>
			  <td nowrap="nowrap" class="info_title_01">意见</td>
			   <td nowrap="nowrap" class="info_title_01">
				操作</td>	
		    </tr>
		    <c:forEach items="${WoodProductsAffirmInfoList}" var="test" varStatus="i">		
		    <tr align="center">		      	    
		      <td nowrap="nowrap"   class="info_content_01">${test.APPLYTYPE}</td>     
		      <td nowrap="nowrap"   class="info_content_01" >${test.EMPID}</td>
		      <td nowrap="nowrap"  class="info_content_01" >${test.CHINESENAME}</td>
		      <td nowrap="nowrap"  class="info_content_01" >${test.DEPTNAME}</td>
		      <td nowrap="nowrap"  class="info_content_01" >${test.APPLY_DATE}</td>
		      <td nowrap="nowrap"   class="info_content_01" >${test.HOPE_COMPLETED_DATE}</td>
		      <td nowrap="nowrap" >
		      <table align="left">
		      <c:forEach items="${test.woodProductsList}" var="woodProdust" varStatus="j">		     
		      <tr>
		      <td nowrap="nowrap" class="info_content_06">${j.index+1}</td>
		      <td nowrap="nowrap" class="info_content_06">制品名称:<font color="red">${woodProdust.WPNAME}</font></td>
		      <td nowrap="nowrap" class="info_content_06">制品数量:<font color="red">${woodProdust.WPNUMBER}个</font></td>
		      <td nowrap="nowrap" class="info_content_06">(长:${woodProdust.WP_L} 宽:${woodProdust.WP_W} 高:${woodProdust.WP_H} )</td>
		      
		      <%--
		      <td nowrap="nowrap" class="info_content_06">单价:<font color="red">${woodProdust.MEASUREMENT_UNIT_PRICE}/元</font></td>
		      --%>
		      <td nowrap="nowrap" class="info_content_06">小计:<font color="red">${woodProdust.WP_SUMPRICE}元</font></td>
		      <td nowrap="nowrap" class="info_content_06">
		       <c:if test="${woodProdust.FILEPATH!=null}">
	            样式：<a href="#" onclick="viewFile('${woodProdust.FILEPATH}');"><font color="red">${woodProdust.FILENAME}</font></a>
	           </c:if>	 
	           <c:if test="${woodProdust.FILEPATH==null}">
	            &nbsp;
	           </c:if>  
	          </td>
		      </tr>
		      </c:forEach>
		      </table>
		      </td>
		      <td nowrap="nowrap" class="info_textarea_content">&nbsp;${test.NOTE}</td>
		      <td nowrap="nowrap">
              <table align="center">                          
              <c:forEach items="${test.affirmorList}" var="affirmor" varStatus="f">
		      <tr>
		      <td nowrap="nowrap">
		          <font color="#990099">${affirmor.AFFIRM_LEVEL}${affirmor.AFFIRMOR_NAME}</font>
		          <c:if test="${affirmor.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		          <c:if test="${affirmor.AFFIRM_FLAG==1}"><font color="#00CC00">已决裁</font></c:if>
		          <c:if test="${affirmor.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
		      </td>
		      </tr>		      
		      </c:forEach>		      
		      </table>
		      </td>
		      <td nowrap="nowrap" align="center" class="info_content_00">
              <table align="center">                     
		      <tr>
		      <td nowrap="nowrap">
		          <font color="#990099">${test.CONFIRMOR}&nbsp;</font>
		          <c:if test="${test.CONFIRM_FLAG==0}"><font color="#990099">未确认</font></c:if>
		          <c:if test="${test.CONFIRM_FLAG==1}"><font color="#00CC00">已确认</font></c:if>
		          <c:if test="${test.CONFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
		          <c:if test="${test.CONFIRM_FLAG==3}"><font color="#00CC00">已确认</font></c:if>
		      </td>
		      </tr>		        
		      </table>
		      </td>
		      <td class="info_content_01">
						<a href="#" onClick="showMemo('${i.index}')">详情</a>
			</td>
		      <td nowrap="nowrap" class="info_content_00">
		      <c:if test="${test.FIRSTAFFIRMFLAG==0 && test.WP_APPLYERID eq adminid}"><a href="/gaControlServlet?menu_code=${param.menu_code}&operation=wpAffirm&content=deleteApply&applyNo=${test.APPLY_NO}" onclick="return(confirm('确认删除吗？这将清空所有的相关信息！'))"><font color="red">删除</font></a></c:if>&nbsp;		  
		      </td>
		      </tr>
		      <div id="opition_${i.index}" style="display: none;">
							<c:forEach items="${test.affirmorList}" var="affirmor">
									  <p>
									  <font color="#990099">${affirmor.AFFIRMOR_NAME}</font>
							          <c:if test="${affirmor.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
							          <c:if test="${affirmor.AFFIRM_FLAG==1}"><font color="#00CC00">已决裁</font></c:if>
							          <c:if test="${affirmor.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
							          &nbsp;${affirmor.AFFIRMORIDEA}
							          </p>
									
							</c:forEach>
							<p>
							<c:if test="${test.CONFIRMOR!=null}"><font color="#990099">${test.CONFIRMOR}</font></c:if>
							<c:if test="${test.CONFIRMOR==null}"><font color="#990099">&nbsp;&nbsp;&nbsp;担当</font> </c:if>							
							<c:if test="${test.CONFIRM_FLAG==0}"><font color="#990099">未确认</font></c:if>
				          	<c:if test="${test.CONFIRM_FLAG==1}"><font color="#00CC00">已确认</font></c:if>
				          	<c:if test="${test.CONFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
				          	<c:if test="${test.CONFIRM_FLAG==3}"><font color="#00CC00">已确认</font></c:if>
				          	&nbsp;${test.CONFIRMORIDEA}				          	
				          	</p>
						
					</div>
		   </c:forEach>	
		   <input type="hidden" name="currentPage" value="${currentPage}">	    
		 </table>
		 				 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=wpAffirm&content=sealproductionAffirmInfo&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/> 
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
<ait:xjos />
</html>