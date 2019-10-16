<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArItem"%>
<%@ page import="com.ait.ar.bean.ArShift010"%>
<%@ page import="com.ait.ar.bean.ArDetail"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="item" scope="page" class="com.ait.ar.dao.ArItemBean"></jsp:useBean>
<jsp:useBean id="shift" scope="page" class="com.ait.ar.dao.ArShift010Bean"></jsp:useBean>
<jsp:useBean id="ardetail" scope="page" class="com.ait.ar.dao.ArDetailBean"></jsp:useBean>
<%
ArrayList items=item.getItemList();
ArrayList shifts=shift.getShift010();
ArrayList ardetails=null;
ArDetail ar_detail=null;
ArDetail ar_temp=null;
ArShift010 ar_shift010=null;
ArItem ar_item=null;
String temp=null;
String empIDs="";
int addNo=0;
int year=0;
int month=0;
int day=0;
String sql=null;

  sql=request.getParameter("stmt");
  System.out.println(sql);
  ardetails=ardetail.getArDetail(sql, null, null);

%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function addrow(id,empid)
{

   document.all[id].style.display="";
   if(document.form1.flag.value==0)
   document.form1.empIDs.value+=empid+"-";

}

function del()
{
  if(!confirm("确定要删除吗?"))
  {
    return;
  }
  var empids="";
  for(var i=0;i<document.form1.elements.length;i++)
  {
    if(form1.elements[i].type=="checkbox")
    {
      if(form1.elements[i].name.indexOf("ck")!=-1)
      {
        if(form1.elements[i].checked==true)
        empids+=form1.elements[i].value+"-";
      }
    }
  }
  document.form1.action="delardetailservelt?pkNos="+empids;
  document.form1.submit();
}
</script>
</head>
<body>
<form name="form1" method="post" action="">
  <table width="100%"  border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
	<td colspan="8" height="50">&nbsp;
	</td>
	</tr>
	<tr>
      <td colspan="4">&nbsp;</td>
        <td colspan="2">
          <input type="button" value="保存"/>&nbsp;
          <input type="button" value="删除" onclick="del()"/>
        </td>
		<td colspan="3">&nbsp;</td>
	</tr>
    <tr align="center" bgcolor="#F5F5F5">
      <td width="11%">操作</td>
      <td width="11%">日期</td>
      <td width="8%">员工姓名</td>
      <td width="10%">项目<img src="../images/jj.gif" width="10" height="10" align="absmiddle" onClick="allrow()"></td>
      <td width="13%">开始时间</td>
      <td width="14%">结束时间</td>
      <td width="10%">班次</td>
      <td width="14%">是否锁定</td>
      <td width="20%">间隔时间</td>
    </tr>
    <%if(ardetails!=null){
    for(int i=0;i<ardetails.size();i++){
      ar_detail=(ArDetail)ardetails.get(i);
      if(i!=ardetails.size()-1){
      ar_temp=(ArDetail)ardetails.get(i+1);
      temp=ar_temp.getEmpID();//第2个
      }

      if(ar_detail.getEmpID().equals(temp)&&i!=ardetails.size()-1){
    %>
    <tr align="center">
      <td><input type="checkbox" name="ck<%=ar_detail.getEmpID()%>" value="<%=ar_detail.getPkNo()%>"/></td>
      <td><%=ar_detail.getAr_date_str()%></td>
      <td><%=ar_detail.getEmpID()%></td>
      <td><%=ar_detail.getItemName()%></td>
      <td><%=ar_detail.getFromTime()%></td>
      <td><%=ar_detail.getToTime()%></td>
      <td><%=ar_detail.getShiftName()%></td>
      <td><%=ar_detail.getLock()%></td>
      <td><%=ar_detail.getQuantity()%></td>
    </tr>
    <%
    }
    if(!ar_detail.getEmpID().equals(temp)){
      empIDs+=ar_detail.getEmpID()+"-";
      addNo++;
    %>
    <tr align="center">
      <td><input type="checkbox" name="ck<%=ar_detail.getEmpID()%>" value="<%=ar_detail.getPkNo()%>"/></td>
      <td><%=ar_detail.getAr_date_str()%></td>
      <td><%=ar_detail.getEmpID()%></td>
      <td><%=ar_detail.getItemName()%><img src="../images/jj.gif" width="10" height="10" align="absmiddle" onClick="addrow('add<%=addNo%>','<%=ar_detail.getEmpID()%>')"></td>
      <td><%=ar_detail.getFromTime()%></td>
      <td><%=ar_detail.getToTime()%></td>
      <td><%=ar_detail.getShiftName()%></td>
      <td><%=ar_detail.getLock()%></td>
      <td><%=ar_detail.getQuantity()%></td>
      </tr>

      <tr align="center" id="add<%=addNo%>" style="display:none ">
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><select name="itemNo<%=ar_detail.getEmpID()%>">
        <%for(int j=0;j<items.size();j++){
          ar_item=(ArItem)items.get(j);
        %>
        <option value="<%=ar_item.getItemNo()%>"><%=ar_item.getItemName()%></option>
        <%}%>
      </select></td>
      <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right"><input name="f_h<%=ar_detail.getEmpID()%>" type="text" size="2" maxlength="2"></td>
          <td align="center">:</td>
          <td><input name="f_m<%=ar_detail.getEmpID()%>" type="text"  size="2" maxlength="2"></td>
        </tr>
      </table></td>
      <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right"><input name="t_h<%=ar_detail.getEmpID()%>" type="text" size="2" maxlength="2"></td>
          <td align="center">:</td>
          <td><input name="t_m<%=ar_detail.getEmpID()%>" type="text"  size="2" maxlength="2"></td>
        </tr>
      </table></td>
      <td><select name="shiftNo<%=ar_detail.getEmpID()%>" >
        <%for(int j=0;j<shifts.size();j++){
          ar_shift010=(ArShift010)shifts.get(j);
        %>
        <option value="<%=ar_shift010.getShift_no()%>"><%=ar_shift010.getShift_Name()%></option>
        <%}%>
      </select></td>
      <td><select name="lock<%=ar_detail.getEmpID()%>" >
        <option value="Y">是</option>
        <option value="N">否</option>
      </select></td>
      <td><input name="qun<%=ar_detail.getEmpID()%>" type="text" size="2" maxlength="2"></td>
    </tr>
    <%}
    if(i==ardetails.size()-1){
      empIDs+=ar_detail.getEmpID()+"-";
      addNo++;
    %>
    <tr align="center">
      <td><input type="checkbox" name="ck<%=ar_detail.getEmpID()%>" value="<%=ar_detail.getPkNo()%>"/></td>
      <td><%=ar_detail.getAr_date_str()%></td>
      <td><%=ar_detail.getEmpID()%></td>
      <td><%=ar_detail.getItemName()%><img src="../images/jj.gif" width="10" height="10" align="absmiddle" onClick="addrow('add<%=addNo%>','<%=ar_detail.getEmpID()%>')"></td>
      <td><%=ar_detail.getFromTime()%></td>
      <td><%=ar_detail.getToTime()%></td>
      <td><%=ar_detail.getShiftName()%></td>
      <td><%=ar_detail.getLock()%></td>
      <td><%=ar_detail.getQuantity()%></td>
      </tr>

      <tr align="center" id="add<%=addNo%>" style="display:none ">
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><select name="itemNo<%=ar_detail.getEmpID()%>" >
        <%for(int j=0;j<items.size();j++){
          ar_item=(ArItem)items.get(j);
        %>
        <option value="<%=ar_item.getItemNo()%>"><%=ar_item.getItemName()%></option>
        <%}%>
      </select></td>
      <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right"><input name="f_h<%=ar_detail.getEmpID()%>" type="text" size="2" maxlength="2"></td>
          <td align="center">:</td>
          <td><input name="f_m<%=ar_detail.getEmpID()%>" type="text"  size="2" maxlength="2"></td>
        </tr>
      </table></td>
      <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right"><input name="t_h<%=ar_detail.getEmpID()%>" type="text" size="2" maxlength="2"></td>
          <td align="center">:</td>
          <td><input name="t_m<%=ar_detail.getEmpID()%>" type="text"  size="2" maxlength="2"></td>
        </tr>
      </table></td>
      <td><select name="shiftNo<%=ar_detail.getEmpID()%>" >
        <%for(int j=0;j<shifts.size();j++){
          ar_shift010=(ArShift010)shifts.get(j);
        %>
        <option value="<%=ar_shift010.getShift_no()%>"><%=ar_shift010.getShift_Name()%></option>
        <%}%>
      </select></td>
      <td><select name="lock<%=ar_detail.getEmpID()%>" >
        <option value="Y">是</option>
        <option value="N">否</option>
      </select></td>
      <td><input name="qun<%=ar_detail.getEmpID()%>" type="text" size="2" maxlength="2"></td>
    </tr>
    <%}
    }}%>
  </table>
<div id="addall" style='position:absolute; left:0px; top:0px; z-index:1; background-color: #FFFFEC; layer-background-color: #FFFFEC; border: 1px double #000000; overflow: hidden; display: "none";'>
<table width="500"  border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<tr align="center">
      <td><select name="itemNo" onChange="allItem()">
        <%for(int j=0;j<items.size();j++){
          ar_item=(ArItem)items.get(j);
        %>
        <option value="<%=ar_item.getItemNo()%>"><%=ar_item.getItemName()%></option>
        <%}%>
      </select></td>
      <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right"><input name="f_h" type="text" size="2" maxlength="2"></td>
          <td align="center">:</td>
          <td><input name="f_m" type="text"  size="2" maxlength="2"></td>
        </tr>
      </table></td>
      <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right"><input name="t_h" type="text" size="2" maxlength="2"></td>
          <td align="center">:</td>
          <td><input name="t_m" type="text"  size="2" maxlength="2"></td>
        </tr>
      </table></td>
      <td><select name="shiftNo" onChange="allShift()">
        <%for(int j=0;j<shifts.size();j++){
          ar_shift010=(ArShift010)shifts.get(j);
        %>
        <option value="<%=ar_shift010.getShift_no()%>"><%=ar_shift010.getShift_Name()%></option>
        <%}%>
      </select></td>
      <td><select name="lock" onChange="allLock()">
        <option value="Y">是</option>
        <option value="N">否</option>
      </select></td>
      <td><input name="qun" type="text" size="2" maxlength="2"></td>
    </tr>
	</table>
</div>
<input type="hidden" name="empIDs" value=""/>
<input type="hidden" name="flag" value="0"/>
<input type="hidden" name="hyear" value="<%=year%>"/>
<input type="hidden" name="hmonth" value="<%=month%>"/>
<input type="hidden" name="hday" value="<%=day%>"/>
<script language="javascript">
function allrow()
{
  document.form1.flag.value=1;
  var i=<%=addNo%>;

  i=parseInt(i)+parseInt(1);
  var id;
  for(var x=1;x<i;x++){
   id="add"+x;
   document.all[id].style.display="";
  }


  document.all.addall.style.display="none";
  document.form1.empIDs.value="<%=empIDs%>";

}
function allItem()
{
	var itemNo=document.form1.itemNo.value;
	for(var i=0;i<form1.elements.length;i++)
	{
		if(form1.elements[i].name.indexOf('itemNo')!=-1)
		{
			document.form1.elements[i].value=itemNo;
		}
	}
}
function allShift()
{
	 var shiftNo=document.form1.shiftNo.value;
	 for(var i=0;i<form1.elements.length;i++)
	{
		if(form1.elements[i].name.indexOf('shiftNo')!=-1)
		{
			document.form1.elements[i].value=shiftNo;
		}
	}
}

function allLock()
{
	var lock=document.form1.lock.value;
	for(var i=0;i<form1.elements.length;i++)
	{
		if(form1.elements[i].name.indexOf('lock')!=-1)
		{
			document.form1.elements[i].value=lock;
		}
	}
}
</script>
</form>
</body>
</html>
