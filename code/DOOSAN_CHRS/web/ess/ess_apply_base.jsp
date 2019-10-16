<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hr.bean.*,com.ait.hr.entity.*,java.util.*,java.util.Vector,com.ait.util.*"%>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="department" class="com.ait.hr.com.ait.gm.DateBean.bean.Department"/>
<jsp:useBean id="personal" class="com.ait.hr.entity.PersonalInfo" scope="request"/>
<jsp:useBean id="basic" class="com.ait.hr.entity.BasicInfo" scope="request"/> <!-- basic information of the selected employee -->
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->
<jsp:useBean id="codemap" class="java.util.HashMap"/> 
<jsp:useBean id="hrentitydao" class="com.ait.hr.dao.HrEntityDAO"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>基本信息申请</title>
</head>
<body>
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<SCRIPT type="text/javascript">
<!--hidden
function Save(){
    document.save.submit();
}
 //-->
</SCRIPT>
<%
  basic = hrentitydao.getBasicInfo(admin.getAdminID());
  personal = hrentitydao.getPersonalInfo(admin.getAdminID());
%>
<form name="save" action="/EssContorlServlet" method="post">
	<input type="hidden" name="command" value="InfoApplyCommandImp"/>
	<input type="hidden" name=content value="InfoApplyBaseContentImp"/>
	<input type="hidden" name=empid value="<%=admin.getAdminID()%>"/>
<%@ include file="/ess/ess_view_basic_v.jsp" %>
<hr width="100%" align="left">


<span class="title1">个人信息</span>
  <br>

 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td class="line_gray_01" style="padding:1 1 1 1 ">
    <table width="100%" height="275"  border="0" cellpadding="0" cellspacing="0">
				    <% int membership = personal.getMembership();
					String member_status="";
					if(membership ==1)  member_status="是";
					else member_status="否";
				%>
	         <tr>
			    <td width="14%"  height="30"  class="info_title_01"><div align="center">姓&nbsp;&nbsp;名</div></td>
				<td width="16%" class="info_content_01"><%=personal.getChineseName()%>&nbsp;</td>
			    <td  height="30"  class="info_title_01"><div align="center">性别</div></td>
				<td class="info_content_01"><select name="sex_code">
                  <option value="">请选择性别</option>
                  <%
					try{
						Vector sex_vector = SysCodeParser.getCode("SexCode");
						String sex_code = personal.getSexCode();
						for ( int i=0; i < sex_vector.size(); i++)
						{
							codemap = (HashMap) sex_vector.elementAt(i);
			%>
                  <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(sex_code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                  <%	}
					}catch (Exception e){
					}
			%>
                </select></td>
			  <td width="13%"  height="30"  class="info_title_01"><div align="center">生&nbsp;&nbsp;日</div></td>
			  <td width="22%" class="info_content_01"><input type="text" name="dob" class="content" style="width:94px " value="<%=personal.getDOB()%>" ></td>
		 </tr>
		    <tr>
				<td height="1" colspan="6"></td>
			</tr>
		 <tr>
		      <td  height="30"  class="info_title_01"><div align="center">英文名</div></td>
			  <td class="info_content_01"><input name="english_name" type="text" size="15" maxlength="" value="<%=personal.getEnglishName()%>"/></td>
		      <td class="info_title_01"><div align="center">民&nbsp;&nbsp;族</div></td>
			  <td class="info_content_01"><select name="nation_code">
                  <option value="">请选择民族</option>
                  <%
					try{
						Vector nation_vector = SysCodeParser.getCode("NationCode");
						String nation_code = personal.getNationCode();
						for ( int i=0; i < nation_vector.size(); i++)
						{
							codemap = (HashMap) nation_vector.elementAt(i);
			%>
                  <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(nation_code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                  <%	}
					}catch (Exception e){
					}
			%>
                 </select></td>
			  <td  height="30"  class="info_title_01"><div align="center">政治面貌</div></td>
			  <td class="info_content_01"><select name="polity_code">
                  <option value="">请选择政治面貌</option>
                  <%
					try{
						Vector polity_vector = SysCodeParser.getCode("PolityCode");
						String polity_code = personal.getPolityCode();
						for ( int i=0; i < polity_vector.size(); i++)
						{
							codemap = (HashMap) polity_vector.elementAt(i);
			%>
                  <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(polity_code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                  <%	}
					}catch (Exception e){
					}
			%>
                </select></td>
	      </tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
		    <tr>
			   <td  height="30"  class="info_title_01"><div align="center">学&nbsp;&nbsp;历</div></td>
			   <td class="info_content_01"><select name="final_degree">
                <option value="">请选择学历</option>
                <%
					try{
						Vector vector = SysCodeParser.getCode("DegreeCode");
						String degree_code = personal.getFinalDegreeCode();
						for ( int i=0; i < vector.size(); i++)
						{
							codemap = (HashMap) vector.elementAt(i);
			%>
                <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(degree_code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                <%	}
					}catch (Exception e){
					}
			%>
              </select></td>
			  <td width="12%" class="info_title_01"><div align="center">婚姻状况</div></td>
			  <td width="23%" class="info_content_01"><select name="marital_code">
                  <option value="">请选择婚姻状况</option>
                  <%
					try{
						Vector marital_vector = SysCodeParser.getCode("MaritalStatusCode");
						String marital_code = personal.getMaritalStatusCode();
						for ( int i=0; i < marital_vector.size(); i++)
						{
							codemap = (HashMap) marital_vector.elementAt(i);
			%>
                  <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(marital_code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                  <%	}
					}catch (Exception e){
					}
			%>
                </select></td>
				<td  height="30"  class="info_title_01"><div align="center">籍&nbsp;&nbsp;贯</div></td>
				<td class="info_content_01"><select name="bornplace_code">
                  <option value="">请选择籍贯</option>
                  <%
					try{
						Vector bornplace_vector = SysCodeParser.getCode("BornPlaceCode");
						out.println("alert("+bornplace_vector.size()+")");
						String bornplace_code = personal.getBornPlaceCode();
						for ( int i=0; i < bornplace_vector.size(); i++)
						{
							codemap = (HashMap) bornplace_vector.elementAt(i);
			%>
                  <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(bornplace_code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                  <%	}
					}catch (Exception e){
					}
			%>
                </select></td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td class="info_title_01"><div align="center">移动电话</div></td>
				<td class="info_content_01"><input name="cell_phone" type="text" size="15" maxlength="" value="<%=personal.getCellPhone()%>"/>&nbsp;</td>
			    <td class="info_title_01"><div align="center">户口所在地</div></td>
				<td class="info_content_01"><input name="reg_place" type="text" size="15" maxlength="" value="<%=personal.getRegPlace()%>"/></td>
			    <td class="info_title_01"><div align="center">工会会员</div></td>
				<td class="info_content_01">				  <select name="membership">
                    <option value="0" <% if(personal.getMembership()==0){%> selected<%}%>>否</option>
                    <option value="1" <% if(personal.getMembership()==1){%> selected<%}%>>是</option>
                  </select></td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td class="info_title_01"><div align="center">家庭电话</div></td>
				<td class="info_content_01"><input name="home_phone" type="text" size="15" maxlength="" value="<%=personal.getHomePhone()%>"/></td>
			    <td class="info_title_01"><div align="center">户口性质</div></td>
				<td class="info_content_01"><select name="regtype_code">
                  <option value="">请选择户口性质</option>
                  <%
					try{
						Vector vector = SysCodeParser.getCode("RegTypeCode");
						String code = personal.getRegTypeCode();
						for ( int i=0; i < vector.size(); i++)
						{
							codemap = (HashMap) vector.elementAt(i);
			%>
                  <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                  <%	}
					}catch (Exception e){
					}
			%>
                </select></td>
			  <td  height="30"  class="info_title_01"><div align="center">爱&nbsp;&nbsp;好</div></td>
			  <td class="info_content_01"><input name="hobby" type="text" size="15" maxlength="" value="<%=personal.getHobby()%>"/></td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			   <td align="center" class="info_title_01"><div align="center">电子邮件</div></td>
			   <td class="info_content_01"><input name="email" type="text" size="15" maxlength="" value="<%=personal.getEmail()%>"/></td>
			   <td  height="30"  class="info_title_01"><div align="center">宗教信仰</div></td>
			   <td class="info_content_01"><select name="religion_code">
                  <option value="">请选择宗教信仰</option>
                  <%
					try{
						Vector religion_vector = SysCodeParser.getCode("ReligionCode");
						String religion_code = personal.getReligionCode();
						for ( int i=0; i < religion_vector.size(); i++)
						{
							codemap = (HashMap) religion_vector.elementAt(i);
			%>
                  <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(religion_code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                  <%	}
					}catch (Exception e){
					}
			%>
                </select>&nbsp;</td>
		        <td align="center" class="info_title_01"><div align="center">护照签发日</div></td>
			    <td class="info_content_01"><input type="text" name="visa_date" class="content" style="width:94px " value="<%=personal.getVisaDate()%>" >&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('save','visa_date');" style="cursor:hand"></td>

			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			<td width="14%" class="info_title_01"><div align="center">国&nbsp;&nbsp;籍</div></td>
			<td width="16%" class="info_content_01"><select name="nationality_code">
                  <option value="">请选择国籍</option>
                  <%
					try{
						Vector nationality_vector = SysCodeParser.getCode("NationalityCode");
						String nationality_code = personal.getNationalityCode();
						for ( int i=0; i < nationality_vector.size(); i++)
						{
							codemap = (HashMap) nationality_vector.elementAt(i);
			%>
                  <option value="<%=codemap.get("code")%>" <%if ((codemap.get("code")).equals(nationality_code)) {%>selected<%}%>><%=codemap.get("name")%></option>
                  <%	}
					}catch (Exception e){
					}
			%>
                </select></td>
				<td class="info_title_01"><div align="center">身份证号码</div></td>
			    <td class="info_content_01"><input name="id_card_no" type="text" size="15" maxlength="" value="<%=personal.getIdcardNo()%>"/></td>
                <td class="info_title_01"><div align="center">护照号</div></td>
			    <td class="info_content_01"><input name="passportno" type="text" size="15" maxlength="" value="<%=personal.getPassportNo()%>"/></td>
		    </tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td align="center" class="info_title_01"><div align="center">身份证地址</div></td>
			<td class="info_content_01" colspan="5"><input name="id_address" type="text" size="50" maxlength="" value="<%=personal.getIdcardAddress()%>"/>				</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
				 <td align="center" class="info_title_01"><div align="center">居住地址</div></td>
				 <td colspan="5" class="info_content_01"><input name="home_address" type="text" size="50" maxlength="" value="<%=personal.getHomeAddress()%>"/>&nbsp;</td>
	        </tr>
</table>
</td>
</tr>
 </table>
</form>
<br>
</body>
</html>
