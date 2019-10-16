<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ev.bean.EvaluateInfo"%>
<%@ page import="com.ait.ev.bean.EvaluateAffirmor"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.ev.bean.EvaluateItem"%>
<jsp:useBean id="itemList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="evaluateItem" scope="page" class="com.ait.ev.bean.EvaluateItem" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />

<jsp:useBean id="evaluateList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="evaluateInfo" class="com.ait.ev.bean.EvaluateInfo" scope="page" />

<html>
<head>

</head>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=EvaluateDetailReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<body>




<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">	
   <tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="14"><b><font size="+2">月别查询报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>	
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		  
		   	<td class="info_title_01" nowrap>序号</td>
			<td class="info_title_01" nowrap>职号</td>
		    <td class="info_title_01" nowrap>姓名</td>	
			<td class="info_title_01" nowrap>评价月份</td>	
		    <td class="info_title_01" nowrap>部门</td>
		    <td class="info_title_01" nowrap>班组</td>
		    <td class="info_title_01" nowrap>职位
		    </td>
		     <td class="info_title_01" nowrap>基础分
		    </td>
		    	 <%
		     	for (int k = 0; k < itemList.size(); k++) {
		     		evaluateItem = (EvaluateItem) itemList.get(k);
		     		
		     		String itemName = evaluateItem.getItemName();
		     %>
							
								<td nowrap="nowrap" class="info_title_01">
									<%=itemName%></td>

								<%
									
									}
								%>
								
			 <td nowrap="nowrap" class="info_title_01">
				评价总分</td>
				 <!--
			<td nowrap="nowrap" class="info_title_01">
				本次排名</td>	
				
			 <td nowrap="nowrap" class="info_title_01">
				上次排名</td>	
			 <td nowrap="nowrap" class="info_title_01">
				名次变动</td>	 		
			  -->
			  <td nowrap="nowrap" class="info_title_01">
				说明事项</td>		
				
			
			  <td nowrap="nowrap" class="info_title_01">
				决裁人</td>
								
		     
			  <td nowrap="nowrap" class="info_title_01">
				决裁等级</td>
		  
		  
			      
		  </tr>
		<%for(int i=0;i<evaluateList.size();i++){                       
			evaluateInfo = (EvaluateInfo) evaluateList.get(i);
		 
		  
		   
		%>
		  <tr align="center">
		  
		    <td class="info_content_09" ><%= i + 1 %></td>
		    <td class="info_content_09" >
		    <%=evaluateInfo.getEMPID() %>
		    </td>
		    <td class="info_content_09">
		    <%=evaluateInfo.getCHINESENAME() %>
		    </td>
		    <td class="info_content_09">
			<%=evaluateInfo.getFOURTHDEPTNAME() %>
			</td>
			<td class="info_content_09">
			<%=evaluateInfo.getMONTH() %>
			</td>
			<td class="info_content_09" >
			<%=evaluateInfo.getDEPTNAME() %>
			</td>
			<td class="info_content_09" >
			<%=evaluateInfo.getPOST_CODE_NAME() %>
			</td>
			
			<td class="info_content_09" >
			<%=NumberUtil.convert(evaluateInfo.getITEM0()) %>
			</td>
			 
			  <%
			                    double itemValue = 0;
		       		    
                            for (int k = 0; k < itemList.size(); k++) {
		     		            evaluateItem = (EvaluateItem) itemList.get(k);
		     		           String itemId = evaluateItem.getItemId();
                                if(itemId.equals("item1")){
                                   itemValue = evaluateInfo.getITEM1();
                                }else if(itemId.equals("item2")){
                                   itemValue = evaluateInfo.getITEM2();
                                }else if(itemId.equals("item3")){
                                   itemValue = evaluateInfo.getITEM3();
                                }else if(itemId.equals("item4")){
                                   itemValue = evaluateInfo.getITEM4();
                                }else if(itemId.equals("item5")){
                                   itemValue = evaluateInfo.getITEM5();
                                }else if(itemId.equals("item6")){
                                   itemValue = evaluateInfo.getITEM6();
                                }else if(itemId.equals("item7")){
                                   itemValue = evaluateInfo.getITEM7();
                                }else if(itemId.equals("item8")){
                                   itemValue = evaluateInfo.getITEM8();
                                }else if(itemId.equals("item9")){
                                   itemValue = evaluateInfo.getITEM9();
                                }else if(itemId.equals("item10")){
                                   itemValue = evaluateInfo.getITEM10();
                                }else if(itemId.equals("item11")){
                                   itemValue = evaluateInfo.getITEM11();
                                }else if(itemId.equals("item12")){
                                   itemValue = evaluateInfo.getITEM12();
                                }else if(itemId.equals("item13")){
                                   itemValue = evaluateInfo.getITEM13();
                                }else if(itemId.equals("item14")){
                                   itemValue = evaluateInfo.getITEM14();
                                }else if(itemId.equals("item15")){
                                   itemValue = evaluateInfo.getITEM15();
                                }else if(itemId.equals("item16")){
                                   itemValue = evaluateInfo.getITEM16();
                                }else if(itemId.equals("item17")){
                                   itemValue = evaluateInfo.getITEM17();
                                }else if(itemId.equals("item18")){
                                   itemValue = evaluateInfo.getITEM18();
                                }else if(itemId.equals("item19")){
                                   itemValue = evaluateInfo.getITEM19();
                                }else if(itemId.equals("item20")){
                                   itemValue = evaluateInfo.getITEM20();
                                }else if(itemId.equals("item21")){
                                   itemValue = evaluateInfo.getITEM21();
                                }else if(itemId.equals("item22")){
                                   itemValue = evaluateInfo.getITEM22();
                                }else if(itemId.equals("item23")){
                                   itemValue = evaluateInfo.getITEM23();
                                }else if(itemId.equals("item24")){
                                   itemValue = evaluateInfo.getITEM24();
                                }else if(itemId.equals("item25")){
                                   itemValue = evaluateInfo.getITEM25();
                                }else if(itemId.equals("item26")){
                                   itemValue = evaluateInfo.getITEM26();
                                }else if(itemId.equals("item27")){
                                   itemValue = evaluateInfo.getITEM27();
                                }else if(itemId.equals("item28")){
                                   itemValue = evaluateInfo.getITEM28();
                                }else if(itemId.equals("item29")){
                                   itemValue = evaluateInfo.getITEM29();
                                }else if(itemId.equals("item30")){
                                   itemValue = evaluateInfo.getITEM30();
                                }else if(itemId.equals("item31")){
                                   itemValue = evaluateInfo.getITEM31();
                                }else if(itemId.equals("item32")){
                                   itemValue = evaluateInfo.getITEM32();
                                }else if(itemId.equals("item33")){
                                   itemValue = evaluateInfo.getITEM33();
                                }else if(itemId.equals("item34")){
                                   itemValue = evaluateInfo.getITEM34();
                                }else if(itemId.equals("item35")){
                                   itemValue = evaluateInfo.getITEM35();
                                }else if(itemId.equals("item36")){
                                   itemValue = evaluateInfo.getITEM36();
                                }else if(itemId.equals("item37")){
                                   itemValue = evaluateInfo.getITEM37();
                                }else if(itemId.equals("item38")){
                                   itemValue = evaluateInfo.getITEM38();
                                }else if(itemId.equals("item39")){
                                   itemValue = evaluateInfo.getITEM39();
                                }else{
                                   itemValue = 0;
                                }
		       		        	
		       		        	
		       	%>
		       <td class="info_content_09" >
		      <%=NumberUtil.convert(itemValue)%>
		       </td>
		   
		             <%
		   		    	}
		   		     %>
		      
			   <td class="info_content_09" >
			  <%=NumberUtil.convert(evaluateInfo.getTOTAL()) %>
			  </td>
			  <!--
			  <td class="info_content_09" >
			    <%=StringUtil.checkNull(evaluateInfo.getPLACE()) %>&nbsp;
			    </td>
			    <td class="info_content_09">
			    <%=StringUtil.checkNull(evaluateInfo.getLAST_MONTH_PLACE()) %>&nbsp;
			    </td>
			       <%  
			           int placeMove = 0;
			           if(NumberUtil.parseInt(evaluateInfo.getLAST_MONTH_PLACE(),0)>0){
			           		 placeMove = NumberUtil.parseInt(evaluateInfo.getPLACE(),0) - NumberUtil.parseInt(evaluateInfo.getLAST_MONTH_PLACE(),0);
			           }
		   		       
		   		   %>
			    <td class="info_content_09" >
			    <%=placeMove%>&nbsp;
			    </td>
			  -->
			   <td class="info_content_09" >
		       <%=StringUtil.checkNull(evaluateInfo.getREMARK()) %>
		       </td>
		       <td class="info_content_09" >
			    <%=StringUtil.checkNull(evaluateInfo.getAffirmor_id()) %>&nbsp;
			    </td>
			     <td class="info_content_09" >
			    <%=StringUtil.checkNull(evaluateInfo.getAffirm_level()) %>&nbsp;
			    </td>
		  </tr>
		<%}%>
		</table>
		</tr>            
		
		</table>






</body>
</html>
