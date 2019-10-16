<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.utils.*" errorPage="" %>
<%@ page import="com.ait.sy.bean.*"%>
<SCRIPT LANGUAGE="JavaScript" src="/js/evtable.js"></SCRIPT>
<%
String menu_code ="";
String rodeLevel ="";
menu_code = request.getParameter("menu_code");
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
MenuBiz menubiz = new MenuBiz();
ArrayList toolmenu =menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);
%>
<script language="JavaScript" type="" src="">
  function evModify() {
    if (ID==''){
        alert("请在列表中选择要修改的项目.");
    } else {
        window.open('evsdetailadd.jsp?EmpID=<%=empID%>&Action=modify&SeqDetail='+ID,'detailadd','toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,width=760,height=500,left=10,top=0');
    }
  }
function evSubmit() {
	if(confirm('确定提交？'))
	{
		if (CheckProp() == 100)
			location.href='evs0201_t.jsp?EmpID=<%=empID%>&year=<%=year%>&Action=submit';
		else{
			if(CheckProp()>100){
				alert("目标书比重超过 100%\n     请重新修改");
			}
			if(CheckProp()<100){
				alert("目标书比重未达到100%\n     请重新修改");
			}
		}
	}
}
  function evDelete() {
      if (ID=='') {
        alert("请在列表中选择要修改的项目.");
      } else {
        if(confirm('确定删除此项内容？')){
          location.href='evs0201_t.jsp?EmpID=<%=empID%>&Action=delete&SeqDetail='+ID;
        }
        return;
      }
    }
function evReject() {
    if(confirm('确定否决返回？')){
        location.href='evs0201_t.jsp?EmpID=<%=empID%>&year=<%=year%>&Action=reject';
    }
    return;
  }
</script>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td height="30" valign="middle">
                <table width="99%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                                <td align="right" valign="middle" style="padding:3 0 3 0 ">
<%
  if (operate.equals("EVOPERATE001")) {
%>
                                    <img name="addbtn" id="addbtn" src="/images/ev/add.gif" border="0" onclick="window.open('evsdetailadd.jsp?EmpID=<%=empID%>&year=<%=year%>&Action=add','detailadd','toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,width=760,height=400,left=10,top=0')" style="cursor:hand;visibility:"  alt="添加"/>

                                    <img name="moditybtn" id="moditybtn" src="/images/ev/modify.gif" border="0" onClick="evModify();" style="cursor:hand;visibility:"  alt="修改"/>

                                    <img name="deletebtn" id="deletebtn" src="/images/ev/delete.gif" border="0" onClick="evDelete();" style="cursor:hand;visibility:"  alt="删除"/>

                                    <img name="submitbtn" id="submitbtn" src="/images/ev/submit.gif" border="0" onClick="evSubmit();" style="cursor:hand;visibility:"  alt="同意/提交" />
<%
  }
  if (operate.equals("EVOPERATE002")) {
%>
                                    <img name="returnbtn" id="returnbtn" src="/images/ev/return.gif" border="0" onClick="evReject();" style="cursor:hand;visibility:"   alt="否决"/>
                                    <img name="submitbtn" id="submitbtn" src="/images/ev/submit.gif" border="0" onClick="evSubmit();" style="cursor:hand;visibility:"  alt="同意/提交" />
<%
  }
%>
                                </td>
                            </tr>
                        </table></td>
                    </tr>
                </table>
