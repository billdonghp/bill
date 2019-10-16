<%@ page contentType="text/html; charset=UTF-8" language="java"	errorPage=""%>

<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"      scope="session" />
<html>
<head>

<script language="javascript" src="../js/prototype/prototype0202.js" /></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 评价进行 > 个人评价</title>
    <script language="javascript">
   
function accAdd(arg1,arg2){ 
var r1,r2,m; 
try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
m=Math.pow(10,Math.max(r1,r2)) 
return (arg1*m+arg2*m)/m 
} 

</script>

</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 

<%
EvsCraft evscraft = new EvsCraft();  
String code_name1="";
code_name1=request.getParameter("code_name1")!=null?request.getParameter("code_name1"):code_name1;
if(code_name1.equals("")){
	code_name1=code_name1+"";
}
String  countemp1 ="0";
countemp1=(String)request.getAttribute("countemp")!=null?(String)request.getAttribute("countemp"):countemp1;
int countemp=Integer.parseInt(countemp1);

String  craftid ="";
craftid=(String)request.getAttribute("craftid")!=null?(String)request.getAttribute("craftid"):craftid; 

String  skileid ="";
skileid=(String)request.getAttribute("skileid")!=null?(String)request.getAttribute("skileid"):skileid; 

%>
<table width="100%" border="0"   cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evs0302toolbar_v_v.jsp"%> 
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
		 <td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 个人评价 -->
				 个人评价</td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
	       		 	<td class="info_title_01" width="80">职号</td> 	       		 
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >${searchEmpList.EMPID}&nbsp;</div>
					</td>				
					
					<td class="info_title_01" width="80">姓名</td> 	       		
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >${searchEmpList.CHINESENAME}&nbsp;</div> 
					</td>					
					<td class="info_title_01" width="80">部门</td> 	       		 	 
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >${searchEmpList.DEPTNAME}&nbsp;</div> 
					</td>
					
					<td class="info_title_01" width="80">部门(所属)</td> 	       		 	 
					<td height="2" align="left" class="info_content_00">
							<div align="left" height="30" >${searchEmpList.DEPTFULLNAME}&nbsp;</div> 
					</td>
				</tr>
			</table>
	      </td>
	</tr>
	</table>
	
	<!-- display 3 level menu -->
	  <%@ include file="inc/evs_nav.jsp"%>
	<!-- display content -->
		<br>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">评价打分</td>
			</tr>
		</table>
	
<form name="form1" method="post" action="/evs0302_eval.jsp" method="POST">	
 <table width="98%" border="0" align="center" cellpadding="0"
	       cellspacing="1">
	 <tr>
		<td class="line">
		             <input type="hidden" name="ev_period_id"   value="${searchEmpList.EV_PERIOD_ID}"/>
					 <input type="hidden" name="ev_emp_id"   value="${searchEmpList.PERSON_ID}"/>
					 <input type="hidden" name="ev_emp_name"   value="${searchEmpList.CHINESENAME}"/>
					 <input type="hidden" name="ev_type_id"   value="${searchEmpList.EV_TYPE_ID}"/>
					 <input type="hidden" name="flag" value="save"/>
					 <input type="hidden" name="flag1" value="submit"/> 
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" onClick="HighLightTR('#F0F1F4','black','','')">
			 <td width="12%" class="info_title_01">
				<div align="center">序号</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">年度</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">工种</div>
				</td>			 
				<td width="13%" class="info_title_01">
				<div align="center">岗位</div>
				</td>				
				<td width="12%" class="info_title_01">
				<div align="center">工序</div>
				</td>
				 <td width="13%" class="info_title_01">
				<div align="center">认证时间</div>
				</td>
				 <td width="13%" class="info_title_01">
				<div align="center">熟练度</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">标准动作</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">作业标准遵守</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">作业品质</div>
				</td>
				 <td width="10%" class="info_title_01">
				<div align="center">单项得分</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">工序技能等级</div>
				</td> 
				<td width="13%" class="info_title_01">
				<div align="center">工序难度系数</div>
				</td> 
				 <td width="10%" class="info_title_01">
				<div align="center">工序得分</div>
				</td>
				 <td width="10%" class="info_title_01">
				<div align="center">累积积分</div>
				</td>
				<td width="38%" class="info_title_01">
				<div align="center">问题点</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">认证人</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">是否复审</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">是否一线通</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">是否保留积分</div>
				</td>
				<td width="8%" class="info_title_01">
				<div align="center">状态</div>
				</td>
			</tr>
			
		             <%  
		                List searchCount = (List)request.getAttribute("searchCount");
		                int number = 0;
		                int affirmNo = 1;
		                int numbers =0;
		                String checkState ="";
		                String statuss="";
		                for(int j=0;j<searchCount.size();j++){
			                Map searchCountList = (Map)searchCount.get(j);
			                number = j;
			                numbers =j+1;
			                String num = String.valueOf(number);
			                request.setAttribute("num",num);
			                checkState ="";
			                
			                if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("2"))
			                     checkState =  "disabled";
			                else if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("4"))
			                     checkState =  "disabled";
			                if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("1"))
			                	statuss =  "已保存";
			                else if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("2"))
			                	statuss =  "已提交";
			                else if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("3"))
			                	statuss =  "已决裁";
			                else if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("4"))
			                	statuss =  "已否决";
			                %>
						<tr	onClick="HighLightTR('#F0F1F4','black','<%=(String)searchCountList.get("SETUPCODENO") %>','<%=(String)searchCountList.get("STATUS")%>')">
						<td class="info_content_09" ><%=numbers%>
							 	<input type="hidden" name="setupcodeNo_${num}" value="<%=(String)searchCountList.get("SETUPCODENO")%>" >
							 </td>
							 <td align="left" >
							   <%=(String)searchCountList.get("EV_PERIOD") %>								 
							</td>	
							<td align="left" >
							   <%=(String)searchCountList.get("CRAFT") %>								 
							</td>							 
							<td align="left">
							 <%=(String)searchCountList.get("LINE") %>
							</td>						 
							<td align="left">
							<%=(String)searchCountList.get("JOBCONTENT") %>	
							</td>						
							 <td align="center">
							 <%=searchCountList.get("PROVEDATE")%>
							 </td>	
							  <!-- 熟练度 -->							 
							 <td align="right">
							  <%=searchCountList.get("PROFICIENCY")%>
							 </td>		
							  <td align="right">
							 <%=searchCountList.get("STANDARDACTION")%>
							 </td> 	
							  <!-- 作业标准 -->		
							 <td align="right">
							 <%=searchCountList.get("OPERATIONCOM")%>
							 </td>	
							  <!-- 作业品质 -->		
							  <td align="right">
							 <%=searchCountList.get("QUALITYOFWORK")%>
							 </td>
							 <!-- 以上 单项得分-->
							 <td align="right">
							<%=searchCountList.get("COMPOSITE")%>
							 </td>
							 <td align="center"> 
							<%=searchCountList.get("SKILLLEVEL")%>
							 </td>
							 <td align="right">
							 <%=searchCountList.get("DESCRIPTIO")%>
							 </td>
							 <td align="right">
							 <%=searchCountList.get("SKILLSCORE")%>
							 </td>
							 <td align="right">
							 <%=searchCountList.get("SUMSCORE")%>
							 </td>
							 <td>
							 <%=searchCountList.get("REMARK")%>
							 </td>
							 <td>
							 <%=searchCountList.get("PROVEBY")%>
							 </td>
							  <td>
							 <%=searchCountList.get("CONFIRMDATE")%>
							 </td>
							  <td>
							  <%=(String)searchCountList.get("SHEOPERCYQ") %>
							 </td>
							 
							 <td>
							  <%=(String)searchCountList.get("PURPOSE") %>	
							 </td>
							  <td>
							  <%=statuss%>			  
							</td>
							  
						</tr>
						
						<%} %> 
								 	
		</table>

		</td>
	</tr>
	</table>
	</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
