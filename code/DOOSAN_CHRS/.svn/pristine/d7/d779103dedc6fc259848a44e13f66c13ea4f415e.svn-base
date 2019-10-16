package com.ait.evs.command;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmor;
import com.ait.evs.EvsCraft;
import com.ait.evs.EvsPeriod;
import com.ait.evs.Evsupcode;
import com.ait.evs.business.EvsServices;
import com.ait.evs.evs0126.bean.BizEvsCode;
import com.ait.evs.evs0126.bean.EntEvsCode;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;
import com.ibm.icu.math.BigDecimal;

public class EvsjinxingsavevaluateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvslineaddvaluateCmd.class);
			
			@Override
			public String execute(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
				MessageSource messageSource ;
				int pageSize = 0;
				int pageGroupSize = 0;
				int currentPage = 0;
				int resultCount = 0;   
				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
			   String ev_emp_id = request.getParameter("ev_emp_id");
			  // String ev_period_id = request.getParameter("ev_period_id").trim();
			   String deptke = request.getParameter("deptke");  
			   String deptzhi = request.getParameter("deptzhi");  
			   String deptzu = request.getParameter("deptzu");  
		
			    
			EvsServices services = EvsServices.getInstance();
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			 simpleMap.put("empID", ev_emp_id);
			 simpleMap.put("period", "");
			 simpleMap.put("evTypeId", "");
			List listcht=new ArrayList();
			Evsupcode evsUpcode = new Evsupcode();
			EvsPeriod evsPeriod=new EvsPeriod();
			String countemp = "1";
			List searchCount;
			Object searchEmpList ;
			List searchCraftList;
			List searchSkitypeList;
			List searchPeriodList;
			List searchLineList;
			List searchAircraftList;
			List searchProcessList;
			List searchJobcontentList;
			List searchTypetionList;
			List searchEvsSkilelist;
			List searchEvsQualificationList;
			List searchEvsPurposeList;
			List<SimpleMap>  craftbyLine = null;
			String[] number = request.getParameterValues("number");
			try { 				    
				if(null!=number)
				   {					 
				      for (int i = 0 ; i < number.length ; ++i)
						{
				        	 
				        	 String  affirmNo = request.getParameter("affirmNo_"+i);
				        	 String  setupcodeNo = request.getParameter("setupcodeNo_"+i);
				        	 
				        	String  proficiency2 =  request.getParameter("proficiency1_"+i);
				        	String  standardaction2= request.getParameter("standardaction1_"+i);
				        	String  operationcom2 = request.getParameter("operationcom1_"+i);
				        	String  qualityofwork2 = request.getParameter("qualityofwork1_"+i);
				        	
				        	String  composite2 = request.getParameter("composite1_"+i);//单项得分
				        	
				        	String  skileid2 = request.getParameter("skileid1_"+i);
				        	
				        	String  craftid2 = request.getParameter("craftid1_"+i);
				        	String  lineid2 = request.getParameter("lineid_"+i);//岗位
				        	String  jobcontent2 = request.getParameter("jobcontent_"+i);//工序
				        	 String evsperiod = request.getParameter("evsperiod1_"+i); 
				        	
				        	
				        	
				        	EntEvsCode ent = BizEvsCode.selectid(jobcontent2);
				        	String descriptio2 = ent.getDescriptio();
				        	
				        	if(descriptio2==null)
				        		descriptio2 ="0";
				        	Double skillstored =0.0;
				        	
				        	BigDecimal descriptiod = new BigDecimal(descriptio2.toString());  
				            BigDecimal composited = new BigDecimal(composite2.toString());  
				            //BigDecimal ddd =  new BigDecimal("10"); 
				        
				        	skillstored = composited.multiply(descriptiod).doubleValue();
				        	
				        	
				        	String  sumscore1 = request.getParameter("sumscore1_"+i);				 							 			
				 			
				 			String  remark2 = request.getParameter("remark1_"+i);
				 			
				 			String  proveby1 = request.getParameter("proveby1_"+i);
				 			
				 			String  confirmdate1 = request.getParameter("confirmdate1_"+i);
				 			String  provedate1 = request.getParameter("provedate1_"+i);
				 			
				 			String  purpose1 = request.getParameter("purpose1_"+i);
				 			
				 			String  sheopercyq1 = request.getParameter("sheopercyq1_"+i);
				 			
				        	 System.out.print(affirmNo);
				        	 if(affirmNo!=null){
				        		 
				        	    evsUpcode.setSETUPCODENO(setupcodeNo);	 
				        	    
				        	    
		        				evsUpcode.setEVS_EMPID(ev_emp_id);
		    				    evsUpcode.setEV_PERIOD_ID(evsperiod);
		    				    evsUpcode.setEV_TYPE_ID("");
		    				    evsUpcode.setCRAFT(craftid2);
		    				    evsUpcode.setSKILLTYPE("");
		    				    evsUpcode.setLINE(lineid2);
		    				    evsUpcode.setAIRCRAFT("");
		    				    evsUpcode.setPROCESS("");
		    				    evsUpcode.setJOBCONTENT(jobcontent2);
		    				    evsUpcode.setTYPEOPERATION("");
		    				    evsUpcode.setQUALIFICATION("");
		    				    evsUpcode.setSKILLSCORE(String.valueOf(skillstored));
		    				    evsUpcode.setPURPOSE(purpose1);
		    				    evsUpcode.setPROFICIENCY(proficiency2);
		    				    evsUpcode.setSHEOPERCYQ(sheopercyq1);
		    				    evsUpcode.setOPERATIONCOM(operationcom2);
		    				    evsUpcode.setQUALITYOFWORK(qualityofwork2);
		    				    evsUpcode.setSTANDARDACTION(standardaction2);
		    				    evsUpcode.setCOMPOSITE(composite2);
		    				    evsUpcode.setSKILLLEVEL(skileid2);
		    				    evsUpcode.setREMARK(remark2);
		    				    evsUpcode.setCREATED_BY(admin.getAdminID());
		    				    evsUpcode.setSUMSCORE(sumscore1);
		    				    evsUpcode.setCONFIRMDATE(confirmdate1);
		    				    evsUpcode.setPROVEBY(proveby1);
		    				    evsUpcode.setPROVEDATE(provedate1);
		    				    evsUpcode.setDeptke(deptke);
		    				    evsUpcode.setDeptzhi(deptzhi);
		    				    evsUpcode.setDeptzu(deptzu);
		    				    
		    				    
		    				    listcht.add(evsUpcode);
		    					services.updateJinxing(listcht);
				    					    	 				      		    
				        	 }
				        	 	  
						  
					   }///循环结束
				   }///存在数据
				
				
					searchCount =  services.getEvsCountEmp(simpleMap);
					 searchEmpList = services.getEvsempcodeList(simpleMap);
					 searchPeriodList= services.getEvsYearIDList();//查询年度
					 searchCraftList = services.getEvsCraftList();//查询工种
					 searchSkitypeList = services.getEvsSkitypeList(); //查询技能类型
					 searchLineList = services.getEvsLineList(); //查询Line线
					 searchAircraftList = services.getEvsAircraftList(); //查询机种
					 searchProcessList = services.getEvsProcesstList(); //查询工序
					 searchJobcontentList = services.getEvsJobcontentList(); //查询作业内容
					 searchTypetionList = services.getEvsTypetionList();//查询作业类型
					 searchEvsSkilelist = services.getEvsSkilelist();//技能等级
					 searchEvsQualificationList = services.getEvsQualificationList();//职业资格
					 searchEvsPurposeList = services.getEvsPurposeList();//培训目标
					
					 
				}
		     catch (Exception e) {
				logger.error(e.toString());
				request.setAttribute("update", 2);
				throw new GlRuntimeException(
						"The information Exception when running the IsertExpiredContract. ",
						e);
			}
			resultCount = listcht.size();
			request.setAttribute("craftbyLine", craftbyLine); 
			request.setAttribute("searchEmpList", searchEmpList); 
			request.setAttribute("searchPeriodList", searchPeriodList); 
			request.setAttribute("searchCraftList", searchCraftList); 
			request.setAttribute("searchSkitypeList", searchSkitypeList); 
			request.setAttribute("searchLineList", searchLineList); 
			request.setAttribute("searchAircraftList", searchAircraftList); 
			request.setAttribute("searchProcessList", searchProcessList); 
			request.setAttribute("searchJobcontentList", searchJobcontentList); 
			request.setAttribute("searchTypetionList", searchTypetionList); 
			request.setAttribute("searchEvsSkilelist", searchEvsSkilelist); 
			request.setAttribute("searchEvsQualificationList", searchEvsQualificationList); 
			request.setAttribute("searchEvsPurposeList", searchEvsPurposeList); 
			request.setAttribute("listcht", listcht); 
			request.setAttribute("searchCount", searchCount); //数据库原有的数据有几条查几条
			request.setAttribute("craftid", ""); 
			request.setAttribute("skileid", ""); 
			request.setAttribute("countemp", countemp);        
			request.setAttribute("resultCount", resultCount);                    
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);  
			request.setAttribute("update", 1);
			return "/evs0202_eval.jsp";
		}
}
