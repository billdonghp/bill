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
	 if($('arrangementDate').value==null || $('arrangementDate').value==""){	 
	  alert("日期不能为空！");
	  return;
	 }else if(checkFile()){
	  alert("只能上传jpg、gif、xls、doc、ppt格式的文件！");
	  return;
	 }else{	  
	  document.form1.action="/gmControlServlet?operation=eatStatistic&content=arrangementAdd&menu_code=${param.menu_code}";
	
	  document.form1.submit(); 
	 }
}

function band(backColor,textColor,pkno,i)
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
		document.form1.pkNo.value=pkno;
		document.form1.iindex.value=i;
		document.form1.serialNumber.value=document.form1["serialNumber_"+i].value;
		document.form1.cardNumber.value=document.form1["cardNumber_"+i].value;		
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
		<form name="form1" method="post" action="" enctype="multipart/form-data">
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
			<tr>
			<td width="10%" class="info_title_01">日期</td>
			<td width="10%" class="info_content_01"><input type="text" name="arrangementDate" value="" onClick="setday(this);" readonly style="width:70px" required></td>
			<td width="10%" class="info_title_01">备注</td>
			<td width="10%" class="info_content_01"><textarea name="Remarks" style=" height: 20px;width:150px "
										type="_moz" onfocus="this.style.height='60px'"
										onblur="this.style.height='20px';"></textarea></td>
			</tr>			
			<tr>
				<td width="10%" class="info_title_01">就餐类型</td>
				<td width="10%" class="info_title_01">餐别区分</td>
				<td width="10%" class="info_title_01">人数</td>
				<td width="10%" class="info_title_01">状态</td>
				
			</tr>
			<tr>
			   <input type="hidden" name="eateryType_0" value="${break}">
			   <input type="hidden" name="foodType_0" value="${Cinese_Food}">
			    <input type="hidden" name="diff_0" value="eatery">
				<td width="10%" class="info_title_01">早餐</td>
				<td width="10%" class="info_content_01">&nbsp;</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_0" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_0">
				  <option value="0">无供餐</option>
				  <option value="1">供餐</option>				 
				</select>
               </td>
				
			</tr>
			<tr>
			  <input type="hidden" name="eateryType_1" value="${lunch}">
			  <input type="hidden" name="foodType_1" value="${Cinese_Food}">
			  <input type="hidden" name="diff_1" value="eatery">
				<td width="10%" class="info_title_01" rowspan="2">午餐</td>
				<td width="10%" class="info_content_01">中餐</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_1" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_1">
				  <option value="0">无供餐</option>
				  <option value="1">供餐</option>				  
				</select>
               </td>
				
			</tr>
			<tr>			
			     <input type="hidden" name="eateryType_2" value="${lunch}">	
			     <input type="hidden" name="foodType_2" value="${Korean_Food}">
			      <input type="hidden" name="diff_2" value="eatery">
				<td width="10%" class="info_content_01">韩餐</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_2" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_2">
				  <option value="0">无供餐</option>
				  <option value="1">供餐</option>				  
				</select>
               </td>
				
			</tr>
			<tr>
			    <input type="hidden" name="eateryType_3" value="${supper}">	
			    <input type="hidden" name="foodType_3" value="${Cinese_Food}">
			    <input type="hidden" name="diff_3" value="eatery">
				<td width="10%" class="info_title_01" rowspan="2">晚餐</td>
				<td width="10%" class="info_content_01">中餐</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_3" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_3">
				  <option value="0">无供餐</option>
				  <option value="1">供餐</option>				 
				</select>
               </td>
				
			</tr>
			<tr>
			    <input type="hidden" name="eateryType_4" value="${supper}">	
			    <input type="hidden" name="foodType_4" value="${Korean_Food}">	
			     <input type="hidden" name="diff_4" value="eatery">			
				<td width="10%" class="info_content_01">韩餐</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_4" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_4">
				  <option value="0">无供餐</option>
				  <option value="1">供餐</option>				  
				</select>
               </td>
				
			</tr>
			<tr>
			   <input type="hidden" name="eateryType_5" value="${dinner}">	
			   <input type="hidden" name="foodType_5" value="${Cinese_Food}">	
			    <input type="hidden" name="diff_5" value="eatery">	
				<td width="10%" class="info_title_01">夜餐</td>
				<td width="10%" class="info_content_01">&nbsp;</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_5" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_5">
				  <option value="0">无供餐</option>
				  <option value="1">供餐</option>				  
				</select>
               </td>
				
			</tr>
			<tr>
			   <input type="hidden" name="eateryType_12" value="${bus}">	
			   <input type="hidden" name="foodType_12" value="${line_seven}">	
			   <input type="hidden" name="diff_12" value="bus">	
				<td width="10%" class="info_title_01" rowspan="20">班车</td>
				<td width="10%" class="info_content_01">牟平17点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_12" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_12">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>				  
				</select>
               </td>
				
			</tr>
			<tr>
			   <input type="hidden" name="eateryType_6" value="${bus}">	
			   <input type="hidden" name="foodType_6" value="${line_one}">	
			   <input type="hidden" name="diff_6" value="bus">	
				
				<td width="10%" class="info_content_01">牟平20点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_6" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_6">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>			  
				</select>
               </td>
				
			</tr>
			<tr>
			   <input type="hidden" name="eateryType_22" value="${bus}">	
			   <input type="hidden" name="foodType_22" value="${line_seventeen}">	
			   <input type="hidden" name="diff_22" value="bus">	
				
				<td width="10%" class="info_content_01">牟平21点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_22" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_22">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>					  
				</select>
               </td>
				
			</tr>
			<tr>
			   <input type="hidden" name="eateryType_11" value="${bus}">	
			   <input type="hidden" name="foodType_11" value="${line_six}">	
			   <input type="hidden" name="diff_11" value="bus">	
				
				<td width="10%" class="info_content_01">牟平夜班</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_11" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_11">
				 <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>				  
				</select>
               </td>
				
			</tr>
			<tr>
			<input type="hidden" name="eateryType_7" value="${bus}">
			<input type="hidden" name="foodType_7" value="${line_two}">	
			 <input type="hidden" name="diff_7" value="bus">					
				<td width="10%" class="info_content_01">开发区17点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_7" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_7">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>							  
				</select>
               </td>
				
			</tr>
			<tr>
			<input type="hidden" name="eateryType_13" value="${bus}">
			<input type="hidden" name="foodType_13" value="${line_eight}">	
			 <input type="hidden" name="diff_13" value="bus">					
				<td width="10%" class="info_content_01">开发区20点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_13" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_13">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>								  
				</select>
               </td>
				
			</tr>
			<tr>
			<input type="hidden" name="eateryType_21" value="${bus}">
			<input type="hidden" name="foodType_21" value="${line_sixteen}">	
			 <input type="hidden" name="diff_21" value="bus">					
				<td width="10%" class="info_content_01">开发区21点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_21" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_21">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>							  
				</select>
               </td>
				
			</tr>
			<tr>
			<input type="hidden" name="eateryType_17" value="${bus}">
			<input type="hidden" name="foodType_17" value="${line_twelve}">	
			 <input type="hidden" name="diff_17" value="bus">					
				<td width="10%" class="info_content_01">开发区夜班</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_17" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_17">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>					  
				</select>
               </td>
				
			</tr>
			<tr>			
			<input type="hidden" name="eateryType_8" value="${bus}">	
			<input type="hidden" name="foodType_8" value="${line_three}">	
			<input type="hidden" name="diff_8" value="bus">
				<td width="10%" class="info_content_01">福山17点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_8" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_8">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>			  
				</select>
               </td>
				
			</tr>
			<tr>			
			<input type="hidden" name="eateryType_14" value="${bus}">	
			<input type="hidden" name="foodType_14" value="${line_nine}">	
			<input type="hidden" name="diff_14" value="bus">
				<td width="10%" class="info_content_01">福山20点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_14" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_14">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>				  
				</select>
               </td>
				
			</tr>
			<tr>			
			<input type="hidden" name="eateryType_23" value="${bus}">	
			<input type="hidden" name="foodType_23" value="${line_eighteen}">	
			<input type="hidden" name="diff_23" value="bus">
				<td width="10%" class="info_content_01">福山21点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_23" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_23">
				 <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>						  
				</select>
               </td>
				
			</tr>
			<tr>			
			<input type="hidden" name="eateryType_18" value="${bus}">	
			<input type="hidden" name="foodType_18" value="${line_thirteen}">	
			<input type="hidden" name="diff_18" value="bus">
				<td width="10%" class="info_content_01">福山夜班</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_18" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_18">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>				  
				</select>
               </td>
				
			</tr>
			
			<tr>		
			<input type="hidden" name="eateryType_9" value="${bus}">
			<input type="hidden" name="foodType_9" value="${line_four}">	
			<input type="hidden" name="diff_9" value="bus">			
				<td width="10%" class="info_content_01">莱山17点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_9" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_9">
				 <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>					 
				</select>
               </td>
				
			</tr>
			
			<tr>		
			<input type="hidden" name="eateryType_15" value="${bus}">
			<input type="hidden" name="foodType_15" value="${line_ten}">	
			<input type="hidden" name="diff_15" value="bus">			
				<td width="10%" class="info_content_01">莱山20点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_15" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_15">
				 <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>						 
				</select>
               </td>
				
			</tr>
			
			<tr>		
			<input type="hidden" name="eateryType_25" value="${bus}">
			<input type="hidden" name="foodType_25" value="${line_twenty}">	
			<input type="hidden" name="diff_25" value="bus">			
				<td width="10%" class="info_content_01">莱山21点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_25" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_25">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>					 
				</select>
               </td>
				
			</tr>
			
			<tr>		
			<input type="hidden" name="eateryType_19" value="${bus}">
			<input type="hidden" name="foodType_19" value="${line_fourteen}">	
			<input type="hidden" name="diff_19" value="bus">			
				<td width="10%" class="info_content_01">莱山夜班</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_19" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="10%" class="info_content_01">
				<select name="flag_19">
				  <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>					 
				</select>
               </td>
				
			</tr>
			
			<tr>	
			<input type="hidden" name="eateryType_10" value="${bus}">
			<input type="hidden" name="foodType_10" value="${line_five}">	
			<input type="hidden" name="diff_10" value="bus">				
				<td width="10%" class="info_content_01">芝罘17点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_10" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="20%" class="info_content_01">
				<select name="flag_10">
				<option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>					  
				</select>
               </td>
				
			</tr>	
			<tr>	
			<input type="hidden" name="eateryType_16" value="${bus}">
			<input type="hidden" name="foodType_16" value="${line_eleven}">	
			<input type="hidden" name="diff_16" value="bus">				
				<td width="10%" class="info_content_01">芝罘20点 </td>
				<td width="10%" class="info_content_01"><input type="text" name="num_16" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="20%" class="info_content_01">
				<select name="flag_16">
				<option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>					  
				</select>
               </td>
				
			</tr>
			<tr>	
			<input type="hidden" name="eateryType_24" value="${bus}">
			<input type="hidden" name="foodType_24" value="${line_nineteen}">	
			<input type="hidden" name="diff_24" value="bus">				
				<td width="10%" class="info_content_01">芝罘21点</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_24" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="20%" class="info_content_01">
				<select name="flag_24">
				 <option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>					  
				</select>
               </td>
				
			</tr>	
			<tr>	
			<input type="hidden" name="eateryType_20" value="${bus}">
			<input type="hidden" name="foodType_20" value="${line_fifteen}">	
			<input type="hidden" name="diff_20" value="bus">				
				<td width="10%" class="info_content_01">芝罘夜班</td>
				<td width="10%" class="info_content_01"><input type="text" name="num_20" value="0" style="width:40px" onkeyup='this.value=this.value.replace(/\D/gi,"")'></td>
				<td width="20%" class="info_content_01">
				<select name="flag_20">
				<option value="2">无班车</option>				  
				  <option value="3">45座1班</option>
				  <option value="9">45座2班</option>	
				  <option value="10">莱山区芝罘区合并45座</option>
				  <option value="14">莱山区芝罘区合并33座</option>
				  <option value="13">莱山区芝罘区合并23座</option>
				  <option value="4">33座</option>	
				  <option value="5">17座</option>	
				  <option value="6">ISTANA</option>	
				  <option value="7">开发区和福山合并45座</option>	
				  <option value="8">开发区和福山合并33座</option>
				  <option value="15">开发区和福山合并23座</option>
				  <option value="11">福山和莱山合并45座</option>	
				  <option value="12">福山和莱山合并33座</option>
				  <option value="16">福山和莱山合并23座</option>		  
				</select>
               </td>
				
			</tr>		
			<tr>
			    <td width="10%" class="info_title_01">菜谱</td>	
			    <td width="10%" class="info_content_01" colspan="2"><input type="text" name="startDate" style="width:70px" value="${startDate}" onClick="setday(this);" readonly="readonly"/>~	
			   <input type="text" name="endDate" style="width:70px" value="${endDate}" onClick="setday(this);" readonly="readonly"/>
			   </td>			
				<td><input type="file" name="jpgUpload" value="" style="width:200px" title="上传">&nbsp;&nbsp;&nbsp;</td>
			</tr>		
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
