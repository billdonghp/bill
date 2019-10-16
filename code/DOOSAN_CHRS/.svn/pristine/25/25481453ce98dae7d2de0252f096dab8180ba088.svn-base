<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<%@ page import="com.ait.ar.bean.ArDetailBack"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="arModifyList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="arDetailBack" class="com.ait.ar.bean.ArDetailBack" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="sDate" class="java.lang.String" scope="request" />
<jsp:useBean id="eDate" class="java.lang.String" scope="request" />

<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#leftnewstd .ellipsis_row{width:100px}
.ellipsis_row{
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
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="armodifyview";
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.flag.value="";
	document.ApplyForm.submit();
}
function JumpPage(currentPage) {
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="armodifyview";
	document.ApplyForm.currentPage.value=currentPage;
	document.ApplyForm.submit();
}

function DeleteOne(cell) {
	var  msg ='<ait:message messageID="alert.ess.common.delete" module="ess"></ait:message>';
	if (confirm(msg)) {  
		document.ApplyForm.action = "/essControlServlet";
		document.ApplyForm.operation.value="delete";		
		var leaveNo = cell.parentNode.parentNode.childNodes[0].firstChild ;
    	leaveNo.checked = true ;	
		document.ApplyForm.submit();  
	}
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
				 , closable: true
				 ,autoScroll: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
} 



function exportExcel()
{
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="exportLeaveViewExcel";
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
			<%@ include file="../ess/inc/ess_toolbar_search_s.jsp"%>
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
			<td class="title1"><!--查询条件  -->
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
	          <td width="10%" class="info_title_01"><!-- 结束日期 -->
	           <ait:message messageID="display.mutual.end_date" module="ess"></ait:message>  
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
				        <option value="0"  <c:if test="${qryType==0}">selected</c:if>>未决裁    
				        </option>   
				         </option>                
				     </select>
			</td>  
	          <td width="10%" class="info_title_01"><!-- 关键字 -->
	             <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>     
	          </td>
	          <td width="10%" class="info_content_00">
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
				<td align="left" class="title1" colspan="10">考勤修改信息
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		
		   	<td class="info_title_01" width="20">序号</td>
			<td class="info_title_01" nowrap><!--工号  -->职号</td>
		    <td class="info_title_01" nowrap><!--  休假人-->姓名</td>
		    <td class="info_title_01" nowrap><!-- 部门 --> <ait:message
					messageID="display.mutual.department"></ait:message></td>
				
		    <td class="info_title_01" nowrap><!-- 休假类型 -->
		    考勤日期
		    </td>
		    <td class="info_title_01" nowrap>班次
		    
		    </td>
		    <td class="info_title_01" nowrap>考勤项目
		    
		    </td>
		    <td class="info_title_01" nowrap>时间段
		    </td>
		    <td class="info_title_01" nowrap>长度</td>
			<td width="5%" class="info_title_01">
        	操作人</td>
        	<td width="8%" class="info_title_01">
        	操作时间</td>
		    
		    <td class="info_title_01" nowrap>修改原因</td>   
		    <td class="info_title_01" nowrap><!--  决裁情况-->
			<ait:message
				messageID="display.mutual.status_2" module="ess"></ait:message>   
			   </td>
			      
		  </tr>
		<%
			for (int i = 0; i < arModifyList.size(); i++) {
				arDetailBack = (ArDetailBack) arModifyList.get(i);
		%>
		  <tr align="center">
		  	
		  	<td class="info_content_09" nowrap><%=i + 1%></td>
		    <td class="info_content_09" id="leftnewstd">
	         <%=arDetailBack.getEmpID()%>
		    </td>
		    <td class="info_content_09" id="leftnewstd">
		    <span class=ellipsis_row title='<%=arDetailBack.getChineseName()%>'><%=arDetailBack.getChineseName()%></span>
		   </td>
		   <td class="info_content_09" id="leftnewstd">
				<%=arDetailBack.getDeptName()%>
			</td>
								<td class="info_content_09" id="leftnewstd">
									<%=arDetailBack.getAr_date_str1()%>

								</td>
								<td>
									<table>
										<tr>
											<td>

												<%=arDetailBack.getShiftName()%>

											</td>
										</tr>

									</table>
								</td>
								<td>
									<table>
										<tr>
											<td align="right">
												<span style="color: green"> <%=arDetailBack.getItemName()%>(现)
												</span>
											</td>
										</tr>
										<tr>
											<td align="right">
												<span style="color: blue"> <%=arDetailBack.getItemName1()%>(原)
												</span>
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table>
										<tr>
											<td>
												<%
													if (admin.getLanguagePreference().equals("zh")) {
												%>
												<%=StringUtil.checkNull(arDetailBack.getFromTime(),
									"未刷卡")%><br><%=StringUtil.checkNull(arDetailBack.getToTime(),
									"未刷卡")%>

												<%
													} else {
												%>
												<%=StringUtil.checkNull(arDetailBack.getFromTime(),
									"No Record")%><br><%=StringUtil.checkNull(arDetailBack.getToTime(),
									"No Record")%>

												<%
													}
												%>
											</td>
										</tr>

									</table>
								</td>

								<td>
									<table>
										<tr>
											<td align="right">
												<span style="color: green"> <%=arDetailBack.getQuantity()%>&nbsp;<%=arDetailBack.getUnitName()%>(现)
												</span>
											</td>
										</tr>
										<tr>
											<td align="right">
												<span style="color: blue"> <%=arDetailBack.getQuantity1()%>&nbsp;<%=arDetailBack.getUnitName1()%>(原)
												</span>
											</td>
										</tr>
									</table>

								</td>

								<td>
									<%=arDetailBack.getCreateBy1()%>
								</td>
								<td>
									<%=arDetailBack.getCreateDate1()%>
								</td>
								
								 <td>
			 
						    	<a  style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('<%=arDetailBack.getPkNo1()%>');">
						    		<span class=ellipsis_row ><%=StringUtil.checkNull(arDetailBack.getRemark1())%></span>
					            </a>
						    	<input type = "hidden" id="memo2_<%=arDetailBack.getPkNo1()%>"  value="<%=StringUtil.checkNull(arDetailBack.getRemark1()) %>" />	            
				               </td>
				              
								<td class="info_content_09" nowrap align="left">
							    <%
							    	essAffirmorList = arDetailBack.getAffirmorList();
							    		if (essAffirmorList.size() > 0) {
							    			for (int j = 0; j < essAffirmorList.size(); j++) {
							    				essAffirmor = (EssAffirmor) essAffirmorList.get(j);
							    %>
								    	<font color="<%=(String) colorMap.get(String
														.valueOf(essAffirmor.getAffirmFlag()))%>">
							    		<%=essAffirmor.getAffirmorName()
												+ StringUtil.getString((4 - essAffirmor
														.getAffirmorName().length()),
														"&nbsp;&nbsp;&nbsp;&nbsp;")
												+ " "
												+ StringUtil.checkNull(essAffirmor
														.getUpdateDate())
												+ "&nbsp;&nbsp;&nbsp;&nbsp;"
												+ (String) statusMap.get(String
														.valueOf(essAffirmor.getAffirmFlag()))%>
								    	</font><br>
								    <%
				    	}
				    		} else {
				    %>
					&nbsp;
				<%
					}
				%>
		   
		    </td>
	
		  </tr>
		  
		<%
			}
		%>
		</table>
		
		<!-- Page Navigation Start-->
					<ait:page       
		               link="/essControlServlet"
		               parameters="&operation=view&content=armodifyview&menu_code=${param.menu_code}&sDate=${sDate}&eDate=${eDate}&deptID=${deptID}&key=${key}&qryType=${qryType}" 
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
<input type="hidden" name="flag" value="${requestScope.flag }" />
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="operation" value="view" />
<input type="hidden" name="content" value="armodifyview" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="currentPage" value="${currentPage}" />

</form>
</body>
</html>
