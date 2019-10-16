<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.hrm.business.HrmServices" %>
<%@ page import="com.ait.hrm.bean.PersonalInfo" %>
<%@ page import="com.ait.hrm.bean.BasicInfo" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script language="javascript" src="../js/prototype/prototype.js"/></script>
<title>人事信息&gt;添加员工</title>
</head>
<SCRIPT type="text/javascript">
function Save(){
	document.form1.fireSubmit();
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

<%
String adminId=admin.getAdminID();
PersonalInfo personalInfo=(PersonalInfo)HrmServices.getInstance().retrievePersonalInfo(adminId);


request.setAttribute("personalInfo",personalInfo);

BasicInfo basic = (BasicInfo)HrmServices.getInstance().retrieveBasicInfo(adminId);
request.setAttribute("basic", basic);
%>

<form name="form1" method="post" action="/ess/ess0301_t.jsp">
<jodd:form bean="personalInfo" scope="request">
<input type="hidden" name="menu_code" value="ess0301">
<input type="hidden" name="createdBy" value="${admin.adminID}"/>
<input type="hidden" name="empID" value="${basic.empID}"/>
<input type="hidden" name="activity" value="0"/>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/esstoolbar_apply.jsp"%>
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
		<%@ include file="../ess/ess_view_basic.jsp"%>
		
		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--  信息申请-->
				 <ait:message  messageID="display.ess.review.personal_info.personal_info_request" module="ess"/>
				
				</td>
			</tr>
		</table>
		<table width="100%"　border="0" cellpadding="0" cellspacing="1" class="dr_d">
		          <tr>
		            <td height="30" class="info_title_01"><!-- 性别 --> 
		            <ait:message  messageID="display.mutual.sex"/>
		            </td>
		            <td class="info_content_00">
		            <ait:codeClass codeClass="SexCode" name="sexCode" selected="${personalInfo.sexCode}" all="all" />
		            </td>
		            <td height="30" class="info_title_01"> <!-- 民族 --> 
		            	<ait:message  messageID="display.mutual.race" module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <ait:codeClass codeClass="NationCode" name="nationCode" selected="${personalInfo.nationCode}" all="all" />
		            </td>
		            <td height="30" class="info_title_01"><!-- 最高学历 -->
		            <ait:message  messageID="display.mutual.diploma" module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <ait:codeClass codeClass="DegreeCode" name="beforeDegreeCode" selected="${personalInfo.beforeDegreeCode}" all="all" />
		            </td>
		          </tr>
		          <tr>
		            <td class="info_title_01"><!--国籍  -->  
		            <ait:message  messageID="display.mutual.nationality" module="ess"/>
		            </td>
		            <td class="info_content_00">
		            <ait:codeClass codeClass="NationalityCode" name="natioNalityCode" selected="${personalInfo.natioNalityCode}" all="all" />
		            </td>
		            <td height="30" class="info_title_01"> <!--  籍贯 -->
		            <ait:message  messageID="display.mutual.native_place" module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <ait:codeClass
							name="bornPlaceCode" codeClass="BornPlaceCode"
							selected="${personalInfo.bornPlaceCode}"   all="ALL" /> 
							<%---- 
		            <ait:codeClass codeClass="BornPlaceCode" name="PlaceCode" selected="${personalInfo.parentBornPlaceCode}" all="all"  onChange="updateBPC(this.name)"/>
		            <ait:codeClass codeClass="${personalInfo.parentBornPlaceCode}" name="bornPlaceCode" selected="${personalInfo.bornPlaceCode}" />
		                --%>         
		            </td>         
		           <td class="info_title_01"><!-- 婚姻状态 -->
		            <ait:message  messageID="display.mutual.martial_status"  module="ess"/>	
		           </td>
		            <td class="info_content_00">
		            <ait:codeClass codeClass="MaritalStatusCode" name="maritalStatusCode" selected="${personalInfo.maritalStatusCode}" all="all" />
		            </td>
		          </tr>
		          <tr >
		            <td class="info_title_01" height="30"> <!-- 家庭电话 --> 
		             <ait:message  messageID="display.sys.info_request.request.home_tel"  module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <input name="homePhone" type="text" size="15" maxlength=""  />
		            </td>
		            <td class="info_title_01"> <!--  移动电话-->
		             <ait:message  messageID="display.sys.info_request.request.mobile_phone"  module="ess"/>	
		             </td>
		            <td class="info_content_00">
		            <input name="cellPhone" type="text" size="15" maxlength=""  />            
		            </td>
		            <td height="30" class="info_title_01"><!-- 办公室 -->
		             <ait:message  messageID="display.sys.info_request.request.office_tel"  module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <input name="officePhone" type="text" size="15" maxlength=""  />
		            </td>
		          </tr>
		          <tr>
		            <td align="center" class="info_title_01"><!-- Email  --> 
		              <ait:message  messageID="display.mutual.e_mail"  module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <input name="email" type="text" size="15" maxlength=""  />            
		            </td>
		            <td class="info_title_01"> <!-- FAX -->
		              <ait:message  messageID="display.mutual.fax"  module="ess"/>	
		             </td>
		            <td class="info_content_00">
		            <input name="fax" type="text" size="15" maxlength=""  /></td>
		            <td align="center" class="info_title_01"><!--档案关系  -->
		              <ait:message  messageID="display.mutual.personnel_file"  module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <ait:codeClass codeClass="FileRelation" name="fileRelationCode" selected="${personalInfo.fileRelationCode}" all="all" />          
		            </td>
		          </tr>
<!--		          <tr>-->
<!--		            <td class="info_title_01">婚姻状态</td>-->
<!--		            <td class="info_content_00">-->
<!--		            <ait:codeClass codeClass="MaritalStatusCode" name="maritalStatusCode" selected="${maritalStatusCode}" all="all" />-->
<!--		            </td>-->
<!--		            <td class="info_title_01"></td>-->
<!--		            <td class="info_content_00">-->
<!--		            <td class="info_title_01"></td>-->
<!--		            <td class="info_content_00">-->
<!--		-->
<!--		            -->
<!--		            -->
<!--		             取消结婚纪念日、配偶工号 -->  
<!--		             -->
<!--		            <td class="info_title_01">结婚纪念日</td>-->
<!--		            <td class="info_content_00">-->
<!--		            <input name="weddingDate" type="text" size="15" maxlength=""  onClick="setday(this);"/>-->
<!--		            </td>-->
<!--		              <td class="info_title_01">配偶工号</td>-->
<!--		              <td class="info_content_00">-->
<!--		              <input name="mateEmpID" type="text" size="15" maxlength=""  />-->
<!--		              </td>           -->
<!--		                    -->
<!--		               -->
<!--		          </tr>-->
		          <tr>
		          	<td class="info_title_01"><!--  现住址-->
		          	  <ait:message  messageID="display.mutual.current_address"  module="ess"/>	
		          	</td>
		            <td class="info_content_00">
		            <input name="homeAddress" type="text" size="25" maxlength=""  />
		            </td>
		            
		            <td class="info_title_01"><!-- 身份证号码 --> 
		              <ait:message  messageID="display.mutual.id_no_2"  module="ess"/>	
		             </td>
		            <td class="info_content_00">
		            <input name="idcardNo" type="text" size="18" maxlength="" /></td>
		            <td class="info_title_01"><!-- 邮编 -->
		              <ait:message  messageID="display.mutual.native_place"  module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <input name="postalCode" type="text" size="15" maxlength=""  /></td>
		          </tr>
		          <tr>
		            <td class="info_title_01"> <!-- 户口所在地  -->
		             <ait:message  messageID="display.sys.info_request.request.seat_of_residence"  module="ess"/>	 
		            </td>
		            <td class="info_content_00">
		            <input name="regPlace" type="text" size="25" maxlength=""  />            
		            </td>
		            <td class="info_title_01"><!-- 户口性质  -->
		              <ait:message  messageID="display.mutual.hukou"  module="ess"/>	
		             </td>
		            <td class="info_content_00">
		            <ait:codeClass codeClass="RegTypeCode" name="regTypeCode" selected="${personalInfo.regTypeCode}" all="all" />
		            </td>
		            <td class="info_title_01"><!-- 政治面貌 -->
		           <ait:message  messageID="display.mutual.political_status" module="ess"/>	 
		            </td>
		            <td class="info_content_00">
					<ait:codeClass codeClass="PolityCode" name="polityCode" selected="${personalInfo.polityCode}" all="all" />
					</td>
		          </tr>
		          <tr>
		            <td class="info_title_01" height="30"><!--宗教  -->
		             <ait:message  messageID="display.mutual.religion"  module="ess"/>	 
		            </td>
		            <td class="info_content_00">
		             <ait:codeClass codeClass="ReligionCode" name="religionCode" selected="${personalInfo.religionCode}" all="all" />
		            </td>
		            <td class="info_title_01"><!-- 爱好/兴趣 -->
		              <ait:message  messageID="display.mutual.hobby"  module="ess"/>	
		            </td>
		            <td class="info_content_00">
		            <input name="hobby" type="text" size="15" maxlength=""  />
		            </td>
		            <td class="info_title_01"><!-- 特长 -->
		           <ait:message  messageID="display.mutual.skill"  module="ess"/>	   
		            </td>
		            <td class="info_content_00">
					<input name="speciality" type="text" size="15" maxlength=""  />
					</td>
		          </tr>
		        </table>
		        
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

</jodd:form>
</form>
</body>
<script src="../js/xjos.js" language="JavaScript"></script>
</html>
