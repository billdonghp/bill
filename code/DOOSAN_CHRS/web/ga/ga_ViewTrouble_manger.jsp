<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<script src="../js/prototype.js"></script>
<script language="javascript">
function ScenePhotos(photosid,str){
window.open("/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=viewPhoto&documentno=TROUBLE"+photosid+str,"","resizable=no,scrollbars,dependent,width=350,height=130,left=350,top=500");
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
					<td align="left" class="title1" colspan="10">车辆事故详细信息</td>
				</tr>
			</table>
			<table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	   

		  <tr>
		     <td class="info_title_01">司机</td>
			 <td class="info_content_00">${DRIVER } </td>
		     <td class="info_title_01">	乘车人数</td>
			 <td class="info_content_00">${USERSCOUNT }</td>
			 </tr>
		   <tr>
			 <td class="info_title_01"> 部门</td>
			 <td class="info_content_00">${DEPTNAME1}</td>		      
		      <td class="info_title_01">事故发生时间</td>
			 <td class="info_content_00">
			 ${START1 }&nbsp;${START2 }
			 </td>
			  </tr>
			  <tr>
		      <td class="info_title_01">事故发生地点</td>
			 <td class="info_content_00">${SITE }</td>
		      <td class="info_title_01">事故原因</td>
			 <td class="info_content_00">${CAUSE }</td>
			  </tr> 
			  <tr>
			  <td class="info_title_01">事故情况</td>
			 <td class="info_content_00">${STATE }</td>
		      <td class="info_title_01">事故认定</td>
			 <td class="info_content_00">${ESTABLISH }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01" colspan="2">当事人</td>
			 <td class="info_title_01" colspan="2">对方</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">姓名</td>
			 <td class="info_content_00">${IN_NAME }</td>
		      <td class="info_title_01">姓名</td>
			 <td class="info_content_00">${IN_NAME }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">性别</td>
			 <td class="info_content_00">
				<c:if test="${IN_SEX == '0' }"> 男</c:if>
				<c:if test="${IN_SEX == '1' }">女</c:if>
		      <td class="info_title_01">性别</td>
			 <td class="info_content_00">
			  <c:if test="${IN_SEX == '0' }"> 男</c:if>
				<c:if test="${IN_SEX == '1' }">女</c:if>
			  </tr>
			  <tr>
		      <td class="info_title_01">单位</td>
			 <td class="info_content_00">${IN_JOB }</td>
		      <td class="info_title_01">单位</td>
			 <td class="info_content_00">${O_JOB }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">本人地址</td>
			 <td class="info_content_00">${IN_ADDER }</td>
		      <td class="info_title_01">本人地址</td>
			 <td class="info_content_00">${O_ADDER }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">联络方式</td>
			 <td class="info_content_00">${IN_DEL }</td>
		      <td class="info_title_01">联络方式</td>
			 <td class="info_content_00">${O_DEL }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">公司地址</td>
			 <td class="info_content_00">${IN_COM }</td>
		      <td class="info_title_01">公司地址</td>
			 <td class="info_content_00">${O_COM }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">车种年份</td>
			 <td class="info_content_00">${IN_YEAR }</td>
		      <td class="info_title_01">车种年份</td>
			 <td class="info_content_00">${O_YEAR }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">车牌号码</td>
			 <td class="info_content_00">${IN_NUM }</td>
		      <td class="info_title_01">车牌号码</td>
			 <td class="info_content_00">${O_NUM }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">驾照号码</td>
			 <td class="info_content_00">${IN_DRIVERNUM }</td>
		      <td class="info_title_01">驾照号码</td>
			 <td class="info_content_00">${O_DRIVERNUM }</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">现场照片</td>
			 <td class="info_content_00"><a href="#" onclick="ScenePhotos('${id}','IN_PHO')" style="color:red" title="上传图片">查看上传图片</a></td>
		      <td class="info_title_01">现场照片</td>
			 <td class="info_content_00"><a href="#" onclick="ScenePhotos('${id}','O_PHO')" style="color:red" title="上传图片">查看上传图片</a></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">备注</td>
			 <td class="info_content_00">${MOME }</td>
		      <td class="info_title_01">上转文件</td>
			 <td class="info_content_00"><a href="#" onclick="ScenePhotos('${id}','FILE')" style="color:red" title="上传图片">查看扫描文件</a></td>
			  </tr>
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
