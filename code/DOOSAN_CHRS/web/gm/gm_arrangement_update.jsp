<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gm_id" class="java.lang.String" scope="request" />
<jsp:useBean id="gmtype" class="java.lang.String" scope="request" />
<jsp:useBean id="xs" class="java.lang.String" scope="request" />
<jsp:useBean id="fz" class="java.lang.String" scope="request" />
<jsp:useBean id="xse" class="java.lang.String" scope="request" />
<jsp:useBean id="fze" class="java.lang.String" scope="request" />
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean" />
<html>
<head>
<script src="../js/prototype.js"></script>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<%@ include file="../inc/meta.jsp" %>
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<script language="javascript">
function checkFile(){ 
   var temp = document.form1.jpgUpload.value;
   if(temp!=null && temp!=""){
       if((temp.indexOf(".jpg")==-1) && (temp.indexOf(".gif")==-1) && (temp.indexOf(".JPG")==-1) && (temp.indexOf(".GIF")==-1) && (temp.indexOf(".xls")==-1)&&(temp.indexOf(".XLS")==-1) && (temp.indexOf(".doc")==-1)&&(temp.indexOf(".DOC")==-1)&& (temp.indexOf(".ppt")==-1)&&(temp.indexOf(".PPT")==-1)){ 
        return true;
       }   
   }else{
     return false;
   }
}
function Save(){
	 if(checkFile()){
	  alert("只能上传jpg、gif、xls、doc、ppt格式的文件！");
	  return;
	  }else{
	  document.form1.action="/gmControlServlet?operation=eatStatistic&content=arrangementUpdate&menu_code=${param.menu_code}";	
	  document.form1.submit(); 	
	  }
}

</script>
</head>
<body>


<table width="100%" border="0" cellspacing="0" cellpadding="0">


	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_a.jsp"%>

		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> 
		<form name="form1" method="post" action=""  enctype="multipart/form-data">
		<input type="hidden" name="listSize" value="${fn:length(arrangementUpdateViewList)}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">就餐安排</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<c:if test="${errorMsg!=null}">
				<td align="center"><font style="color:red;">${errorMsg}</font></td>
				</c:if>		
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;" align="left">
		<tr>
		<td width="10%">&nbsp;</td>
		<td width="40%">
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;" align="left">	
			<c:forEach items="${arrangementUpdateViewList}" var="test" varStatus="i">
			<c:if test="${i.index==0}">
			<tr>
			<td width="10%" class="info_title_01">日期</td>
			<td width="10%" class="info_content_01"><input type="text" name="arrangementDate" value="${test.ARRANGEMENT_DATE}" onClick="setday(this);" readonly style="width:70px" required></td>
			<td width="10%" class="info_title_01">备注</td>
			<td width="10%" class="info_content_01"><textarea name="Remarks" style=" height: 20px;width:150px "
										type="_moz" onfocus="this.style.height='60px'"
										onblur="this.style.height='20px';">${test.REMARK }</textarea></td>
			</tr>	
			</c:if>
			</c:forEach>		
			<tr>
				<td width="10%" class="info_title_01">就餐类型</td>
				<td width="10%" class="info_title_01">餐别区分</td>
				<td width="10%" class="info_title_01">人数</td>
				<td width="10%" class="info_title_01">状态</td>
				
			</tr>
			<c:forEach items="${arrangementUpdateViewList}" var="test" varStatus="i">
			<tr>
			   <input type="hidden" name="pkNo_${i.index}" value="${test.PKNO}">			  
				<td width="10%" class="info_title_01">${test.TYPENAME}</td>
				<td width="10%" class="info_content_01">${test.FOODTYPENAME}</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_${i.index}" value="${test.NUM}" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_${i.index}">
				  <c:if test="${test.DIFF=='eatery'}">
				  <option value="1" <c:if test="${test.FLAG==1}">selected</c:if>>供餐</option>
				  <option value="0" <c:if test="${test.FLAG==0}">selected</c:if>>无供餐</option>
				  </c:if>
				  <c:if test="${test.DIFF=='bus'}">
				  <option value="2" <c:if test="${test.FLAG==2}">selected</c:if>>无班车</option>				  
				  <option value="3" <c:if test="${test.FLAG==3}">selected</c:if>>45座1班</option>	
				  <option value="9" <c:if test="${test.FLAG==9}">selected</c:if>>45座2班</option>	
				  <option value="4" <c:if test="${test.FLAG==4}">selected</c:if>>33座</option>	
				  <option value="5" <c:if test="${test.FLAG==5}">selected</c:if>>23座</option>	
				  <option value="6" <c:if test="${test.FLAG==6}">selected</c:if>>ISTANA</option>	
				  <option value="7" <c:if test="${test.FLAG==7}">selected</c:if>>开发区和福山合并45座</option>	
				  <option value="8" <c:if test="${test.FLAG==8}">selected</c:if>>开发区和福山合并33座</option>	
				  <option value="11" <c:if test="${test.FLAG==11}">selected</c:if>>福山和莱山合并45座</option>	
				  <option value="12" <c:if test="${test.FLAG==12}">selected</c:if>>福山和莱山合并33座</option>
				  <option value="10" <c:if test="${test.FLAG==10}">selected</c:if>>莱山区芝罘区合并45座</option>
				  <option value="14" <c:if test="${test.FLAG==14}">selected</c:if>>莱山区芝罘区合并33座</option>
				  <option value="13" <c:if test="${test.FLAG==13}">selected</c:if>>莱山区芝罘区合并23座</option>			
				  <option value="15" <c:if test="${test.FLAG==15}">selected</c:if>>开发区和福山合并23座</option>	
				  <option value="16" <c:if test="${test.FLAG==16}">selected</c:if>>福山和莱山合并23座</option>	
				  
				  <option value="10">莱山区芝罘区合并23座</option>
				  <option value="13">莱山区芝罘区合并33座</option>
				  <option value="14">莱山区芝罘区合并45座</option>
				  </c:if>
				</select>
               </td>
				
			</tr>	
			<c:if test="${i.index==(fn:length(arrangementUpdateViewList)-1)}">
			<tr>
			    <td width="10%" class="info_title_01">菜谱</td>	
			    <td width="10%" class="info_content_01" colspan="2"><input type="text" name="startDate" style="width:70px" value="${startDate}" onClick="setday(this);" readonly="readonly"/>~	
			   <input type="text" name="endDate" style="width:70px" value="${endDate}" onClick="setday(this);" readonly="readonly"/>
			   </td>			
				<td><input type="file" name="jpgUpload" value="" style="width:200px" title="上传">&nbsp;&nbsp;&nbsp;</td>
			</tr>		
			</c:if>	
			</c:forEach>			
		</table>
		</td>
		<td width="50%">&nbsp;</td>
		</tr>
		</table>
		</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
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

</body>
</html>
