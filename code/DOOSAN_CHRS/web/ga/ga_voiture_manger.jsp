<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.utils.FormUtil,com.ait.ga.bean.*,com.ait.ga.cmd.visit.*"%>
<jsp:useBean id="allVoitureList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="voitureBean" class="com.ait.ga.bean.VoitureBean"/>
<jsp:useBean id="voitureManger" class="com.ait.ga.cmd.visit.VoitureManger"/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>车辆信息</title>
</head>
<SCRIPT type="text/javascript">


var s;///车辆编号
var mytempvar;///所选择的编号
function Add() {

	document.form1.action="/ga/ga_add_voiture.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {
if(s==""||s==null){
alert("请选择项目");
return;
}
	document.form1.action="/ga/ga_edit_voiture.jsp?menu_code=${param.menu_code}&sign="+s;
	document.form1.submit();
}
function Save() {
if(s==""||s==null){
alert("请选择项目");
return;
}
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=voitureManger&content=updateVoitureSate&sign="+s+"&mytempvar="+mytempvar;
	document.form1.submit();
}

function band(backColor,textColor,voitureid,temp)
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
		 s=voitureid;
		 mytempvar=temp;
	} 

function getAllVoiture(){

  document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=voitureManger&content=voitureManger&getallvoiture=getAllStatsVoiture";
  document.form1.submit();

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
			<%@ include file="inc/gatoolbar1.jsp"%>
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
				<ait:message messageID="display.ga.view" module="ga"></ait:message>
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
				编号</td>
		      <td nowrap="nowrap" class="info_title_01">
				车辆名称</td>
			 <td nowrap="nowrap" class="info_title_01">
				车种（型号）</td>
		      <td nowrap="nowrap" class="info_title_01">
				出厂日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				车牌号码</td>
			  <td nowrap="nowrap" class="info_title_01">
				座位数</td>
			  <td nowrap="nowrap" class="info_title_01">
				里程数</td>
			  <td nowrap="nowrap" class="info_title_01">
				车辆状态</td>		
		      
		    </tr>
		    <% for(int i=0;i<allVoitureList.size();i++){ 
		      voitureBean=(VoitureBean)allVoitureList.get(i);
		      int count =allVoitureList.size();
		    %>
		    <tr onClick="band('#E7F0EF','black',<%=voitureBean.getVOITURE_ID()%>,<%=i%>)" height="30" >   
		      <td align="center">&nbsp;<%=i+1%></td>     
		      <td align="center">&nbsp;<%=voitureBean.getVoiture_Brand() %></td>
		      <td align="center">&nbsp;<%=voitureBean.getVoiture_Model() %></td>
		      <td align="center">&nbsp;<%=voitureBean.getPurchase_Date() %></td>
		      <td align="center">&nbsp;<%=voitureBean.getVoiture_Number()!=null?voitureBean.getVoiture_Number():"&nbsp;" %></td>
		      <td align="center">&nbsp;<%=voitureBean.getSeats()!=null?voitureBean.getSeats():"&nbsp;" %></td>
		      <td align="center">&nbsp;<%=voitureBean.getGO_KILL()!=null?voitureBean.getGO_KILL():"&nbsp;" %></td>
		      <td align="center" style="color: #666666;">&nbsp;<%=FormUtil.gaVoitureState("VOITURE_STATE"+i,voitureBean.getVOITURE_STATE(),FormUtil.getSysCode("voitureState"))%>		     
		      </td>		     
		    </tr>
		   <%} %>	
		   <%for(int y=allVoitureList.size();y<10;y++){ %>
		   	<tr height="30" >   
		      <td align="center">&nbsp;</td>     
		      <td align="center">&nbsp;</td>
		      <td align="center">&nbsp;</td>
		      <td align="center">&nbsp;</td>
		      <td align="center">&nbsp;</td>
		      <td align="center">&nbsp;</td>
		      <td align="center">&nbsp;</td>
		      <td align="center" style="color: #666666;">&nbsp;		     
		      </td>		     
		    </tr>
		   <%} %>		
		    <tr align="center"> 
			 </tr>
		 </table>
		 <br />
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
