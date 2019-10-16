<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:include page="/jsp/inc/hrnav.jsp"/>
<!-- tool bar -->
<jsp:include page="/jsp/inc/hrtoolbar.jsp"/>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><TABLE width="100%">
        <TBODY>
          <TR>
            <TD ><IMG height=16 src="SY0106v.files/arrow.gif" 
            width=18> 系统维护 - 决裁者设置 - 查看</TD>
          </TR>
        </TBODY>
      </TABLE></td>
  </tr>
  <tr>
    <td height="20"><TABLE  borderColor=#a3acb5 cellSpacing=0 cellPadding=4 
      rules=all width="100%" border=1>
        <TBODY>
          <TR>
            <TD  align=middle>项目名称</TD>
            <TD align=middle>项目值</TD>
          </TR>
          <TR>
            <TD align=middle>决裁类型</TD>
            <TD><SELECT 
            onchange="location.href = '/SY/SY0206v.asp?DecObjectID=10000&amp;DecTypeID='+this.options[this.selectedIndex].value;" 
            name=DecTypeID>
                <OPTION value=0>请选择</OPTION>
                <OPTION 
              value=DecType001>加班决裁</OPTION>
                <OPTION value=DecType002 
              selected>休假决裁</OPTION>
                <OPTION 
            value=DecType003>个人信息决裁</OPTION>
              </SELECT></TD>
          </TR>
          <TR>
            <TD align=middle>（部门/人员）名称</TD>
            <TD>总经理
              <INPUT type=hidden value=10000 name=DecObjectID></TD>
          </TR>
        <FORM name=LPDBJForm action=/SY/SY0206s.asp method=post>
          <TR>
            <TD align=middle>决裁等级</TD>
            <TD><IFRAME name=DecEmp marginWidth=0 marginHeight=0 
            src="SY0106v.files/SY0206temv.htm" frameBorder=0 width=600 
            scrolling=yes 
  height=200></IFRAME></TD>
          </TR>
        </FORM>
      </TABLE></td>
  </tr>
</table>
