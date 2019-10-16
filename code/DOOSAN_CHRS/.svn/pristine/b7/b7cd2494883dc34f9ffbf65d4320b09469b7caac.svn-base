<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*,com.ait.util.StringUtil" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/default.css">
<script type="text/javascript"> 
var msg=new Array('<ait:message messageID="alert.sys.authority.authority.edit.emp_user" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.pattern.authority_no" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.user" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.maximum_length_20" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.pattern.group_description" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.maximum_length_50" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.select_group" module="sys"></ait:message>'
                   );
</script>
</head>
<body>       
<%@ page import="com.ait.sy.sy0101.bean.*"%>
<jsp:include page="/inc/hrnav.jsp" />
<%@ include file="../inc/import.jsp"%>
<script language="javascript">
function checkAll()
{
	if(document.EM2Form.In.checked==true)
	{
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name.indexOf("In")!=-1)
			{
				EM2Form.elements[i].checked=true;
			}
		}
	}
	else
	{
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name.indexOf("In")!=-1)
			{
				EM2Form.elements[i].checked=false;
			}
		}
	}
	//
	if(document.EM2Form.De.checked==true)
	{
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name.indexOf("De")!=-1)
			{
				EM2Form.elements[i].checked=true;
			}
		}
	}
	else
	{
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name.indexOf("De")!=-1)
			{
				EM2Form.elements[i].checked=false;
			}
		}
	}
	//
	if(document.EM2Form.Up.checked==true)
	{
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name.indexOf("Up")!=-1)
			{
				EM2Form.elements[i].checked=true;
			}
		}
	}
	else
	{
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name.indexOf("Up")!=-1)
			{
				EM2Form.elements[i].checked=false;
			}
		}
	}
	//
	if(document.EM2Form.Fo.checked==true)
	{
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name.indexOf("Fo")!=-1)
			{
				EM2Form.elements[i].checked=true;
			}
		}
	}
	else
	{
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name.indexOf("Fo")!=-1)
			{
				EM2Form.elements[i].checked=false;
			}
		}
	}
}
//////////
function myCheck(a,menu_code,depth)
{
var name2;
var name1;
var value1;
var lc;
var temp;
if(depth>1)
return;
	if(document.EM2Form.all[a].checked==true)
	{
		name1=document.EM2Form.all[a].value;
		if(a.indexOf("RFore")!=-1)
		{	
			name2=a.substring(0,5);
		}
		else
		{	
			name2=a.substring(0,7);
		}
		
		if(depth==0)
		{	
			if(menu_code.indexOf("ess")!=-1||menu_code.indexOf("evs")!=-1||menu_code.indexOf("evj")!=-1||menu_code.indexOf("kpa")!=-1){
				value1=name1.substring(0,3);
			}else{
				value1=name1.substring(0,2);
			}	
		}
		if(depth==1)
		{	
			if(menu_code.indexOf("ess")!=-1||menu_code.indexOf("evs")!=-1||menu_code.indexOf("evj")!=-1||menu_code.indexOf("kpa")!=-1){
				value1=name1.substring(0,5);
			}else{
				value1=name1.substring(0,4);
			}	
		}
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox")
			{
				temp=EM2Form.elements[i].value;
				if(depth==0)
				{
					
					if(temp.indexOf("ess")!=-1||temp.indexOf("evs")!=-1||temp.indexOf("evj")!=-1||menu_code.indexOf("kpa")!=-1)
					{
						temp=temp.substring(0,3);
					}else{
						temp=temp.substring(0,2);
					}
				}
				if(depth==1)
				{
					
					if(temp.indexOf("ess")!=-1||temp.indexOf("evs")!=-1||temp.indexOf("evj")!=-1||menu_code.indexOf("kpa")!=-1)
					{
						temp=temp.substring(0,5);
					}else{
						temp=temp.substring(0,4);
					}					
				}
				if(temp==value1&&EM2Form.elements[i].name.indexOf(name2)!=-1)
					EM2Form.elements[i].checked=true;
			}
		}
	}
	else
	{
		name1=document.EM2Form.all[a].value;
		if(a.indexOf("RFore")!=-1)
		{
			name2=a.substring(0,5);
		}
		else
		{
			name2=a.substring(0,7);
		}
		if(depth==0)
		{	
			if(menu_code.indexOf("ess")!=-1||menu_code.indexOf("evs")!=-1||menu_code.indexOf("evj")!=-1||menu_code.indexOf("kpa")!=-1){
				value1=name1.substring(0,3);
			}else{
				value1=name1.substring(0,2);
			}	
		}
		if(depth==1)
		{
			if(menu_code.indexOf("ess")!=-1||menu_code.indexOf("evs")!=-1||menu_code.indexOf("evj")!=-1||menu_code.indexOf("kpa")!=-1){
				value1=name1.substring(0,5);
			}else{
				value1=name1.substring(0,4);
			}	
		}
		for(var i=0;i<EM2Form.elements.length;i++)
		{
			if(EM2Form.elements[i].type=="checkbox")
			{
				temp=EM2Form.elements[i].value;
				
				if(depth==0)
				{
					
					if(temp.indexOf("ess")!=-1||temp.indexOf("evs")!=-1||temp.indexOf("evj")!=-1||menu_code.indexOf("kpa")!=-1)
					{
						temp=temp.substring(0,3);
					}else{
						temp=temp.substring(0,2);
					}
				}
				if(depth==1)
				{
					
					if(temp.indexOf("ess")!=-1||temp.indexOf("evs")!=-1||temp.indexOf("evj")!=-1||menu_code.indexOf("kpa")!=-1)
					{
						temp=temp.substring(0,5);
					}else{
						temp=temp.substring(0,4);
					}
				}
				//alert("temp:"+temp+"  value1:"+value1+"   name2:"+name2+"   EM2Form.elements[i].name:"+EM2Form.elements[i].name);
				if(temp==value1&&EM2Form.elements[i].name.indexOf(name2)!=-1)
					EM2Form.elements[i].checked=false;
			}
		}
	}
}

function codeChange()
{	
	
	target=document.all("ar");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("pa");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("sy");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("ess");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("evs");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("ga");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("gm");
	if(target){
	 	if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("se");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
  target=document.all("pu");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("report");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	target=document.all("kpa");
	if(target){
		if(target.length){
			for (i=0;i<target.length;i++){
				target[i].style.display='none';
			}
		}
		else
		{
			target.style.display='none';
		}
	}
	
	id=document.EM2Form.changeCode.value;
	target = document.all(id);
	if(target)
	{
		if(target.length)
		{
			for (i=0;i<target.length;i++){
				target[i].style.display='';
			}
		}
		else
		{
			target.style.display='';
		}
	}
}
</script>
<SCRIPT LANGUAGE="JavaScript" src="../js/sy0105.js"></SCRIPT>
<%Ent Ent = new Ent();
			String x = request.getParameter("strPage");
			String y = request.getParameter("bigpage");
			PageControl pc = new PageControl(1000, 10);
			int bigpage = pc.getTmpBig(y);
			int strPage = pc.getTmpSmall(x, bigpage);
			pc.setRowCount("sy_menu order by MENU_NO desc ");
			pc.setintPage(strPage, bigpage);
			int sy_menuSum = Biz.getSy_menuSum();
			Vector vlist2 = new Vector();
			com.ait.sy.sy0105.bean.Biz Biz2 = new com.ait.sy.sy0105.bean.Biz();
			com.ait.sy.sy0105.bean.Ent Ent2 = new com.ait.sy.sy0105.bean.Ent();
			vlist2 = Biz2.Detail(no);
			Ent2 = (com.ait.sy.sy0105.bean.Ent) vlist2.elementAt(0);
			int ii = new Integer(Ent2.getScreenGrantNo()).intValue();
			vlist = Biz.List(pc, ii);
			List vlistCode = Biz.ListCode();
			%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif" bgcolor="#FFFFFF" style='position: relative; top: expression(this.offsetParent.scrollTop);'>
		
			<!-- display toolbar -->
			<%@ include file="../sy/inc/sy_toolbar_a.jsp"%>  
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

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 权限组 -->
					<ait:message messageID="display.mutual.authority_group" ></ait:message>   
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		  <td height="20">
		  <table width="100%" border="0" bgcolor="#FFFFFF" align="center">
		    <form name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=update&menu_code=<%=menu_code%>&screenGrantNo_O=<%=no%>&no=<%=Ent2.getScreenGrant_Seq()%>" onSubmit="return CheckForm()">
		      <tr>
		        <td width="100%">
		        <TABLE width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		            <TBODY>
		              <TR>
		                    <TD  class="info_title_01" nowrap><div align="left"><!-- 权限组号 -->
		               <ait:message messageID="display.sys.authority.group.edit.group_no"   module="sys"></ait:message>   	     
		                    </div></TD>
		                <TD align=left width="85%"><div align="left"> <%=ii%>
		                    <input type="hidden" name="screenGrantNo" value="<%=ii%>">
		                    <input type="hidden" name="check" value=" <ait:message messageID="alert.sys.successful" module="sys"/>">
		                  </div></TD>
		              </TR>
		              <TR>
		                    <TD  class="info_title_01" nowrap><div align="left">法人区分</div></TD>
		                <TD align=left width="85%"><div align="left"><%=Ent2.getCpnyname()%></div></TD>
		              </TR>
		              <TR>
		                   <TD class="info_title_01" nowrap><div align="left"> <!-- 权限组描述 --> 
		                  <ait:message messageID="display.sys.authority_management.authority_group.authority_group_name"   module="sys"></ait:message>   	  
		                    </div></TD>
		                <TD ><div align="left"> <%=Ent2.getScreenGrantName()%>
		                    <INPUT type="hidden" size=40 name="screenGrantName" value="<%=Ent2.getScreenGrantName()%>">
		                  </div></TD>
		              </TR>
		               <TR>
		                 <TD class="info_title_01" nowrap><div align="left"><!-- 权限组英文描述 -->
		                   <ait:message messageID="display.sys.authority.group.edit.english_name"   module="sys"></ait:message>   	 
		                    </div></TD>
		                <TD ><div align="left"> <%=Ent2.getScreenGrantEnName() %>
		                    <INPUT type="hidden" size=40 name="screenGrantEnName" value="<%=Ent2.getScreenGrantEnName()%>">
		                  </div></TD>
		              </TR>
		          </table></td>
		      </tr>                 
		      <tr>
		        <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		            <tr>
		              <td class="line"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		                  <tr style='position: relative; top: expression(this.offsetParent.scrollTop);'>
		                     <td nowrap height="30" class="info_title_01"><!-- 序号 -->
		                      <ait:message messageID="display.mutual.no" ></ait:message>   	  
		                        </td>
		                        <td nowrap height="30" class="info_title_01"><!-- 深度 -->
		                        <ait:message messageID="display.sys.authority.group.edit.depth"   module="sys"></ait:message>   	
		                        </td>
		                        <td nowrap height="30" class="info_title_01"><!--  屏幕编号-->
		                        <ait:message messageID="display.sys.authority.group.edit.screen_no"   module="sys"></ait:message>   	
		                      <select name="changeCode" onChange="codeChange()">
		                        <option value="">select
		                        </option>
		                        <option value="ar"> ar </option>
		                        <option value="ess"> ess </option>
		                        <option value="ga"> ga </option>
		                        <option value="pa"> pa </option>
		                         <option value="evs"> evs </option>
		                        <option value="sy"> sy </option>
		                        <option value="report"> report </option>
		                         <option value="kpa"> kpa </option>
		                      </select>
		                    </td>    
		                      <td nowrap height="30" class="info_title_01"><!--屏幕解释  -->
		                      <ait:message messageID="display.sys.authority.group.edit.description"   module="sys"></ait:message>   	  
		                        </td>
		                        <td nowrap height="30" class="info_title_01"><!--添加  -->
		                  <ait:message messageID="display.mutual.add"  ></ait:message>   	      
		                        </td>
		                        <td nowrap class="info_title_01"><!-- 删除 -->
		                       <ait:message messageID="display.mutual.delete"  ></ait:message> 
		                        </td>
		                        <td nowrap class="info_title_01"><!-- 修改 -->
		                         <ait:message messageID="display.mutual.edit"  ></ait:message>   	                       	     
		                        </td>
		                        <td nowrap class="info_title_01"><!--  列表-->
		                        <ait:message messageID="display.sys.authority.group.edit.list" module="sys"  ></ait:message>   	
		                        </td>
		                  </tr>
		                  <%int listNo = 1;
					int rowcount = vlist.size();
					for (int i = 0; i < vlist.size(); i++) {
						Ent = (Ent) vlist.elementAt(i);
						
						%>
		                    <%if (Ent.getMenuCode().indexOf("ar") != -1) {%>
		                  <tr id="ar" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("ess") != -1) {%>
		                  <tr id="ess" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("ga") != -1) {%>
		                    <tr id="ga" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("pa") != -1) {%>
		                    <tr id="pa" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("sy") != -1) { %>
		                    <tr id="sy" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("gm") != -1) { %>
		                    <tr id="gm" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("evs") != -1) { %>
		                    <tr id="evs" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("se") != -1) { %>
		                    <tr id="se" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("pu") != -1) { %>
		                    <tr id="pu" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("report") != -1) { %>
		                    <tr id="report" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    <%if (Ent.getMenuCode().indexOf("kpa") != -1) { %>
		                    <tr id="kpa" bgcolor="#FFFFFF" onClick="HighLightTR('#99CCFF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>')" style="display:'none'">
		                    <%}%>
		                    
		                    <td height="30" class="info_content_01"><%=listNo%>
		                      <%listNo = listNo + 1;%>
		                    </td>
		                    <td class="info_content_01"><img src="/images/btn/<%=Ent.getDepth()%>1.gif" width="18" height="18"> </td>
		                    <td class="info_content_01"><%=Ent.getMenuCode()%> </td>
		                    <td class="info_content_01">
                        <ait:content enContent='<%=StringUtil.checkNull(Ent.getMenuEnIntro())%>' 
		                         zhContent='<%=StringUtil.checkNull(Ent.getMenuIntro())%>'></ait:content>
		                      <input name="screenCode<%=i%>" type="hidden" value="<%=Ent.getMenuCode()%>" size="100">
		                    </td>
		                    <td class="info_content_01"><input type="checkbox" name="RInsert<%=i%>" value="<%=Ent.getMenuCode()%>" <% if (Ent.getInsertr()==1){%> checked <%}%>
																	onClick="myCheck('RInsert<%=i%>','<%=Ent.getMenuCode()%>','<%=Ent.getDepth()%>')">
		                    </td>
		                    <td class="info_content_01"><input type="checkbox" name="RDelete<%=i%>" value="<%=Ent.getMenuCode()%>" <% if (Ent.getDeleter()==1){%> checked <%}%>
																	onClick="myCheck('RDelete<%=i%>','<%=Ent.getMenuCode()%>','<%=Ent.getDepth()%>')">
		                    </td>
		                    <td class="info_content_01"><input type="checkbox" name="RUpdate<%=i%>" value="<%=Ent.getMenuCode()%>" <% if (Ent.getUpdater()==1){%> checked <%}%>
																	onClick="myCheck('RUpdate<%=i%>','<%=Ent.getMenuCode()%>','<%=Ent.getDepth()%>')">
		                    </td>
		                    <td class="info_content_01"><input type="checkbox" name="RFore<%=i%>" value="<%=Ent.getMenuCode()%>" <% if (Ent.getSelectr()==1){%> checked <%}%>
																	onClick="myCheck('RFore<%=i%>','<%=Ent.getMenuCode()%>','<%=Ent.getDepth()%>')">
		                    </td>
		                  </tr>
		                  <%}%>
		                </table></td>
		            </tr>
		          </table></td>
		      </tr>
		      <input type="hidden" name="rowcount" value="<%=rowcount%>">
		      <input name="orderNo" type="hidden" value="<%=Ent2.getOrderNo()%>">
		      <input name="InsertR" type="hidden" value="">
		      <input name="UpdateR" type="hidden" value="">
		      <input name="DeleteR" type="hidden" value="">
		      <input name="ForeR" type="hidden" value="">
		      </td>
		      
		      </tr>
		      
		    </form>
		  </table>
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

