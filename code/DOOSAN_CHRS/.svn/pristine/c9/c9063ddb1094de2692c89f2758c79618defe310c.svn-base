<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">


function Save(){ 
	
	if(confirm('确认信息无误时，再保存！')){
        document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=expressManger&content=expressInstallSave";
        document.form1.submit(); 	
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
		<input type="hidden" name="temp" value="0">
			<table width="85%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">快件资费标准</font></td>
				</tr>
			</table>
		  <table width="85%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	    
		    <tr>
		      <td class="info_title_01">序号</td>
			 <td class="info_content_00"><input name="number" type="text" style="width:120px "></td>
			 </tr>
		  <tr>
		     <td class="info_title_01">发件城市</td>
			 <td class="info_content_00"> <input name="give_city" type="text" style="width:120px "></td>
			 </tr>
		  <tr>
			 <td class="info_title_01">寄达城市</td>
			 <td class="info_content_00"><input name="arrive_city" type="text" style="width:120px "></td>		      
		    </tr>
		    <tr>
			 <td class="info_title_01">邮寄费</td>
			 <td class="info_content_00"><input name="email_expenses" type="text" style="width:120px " onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">&nbsp;元</td>		      
		    </tr>
		    <tr>
			 <td class="info_title_01">快件类型</td>
			 <td class="info_content_00">
			 <ait:codeClass codeClass="ExpressType" name="expenses_type"></ait:codeClass>
			 </td>		      
		    </tr>
		  </tr>	
		  </table>
		</form>
		<br />
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
