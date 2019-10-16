<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.hrm.business.*"%>
<%@ page import="com.ait.hrm.bean.Department"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="department" class="com.ait.hrm.bean.Department" scope="page" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%@ taglib uri="/ait-taglib" prefix="ait" %>
<%@ include file="/inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<!--被评价者-->
<%
String evEmpId="";
String evPeriodId="";
String evTypeId="";
String evDeptId="";
String url="";
evEmpId=request.getParameter("ID")!=null?request.getParameter("ID"):evEmpId;
evPeriodId=request.getParameter("ID2")!=null?request.getParameter("ID2"):evPeriodId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
System.out.println("1344654654654654" + evPeriodId);
EvsEmp evsEmp=new EvsEmp(evEmpId,evPeriodId);

HrmServices hrServices = HrmServices.getInstance();
EvsPeriod evPeriod=new EvsPeriod();
List lEvDept=null;
List lEvType=null;
try{
	SimpleMap map=new SimpleMap();
  	map.setString("EMPID",admin.getAdminID());
	evsEmp.getEvEmpMastersByEmpIdPeriod();
	 String cpnyid = admin.getCpnyId();
    dept_list = (ArrayList) hrServices.getAllDept(cpnyid);
	
}catch(Exception e){}

if(("").equals(evTypeId)){
	evTypeId=evsEmp.getEvTypeID();
}
if(("").equals(evDeptId)){
	evDeptId=evsEmp.getEvDeptID();
}
//评价项目评价流程
String evProcessId="";
EvsProcess evsProcess=null;
List lEvsProcess=null;

int lEvsProcessSize=0;
if(!evPeriodId.equals("")&&!evTypeId.equals("")){
	evsProcess=new EvsProcess(evPeriodId,evTypeId);
	try{
		lEvsProcess=evsProcess.getProcessByTypePeriod();
		//lEvDept=EvsEmp.getEvsEmpDeptList(admin.getAdminID());
		lEvType=evPeriod.getEvTypeByEvPeriodId(evPeriodId);
	}catch(Exception e){
		session.removeAttribute("evsEmpList");
	}
}
	
url="/evs/evs0105_m.jsp?menu_code=evs0105&ID="+evEmpId+"&ID2="+evPeriodId+"&evTypeId=document.LGFORM.evTypeId.value"+evTypeId+"&evDeptId=document.LGFORM.evDeptId.value";

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价者设置</title>
</head>
<body>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/evstoolbar_m.jsp"%>
			
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
		<br>

<form name="LGFORM" method="POST" action="">
<input type="hidden" name="flag" value="update">
<input type="hidden" name="menu_code" value="<%=menu_code%>">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1">
	<tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			
			<tr align="center">
				<td class="info_title_01">工号
				<input type="hidden" name="evPeriodId" value="<%=StringUtil.checkNull(evPeriodId)%>">
				<input type="hidden" name="empId" value="<%=StringUtil.checkNull(evEmpId)%>">
				</td>
				<td class="info_title_01">姓名</td>
				<td class="info_title_01">当前流程</td>
				<td class="info_title_01">部门</td>
				<td class="info_title_01">评价类型</td>
			</tr>
			<tr align="center">
				<td class="info_content_01"><%=evsEmp.getEvEmpID()%>&nbsp;</td>
				<td class="info_content_01"><%=evsEmp.getEvEmpName()%>&nbsp;</td>
				<td class="info_content_01"><%=evsEmp.getEvCurrentProcessName()%>&nbsp;</td>
				<td width="20%" class="info_content_01">
					<ait:selEvDept name="evDeptId" evPeriodId="<%=evPeriodId %>" selectEvDeptId="<%= evDeptId %>" />
				</td>
				<td class="info_content_01">
					<select name="evTypeId"
						onChange="location.href='/evs/evs0105_m.jsp?menu_code=evs0105&ID=<%=evEmpId%>&ID2=<%=evPeriodId%>&evTypeId='+this.value+'&evDeptId='+document.LGFORM.evDeptId.value;">
						<option value="">评价类型</option>
						<%
	                            if(lEvType!=null){
	                            	int lEvsTypeSize=lEvType.size();
	                            	for(int i=0;i<lEvsTypeSize;i++){
	                            		EvsType evsType_sel=(EvsType)lEvType.get(i);
	                              %>
						<option value="<%=evsType_sel.getEvTypeID()%>"
							<%if (evTypeId.equals(evsType_sel.getEvTypeID())) {%>
							selected="selected" <%}%>><%=evsType_sel.getEvTypeName()%></option>
						<%}}%>
					</select>
				</td>

			</tr>

			<tr>
				<td colspan="5" class="info_content_01">
				<table width="100%">
					<td class="info_content_01">
					<table border="1" cellpadding="0" cellspacing="0" width="100%"
						bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
						style="padding: 2px 2px 2px 2px;">
						<tr bgcolor="#F5F5F5">
							<td class="info_title_01">评价流程</td>
							<td class="info_title_01">操作者</td>

						</tr>

						<%		
							if(lEvsProcess!=null){
							lEvsProcessSize=lEvsProcess.size();
								for(int k=0;k<lEvsProcessSize;k++){
									EvsProcess evsProcess_tr=(EvsProcess)lEvsProcess.get(k);
									List lEvMaster=new Vector();
									%>
						<tr>
							<input name="evProcessId<%=k%>" type="hidden"
								value="<%=StringUtil.checkNull(evsProcess_tr.getEvProcessID())%>">
							<td class="info_content_01"><input type="hidden" name="check" value="<%=k%>">&nbsp;<%=evsProcess_tr.getEvProcessName()%></td>
							<td align="center" class="info_content_01"><%
									
									boolean isEquals=false;
									String evMaster="";
	                                if(evsEmp.getEvsMaster()!=null){
	                                	lEvMaster=evsEmp.getEvsMaster();
	                                	int lEvMasterSize=0;
	                                	lEvMasterSize=lEvMaster.size();
	                                	
	                                  	for(int i=0;i<lEvMasterSize;i++){
	                                    		EvsMaster m = (EvsMaster)lEvMaster.get(i);
	                                    	
	                                    	if(m.getEvProcessID().equals(evsProcess_tr.getEvProcessID())){
	                                    		isEquals=true;
                               					evMaster=m.getEvMaster();
												}
                           					}
                           				if(isEquals){
                               					out.print("<input type='text' name='evMaster"+k+"' value='"+StringUtil.checkNull(evMaster)+"' onfocus='getValue(this)'");											   
										}else{
												out.print("<input type='text' name='evMaster"+k+"' value='' onfocus='getValue(this)'");											   
										}
                           			}else{
                           				out.print("<input type='text' name='evMaster"+k+"' value='' onfocus='getValue(this)'");	
                           			}
                           			%></td>
						</tr>
						<%
                           		}
                           		}
                         	%>

					</table>
					</td>
					<td valign="top" width="40%" class="info_content_01">
					<table border="1" cellpadding="0" cellspacing="0" width="100%"
						bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
						style="padding: 2px 2px 2px 2px;">
						<tr bgcolor="#F5F5F5">
							<td align="center" class="info_title_01">评价者列表</td>
						</tr>
						<tr>
							<td align="center" class="info_content_01"><select name="master_dept_id"
								onChange="evsMasterListI.location.href='/evs/evsMasterList.jsp?deptid='+this.value;">
								<option>请选择部门</option>
								<%
                                                  String deptID = request.getParameter("deptid");
                                                  for (int d = 0 ; d < dept_list.size() ; d++ )
                                                  {
                                                    department = (Department) dept_list.get(d);
                                                    %>
								<option value="<%=department.getDeptID()%>"
									<%if ((department.getDeptID()).equals(deptID)){%> selected
									<%}%>><%
                                                      String deptname="";
                                                      int level=department.getDeptLevel();
                                                      for(int j=0;j<level;j++){
                                                        deptname+="　";
                                                      }
                                                      %> <%=deptname+department.getDeptName()%>
								</option>
								<%}%>
							</select></td>
						</tr>
						<tr align="center">
							<td class="info_content_01">
								<iframe src="/evs/evsMasterList.jsp"
									width="205" name="evsMasterListI" scrolling="yes"
									frameborder="0" height="<%=lEvsProcessSize*60%>"
									marginheight="0" marginwidth="0" id="evsMasterListI">
								</iframe>
							</td>
						</tr>
					</table>
					</td>
				</table>

				</td>
			</tr>
		</table>
		</td>
	</tr>
	
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
</form> 
</body>
</html>
<script type="text/javascript" language="javascript">

function getValue(t)
{
  try{

    t.value=evsMasterListI.evsMasterListForm.evsMasterList.value;
  }catch(e){alert(e);}
}

function selectall()
{
  try{
    var length=<%=lEvsProcessSize%>;


    document.LGFORM.checkall.checked = document.LGFORM.checkall.checked|0;

    if ( length== 0 ){
      return;
    }
    if (length ==1 )
    {
      document.LGFORM.check.checked=document.LGFORM.checkall.checked ;
    }

    if (length>1)
    {
      for (var i = 0; i < length; i++)
      {

        document.LGFORM.check[i].checked=document.LGFORM.checkall.checked;
      }
    }
  }catch(e){alert("error:"+e);}
}
</script>


