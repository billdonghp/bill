<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<%@ page import="com.ait.gm.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<html>
<head>
<ait:processBarJs />
<title>就餐计划统计页面</title>
</head>
<script src="../js/prototype.js"></script>
<script src="../js/gmMuli.js"></script>
<SCRIPT type="text/javascript">
function dateSelectClick()
{
	document.form1.action="/gmControlServlet?operation=eatStatistic&menu_code=${param.menu_code}&content=view&eateryType="+$('eateryType').value;
 	document.form1.submit();
}
function eateryTypeCheck(){
	document.form1.action="/gmControlServlet?operation=eatStatistic&menu_code=${param.menu_code}&content=view&eateryType="+$('eateryType').value;
 	document.form1.submit();
}

function hrefAddsubmit(){
   if(confirm("确认提交数据吗？")){
   　　       
 	document.form1.action="/gmControlServlet?operation=eatStatistic&content=add&menu_code=${param.menu_code}";
 	beforeSubmit();
	document.form1.fireSubmit();
	afterSubmit();
  	////document.getElementById("submits").style.visibility = 'hidden'; 
 		
  }else{
   return;
  }
}

function statistic(num1){  
	var totallunch = 0;
	var totalsupper = 0;
	var totaldinner = 0;
	var totalbreak = 0;
	var lineone = 0;
	var linetwo = 0;
	var linethree = 0;
	var linefour = 0;
	var linefive = 0;
	var cpnyIds=$('cpnyId').value
	for(var i=0;i<num1;i++){	
		 totallunch = totallunch+parseInt(document.form1["lunchcounts_"+i].value);			
	     totalsupper = totalsupper+parseInt(document.form1["suppercounts_"+i].value);	     
	     totaldinner = totaldinner+parseInt(document.form1["dinnercounts_"+i].value);	     
	     totalbreak = totalbreak+parseInt(document.form1["breakcounts_"+i].value);	
	     if(cpnyIds=='63000000'){
	     lineone +=parseInt(document.form1["line_one_"+i].value);
	     linetwo +=parseInt(document.form1["line_two_"+i].value);
	     linethree +=parseInt(document.form1["line_three_"+i].value);
	     linefour +=parseInt(document.form1["line_four_"+i].value);
	     linefive +=parseInt(document.form1["line_five_"+i].value);
	     linesix +=parseInt(document.form1["line_six_"+i].value);
	     lineseven +=parseInt(document.form1["line_seven_"+i].value);
	     lineeight +=parseInt(document.form1["line_eight_"+i].value);
	     linenine +=parseInt(document.form1["line_nine_"+i].value);
	     lineten +=parseInt(document.form1["line_ten_"+i].value);
	     lineeleven +=parseInt(document.form1["line_eleven_"+i].value);
	     linetwelve +=parseInt(document.form1["line_twelve_"+i].value);
	     linethirteen +=parseInt(document.form1["line_thirteen_"+i].value);
	     linefourteen +=parseInt(document.form1["line_fourteen_"+i].value);
	     linefifteen +=parseInt(document.form1["line_fifteen_"+i].value);
	     linesixteen +=parseInt(document.form1["line_sixteen_"+i].value);
	     lineseventeen +=parseInt(document.form1["line_seventeen_"+i].value);
	     lineeighteen +=parseInt(document.form1["line_eighteen_"+i].value);
	     linenineteen +=parseInt(document.form1["line_nineteen_"+i].value);
	     linetwenty +=parseInt(document.form1["line_twenty_"+i].value);
	     }   
	}	
	  $('lunch').innerHTML = "计划总计("+totallunch+")";
	  $('supper').innerHTML = "计划总计("+totalsupper+")";
	  $('dinner').innerHTML = "计划总计("+totaldinner+")";
	  $('break').innerHTML = "计划总计("+totalbreak+")";	     
	  if(cpnyIds=='63000000'){
	  $('bus').innerHTML = "车辆总计("+(lineone+linetwo+linethree+linefour+linefive)+")";	   
	  $('lineone').innerHTML = "("+lineone+")";	   
	  $('linetwo').innerHTML = "("+linetwo+")";	
	  $('linethree').innerHTML ="("+linethree+")";	
	  $('linefour').innerHTML = "("+linefour+")";	
	  $('linefive').innerHTML = "("+linefive+")";
	  $('linesix').innerHTML = "("+linesix+")";	   
	  $('lineseven').innerHTML = "("+lineseven+")";	
	  $('lineeight').innerHTML ="("+lineeight+")";	
	  $('linenine').innerHTML = "("+linenine+")";	
	  $('lineten').innerHTML = "("+lineten+")";	
	  $('lineeleven').innerHTML = "("+lineeleven+")";	   
	  $('linetwelve').innerHTML = "("+linetwelve+")";	
	  $('linethirteen').innerHTML ="("+linethirteen+")";	
	  $('linefourteen').innerHTML = "("+linefourteen+")";	
	  $('linefifteen').innerHTML = "("+linefifteen+")";	
	   $('linesixteen').innerHTML = "("+linesixteen+")";	   
	  $('lineseventeen').innerHTML = "("+lineseventeen+")";	
	  $('lineeighteen').innerHTML ="("+lineeighteen+")";	
	  $('linenineteen').innerHTML = "("+linenineteen+")";	
	  $('linetwenty').innerHTML = "("+linetwenty+")";		
	   }   

	////document.getElementById("submits").style.visibility = 'visible'; 
}

</SCRIPT>
<body>

<form name="form1" method="post" action="">
<input type="hidden" name="menu_code" value="gm0107" />
<input type="hidden" name="plan_statistic" value="view" />
<input type="hidden" name="listsize" value="${fn:length(StatisticList)}" />
<input type="hidden" name="cpnyId" value="${cpnyId}"/>
<input type="hidden" name="temp" value="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_none.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP">
		
		<!-- display basic info -->
		<br>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><ait:message messageID="display.gm.eatery.plan.Statistics" module="gm"></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="center"><ait:processBar />&nbsp;<br></td>
				<c:if test="${errorMsg!=null}">
				<td align="center"><font style="color:red;">${errorMsg}</font></td>
				</c:if>		
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	        <tr>
	           <td nowrap="nowrap" class="info_title_01" ><!--  选择日期-->
	          	选择餐别
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <select name="eateryType" onchange="eateryTypeCheck()">
	          <option value="CH" <c:if test="${eateryType=='CH'}">selected</c:if>>中餐</option>
	          <option value="KO" <c:if test="${eateryType=='KO'}">selected</c:if>>韩餐</option>
	          </select>	       
	          </td>  
	         <td nowrap="nowrap" class="info_title_01" ><!--  选择日期-->
	          	选择日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="selectdate" value="${current_date}" onClick="setday(this);" readonly style="width:70px">	
	          </td>  
	          <td nowrap="nowrap" align="center" class="info_content_00">    
	          	<img src="../images/count.gif" style=" cursor:pointer;" title="就餐人数统计" onclick="statistic(${fn:length(StatisticList)});">&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;
	          <span id="submits" onClick="hrefAddsubmit()">	<img src="../images/btn/Apply.gif"></span>
	          </td>
	        </tr>
	     </table>
	     <br>		
		<table width="100%"  border="1" cellspacing="0" cellpadding="8" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		       <tr bgcolor="#F5F5F5">
		 		<td nowrap="nowrap" class="info_title_01" rowspan="2">
		 			部门
		 		</td>	  
			 	
		 		<td  nowrap="nowrap" class="info_title_01" colspan="2">
		 			早餐&nbsp;<span id="break"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01" colspan="2">
		 			午餐&nbsp;<span id="lunch"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01" colspan="2">
		 			晚餐&nbsp;<span id="supper"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01" colspan="2">
		 			夜餐&nbsp;<span id="dinner"></span>
		 		</td>	
		 		<c:if test="${cpnyId=='63000000'}">
		 		<td  nowrap="nowrap" class="info_title_01" colspan="20">
		 			班车 &nbsp;<span id="bus"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01" rowspan="2">
		 			备注</span>
		 		</td>
		 		</c:if>
			    </tr>			    
			   <tr bgcolor="#F5F5F5">
			   <td  nowrap="nowrap" class="info_title_01">
		 			计划人数
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			刷卡人数
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			计划人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			刷卡人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			计划人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			刷卡人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			计划人数
		 		</td>
		 		<td  nowrap="nowrap"  class="info_title_01">
		 			刷卡人数
		 		</td>	
		 		<c:if test="${cpnyId=='63000000'}">
		 		<td  nowrap="nowrap" class="info_title_01">
		 			牟平17点<span id="lineone"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			牟平20点<span id="lineone"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			牟平21点<span id="lineone"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			牟平夜班<span id="lineone"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			开发区17点<span id="linetwo"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			开发区20点<span id="linetwo"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			开发区21点<span id="linetwo"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			开发区夜班<span id="linetwo"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			福山17点<span id="linethree"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			福山20点<span id="linethree"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			福山21点<span id="linethree"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01"">
		 			福山夜班<span id="linethree"></span>
		 		</td>
		 		
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山17点<span id="linefour"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山20点<span id="linefour"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山21点<span id="linefour"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			莱山夜班<span id="linefour"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘17点<span id="linefive"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘20点<span id="linefive"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘21点<span id="linefive"></span>
		 		</td>
		 		<td  nowrap="nowrap" class="info_title_01">
		 			芝罘夜班<span id="linefive"></span>
		 		</td>
		 		</c:if>		  
			    </tr>			    
			    <c:forEach items="${StatisticList}" var="statisticList" varStatus="i">
			 		<tr style="color: #666666;">
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.DEPTNAME}
				 		<input type="hidden" name="deptid_${i.index}" value="${statisticList.DEPTID}">
				 		</td>			 		
				 		<td nowrap="nowrap"  align="center">
				 		<input name="breakcounts_${i.index}" value="${statisticList.BREAKCOUNTS}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.BREAKRECORDS}
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		<input name="lunchcounts_${i.index}" value="${statisticList.LUNCHCOUNTS}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td nowrap="nowrap" align="center">
				 		${statisticList.LUNCHRECORDS}
				 		</td>	
				 		<td nowrap="nowrap"  align="center">
				 		<input name="suppercounts_${i.index}" value="${statisticList.SUPPERCOUNTS}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.SUPPERRECORDS}
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		<input name="dinnercounts_${i.index}" value="${statisticList.DINNERCOUNTS}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td nowrap="nowrap"  align="center">
				 		${statisticList.DINNERRECORDS}
				 		</td>	
				 		<c:if test="${cpnyId=='63000000'}">
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_seven_${i.index}" value="${statisticList.LINE_ONE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_one_${i.index}" value="${statisticList.LINE_TWO}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center"">
				 			<input name="line_seventeen_${i.index}" value="${statisticList.LINE_THREE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_six_${i.index}" value="${statisticList.LINE_FOUR}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_two_${i.index}" value="${statisticList.LINE_FIVE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_eight_${i.index}" value="${statisticList.LINE_ONE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_sixteen_${i.index}" value="${statisticList.LINE_TWO}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center"">
				 			<input name="line_twelve_${i.index}" value="${statisticList.LINE_THREE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_three_${i.index}" value="${statisticList.LINE_FOUR}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_nine_${i.index}" value="${statisticList.LINE_FIVE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_eighteen_${i.index}" value="${statisticList.LINE_ONE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_thirteen_${i.index}" value="${statisticList.LINE_TWO}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center"">
				 			<input name="line_four_${i.index}" value="${statisticList.LINE_THREE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_ten_${i.index}" value="${statisticList.LINE_FIVE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_twenty_${i.index}" value="${statisticList.LINE_ONE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_fourteen_${i.index}" value="${statisticList.LINE_TWO}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center"">
				 			<input name="line_five_${i.index}" value="${statisticList.LINE_THREE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_eleven_${i.index}" value="${statisticList.LINE_FOUR}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_nineteen_${i.index}" value="${statisticList.LINE_FIVE}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 			<input name="line_fourteen_${i.index}" value="${statisticList.LINE_FOUR}" type="text" style="width: 50px" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
				 		</td>
				 		<td  nowrap="nowrap" align="center">
				 		<textarea name="remark_${i.index}" style=" height: 20px;width:150px " type="_moz" onfocus="this.style.height='60px'" onblur="this.style.height='20px';">${statisticList.REMARK}</textarea>				 			
				 		</td>
				 		
				 		</c:if>								    			   
				  </tr>
			    </c:forEach>
		 </table>
		</form>

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
<ait:xjos /> 
</body>
</html>