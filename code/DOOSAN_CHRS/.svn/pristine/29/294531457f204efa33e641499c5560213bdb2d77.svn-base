<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:include page="/jsp/inc/hrnav.jsp"/>
<!-- tool bar -->
<jsp:include page="/jsp/inc/hrtoolbar.jsp"/>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><TABLE width="100%">
        <TBODY>
          <TR>
            <TD ><IMG height=16 src="SY0106m.files/arrow.gif" 
            width=18> 系统维护 - 决裁者设置 - 修改<!--  -->
<!--            <ait:message messageID=""></ait:message>-->
            </TD>                                                            
          </TR>
        </TBODY>
      </TABLE></td>
  </tr>
  <tr>
    <td height="20"><TABLE borderColor=#a3acb5 cellSpacing=0 cellPadding=4 
      rules=all width="100%" border=1>
        <TBODY>
          <TR>
            <TD  align=middle class="titlebg"><div align="left"><!--  -->项目名称
<!--            <ait:message messageID=""></ait:message>-->
            </div></TD>
            <TD  align=middle class="titlebg"><div align="left"><!--  -->项目值
<!--            <ait:message messageID=""></ait:message>-->
            </div></TD>
          </TR>                   
          <TR>
            <TD align=middle><!--  -->决裁类型
<!--            <ait:message messageID=""></ait:message>-->
            </TD>
            <TD><SELECT 
            onchange="location.href = '/SY/SY0206u.asp?DecObjectID=32402&amp;DecTypeID='+this.options[this.selectedIndex].value;" 
            name=DecTypeID>
                <OPTION value=0>select 
                </OPTION>
                <OPTION 
              value=DecType001 selected><!--  -->加班决裁
<!--              <ait:message messageID=""></ait:message>-->
              </OPTION>
                <OPTION 
              value=DecType002><!--  -->休假决裁
<!--              <ait:message messageID=""></ait:message>-->
              </OPTION>
                <OPTION 
              value=DecType003><!--  -->个人信息决裁
<!--              <ait:message messageID=""></ait:message>-->
              </OPTION>
              </SELECT></TD>
          </TR>
          <TR>
            <TD align=middle><!--  -->（部门/人员）名称
            
            </TD>
            <TD><!--  -->品质保证 GROUP
            
<!--            <ait:message messageID=""></ait:message>-->
              <INPUT type=hidden value=32402 name=DecObjectID></TD>
          </TR>
          <SCRIPT language=JavaScript type=text/JavaScript>
function change(info,Var1,Var2)
{
document.SearchEmpID.action="/sy/inc/DecEmpIDList.asp?info="+info+"&"+"DecTypeID="+Var1+"&"+"DecObjectID="+Var2;
document.SearchEmpID.submit();
if (info!="") 
{openm();}
else
{closem();}

}
function closem()
{
document.all.EmpIDList.style.display="none";
}
function openm()
{
document.all.EmpIDList.style.display="";
}
    </SCRIPT>
        <FORM name=SearchEmpID action="" method=post target=EmpIDList>
          <TR>
            <TD align=middle><!--  -->决裁者：
<!--            <ait:message messageID=""></ait:message>-->
            </TD>
            <TD><INPUT onkeyup="change(this.value,'DecType001','32402')" 
            name=EmpID1></TD>
          </TR>
        </FORM>
        <IFRAME class=ifram_EmpIDList id=EmpIDList 
        style="DISPLAY: none; LEFT: 300px; POSITION: absolute; TOP: 90px" 
        name=EmpIDList src="about:blank"></IFRAME>
        <FORM name=LPDBJForm action=/SY/SY0206s.asp method=post>
          <TR>
            <TD align=middle><!--  -->决裁等级
<!--            <ait:message messageID=""></ait:message>-->
            </TD>
            <TD><IFRAME name=DecEmp marginWidth=0 marginHeight=0 
            src="SY0106m.files/SY0206tem.htm" frameBorder=0 width=600 
            scrolling=yes 
  height=200></IFRAME></TD>
          </TR>
        </FORM>
      </TABLE></td>
  </tr>
</table>
