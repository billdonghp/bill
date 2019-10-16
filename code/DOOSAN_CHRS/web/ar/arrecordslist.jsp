<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArRecords"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.sy.bean.AdminBean" %>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArRecordsBean" />
<%
	ArrayList list=null;
	ArRecords info=null;
	/*传递登陆者ID*/
	AdminBean admin1 = (AdminBean)session.getAttribute("admin");
	Logger.getLogger(admin1.toString());
	dao.setLoginID(admin1.getAdminID());
	String rdTime = request.getParameter("rdtime");
	String rtTime = request.getParameter("rttime");
	String empId = StringUtil.toCN(request.getParameter("emp"));
	String selectedState = request.getParameter("sk");
	if(selectedState == null){
		selectedState = "";
	}
	if(request.getParameter("action")!=null){
	    list=dao.getRecordsSearch(rdTime, rtTime, empId, request.getParameter("sk"), admin1.getAdminID());
	}else{
		Date date=new Date();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		list=dao.getRecordsDay(dateStr,dateStr);
	}
	request.setAttribute("list", list);
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/sortabletable.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/sortabletable.js"></script>
<script language="javascript" src="../js/meizzDate.js"></script>
<script language="javascript">
	function search(){
		var loc="arrecordslist.jsp?menu_code=<%=request.getParameter("menu_code")%>&action=search&";
		var flag=0;
		if(document.form1.rdtime.value=="" && document.form1.rttime.value==""){
	          alert("请输入查询日期");
	          return;
	  }
		if(document.form1.rdtime.value!=""){
			var rdtime=document.form1.rdtime.value;
			loc+="rdtime_="+rdtime+"&";
	    flag=1;
		}
		
		if(document.form1.emp.value!=""){
			var emp=document.form1.emp.value;
			loc+="emp_="+emp+"&";
	                flag=1;
		}
		
		if(document.form1.sk.value!=""){
			var sk=document.form1.sk.value;
			loc+="sk_="+sk;
	                flag=1;
		}
		
		if(loc.substring(loc.length-1)=="?"||loc.substring(loc.length-1)=="&"){
			loc=loc.substring(0,loc.length-1);
		}
    if(flag==0){
      loc="arrecordslist.jsp?menu_code=<%=request.getParameter("menu_code")%>";
    }
		document.form1.action=loc;
		document.form1.submit();
	}
	
	function Add(){
		document.form1.action="arrecords_a.jsp?menu_code=<%=request.getParameter("menu_code")%>";
		document.form1.submit();
	}
	
	function band(backColor, textColor, empId, rTime, insertBy){
		var t;
		if(typeof(preEl)!='undefined') {
			preEl.bgColor=orgBColor;
			try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
		}
		var el = event.srcElement;
		el = el.parentElement;
		orgBColor = el.bgColor;
		orgTColor = el.style.color;
		el.bgColor=backColor;
		try{
			ChangeTextColor(el,textColor);
		}catch(e){
			;
		}
		preEl = el;
		document.form1.m_empId.value=empId;
		document.form1.rTime.value=rTime;
		document.form1.m_insertBy.value=insertBy;
	}
	
	function ChangeTextColor(a_obj,a_color){
		for (i=0;i<a_obj.cells.length;i++){
			a_obj.cells(i).style.color=a_color;
		}
	}
	
	function Update(){
		if(document.form1.m_empId.value==0){
			alert("请选择修改项目");
			return;
		}
		if(document.form1.m_insertBy.value=='M'){
			alert("只能修改手工录入的项目");
			return;
		}
		document.form1.action="arrecords_m.jsp?menu_code=<%=request.getParameter("menu_code")%>";
		document.form1.submit();
	}
</script>
</head>
<body>
 
<form name="form1" method="post" action="">
  <input type="hidden" name="m_empId" value="0" /><!-- 被修改的员工考勤信息工号 -->
  <input type="hidden" name="rTime" value="0" /><!-- 被修改的员工考勤信息时间 -->
  <input type="hidden" name="m_insertBy" value="0" /><!-- 机器录入还是手工录入 -->
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			 <%@ include file="../inc/common_toolbar_all.jsp"%>
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
			<td class="title1">查询条件</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       	<tr>
		      <td width="10%" class="info_title_01">开始日期</td>
		      <td width="12%" class="info_content_00"><input type="text" name="rdtime" value="<%=rdTime == null ? "" : rdTime %>" onClick="setday(this);" readonly="true" class="content" style="width:90px " ></td>
		      <td width="10%" class="info_title_01">结束日期</td>
		      <td width="12%" class="info_content_00"><input type="text" name="rttime" value="<%=rtTime == null ? "" : rtTime %>"  onClick="setday(this);"  class="content" style="width:90px " ></td>
		      <td width="10%" class="info_title_01">人员</td>
		      <td width="12%" class="info_content_00"><input name="emp" type="text" size="10" value="<%=empId == null ? "" : empId %>" maxlength="10"></td>
		      <td width="10%" class="info_title_01">刷卡情况</td>
		      <td width="12%" class="info_content_00">
		       <select name="sk">
		          <option value="">[请选择]</option>
		          <option value="IN" <%=selectedState.equalsIgnoreCase("IN") ? "selected" : ""%>> 进门卡</option>
		          <option value="OUT" <%=selectedState.equalsIgnoreCase("OUT") ? "selected" : ""%>> 出门卡</option>
		        </select>
		      </td>
		      <td width="12%" class="info_content_00"><ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:search();" style="cursor:hand" /></td>
		    </tr>
			</table>
	      </td>
		</tr>
		</table>
		  
		<!-- display 3 level menu -->
		 <%@ include file="../inc/common_toolbar.jsp"%>
		 
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">刷卡记录</td>
			</tr>
		</table>
		  <table class="sort-table" id="t1" name="t1" width="100%" border="1" cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<thead>
			 <tr bgcolor="#F5F5F5">
		      <td width="12%" class="info_title_01">部门名称</td>
		      <td width="9%" class="info_title_01">员工号</td>
		      <td width="9%" class="info_title_01">员工姓名</td>
		      <td width="11%" class="info_title_01">进门时间</td>
		      <td width="11%" class="info_title_01">出门时间</td>
		      <td width="9%" class="info_title_01">记录来源</td>
		      <td width="11%" class="info_title_01">汇总时有效</td>
		      <td width="11%" class="info_title_01">原记录</td>
		      <td width="13%" class="info_title_01">备注</td>
		    </tr>
			</thead>
			<tbody>
		    <%if(list != null && list.size()>0){
		      for(int i=0;i<list.size();i++){
		        info=(ArRecords)list.get(i);
		    %>
		    <tr onClick="band('#99CCFF','black','<%=info.getEmpid()%>','<%=info.getEnterTime() + info.getOutTime() %>','<%=info.getInsert_by() %>')">
		      <td class="info_content_01"><%=info.getDeptName() %></td>
		     	<td class="info_content_01"><%=info.getEmpid() %></td>
		     	<td class="info_content_01"><%=info.getEmpNameToUnicode() %></td>
		      <td class="info_content_01"><%=info.getEnterTime()%>&nbsp;</td>
		      <td class="info_content_01"><%=info.getOutTime() %>&nbsp;</td>
		      <td class="info_content_01"><%=info.getInsert_by_js() %></td>
		      <td class="info_content_01"><%=info.getActive().equalsIgnoreCase("Y")?"有效":"无效" %></td>
		      <td class="info_content_01"><%=info.getD_time()%>&nbsp;</td>
		      <td class="info_content_01"><%=info.getRemark() %>&nbsp;</td>
		    </tr>
		<%
				}
		    }
		    %>
			</tbody>
		  </table>
		  <table width="100%" border="0" cellspacing="0" cellpadding="10"
			<c:if test="${fn:length(list) < 7}">
				<c:forEach var="i" begin="1" end="${7-fn:length(list)}"
					step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
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

</form>
<script type="text/javascript">

var st1 = new SortableTable(document.getElementById("t1"),
	["None", "String", "None", "String", "String", "None", "None"]);

</script>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
