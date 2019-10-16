<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%@ page import="com.ait.ev.bean.EvaluateInfo"%>
<%@ page import="com.ait.ev.bean.EvaluateItem"%>
<jsp:useBean id="evaluate" scope="page"
	class="com.ait.ev.bean.EvaluateInfo" />
<jsp:useBean id="evaluateItem" scope="page"
	class="com.ait.ev.bean.EvaluateItem" />
<%@ page
	import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil,com.ait.util.DateUtil"%>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="itemList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="evaluateApplyList" scope="request"
	class="java.util.ArrayList" />

<html>
<head>
<!-- ev_apply_view.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>评价输入</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function changeRoomNames(){
if(document.form2.selectDept.value != ""){

		var url = "/ajaxControlServlet" ;
     	var pars = "operation=changePostCodeNames&DEPTID="+document.form2.selectDept.value;

		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );}else{
        	$('positionDiv').innerHTML = '';
   }
}
function showResponse(XmlHttpRequest){
	document.getElementById("positionDiv").innerHTML = XmlHttpRequest.responseText;
}

function caclPrice(k){
    var sum = 0;
    var sumt = 0;   
    var sumtg = 0; 
    var ii = 1;
    for(var i=0;i<=70;i++){
	    if(document.getElementById("item"+i+"_"+k) != null){
	    	if("item"+i+"_"+k == "item34_"+k || "item"+i+"_"+k == "item35_"+k 
		    	|| "item"+i+"_"+k == "item36_"+k || "item"+i+"_"+k == "item37_"+k){
	    		 	var ss=document.getElementById("item"+i+"_"+k);
	  	       		var svv = ss.value;
	  	       		if(svv !=''){
	  	       				sumt = accAdd(sumt,svv); 
	  	       		} 
	  	       		ii++;
		    }else if("item"+i+"_"+k == "item38_"+k || "item"+i+"_"+k == "item39_"+k){
		    	var sss=document.getElementById("item"+i+"_"+k);
  	       		var svvv = sss.value;
  	       		if(svvv !=''){
  	       				sumtg = accAdd(sumtg,svvv); 
  	       		} 
					ii++;
		    }else{
		    	var s=document.getElementById("item"+i+"_"+k);
       			var sv = s.value;
      			 if(sv !=''){
      					 /// sum = sum + parseFloat(sv); 
       						sum = accAdd(sum,sv); 
       			 } 
			    }
	    }
    }
  ///$("total_" + k).innerHTML = sum;
  if(ii == 1){
	  $("total_" + k).value = sum;
	}else{
    	var grjx_NO = $("grjx_" + k).value = sumt;
      	var gzxw_NO = $("gzxw_" + k).value = sumtg;
      	var demoNo = (grjx_NO/130*80)+gzxw_NO ;
      	$("total_" + k).value = demoNo+sum;
	  	}
}
function accAdd(arg1,arg2){ 
var r1,r2,m; 
try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
m=Math.pow(10,Math.max(r1,r2)) 
return (arg1*m+arg2*m)/m 
} 



function search(){
document.form2.action="/evControlServlet?operation=evaluateApply&content=evaluateApplyView&menu_code=${param.menu_code}";
document.form2.submit();

}

function Save(){
     	///var cpny = document.ApplyForm.cpny.value ;
  
		var size = document.form1.applySize.value ;
		
		var c = 0;
		 
		for (var i=0; i<size; i++){
		
			tags = document.form1("ck_" + i);
			if(tags.checked == true){
			   
			    var s=document.getElementById("total_"+i).value;
			 
				if(s > 100){
		
				   alert("评价总分不得大于100分");
			       return ;
				}
	
			   c++;
			   
			    var ad=document.getElementById("affirmData_"+i).value;
			    var id=document.getElementById("itemData_"+i).value;
				if(ad == '0'){
					alert("未设置决裁者不能申请,请设置决裁者");
					return ;
				}
				if(id == '0'){
					alert("未设置评价项目不能申请,请设置评价项目");
					return ;
				}
			}
			
		}
		
		
		
		
		
		if (c == 0){
		    alert("请选择申请的员工!!!");
		    return ;
		}
		
		if (confirm('是否确认保存') ){
		       document.form1.action="/evControlServlet?operation=evaluateApply&content=evaluateApplyAdd&menu_code=${param.menu_code}&year=${year}&month=${month}&selectDept=${selectDept}&selectPosition=${selectPosition}&status_code=${status_code}";
		       document.form1.submit();
		       document.getElementById("trname").style.display="none";//避免重复提交，隐藏按钮
		}
		
		    
	
}



 function band(backColor,textColor,i,isupdate,isOver,plan)
	{
		 var t;
		 if(typeof(preEl)!='undefined')
		 {
		 preEl.bgColor=orgBColor;
	
		 try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
		 }
		 var el = event.srcElement;
		 el = el.parentElement;
		 orgBColor = el.bgColor;
		 orgTColor = el.style.color;
		 el.bgColor=backColor;
		 try{ChangeTextColor(el,textColor);}catch(e){;}
		 preEl = el;
		 document.form1.temp.value=i;
		 document.form1.isCanUpdate.value=isupdate;
		 document.form1.isOver.value=isOver;
		 document.form1.isPlan.value=plan;
	} 


function checkAll1()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var applyno = document.getElementsByName("applyno") ;
  	for (var i = 0; i< applyno.length ; i++){
		applyno[i].checked = selected ;
		
       
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}

function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var size = document.form1.applySize.value ;
  	for (var i=0; i<size; i++){
		document.form1("ck_" + i).checked = selected ;
	    document.form1("ck_" + i).parentNode.parentNode.style.background=document.form1("ck_" + i).checked?"#e7f0ef":"#fff"
		//选中变色   
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}


function ChangeColor(o){
o.parentNode.parentNode.style.background=o.checked?"#e7f0ef":"#fff"
}


</SCRIPT>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="inc/common_toolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info -->
		<form action="" name="form2" method="post">
		<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<br>
			<tr>
				<td class="title1"><!-- 查询条件 --> <ait:message
					messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>

						<td nowrap="nowrap" class="info_title_01"><!-- 评价月份--> 评价月份</td>
						<td nowrap="nowrap" class="info_content_00"><ait:date
							yearName="year" monthName="month" yearSelected="${year}"
							monthSelected="${month}" yearPlus="10" /></td>

						<td nowrap="nowrap" class="info_title_01"><!--结束日期  --> 部门</td>
						<td nowrap="nowrap" class="info_content_00"><select
							name="selectDept">
							<option value="">全部</option>
							<c:forEach items="${deptList}" var="deptList" varStatus="i">

								<option value="${deptList.DEPTID}"
									<c:if test="${deptList.DEPTID==selectDept}">selected</c:if>>
								${deptList.DEPTNAME}</option>
							</c:forEach>
						</select></td>
						<td nowrap="nowrap" class="info_title_01"><!--结束日期  --> 职位</td>
						<td nowrap="nowrap" class="info_content_00"><select
							name="selectPosition">

							<c:forEach items="${positionList}" var="positionList"
								varStatus="i">

								<option value="${positionList.AFFIRM_POSITION}"
									<c:if test="${positionList.AFFIRM_POSITION==selectPosition}">selected</c:if>>
								${positionList.AFFIRM_POSITION_NAME}</option>
							</c:forEach>
						</select></td>
						<td nowrap="nowrap" class="info_title_01">状态</td>
						<td nowrap="nowrap" class="info_content_00"><select
							name="status_code">
							<option value="N"
								<c:if test="${status_code=='N'}">selected</c:if>>未保存</option>
							<option value="Y"
								<c:if test="${status_code=='Y'}">selected</c:if>>已保存</option>
						</select></td>



					</tr>
				</table>
				</td>
			</tr>
		</table>
		</form>

		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<%
			if (!errorMsg.equals("")) {
		%>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td align="center"><font color="red"><%=errorMsg%></font></td>
			</tr>
		</table>
		<%
			}
		%>
		<form name="form1" method="post" action=""><input type="hidden"
			name="menu_code" value="${param.menu_code}"> <input
			type="hidden" name="temp" value=""> <input type="hidden"
			name="isCanUpdate" value=""> <input type="hidden"
			name="isOver" value=""> <input type="hidden" name="isPlan"
			value=""> <input type="hidden" name="ck_all" value="0" /> <input
			type="hidden" name="applySize" value=<%=evaluateApplyList.size()%> />
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">评价输入</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">

			<tr align="center" bgcolor="#F5F5F5">
				<!--		    <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"> 全选  -->
				<!--		       	<ait:message messageID="display.mutual.select_2" module="ess" /></a></td>-->
				<td nowrap="nowrap" class="info_title_01"><a href="#"
					onclick="checkAll();" style="color: red;"><!-- 全选 --> 全<br>
				选</a></td>
				<td nowrap="nowrap" class="info_title_01">序<br>
				号</td>

				<td nowrap="nowrap" class="info_title_01">职号</td>
				<td nowrap="nowrap" class="info_title_01">姓名</td>

				<td nowrap="nowrap" class="info_title_01">评价<br>
				月份</td>
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_title_01">职位</td>
				<td nowrap="nowrap" class="info_title_01">员工<br>
				状态</td>


				<%
					for (int k = 0; k < itemList.size(); k++) {
						evaluateItem = (EvaluateItem) itemList.get(k);

						String itemName = evaluateItem.getItemName();
						String itemProportion = evaluateItem.getItemProportion();
						if (!"".equals(itemProportion) && !(itemProportion == null)) {
							itemName = itemName + "<br>" + itemProportion + "%";
						}
				%>

				<td nowrap="nowrap" class="info_title_01" width="60"><%=itemName%></td>

				<%
					}
				%>

				<c:if test="${flag}">
				<td nowrap="nowrap" class="info_title_01">个人绩效<br>
				考核分数</td>
				<td nowrap="nowrap" class="info_title_01">工作行为<br>
				考核分数</td>
				</c:if>

				<td nowrap="nowrap" class="info_title_01">评价<br>
				总分</td>

				<td nowrap="nowrap" class="info_title_01">上月<br>
				排名</td>

				<td nowrap="nowrap" class="info_title_01">说明事项</td>
				<td nowrap="nowrap" class="info_title_01">决裁者</td>

			</tr>
			<%
				for (int i = 0; i < evaluateApplyList.size(); i++) {
					evaluate = (EvaluateInfo) evaluateApplyList.get(i);
			%>
			<tr height="30">
				<td height="30" class="info_content_10" nowrap="nowrap">
				<%
					if (evaluate.getACTIVITY().equals("Y")) {
				%> <input type="checkbox"
					name="ck_<%=i%>" value="<%=evaluate.getPERSON_ID()%>"
					onclick="ChangeColor(this)"> <%
 	} else {
 			out.println("&nbsp;");
 		}
 %> <input type="hidden" name="person_id_<%=i%>"
					value="<%=evaluate.getPERSON_ID()%>"></td>
				<td class="info_content_10" nowrap="nowrap"><%=i + 1%></td>

				<td class="info_content_10" nowrap="nowrap"><%=evaluate.getEMPID()%></td>
				<td class="info_content_10" nowrap="nowrap"><%=evaluate.getCHINESENAME()%></td>

				<td class="info_content_10" nowrap="nowrap"><%=evaluate.getMONTH()%></td>
				<input type="hidden" name="month_<%=i%>"
					value="<%=evaluate.getMONTH()%>">
				<td class="info_content_10" nowrap="nowrap"><%=evaluate.getDEPTNAME()%></td>
				<input type="hidden" name="deptid_<%=i%>"
					value="<%=evaluate.getDEPTID()%>">
				<td class="info_content_10" nowrap="nowrap"><%=evaluate.getPOST_CODE_NAME()%></td>
				<input type="hidden" name="post_code_<%=i%>"
					value="<%=evaluate.getPOST_CODE()%>">
				<td class="info_content_10" nowrap="nowrap"><%=evaluate.getSTATUS()%></td>


				<!-- onkeyup="if(isNaN(value))execCommand('undo')" -->

				<%
					if (itemList.size() > 0) {
				%>
				<input type="hidden" name="itemData_<%=i%>" value="1">
				<%
					for (int j = 0; j < itemList.size(); j++) {
								evaluateItem = (EvaluateItem) itemList.get(j);
								String itemId = evaluateItem.getItemId();
								String itemValue = "0";
								if ("item0".equals(itemId)) {
									itemValue = "75";
								}
				%>

				<td class="info_content_10" nowrap="nowrap"><input
					id="<%=itemId%>_<%=i%>" type="text" style="width: 50px;"
					name="<%=itemId%>_<%=i%>" value=<%=itemValue%> onblur=caclPrice(<%=i%>) onChange="if(!(/^(\+|-)?\d+($|\.\d+$)/.test(value))) this.value=''">
				</td>

				<%
					}
						} else {
				%>
				<input type="hidden" name="itemData_<%=i%>" value="0">
				<%
					}
				%>

				<!--  <td class="info_content_10" nowrap="nowrap" class="info_content_01"><div id="total_<%=i%>">&nbsp;</div></td>-->
				<c:if test="${flag}">
				<td class="info_content_10" nowrap="nowrap"><input
					id="grjx_<%=i%>" type="text" style="width: 50px;"
					name="grjx_<%=i%>" value="0" readonly></td>
				<td class="info_content_10" nowrap="nowrap"><input
					id="gzxw_<%=i%>" type="text" style="width: 50px;"
					name="gzxw_<%=i%>" value="0" readonly></td>
				</c:if>
				
				<td class="info_content_10" nowrap="nowrap"><input
					id="total_<%=i%>" type="text" style="width: 50px;"
					name="total_<%=i%>" value="0" readonly></td>

				<td class="info_content_10" nowrap="nowrap"><%=StringUtil.checkNull(evaluate.getLAST_MONTH_PLACE())%>&nbsp;/&nbsp;<%=StringUtil.checkNull(evaluate
								.getLAST_MONTH_AMOUNT())%>
				</td>
				<input type="hidden" name="last_month_place_<%=i%>"
					value="<%=evaluate.getLAST_MONTH_PLACE()%>">
				<input type="hidden" name="last_month_amount_<%=i%>"
					value="<%=evaluate.getLAST_MONTH_AMOUNT()%>">

				<td class="info_content_10" width='100'
					style='word-break: break-all' align="left"><textarea
					name="remark_<%=i%>" style="height: 25px; width: 100px" type="_moz"
					onfocus="this.style.height='50px'"
					onblur="this.style.height='25px';"></textarea></td>
				<%
					if (evaluate.getAffirmData() != null) {
				%>
				<td align="center" height="30" id="leftnewstd"><span
					class=ellipsis_row2 title='<%=evaluate.getAffirmData()%>'><%=evaluate.getAffirmData()%></span>
				</td>
				<input type="hidden" name="affirmData_<%=i%>" value="1">
				<%
					} else {
				%>
				<td align="center" height="30" style="color: red;" id="leftnewstd"><!-- 未设置决裁者  -->
				<span class=ellipsis_row2 title='未设置决裁者'>未设置决裁者</span> <input
					type="hidden" name="affirmData_<%=i%>" value="0"></td>
				<%
					}
				%>


			</tr>
			<%
				}
			%>
			<input type="hidden" name="currentPage" value="${currentPage}">
		</table>

		<!-- Page Navigation Start--> <ait:page link="/evControlServlet"
			parameters="&operation=evaluateApply&content=evaluateApplyView&menu_code=${param.menu_code}&year=${year}&month=${month}&selectDept=${selectDept}&selectPosition=${selectPosition}&status_code=${status_code}"
			total="${resultCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" />
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
<ait:xjos />
</body>

</html>
