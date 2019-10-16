<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,com.ait.utils.FormUtil,com.ait.sysparam.*" %>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" />
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil"%>
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date,com.ait.sysparam.*"%>
<%@ page import="com.ait.ga.servlet.ExpressApplyAndAffirmCommand"%>
<jsp:useBean id="applyNoFaht" class="java.lang.String" scope="request" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/css/default.css" rel="stylesheet" type="text/css">
	<title>快件申请</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

	var tableUtil = new Object();
	var i=0;

	tableUtil.appendRow = function(){

		i = Number(document.form1.maxRowNum.value)+1;
		document.form1.maxRowNum.value = i;

		document.form1.rowCount.value = Number(document.form1.rowCount.value)+1;

		var nTr = document.getElementById('operateTable').insertRow();

        td = nTr.insertCell() ;
        td.className = "info_content_01" ;
        td.innerHTML = "<select name='expressType_"+i+"' > " + document.getElementById('expressType_0').innerHTML + "</select>" ;

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='sendToPerson_" + i + "' style='width:100px'>" ;

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='mobileA_" + i + "' style='width:100px'>" ;

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "省<input type='text' name='cityArriveS_" + i + "' style='width:80px'/>&nbsp;" +
						"市<input type='text' name='cityArrive_" + i + "' style='width:80px'/>&nbsp;" +
						"区<input type='text' name='cityArriveQ_" + i + "' style='width:80px'/>&nbsp;" +
						"详细地址<input type='text' name='cityArriveD_" + i + "' style='width:150px'/>" +
						"邮编<input type='text' name='postCodeA_" + i + "' style='width:80px'>";


		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='expType_"+i+"' > " + document.getElementById('expType_0').innerHTML + "</select>" ;

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='postDescription_"+i+"'  style='width:150px' value='' />";

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='postAddress_"+i+"'  style='width:150px' value='' />";

		/**
		 td = nTr.insertCell() ;
		 td.className = "info_content_01" ;
		 td.innerHTML = "<input type='text' name='cause_"+i+"'  style='width:150px' value='' />";

		 td = nTr.insertCell() ;
		 td.className = "info_content_01" ;
		 td.innerHTML = "<input type='text' name='sendToPerson_"+i+"'  style='width:150px' value='' />";


		 td = nTr.insertCell() ;
		 td.className = "info_content_01" ;
		 td.innerHTML = "<textarea name='Note_" + i + "' style=' height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px';></textarea>";
		 **/

		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";
	}

	tableUtil.deleteRow = function(){

		document.form1.rowCount.value = Number(document.form1.rowCount.value)-1;

		var radioArr = document.getElementsByName('rowNum');
		var tbody = document.getElementById('operateTable').tBodies[0];
		var flag = false;
		for(var i=0;i<radioArr.length;i++)
			if(radioArr[i].checked){
				tbody.removeChild(radioArr[i].parentNode.parentNode);
				flag = true;
			}
		if(flag)
			return;
		else
			alert('请先选择要删除的行');
	}



	var time=null;
	function SearchEmp(condition,id){
		if(time!=null){
			clearTimeout(time);
			time=null;
		}
		time=setTimeout(function(){
			//	alert(condition);
			SearchE(condition,id);
		},500);
	}

	function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
		var pars = "operation=GaSearchEmployee&condition=" + encodeURIComponent(condition);

		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		layer = $('emp_list');
		layeri = $('iemp');

		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;

		new Ajax.Request(
				url,{method: 'post', parameters: pars, onComplete: showResponse1}
		);
	}

	function showResponse1(XmlHttpRequest){
		var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
		size = size*25+30;
		if(size<40){
			layeri.style.height = 48;
			layer.style.height = 48;
		}else if(size<210){
			layeri.style.height = size;
			layer.style.height = size;
		}else{
			layeri.style.height = 210;
			layer.style.height = 210;
		}

		layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
		layer.style.visibility = 'visible';
		layeri.style.visibility = 'visible';
	}

	function layerClose(){
		$('emp_list').innerHTML = "" ;
		layeri.style.visibility = 'hidden';
		layer.style.visibility = 'hidden';

	}

	function updateValue(cell) {
		$('SENDPERSON').value=cell.childNodes[0].firstChild.nodeValue;
		$('SENDPERSON1').value=cell.childNodes[2].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		$('personId').innerHTML = cell.childNodes[2].firstChild.nodeValue ;
		layerClose();
	}

	var  patrn=/^[A-Za-z]{2}[0-9]{9}[A-Za-z]{2}$/;
	var  patrnNew=/^[0-9]{13}$/;
	var  empidpatrn=/^[A-Za-z]{2}[0-9]{7}$/;
	function Save1() {
		if($('personId').innerHTML==""||$('personId').innerHTML==null){
			alert("请选择发件人！");
			return;
		}
		if(document.form1.SENDPERSON.value==""||document.form1.SENDPERSON.value==null){
			alert("发件人不能为空！");
			return;
		} if(document.form1.citySent.value==""||document.form1.citySent.value==null){
			alert("寄达城市不能为空！");
			return;
		} if(document.form1.postAddress.value==""||document.form1.postAddress.value==null){
			alert("收件单位不能为空！");
			return;
		} if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
			alert("没有设置决裁者！请设置");
			return;
		}
		if(document.form1.expressType.value == "" ||document.form1.expressType.value == null ){
			alert("必须有邮件种类！");
			return;
		} if(document.form1.expressType.value == 'EMS'){
			if(!patrn.test(document.form1.postNumber.value) && !patrnNew.test(document.form1.postNumber.value)){
				alert("请输入正确格式的邮件号！");
				return;
			}
		}
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=andExpressApply&firstaffrim="+document.form1.firstaffirmor0.value;
		document.form1.submit();

	}

	function Save() {


		var rowNum = Number($("maxRowNum").value);
		for(var i=0; i<=rowNum; i++){
			if($('personId').innerHTML==""||$('personId').innerHTML==null){
				alert("请选择发件人！");
				return;
			}
			if(document.form1.SENDPERSON.value==""||document.form1.SENDPERSON.value==null){
				alert("发件人不能为空！");
				return;
			}
			/**
			 if($("postAddress_" + i).value==""||$("postAddress_" + i).value==null){
		   alert("收件单位不能为空！");
		   return;
		   }
			 **/
			if($("cityarrive_" + i).value==""||$("cityarrive_" + i).value==null){
				alert("寄达城市不能为空！");
				return;
			}
			if($("affirmor").value==""||$("affirmor").value==null){
				alert("没有设置决裁者！请设置");
				return;
			}
			if($("expType_" + i).value==""||$("expType_" + i).value==null){
				alert("必须有邮件种类！");
				return;
			}
		}
//	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=andExpressApply&firstaffrim="+document.form1.firstaffirmor0.value;

		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=andExpressApply";
		document.form1.submit();

		document.getElementById("applysaveid").style.display="none";//避免重复提交，隐藏按钮


	}
	function CheckNumber(tempvalue){
		var   patrn=/^[0-9]+.{0,1}[0-9]{0,3}$/;
		if  (!patrn.test(tempvalue)){
			alert("输入大于0的整数或者小数！");
			return  false;
		}
		return true;
	}
	function city(){
		if(document.form1.citySent.value != ""&& $("cityarrive_" + i).value != ""){
			var url = "/ajaxControlServlet" ;
			var pars = "operation=expressNames&citySent="+document.form1.citySent.value+"&cityarrive="+$("cityarrive_" + i).value;

			new Ajax.Request(
					url,{method: 'post', parameters: pars, onComplete: showResponse}
			);}else{
			//document.getElementById("expressType").innerHTML = "" ;
		}
	}
	function showResponse(XmlHttpRequest){
		document.getElementById("expressTypeDiv").innerHTML = XmlHttpRequest.responseText;
	}
	function expeses(){
		var expenese =document.form1.expressType_0.value+"1";
		document.form1.costs.value = document.getElementById(expenese).value;
	}
	function checkTimeValue(str){
		var z=/([0-1]\d)|2[0-3]:(([0-5]\d)|60)/;
		if(!z.test($(str).value)){
			alert("请输入正确的时间格式(xx:xx)");
			$(str).focus();
			return false;
		}

	}
	function findAffirmor(){
		var isOver_kg = document.form1.isOver_kg.value;
		//location.href="/ga/ga_express_apply.jsp?menu_code=${param.menu_code}&isOver_kg="+isOver_kg;

		document.form1.action='/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=sendToExpressApplyPage';
		document.form1.submit();
	}
	//function deletechange(AffirmorName,AffirmorId,AffirmLevel,ApplyNoFa){
	//	alert("你确定要删除决裁者吗？");
	//	document.form1.action='/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=deleteExpressAffrimInfo&ApplyNoFa='+ApplyNoFa+'&AffirmLevel='+AffirmLevel+'&AffirmorId='+AffirmorId+'&AffirmorName='+AffirmorName;
	//	document.form1.submit();

	//}
</SCRIPT>
<%
	String isKg = request.getParameter("isOver_kg")!=null?request.getParameter("isOver_kg"):"0";
%>
<body>

<form name="form1" method="post" action="">
	<input type="hidden" name="maxRowNum" value="0">
	<input type="hidden" name="rowCount" value="0">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="15"></td>
			<td width="11" height="33" valign="TOP" align="RIGHT"><img
					src="../images/tablbk01_r1_c1.gif"></td>
			<td background="../images/tablbk01_r1_c2.gif">

				<!-- display toolbar -->
				<%@ include file="../inc/toolbar_apply.jsp"%>
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

				<!-- display 3 level menu -->
				<%@ include file="../inc/thirdToolBar.jsp"%>

				<!-- display content -->
				<br>
				<table width="100%" border="0" cellpadding="0" cellspacing="1" >
					<tr>
						<td align="left" class="title1" colspan="10">
							快件申请<font color="red" size="2">${declaration}</font>
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
							申请人</td>
						<td nowrap="nowrap" class="info_content_00" ><%=admin.getChineseName()%></td>
						<td nowrap="nowrap" class="info_title_01">
							部门</td>
						<td nowrap="nowrap" class="info_content_00" ><%=admin.getDepartment() %></td>
						<td nowrap="nowrap" class="info_title_01">
							发件人</td>
						<td nowrap="nowrap" class="info_content_00">
							<input id="SENDPERSON" name="SENDPERSON" type="text" size="6" value="${key}" onkeyup="SearchEmp(this.value,this.id)">
							<input id="SENDPERSON1" name="SENDPERSON1" type="hidden">
							<span id="name"></span><span id="personId"></span></td>

						<td nowrap="nowrap" class="info_title_01">发件人手机号</td>
						<td nowrap="nowrap" class="info_content_00">
							<input id="mobileS" name="mobileS" type="text" size="11" value="">
						 </td>

						<%--<td nowrap="nowrap" class="info_title_01">
                          邮件费</td>
                          <td nowrap="nowrap" class="info_content_00" ><input type="text" name="costs" style="width:150px" value="16" onkeyup="CheckNumber(this.value)">&nbsp;元</td>--%>

						<td nowrap="nowrap" class="info_title_01">发件地址</td>
						<td nowrap="nowrap" class="info_content_00" >
							省<input type="text" name="citySentS" style="width:80px" value="山东省">&nbsp;
							市<input type="text" name="citySent" style="width:80px" value="烟台市">&nbsp;
							区<input type="text" name="citySentQ" style="width:80px" value="福山区">&nbsp;
							详细地址<input type="text" name="citySentD" style="width:150px" value="开发区五指山路28号">&nbsp;
                            邮编<input type="text" name="postCodeS" style="width:80px" value="264006">&nbsp;
						</td>
						<td nowrap="nowrap" class="info_title_01">
                          快件重量</td>
                          <td nowrap="nowrap" class="info_content_00" >
                              <select name="isOver_kg" onchange="findAffirmor()">
                                  <option value="0" <%if (isKg.equals("0")) {%> selected <%}%>>小于1公斤</option>
                                  <option value="1" <%if (isKg.equals("1")) {%> selected <%}%>>大于1公斤</option>
                              </select>
                          </td>
						<td nowrap="nowrap" class="info_title_01">
							决裁者</td>
						<td nowrap="nowrap">
							<%
								GaAffirm ga = new GaAffirm();
								EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
										EssSysparam.class, admin.getCpnyId());

								List list =ga.getAffirmor1(admin.getAdminID(),"ExpressApply",essSysparam.isContainsOwner());%>
							<%if(!list.isEmpty()){%>
							<input type="hidden" value="<%=list.size()%>" name="affirmor">
							<table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

								<%int affirmLevel=10;
									String affirmorId="01";
									boolean flag = false;
									for(int i=0;i<list.size();i++){
										gaAffirmList=(GaAffirmList)list.get(i);
										if(gaAffirmList.getAffirmorDuty()!=null && isKg.equals("0") && gaAffirmList.getAffirmorDuty().equals("C_12005_43")){
											continue;
										}

										if(flag){
											if(gaAffirmList.getAffirmorId().equals(affirmorId)){
												continue;
											}
										}
										flag=true;
										affirmorId=gaAffirmList.getAffirmorId();
								%>
								<tr>
									<td>

										<font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
										<input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId">
										<input type="hidden" value="<%=gaAffirmList.getAffirmorDuty()%>" name="affirmorDuty">
										<font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>
										<%
											if (gaAffirmList.getAffirmorDuty()!=null && (gaAffirmList.getAffirmorDuty().equals("C_12005_93775") ||
													gaAffirmList.getAffirmorDuty().equals("C_12005_43") || gaAffirmList.getAffirmorDuty().equals("C_12005_1330060"))) {
												affirmLevel=gaAffirmList.getAffirmLevel();
											}

											if (gaAffirmList.getAffirmLevel()<affirmLevel){
										%>
										<img src="../images/btn/Delete_little.gif" title="删除" onclick='affirmorlist.deleteRow(this.parentNode.parentNode.rowIndex);'  align="absmiddle"><br />
										<%
											}
										%>
									</td>
								</tr>
								<%
									}
								%>
							</table>
							<%
							} else {
							%> <input type="hidden" value="" name="affirmor">
							<table align="center">
								<tr>
									<td><font color="red">没有决裁者</font></td>
								</tr>
							</table>
							<%
								}
							%>
						</td>
						<%--<input name="Note" type="hidden" value="">
                         <td nowrap="nowrap" class="info_title_01">
                           发件城市</td>
                       <td nowrap="nowrap" class="info_content_00">
                           <select name="citySent" onchange="city()">
                           <option value="">请选择 </option>
                           <c:forEach items="${list1}" var="list">
                               <option value="${list.GIVE_CITY }">${list.GIVE_CITY } </option>
                           </c:forEach>
                           </select>
                       </td>--%>
						<!--
		      </tr>
		     <tr>
		    <td nowrap="nowrap" class="info_title_01">
				寄达城市</td>
		    <td nowrap="nowrap" class="info_content_00">
		    <select name="cityarrive" onchange="city()">
		    <option value="">请选择 </option>
		    	<c:forEach items="${list2}" var="list">
		    		<option value="${list.ARRIVE_CITY }">${list.ARRIVE_CITY } </option>
		    	</c:forEach>
		    	</select>
		    </td>
		      <td nowrap="nowrap" class="info_title_01">
				收件单位</td>
			  <td nowrap="nowrap" class="info_content_00">
			  <input type="text" name="postAddress" style="width:150px"></td>
			  <td nowrap="nowrap" class="info_title_01">
				快件种类</td>
			  <td nowrap="nowrap" class="info_content_00">
			  	<div id="expressTypeDiv" name="expressTypeDiv">
<!--			  <ait:codeClass name="expressType" codeClass="ExpressType"/>
			  </div></td>
		      <td nowrap="nowrap" class="info_title_01">
				邮件号</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text" name="postNumber" style="width:150px" title="请正确输入邮件号"></td>
		           </tr>
		    <tr>
		      <td nowrap="nowrap" class="info_title_01">
				邮件内容 </td>
				<td nowrap="nowrap" class="info_content_00"><input type="text" name="postDescription" style="width:150px"></td>
		       <td nowrap="nowrap" class="info_title_01">
				邮件原因 </td>
				<td nowrap="nowrap" class="info_content_00"><input type="text" name="CAUSE" style="width:150px"></td>
		      <td nowrap="nowrap" class="info_title_01">
				收件人</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" name="sendToPerson" style="width:150px"></td>

			     </tr>	-->
					<tr>


					</tr>

				</table>
				<br>
				<br>
				<table id='operateTable' width="100%" border="1" cellspacing="0"
					   cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
					<tr>
						<td align="left" class="title1" colspan="18">快件信息</td>
					</tr>
					<tr align="center" bgcolor="#F5F5F5">
						<td nowrap="nowrap" class="info_title_01">快递区分</td>
						<td nowrap="nowrap" class="info_title_01">收件人姓名</td>
						<td nowrap="nowrap" class="info_title_01">收件人手机号</td>
						<td nowrap="nowrap" class="info_title_01">寄达城市</td>
						<td nowrap="nowrap" class="info_title_01">快件种类</td>
						<td nowrap="nowrap" class="info_title_01">快件说明</td>
						<td nowrap="nowrap" class="info_title_01">收件单位</td>
						<!--
                        <td nowrap="nowrap" class="info_title_01">邮件原因</td>
                        <td nowrap="nowrap" class="info_title_01">备注</td>
                         -->
						<td nowrap="nowrap" class="info_title_01">操作</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="info_content_01">
                            <ait:codeClass name="expressType_0" codeClass="ExpressType"/>
						</td>
						<td nowrap="nowrap" class="info_content_01">
                            <input type="text" name="sendToPerson_0" style="width:100px">
						</td>
						<td nowrap="nowrap" class="info_content_01">
							<input type="text" name="mobileA_0" style="width:100px">
						</td>
						<td nowrap="nowrap" class="info_content_01">
							省<input type="text" name="cityArriveS_0" style="width:80px">
							市<input type="text" name="cityArrive_0" style="width:80px">
							区<input type="text" name="cityArriveQ_0" style="width:80px">
							详细地址<input type="text" name="cityArriveD_0" style="width:150px">
                            邮编<input type="text" name="postCodeA_0" style="width:80px">
						</td>
						<td nowrap="nowrap" class="info_content_01">
							<ait:codeClass name="expType_0" codeClass="EXP_TYPE" all="all"/>
						</td>
						<td nowrap="nowrap" class="info_content_01">
							<input type="text" name="postDescription_0" style="width:150px">
						</td>
						<td nowrap="nowrap" class="info_content_01">
							<input type="text" name="postAddress_0" style="width:150px">
						</td>
						<!--
                        <td nowrap="nowrap" class="info_content_00">
                            <input type="text" name="cause_0" style="width:150px">
                        </td>
                        <td nowrap="nowrap" class="info_content_00" >
                            <input type="text" name="sendToPerson_0" style="width:150px">
                        </td>
                        <td nowrap="nowrap" class="info_content_01">
                            <textarea name="Note_0" style=" height: 25px;width:150px " type="_moz"
                            onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
                        </td>
                         -->
						<td nowrap="nowrap" class="info_content_01">
							<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();">
							<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();">
						</td>
					</tr>

				</table>

				<table width="100%" border="0" cellspacing="0" cellpadding="10">
					<c:forEach var="i" begin="1" end="9">
						<tr>
							<td class="info_content_01">&nbsp;</td>
						</tr>
					</c:forEach>
				</table>
			</td>

			<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
			<td width="11"></td>
		</tr>
		<tr>
			<td width="15"></td><br>
			<td width="11" height="5" align="LEFT" valign="TOP"><img
					src="../images/tablbk01_r14_c1.gif"></td>
			<td bgcolor="#569DD1" height="5"></td>
			<td width="10" height="5" align="LEFT" valign="TOP"><img
					src="../images/tablbk01_r14_c26.gif"></td>
			<td width="11"></td>
		</tr>
	</table>
</form>
<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;visibility: hidden;">
</div>
</body>
<ait:xjos />
</html>
