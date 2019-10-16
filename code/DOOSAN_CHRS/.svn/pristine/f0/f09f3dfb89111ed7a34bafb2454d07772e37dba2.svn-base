<%@ page language="java" import="java.util.*,com.ait.hrm.business.* " pageEncoding="UTF-8"%>  
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="java.net.URLEncoder,com.ait.i18n.MessageSource,com.ait.sy.bean.AdminBean"%>

<html>
  <head>  
  <%@ include file="../inc/meta.jsp"%>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="../css/default.css" rel="stylesheet" type="text/css">  
    <title>report</title>         
                        
	<meta http-equiv="pragma" content="no-cache">   
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	function SearchItem(item,flag){
	if(item!=''){
		document.all.showsearch.style.visibility='visible';
		document.hr_itemList.location.href = "/hrm/hrItemForReports.jsp?item="+item+"&flag="+flag;
	} else {
		document.all.showsearch.style.visibility='hidden';
	}
}  
		function Report(){
			
			if(document.getElementById("rowselect").value==""){
			
				//"请选择横向列"
				alert('<ait:message  messageID="alert.emp.statistics.horizontal_search_criteria"  module="hrm"/>');
				return;
			}
			
			if(document.getElementById("colselect").value==""){
			
				//"请选择纵向列"
				alert('<ait:message  messageID="alert.emp.statistics.vertical_column"  module="hrm"/>');
				return;
			}
			if(document.getElementById("deptid").value==""){
			
				//"请选择部门"
				alert('<ait:message  messageID="alert.emp.statistics.select_department"  module="hrm"/>'); 
				return;
			}
			if(document.all.item_row.value == ""){document.all.item_row.value="all";}
			if(document.all.item_col.value == ""){document.all.item_col.value="all";}
			var url = "/hrm/report/hr_report.jsp?rowvalue="+document.getElementById("rowselect").value+
								   "&rowtitle="+rpt2.rowselect.options[rpt2.rowselect.selectedIndex].title+
								   "&colvalue="+document.getElementById("colselect").value+	
								   "&coltitle="+rpt2.colselect.options[rpt2.colselect.selectedIndex].title+
								   "&deptid="+document.getElementById("deptid").value+
								   "&rowtext="+URLEncode(rpt2.rowselect.options[rpt2.rowselect.selectedIndex].label)+
								   "&coltext="+URLEncode(rpt2.colselect.options[rpt2.colselect.selectedIndex].label)+       
								   "&rowItem="+document.all.item_row.value+                     
								   "&colItem="+document.all.item_col.value;              
			
			var par = "top=0, left=0, width="+screen.width+", height="+screen.height+", status=no, scrollbars=yes, resizable=no";
			window.open(url,"_blank",par);  	
		}  
		
function URLEncode(strURL)   
{ 
var strSpecialUrl = " <>\"#%{}|^~[]`'&?+";                             
var strEncode=""; 
var i, j, chUrl, iCode, iCodeBin, num; 
var tempBin; 
var leadingzeros; 

strURL+=""; 
for (i=0; i<strURL.length; i++) { 
chUrl = strURL.charAt(i); 
iCode = chUrl.charCodeAt(0); 
if (iCode<=parseInt("0x7F")) { 
if (strSpecialUrl.indexOf(chUrl)!=-1) { 
//chUrl is a special character that needs to be Url encoded 
strEncode+="%"+iCode.toString(16).toUpperCase(); 
} else { 
//otherwise chrUrl is normal 
strEncode+=chUrl; 
} 
} else { 
leadingzeros=""; 
iCodeBin=iCode.toString(2) 
if (iCode<=parseInt(0x7FF)) { 
//glyph is represented by two chars 

//check leading zeros on iCodeBin (should be 11 digits) 
for (j=11; j>iCodeBin.length; j--) leadingzeros+="0"; 
iCodeBin=leadingzeros+iCodeBin 

tempBin="110"+iCodeBin.substr(0,5); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(5,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
} else { 
if (iCode<=parseInt(0xFFFF)) { 
//glyph is represented by three chars 

//check leading zeros on iCodeBin (should be 16 digits) 
for (j=16; j>iCodeBin.length; j--) leadingzeros+="0"; 
iCodeBin=leadingzeros+iCodeBin 

tempBin="1110"+iCodeBin.substr(0,4); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(4,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(10,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
} else { 
if (iCode<=parseInt(0x1FFFFF)) { 
//glyph is represented by four chars 

//check leading zeros on iCodeBin (should be 21 digits) 
for (j=21; j>iCodeBin.length; j--) leadingzeros+="0"; 
iCodeBin=leadingzeros+iCodeBin 

tempBin="11110"+iCodeBin.substr(0,3); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(3,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(9,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(15,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
} else { 
if (iCode<=parseInt(0x3FFFFFF)) { 
//glyph is represented by five chars 

//check leading zeros on iCodeBin (should be 26 digits) 
for (j=26; j>iCodeBin.length; j--) leadingzeros+="0"; 
iCodeBin=leadingzeros+iCodeBin 

tempBin="111110"+iCodeBin.substr(0,2); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(2,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(8,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(14,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(20,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
} else { 
if (iCode<=parseInt(0x7FFFFFFF)) { 
//glyph is represented by six chars 

//check leading zeros on iCodeBin (should be 31 digits) 
for (j=31; j>iCodeBin.length; j--) leadingzeros+="0"; 
iCodeBin=leadingzeros+iCodeBin 

tempBin="1111110"+iCodeBin.substr(0,1); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(1,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(7,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(13,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(19,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
tempBin="10"+iCodeBin.substr(25,6); 
strEncode+="%"+parseInt(tempBin,2).toString(16).toUpperCase() 
} 
} 
} 
} 
} 
} 
} 
return strEncode; 
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
         	<c:import url="./inc/hrmstatisticstoolbar.jsp" /> 
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
  		<%@ include file="../inc/common_toolbar_n.jsp"%>                             
   <br>                            
  	 <%
  	    ReportServices rps=new ReportServices();
  		List dept_list =rps.getGrantDept(admin.getAdminID());      
  		String[] msg = new String[17];
  		HttpSession session1 = request.getSession(true);
  		AdminBean admin1 = (AdminBean) session.getAttribute("admin");
  		MessageSource messageSource1 = new MessageSource(admin.getLocale(), "UTF-8");
  		MessageSource messageSource2 = new MessageSource("hrm",admin.getLocale(), "UTF-8");
  		msg[0] = messageSource2.getMessage("display.emp.staff_info.basic_info.staff_type");
  		msg[1] = messageSource1.getMessage("display.mutual.staff_status");
  		msg[2] = messageSource1.getMessage("display.mutual.post_group");
  		msg[3] = messageSource1.getMessage("display.mutual.post_grade");
  		msg[4] = messageSource1.getMessage("display.mutual.post");
  		msg[5] = messageSource1.getMessage("display.mutual.position");
  		msg[6] = messageSource1.getMessage("display.mutual.sex");
  		msg[7] = messageSource1.getMessage("display.mutual.nationality");
  		msg[8] = messageSource1.getMessage("display.mutual.race");
  		msg[9] = messageSource1.getMessage("display.mutual.native_place");
  		msg[10] = messageSource1.getMessage("display.mutual.religion");
  		msg[11] = messageSource1.getMessage("display.mutual.personnel_file");
  		msg[12] = messageSource2.getMessage("display.emp.staff_info.basic_info.insurance");
  		msg[13] = messageSource1.getMessage("display.mutual.martial_status");  		
  		msg[14] = messageSource1.getMessage("display.mutual.diploma_2");
  		msg[15] = messageSource1.getMessage("display.mutual.political_status");  		
  		msg[16] = messageSource1.getMessage("display.mutual.hukou");
  	 %>  
  	  
    <form name="rpt2" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="30">
			<tr>
				<td>&nbsp;</td>
			</tr>  
			<tr>
				<td>&nbsp;</td>
			</tr>  <tr>
				<td>&nbsp;</td>
			</tr> 
   </table>    
	<input type="hidden" name="menu_code" value="<%=menu_code%>">  
		<table width="490" height="97" border="1" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"  height="40">
				<td align="center" class="info_title_01"><!-- 横向 -->
                    <ait:message  messageID="display.emp.statistic.total_info.horizontal" module="hrm"/>
                  </td>
				<td height="30" align="center" valign="middle" colspan="3">   
					 <select name="colselect" style="width:150;" onchange="SearchItem(this.value,1)">
					        <option value="">
					        <!-- 请选择 --><ait:message  messageID="display.emp.statistic.select"  module="hrm"/>
					        </option>
					        <option value="EmpDivision" title="EmpDivision" label="<%=URLEncoder.encode(msg[0],"UTF-8")%>">
					        <!-- 员工类别 --><ait:message  messageID="display.emp.staff_info.basic_info.staff_type"  module="hrm"/></option>
					        <option value="EmpStatus" title="EmpStatus" label="<%=URLEncoder.encode(msg[1],"UTF-8")%>">
					        <!-- 员工状态 --><ait:message  messageID="display.mutual.staff_status"/></option>
					        
					       <option value="post_group_id" title="post_group_id"  label="<%=URLEncoder.encode(msg[2],"UTF-8")%>">
					       <!-- 职群 --><ait:message  messageID="display.mutual.post_group"/></option>
					        <option value="post_grade_id" title="post_grade_id"  label="<%=URLEncoder.encode(msg[3],"UTF-8")%>">
					        <!-- 职级 --><ait:message  messageID="display.mutual.post_grade"/></option>
					        <option value="post_code" title="PostCode"  label="<%=URLEncoder.encode(msg[4],"UTF-8")%>">
					        <!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></option> 
					                   
					         <option value="PositionCode" title="PositionCode"  label="<%=URLEncoder.encode(msg[5],"UTF-8")%>">
					         <!-- 职位 --><ait:message  messageID="display.mutual.position"/></option>
					        <option value="SexCode" title="SexCode"  label="<%=URLEncoder.encode(msg[6],"UTF-8")%>"><!-- 性别 -->
						<ait:message  messageID="display.mutual.sex"/>
						</option>
			                <option value="NationalityCode" title="NationalityCode"  label="<%=URLEncoder.encode(msg[7],"UTF-8")%>"><!-- 国籍 -->
						<ait:message  messageID="display.mutual.nationality"/>	
						</option>
			                <option value="NationCode" title="NationCode"  label="<%=URLEncoder.encode(msg[8],"UTF-8")%>"><!-- 民族 -->
						<ait:message  messageID="display.mutual.race"/>	
						</option>
			                <option value="BornPlaceCode" title="BornPlaceCode"  label="<%=URLEncoder.encode(msg[9],"UTF-8")%>"><!-- 籍贯 -->
						<ait:message  messageID="display.mutual.native_place"/>	
						</option>
			                <option value="ReligionCode" title="ReligionCode"  label="<%=URLEncoder.encode(msg[10],"UTF-8")%>">
			                <!-- 宗教 --><ait:message  messageID="display.mutual.religion"/></option>
			                <option value="FileRelation" title="FileRelation"  label="<%=URLEncoder.encode(msg[11],"UTF-8")%>">
			                <!-- 档案关系 --><ait:message  messageID="display.mutual.personnel_file"/></option>
			                <option value="BaoxianType" title="BaoxianType"  label="<%=URLEncoder.encode(msg[12],"UTF-8")%>">
			                <!-- 保险交费类型 --><ait:message  messageID="display.emp.staff_info.basic_info.insurance" module="hrm"/></option>
					        <option value="MaritalStatusCode" title="MaritalStatusCode"  label="<%=URLEncoder.encode(msg[13],"UTF-8")%>"><!-- 婚姻状态 -->
							<ait:message  messageID="display.mutual.martial_status"/>
						</option>
					        <option value="DegreeCode" title="DegreeCode"  label="<%=URLEncoder.encode(msg[14],"UTF-8")%>">
					        <!-- 最终学历 --><ait:message  messageID="display.mutual.diploma_2"/></option>
					        <option value="PolityCode" title="PolityCode"  label="<%=URLEncoder.encode(msg[15],"UTF-8")%>"><!-- 政治面貌 -->
						<ait:message  messageID="display.mutual.political_status"/>	
						</option>  
					        <option value="RegTypeCode" title="RegTypeCode"  label="<%=URLEncoder.encode(msg[16],"UTF-8")%>">
					        <!-- 户口性质 --><ait:message  messageID="display.mutual.hukou"/></option>       
					 </select>	
				</td>
				
			</tr><input type="hidden" name="item_col" value="">
			<tr align="center"  height="40">          
				<td align="center" class="info_title_01"><!-- 纵向 -->
                    <ait:message  messageID="display.emp.statistic.total_info.vertical" module="hrm"/>
                    </td>
				  
				<td height="30" align="center" valign="middle" colspan="3">
					<div id="createEmp">
					<select name="rowselect" style="width:150;" onchange="SearchItem(this.value,0)">
					         <option value="">
					         <!-- 请选择 --><ait:message  messageID="display.emp.statistic.select"  module="hrm"/>
					         </option>
					        <option value="EmpDivision" title="EmpDivision" label="<%=URLEncoder.encode(msg[0],"UTF-8")%>">
					        <!-- 员工类别 --><ait:message  messageID="display.emp.staff_info.basic_info.staff_type"  module="hrm"/></option>
					        <option value="EmpStatus" title="EmpStatus"  label="<%=URLEncoder.encode(msg[1],"UTF-8")%>">
					        <!-- 员工状态 --><ait:message  messageID="display.mutual.staff_status"/></option>
					        
					        <option value="post_group_id" title="post_group_id"  label="<%=URLEncoder.encode(msg[2],"UTF-8")%>">
					       <!-- 职群 --><ait:message  messageID="display.mutual.post_group"/></option>
					        <option value="post_grade_id" title="post_grade_id"  label="<%=URLEncoder.encode(msg[3],"UTF-8")%>">
					        <!-- 职级 --><ait:message  messageID="display.mutual.post_grade"/></option>
					        <option value="post_code" title="PostCode"  label="<%=URLEncoder.encode(msg[4],"UTF-8")%>">
					        <!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></option> 
					                                   
					         <option value="PositionCode" title="PositionCode"  label="<%=URLEncoder.encode(msg[5],"UTF-8")%>">
					         <!-- 职位 --><ait:message  messageID="display.mutual.position"/></option>
					        <option value="SexCode" title="SexCode"  label="<%=URLEncoder.encode(msg[6],"UTF-8")%>"><!-- 性别 -->
						<ait:message  messageID="display.mutual.sex"/></option>
			                <option value="NationalityCode" title="NationalityCode"  label="<%=URLEncoder.encode(msg[7],"UTF-8")%>"><!-- 国籍 -->
						<ait:message  messageID="display.mutual.nationality"/>	</option>
			                <option value="NationCode" title="NationCode"  label="<%=URLEncoder.encode(msg[8],"UTF-8")%>"><!-- 民族 -->
						<ait:message  messageID="display.mutual.race"/>	</option>
			                <option value="BornPlaceCode" title="BornPlaceCode"  label="<%=URLEncoder.encode(msg[9],"UTF-8")%>"><!-- 籍贯 -->
						<ait:message  messageID="display.mutual.native_place"/>	</option>
			                <option value="ReligionCode" title="ReligionCode"  label="<%=URLEncoder.encode(msg[10],"UTF-8")%>">
			                <!-- 宗教 --><ait:message  messageID="display.mutual.religion"/></option>
			                <option value="FileRelation" title="FileRelation"  label="<%=URLEncoder.encode(msg[11],"UTF-8")%>">
			                <!-- 档案关系 --><ait:message  messageID="display.mutual.personnel_file"/></option>
			                <option value="BaoxianType" title="BaoxianType"  label="<%=URLEncoder.encode(msg[12],"UTF-8")%>">
			                <!-- 保险交费类型 --><ait:message  messageID="display.emp.staff_info.basic_info.insurance" module="hrm"/></option>
					        <option value="MaritalStatusCode" title="MaritalStatusCode"  label="<%=URLEncoder.encode(msg[13],"UTF-8")%>"><!-- 婚姻状态 -->
							<ait:message  messageID="display.mutual.martial_status"/></option>
					        <option value="DegreeCode" title="DegreeCode"  label="<%=URLEncoder.encode(msg[14],"UTF-8")%>">
					        <!-- 最终学历 --><ait:message  messageID="display.mutual.diploma_2"/></option>
					        <option value="PolityCode" title="PolityCode"  label="<%=URLEncoder.encode(msg[15],"UTF-8")%>"><!-- 政治面貌 -->
						<ait:message  messageID="display.mutual.political_status"/>	</option>  
					        <option value="RegTypeCode" title="RegTypeCode"  label="<%=URLEncoder.encode(msg[16],"UTF-8")%>">
					        <!-- 户口性质 --><ait:message  messageID="display.mutual.hukou"/></option>      
					 </select>	
					</div> 
				</td>
			</tr><input type="hidden" name="item_row" value=""> 
			<tr align="center"  height="40">                
				<td align="center" class="info_title_01"><!-- 部  门 -->
                    <ait:message  messageID="display.mutual.dept"/>
                    </td>   
				<td height="30" align="center" valign="middle" colspan="3">
				<!-- 	 <select name="deptid" style="width:150;">
					        <%
			   				//	out.println(HrmServices.createDeptTree(dept_list,request.getParameter("deptid"),"&nbsp;&nbsp;"));
							%>
					 </select>	 -->
					 <ait:selDept dataListName="dept_list" name="deptid"/>
				</td>
			</tr>
		</table>		
		 <table width="100%" border="0" cellspacing="0" cellpadding="0" height="30">
			<tr>
				<td>&nbsp;</td>
			</tr>  
			<tr>
				<td>&nbsp;</td>
			</tr>  <tr>
				<td>&nbsp;</td>
			</tr>  <tr>
				<td>&nbsp;</td>
			</tr>  <tr>
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
</form>
<div id='showsearch' style="position:absolute; top:70;left:500; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
		<iframe width="370" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0" name="hr_itemList" ></iframe>
	</div>
  </body>
</html>
