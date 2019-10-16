<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.ga.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<style>
#leftnewstd .ellipsis_row{width:80px}
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title司机加班决裁情况</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function Expel(applyno){
	document.form1.action="/xlsReportServlet?operation=ga_voiture&xlsKey=ExcelVioture&applyno="+applyno;
	document.form1.submit();
}
function ImportExcel() {
    document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaAffirm&content=viewAffirmDriverOtInfo&excelFlag=excel";
	document.form1.submit();

}
function search(){
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaAffirm&content=viewAffirmDriverOtInfo";
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
function showMemo1(val) {
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
		          <td  class="info_content_00" nowrap="nowrap"><input type="text" name="startDate" style="width:70px" value="${startDate}" onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" onClick="setday(this);" /></td>                                                                                                                      
		            <td  class="info_title_01" nowrap="nowrap">工作类型
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		        <ait:codeClass codeClass="DriverOtType" name="driverOtType" selected="${driverOtType}" all="all"/>
		          </td>
		       	 
		       	  
		           <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		         <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="3" <c:if test="${qryType==3}">selected</c:if>>全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未决裁</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>
				  </td>          
		        </tr> 
		        <tr>
		         <td  class="info_title_01" nowrap="nowrap">加班类型
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		        <ait:codeClass codeClass="OTTypeCode" name="OTTypeCode" selected="${OTTypeCode}" all="all"/>
		          </td>
		        
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入代请人或者司机姓名"/></td>       
	
		           <td  class="info_title_01">&nbsp;
		          </td>
		         <td  class="info_content_00">
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
		         </td>
		          <td  class="info_title_01">&nbsp;
		          </td>
		         <td  class="info_content_00">
		        <img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="javascript:ImportExcel();"/>
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
				<td align="left" class="title1" colspan="10">
				司机加班情况
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
				代请人</td>
			   <td nowrap="nowrap" class="info_title_01">
				司机</td>
			  <td nowrap="nowrap" class="info_title_01">
				工作类型</td>
			  <td nowrap="nowrap" class="info_title_01">
				加班类型</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				开始日期</td>
		      <td nowrap="nowrap" class="info_title_01">
				开始时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				结束日期</td>
		      <td nowrap="nowrap" class="info_title_01">
				结束时间</td>
		      <td nowrap="nowrap" class="info_title_01">
				加班原因</td>
				 <td nowrap="nowrap" class="info_title_01">
				加班长度</td>
			  <td nowrap="nowrap" class="info_title_01">
				备注</td>
			 
			 
			  <td nowrap="nowrap" class="info_title_01">
				意见</td>
								

			  <td nowrap="nowrap" class="info_title_01">
				决裁情况</td>	
			 
		      
		    </tr>
		    <tr>
		    <c:forEach items="${driverOtListList}" var="list" varStatus="i">
		    <tr align="center">			     
		      <td nowrap="nowrap" align="center" class="info_content_01" >
    			${list.APPLYER_CHINESENAME }&nbsp;</td>
   			<td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.DRIVER_CHINESENAME }&nbsp;</td>
   			<td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.DRIVER_OT_TYPE_NAME}&nbsp;</td>  
   			  <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.OT_TYPE_NAME}&nbsp;</td> 
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.CREATE_DATE}&nbsp;</td>
   			
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.APPLY_STARTDATE }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.APPLY_STARTTIME }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.APPLY_ENDDATE }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.APPLY_ENDTIME }&nbsp;</td>
   			 
   			  <td class="info_content_00" nowrap="nowrap">
		    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo1('${list.DRIVER_OT_APPLYID}');">
		    		<span class=ellipsis_row>${list.REASON}</span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo_${list.DRIVER_OT_APPLYID }" 
		    		   value="${list.REASON }" />	            
            </td>
            <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.OT_LENGTH }&nbsp;</td>
   			 <td class="info_content_00" nowrap="nowrap">
		    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('${list.DRIVER_OT_APPLYID}');">
		    		<span class=ellipsis_row>${list.REMARK}</span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo2_${list.DRIVER_OT_APPLYID }" 
		    		   value="${list.REMARK }" />	            
            </td>
   			
   			 
   			 <td class="info_content_01">
						<a href="#" onClick="showMemo('${i.index}')">详情</a>
			</td>
			 <div id="opition_${i.index}" style="display: none;">
							<c:forEach items="${list.allDriverOtListList}" var="view">
									  <p>
									  <font color="#990099">${view.AFFIRM_LEVEL}${view.CHINESENAME}</font>
							          <c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未决裁</font></c:if>
								      <c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
								      <c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
							          &nbsp;${view.AFFIRMORIDEA}
							          </p>
									
							</c:forEach>
							
						
					</div>
			
			
			
			
   			 <td nowrap="nowrap" class="info_content_01">
			     <c:forEach items="${list.allDriverOtListList}" var="view" varStatus="j">
			      	${view.AFFIRM_LEVEL}${view.CHINESENAME}
			      	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
			      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
			      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
			      	<br>
		      	 </c:forEach>
		      </td>
		    
		      
		    </c:forEach>
	<tr align="center"> 
			 </tr>
		 </table>
		 <!-- Page Navigation Start-->
	<ait:page       
             link="/gaControlServlet"
             parameters="&operation=gaAffirm&content=viewAffirmDriverOtInfo&menu_code=${param.menu_code}&qryType=${qryType }&endDate=${endDate }&startDate=${startDate }&key=${key }&driverOtType=${driverOtType }&OTTypeCode=${OTTypeCode}"
             total="${driverOtListInt}"
             currentpage="${currentPage}"
             pagesize= "${pageSize}"
             beginlabel="paging_prv10"
             endlabel="paging_next10"
             prevlabel="paging_prv"
             nextlabel="paging_next"
             pagegroupsize="${pageGroupsize}"
             useJS="false"/>      
	<!-- Page Navigation End -->
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
<input type="hidden" name="currentPage" value="${currentPage}">
</form>
<table>
 <tr>  
  </tr>
</table>
</body>

</html>
