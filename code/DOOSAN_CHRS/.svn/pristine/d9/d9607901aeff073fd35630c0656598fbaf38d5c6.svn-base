<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>

<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssOverTimeBean"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="overTimeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essOverTimeBean" class="com.ait.ess.bean.EssOverTimeBean" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="sDate" class="java.lang.String" scope="request" />
<jsp:useBean id="eDate" class="java.lang.String" scope="request" />
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
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.content.value="otview";
	document.ApplyForm.submit();
}
function Excel(){
    document.ApplyForm.content.value="otviewExcel";
	document.ApplyForm.submit();
}
function DeleteOne(cell) {
	// nodeName（节点名称） 
	// nodeValue（节点值） 
	// nodeType（节点类型） 

	var  msg ='<ait:message messageID="alert.ess.common.delete" module="ess"></ait:message>';
	if (confirm(msg)) {  
		document.ApplyForm.operation.value="delete";
				
		var otNo = cell.parentNode.parentNode.childNodes[0].firstChild ;
		
    	otNo.checked = true ;	
		document.ApplyForm.submit();  
	}
}

function Delete() {
	var size = document.getElementsByName("otNo").length ;
    var otNo = document.getElementsByName("otNo") ;
  	var flag = false ;
  	
  	for (var i = 0 ; i < size ; ++i)
  	{
  		if (otNo[i].checked)
  		{
  			flag = true ;
  			break ;
  		}
  	}
  	
  	if (!flag) {
  		alert("请选择要删除的数据!!!");
  		return;
  	}
  	
	if (confirm("你确定要删除当前已选择的记录？")) {  
		document.ApplyForm.operation.value="delete";
		document.ApplyForm.submit();  
	}
}

function checkAll()
{
    var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
    var size = document.getElementsByName("otNo").length ;
    var otNo = document.getElementsByName("otNo") ;
  	for (var i=0 ; i< size; i++){
		otNo[i].checked = selected ;
	
	}
    document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

function showMemo(val) {
	var memo = document.getElementById('memo_'+val).value;
	var html = "<div style='background-color:#FFFFFF;height: 100%'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 350
				  , height: 150
				 , shadow: true
				 , closable: true
				  ,autoScroll: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
} 
function showMemo2(val) {
	var memo = document.getElementById('memo2_'+val).value;
	var html = "<div style='background-color:#FFFFFF;height: 100%'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 350
				  , height: 150
				 , shadow: true
				  ,autoScroll: true
				 , closable: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
} 
function exportExcel()
{
	document.ApplyForm.content.value="exportExcel";
	document.ApplyForm.submit();
}

function importExcel(otNo){

		document.ApplyForm.action="/xlsReportServlet?xlsKey=otApplyForm&OtNo="+otNo;
	 	document.ApplyForm.submit(); 		
	
}

</script> 

<form name="ApplyForm" action="/essControlServlet" method="post">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ess/inc/ess_toolbar_search.jsp"%>
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
	          <td width="10%" class="info_title_01"><!--  开始日期-->
	         <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
	          </td>
	          <td width="7%" class="info_content_00"><input type="text" name="sDate" size="7" maxlength="10" value="<%=sDate%>" readonly onClick="setday(this);" /></td>
	          <td width="10%" class="info_title_01"><!--结束日期  -->
	           <ait:message messageID="display.mutual.end_date" module="ess" ></ait:message>  
	          </td>
	          <td width="7%" class="info_content_00"><input type="text" name="eDate" size="7" maxlength="10" value="<%=eDate%>" readonly onClick="setday(this);" /></td>
	          <td width="10%" class="info_title_01"><!-- 部门 -->
	        <ait:message messageID="display.mutual.dept" ></ait:message>   
	          </td>
	          <td width="15%" class="info_content_00"><ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
	           <td width="10%" class="info_title_01"><!--  状态-->
		       <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		      </td>
		          <td width="10%" class="info_content_00">
				     <select name="qryType">
				        <option value="0"  <c:if test="${qryType==0}">selected</c:if>>未确认    
				         </option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已确认      
				         </option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决   
				         <option value="3" <c:if test="${qryType==3}">selected</c:if>>全部   
				         </option>                
				     </select>
			</td>  	          
	          <td width="10%" class="info_title_01"><!-- 关键字 -->
	         <ait:message messageID="display.mutual.key_word" module="ess" ></ait:message> 
	          </td>
	          <td width="15%" class="info_content_00">
	          <input type="text" name="key" value="${key}" /></td>
	        </tr></table>
	      </td>
		</tr>
		</table>

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 加班申请 -->
				
			 <ait:message messageID="display.ess.review.overtime.ot_request" module="ess"></ait:message> 
				</td>
			</tr>
		</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  <tr>
	  	<td class="info_title_01" width="20"><a href="#"onclick="checkAll();" style="color:red;"><!-- 全选 --> 
		       <ait:message messageID="display.mutual.select_2" module="ess" /></a></td>
	    <td class="info_title_01" width="20">序号</td>
	    <td class="info_title_01" nowrap>职号
	    </td>
	    <td class="info_title_01" nowrap>姓名
	    </td>
	    <td class="info_title_01" nowrap><!-- 部门 -->
	      <ait:message messageID="display.mutual.department" ></ait:message>
	    </td>
	    <td class="info_title_01" nowrap><!--职级  -->
	     <ait:message messageID="display.mutual.post_grade" ></ait:message>
	    </td>
	    <td class="info_title_01" nowrap><!-- 申请时间 -->申请<br>时间
	    </td>
	    <td class="info_title_01" nowrap>班次日期
	    </td>
	    <td class="info_title_01" nowrap><!-- 加班类型 -->加班<br>类型
	    </td>
	    <td class="info_title_01" nowrap><!-- 加班时段 -->
	     <ait:message messageID="display.ess.review.overtime.period" module="ess"></ait:message>
	    </td>
	    <td class="info_title_01" nowrap>预计<br>长度
	    </td>
	    <td class="info_title_01" nowrap>创建者
	    </td>
	    <td class="info_title_01" nowrap>
	     已申请<Br>加班
	    </td>
	    <%if(admin.getCpnyId().equals("6100000000")){  %>	 
	    <td class="info_title_01" nowrap>
			申请书
	    </td>
	     <% } %>  
	   	<td class="info_title_01" nowrap><!--  工作内容 -->
			工作<br>内容
	    </td>
	    <td class="info_title_01" nowrap><!--  决裁情况-->
			 <ait:message messageID="display.mutual.status_2" module="ess"></ait:message> </td>
		<td class="info_title_01" nowrap>决裁<br>意见</td>
	    <td class="info_title_01" nowrap><!-- 删除-->删<br>除
	    </td>
	  </tr>
	<%for(int i=0;i<overTimeList.size();i++){
		essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);%>
	  <tr align="center">
	  	<td height="30" class="info_content_09" nowrap="nowrap">
	  	<%if (essOverTimeBean.getDel_flag() == 1) {%>
	     <input type="checkbox" name="otNo" value="<%=essOverTimeBean.getOtNo() %>" />
	     <% }
	  	else
	  	{
	  		out.println("&nbsp;") ;
	  	}	
	     %>
	     </td>
	    <td class="info_content_09" nowrap><%= i + 1 %></td>
	    <td class="info_content_09" id="leftnewstd">
		<span class=ellipsis_row title='<%=essOverTimeBean.getEmpId()%>'><%=essOverTimeBean.getEmpId()%></span>
		</td>
	    <td class="info_content_09" id="leftnewstd">
	    <span class=ellipsis_row title='<%=essOverTimeBean.getChineseName()%>'><%=essOverTimeBean.getChineseName()%></span>
	   </td>
	    <td class="info_content_09" id="leftnewstd">
	    <%
	    	if (essOverTimeBean.getDeptName().equals(essOverTimeBean.getFourthDeptName())){
	    %>
	    	<span class=ellipsis_row2 title='<%=StringUtil.checkNull(essOverTimeBean.getDeptName())%>'><%=StringUtil.checkNull(essOverTimeBean.getDeptName()) %></span>
	    <% 
	    	}else{
	    %>		
	    	<span class=ellipsis_row2 title='<%=StringUtil.checkNull(essOverTimeBean.getFourthDeptName())%> <%=StringUtil.checkNull(essOverTimeBean.getDeptName()) %>'>
	    	<%=StringUtil.checkNull(essOverTimeBean.getFourthDeptName())%><br><%=StringUtil.checkNull(essOverTimeBean.getDeptName()) %></span>
	    <%  } %>
	    </td>
	    <td class="info_content_09" id="leftnewstd">
	    <span class=ellipsis_row title='<%=StringUtil.checkNull(essOverTimeBean.getPostGradeCode())%>'><%=StringUtil.checkNull(essOverTimeBean.getPostGradeCode()) %></span>
	    </td>
	    <td class="info_content_09" nowrap><%=essOverTimeBean.getCreateDate().substring(5).replace(" ", "<br>") %></td>
	    <td class="info_content_09" nowrap><%= essOverTimeBean.getOtDate() %></td>
	    <td class="info_content_09" id="leftnewstd">
	    <span class="ellipsis_row" title='<%=StringUtil.checkNull(essOverTimeBean.getOtTypeName())%>'>
	       	<%if(essOverTimeBean.isForceType())out.println("<font color=\"red\">"); %> 
		    <%=StringUtil.checkNull(essOverTimeBean.getOtTypeName()) %>
	    	<%if(essOverTimeBean.isForceType())out.println("</font>"); %>
	   	</span></td>
	   	
	   	<% if(essOverTimeBean.getOtTypeCode().equals("WorkingOtType01")) 
	   		{
	   	%>
	    	<td class="info_content_09" nowrap><%=essOverTimeBean.getOtFromTime()%><br><%=essOverTimeBean.getOtToTime()%></td>
	    
	    <%	}
	   		else
	   		{
	   	%>
	   		<td class="info_content_09" nowrap><%= essOverTimeBean.getOtDate() %></td>
	   	<% 
	   		}
	    %>	
	    
	    <td class="info_content_09" nowrap><%=essOverTimeBean.getOtLength()%></td>
	    <td class="info_content_09" nowrap><%=essOverTimeBean.getCreChinesename()%></td>
	     <td class="info_content_09" nowrap>
	      <% 
         	if (admin.getCpnyId().equals("60000000") || admin.getCpnyId().equals("78000000")){
         		out.println(essOverTimeBean.getAppply_ot() + "/" + essOverTimeBean.getOtTotal()) ;
         	}else{
         		out.println(essOverTimeBean.getOtTotal()) ;
         	}
         %>
	     </td>
	     
	     <%if(admin.getCpnyId().equals("61000000000")){  %>	
	     <td class="info_content_09" nowrap>
		    			<a onclick="importExcel('<%= essOverTimeBean.getOtNo() %>')" href="#" title="点击导出加班申请书">
		    				<font color="red">导出</font>
		    			</a>
		 </td>
	     <% } %>
	     
	    <td class="info_content_00" nowrap="nowrap">
		    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo('<%=essOverTimeBean.getOtNo()%>');">
		    		<span class=ellipsis_row><%=StringUtil.checkNull(essOverTimeBean.getOtRemark()) %></span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo_<%=essOverTimeBean.getOtNo()%>" 
		    		   value="<%=StringUtil.checkNull(essOverTimeBean.getOtRemark()) %>" />	            
        </td>
	    <td class="info_content_09" nowrap>
		    <%essAffirmorList = essOverTimeBean.getAffirmorList();
		    if (essAffirmorList.size() > 0) {
			    for(int j=0;j<essAffirmorList.size();j++){
			    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
			    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
		    		<%=essAffirmor.getAffirmorName() + StringUtil.getString((4 - essAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") + " " +  StringUtil.checkNull(essAffirmor.getUpdateDate()) + "&nbsp;&nbsp;" + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
			    	</font><br>
			    <%}
			} else {%>
				&nbsp;
			<%}%>
			<%if(essOverTimeBean.getOpFlag()!=null){ %>
			<%if(essOverTimeBean.getOpFlag()==0||essOverTimeBean.getOpFlag()==3){ %><font color="<%=(String) colorMap.get("0")%>">人事未确认</font><%}%>
			<%if(essOverTimeBean.getOpFlag()==1){ %><font color="<%=(String) colorMap.get("1")%>">人事已确认</font><%}%>
			<%if(essOverTimeBean.getOpFlag()==2){ %><font color="<%=(String) colorMap.get("2")%>">人事已否决</font><%}%>
			
			<%}%>
			
	    </td>    
	     <td class="info_content_00" nowrap="nowrap">
		    	<!--<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('<%=essOverTimeBean.getOtNo()%>');">
		    		<span class=ellipsis_row><%=StringUtil.checkNull(essOverTimeBean.getRemark()) %></span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo2_<%=essOverTimeBean.getOtNo()%>" 
		    		   value="<%=StringUtil.checkNull(essOverTimeBean.getRemark()) %>" />	            
            -->
            <% if (essAffirmorList.size() > 0) {
					    for(int j=0;j<essAffirmorList.size();j++){
					    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
					    	<!--<font><%= essAffirmor.getRemark() %></font><br>
					    	--><a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('<%=j%>');">
					    		<span class=ellipsis_row><%=StringUtil.checkNull(essAffirmor.getRemark()) %></span><br>
	            			</a>
	            			<input type = "hidden"
		    		   			id="memo2_<%=j%>" 
		    		   			value="<%=StringUtil.checkNull(essAffirmor.getRemark()) %>" />
					    <%}
            			
		            } else {%>
						&nbsp;
				<%}%>
            </td>
	    <td class="info_content_09" nowrap>
	   		<%if (essOverTimeBean.getDel_flag() == 1) {%>
	    		<span style="color:red; cursor:pointer;" onClick="DeleteOne(this);">
	    		<ait:image src="/images/btn/Delete_little.gif"  border="0" align="absmiddle" style="cursor:hand" title="删除" enTitle="delete" /></span>
			<%} else out.print("&nbsp;");%>
		</td>
	  </tr>
	  
	  <div id="opition_<%=i %>" style="display: none;">
		 <%essAffirmorList = essOverTimeBean.getAffirmorList();
		    if (essAffirmorList.size() > 0) {
			    for(int j=0;j<essAffirmorList.size();j++){
			    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
			    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
		    		<%=essAffirmor.getAffirmorName() + " " +  StringUtil.checkNull(essAffirmor.getUpdateDate()) + " " + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
			    	</font><%=StringUtil.checkNull(essAffirmor.getRemark())%><br>
			    <%}
			} else {%>
				&nbsp;
			<%}%>
			<%if(essOverTimeBean.getOpFlag()!=null){ %>
			<%if(essOverTimeBean.getOpFlag()==0||essOverTimeBean.getOpFlag()==3){ %><font color="<%=(String) colorMap.get("0")%>">人事未确认</font><%}%>
			<%if(essOverTimeBean.getOpFlag()==1){ %><font color="<%=(String) colorMap.get("1")%>">人事已确认&nbsp;</font><%=StringUtil.checkNull(essOverTimeBean.getRemark()) %><%}%>
			<%if(essOverTimeBean.getOpFlag()==2){ %><font color="<%=(String) colorMap.get("2")%>">人事已否决&nbap;</font><%=StringUtil.checkNull(essOverTimeBean.getRemark()) %><%}%>
		<%}%>
	  </div>
	<%}%>
	</table>
	
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/essControlServlet"
		               parameters="&operation=view&content=otview&menu_code=${param.menu_code}&sDate=${sDate}&eDate=${eDate}&deptID=${deptID}&key=${key}&qryType=${qryType}" 
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
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="operation" value="view" />
<input type="hidden" name="content" value="otview" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="currentPage" value="${currentPage}" />
</form>
</body>
</html>
