<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<!-- hrm_dl_addemp.jsp -->
<%@ include file="../inc/meta.jsp"%>
<script language="javascript" src="../js/prototype/prototype.js" /></script>
<title>LSFC&gt;人事信息&gt;添加员工</title>
</head>
<SCRIPT type="text/javascript">
function Save(){
	document.form1.fireSubmit();                  
}
function checkEmpId(obj){
		var url = '/hrmControlServlet';
        var pars = 'operation=checkEmpId&menu_code=<c:out value='${toolbarInfo.menu_code}'/>&empId='+$F(obj);
        var myAjax = new Ajax.Updater(
                    {success: 'msg'},
                    url,
                    {method: 'post', parameters: pars,evalScripts:true});
}
function updateBPC(obj)
{
    var url = '/codeServlet';
    var pars = 'pc='+$F(obj);
   	var myAjax = new Ajax.Request(
                url,
                {method: 'get', parameters: pars, onComplete: showBornPlace}            
                );

}

function showBornPlace(originalRequest)
{
    //put returned XML to the select
    
    var sel=$("bornPlaceCode");
    sel.options.length=0;
      
    var xml = originalRequest.responseXML;
    var root=xml.documentElement;
    var responseNodes=root.getElementsByTagName("response");
    if(responseNodes.length>0){
    	var responseNode=responseNodes[0];
    	var itemNodes=responseNode.getElementsByTagName("item");
    	for(var i=0,j=itemNodes.length;i<j;i++){
    		var nameNodes=itemNodes[i].getElementsByTagName("name");
    		var valueNodes=itemNodes[i].getElementsByTagName("value");
    		if(nameNodes.length>0&&valueNodes.length>0){
	    		var option=new Option(nameNodes[0].firstChild.nodeValue,valueNodes[0].firstChild.nodeValue);
	    		sel.add(option);
    		}
    	}
    }
}
</SCRIPT>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<c:import url="inc/hr_dl_toolbar_a.jsp" /></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		
		<!-- display 3 level menu -->
		<c:import url="inc/hrm_dl_toolbar.jsp" /> 
		
		<!-- display content -->
		<br>
		<div id="msg" style="height: 10;"></div>
		<p align="left" width="50%"><span class="title1">
		<!-- 社内信息--><ait:message  messageID="display.emp.offer_regulation.recruit.internal_info" module="hrm"/></span></p>

		<table width="100%" 　border="0" cellpadding="0" cellspacing="1"
			class="dr_d">

			<form name="form1" method="post" action="hrmControlServlet"><jodd:form
				fromRequest="true">
				<input type="hidden" name="operation" value="insertHire">
				<input type="hidden" name="menu_code"
					value="<c:out value='${toolbarInfo.menu_code}'/>">
				<input type="hidden" name="createdBy" value="${admin.adminID}" />
				<tr>
					<td width="13%" height="30" align="center" class="info_title_01" nowrap="nowrap">
					<!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
					<td width="26%" height="30" align="left" class="info_content_00">
					<input name="chineseName" style="width:60px" id="chineseName"
						type="text" class="content" required focus_this
						onkeydown="ekeydown(event); " /> ( <input name="chinesePinyin"
						type="text" style="width:60px" class="content"
						onkeydown="ekeydown(event);" /> )</td>
					<td width="13%" height="30" align="center" class="info_title_01" nowrap="nowrap">
					<!--出生日期--><ait:message  messageID="display.mutual.birth_date"/></td>
					<td width="20%" align="left" class="info_content_00"><input
						type="text" name="dob" id="dob" readonly class="content"
						style="width:90;" onClick="setday(this);" required
						onkeydown="ekeydown(event);" /></td>
					<td width="13%" align="center" class="info_title_01" nowrap="nowrap">
					<!--入社日期--><ait:message  messageID="display.emp.staff_info.basic_info.hire_date" module="hrm"/></td>
					<td align="left" class="info_content_00"><input
						name="dateStarted" type="text" readonly class="content"  
						style="width:90;" onClick="setday(this);" required               
						onkeydown="ekeydown(event);" /></td>
						
	  				<td width="13%" align="center" class="info_title_01" nowrap="nowrap">
	  				<!-- 员工状态 --><ait:message  messageID="display.mutual.staff_status"/></td>
					<td width="22%" class="info_content_00"><ait:codeClass codeClass="EmpStatus" name="status" selected="${status}"/>
				　　　</td>	
				</tr>       
				<tr>
					<td align="center" height="30" class="info_title_01" nowrap="nowrap"><!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>     
					<td class="info_content_00" style="float:left"><input
						name="empID" id="empID" type="text" style="width:60px" required
						onblur="javaScript:checkEmpId(this.id)"   
						onkeydown="ekeydown(event);" onKeyUp="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="6"/></td>
                     <td class="info_title_01" style="float:left" nowrap="nowrap">
                     <!-- 员工类别 --><ait:message  messageID="display.emp.staff_info.basic_info.staff_type"  module="hrm"/></td>
					      <td height="30" align="left" class="info_content_00">
					<ait:codeClass codeClass="EmpDivision" name="joinTypeCode" selected="${joinTypeCode}" />
						</td>           
   
					<td align="center" class="info_title_01" nowrap="nowrap">
					<!-- 预转正日期 --><ait:message  messageID="display.emp.offer_regulation.recruit.internal_info.off_probation"  module="hrm"/></td>
					<td align="left" class="info_content_00"><input        
						name="endProbationDate" readonly type="text" class="content"
						style="width:90;" required onClick="setday(this);" /></td>

					<td height="30" align="center"  width="13%" class="info_title_01" nowrap="nowrap">
					<!-- 人事令号 --><ait:message  messageID="display.mutual.regulation_no"/></td>
					<td align="left" class="info_content_00"><input name="transNo"
						type="text" /></td>
				</tr>
				
				<tr>
					<td height="30" align="center" class="info_title_01" nowrap="nowrap">
					<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
					<td height="30" align="left" class="info_content_00"><ait:selDept
						dataListName="dept_list" name="deptID" /></td>

					<td align="center" class="info_title_01" nowrap="nowrap">
					<!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
					<td align="left" class="info_content_00"><ait:codeClass
						codeClass="PositionCode" name="positionCode"
						selected="${positionCode}"  /></td>

					<td align="center" class="info_title_01" nowrap="nowrap">
					<!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
					<td align="left" class="info_content_00"><ait:select
						dataListName="post_list" name="postCode" selected="${postCode}"
						disabled="false"  /></td>
						
				  <td align="center" class="info_title_01" nowrap="nowrap">
				  <!-- 职级 --><ait:message  messageID="display.mutual.post_grade"/></td>              
					<td align="left" class="info_content_00"><ait:select
						dataListName="post_grade_list" name="postGrade" selected="${postGrade}"
						disabled="false"  /></td>  
				 </tr>  
				 
				 <tr>
				 <td height="30" align="center" class="info_title_01" nowrap="nowrap">
				<!-- 级号 --><ait:message  messageID="display.mutual.pay_grade"/></td>     
				    <td align="left" class="info_content_00"><ait:select
						dataListName="post_grade_level_list" name="postCoef" selected="${postCoef}"   
						disabled="false" all="all"/></td>       
				 <td height="30" align="center" class="info_title_01"></td>
				 <td align="left" class="info_content_00"></td>
				   <td height="30" align="center" class="info_title_01"></td>
				   <td align="left" class="info_content_00"></td>
				  <td height="30" align="center" class="info_title_01"></td>
				  <td align="left" class="info_content_00"></td>
				</tr>   

		</table>
		<p align="left"><span class="title1"><!-- 个人信息 -->
						<ait:message  messageID="display.emp.offer_regulation.recruit.personal_info" module="hrm"/>							   
					   </span></p>
		<table width="100%" 　border="0" cellpadding="0" cellspacing="1"
			class="dr_d">
			<tr>
				<td width="13%" height="30" class="info_title_01"><!-- 性别 -->
						<ait:message  messageID="display.mutual.sex"/>
						</td>
				<td width="22%" class="info_content_00"><ait:codeClass
					codeClass="SexCode" name="sexCode" selected="${sexCode}"  />
				</td>
				<td width="13%" height="30" class="info_title_01"><!-- 民族 -->
						<ait:message  messageID="display.mutual.race"/>	
						</td>
				<td width="20%" class="info_content_00"><ait:codeClass
					codeClass="NationCode" name="nationCode" selected="${nationCode}"
					all="all" /></td>
				<td width="13%" height="30" class="info_title_01"><!-- 最高学历 -->
						<ait:message  messageID="display.mutual.diploma"/>						
						</td>
				<td class="info_content_00"><ait:codeClass
					codeClass="DegreeCode" name="beforeDegreeCode"
					selected="${beforeDegreeCode}" all="all" /></td>
			</tr>
			<tr>
				<td class="info_title_01"><!-- 国籍 -->
						<ait:message  messageID="display.mutual.nationality"/>	
						</td>
				<td class="info_content_00"><ait:codeClass
					codeClass="NationalityCode" name="natioNalityCode"
					selected="${natioNalityCode}"  /></td>
				<td height="30" class="info_title_01"><!-- 籍贯 -->
						<ait:message  messageID="display.mutual.native_place"/>	
						</td>
				<td class="info_content_00"><ait:codeClass
					codeClass="BornPlaceCode" name="bornPlaceCode"
					selected="${bornPlaceCode}" all="all" />                
<!--					<select name="bornPlaceCode" id="bornPlaceCode" >-->
					<option value=""></option>                       
				</select></td>
				<td class="info_title_01"><!-- 保险缴费类型 -->
						<ait:message  messageID="display.emp.staff_info.basic_info.insurance" module="hrm"/>							   
					   </td>                                      
				<td class="info_content_00"><ait:codeClass
					codeClass="BaoxianType" name="work_area"
					selected="${workArea}"  /></td>           
			</tr>
			<tr>
				<td class="info_title_01" height="30"><!-- 家庭电话 -->
							<ait:message  messageID="display.emp.offer_regulation.recruit.home_phone" module="hrm"/>
						</td>
				<td class="info_content_00"><input name="homePhone" type="text"
					size="15" maxlength="" /></td>
				<td class="info_title_01"><!-- 移动电话 -->
							<ait:message  messageID="display.emp.offer_regulation.recruit.cellular_phone" module="hrm"/>
						</td>
				<td class="info_content_00"><input name="cellPhone" type="text"
					size="15" maxlength="" /></td>
				<td height="30" class="info_title_01"><!-- 办公电话 -->
							<ait:message  messageID="display.emp.offer_regulation.recruit.work_phone" module="hrm"/>
						</td>
				<td class="info_content_00"><input name="officePhone"
					type="text" size="15" maxlength="" /></td>
			</tr>
			<tr>
				<td align="center" class="info_title_01"><!-- e-Mail -->
							<ait:message  messageID="display.mutual.e_mail"/>
						</td>
				<td class="info_content_00"><input name="email" type="text"
					size="15" maxlength="" /></td>
				<td class="info_title_01"><!-- Fax -->
							<ait:message  messageID="display.mutual.fax"/>
						</td>
				<td class="info_content_00"><input name="fax" type="text"
					size="15" maxlength="" /></td>
				<td align="center" class="info_title_01"><!-- 档案关系 -->
							<ait:message  messageID="display.mutual.personnel_file"/>
						</td>
				<td class="info_content_00"><ait:codeClass
					codeClass="FileRelation" name="fileRelationCode"
					selected="${fileRelationCode}" all="all" /></td>
			</tr>
			<tr>
				<td class="info_title_01"><!-- 婚姻状态 -->
							<ait:message  messageID="display.mutual.martial_status"/>
						</td>
				<td class="info_content_00"><ait:codeClass
					codeClass="MaritalStatusCode" name="maritalStatusCode"
					selected="${maritalStatusCode}" all="all" /></td>
				<td align="center" class="info_title_01"></td>
				<td align="left" class="info_content_01">
				<td align="center" class="info_title_01"></td>
				<td align="left" class="info_content_01"> 
			</tr>
			<tr>
				<td class="info_title_01"><!-- 现住址 -->
							<ait:message  messageID="display.mutual.current_address"/>
						</td>
				<td class="info_content_00"><input name="homeAddress"
					type="text" size="25" maxlength="" /></td>

				<td class="info_title_01"><!-- 身份证号码 -->
							<ait:message  messageID="display.mutual.id_no_2"/>
						</td>
				<td class="info_content_00"><input name="idcardNo" type="text"
					size="18" maxlength=""  required /></td>
				<td class="info_title_01"><!-- 邮编 -->
							<ait:message  messageID="display.mutual.postal_code"/>
						</td>
				<td class="info_content_00"><input name="postalCode"
					type="text" size="15" maxlength="" /></td>
			</tr>
			<tr>
				<td class="info_title_01"><!-- 户口所在地 -->
							<ait:message  messageID="display.emp.offer_regulation.recruit.personal_info.seat_of_residence" module="hrm"/>
						</td>
				<td class="info_content_00"><input name="regPlace" type="text"
					size="25" maxlength="" /></td>
				<td class="info_title_01"><!-- 户口性质 -->
							<ait:message  messageID="display.mutual.hukou"/>
						</td>
				<td class="info_content_00"><ait:codeClass
					codeClass="RegTypeCode" name="regTypeCode"
					selected="${regTypeCode}" all="all" /></td>
				<td class="info_title_01"><!-- 政治面貌 -->
						<ait:message  messageID="display.mutual.political_status"/>	
						</td>
				<td class="info_content_00"><ait:codeClass
					codeClass="PolityCode" name="polityCode" selected="${polityCode}"
					all="all" /></td>
			</tr>
			<tr>
				<td class="info_title_01" height="30"><!-- 宗教 -->
							<ait:message  messageID="display.mutual.religion"/>
						</td>
				<td class="info_content_00"><ait:codeClass
					codeClass="ReligionCode" name="religionCode"
					selected="${religionCode}"  /></td>
				<td class="info_title_01"><!-- 爱好/兴趣 -->
							<ait:message  messageID="display.mutual.hobby"/>
						</td>
				<td class="info_content_00"><input name="hobby" type="text"
					size="15" maxlength="" /></td>
				<td class="info_title_01"><!-- 特长 -->
							<ait:message  messageID="display.mutual.skill"/>
						</td>
				<td class="info_content_00"><input name="speciality"
					type="text" size="15" maxlength="" /></td>
			</tr>
		</table>

		<p align="left"><span class="title1"><!-- 合同信息 -->
								<ait:message  messageID="display.mutual.contract_info"/>
							</span></p>
		<table width="100%" border="0" cellpadding="0" cellspacing="1"
			class="dr_d">
			<tr>
				<td height="30" class="info_title_01"><!-- 合同类型 -->
				<ait:message  messageID="display.mutual.contract_type"/>
				</td>
				<td class="info_title_01"><!-- 开始日 -->
							<ait:message  messageID="display.mutual.start_date_3"/>
						</td>
				<td class="info_title_01"><!-- 结束日 -->
							<ait:message  messageID="display.mutual.end_date_3"/>
						</td>
				<td class="info_title_01"><!-- 合同再签与否 -->
							<ait:message  messageID="display.emp.offer_regulation.contract_info.extend_or_not" module="hrm"/>
						</td>
			</tr>
			<tr>
				<td class="info_content_01" height="30"><ait:codeClass
					codeClass="ContractTypeCode" name="contractTypeCode"
					selected="${contractTypeCode}" /></td>
				<td class="info_content_01"><input type="text"
					name="contractStartDate" class="content" style="width:90;"
					onClick="setday(this);" /></td>
				<td class="info_content_01"><input type="text"
					name="contractEndDate" class="content" style="width:90;"
					onClick="setday(this);" /></td>
				<td class="info_content_01"><ait:codeClass
					codeClass="ContractStatusCode" name="contractContCode"
					selected="${contractContCode}" all="all" /></td>

			</tr>
			</jodd:form>
			</form>
		</table>



		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="30">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</body>
<ait:xjos></ait:xjos>    
</html>
