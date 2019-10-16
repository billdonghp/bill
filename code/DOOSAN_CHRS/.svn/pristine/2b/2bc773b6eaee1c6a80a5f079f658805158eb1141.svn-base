<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArItem"%>
<%@ page import="com.ait.ar.bean.ArItemBasic"%>
<%@ page import="com.ait.ar.bean.ArItemApply"%>
<%@ page import="com.ait.ar.bean.*,java.util.*,com.ait.util.*"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArItemBean">
</jsp:useBean>
<jsp:useBean id="daob" scope="page" class="com.ait.ar.dao.ArItemBasicBean">
</jsp:useBean>
<jsp:useBean id="daoa" scope="page" class="com.ait.ar.dao.ArItemApplyBean">
</jsp:useBean>
<jsp:useBean id="arServices" scope="page" class="com.ait.ar.business.ArServices"/>
<%

ArrayList list=null;
ArItem info=null;
ArItem infot=null;
list=dao.getItemList();
ArrayList groupList=null;
ArItemBasic infog=null;
groupList=daob.getGROUP();
request.setAttribute("groupList",groupList);

int itemNo=0;
ArrayList appList=daoa.getApply();
List arShiftList = arServices.getShiftList();
request.setAttribute("appList",appList);
request.setAttribute("arShiftList",arShiftList);

ArItemApply ainfo=null;
/*从session中取出 Aritem对象 或者从request中取出itemNo*/
if(session.getAttribute("aritem") != null){
    infot = (ArItem)session.getAttribute("aritem");
}
else{
  itemNo=Integer.parseInt(request.getParameter("itemNo_"));
  infot=dao.getArItemInfo(itemNo);
}
request.setAttribute("infot",infot);
%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">


<script language="javascript"><!-- 
function windowshow(id)
{
	var no=document.form1.itemNo.value;
	if(id==1){
	window.open("window_basic.jsp?no="+no+"&type=1","","width=500,height=300");
	}
	else if(id==2)
	{
	window.open("window_basic.jsp?no="+no+"&type=2","","width=500,height=300");
	}
	else if(id==3){
	window.open("aritem_temp.jsp","","width=900,height=800");
	}
}
function Save()
{

	  if(isNaN(document.form1.unit_value.value))
	  {//"最小单位必须为数值"
	  	alert('<ait:message  messageID="alert.att.minimum_unit_numeric" module="ar"/>');
		return;
	  }
	  
	   if(document.form1.min_value.value!="") {
	    if(isNaN(document.form1.min_value.value)) {
	    //"最小值必须为数值"
		  	alert('<ait:message  messageID="alert.att.minimum_value_numeric" module="ar"/>');
			return;
	  	}
	  }
	  
	  if(document.form1.max_value.value!="")
	  {
	    if(isNaN(document.form1.max_value.value))
		  {//"最大值必须为数值"
		  	alert('<ait:message  messageID="alert.att.maximum_value_numeric" module="ar"/>');
			return;
	  	}
	 
	  }

	  if(isNaN(document.form1.min_value.value))
	  {//"最小值必须为数值"
		  	alert('<ait:message  messageID="alert.att.minimum_value_numeric" module="ar"/>');
		return;
	  }
	  
	  if(isNaN(document.form1.max_value.value))
	  {//"最大值必须为数值"
		  	alert('<ait:message  messageID="alert.att.maximum_value_numeric" module="ar"/>');
		return;
	  }
	  
	if(document.form1.card_to_offset.value=="")
	  {//请输入打卡开始偏移
	    alert('<ait:message  messageID="alert.att.offset_punch_in" module="ar"/>');
	    return;
	  }
	  if(isNaN(document.form1.card_to_offset.value))
	  {//打卡开始偏移必须为数值
	  	alert('<ait:message  messageID="alert.att.offset_numeric_start" module="ar"/>');
		return;
	  }
	  
	  if(document.form1.card_from_offset.value=="")
	  {//"请输入打卡结束偏移"
	    alert('<ait:message  messageID="alert.att.offset_punch_out" module="ar"/>');
	    return;
	  }
	  if(isNaN(document.form1.card_from_offset.value))
	  {//"打卡结束偏移必须为数值"
	  	alert('<ait:message  messageID="alert.att.offset_numeric_end" module="ar"/>');
		return;
	  }
	  
	  
	  if(document.form1.apply_fullday_value.value=="")
	  {//"请输入申请满一日数值"
	    alert('<ait:message  messageID="alert.att.setting.parameter.maximum_hour_day" module="ar"/>');
	    return;
	  }
	  if(isNaN(document.form1.apply_fullday_value.value))
	  {//"申请满一日数值必须为数值"
	  	alert('<ait:message  messageID="alert.att.setting.parameter.vlue_number" module="ar"/>');
		return;
	  }

	  var check="0"
	  for(var i=0;i<form1.elements.length;i++){
					if(form1.elements[i].type=="checkbox"){
						if(form1.elements[i].checked){
							check="1";
							break;
							
						}				
					}
	}
	if(check=="0"){
		//"请选择有效日期类型!"
		alert('<ait:message  messageID="alert.att.select_valid_time" module="ar"/>');
		return;
	}
	var sdate_type="";
	for(var i=0;i<form1.elements.length;i++){
					if(form1.elements[i].type=="checkbox"){
						if(form1.elements[i].checked){
							 sdate_type=sdate_type+form1.elements[i].value+",";
						}				
					}
	}
	var last = sdate_type.lastIndexOf(",");

	document.form1.date_type.value = sdate_type.substring(0,last);
	document.form1.action="/ar/aritem_param_t.jsp?operation=ADD";
	document.form1.submit();
}

function reloadgroupNo(groupList){
	var form =document.form1;
	if (groupList.length>0)
	{
		form.groupNo.length=1;
		for (var i=0;i<groupList.length;i++ )
		{
		form.groupNo.options[form.groupNo.length] = new Option(groupList[i]["text"],groupList[i]["value"]);
		}
	}
}
--></script>
</head>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
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
		
		<!-- display content -->
		<br>
		<form name="form1" method="post" >
		<input type="hidden" name="date_type" vlaue="">
		<input type="hidden" name="menu_code" value="<%=request.getParameter("menu_code")%>">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 项目参数-->
					<ait:message  messageID="display.att.setting.parameter.parameter" module="ar"/></td>
			</tr>
		</table>
		<table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		    <td width="20%" class="info_title_01"><!-- 考勤项目-->
					<ait:message  messageID="display.att.setting.parameter.attendance_items" module="ar"/></td>
		    <td width="35%" class="info_content_00">
		    	<ait:content enContent="${infot.itemEnName}" zhContent="${infot.itemName}" koContent="${infot.itemKorName}"/>
		  <!-- --> <%//infot.getItemName()%></td>
		    <td class="info_title_01">员工区分类型</td>
		    <td class="info_content_00"><ait:empDiff name="statTypeCode" cpnyDiff="<%=admin.getCpnyId()%>"/></td>
		  </tr>
		  <tr>
		    <td width="20%" class="info_title_01">工资类别</td>
		    <td width="35%" class="info_content_00"><ait:codeClass codeClass="WagesType" name="wagesTypeCode" /></td>
		    <td class="info_title_01">班次名称</td>
		    <td class="info_content_00"><select name="shiftNo">
		    	<option value="constant"><!-- 默认-->
					<ait:message  messageID="display.mutual.default"/></option>
		    	<c:forEach var="arShift" items="${arShiftList}" varStatus="i">
		    		<option value="${arShift.shift_no}">${arShift.shift_Name}</option>
		    	</c:forEach>
		    	</select>
		    </td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 单位-->
					<ait:message  messageID="display.att.setting.parameter.edit.unit" module="ar"/></td>
		    <td class="info_content_00">
		    <ait:codeClass codeClass="ItemUnit" name="unit" remove="TIME"/>
		    <!-- <select name="unit">
		      <option value="DAY"> 天</option>
		      <option value="HOUR" selected>小时</option>
		      <option value="MINUTE">分钟</option>		     
		    </select> -->
		    </td>
		    <td class="info_title_01"><!-- 最小单位-->
					<ait:message  messageID="display.att.setting.parameter.edit.minimum_unit" module="ar"/></td>
		    <td class="info_content_00"><input name="unit_value" type="text" id="unit_value" value="0.5" size="5" maxlength="5"></td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 最小值-->
					<ait:message  messageID="display.att.setting.parameter.edit.minimum_value" module="ar"/></td>
		    <td class="info_content_00"><input name="min_value" type="text" id="min_value" size="5" maxlength="5" ></td>
		    <td class="info_title_01"><!-- 最大值-->
					<ait:message  messageID="display.att.setting.parameter.edit.maximal_value" module="ar"/></td>
		    <td class="info_content_00"><input name="max_value" type="text" id="max_value" size="5" maxlength="5"></td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 替代项目-->
					<ait:message  messageID="display.att.setting.parameter.edit.substituted_item" module="ar"/></td>
		    <td class="info_content_00"><input name="replace_item" type="text" id="replace_item" size="5" maxlength="5">
		    		<img src="../images/jj.gif" width="20" height="22" align="top" style="cursor:pointer " onClick="windowshow(1)">
		    	</td>
		    <td class="info_title_01"><!-- 依赖项目-->
					<ait:message  messageID="display.att.setting.parameter.edit.depend_on" module="ar"/>
		    </td>
		    <td class="info_content_00"><input name="depend_item" type="text" id="depend_item" size="5" maxlength="5">
		    	<img src="../images/jj.gif" width="20" height="22" align="top" style="cursor:pointer " onClick="windowshow(2)">
		    </td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 有效日期类型-->
					<ait:message  messageID="display.att.setting.parameter.time_range" module="ar"/></td>
		    <td class="info_content_00">
		    <input type="checkbox" name="checkbox" value="1">
		      <!-- 平日-->
					<ait:message  messageID="display.mutual.workday"/>
		        <input type="checkbox" name="checkbox" value="2">
		       <!-- 周末-->
					<ait:message  messageID="display.mutual.weekend"/>
		        <input type="checkbox" name="checkbox" value="3">
		      <!-- 节假日-->
					<ait:message  messageID="display.mutual.holiday"/></td>
		    <td width="20%" class="info_title_01"><!-- 组名称-->
					<ait:message  messageID="display.att.setting.parameter.team" module="ar"/></td>
		    <td width="35%" class="info_content_00">
		    <select name="ar_group_No" id="ar_group_No">
		      <option value="constant"><!-- 默认-->
					<ait:message  messageID="display.mutual.default"/></option>
		      <c:choose>
		      	<c:when test="${groupList != null}">
		      		<c:forEach items="${groupList}" var="groupList">
		      			<option value="${groupList.groupNo}">
		      			<ait:content enContent="${groupList.enGroupName}" zhContent="${groupList.groupName}" koContent="${groupList.korGroupName}"/>
		      			</option>
		      		</c:forEach>
		      	</c:when>
		      </c:choose>
		    </select>
			</td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 是否参考刷卡-->
					<ait:message  messageID="display.att.setting.parameter.refer_to_punchcard" module="ar"/></td>
		    <td class="info_content_00"><select name="card_flag" id="card_flag">
		      <option value="1" selected><!-- 是-->
					<ait:message  messageID="display.mutual.yes"/></option>
		      <option value="0"><!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></option>
		    </select>
		    </td>
		    <td class="info_title_01"><!-- 打卡开始标志-->
					<ait:message  messageID="display.att.setting.parameter.edit.start_symbol" module="ar"/></td>
		    <td class="info_content_00"> 
		    <select name="card_from_flag" id="card_from_flag">
		      <option value="1" selected><!-- 是-->
					<ait:message  messageID="display.mutual.yes"/></option>
		      <option value="0"><!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></option>
		    </select>
		    </td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 打卡开始偏移-->
					<ait:message  messageID="display.att.setting.parameter.edit.start_offset" module="ar"/></td>
		    <td class="info_content_00">
		    <input name="card_from_offset" type="text" id="card_from_offset" size="5" maxlength="5" value="0">
		    </td>
		    <td class="info_title_01"><!-- 打卡开始偏移方向-->
					<ait:message  messageID="display.att.setting.parameter.edit.offset_type_start" module="ar"/></td>
		    <td class="info_content_00">      
		    <select name="card_from_relation" id="card_from_relation">
		      <option value="＞"><!-- 大于-->
					<ait:message  messageID="display.att.setting.parameter.edit.greater_than" module="ar"/></option>
		      <option value="＜"><!-- 小于-->
					<ait:message  messageID="display.att.setting.parameter.edit.lesser_than" module="ar"/></option>
		      <option value="=" selected><!-- 等于-->
					<ait:message  messageID="display.att.setting.parameter.edit.equal_to" module="ar"/></option>
		    </select></td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 打卡结束标志-->
					<ait:message  messageID="display.att.setting.parameter.edit.end_symbol" module="ar"/></td>
		    <td class="info_content_00">
		    <select name="card_to_flag" id="card_to_flag">
		      <option value="1" selected><!-- 是-->
					<ait:message  messageID="display.mutual.yes"/></option>
		      <option value="0"><!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></option>
		    </select>
		    </td>
		    <td class="info_title_01"><!-- 打卡结束偏移-->
					<ait:message  messageID="display.att.setting.parameter.edit.end_offset" module="ar"/></td>
		    <td class="info_content_00"><input name="card_to_offset" type="text" id="card_to_offset" size="5" maxlength="5" value="0"></td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 打卡结束偏移方向-->
					<ait:message  messageID="display.att.setting.parameter.edit.offset_type_end" module="ar"/></td>
		    <td class="info_content_00">      
		    <select name="card_to_relation" id="card_to_relation">
		      <option value="＞"><!-- 大于-->
					<ait:message  messageID="display.att.setting.parameter.edit.greater_than" module="ar"/></option>
		      <option value="＜"><!-- 小于-->
					<ait:message  messageID="display.att.setting.parameter.edit.lesser_than" module="ar"/></option>
		      <option value="=" selected><!-- 等于-->
					<ait:message  messageID="display.att.setting.parameter.edit.equal_to" module="ar"/></option>
		      </select></td>
		    <td class="info_title_01"><!-- 参考申请标志-->
					<ait:message  messageID="display.att.setting.parameter.edit.refer_to_request" module="ar"/></td>
		    <td class="info_content_00">
		    <select name="apply_flag" id="apply_flag">
		      <option value="1" selected><!-- 是-->
					<ait:message  messageID="display.mutual.yes"/></option>
		      <option value="0"><!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></option>
		    </select>
		    </td>
		  </tr>
		  <tr>
		    <td class="info_title_01">勤态类型</td>
		    <td class="info_content_00">      
			    <select name="apply_type">
				    <c:forEach items="${appList}" var="appList">
				    	<option value="${appList.code_id}">
				    	<ait:content enContent="${appList.code_en_name}" zhContent="${appList.code_name}" koContent="${appList.code_kor_name}"/>
				    	</option>
				    </c:forEach>		      
			    </select>
		    </td>
		    <td class="info_title_01"><!-- 申请满一日数值-->
					<ait:message  messageID="display.att.setting.parameter.edit.ceiling_value_day" module="ar"/></td>
		    <td class="info_content_00"><input name="apply_fullday_value" type="text" id="apply_fullday_value" value="8" size="5" maxlength="5"></td>
		  </tr>
		  <tr>
		    <td class="info_title_01"><!-- 申请与打卡优先级-->
					<ait:message  messageID="display.att.setting.parameter.edit.priority" module="ar"/></td>
		    <td class="info_content_00"><select name="apply_card_proior" id="apply_card_proior">
		      <option value="0" selected><!--打卡-->
					<ait:message  messageID="display.att.setting.parameter.edit.punch_card" module="ar"/></option>
		      <option value="1"><!--申请-->
					<ait:message  messageID="display.att.setting.parameter.edit.request" module="ar"/></option>
			  <option value="2"><!--申请,打卡,取交集-->
					<ait:message  messageID="display.att.setting.parameter.edit.intersection" module="ar"/></option>
		            </select></td>
		    <td class="info_title_01"> <!-- 是否活跃-->
					<ait:message  messageID="display.mutual.active"/> </td>
		    <td class="info_content_00">
		    <select name="activity" id="activity">
		      <option value="1" selected><!-- 是-->
					<ait:message  messageID="display.mutual.yes"/></option>
		      <option value="0"><!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></option>
		    </select>
		    </td>
		  </tr>
		  
		</table>
		<input type="hidden" name="itemNo" value="<%=itemNo%>"/>
		</form>

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

</body>

</html>
