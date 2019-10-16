<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="visiterViewDetail" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="visiterViewDetail1" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="visiterViewDetail2" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="visiterViewDetail3" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<!-- ga_visiter_viewDetail.jsp -->
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">
function ScenePhotos(photosid){
window.open("/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=viewPhoto&documentno="+photosid+"&flag=view","","resizable=no,scrollbars,dependent,width=350,height=130,left=350,top=500");
}
</script>
</head>
<body>

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
		<form name="form1" method="post" action="" enctype="multipart/form-data">

			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">来访者信息</td>
				</tr>
			</table>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		<%for(int i=0;i<visiterViewDetail.size();i++){ 
			dataMap=(SimpleMap)visiterViewDetail.get(i);%>
		    <tr align="center">				
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("GA_VISITER_APPLY_ID"),"&nbsp;")%></td>
			  <td nowrap="nowrap" class="info_title_01">
				来访介绍单位</td>
			  <td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISIT_INTRO_DEPT"),"&nbsp;")%></td>
			  <td nowrap="nowrap" class="info_title_01">
			  	联系人电话</td>
			  <td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_CONTACT_TEL"),"&nbsp;")%></td>
			  <td nowrap="nowrap" class="info_title_01">
			  	来访单位</td>
			  <td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_COMPANY"),"&nbsp;")%></td>
			</tr>
			
			<tr align="center">
			  <td nowrap="nowrap" class="info_title_01">
			  	访问地点</td>
			  <td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISIT_PLACE"),"&nbsp;")%></td>
			  <td nowrap="nowrap" class="info_title_01"  >
				来访日期</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_DATE"),"&nbsp;")%></td>	
			  <td nowrap="nowrap" class="info_title_01">
				到达时间</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_COME_TIME"),"&nbsp;")%></td>					
			  <td nowrap="nowrap" class="info_title_01">
				离开时间</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_END_TIME"),"&nbsp;")%></td>
			</tr>
			<tr align="center">
			  <td nowrap="nowrap" class="info_title_01">
				来访人数</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISIT_COUNT"),"&nbsp;")%></td>	
			  <td nowrap="nowrap" class="info_title_01">
				主要客人姓名/职务</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_NAME"),"&nbsp;")%></td>	
			  <td nowrap="nowrap" class="info_title_01">
				车牌号</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_CARNUM,"),"&nbsp;")%></td>	
			  <td nowrap="nowrap" class="info_title_01">
				来访目的</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_OBJECTIVE"),"&nbsp;")%></td>	
			</tr>
			<tr align="center">	
			  
			   <td nowrap="nowrap" class="info_title_01">
				期待效果</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("EXPECT_RESULT"),"&nbsp;")%></td>	
				<td nowrap="nowrap" class="info_title_01">
				来访类型</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISIT_TYPE"),"&nbsp;")%></td>	
				<td nowrap="nowrap" class="info_title_01">
				访客类型</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITOR_TYPE"),"&nbsp;")%></td>
			</tr>
			
			
		<%if("true".equals(dataMap.get("VISITER_EATRY_FLAG"))){ %>
		     <tr align="center">	
				<td align="left" class="title1" colspan="10">用餐信息</td>
			</tr>
			<tr align="center">	
		      <td nowrap="nowrap" class="info_title_01">
				区分</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_EATRY_DISTINCTION_NAME"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				餐厅名称</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_EATRY_NAME"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				用餐日期</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_EATRY_DATE"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				用餐时间</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_EATRY_TIME"),"&nbsp;")%></td>
				
			</tr>
			<tr align="center">	
			  
		      <td nowrap="nowrap" class="info_title_01">
				餐别</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_EATRY_TYPE_NAME"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				用餐人数</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_EATRY_NUMBER"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				备注</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_EATRY_MEMO"),"&nbsp;")%></td>
			
			</tr>
			
		<%}%>
		
		<%if("true".equals(dataMap.get("VISITER_VOITURE_FLAG"))){ %>
		     <tr align="center">	
				<td align="left" class="title1" colspan="10">车辆信息</td>
			</tr>
			<tr align="center">	
		      <td nowrap="nowrap" class="info_title_01">
				人数</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_NUMBER"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				出发日期</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_IN_DATE"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				出发时间</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_IN_TIME"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				预计到达日期</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_OUT_DATE"),"&nbsp;")%></td>
				
			</tr>
			<tr align="center">	
			  <td nowrap="nowrap" class="info_title_01">
				预计到达时间</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_OUT_TIME"),"&nbsp;")%></td>
		      <td nowrap="nowrap" class="info_title_01">
				出发地</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_START_AREA"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				途经地</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_PASS_AREA"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				最终目的地</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_END_AREA"),"&nbsp;")%></td>
				
			
			</tr>
			<tr align="center">	
			
				<td nowrap="nowrap" class="info_title_01">
				备注</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_VOITURE_MEMO"),"&nbsp;")%></td>
			
			</tr>
		<%}%>
		
		<%if("true".equals(dataMap.get("VISITER_CONFERENCE_FLAG"))){ %>
		     <tr align="center">	
				<td align="left" class="title1" colspan="10">参观者接待</td>
			</tr>
			<tr align="center">	
		      <td nowrap="nowrap" class="info_title_01">
				接待地点</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_CONFERENCE_ROOM_NAME"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				工厂介绍资料</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_CONFERENCE_LANGUAGE"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				讲解人</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_CONFERENCE_PEOPLE"),"&nbsp;")%></td>
					
			</tr>
			<tr align="center">	
			  <td nowrap="nowrap" class="info_title_01">
				接待领导</td>
				<td nowrap="nowrap" class="info_content_00">
				<!-- StringUtil.checkNull(dataMap.get("VISITER_CONFERENCE_MANAGE_NAME") -->
				<%=StringUtil.checkNull(dataMap.get("VISITER_CONFERENCE_MANAGE_NAME"),"&nbsp;")%></td>
			
			  <td nowrap="nowrap" class="info_title_01">
				公司宣传片</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_CONFERENCE_DATE"),"&nbsp;")%></td>
		      <!-- <td nowrap="nowrap" class="info_title_01">
				时间</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_CONFERENCE_TIME"),"&nbsp;")%></td> -->
				<td nowrap="nowrap" class="info_title_01">
				其他</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_CONFERENCE_PURPOSE"),"&nbsp;")%></td>
				
			
			</tr>
			
		<%}%>
		
		<%if("true".equals(dataMap.get("VISITER_FACTORY_FLAG"))){ %>
		     <tr align="center">	
				<td align="left" class="title1" colspan="10">工厂参观</td>
			</tr>
			<tr align="center">	
		      <td nowrap="nowrap" class="info_title_01">
				参观人数</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_NUMBER"),"&nbsp;")%></td>
				<!--  <td nowrap="nowrap" class="info_title_01">
				参观方法</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_METHOD_NAME"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				参观路线</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_ROUTE_NAME"),"&nbsp;")%></td>
					
			</tr>
			<tr align="center">	
			  <td nowrap="nowrap" class="info_title_01">
				试听设备</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_DEVICE"),"&nbsp;")%></td>
			
			  <td nowrap="nowrap" class="info_title_01">
				安全帽</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_HELMET_NAME"),"&nbsp;")%></td>
		      <td nowrap="nowrap" class="info_title_01">
				数量</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_HELMET_NUMBER"),"&nbsp;")%></td>
				
			
			</tr>
			<tr align="center">	
			  <td nowrap="nowrap" class="info_title_01">
				导览系统</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_SYSTEM_NAME"),"&nbsp;")%></td>
			
			  <td nowrap="nowrap" class="info_title_01">
				中文人数</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_C_NUMBER"),"&nbsp;")%></td>
		      <td nowrap="nowrap" class="info_title_01">
				韩文人数</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_K_NUMBER"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				英文人数</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_E_NUMBER"),"&nbsp;")%></td>
				
			
			</tr>
			
			<tr align="center">	-->
			  <td nowrap="nowrap" class="info_title_01">
				陪同人员</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_PEOPLE"),"&nbsp;")%></td>
			<td nowrap="nowrap" class="info_title_01" ></td>	
			<td nowrap="nowrap" class="info_content_00"></td>
			</tr>
			<tr align="center">	
			  <td nowrap="nowrap" class="info_title_01">
				大厅欢迎词</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_WELCOME_SPEECH"),"&nbsp;")%></td>
		      <td nowrap="nowrap" class="info_title_01">
				欢迎词语言</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_LANGUAGE"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				其他</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_FACTORY_MEMO"),"&nbsp;")%></td>
				
			
			</tr>
			
		<%}%>
		
		<%if("true".equals(dataMap.get("VISITER_TOURISM_FLAG"))){ %>
		     <tr align="center">	
				<td align="left" class="title1" colspan="10">观光旅游</td>
			</tr>
			<tr align="center">	
		      <td nowrap="nowrap" class="info_title_01">
				观光旅游</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_TOURISM_NAME"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				日期</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_TOURISM_DATE"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				时间</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_TOURISM_TIME"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">
				景点</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_TOURISM_TYPE_NAME"),"&nbsp;")%></td>
			
			</tr>
			<tr align="center">	
			  <td nowrap="nowrap" class="info_title_01">
				费用负担</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_TOURISM_FEE"),"&nbsp;")%></td>
			
			  <td nowrap="nowrap" class="info_title_01">
				备注</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("VISITER_TOURISM_MEMO"),"&nbsp;")%></td>	
			
			</tr>
			
		<%}%>
			
		<%}%>	
		</table>
		<br><br>
	<%for(int i=0;i<visiterViewDetail.size();i++){ 
			dataMap=(SimpleMap)visiterViewDetail.get(i);%>
		<table id = 'operateTable' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">	
				<tr>
				<td align="left" class="title1" colspan="18">
					管理部协助内容
				</td>
			</tr>	
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">欢迎屏</td>
				<td nowrap="nowrap" class="info_title_01">接待地点(主楼一楼)</td>
				<td nowrap="nowrap" class="info_title_01">茶</td>
				<td nowrap="nowrap" class="info_title_01">咖啡</td>
				<td nowrap="nowrap" class="info_title_01">矿泉水</td>
				<td nowrap="nowrap" class="info_title_01">宣传片</td>
				
			</tr>
			
			<tr>			
				<td nowrap="nowrap" class="info_content_01">
				<%if("欢迎屏".equals(StringUtil.checkNull(dataMap.get("WELCOME_SCR"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				
				
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<%=StringUtil.checkNull(dataMap.get("RECEPTION_OFFICE"),"&nbsp;") %>
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<%if("茶".equals(StringUtil.checkNull(dataMap.get("TEA"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<%if("咖啡".equals(StringUtil.checkNull(dataMap.get("COFFEE"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<%if("矿泉水".equals(StringUtil.checkNull(dataMap.get("PRESENT"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<%if("宣传片".equals(StringUtil.checkNull(dataMap.get("ADVERTISING_VIDEO"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>
				
			</tr>
			
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">现场防护设备</td>
				<td nowrap="nowrap" class="info_title_01">传译设备</td>
				<td nowrap="nowrap" class="info_title_01">拍摄</td>
				<td nowrap="nowrap" class="info_title_01">午餐就餐</td>
				<td nowrap="nowrap" class="info_title_01">VIP餐厅</td>
				<td nowrap="nowrap" class="info_title_01">水果(VIP)</td>
			</tr>
			<tr>		
				<td nowrap="nowrap" class="info_content_01">
				<%if("现场防护设备".equals(StringUtil.checkNull(dataMap.get("DEFEND_FACILITY"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>	
				<td nowrap="nowrap" class="info_content_01">
				<%if("传译设备".equals(StringUtil.checkNull(dataMap.get("INTERPRET_FACILITY"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				
				
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<%if("拍摄".equals(StringUtil.checkNull(dataMap.get("SHOOT"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>
				
				<td nowrap="nowrap" class="info_content_01">
				<%if("午餐就餐".equals(StringUtil.checkNull(dataMap.get("LUNCH_REPAST"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<%if("VIP餐厅".equals(StringUtil.checkNull(dataMap.get("RESTAURANT"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<%if("水果(VIP)".equals(StringUtil.checkNull(dataMap.get("FRUIT"),"&nbsp;"))){%>	
				<input type="checkbox" checked="checked" />
				<%} else{%>
					<input type="checkbox" />
					
				<%}%>
				</td>
			</tr>		
		</table>
		<%} %>
		<br>
		<br>
		<%for(int i=0;i<visiterViewDetail.size();i++){ 
			dataMap=(SimpleMap)visiterViewDetail.get(i);%>
		<table id = 'operateTable' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">	
				<tr>
				<td align="left" class="title1" colspan="18">
					接待部门信息
				</td>
			</tr>	
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01"><font color="red"></font>接待部门</td>
				<td nowrap="nowrap" class="info_content_00">
				
				<!--<div id="deptName" name="deptName">&nbsp;</div>-->
				<ait:selDept name="frontdeptID" supervisorType="pa" all="all" selected="${DeptID}" />
				</td>
				<td nowrap="nowrap" class="info_title_01">				
				接待担当</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("FRONT_EMP"),"&nbsp;")%></td>
				
			</tr>
			
			<tr>			
				<td nowrap="nowrap" class="info_title_01">				
				接待领导</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("RECEPTION_LEAD"),"&nbsp;")%></td>
				<td nowrap="nowrap" class="info_title_01">				
				接待担当电话</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("RECEPTION_ACT_PHONE"),"&nbsp;")%></td>
			</tr>
			<tr>			
				<td nowrap="nowrap" class="info_title_01">特记事项</td>
				<td nowrap="nowrap" class="info_content_00">
				<%=StringUtil.checkNull(dataMap.get("NOTE"),"&nbsp;")%></td>
			</tr>
					
		</table>
		<%} %>
		<%if(visiterViewDetail3.size()>0){ %>
		<table id = 'operateTable' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">	
				<tr>
				<td align="left" class="title1" colspan="18">
					用餐信息
				</td>
			</tr>	
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">区分&nbsp; </td>
				<td nowrap="nowrap" class="info_title_01">餐厅名称&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">用餐日期&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">用餐时间&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">餐别&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">用餐人数&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">备注&nbsp;</td>
			</tr>
			<c:forEach items="${visiterViewDetail3}" var="test" varStatus="j">
			<tr>			
				<td nowrap="nowrap" class="info_content_01">${test.VISITER_EATRY_DISTINCTION_NAME }</td>
				<td nowrap="nowrap" class="info_content_01">${test.VISITER_EATRY_NAME }</td>
				<td nowrap="nowrap" class="info_content_01">${test.VISITER_EATRY_DATE }</td>
					
				<td nowrap="nowrap" class="info_content_01">${test.VISITER_EATRY_TIME }</td>
				
				<td nowrap="nowrap" class="info_content_01">${test.VISITER_EATRY_TYPE_NAME }</td>
					
				<td nowrap="nowrap" class="info_content_01">${test.VISITER_EATRY_NUMBER }</td>
				<td nowrap="nowrap" class="info_content_01">${test.VISITER_EATRY_MEMO }</td>

			</tr>
			</c:forEach>
		</table>
		<%} %>
			<%if(visiterViewDetail2.size()>0){ %>
		<table id = 'operateTable' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">	
				<tr>
				<td align="left" class="title1" colspan="18">
					住宿信息
				</td>
			</tr>	
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">人员姓名&nbsp; </td>
				<td nowrap="nowrap" class="info_title_01">住宿标准&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">入住日期&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">入住时间&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">退房日期&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">退房时间&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">备注&nbsp;</td>
			</tr>
			<c:forEach items="${visiterViewDetail2}" var="test" varStatus="j">
			<tr>			
				<td nowrap="nowrap" class="info_content_01">${test.ACCOMMODATION_NAME }</td>
				<td nowrap="nowrap" class="info_content_01">${test.ACCOMMODATION_STANDARD_NAME }</td>
				<td nowrap="nowrap" class="info_content_01">${test.ACCOMMODATION_IN_DATE }</td>
					
				<td nowrap="nowrap" class="info_content_01">${test.ACCOMMODATION_IN_TIME }</td>
				
				<td nowrap="nowrap" class="info_content_01">${test.ACCOMMODATION_OUT_DATE }</td>
					
				<td nowrap="nowrap" class="info_content_01">${test.ACCOMMODATION_OUT_TIME }</td>
				<td nowrap="nowrap" class="info_content_01">${test.ACCOMMODATION_MEMO }</td>

			</tr>
			</c:forEach>
		</table>
		<%} %>
		
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
<ait:xjos />
</body>

</html>