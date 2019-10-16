<%@ page contentType="text/html; charset=UTF-8" import =" com.ait.sqlmap.util.SimpleMap"%>
<%@ include file="../inc/taglibs.jsp"%>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<jsp:useBean id="allEateryaType" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="result" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="resultTotal" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%@ page import="com.ait.gm.bean.*"%>
<html>
<head>
<title>就餐人数确认页面</title>
</head>
<script src="../js/prototype.js"></script>
<script src="../js/gmMuli.js"></script>
<SCRIPT type="text/javascript">
function dateSelectClick()
{
	document.form1.action="/gmControlServlet?operation=eatery&content=planconfirm&menu_code=${param.menu_code}&flag=search";
 	document.form1.submit();
}
function ImpExcel()
{
	document.form1.action="/gmControlServlet?operation=eatery&content=planconfirm&menu_code=${param.menu_code}&flag=excel";
 	document.form1.submit();
}

</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			
			
			
			<%@ include file="../inc/common_toolbar_none.jsp"%>
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
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><ait:message messageID="display.gm.eatery.plan.confirm" module="gm"></ait:message></td>
			</tr>
		</table>
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="center" colspan="10"><font color=red size=5>${current_date} 各部门就餐计划</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td align="center" width="90%">
					日期选择&nbsp;<input type="text" size="10" name="selectdate" value="${current_date }" onClick="setday(this);" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<ait:image src="../images/btn/Excel_Exp.gif" onclick="ImpExcel()"></ait:image>
				</td>
			</tr>
			
		</table>
		<table width="100%" align="center"  border="1" cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		 		<!--<tr align="center" bgcolor="#F5F5F5">
		 		<td width="5%" class="info_title_01">部门</td>
			 		<c:forEach items="${confirmEateryList}" var="confirmEateryList" varStatus="i">
					    <td width="10%" class="info_title_01">
				          	${confirmEateryList.GM_TYPE}就餐人数合计:
				          	<input type="text" name="total${i.index}" value="${confirmEateryList.eateryPeopleNum}" readonly="readonly" style="width:50px"/>
				        </td>
			        </c:forEach>
		        </tr>
		 		--><!--
		 		<tr style="color: #666666;" align="center">
		 		 	<c:forEach items="${confirmEateryList}" var="confirmEateryList" varStatus="i">
		 			<td align="center">
			 			<table align="center">
				 			<tr style="color: #666666;" align="center">
					        	<c:forEach items="${confirmEateryList.confirmDeptList}" var="confirmDeptList" varStatus="j">
								    <td width="50%" style="color: #666666;" align="center">
							          	${confirmDeptList.DEPTNAME}
							        </td> 	
							        <td width="100px" style="color: #666666;" align="center">
							          	人数合计:
							          	<input type="text" name="depttotal${i.index}${j.index}" value="${confirmDeptList.EAT_CONFIRM_PEOPLE}" readonly="readonly" style="width:30px"/>
							        </td>
							        <c:if test="${(j.index+1)%1==0}">
							    		</tr><tr>
							    	</c:if>
							    </c:forEach>
						    </tr>
					    </table>
				    </td>
				    </c:forEach>
        		</tr>
		 		-->
		 		
		 		<tr align="center" bgcolor="#F5F5F5">
		 		<td width="15%" class="info_title_01" rowspan="2">部门</td>
			 		<%
				 		SimpleMap resultTotalMap = null;
				 		SimpleMap sm2 = null;
				 		String gmtype = "";
				 		String result1 = "";
				 		for(int i=0 ; i<resultTotal.size() ; i++){
			 				resultTotalMap = (SimpleMap) resultTotal.get(i);
			 				
					 		for(int j=0 ; j<allEateryaType.size() ; j++){
								sm2 = (SimpleMap) allEateryaType.get(j);
								gmtype = sm2.getString("GM_TYPE");
				 			
								result1 = resultTotalMap.getString(gmtype);
			 		%>
						    <td width="10%" class="info_title_01" colspan="2">
					          	<%=gmtype %>
					        </td>
			        <%
				 			}
				 		}
			        %>
		        </tr>
		        <tr align="center">
		 		<%
				 		SimpleMap resultTotalMap1 = null;
				 		SimpleMap sm5 = null;
				 		String gmtype1 = "";
				 		String result5 = "";
				 		for(int i=0 ; i<resultTotal.size() ; i++){
			 				resultTotalMap1 = (SimpleMap) resultTotal.get(i);
			 				
					 		for(int j=0 ; j<allEateryaType.size() ; j++){
								sm5 = (SimpleMap) allEateryaType.get(j);
								gmtype1 = sm5.getString("GM_TYPE");
				 			
								result5 = resultTotalMap1.getString(gmtype1);
			 		%>
						    
					          <td nowrap="nowrap" class="info_title_01">统计人数: <%=result5 %></td>
					        <%if(sm5.getString("GM_ID").equals("42")){ %>
							<td align="center" nowrap="nowrap" class="info_title_01">昨日打卡人数：<%=resultTotalMap1.getString("NIGHTNUM") %></td>
							<%} %>
							<%if(sm5.getString("GM_ID").equals("43")){ %>
							<td align="center" nowrap="nowrap" class="info_title_01">昨日打卡人数：<%=resultTotalMap1.getString("YENUM") %></td>
							<%} %>
							<%if(sm5.getString("GM_ID").equals("26")){ %>
							<td align="center" nowrap="nowrap" class="info_title_01">昨日打卡人数：<%=resultTotalMap1.getString("MORINGNUM") %></td>
							<%} %>
							<%if(sm5.getString("GM_ID").equals("40")){ %>
							<td align="center" nowrap="nowrap" class="info_title_01">昨日打卡人数：<%=resultTotalMap1.getString("NOONNUM") %></td>
							<%} %>
					       
			        <%
				 			}
				 		}
			      %>
		 		 </tr>
		 		<%
		 			SimpleMap sm1 = null;
		 			SimpleMap sm = null;
		 			String DEPTNAME = "";
		 			String gm_type = "";
		 			String resultGmtype = "";
		 			for(int i=0 ; i<result.size() ; i++){
		 				sm1 = (SimpleMap) result.get(i);
		 				DEPTNAME = sm1.getString("DEPTNAME");
		 		%>
		 			<tr style="color: #666666;" align="center">
		 				<td align="center" nowrap="nowrap">
		 					<%=DEPTNAME %>
		 				</td>
						<%
							for(int j=0 ; j<allEateryaType.size() ; j++){
								sm = (SimpleMap) allEateryaType.get(j);
								gm_type = sm.getString("GM_TYPE");
								resultGmtype = sm1.getString(gm_type);
						%>	
							<td align="center" nowrap="nowrap">
								<%=resultGmtype %>
							</td>
							<%if(sm.getString("GM_ID").equals("42")){ %>
							<td align="center" nowrap="nowrap"><%=sm1.getString("NIGHTNUM") %></td>
							<%} %>
							<%if(sm.getString("GM_ID").equals("43")){ %>
							<td align="center" nowrap="nowrap"><%=sm1.getString("YENUM") %></td>
							<%} %>
							<%if(sm.getString("GM_ID").equals("26")){ %>
							<td align="center" nowrap="nowrap"><%=sm1.getString("MORINGNUM") %></td>
							<%} %>
							<%if(sm.getString("GM_ID").equals("40")){ %>
							<td align="center" nowrap="nowrap"><%=sm1.getString("NOONNUM") %></td>
							<%} %>
						<%
							}
						%>
		 			</tr>
	 			<%
		 			}
	 			%>
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
</html>