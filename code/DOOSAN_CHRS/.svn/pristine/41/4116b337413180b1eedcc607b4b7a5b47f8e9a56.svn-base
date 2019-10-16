<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.ga.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="getWasteAffirm" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="wasteBean" class="com.ait.ga.bean.WasteBean"/>
<%@page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*" %>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" /> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>废品单价决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

</SCRIPT>

<body>
<%GaAffirm  ga = new GaAffirm(); %>
<form name="form1" method="post" action="">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/gatoolbar_none.jsp"%>
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
					废品单价申请决裁
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
				申请者</td>
			<td nowrap="nowrap" class="info_title_01">
				申请者部门</td>
			<td nowrap="nowrap" class="info_title_01">
				废品名称</td>
			<td nowrap="nowrap" class="info_title_01">
				当前单价</td>
			<td nowrap="nowrap" class="info_title_01">
				申请单价</td>
			<td nowrap="nowrap" class="info_title_01">
				生效日期</td>
			<td nowrap="nowrap" class="info_title_01">
				申请日期</td>
			<td nowrap="nowrap" class="info_title_01">
				价格变动原因</td>
		    <td nowrap="nowrap" class="info_title_01">
				操作</td>
		    </tr>
		    
		    <%for(int i=0;i<getWasteAffirm.size();i++){	
		    	wasteBean= (WasteBean)getWasteAffirm.get(i);
		    %>
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" ><%=wasteBean.getUSER_ID()  %></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" ><%=wasteBean.getUSER_NAME()  %></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" ><%=wasteBean.getUSER_DEPT()  %></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" ><%=wasteBean.getWASTE_NAME()  %></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" ><%=wasteBean.getWASTE_ATPRESENTUNITPRICE()  %></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" ><%=wasteBean.getWASTE_APPLYUNITPRICE()  %></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" ><%=wasteBean.getWASTE_UNITPRICEOKDATE()  %></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" ><%=wasteBean.getWASTE_APPLYDATE()  %></td>
		      
		      <td nowrap="nowrap"  align="center" class="info_content_01">
		      	<textarea name="Remarks" style="width:200px " readonly="readonly"><%=wasteBean.getREMARK() %></textarea>
		      </td>
		      <td nowrap="nowrap" align="center" class="info_content_00">
		      <%
		      List list =ga.getWasteAffirmorInfo(wasteBean.getWASTEUNITPRICE_ID());
              %>
		      <!-- 下面代表决裁者 -->
		      <%if(!list.isEmpty()){%>
		      <table> 
		      <%for(int affirm=0;affirm<list.size();affirm++){
			      gaAffirmList=(GaAffirmList)list.get(affirm);
			      int temp=gaAffirmList.getAffirmFlag();
		      %>
		      <tr>
		      <td nowrap="nowrap"><font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font></td>
		      <td nowrap="nowrap"><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font></td>
		      <td nowrap="nowrap"><%if(temp==0){ %><font color="#00CC00">未决裁</font><%} %>
		          <%if(temp==1){ %><font color="#00CC00">通过</font><%} %>
		          <%if(temp==2){ %><font color="#00CC00">已否决</font><%} %>
		      </td>
		      <td nowrap="nowrap">|</td>
		      <td nowrap="nowrap">
		     <%if((temp==0||temp==2)&& (gaAffirmList.getAffirmorId().equals(admin.getAdminID()))){%>
		     <a href="/gaControlServlet?menu_code=ga0207&operation=gaAffirm&content=approvWasteAffirm&affirmno=<%=wasteBean.getWASTEUNITPRICE_ID() %>&wasteId=<%=wasteBean.getWASTE_ID().trim() %>&wasteId=<%=wasteBean.getWASTE_NAME().trim() %>&unitPrice=<%=wasteBean.getWASTE_APPLYUNITPRICE() %>&affirmorid=<%=gaAffirmList.getAffirmorId()%>"><font color="red">通过</font></a><%}%>
		     <%if((temp==1||temp==0)&& (gaAffirmList.getAffirmorId().equals(admin.getAdminID()))){%>
		     <a href="/gaControlServlet?menu_code=ga0207&operation=gaAffirm&content=rejectWasteAffirm&affirmno=<%=wasteBean.getWASTEUNITPRICE_ID() %>&affirmorid=<%=gaAffirmList.getAffirmorId()%>" onclick="return(confirm('确定进行否决操作吗？'))"><font color="red">否决</font></a>
		     <%}%>		    
		     </td>		      
		      </tr>		     	      
		      <%} %>		     
		      </table>
		      <!-- 下面代表无决裁者 -->
		      <%}else{ %>
		      <table align="center">
		      <tr><td><font color="red">没有决裁者</font></td></tr>		      
		      </table>
		      <% }%>
		      
		      </td>		    
		    </tr>
		    <% }%>		  
		    <tr align="center"> 
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
<table>
 <tr>  
 <td align="left">
  <%int listsize =ga.getWasteAffirmNumber(admin.getAdminID());%>
  共有<%=listsize!=10?(listsize/10)+1:1%>页<%=listsize%>条数据
 </td> 
 <td align="center">
 <%for(int pagesize=1;pagesize<=(listsize!=10?(listsize/10)+1:1);pagesize++){%>
	<a href="/gaControlServlet?menu_code=ga0207&operation=gaAffirm&content=WasteUnitPriceConfirmed&page=<%=pagesize%>">
	<font color="red"><b><%=pagesize%></b></font></a>&nbsp;
 <%}%>
  </td>
  </tr>
</table>	
</body>
</html>