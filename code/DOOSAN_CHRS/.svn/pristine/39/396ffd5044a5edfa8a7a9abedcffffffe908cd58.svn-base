<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*"%>
<%@ page import="com.ait.utils.EntEmpTree"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/Style.css" rel="stylesheet" type="text/css">

</head>
<style  type="text/css">
img.hand{cursor:hand;}
</style>
<body topmargin="0" marginheight="0" >
<table  width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
	<td valign="top" >
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<form name="emptree">
		<tr>
		<td class="line">
  <input name='上传' value="选择" onclick=" send()" class="content" type="button" style="width:50px ">
    &nbsp;
  <input name="queryname" type="text" value="--请输入查找人的姓名--" onfocus="javascript:this.value=''"/>
    &nbsp;
  <input type="button" name="butquery" value="查询"  onclick="query()"/>

  <table id="mytable" name="mytable" width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr id="q1">
			<td height="5" colspan="6"></td>
			</tr><%
			String deptid="";
			EntEmpTree entEmpTree = new EntEmpTree();
			Vector vlist = new Vector();
			vlist = entEmpTree.getDeptSingleTree();
			for ( int i = 0 ; i < vlist.size() ; i++ )
			{
				entEmpTree=(EntEmpTree)vlist.elementAt(i) ;
				if (entEmpTree.getDeptLevel()==0) {
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr id="q2">
					<td width="12" height="22"><img src="../images/left_menubullet_main_m.gif" width="14" height="22" class='hand'  onClick="show('<%=entEmpTree.getDeptID()%>_0')"></td>
					<td colspan="5" class="left_menu_1depth"><b>&nbsp;
					<%deptid = entEmpTree.getDeptID();
					out.print(entEmpTree.getDeptName());
					%></b></td>
				</tr>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_0" style="display:none ">
					<td width="12" height="22">&nbsp;</td>
					<td colspan="5" class="left_menu_1depth">&nbsp;
                                          <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
                                          <input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
                                            <input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr><%}}else{
				if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_0" style="display:none ">
					<td width="12" height="22">&nbsp;</td>
					<td colspan="5" class="left_menu_1depth">&nbsp;
                                          <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
                                          <input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
                                            <input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr><%}}}else if (entEmpTree.getDeptLevel()==1){
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr id="q3">
				<td width="12" height="22">&nbsp;	</td>
				<td width="30" align="left" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node_02.gif" width="14" height="22"><img src="../images/left_menubullet_main_m.gif" width="14" height="22" class='hand' onClick="show('<%=entEmpTree.getDeptID()%>_1')"></td>
				<td colspan="4" class="left_menu_1depth"><b>&nbsp;
				<%deptid = entEmpTree.getDeptID();
				out.print(entEmpTree.getDeptName());
				%>
				</b></td>
				</tr><%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_1" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td colspan="4" class="left_menu_1depth">
                                  <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
				<input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
				<input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr><%}}else{%>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_1" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td colspan="4" class="left_menu_1depth">
                                  <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
				<input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
				<input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr><%}}}else if (entEmpTree.getDeptLevel()==2){
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr id="q4">
				<td width="12" height="22">&nbsp;</td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" align="left"><img src="../images/left_menubullet_sub_node_02.gif" width="14" height="22">
                                  <span class="left_menu_1depth"><b>
                                    <img src="../images/left_menubullet_main_m.gif" width="14" height="22" class='hand' onClick="show('<%=entEmpTree.getDeptID()%>_2')"></b></span></td>
				<td colspan="3" class="left_menu">
                                  <span class="left_menu_1depth"><b>&nbsp;
				<%deptid = entEmpTree.getDeptID();
				out.print(entEmpTree.getDeptName());
				%>
				</b></span></td>
				</tr><%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_2" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td colspan="3" class="left_menu_1depth">
                                  <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
				<input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
				<input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr><%}}else{%>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_2" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td colspan="3" class="left_menu_1depth">
                                  <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
				<input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
				<input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr>
				<%}}}else  if (entEmpTree.getDeptLevel()==3){
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr id="q5">
				<td width="12" height="22"></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu"><img src="../images/left_menubullet_sub_node_02.gif" width="14" height="22"><img src="../images/left_menubullet_main_m.gif" width="14" height="22" class='hand' onClick="show('<%=entEmpTree.getDeptID()%>_3')"></td>
				<td colspan="2" valign="middle" class="left_menu"><span class="left_menu_1depth"><b>&nbsp;
				<%deptid = entEmpTree.getDeptID();
				out.print(entEmpTree.getDeptName());
				%>
				</b></span></td>
				</tr><%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_3" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td valign="middle" class="left_menu_1depth" colspan="2" >
                                  <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
				<input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
				<input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr><%}}else{%>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_3" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td valign="middle" class="left_menu_1depth" colspan="2" >
                                  <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
				<input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
				<input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr>
				<%}}}else{
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr id="q6">
				<td width="12" height="22">&nbsp;</td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td><span class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></span></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu"><img src="../images/left_menubullet_sub_node_02.gif" width="14" height="22"><img src="../images/left_menubullet_main_m.gif" width="14" height="22" onClick="show('<%=entEmpTree.getDeptID()%>_4')"></td>
				<td valign="middle" class="left_menu"><span class="left_menu_1depth"><b>&nbsp;
				<%deptid = entEmpTree.getDeptID();
				out.print(entEmpTree.getDeptName());
				%></b></span></td>
				</tr><%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_4" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td><span class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></span></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td class="left_menu_1depth">&nbsp;</td>
				<td valign="middle" class="left_menu_1depth">
                                  <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
				<input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
				<input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr><%}}else{%>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_4" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td><span class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></span></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td class="left_menu_1depth">&nbsp;</td>
				<td valign="middle" class="left_menu_1depth">
                                  <a name="<%=entEmpTree.getChineseName()%>"><%=entEmpTree.getChineseName()%></a>
				<input type="hidden" value="<%=entEmpTree.getChineseName()%>" name = "empname">
				<input type="checkbox" value="<%=entEmpTree.getEmpID()%>" name = "empid"> </td>
				</tr><%}}}}%>
			</table>
		</td>
		</tr>
		</form>
	</table>
	<span class="line">
	<input  value="选择" onclick=" send()" class="content" type="button" style="width:50px ">
	</span></td>
</tr>
</table>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--

//去掉字串左边的空格
function lTrim(str)
{
if (str.charAt(0) == " ")
{
//如果字串左边第一个字符为空格
str = str.slice(1);//将空格从字串中去掉
//这一句也可改成 str = str.substring(1, str.length);
str = lTrim(str); //递归调用
}
return str;
}


//去掉字串右边的空格
function rTrim(str)
{
var iLength;

iLength = str.length;
if (str.charAt(iLength - 1) == " ")
{
//如果字串右边第一个字符为空格
str = str.slice(0, iLength - 1);//将空格从字串中去掉
//这一句也可改成 str = str.substring(0, iLength - 1);
str = rTrim(str); //递归调用
}
return str;
}


//去掉字串两边的空格
function trim(str)
{
return lTrim(rTrim(str));
}

function query(){
  var s=document.emptree.queryname.value;
  s=trim(s);
  if(s!=""){
       var o=eval("document.all."+s);
       if(o==null){
         alert("找不到此人。");
         return;
         }else{
           showquery(s);
           if(o.style!=null){
           o.style.color='white';
           o.style.background='blue'
           }
           window.location="#"+s;
           }
 }
}


function showquery(s){

  	for (i=0; i < document.all.mytable.rows.length; i++) {
            var o=document.all.mytable.rows[i].all(s);
            if(o!=null){
              target = document.all(document.all.mytable.rows[i].id);
              if(target.length==null)
              {
              		target.style.display='';
              }else
              {
                             for (i=0;i<target.length;i++){
					 if (target[i].style.display=='none'){
                                          target[i].style.display='';
                                         }
				}
            }

              return;
             }
  	}
  }

function show(id)
{
	try {
		target = document.all(id);
		if (target) {
			if (target.length){
				for (i=0;i<target.length;i++){
					if (target[i].style.display=='none')
					{target[i].style.display='';}
					else  {target[i].style.display='none';}
				}
			}
			else{
				if (target.style.display=='none')
					{target.style.display='';}
					else  {target.style.display='none';}
			}
		}
	} catch (e) {alert(e);}
}
//alert(document.emptree.empid.length)
function send()
{
	form = document.emptree;
	var str="";
	for(i=0;i<form.empid.length;i++){
		if (form.empid[i].checked) {
		    str+=" &nbsp;<input name='empid'value='"+form.empid[i].value+"'type='hidden'><input id='empname'value='"+form.empname[i].value+"'type='hidden'>"+form.empname[i].value+",";
		}
	}
	opener.document.all.emparea.innerHTML=str.substring(0,str.length-1);
	//alert(str.substring(0,str.length-1));
	self.close();
}
function moifyempid (){
	form = document.emptree;
	if (!opener){return  false;}
	if (opener.document.all.empname)
	{
		target = new Array
		if (opener.document.all.empname.length)
		{
			for (i=0;i<opener.document.all.empname.length;i++)
			{
			target[target.length]=opener.document.all.empname[i].value;
			}
		}else{
		target[target.length]=opener.document.all.empname.value;
		//alert(target);
		}
	}
	else {return false;}
	for (j=0;j<form.empname.length;j++)
	{
		if (target.length)
		{
			for (i=0;i<target.length;i++)
			{
				if (target[i] == form.empname[j].value)
				{
					form.empid[j].checked=true;
					target.splice(i,1);
					break;
				}
			}
		}else{
		if (target == form.empname[j].value)
			{
				form.empid[j].checked=true;
				break;
			}
		}
	}
}
moifyempid();
//-->
</SCRIPT>
