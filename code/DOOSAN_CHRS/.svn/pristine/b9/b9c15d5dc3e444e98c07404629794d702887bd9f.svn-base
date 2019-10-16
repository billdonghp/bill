<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="eatTypeSize" class="java.lang.String" scope="request" />
<jsp:useBean id="DeptID" class="java.lang.String" scope="request" />
<jsp:useBean id="affirmList"  class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList"
	scope="page" />
<%@ page
	import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date"%>
<html>
	<head>
	<!-- ga_check_application.jsp -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="/css/default.css" rel="stylesheet" type="text/css">
		<title>支票申请</title>
	</head>
<script src="../js/prototype.js"></script>
<script src="../js/cascadeSelect.js"></script>
<script language="javascript">
function DoubleComboS(){
	new DoubleCombo('CHECKBANK_TYPE', 'ACCOUNT', null, '/ajaxControlServlet?operation=checkAccountCmd&combo=true&type=CHECKBANK_TYPE', {},true,'');
}
function Save() {
        if(document.form1.JINE.value==""||document.form1.JINE.value==null){
			 alert("金额不能为空");	
			 return;	
		}else if(document.form1.CHECKBANK_TYPE.value==""||document.form1.CHECKBANK_TYPE.value==null){
		     alert("请选择银行名称");
				return;
		}else if(document.form1.ACCOUNT.value==""||document.form1.ACCOUNT.value==null){
		     alert("请选择账号");
				return;
		}else if(document.form1.CHECK_TYPE.value==""||document.form1.CHECK_TYPE.value==null){
		     alert("请选择支票类型");
				return;
		}else if(document.form1.CHECKACCOUNT.value==""||document.form1.CHECKACCOUNT.value==null||document.form1.CHECKACCOUNT.value=="0"){
		     alert("支票号不存在");
				return;
		}else if(document.form1.QIANACCOUNT.value==""||document.form1.QIANACCOUNT.value==null){
		     alert("请录入费用申请企案号");
				return;
		}else if(document.form1.PZACCOUNT.value==""||document.form1.PZACCOUNT.value==null){
		     alert("请录入费用凭证号");
				return;
		}else if(document.form1.SKALLNAME.value==""||document.form1.SKALLNAME.value==null){
		     alert("请录入收款人全称");
				return;
		}else if(document.form1.affirmor.value=="" || document.form1.affirmor.value==null){
		 alert("没有设置决裁者！请先设置决裁者");
		 return;
		}else{
			if(confirm("是否提交申请!!!")){
				document.form1.action="/gaControlServlet?operation=gaApply&content=checkApplyAdd&menu_code=${param.menu_code}";
				document.form1.submit();
			}
	  }
	
}

function GetCheckAccount(){
    document.form1.action="/gaControlServlet?menu_code=ga0121&operation=gaApply&content=getCheckAccount&menu_code=${param.menu_code}";
	document.form1.submit();
}


</SCRIPT>
	<body>
		<form name="form1" method="post" action="">
			<input type="hidden" name="admin" value="<%=admin.getAdminID()%>" />
			<input type="hidden" name="temp" value="" />
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
				<td align="left" class="title1" id = 'checkRoomInfo' colspan="10">
				支票申请<font color="red" size="2">${declaration}</font>
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
						<table width="100%" border="1" cellspacing="0" cellpadding="0"
							bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
							<tr>
								<td nowrap="nowrap" class="info_title_01">
									所属法人
								</td>
								<td nowrap="nowrap" class="info_content_00">									
		   					       <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="GetCheckAccount();"/>
								</td>
								<td nowrap="nowrap" class="info_title_01">
									申请部门
								</td>
								<td nowrap="nowrap" class="info_content_01">
									${DeptID }
								</td>
								<td nowrap="nowrap" class="info_title_01">
									申请日期
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" name="createDate" value="${startDate }"
										style="width:70px " onClick="setday(this);" readonly>
								</td>
								<td nowrap="nowrap" class="info_title_01">
									金额
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" name="JINE" size="27" required>
								</td>
    							</tr>
							<tr>
								<td nowrap="nowrap" class="info_title_01">
									银行名称
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<ait:codeClass name="CHECKBANK_TYPE" codeClass="CheckBankInfor" disabled="false" all="all" cpnyId ="${cpnyId}" selected="${CHECKBANK_TYPE}" onChange="DoubleComboS();"/>
								</td>
								<td nowrap="nowrap" class="info_title_01">
									账号
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<select name="ACCOUNT" id="ACCOUNT" onChange="GetCheckAccount();">
									<option value="${ACCOUNT}">${ACCOUNT}</option></select>
								</td>

								<td nowrap="nowrap" class="info_title_01">
									支票类型
								</td>
								<td nowrap="nowrap" class="info_content_00">
									<ait:codeClass name="CHECK_TYPE" codeClass="CheckType1" all="all" selected="${CHECK_TYPE}" onChange="GetCheckAccount();"/>
								</td>
								<td nowrap="nowrap" class="info_title_01">
									支票号
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<c:if test="${result != '0'}">${result}</c:if>
									<c:if test="${result == '0'}">无此类型支票</c:if>
									<input type="hidden" name="CHECKACCOUNT" value="${result}">
								</td>							

							</tr>
							<tr>
								<td nowrap="nowrap" class="info_title_01">
									费用申请企案号
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" name="QIANACCOUNT" size="27" required>
								</td>
								<td nowrap="nowrap" class="info_title_01">
									费用凭证号
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" name="PZACCOUNT" size="27" required>
								</td>

								<td nowrap="nowrap" class="info_title_01">
									费用凭证日期
								</td>
								<td nowrap="nowrap" class="info_content_00">
									<input type="text" name="PZDATE" value="${startDate }"
										style="width:70px " onClick="setday(this);" readonly>
								</td>
								<td nowrap="nowrap" class="info_title_01">
									收款人全称
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" name="SKALLNAME" size="27" required>
								</td>	
							</tr>

							<tr >
								<td nowrap="nowrap" class="info_title_01">
									申请人
								</td>
								<td nowrap="nowrap" class="info_content_00">
									${name}
								</td>
								<td nowrap="nowrap" class="info_title_01">
									决裁者
								</td>
								 <td nowrap="nowrap">
							      <%if(!affirmList.isEmpty()){%>
							      <input type="hidden" value="<%=affirmList.size()%>" name="affirmor">
							      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

									      <%
									      int affirmLevel=10;
									      for(int i=0;i<affirmList.size();i++){
									      gaAffirmList=(GaAffirmList)affirmList.get(i);%> 
									    <tr>
									      <td>
					
									      <font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
									      <input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId"><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>
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
							      <%}else{ %>
							      <input type="hidden" value="" name="affirmor">
							      <table>
							      <tr><td nowrap="nowrap"><font color="red">没有决裁者</font></td></tr>		      
							      </table>
							      <% }%>		      
							      </td>					
								<td nowrap="nowrap" class="info_title_01">
									摘要
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" name="NOTE" size="27">
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
<iframe id='iemp' style="position:absolute; top:100; width:570; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:570; height:210; z-index:2;visibility: hidden;">   
	</div>
	<ait:xjos />
</html>
