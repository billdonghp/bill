<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<script src="../js/prototype.js"></script>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="wasteType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="date" class="java.lang.String" scope="request" />
<jsp:useBean id="chinesename" class="java.lang.String" scope="request" />
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" /> 
<jsp:useBean id="essSysparam" class="com.ait.sysparam.EssSysparam" scope="request" />
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>废品单价申请</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">


function Save() {
	if(document.form1.wasteType.value==0){
		alert("请选择废品类别");
		return ;
	}
	if(document.form1.affirmUnitprice.value==0){
		alert("请填写废品申请单价");
		return ;
	}
	if(document.form1.Commencement.value==0){
		alert("生效日期不能为空！请填写！");
		return ;
	}
	if(document.form1.Commencement.value<document.form1.date.value){
		alert("生效日期不能早于今日!");
		return ;
	}
	if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
	 alert("没有设置决裁者！请设置");
	 return;
   	}
	document.form1.action="/gaControlServlet?operation=gaApply&content=addWasteApply&menu_code=${param.menu_code}";
	document.form1.submit();
}

function changeWasteaTypeShowUnitPrice(TypeId){
		
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=changeWasteaTypeShowUnitPrice&TypeId="+document.form1.wasteType.value;

		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){

	var unitp = $('unitp');
	unitp.innerHTML=XmlHttpRequest.responseText;
	document.form1.wasteUnitPrice.value=document.form1.UP.value;
}

</SCRIPT>

<body>

<form name="form1" method="post" action="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<input type="hidden" name="operation" value="CardApplication"/>
<input type="hidden" name="date" value="${date}"/>
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
					废品单价申请页面
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
				职号</td>
			  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
			  <td nowrap="nowrap" class="info_title_01">
				部门</td>
		      <td nowrap="nowrap" class="info_title_01">
				废品名称</td>
		      <td nowrap="nowrap" class="info_title_01">
				当前单价</td>
		      <td nowrap="nowrap" class="info_title_01">
				申请单价</td>
			  <td nowrap="nowrap" class="info_title_01">
				生效日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				价格变动原因</td>
			  <td nowrap="nowrap" class="info_title_01">
				决裁者</td>
		    </tr>			
		    <tr>
		      <td align="center" class="info_content_01" >
		      		${admin.adminID}
		      		<input type="hidden" name="adminid" value="${admin.adminID}"/>
		      </td>
		      <td align="center" class="info_content_01" >
		      		${chinesename}
		      		<input type="hidden" name="adminusername" value="${admin.username}"/>
		      </td>
		      <td align="center" class="info_content_01" >
		      		${admin.department}
		      		<input type="hidden" name="deptid" value="${admin.deptID}"/>
		      </td>
		      <td align="center" class="info_content_01" >
			      <select name="wasteType" onchange="changeWasteaTypeShowUnitPrice(this.value);">
			     		<option value="0">
							请选择类别
						</option>
		      		<c:forEach items="${wasteType}" var="view" varStatus="">
		      			<option value="${view.CODE_ID}">
		      				${view.CODE_NAME}
		      			</option>
		      		</c:forEach>
			      </select>
		      </td>
		      <td align="center" class="info_content_01" >
		      		<input type="text"id="UnitPrice_id" readonly="readonly" name="wasteUnitPrice" value="" size="10"/>
					<span id="unitp"></span>
		      </td>
		      <td align="center" class="info_content_01" >
		      		<input type="text" style="width: 50" name="affirmUnitprice" value=""/>
		      </td>
		      <td align="center" class="info_content_01" >
		      		<input type="text" style="width:95px" name="Commencement" value="" onClick="setday(this);" readonly="readonly"/>
		      </td>
		      <td align="center" class="info_content_01" >
		      	<textarea name="remark" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
		      </td>
		      <td nowrap="nowrap" align="center" >
			      <% GaAffirm  ga = new GaAffirm();
			      List list =ga.getAffirmor(admin.getAdminID(),"WASTE_UNITPRICE_APPLY", essSysparam.isContainsOwner());%>
			      <%if(!list.isEmpty()){%>
			      <input type="hidden" value="<%=list.size()%>" name="affirmor">
			      <table> 
			      <%for(int i=0;i<list.size();i++){
			      gaAffirmList=(GaAffirmList)list.get(i);%> 
			     
			      <tr><td><font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font></td><td><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font></td></tr>		      
			      <%} %>
			      </table>
			      <%}else{ %>
			      <input type="hidden" value="" name="affirmor">
			      <table>
			      <tr><td><font color="red">没有决裁者</font></td></tr>		      
			      </table>
			      <% }%>		      
			    </td>	
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
</body>
</html>
