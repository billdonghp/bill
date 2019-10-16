<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="AnviewCorrectivePlanList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">
function ScenePhotos(photosid){
photosid = photosid+"1";
//window.open("/ajaxControlServlet?operation=uploadImp&correctiveplan=correctiveplan&documentno="+photosid,"","resizable,scrollbars,dependent,width=500,height=400,left=250,top=300");
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
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
			<%@ include file="inc/safe_toolbar_onlyback.jsp"%>
			
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

			<table width="85%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">隐患整改反馈</td>
				</tr>
			</table>
		  <table width="85%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
          <%for(int i=0;i<AnviewCorrectivePlanList.size();i++){ 
          dataMap=(SimpleMap)AnviewCorrectivePlanList.get(i);%>
			<tr>
				<td width="20%" class="info_title_01">编号</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("SECURITYCHECKSNO"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">部门</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("DEPTNAME"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">整改人</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("CHINESENAME"),"&nbsp;")%></td>
			</tr>			
			<tr>
				<td width="20%" class="info_title_01">整改情况及结果</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("CRFNOTE"),"&nbsp;")%></td>
			</tr>		
			<tr>
				<td width="20%" class="info_title_01">实际完成日期</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("OVERDATE"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">整改要求日期</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("COMPLETIONDATE"),"&nbsp;")%></td>
			</tr>				
			<tr>
				<td width="20%" class="info_title_01">整改完成照片</td>
				<td class="info_content_00"><%if(dataMap.get("ISCOMPLETEDRECTIFICATION").equals("2")){ %><span style="color:red; cursor:pointer;" onClick="ScenePhotos('<%=dataMap.get("SECURITYCHECKSNO")%>')">查看现场照片</span><%}else{ %><font color="red">整改正在进行，暂无照片</font><%} %></td>

		<%}%>	
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
<ait:xjos />
</body>

</html>
