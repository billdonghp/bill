

<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsOtherItem"%>
<%@ page import="com.ait.evs.EvsOtherItemDetail"%>
<%@ page import="com.ait.evs.EvsOtherColumn"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.Constants"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>

<%

String evPeriodId="";
String evEmpId="";
String evTypeId="";
String evItemId="";
String flag="";
EvsOtherItem evOtherItem=null;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evEmpId=request.getParameter("evEmpId")!=null?request.getParameter("evEmpId"):evEmpId;

evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;

try{
	EvsEmp evsEmp = new EvsEmp(evEmpId,evPeriodId);
	evsEmp.getEvEmpByEmpIdPeriod();
  	if (evsEmp!=null) {
  		evTypeId=evsEmp.getEvTypeID();
 	}
}catch(Exception e){}

String RedirectURL="/evs/evs0208.jsp?menu_code=evs0208&evPeriodId="+evPeriodId+"&evEmpId="+evEmpId;
if("add".equals(flag)){
	if(!evEmpId.equals("")&&!evPeriodId.equals("")){
		
	
		EvsOtherItemDetail evOtherItemDetail=null;
		evOtherItem=new EvsOtherItem(evPeriodId,evTypeId,evEmpId);
		List lEvOtherColumn=null;
		List lEvOtherDetailColumn=new Vector();
		try{
			lEvOtherColumn=evOtherItem.getEvOtherColumns(evItemId);
			if(evItemId.equals(Constants.EVITEM005)){
				String [] check=request.getParameterValues("check");
				
				if(check!=null){
					int len=check.length;
					for(int j=0;j<len;j++){
						float evDetailProp=0;
						try{
							evDetailProp=Float.parseFloat(request.getParameter("evItemDetailProp_"+check[j]));
						}catch(Exception e){
							evDetailProp=0f;
						}
						if(lEvOtherColumn!=null){
							int lEvOtherColumnsSize=lEvOtherColumn.size();
							for(int i=0;i<lEvOtherColumnsSize;i++){
								EvsOtherColumn evOtherColumn=(EvsOtherColumn)lEvOtherColumn.get(i);
								evOtherColumn.setEvColumnDetail(StringUtil.toCN(request.getParameter(evOtherColumn.getEvColumnId()+"_detail_"+check[j])));
								lEvOtherDetailColumn.add(evOtherColumn);
								
							}
						}
						evOtherItemDetail=new EvsOtherItemDetail(evPeriodId,evItemId,evEmpId,evDetailProp,lEvOtherDetailColumn);
						evOtherItemDetail.addEvOtherItem();
					}
				}
			}else{
				
				float evDetailProp=0;
				try{
					evDetailProp=Float.parseFloat(request.getParameter("evItemDetailProp"));
				}catch(Exception e){
					evDetailProp=0f;
				}
				if(lEvOtherColumn!=null){
					int lEvOtherColumnsSize=lEvOtherColumn.size();
					for(int i=0;i<lEvOtherColumnsSize;i++){
						EvsOtherColumn evOtherColumn=(EvsOtherColumn)lEvOtherColumn.get(i);
						evOtherColumn.setEvColumnDetail(StringUtil.toCN(request.getParameter(evOtherColumn.getEvColumnId()+"_detail")));
						lEvOtherDetailColumn.add(evOtherColumn);
						
					}
				}
				evOtherItemDetail=new EvsOtherItemDetail(evPeriodId,evItemId,evEmpId,evDetailProp,lEvOtherDetailColumn);
				evOtherItemDetail.addEvOtherItem();
			}	
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

	if(!evEmpId.equals("")&&!evPeriodId.equals("")){
		
		EvsOtherItemDetail evOtherItemDetail=null;
		evOtherItem=new EvsOtherItem(evPeriodId,evTypeId,evEmpId);
		List lEvOtherColumn=null;
		List lEvOtherDetailColumn=new Vector();
		try{
			lEvOtherColumn=evOtherItem.getEvOtherColumns(evItemId);
			int evOtherDetailSeq=-1;
			float evDetailProp=0;
			try{
			evOtherDetailSeq=Integer.parseInt(request.getParameter("evOtherDetailSeq"));
			evDetailProp=Float.parseFloat(request.getParameter("evItemDetailProp"));
			}catch(Exception e){
				evDetailProp=0f;
			}
			if(lEvOtherColumn!=null){
				int lEvOtherColumnsSize=lEvOtherColumn.size();
				for(int i=0;i<lEvOtherColumnsSize;i++){
					EvsOtherColumn evOtherColumn=(EvsOtherColumn)lEvOtherColumn.get(i);
					
					
					evOtherColumn.setEvColumnDetail(StringUtil.toCN(StringUtil.checkNull(request.getParameter(evOtherColumn.getEvColumnId()+"_detail"))));
					lEvOtherDetailColumn.add(evOtherColumn);
				}
			}
			evOtherItemDetail=new EvsOtherItemDetail(evOtherDetailSeq,evDetailProp,lEvOtherDetailColumn);
			evOtherItemDetail.updateEvOtherItem();
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
	EvsOtherItemDetail evOtherItemDetail=null;
	int seq=-1;
	try{
		seq=Integer.parseInt(request.getParameter("ID"));
		evOtherItemDetail=new EvsOtherItemDetail(seq);
		evOtherItemDetail.delEvOtherItem();
	}catch(Exception e){
	}finally{
		response.sendRedirect(RedirectURL);
	}
}


%>

