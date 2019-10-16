<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.mando.sy.bean.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ page import="com.ait.sy.sy0103.bean.*"%>
<jsp:include page="/inc/hrnav.jsp"/>
<%@ include file="/inc/import.jsp"%>
<%
		Ent ent = new Ent();
		vlist = Biz.getLevelFirst();
%>
<script language="JavaScript1.2" type="text/JavaScript1.2">
<!--
	MENU_CODE='sy0103'
	function getDetailCodeList(para){
		parent.url;
		param_date.location.href = "sy_code_detail_view.jsp?parentCode="+para;
		ID = para;
	}
-->
</script>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="731" height="1" class="title_line_01"></td>
  </tr>
  <tr>
    <td height="2" class="title_line_02"></td>
  </tr>
  <tr>
    <td height="1"></td>
  </tr>
  <tr>
    <td class="line"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
        <tr>
          <td width="200" height="30" bgcolor="#F5F5F5" align="center">基本代码</td>
          <td height="30" bgcolor="#F5F5F5" align="center">明晰代码</td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="200"><select name="parentCode" size="20" style="width:200px; height:400px; border:0px; " onChange="getDetailCodeList(this.value)" class="check">
              <%
	for(int i = 0 ; i < vlist.size() ; i++ ){
				ent=(Ent)vlist.elementAt(i) ;
				String basicName=ent.getBasicName();
				int depth=ent.getDepth();
				for(int j=0;j<depth;j++){
					basicName="&nbsp;&nbsp;&nbsp;"+basicName;
				}
%>
              <option value="<%=ent.getBasicCode() %>">
              <%if(depth==0){out.println("<font color='red'>");}%>
              <%=basicName%></font></option>
              <%
	}
%>
            </select></td>
          <td ><iframe width="80%" height="400px" marginwidth="0" marginheight="0" frameborder="0" name="param_date"></iframe></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF"></td>
  </tr>
</table>
