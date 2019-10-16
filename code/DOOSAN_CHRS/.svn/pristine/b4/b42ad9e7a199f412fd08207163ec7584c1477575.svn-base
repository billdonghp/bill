

<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.EvsRelation"%>
<%@ page import="com.ait.evs.EvsOperate"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<jsp:directive.page import="com.ait.util.NumberUtil;"/>

<%
String evPeriodId="";
String evTypeId="";
String evItemId="";
String evProcessId="";
String flag="";

EvsItem evsItem=null;
EvsProcess evsProcess=null;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;

flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;

String RedirectURL="/evs/evs0102.jsp?menu_code=evs0102&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;
if("delEvItem".equals(flag)){
	evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;
	if(!evItemId.equals("")&&!evPeriodId.equals("")&&!evTypeId.equals("")){
		evsItem=new EvsItem(evPeriodId,evTypeId,evItemId,"", 0);
		try{
		evsItem.delEvsTypeItem();
		}catch(Exception e){}
	}
	
}
if("delEvProcess".equals(flag)){
	evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;
	if(!evProcessId.equals("")&&!evPeriodId.equals("")&&!evTypeId.equals("")){
		evsProcess=new EvsProcess(evPeriodId,evTypeId,evProcessId,"", 0,"","");
		try{
			evsProcess.delEvsTypeProcess();
		}catch(Exception e){}
	}
	
}
if("update".equals(flag)){



	//更新评价流程信息
	List lEvsProcess_tr=new Vector();
	String [] evProcessId_tr;
	String [] evProcessOrder;
	String [] evProcessSDate;
	String [] evProcessEDate;
	evProcessId_tr=request.getParameterValues("evProcessId_tr");
	evProcessOrder=request.getParameterValues("evProcessOrder");
	evProcessSDate=request.getParameterValues("evProcessSDate");
	evProcessEDate=request.getParameterValues("evProcessEDate");
	
	if(evProcessId_tr!=null&&evProcessOrder!=null&&evProcessSDate!=null&&evProcessEDate!=null){
	
		try{
			for(int i=0;i<evProcessId_tr.length;i++){
				EvsProcess evs=new EvsProcess(evPeriodId,evTypeId,evProcessId_tr[i],
		            "",Integer.parseInt(evProcessOrder[i]),evProcessSDate[i],evProcessEDate[i]);
		        lEvsProcess_tr.add(evs);
	        }
	        EvsProcess e=new EvsProcess();
	        e.updateEvsProcessByTypePeriod(lEvsProcess_tr);
        }catch(Exception e){}
	}

	//评价期间评价类型
	EvsType evsType=new EvsType(evTypeId,"");
	EvsPeriod evsPeriod=new EvsPeriod(evPeriodId);
	//评价项目评价流程
	List lEvsProcess=null;
	List lEvsItem=null;
	
	if(!evTypeId.equals("")&&!evPeriodId.equals("")){
		evsProcess=new EvsProcess(evPeriodId,evTypeId);
		evsItem=new EvsItem(evPeriodId,evTypeId);
		try{
			lEvsProcess=evsProcess.getProcessByTypePeriod();
			lEvsItem=evsItem.getItemByTypePeriod();
		}catch(Exception e){}
	}
		
	//更新评价项目关系
	try{
	if(lEvsProcess!=null){
		List lEvsRelation=new Vector();
		EvsRelation evsRelation=new EvsRelation();
		int lEvsProcessSize=lEvsProcess.size();
		for(int i=0;i<lEvsProcessSize;i++){
			EvsProcess evsProcess_tr=(EvsProcess)lEvsProcess.get(i);
			String evsProcessId="";
			evsProcessId=evsProcess_tr.getEvProcessID();
			if(lEvsItem!=null){
				int lEvsItemSize_td=lEvsItem.size();
				for(int j=0;j<lEvsItemSize_td;j++){
				
					EvsItem evsItem_td=(EvsItem)lEvsItem.get(j);
					String evsItemId="";
					EvsRelation er=null;
					evsItemId=evsItem_td.getEvItemID();
					
					String evCheck="";
					evCheck=request.getParameter(evsProcessId+"_"+evsItemId+"_"+"check")!=null?request.getParameter(evsProcessId+"_"+evsItemId+"_"+"check"):"";
					if(!evCheck.equals("")){
						String evMarksValues="";
						String evMarksName="";
						String evOperateId="";
						float processProp=0;
						float itemProp=0;
						float markDefault=0;
						evMarksValues=request.getParameter(evsProcessId+"_"+evsItemId+"_"+"marksValues");
						evMarksName=StringUtil.toCN(request.getParameter(evsProcessId+"_"+evsItemId+"_"+"marksName"));
						evOperateId=request.getParameter(evsProcessId+"_"+evsItemId+"_"+"operateId");
						processProp=Float.parseFloat(request.getParameter(evsProcessId+"_"+evsItemId+"_"+"processProp"));
						itemProp=Float.parseFloat(request.getParameter(evsProcessId+"_"+evsItemId+"_"+"itemProp"));
						markDefault=Float.parseFloat(request.getParameter(evsProcessId+"_"+evsItemId+"_"+"markDefault"));
						
						EvsOperate evsOperate=new EvsOperate(evOperateId,"");
						er=new EvsRelation(evsPeriod,evsType, evsItem_td,evsProcess_tr, evsOperate,processProp,itemProp,evMarksValues,evMarksName,markDefault);
						lEvsRelation.add(er);
					}else{
						EvsRelation erd=null;
						erd=new EvsRelation(evsPeriod,evsType,evsItem_td,evsProcess_tr);
						erd.delRelationByPeriodTypeItemProcess();
					}
				}	
			}
		}
		if(!evPeriodId.equals("")&&!evTypeId.equals("")){
			evsRelation.addEvRelation(lEvsRelation,evPeriodId,evTypeId);
		}
	}	
	}catch(Exception e){}	
}

response.sendRedirect(RedirectURL);

%>
