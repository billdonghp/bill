<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="securityChecksList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">
function ScenePhotos(photosid){
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=se0301&flag=view&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=300,height=100,left=350,top=500");
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
					<td align="left" class="title1" colspan="10">检查记录详情查看</td>
				</tr>
			</table>
		  <table width="85%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
          <%for(int i=0;i<securityChecksList.size();i++){ 
          dataMap=(SimpleMap)securityChecksList.get(i);%>
			<tr>
				<td width="20%" class="info_title_01">编号</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("DOCUMENTNO"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">检查人</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("CHINESENAME"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">检查日期</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("SECURITYCHECKSDATE"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">受检部门</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("DEPTNAME"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">地点</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("LOCATION"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">隐患及不符合通报</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("HIDDENDANGERS_NAME"),"&nbsp;")%></td>
			</tr>		
			<tr>
				<td width="20%" class="info_title_01">整改完成日期</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("RECTIFICATION_DATE"),"&nbsp;")%></td>
			</tr>	
			<tr>
				<td width="20%" class="info_title_01">整改要求</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("RECTIFICATION_NAME"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">备注</td>
				<td class="info_textarea_content"><%=StringUtil.checkNull(dataMap.get("BRIEF"),"&nbsp;")%></td>
			</tr>		
			<tr>
				<td width="20%" class="info_title_01">现场照片</td>
				<td class="info_content_00"><span style="color:red; cursor:pointer;" onClick="ScenePhotos('<%=dataMap.get("DOCUMENTNO")%>')">查看现场照片</span></td>

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
