<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<%@ page import="com.ait.utils.FormUtil,com.ait.ga.bean.*,com.ait.ga.cmd.visit.*,java.util.*"%>
<jsp:useBean id="voitureBean" class="com.ait.ga.bean.VoitureBean"/>
<jsp:useBean id="voitureResume" class="com.ait.ga.bean.VoitureBean"/>
<script language="javascript">

function Save(){ 
	 if(document.form1.DRIVER.value == ""){
	 	alert("司机不能为空！");
	 	return;
	 }
	 if(document.form1.IN_NAME.value == ""){
	 	alert("当事人姓名不能为空！");
	 	return;
	 }
	 if(document.form1.O_NAME.value == ""){
	 	alert("对方姓名不能为空！");
	 	return;
	 }
	   document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=voitureManger&content=UpdatesavevoitureManger";
	    document.form1.fireSubmit(); 
  
}

  function   CheckNumber(tempvalue)       {   
    var   patrn=/^[0-9]+.{0,1}[0-9]{0,3}$/;
    if  (!patrn.test(tempvalue)){   
       alert("输入数字或小数");   
       return  false;   
      }   
       return true; 
   }
    
function CheckNumberlength(){
 var text=document.form1.NOTE0.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>500){
 return true;
 }else{
 return false;
 }
}
function paseValueToAmount(value){   
    if(value!=null&&value!=''){   
        var decimalIndex=value.indexOf('.');   
        if(decimalIndex=='-1'){   
            return false;   
        }else{   
            var decimalPart=value.substring(decimalIndex+1,value.length);   
            if(decimalPart.length>2){   
                return true;   
            }else{   
                return false;   
            }   
        }   
    }   
    return false;   
 }  
function uploadImp(photosid,str){
window.open("/ga/ga_visiter_upload.jsp?documentno=TROUBLE"+photosid+str,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
}

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
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
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
		<input name="id" type="hidden" value="${id }">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆信息</td>
				</tr>
			</table>
			<table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	   

		  <tr>
		     <td class="info_title_01">司机</td>
			 <td class="info_content_00"><input type="text" name="DRIVER" value="${DRIVER }"> </td>
		     <td class="info_title_01">	乘车人数</td>
			 <td class="info_content_00"><input type="text" name="USERSCOUNT" value="${USERSCOUNT }" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
			 </tr>
		   <tr>
			 <td class="info_title_01"> 部门</td>
			 <td class="info_content_00"><ait:selDept name="DEPTNAME" supervisorType="hr" all="all" selected="${DEPTNAME}"/></td>		      
		      <td class="info_title_01">事故发生时间</td>
			 <td class="info_content_00">
			 <input type="text" name="START1" value="${START1 }" class="content" readonly onClick="setday(this);" style="width:70px">
			 <ait:time name="START2" spacing="1" selected="${START2 }"/>
			 </td>
			  </tr>
			  <tr>
		      <td class="info_title_01">事故发生地点</td>
			 <td class="info_content_00"><input type="text" name="SITE" value="${SITE }"></td>
		      <td class="info_title_01">事故原因</td>
			 <td class="info_content_00"><input type="text" name="CAUSE" value="${CAUSE }"></td>
			  </tr> 
			  <tr>
			  <td class="info_title_01">事故情况</td>
			 <td class="info_content_00"><input type="text" name="STATE" value="${STATE }" style="width: 400px"></td>
		      <td class="info_title_01">事故认定</td>
			 <td class="info_content_00"><input type="text" name="ESTABLISH" value="${ESTABLISH }" style="width: 400px"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01" colspan="2">当事人</td>
			 <td class="info_title_01" colspan="2">对方</td>
			  </tr>
			  <tr>
		      <td class="info_title_01">姓名</td>
			 <td class="info_content_00"><input type="text" name="IN_NAME" value="${IN_NAME }"></td>
		      <td class="info_title_01">姓名</td>
			 <td class="info_content_00"><input type="text" name="O_NAME" value="${IN_NAME }"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">性别</td>
			 <td class="info_content_00">
				 <select name="IN_SEX">
				 	<option value="0" <c:if test="${IN_SEX == '0' }"> selected="selected"</c:if> >男</option>
				 	<option value="1" <c:if test="${IN_SEX == '1' }"> selected="selected"</c:if>>女</option>
				 </select>
		      <td class="info_title_01">性别</td>
			 <td class="info_content_00">
			 <select name="O_SEX">
				 	<option value="0" <c:if test="${IN_SEX == '0' }"> selected="selected"</c:if> >男</option>
				 	<option value="1" <c:if test="${IN_SEX == '1' }"> selected="selected"</c:if> >女</option>
				 </select>
			  </tr>
			  <tr>
		      <td class="info_title_01">单位</td>
			 <td class="info_content_00"><input type="text" name="IN_JOB" value="${IN_JOB }"></td>
		      <td class="info_title_01">单位</td>
			 <td class="info_content_00"><input type="text" name="O_JOB" value="${O_JOB }"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">本人地址</td>
			 <td class="info_content_00"><input type="text" name="IN_ADDER" value="${IN_ADDER }"></td>
		      <td class="info_title_01">本人地址</td>
			 <td class="info_content_00"><input type="text" name="O_ADDER" value="${O_ADDER }"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">联络方式</td>
			 <td class="info_content_00"><input type="text" name="IN_DEL" value="${IN_DEL }"></td>
		      <td class="info_title_01">联络方式</td>
			 <td class="info_content_00"><input type="text" name="O_DEL" value="${O_DEL }"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">公司地址</td>
			 <td class="info_content_00"><input type="text" name="IN_COM" value="${IN_COM }"></td>
		      <td class="info_title_01">公司地址</td>
			 <td class="info_content_00"><input type="text" name="O_COM" value="${O_COM }"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">车种年份</td>
			 <td class="info_content_00"><input type="text" name="IN_YEAR" value="${IN_YEAR }"></td>
		      <td class="info_title_01">车种年份</td>
			 <td class="info_content_00"><input type="text" name="O_YEAR" value="${O_YEAR }"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">车牌号码</td>
			 <td class="info_content_00"><input type="text" name="IN_NUM" value="${IN_NUM }"></td>
		      <td class="info_title_01">车牌号码</td>
			 <td class="info_content_00"><input type="text" name="O_NUM" value="${O_NUM }"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">驾照号码</td>
			 <td class="info_content_00"><input type="text" name="IN_DRIVERNUM" value="${IN_DRIVERNUM }"></td>
		      <td class="info_title_01">驾照号码</td>
			 <td class="info_content_00"><input type="text" name="O_DRIVERNUM" value="${O_DRIVERNUM }"></td>
			  </tr>
			  <tr>
		      <td class="info_title_01">现场照片</td>
			 <td class="info_content_00">
			 <a href="#" onclick="ScenePhotos('${id}','IN_PHO')" style="color:red" title="上传图片">查看上传图片</a>
			 <a href="#" onclick="uploadImp('${id}','IN_PHO')" style="color:red" title="上传图片">上传图片</a>
		      <td class="info_title_01">现场照片</td>
			 <td class="info_content_00">
			 <a href="#" onclick="ScenePhotos('${id}','O_PHO')" style="color:red" title="上传图片">查看上传图片</a>
			 <a href="#" onclick="uploadImp('${id}','O_PHO')" style="color:red" title="上传图片">上传图片</a>
			  </tr>
			  <tr>
		      <td class="info_title_01">备注</td>
			 <td class="info_content_00"><input type="text" name="MOME" value="${MOME }"></td>
		      <td class="info_title_01">上转文件</td>
			 <td class="info_content_00">
			 <a href="#" onclick="ScenePhotos('${id}','FILE')" style="color:red" title="上传图片">查看扫描文件</a>
			 <a href="#" onclick="uploadImp('${id}','FILE')" style="color:red" title="上传图片">上传扫描文件</a>
			</td>
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
