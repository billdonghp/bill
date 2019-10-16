<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.ga.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="getAllAffirm" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="voitureBean" class="com.ait.ga.bean.VoitureBean"/>
<%@page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,com.ait.util.StringUtil" %>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" /> 
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
<title>班车安排决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
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
function Expel(applyno){
	document.form1.action="/xlsReportServlet?operation=ga_voiture&xlsKey=ExcelVioture&applyno="+applyno;
	document.form1.submit();
}

function search(){
	document.form1.action="/gaControlServlet?menu_code=ga0212&operation=gaAffirm&content=viewBusArrangeAffirm";
	document.form1.submit();
}
function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var applyno = document.getElementsByName("applyno") ;
  	for (var i = 0; i< applyno.length ; i++){
		applyno[i].checked = selected ;
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}
function doAffirm(){
var msg1='<ait:message messageID="alert.ess.common.nodatatopass" module="ess"></ait:message>';
	
	var msg3='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';

	var msg5='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';

	var affirmno = document.getElementsByName("applyno");
	
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert(msg1);
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg3);
		return false;
	}                                       
    if (confirm(msg5)) {
	document.form1.action = "/gaControlServlet?menu_code=${param.menu_code}&operation=gaAffirm&flag=1&content=busArrangeApprovalOKorNO";
	document.form1.submit();
	}

}

function doReject(){
	var msg2='<ait:message messageID="alert.ess.common.nodatatoreject" module="ess"></ait:message>';
	var msg4='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
	var msg6='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	
	var affirmno = document.getElementsByName("applyno");
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert(msg2);
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg4);
		return false;
	} 
	if (confirm(msg6)) {
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaAffirm&excelFlag=2&content=busArrangeApprovalOKorNO";
	document.form1.submit();
	}
}
function busArrangeApprovalOKorNO(applyno,flag){
document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaAffirm&content=busArrangeApprovalOKorNO&flag="+flag+"&applyno="+applyno;
document.form1.submit();
}
function ImportExcel() {
    document.form1.action="/gaControlServlet?menu_code=ga0212&operation=gaAffirm&content=viewBusArrangeAffirm&excelFlag=excel";
	document.form1.submit();

}
</SCRIPT>

<body>
<%GaAffirm  ga = new GaAffirm(); %>
<form name="form1" method="post" action="">
	<input type="hidden" name="ck_all" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gatoolbar_affirm.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="13"></td>
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
		    
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		            区域                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  
		        <ait:codeClass codeClass="BusAreaType" name="busAreaType"  all="all"/>
		          </td> 
		           <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		           班车            
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  
		          <ait:codeClass codeClass="BusTypeCode" name="busTypeCode"  all="all"/>
		          </td>         
	                
	                
	                
	               
		           <td  class="info_title_01">&nbsp;
		          </td>
		         <td  class="info_content_00">
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
		         
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
				班车安排决裁
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
		      <td class="info_title_01"  nowrap><a href="#" onclick="checkAll();" style="color:red;"><!-- 全选 --> 
		       	<ait:message messageID="display.mutual.select_2" module="ess" /></a></td>
		      <td nowrap="nowrap" class="info_title_01">
				代请人</td>
			   <td nowrap="nowrap" class="info_title_01">
				区域</td>
			  <td nowrap="nowrap" class="info_title_01">
				日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				时间</td>
		      <td nowrap="nowrap" class="info_title_01">
				运营班车</td>
			  <td nowrap="nowrap" class="info_title_01">
				座位数</td>
		      <td nowrap="nowrap" class="info_title_01">
				乘坐人数</td>
			  <td nowrap="nowrap" class="info_title_01">
				乘坐率</td>
		      <td nowrap="nowrap" class="info_title_01">
				到达日期</td>
				 <td nowrap="nowrap" class="info_title_01">
				到达时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				备注</td>
			 
			  <td nowrap="nowrap" class="info_title_01">
				决裁情况</td>
			  <td nowrap="nowrap" class="info_title_01">
				意见</td>				

		    </tr>
		    <tr>
		    <c:forEach items="${busArrangeList}" var="list">
		    <tr align="center">	
		    <td nowrap="nowrap" align="center" class="info_content_01" >
		      	&nbsp;<c:if test="${ list.ACTIVE == 0 && list.AFFIRM_FLAG==0 }">
		      	 <input type="checkbox" name="applyno" value="${list.BUS_ARRANGE_APPLYID}" />
		      	</c:if>&nbsp;
			  </td>		
			   
		      <td nowrap="nowrap" align="center" class="info_content_01" >
    			${list.APPLYER_CHINESENAME }
    			<input type="hidden" name="applerId_${list.BUS_ARRANGE_APPLYID }" value="${list.BUS_ARRANGE_APPLYID }"> 
    			<input type="hidden" name="appler_Name_${list.BUS_ARRANGE_APPLYID }" value="${list.APPLYER_CHINESENAME }">
    			<input type="hidden" name="BUS_ARRANGE_BUS_TYPE_${list.BUS_ARRANGE_APPLYID }" value="${list.BUS }">
    			<input type="hidden" name="BUS_ARRANGE_BUS_AREA_${list.BUS_ARRANGE_APPLYID }" value="${list.BUS_AREA }">
    			<input type="hidden" name="BUS_ARRANGE_APPLY_STARTDATE_${list.BUS_ARRANGE_APPLYID }" value="${list.APPLY_STARTDATE }">
    			<input type="hidden" name="BUS_ARRANGE_APPLY_STARTTIME_${list.BUS_ARRANGE_APPLYID }" value="${list.APPLY_STARTTIME }">
    			<input type="hidden" name="BUS_ARRANGE_ARRIVE_DATE_${list.BUS_ARRANGE_APPLYID }" value="${list.ARRIVE_DATE }">
    			<input type="hidden" name="BUS_ARRANGE_ARRIVE_TIME_${list.BUS_ARRANGE_APPLYID }" value="${list.ARRIVE_TIME }">
    			
    			</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.BUS_AREA}&nbsp;</td>  
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.APPLY_STARTDATE}&nbsp;</td>
   			
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.APPLY_STARTTIME }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.BUS }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.SEATNUM }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.RIDENUM }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 <fmt:formatNumber value="${list.RIDEPERCENT }" type="percent" />
   			 &nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.ARRIVE_DATE }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.ARRIVE_TIME }&nbsp;</td>
   			
   			 <td class="info_content_00" nowrap="nowrap">
		    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('${list.BUS_ARRANGE_APPLYID}');">
		    		<span class=ellipsis_row>${list.REMARK}</span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo2_${list.BUS_ARRANGE_APPLYID }" 
		    		   value="${list.REMARK }" />	            
            </td>
   			 
   			 
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 <c:forEach items="${list.busArrangeAffiirmList}" var="view" varStatus="i">
   			 	<c:if test="${view.AFFIRMOR_ID != adminId}">
   			 	<font color="#990099">${view.AFFIRM_LEVEL }${view.CHINESENAME }</font>
   			 	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
		      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
   			 	</c:if>
   			 	<c:if test="${view.AFFIRMOR_ID == adminId}">
   			 	<input type="hidden" name="affirmNo_${list.BUS_ARRANGE_APPLYID}" value="${view.ID}">
   			 	<input type="hidden" name="MAX_AFFIRM_FLAG_${list.BUS_ARRANGE_APPLYID}" value="${view.MAX_AFFIRM_FLAG}">
   			 	<input type="hidden" name="AFFIRM_LEVEL_${list.BUS_ARRANGE_APPLYID}" value="${view.AFFIRM_LEVEL}">
   			 	<font color="#990099">${view.AFFIRM_LEVEL }${view.CHINESENAME }</font>
   			 		<c:if test="${view.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
   			 		<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
					<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
					
			      	<c:if test="${list.ACTIVE==0 && view.AFFIRM_FLAG==0}">|
			      		<a href="#" onclick="busArrangeApprovalOKorNO('${list.BUS_ARRANGE_APPLYID}','1')"><font color="red">通过</font></a>
			      	</c:if>
			      	<c:if test="${list.ACTIVE==0 && view.AFFIRM_FLAG==0}">
<!--			      		<a href="&affirmNo_${list.VOITURE_APPLYID}=${view.ID}&MAX_AFFIRM_FLAG_${list.VOITURE_APPLYID}=${view.MAX_AFFIRM_FLAG}&applyno=${list.VOITURE_APPLYID}&content=visiterApprovalOKorNO&affirmorIdea_=${list.VOITURE_APPLYID}">-->
			      			<a href="#" onclick="busArrangeApprovalOKorNO('${list.BUS_ARRANGE_APPLYID}','2')"><font color="red">否决</font></a>
			      	</c:if>
   			 	</c:if>
   			 	<br />
   			 </c:forEach>
   			 <td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${list.BUS_ARRANGE_APPLYID}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${list.AFFIRMORIDEA}</textarea></td>

		    </c:forEach>
	<tr align="center"> 
			 </tr>
		 </table>
		 <!-- Page Navigation Start-->
	<ait:page       
             link="/gaControlServlet"
             parameters="&operation=gaAffirm&content=viewBusArrangeAffirm&menu_code=${param.menu_code}&qryType=${qryType }&busTypeCode=${busTypeCode}&busAreaType=${busAreaType}&endDate=${endDate }&startDate=${startDate }&key=${key }"
             total="${busArrangeInt}"
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
		<td width="15">
		</td>
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
