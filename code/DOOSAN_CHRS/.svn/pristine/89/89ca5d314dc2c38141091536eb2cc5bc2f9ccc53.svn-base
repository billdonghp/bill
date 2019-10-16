<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<%@ page import="com.ait.utils.FormUtil,com.ait.ga.bean.*,com.ait.ga.cmd.visit.*,java.util.*"%>
<jsp:useBean id="voitureBean" class="com.ait.ga.bean.VoitureBean"/>
<jsp:useBean id="voitureResume" class="com.ait.ga.bean.VoitureBean"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<script language="javascript">

</script>
</head>
<body>

<%VoitureManger vm = new VoitureManger();
List voitureResumelist =vm.getAllVoitureResume(); %>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/toolbar_onlyback.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr> 
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display content -->
		<br>
		<form name="form1" method="post" action="">
		<input name="temp" type="hidden" value="">
		<%
		String sign =(request.getParameter("sign")!=null&&!request.getParameter("sign").equals("undefined"))?request.getParameter("sign"):"";		
		if(!sign.equals("") && sign!=null){ 		
		voitureBean=vm.getAnVoiture(sign);%>
		<input name="sign" type="hidden" value="<%=sign%>">
		
		<%} %>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆信息</td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	   

		  <tr>
		     <td class="info_title_01">车种（名称）</td>
			 <td class="info_content_01"><%=voitureBean.getVoiture_Brand()!=null?voitureBean.getVoiture_Brand():"&nbsp;" %></td>
		     <td class="info_title_01">	车种（型号）</td>
			 <td class="info_content_01"><%=voitureBean.getVoiture_Model()!=null?voitureBean.getVoiture_Model():"&nbsp;" %></td>
			 <td class="info_title_01"> 车种（牌号）</td>
			 <td class="info_content_01"><%=voitureBean.getVoiture_Number()!=null?voitureBean.getVoiture_Number():"&nbsp;"%></td>		      
		    </tr>
		   <tr>
		      <td class="info_title_01">购买日期</td>
			 <td class="info_content_01"><%=voitureBean.getPurchase_Date()!=null?voitureBean.getPurchase_Date():"&nbsp;" %>&nbsp;</td>
		     <td class="info_title_01">保修期限</td>
			 <td class="info_content_01" colspan="3"><%=voitureBean.getAS_Duration()!=null?voitureBean.getAS_Duration():"&nbsp;" %>年</td>
		  </tr>	   
		  </table>	
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆履历信息</td>
				</tr>
		 </table>
     <table width="100%"  border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" id = 'operateTable'>
		    <tr align="center" bgcolor="#F5F5F5">
		      <td class="info_title_01">
				发生日期</td>
		      <td class="info_title_01">
				项目</td>
			 <td class="info_title_01">
				单位</td>
			 <td class="info_title_01">
				数量</td>
		      <td width="25%" class="info_title_01">
				备注(250个汉字以内)</td>
			  <td class="info_title_01">
				输入者</td> 				      
		    </tr>
		    <%VoitureManger voitureManger =new VoitureManger();
		     VoitureBean vb = new VoitureBean();
		    List arrylist = new ArrayList();
		    arrylist=voitureManger.getAllMaintenanceCosts(sign);%>
		    <%for(int i=0; i<arrylist.size();i++){ 
		    vb=(VoitureBean)arrylist.get(i);%>			 
		    <tr height="30">
		      <td nowrap="nowrap" class="info_content_01"><%=vb.getTIMING_DATE()!=null?vb.getTIMING_DATE():""%></td>     
		      <td nowrap="nowrap" class="info_content_01">
		      <%for(int s=0;s<voitureResumelist.size();s++) {
		    	  voitureResume=(VoitureBean)voitureResumelist.get(s);%>
		    	  <%if(voitureResume.getCode_id().equals(vb.getCAUSES()!=null?vb.getCAUSES():"")){%>
		    	  <%=voitureResume.getCode_name()%><%}} %></td>
		      <td nowrap="nowrap" class="info_content_01">
		      	 <%if(vb.getUnit().equals("0")){ %> 元 <%} %>
		      	<%if(vb.getUnit().equals("1")){ %> 公里 <%} %>
		      	<%if(vb.getUnit().equals("2")){ %> 升 <%} %>
		      </td>
		      <td nowrap="nowrap" class="info_content_01"><%=vb.getAMOUNT()!=null?vb.getAMOUNT():""%></td>
		      <td nowrap="nowrap" class="info_content_01"><%=vb.getNOTE()!=null?vb.getNOTE():""%></td>
		      <td nowrap="nowrap" class="info_content_01"><font color="red"><%=vb.getCREATE_BY()%></font></td>	
		   </tr>
		   <%}%>
		   	<tr><td><br>	</td></tr>
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

</body>
<ait:xjos />
</html>
