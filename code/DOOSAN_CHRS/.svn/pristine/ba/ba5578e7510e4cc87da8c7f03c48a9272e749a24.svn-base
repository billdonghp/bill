

<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsCommonItem"%>
<%@ page import="com.ait.evs.EvsCommonItemDetail"%>
<%@ page import="com.ait.evs.EvsCommonColumn"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>

<%
String evPeriodId="";
String evTypeId="";
String evItemId="";
String flag="";
EvsCommonItem evCommonItem=null;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
String RedirectURL="/evs/evs0106.jsp?menu_code=evs0106&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;
if("add".equals(flag)){
	if(!evTypeId.equals("")&&!evPeriodId.equals("")){
		EvsCommonItemDetail evCommonItemDetail=null;
		evCommonItem=new EvsCommonItem(evPeriodId,evTypeId);
		List lEvCommonColumn=null;
		List lEvCommonDetailColumn=new Vector();
		try{
			lEvCommonColumn=evCommonItem.getEvCommonColumns(evItemId);
			float evDetailProp=Float.parseFloat(request.getParameter("evItemDetailProp"));
			
			
			if(lEvCommonColumn!=null){
				int lEvCommonColumnsSize=lEvCommonColumn.size();
				for(int i=0;i<lEvCommonColumnsSize;i++){
					EvsCommonColumn evCommonColumn=(EvsCommonColumn)lEvCommonColumn.get(i);
					evCommonColumn.setEvColumnDetail(StringUtil.toCN(request.getParameter(evCommonColumn.getEvColumnId()+"_detail")));
					lEvCommonDetailColumn.add(evCommonColumn);
					
				}
			}
			evCommonItemDetail=new EvsCommonItemDetail(evPeriodId,evItemId,evTypeId,evDetailProp,lEvCommonDetailColumn);
			evCommonItemDetail.addEvCommonItem();
		}catch(Exception e){
		}finally{
			response.sendRedirect(RedirectURL);
		%>
		<script language="JavaScript">
			window.opener.location.href=opener.location.href;	
			//window.opener.close();
			window.close();
		</script>
		
<%
		}		
	}
}
if("update".equals(flag)){

	if(!evTypeId.equals("")&&!evPeriodId.equals("")){
		
		EvsCommonItemDetail evCommonItemDetail=null;
		evCommonItem=new EvsCommonItem(evPeriodId,evTypeId);
		List lEvCommonColumn=null;
		List lEvCommonDetailColumn=new Vector();
		try{
			lEvCommonColumn=evCommonItem.getEvCommonColumns(evItemId);
			int evCommonDetailSeq=Integer.parseInt(request.getParameter("evCommonDetailSeq"));
			float evDetailProp=Float.parseFloat(request.getParameter("evItemDetailProp"));
			if(lEvCommonColumn!=null){
				int lEvCommonColumnsSize=lEvCommonColumn.size();
				for(int i=0;i<lEvCommonColumnsSize;i++){
					EvsCommonColumn evCommonColumn=(EvsCommonColumn)lEvCommonColumn.get(i);
					
					
					evCommonColumn.setEvColumnDetail(StringUtil.toCN(StringUtil.checkNull(request.getParameter(evCommonColumn.getEvColumnId()+"_detail"))));
					lEvCommonDetailColumn.add(evCommonColumn);
				}
			}
			evCommonItemDetail=new EvsCommonItemDetail(evCommonDetailSeq,evDetailProp,lEvCommonDetailColumn);
			evCommonItemDetail.updateEvCommonItem();
		}catch(Exception e){
		}finally{
			response.sendRedirect(RedirectURL);
		%>
		<script language="JavaScript">			
			window.opener.location.href=opener.location.href;	
			//window.opener.close();
			window.close();
		</script>
		
<%
		}
	}
}
if("del".equals(flag)){
	EvsCommonItemDetail evCommonItemDetail=null;
	int seq=-1;
	try{
		seq=Integer.parseInt(request.getParameter("ID"));
		evCommonItemDetail=new EvsCommonItemDetail(seq);
		evCommonItemDetail.delEvCommonItem();
	}catch(Exception e){
	}finally{
		response.sendRedirect(RedirectURL);
	}
}

if("endow".equals(flag)){
	EvsCommonItem evCommon=new EvsCommonItem();
	//String endowType="";
	int ok=0;
	try{
			List lEvEmps=new Vector();
			int sum=0;	          
		    String [] aEmpId =request.getParameterValues("empid");
			if(aEmpId!=null&&!evPeriodId.equals("")){
		    for (int i = 0; i < aEmpId.length; i++) {
		        EvsEmp emp = new EvsEmp(aEmpId[i],evPeriodId);
		        lEvEmps.add(emp);
		     }
			}
			try{
				sum=evCommon.evCommonEndow(lEvEmps,evItemId);
			}catch(Exception e){
			%>
			<script language="JavaScript">
				alert("操作失败！\n\n  请重试！");
				window.close();
			</script>
			<%	
			}
			%>
			<script language="JavaScript">
				alert("操作成功！");
				window.close();
			</script>
			<%
	}catch(Exception e){}
}

if("endowAll".equals(flag)){
	EvsCommonItem evCommon=new EvsCommonItem();
	int ok=0;
	try{
		
		if(!evPeriodId.equals("")&&!evTypeId.equals("")){
			ok=evCommon.evCommonEndow(evPeriodId,evTypeId);
		}
		if(ok==1){
		%>
		<script language="JavaScript">
			alert("操作成功！");
			location.href='<%=RedirectURL%>';
		</script>
		<%
		}else{
		%>
		<script language="JavaScript">
			alert("操作失败！\n\n  请重试！");
			location.href='<%=RedirectURL%>';
		</script>
		<%
		}
	}catch(Exception e){}
}

%>

