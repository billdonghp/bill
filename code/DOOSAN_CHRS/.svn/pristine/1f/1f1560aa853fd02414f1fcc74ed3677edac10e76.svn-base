package com.ait.ev.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;

import com.ait.ev.bean.EvaluateAffirmParam;
import com.ait.ev.bean.EvaluateAffirmor;
import com.ait.ev.bean.EvaluateInfo;
import com.ait.ev.business.EvaluateApplyServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.ev.bean.EvaluateItem;
import com.ait.utils.FormUtil;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (zhangji@ait.net.cn)
 * @Date 2011-9-19
 * 
 */
public class EvaluateApplyCommand extends ArAbstractCommand {
	
	private EvaluateApplyServices evServices =null;
	
	private EssSysparam essSysparam = null;
	
	private Mail mail ;
	
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://portal.doosan.com" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	public EvaluateApplyCommand(){
		evServices = new EvaluateApplyServices();
		
		mail = new Mail() ;
	}

	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		String returnPage=null;
	    if(!content.equals("") && content.equals("evaluateApplyView")){
			returnPage = this.getEvaluateApplyView(request, admin);
			//returnPage="/ev_apply_view.jsp";
		}
		else if(!content.equals("") && content.equals("evaluateApplyAdd")){
			this.addEvaluateApply(request, admin);
			this.getEvaluateApplyView(request, admin);
			returnPage="/ev_apply_view.jsp";
		}else if(!content.equals("") && content.equals("updateEvaluateDetail")){
			this.updateEvaluateDetail(request, admin);
			this.getEvaluateApplyView(request, admin);
			returnPage="/ev_detail_view.jsp";
		
		}else if(!content.equals("") && content.equals("applyEvaluateDetail")){
			this.applyEvaluateDetail(request, admin);
			this.getEvaluateApplyView(request, admin);
			returnPage="/ev_detail_view.jsp";
		
		}else{
			return "error.jsp";
		}
//		
		return returnPage;
	}
	
	/*得到评价申请信息*/
	 public String getEvaluateApplyView(HttpServletRequest request,AdminBean admin){	  
		   
		 String page = "";
		 try{
			    this.putToolbarInfo(request);
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("ADMIN_ID", admin.getAdminID());
				parameterObject.set("cpnyId", admin.getCpnyId());	
				int pageSize =10;
				int pageGroupSize =10;
				int currentPage = 0;
				List deptList=evServices.getEvaluateApplyDeptList(parameterObject);
				List positionList=evServices.getEvaluateApplyPositionList(parameterObject);
				
				String yearMonth = request.getParameter("year") + request.getParameter("month");
				if(request.getParameter("selectDept")== null){
					if(deptList.size()>0){
						SimpleMap s =(SimpleMap)deptList.get(0);
						String sd = s.getString("DEPTID");
						//parameterObject.set("dept", sd);
						//request.setAttribute("selectDept", sd);
					}
				}else{
					parameterObject.set("dept", request.getParameter("selectDept"));
					request.setAttribute("selectDept", request.getParameter("selectDept"));
				}
				
				if(request.getParameter("selectPosition")== null){
					if(positionList.size()>0){
						SimpleMap p =(SimpleMap)positionList.get(0);
						String pl = p.getString("AFFIRM_POSITION");
						parameterObject.set("position", pl);
						request.setAttribute("selectPosition", pl);
					}
				}else{
					parameterObject.set("position", request.getParameter("selectPosition"));
					request.setAttribute("selectPosition", request.getParameter("selectPosition"));
				}
				
				if(request.getParameter("year")==null || request.getParameter("month")==null){
					
					// 取日期参数
					GregorianCalendar currentDay = new GregorianCalendar();
					currentDay.add(2,-1);//月份减一
					int m = currentDay.get(Calendar.MONTH) + 1;
					int y = currentDay.get(Calendar.YEAR);

					String month = ("0" + String.valueOf(m)).substring(("0" + String
							.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
					String year = String.valueOf(y);

					parameterObject.set("yearMonth", year + month);
					request.setAttribute("year", year);
					request.setAttribute("month", month);
					
				}else{
					parameterObject.set("yearMonth", yearMonth);
					request.setAttribute("year", request.getParameter("year"));
					request.setAttribute("month", request.getParameter("month"));
				}
				
				
				
				
				
				//parameterObject.set("position", request.getParameter("selectPosition"));
				//parameterObject.set("yearMonth", yearMonth);	
				
				String status_code = request.getParameter("status_code") != null ? request.getParameter("status_code") : "N";
				if (status_code.equals("N")) {
				
						if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
							currentPage = Integer.parseInt(request.getParameter("currentPage"));
						
						List itemList = evServices.getEvaluateItem(parameterObject.getString("yearMonth"),parameterObject.getString("dept"),parameterObject.getString("position"),parameterObject.getString("ADMIN_ID"),1);
						
						//String itemId = evServices.getEvaluateApplyItem(parameterObject);
				       //取得数据行数
						int resultCount = evServices.getEvaluateApplyListCnt(parameterObject);
						List evaluateApplyList=evServices.getEvaluateApplyList(parameterObject,currentPage,pageSize);
						
						EvaluateInfo evaluateInfo = new EvaluateInfo();
						for (int i = 0; i < evaluateApplyList.size(); i++) {
							SimpleMap parameterObject1 = new SimpleMap();
							evaluateInfo = (EvaluateInfo) evaluateApplyList.get(i);
							
							
							parameterObject1.setString("DEPTID", evaluateInfo.getDEPTID()) ;
							parameterObject1.setString("POSTCODE", evaluateInfo.getPOST_CODE());
							
							List affirmorList=evServices.getEvaluateAffirmorList(parameterObject1);
							
							EvaluateAffirmor evAffirmor = new EvaluateAffirmor();
							String AffirmData = "";
							for (int j = 0; j < affirmorList.size(); j++) {
								evAffirmor = (EvaluateAffirmor) affirmorList.get(j);
								AffirmData += evAffirmor.getAffirmLevel()
										+ evAffirmor.getAffirmorName() + "<br>";
		//						AffirmData += essAffirmor.getAffirmLevelOriginal()
		//						+ essAffirmor.getAffirmorName() + "<br>";
							}
							
							if (AffirmData.equals("")) {
								evaluateInfo.setAffirmData(null);
							} else {
								evaluateInfo.setAffirmData(AffirmData.substring(0,
										AffirmData.length() - 4));
							}
							
							
		
							
						}
						
						request.setAttribute("deptList",deptList);
						request.setAttribute("positionList",positionList);
						
//						request.setAttribute("selectDept", request.getParameter("selectDept"));
//						request.setAttribute("selectPosition", request.getParameter("selectPosition"));
//						request.setAttribute("year", request.getParameter("year"));
//						request.setAttribute("month", request.getParameter("month"));
						request.setAttribute("itemList",itemList);
						EvaluateItem evaluateItem = new EvaluateItem();
						boolean flag = false;
						for (int j = 0; j < itemList.size(); j++) {
							evaluateItem = (EvaluateItem) itemList.get(j);
							String itemId = evaluateItem.getItemId();
							if ("item34".equals(itemId) || "item35".equals(itemId) || "item36".equals(itemId)
									 || "item37".equals(itemId) || "item38".equals(itemId) || "item39".equals(itemId)) {
								flag = true;
							}
						}
						request.setAttribute("flag",flag);
						//request.setAttribute("itemId",itemId);
						request.setAttribute("status_code",status_code);
						request.setAttribute("evaluateApplyList",evaluateApplyList);
						request.setAttribute("resultCount", resultCount);
						request.setAttribute("currentPage", currentPage);
						request.setAttribute("pageSize", pageSize);
						request.setAttribute("pageGroupsize", pageGroupSize);
						if(evaluateApplyList==null ||evaluateApplyList.size()==0){
						 request.setAttribute("errorMsg", "暂无相关信息,请尝试其他查询条件！");
						}
						page = "/ev_apply_view.jsp";
				
				
				}else if (status_code.equals("Y")){
					
					if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
						currentPage = Integer.parseInt(request.getParameter("currentPage"));
					List itemList = evServices.getEvaluateItem(parameterObject.getString("yearMonth"),parameterObject.getString("dept"),parameterObject.getString("position"),parameterObject.getString("ADMIN_ID"),1);
					
			       //取得数据行数
					int resultCount = evServices.getEvaluateDetailListCnt(parameterObject);
					List evaluateDetailList=evServices.getEvaluateDetailList(parameterObject,currentPage,pageSize);
					
					EvaluateInfo evaluateInfo = new EvaluateInfo();
					for (int i = 0; i < evaluateDetailList.size(); i++) {
						
						EvaluateInfo evi = (EvaluateInfo) evaluateDetailList.get(i);

						evi.setAffirmorList((ArrayList) evServices.getAffirmorList(evi.getNo()));
						
									// 没有决裁过的
						if (evi.getAffirm_flag() == 0 )
										// 可进行修改
						evi.setOpFlag(1);
						
						if (evi.getAffirm_flag() == 1 )
							// 决裁过的
			            evi.setOpFlag(0);
								
								
						
					}
					
					MessageSource messageSource = new MessageSource("ess", admin
							.getLocale(), "UTF-8");
					String message1 = messageSource
							.getMessage("display.ess.approval.pending");
					String message2 = messageSource
							.getMessage("display.ess.approval.approved");
					String message3 = messageSource
							.getMessage("display.ess.approval.rejected");
					Map statusMap = new HashMap();
					Map colorMap = new HashMap();
					if (evaluateDetailList.size() > 0) {
						statusMap.put("0", message1);
						statusMap.put("1", message2);
						statusMap.put("2", message3);

						colorMap.put("0", "#990099");
						colorMap.put("1", "#00CC00");
						colorMap.put("2", "#CC0000");
					}
					
					request.setAttribute("deptList",deptList);
					request.setAttribute("positionList",positionList);
					
//					request.setAttribute("selectDept", request.getParameter("selectDept"));
//					request.setAttribute("selectPosition", request.getParameter("selectPosition"));
//					request.setAttribute("year", request.getParameter("year"));
//					request.setAttribute("month", request.getParameter("month"));
					request.setAttribute("itemList",itemList);
					request.setAttribute("status_code",status_code);
					
					request.setAttribute("statusMap", statusMap);
					request.setAttribute("colorMap", colorMap);
					
					request.setAttribute("evaluateDetailList",evaluateDetailList);
					request.setAttribute("resultCount", resultCount);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("pageSize", pageSize);
					request.setAttribute("pageGroupsize", pageGroupSize);
					if(evaluateDetailList==null ||evaluateDetailList.size()==0){
					 request.setAttribute("errorMsg", "暂无相关信息,请尝试其他查询条件！");
					}
					
					page = "/ev_detail_view.jsp";
					
				}
				
				
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get ev  getEvaluateDetailList Exception. ", e);
			}		 
		 return page;
		   
	   }
	 
	 
	 /*保存评价申请信息*/
	 public String addEvaluateApply(HttpServletRequest request,AdminBean admin){	  
		   try{
			   this.putToolbarInfo(request);
				SimpleMap parameterObject = null;
				List<EvaluateInfo> applyList = new ArrayList<EvaluateInfo>() ;
				List<EvaluateInfo> insertApplyList = new ArrayList<EvaluateInfo>() ; // 插入申请人数据;
				List<String> errorMsgList = new ArrayList<String>();
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("ADMIN_ID", admin.getAdminID());
				parameterObject.set("cpnyId", admin.getCpnyId());	
				int pageSize =10;
				int pageGroupSize =10;
				int currentPage = 0;
				
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
				String yearMonth = request.getParameter("year") + request.getParameter("month");
				
				parameterObject.set("dept", request.getParameter("selectDept"));
				parameterObject.set("position", request.getParameter("selectPosition"));
				parameterObject.set("yearMonth", yearMonth);	
				
				int applySize = parameterObject.getInt("applySize");
//				
//				String[] str_affirmNos = request.getParameterValues("ck_");
//				if (str_affirmNos == null)
//					str_affirmNos = new String[0];
				
				
				SimpleMap map = new SimpleMap() ;
				for(int i = 0 ; i < applySize ; i ++)		
				{
					//map.clear() ;
					EvaluateInfo ev = new EvaluateInfo();
					ev.setCk(StringUtil.checkNull(parameterObject.getString("ck_" + i)));
					ev.setPERSON_ID(parameterObject.getString("person_id_" + i));
					ev.setMONTH(parameterObject.getString("month_" + i));
					ev.setDEPTID(parameterObject.getString("deptid_" + i));
					ev.setPOST_CODE(parameterObject.getString("post_code_" + i));
					ev.setTOTAL(NumberUtil.parseDouble(parameterObject.getString("total_" + i),0));
					ev.setREMARK(parameterObject.getString("remark_" + i));
					ev.setLAST_MONTH_PLACE(NumberUtil.parseInt(parameterObject.getString("last_month_place_" + i)));
					ev.setLAST_MONTH_AMOUNT(NumberUtil.parseInt(parameterObject.getString("last_month_amount_" + i)));
					
					ev.setCreatedBy(admin.getAdminID());
					
					ev.setITEM1(NumberUtil.parseDouble(parameterObject.getString("item1_" + i),0));
					ev.setITEM2(NumberUtil.parseDouble(parameterObject.getString("item2_" + i),0));
					ev.setITEM3(NumberUtil.parseDouble(parameterObject.getString("item3_" + i),0));
					ev.setITEM4(NumberUtil.parseDouble(parameterObject.getString("item4_" + i),0));
					ev.setITEM5(NumberUtil.parseDouble(parameterObject.getString("item5_" + i),0));
					ev.setITEM6(NumberUtil.parseDouble(parameterObject.getString("item6_" + i),0));
					ev.setITEM7(NumberUtil.parseDouble(parameterObject.getString("item7_" + i),0));
					ev.setITEM8(NumberUtil.parseDouble(parameterObject.getString("item8_" + i),0));
					ev.setITEM9(NumberUtil.parseDouble(parameterObject.getString("item9_" + i),0));
					ev.setITEM10(NumberUtil.parseDouble(parameterObject.getString("item10_" + i),0));
					ev.setITEM11(NumberUtil.parseDouble(parameterObject.getString("item11_" + i),0));
					ev.setITEM12(NumberUtil.parseDouble(parameterObject.getString("item12_" + i),0));
					ev.setITEM13(NumberUtil.parseDouble(parameterObject.getString("item13_" + i),0));
					ev.setITEM14(NumberUtil.parseDouble(parameterObject.getString("item14_" + i),0));
					ev.setITEM15(NumberUtil.parseDouble(parameterObject.getString("item15_" + i),0));
					ev.setITEM16(NumberUtil.parseDouble(parameterObject.getString("item16_" + i),0));
					ev.setITEM17(NumberUtil.parseDouble(parameterObject.getString("item17_" + i),0));
					ev.setITEM18(NumberUtil.parseDouble(parameterObject.getString("item18_" + i),0));
					ev.setITEM19(NumberUtil.parseDouble(parameterObject.getString("item19_" + i),0));
					ev.setITEM20(NumberUtil.parseDouble(parameterObject.getString("item20_" + i),0));
					ev.setITEM21(NumberUtil.parseDouble(parameterObject.getString("item21_" + i),0));
					ev.setITEM22(NumberUtil.parseDouble(parameterObject.getString("item22_" + i),0));
					ev.setITEM23(NumberUtil.parseDouble(parameterObject.getString("item23_" + i),0));
					ev.setITEM24(NumberUtil.parseDouble(parameterObject.getString("item24_" + i),0));
					ev.setITEM25(NumberUtil.parseDouble(parameterObject.getString("item25_" + i),0));
					ev.setITEM26(NumberUtil.parseDouble(parameterObject.getString("item26_" + i),0));
					ev.setITEM27(NumberUtil.parseDouble(parameterObject.getString("item27_" + i),0));
					ev.setITEM28(NumberUtil.parseDouble(parameterObject.getString("item28_" + i),0));
					ev.setITEM29(NumberUtil.parseDouble(parameterObject.getString("item29_" + i),0));
					ev.setITEM30(NumberUtil.parseDouble(parameterObject.getString("item30_" + i),0));
					ev.setITEM31(NumberUtil.parseDouble(parameterObject.getString("item31_" + i),0));
					ev.setITEM32(NumberUtil.parseDouble(parameterObject.getString("item32_" + i),0));
					ev.setITEM33(NumberUtil.parseDouble(parameterObject.getString("item33_" + i),0));
					ev.setITEM34(NumberUtil.parseDouble(parameterObject.getString("item34_" + i),0));
					ev.setITEM35(NumberUtil.parseDouble(parameterObject.getString("item35_" + i),0));
					ev.setITEM36(NumberUtil.parseDouble(parameterObject.getString("item36_" + i),0));
					ev.setITEM37(NumberUtil.parseDouble(parameterObject.getString("item37_" + i),0));
					ev.setITEM38(NumberUtil.parseDouble(parameterObject.getString("item38_" + i),0));
					ev.setITEM39(NumberUtil.parseDouble(parameterObject.getString("item39_" + i),0));
					ev.setITEM40(NumberUtil.parseDouble(parameterObject.getString("item40_" + i),0));
					ev.setITEM41(NumberUtil.parseDouble(parameterObject.getString("item41_" + i),0));
					ev.setITEM42(NumberUtil.parseDouble(parameterObject.getString("item42_" + i),0));
					ev.setITEM43(NumberUtil.parseDouble(parameterObject.getString("item43_" + i),0));
					ev.setITEM44(NumberUtil.parseDouble(parameterObject.getString("item44_" + i),0));
					ev.setITEM45(NumberUtil.parseDouble(parameterObject.getString("item45_" + i),0));
					ev.setITEM46(NumberUtil.parseDouble(parameterObject.getString("item46_" + i),0));
					ev.setITEM47(NumberUtil.parseDouble(parameterObject.getString("item47_" + i),0));
					ev.setITEM48(NumberUtil.parseDouble(parameterObject.getString("item48_" + i),0));
					ev.setITEM49(NumberUtil.parseDouble(parameterObject.getString("item49_" + i),0));
					ev.setITEM50(NumberUtil.parseDouble(parameterObject.getString("item50_" + i),0));
					ev.setITEM51(NumberUtil.parseDouble(parameterObject.getString("item51_" + i),0));
					ev.setITEM52(NumberUtil.parseDouble(parameterObject.getString("item52_" + i),0));
					ev.setITEM53(NumberUtil.parseDouble(parameterObject.getString("item53_" + i),0));
					ev.setITEM54(NumberUtil.parseDouble(parameterObject.getString("item54_" + i),0));
					ev.setITEM55(NumberUtil.parseDouble(parameterObject.getString("item55_" + i),0));
					ev.setITEM56(NumberUtil.parseDouble(parameterObject.getString("item56_" + i),0));
					ev.setITEM57(NumberUtil.parseDouble(parameterObject.getString("item57_" + i),0));
					ev.setITEM58(NumberUtil.parseDouble(parameterObject.getString("item58_" + i),0));
					ev.setITEM59(NumberUtil.parseDouble(parameterObject.getString("item59_" + i),0));
					ev.setITEM60(NumberUtil.parseDouble(parameterObject.getString("item60_" + i),0));
					ev.setITEM61(NumberUtil.parseDouble(parameterObject.getString("item61_" + i),0));
					ev.setITEM62(NumberUtil.parseDouble(parameterObject.getString("item62_" + i),0));
					ev.setITEM0(NumberUtil.parseDouble(parameterObject.getString("item0_" + i),0));
					

					
					//System.out.println(StringUtil.checkNull(parameterObject.getString("ck_" + i))+NumberUtil.parseInt(parameterObject.getString("item1_" + i),0)+"%%%%%%%%%%%%%%%%"+parameterObject.getString("person_id_" + i)+parameterObject.getString("month_" + i)+parameterObject.getString("deptid_" + i)+parameterObject.getString("post_code_" + i));

				

				  applyList.add(ev) ;
				}
				
				SimpleMap parameterObject2 = new SimpleMap();
				
				Iterator iterator = applyList.iterator();
				
				while (iterator.hasNext()) {

					EvaluateInfo evi = (EvaluateInfo) iterator.next();
					//System.out.println("CK:"+evi.getCk());
//					 验证申请(只取得第一个不符和条件的信息)
					if (evi.getCk() != null && evi.getCk().length() > 0) {
										
						List affirmList = new ArrayList() ;
						
						SimpleMap parameterObject1 = new SimpleMap();
						parameterObject1.setString("DEPTID", evi.getDEPTID()) ;
						parameterObject1.setString("POSTCODE", evi.getPOST_CODE());
						
						parameterObject2.setString("dept", evi.getDEPTID()) ;
						parameterObject2.setString("yearMonth", evi.getMONTH());
						parameterObject2.setString("post", evi.getPOST_CODE());
						
						affirmList=evServices.getEvaluateAffirmorList(parameterObject1);
						
						
						if(affirmList == null || affirmList.size() == 0)
						{
							request.setAttribute("errorMsg", "暂无相关信息！");
							
						}
						
//							
							// 添加决裁者列表
							evi.setAffirmorList((ArrayList)affirmList);

							insertApplyList.add(evi) ;
									
					}
				}
				//HashMap result = null;
				
				// 插入评价申请数据
				if (errorMsgList.isEmpty()) {
					List listResult = evServices.doEvaluateApplyByBatch(insertApplyList);
					
//					String post = parameterObject2.getString("post");
//					
//					if("C_12004_1355928".equals(post)){
//						evServices.updateEvaluateDetailSortGongren(parameterObject2);//排序
//					}else{
//						evServices.updateEvaluateDetailSortNoGongren(parameterObject2);
//					}
					
					
					
					SimpleMap simpleMap5 = new SimpleMap();
					StringBuffer buffer5 = new StringBuffer(100);
					Iterator iterator5 = listResult.iterator();
					for(int i= 0;iterator5.hasNext();i++){
						
						EvaluateInfo param = (EvaluateInfo)iterator5.next();
						buffer5.append("'"+param.getNo()+"',");	
					}
					simpleMap5.setString("applyStr", buffer5.substring(0,buffer5.length()-1));
					simpleMap5.setString("curAffirmor", admin.getAdminID());
					List list4 = evServices.getEvaluateDeptAndPosition(simpleMap5);
					
					if(list4.size()>0){
						Iterator iterator4 = list4.iterator();
						SimpleMap simpleMap2 = new SimpleMap();
						for(;iterator4.hasNext();){
							SimpleMap map4 = (SimpleMap)iterator4.next();
							simpleMap2.setString("yearMonth", map4.getString("EVALUATE_MONTH"));
							simpleMap2.setString("dept", map4.getString("DEPTID"));
							simpleMap2.setString("post", map4.getString("POSITION"));
							
							if("C_12004_1355928".equals(StringUtil.checkNull(map4.getString("POSITION")))){
								evServices.updateEvaluateDetailSortGongren(simpleMap2);//排序
							}else{
								if("78020027".equals(StringUtil.checkNull(map4.getString("DEPTID")))
										||"780G6200".equals(StringUtil.checkNull(map4.getString("DEPTID")))
										||"78020029".equals(StringUtil.checkNull(map4.getString("DEPTID")))){
									evServices.updateEvaluateDetailSortGongren(simpleMap2);//排序
								}else{
								evServices.updateEvaluateDetailSortNoGongren(simpleMap2);
								}
							}
						}
					
						
					}
					

				}
			


					
				
				
				request.setAttribute("selectDept", parameterObject.getString("selectDept"));
				request.setAttribute("selectPosition", parameterObject.getString("selectPosition"));
				request.setAttribute("year", parameterObject.getString("year"));
				request.setAttribute("month", parameterObject.getString("month"));
				
				


//				if(evaluateApplyList==null ||evaluateApplyList.size()==0){
//				 request.setAttribute("errorMsg", "暂无相关信息！");
//				}
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get ev  getEvaluateApplyList Exception. ", e);
			}		 
		 return "/ev_apply_view.jsp";
		 
		 
		   
	   }
	 
	 
	 public void sendEvaluateMail(int[]Applyno,String setTo,int flag)throws Exception{
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("setTo", setTo);
			SimpleMap result = (SimpleMap) evServices.setToEmail(parameterObject);
			String applyStr="";
			for(int i=0;i<Applyno.length;i++){
				applyStr +=("'"+Applyno[i]+"',");
			}
			parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
			List applyResult = (List) evServices.applyEvaluateResultNoAffirmor(parameterObject);
			SimpleMap inputData = new SimpleMap();
			String emailTitle="";	
			StringBuffer content = new StringBuffer();
			if(flag==1){
				emailTitle="评价申请";
			}else if(flag==2){
				emailTitle="评价申请,被否决";
			}else{
				emailTitle="评价申请";
			}
			String emailAddress=result.getString("EMAIL");
			for(int i=0;i<applyResult.size();i++){
				SimpleMap simpleMap=(SimpleMap)applyResult.get(i);

				content.append("姓名:").append(simpleMap.getString("CHINESENAME"));
				content.append("<br>").append("评价总分:").append(StringUtil.checkNull(simpleMap.getString("TOTAL")));
				content.append("<br>").append("说明事项:").append(StringUtil.checkNull(simpleMap.getString("REMARK")));
				
				
				content.append("<br>");
				content.append("--------------------------------------------------------------------------");
				content.append("<br>");
			}
			content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>");
			
			inputData.setString("EMAIL_TITLE", emailTitle);

			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());

			inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

			new Mail().sendMail(inputData) ;	
		}

	 /*修改已申请的评价信息*/
	 public void updateEvaluateDetail(HttpServletRequest request,AdminBean admin){	  
		 try {
				admin = (AdminBean) request.getSession().getAttribute("admin") ;
				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				String menuCode = "ess0404";
			
				List<EvaluateInfo> updateList = new ArrayList<EvaluateInfo>() ;
//              
                String post = "";

				int flag = NumberUtil.parseInt(request.getParameter("flag"));
				String adminId = admin.getAdminID();
				
				int applySize = parameterObject.getInt("applySize");
				for (int i = 0; i < applySize; i++) {
					
					if(StringUtil.checkNull(request.getParameter("applyNo_" + i)) !=null && StringUtil.checkNull(request.getParameter("applyNo_" + i)).length()>0 )
					{
							
							EvaluateInfo ev = new EvaluateInfo();
							ev.setNo(NumberUtil.parseInt(parameterObject.getString("applyNo_" + i)));
							ev.setREMARK(StringUtil.checkNull(parameterObject.getString("remark_" + i)));
							ev.setACTIVITY(StringUtil.checkNull(parameterObject.getString("activity_" + i)));
							ev.setTOTAL(NumberUtil.parseDouble(parameterObject.getString("total_" + i),0));
							ev.setITEM1(NumberUtil.parseDouble(parameterObject.getString("item1_" + i),0));
							ev.setITEM2(NumberUtil.parseDouble(parameterObject.getString("item2_" + i),0));
							ev.setITEM3(NumberUtil.parseDouble(parameterObject.getString("item3_" + i),0));
							ev.setITEM4(NumberUtil.parseDouble(parameterObject.getString("item4_" + i),0));
							ev.setITEM5(NumberUtil.parseDouble(parameterObject.getString("item5_" + i),0));
							ev.setITEM6(NumberUtil.parseDouble(parameterObject.getString("item6_" + i),0));
							ev.setITEM7(NumberUtil.parseDouble(parameterObject.getString("item7_" + i),0));
							ev.setITEM8(NumberUtil.parseDouble(parameterObject.getString("item8_" + i),0));
							ev.setITEM9(NumberUtil.parseDouble(parameterObject.getString("item9_" + i),0));
							ev.setITEM10(NumberUtil.parseDouble(parameterObject.getString("item10_" + i),0));
							ev.setITEM11(NumberUtil.parseDouble(parameterObject.getString("item11_" + i),0));
							ev.setITEM12(NumberUtil.parseDouble(parameterObject.getString("item12_" + i),0));
							ev.setITEM13(NumberUtil.parseDouble(parameterObject.getString("item13_" + i),0));
							ev.setITEM14(NumberUtil.parseDouble(parameterObject.getString("item14_" + i),0));
							ev.setITEM15(NumberUtil.parseDouble(parameterObject.getString("item15_" + i),0));
							ev.setITEM16(NumberUtil.parseDouble(parameterObject.getString("item16_" + i),0));
							ev.setITEM17(NumberUtil.parseDouble(parameterObject.getString("item17_" + i),0));
							ev.setITEM18(NumberUtil.parseDouble(parameterObject.getString("item18_" + i),0));
							ev.setITEM19(NumberUtil.parseDouble(parameterObject.getString("item19_" + i),0));
							ev.setITEM20(NumberUtil.parseDouble(parameterObject.getString("item20_" + i),0));
							ev.setITEM21(NumberUtil.parseDouble(parameterObject.getString("item21_" + i),0));
							ev.setITEM22(NumberUtil.parseDouble(parameterObject.getString("item22_" + i),0));
							ev.setITEM23(NumberUtil.parseDouble(parameterObject.getString("item23_" + i),0));
							ev.setITEM24(NumberUtil.parseDouble(parameterObject.getString("item24_" + i),0));
							ev.setITEM25(NumberUtil.parseDouble(parameterObject.getString("item25_" + i),0));
							ev.setITEM26(NumberUtil.parseDouble(parameterObject.getString("item26_" + i),0));
							ev.setITEM27(NumberUtil.parseDouble(parameterObject.getString("item27_" + i),0));
							ev.setITEM28(NumberUtil.parseDouble(parameterObject.getString("item28_" + i),0));
							ev.setITEM29(NumberUtil.parseDouble(parameterObject.getString("item29_" + i),0));
							ev.setITEM30(NumberUtil.parseDouble(parameterObject.getString("item30_" + i),0));
							ev.setITEM31(NumberUtil.parseDouble(parameterObject.getString("item31_" + i),0));
							ev.setITEM32(NumberUtil.parseDouble(parameterObject.getString("item32_" + i),0));
							ev.setITEM33(NumberUtil.parseDouble(parameterObject.getString("item33_" + i),0));
							ev.setITEM34(NumberUtil.parseDouble(parameterObject.getString("item34_" + i),0));
							ev.setITEM35(NumberUtil.parseDouble(parameterObject.getString("item35_" + i),0));
							ev.setITEM36(NumberUtil.parseDouble(parameterObject.getString("item36_" + i),0));
							ev.setITEM37(NumberUtil.parseDouble(parameterObject.getString("item37_" + i),0));
							ev.setITEM38(NumberUtil.parseDouble(parameterObject.getString("item38_" + i),0));
							ev.setITEM39(NumberUtil.parseDouble(parameterObject.getString("item39_" + i),0));
							ev.setITEM40(NumberUtil.parseDouble(parameterObject.getString("item40_" + i),0));
							ev.setITEM41(NumberUtil.parseDouble(parameterObject.getString("item41_" + i),0));
							ev.setITEM42(NumberUtil.parseDouble(parameterObject.getString("item42_" + i),0));
							ev.setITEM43(NumberUtil.parseDouble(parameterObject.getString("item43_" + i),0));
							ev.setITEM44(NumberUtil.parseDouble(parameterObject.getString("item44_" + i),0));
							ev.setITEM45(NumberUtil.parseDouble(parameterObject.getString("item45_" + i),0));
							ev.setITEM46(NumberUtil.parseDouble(parameterObject.getString("item46_" + i),0));
							ev.setITEM47(NumberUtil.parseDouble(parameterObject.getString("item47_" + i),0));
							ev.setITEM48(NumberUtil.parseDouble(parameterObject.getString("item48_" + i),0));
							ev.setITEM49(NumberUtil.parseDouble(parameterObject.getString("item49_" + i),0));
							ev.setITEM50(NumberUtil.parseDouble(parameterObject.getString("item50_" + i),0));
							ev.setITEM51(NumberUtil.parseDouble(parameterObject.getString("item51_" + i),0));
							ev.setITEM52(NumberUtil.parseDouble(parameterObject.getString("item52_" + i),0));
							ev.setITEM53(NumberUtil.parseDouble(parameterObject.getString("item53_" + i),0));
							ev.setITEM54(NumberUtil.parseDouble(parameterObject.getString("item54_" + i),0));
							ev.setITEM55(NumberUtil.parseDouble(parameterObject.getString("item55_" + i),0));
							ev.setITEM56(NumberUtil.parseDouble(parameterObject.getString("item56_" + i),0));
							ev.setITEM57(NumberUtil.parseDouble(parameterObject.getString("item57_" + i),0));
							ev.setITEM58(NumberUtil.parseDouble(parameterObject.getString("item58_" + i),0));
							ev.setITEM59(NumberUtil.parseDouble(parameterObject.getString("item59_" + i),0));
							ev.setITEM60(NumberUtil.parseDouble(parameterObject.getString("item60_" + i),0));
							ev.setITEM61(NumberUtil.parseDouble(parameterObject.getString("item61_" + i),0));
							ev.setITEM62(NumberUtil.parseDouble(parameterObject.getString("item62_" + i),0));
							ev.setITEM0(NumberUtil.parseDouble(parameterObject.getString("item0_" + i),0));
							
							parameterObject.set("yearMonth", StringUtil.checkNull(parameterObject.getString("yearMonth_" + i)));
							parameterObject.set("dept", StringUtil.checkNull(parameterObject.getString("dept_" + i)));
							post=StringUtil.checkNull(parameterObject.getString("post_" + i));
							parameterObject.set("post", StringUtil.checkNull(parameterObject.getString("post_" + i)));
							
							
							updateList.add(ev) ;		
					}
				}
				//flag 1,通过,2,否决
				
				int result = evServices.updateEvaluateDetail(updateList);
				
				
				if(result > 0){//根据不同的职级进行排序
						
						SimpleMap simpleMap5 = new SimpleMap();
						StringBuffer buffer5 = new StringBuffer(100);
						Iterator iterator5 = updateList.iterator();
						for(int i= 0;iterator5.hasNext();i++){
							
							EvaluateInfo param = (EvaluateInfo)iterator5.next();
							buffer5.append("'"+param.getNo()+"',");	
						}
						simpleMap5.setString("applyStr", buffer5.substring(0,buffer5.length()-1));
						simpleMap5.setString("curAffirmor", admin.getAdminID());
						List list4 = evServices.getEvaluateDeptAndPosition(simpleMap5);
						
						if(list4.size()>0){
							Iterator iterator = list4.iterator();
							SimpleMap simpleMap2 = new SimpleMap();
							for(;iterator.hasNext();){
								SimpleMap map = (SimpleMap)iterator.next();
								simpleMap2.setString("yearMonth", map.getString("EVALUATE_MONTH"));
								simpleMap2.setString("dept", map.getString("DEPTID"));
								simpleMap2.setString("post", map.getString("POSITION"));
								
								if("C_12004_1355928".equals(StringUtil.checkNull(map.getString("POSITION")))){
									evServices.updateEvaluateDetailSortGongren(simpleMap2);//排序
								}else{
									evServices.updateEvaluateDetailSortNoGongren(simpleMap2);
								}
								
								evServices.updateEvaluateDetailSortZero(simpleMap2); //把总分修改为零的信息进行名次处理
							}
						
							
						}
						
				}
				if(result > 0){//申请后的修改再次发送邮件
						Iterator iter = updateList.iterator();
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);
						
						for(;iter.hasNext();){
							EvaluateInfo evaluateInfo = (EvaluateInfo)iter.next();
							if("0".equals(evaluateInfo.getACTIVITY())){//已经申请的，并且未决裁的信息
								buffer.append("'"+evaluateInfo.getNo()+"'");
								if(iter.hasNext()){								
									buffer.append(" , ");
								}
							}
							
						}
						simpleMap.set("applyStr",buffer.toString());
						simpleMap.set("applyLevel",2);//二级为决裁表中的第一级决裁者，原为1
						//simpleMap.setString("applyType", "LeaveApply");
						List affirmorlist = evServices.getEvAffirmorEvaluateAffirm(simpleMap);
						
						Iterator affiter = affirmorlist.iterator();
						for(;affiter.hasNext();){
							SimpleMap sm = (SimpleMap)affiter.next();
							simpleMap.clear();
							simpleMap.set("affirmLevel", 2);//二级为决裁表中的第一级决裁者，原为1
							simpleMap.setString("affirmorId", sm.getString("AFFIRMOR_ID"));
							simpleMap.setString("applyStr", buffer.toString());
							List applyNoList = evServices.getEvaluateApplyNoForMail(simpleMap);
							
							Iterator it = applyNoList.iterator();
							int applyNo[] = new int[applyNoList.size()] ;
							for (int i=0; it.hasNext();i++) {
								SimpleMap map1 = (SimpleMap) it.next();
								applyNo[i] = Integer.parseInt(map1.getString("APPLY_NO"));
							}
							this.sendEvaluateMail(applyNo, sm.getString("AFFIRMOR_ID"), 3);
						}
				}
				
				request.setAttribute("selectDept", parameterObject.getString("selectDept"));
				request.setAttribute("selectPosition", parameterObject.getString("selectPosition"));
				request.setAttribute("year", parameterObject.getString("year"));
				request.setAttribute("month", parameterObject.getString("month"));
				request.setAttribute("status_code", parameterObject.getString("status_code"));
				
			} catch (Exception e) {
				Logger.getLogger(getClass()).debug(e.toString());
				e.printStackTrace();
				//return "/error.jsp";
			}
	   }
	 
	 

	 /*申请的评价信息*/
	 public void applyEvaluateDetail(HttpServletRequest request,AdminBean admin){	  
		 try {
				admin = (AdminBean) request.getSession().getAttribute("admin") ;
				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				String menuCode = "ess0404";
			
				List<EvaluateInfo> applyList = new ArrayList<EvaluateInfo>() ;
//              
                String post = "";

				int flag = NumberUtil.parseInt(request.getParameter("flag"));
				String adminId = admin.getAdminID();
				
				int applySize = parameterObject.getInt("applySize");
				for (int i = 0; i < applySize; i++) {
					
					if(StringUtil.checkNull(request.getParameter("applyNo_" + i)) !=null && StringUtil.checkNull(request.getParameter("applyNo_" + i)).length()>0 )
					{
							
							EvaluateInfo ev = new EvaluateInfo();
							ev.setNo(NumberUtil.parseInt(parameterObject.getString("applyNo_" + i)));
							ev.setREMARK(StringUtil.checkNull(parameterObject.getString("remark_" + i)));
							ev.setTOTAL(NumberUtil.parseDouble(parameterObject.getString("total_" + i),0));
							ev.setITEM1(NumberUtil.parseDouble(parameterObject.getString("item1_" + i),0));
							ev.setITEM2(NumberUtil.parseDouble(parameterObject.getString("item2_" + i),0));
							ev.setITEM3(NumberUtil.parseDouble(parameterObject.getString("item3_" + i),0));
							ev.setITEM4(NumberUtil.parseDouble(parameterObject.getString("item4_" + i),0));
							ev.setITEM5(NumberUtil.parseDouble(parameterObject.getString("item5_" + i),0));
							ev.setITEM6(NumberUtil.parseDouble(parameterObject.getString("item6_" + i),0));
							ev.setITEM7(NumberUtil.parseDouble(parameterObject.getString("item7_" + i),0));
							ev.setITEM8(NumberUtil.parseDouble(parameterObject.getString("item8_" + i),0));
							ev.setITEM9(NumberUtil.parseDouble(parameterObject.getString("item9_" + i),0));
							ev.setITEM10(NumberUtil.parseDouble(parameterObject.getString("item10_" + i),0));
							ev.setITEM11(NumberUtil.parseDouble(parameterObject.getString("item11_" + i),0));
							ev.setITEM12(NumberUtil.parseDouble(parameterObject.getString("item12_" + i),0));
							ev.setITEM13(NumberUtil.parseDouble(parameterObject.getString("item13_" + i),0));
							ev.setITEM14(NumberUtil.parseDouble(parameterObject.getString("item14_" + i),0));
							ev.setITEM15(NumberUtil.parseDouble(parameterObject.getString("item15_" + i),0));
							ev.setITEM16(NumberUtil.parseDouble(parameterObject.getString("item16_" + i),0));
							ev.setITEM17(NumberUtil.parseDouble(parameterObject.getString("item17_" + i),0));
							ev.setITEM18(NumberUtil.parseDouble(parameterObject.getString("item18_" + i),0));
							ev.setITEM19(NumberUtil.parseDouble(parameterObject.getString("item19_" + i),0));
							ev.setITEM20(NumberUtil.parseDouble(parameterObject.getString("item20_" + i),0));
							ev.setITEM0(NumberUtil.parseDouble(parameterObject.getString("item0_" + i),0));
							
							parameterObject.set("yearMonth", StringUtil.checkNull(parameterObject.getString("yearMonth_" + i)));
							parameterObject.set("dept", StringUtil.checkNull(parameterObject.getString("dept_" + i)));
							post=StringUtil.checkNull(parameterObject.getString("post_" + i));
							parameterObject.set("post", StringUtil.checkNull(parameterObject.getString("post_" + i)));
							
							
							applyList.add(ev) ;		
					}
				}
				//flag 1,通过,2,否决
				
				int result = evServices.updateEvaluateDetailFlag(applyList);
				
				
//				if("C_12004_1355928".equals(post)){
//					evServices.updateEvaluateDetailSortGongren(parameterObject);//排序
//				}else{
//					evServices.updateEvaluateDetailSortNoGongren(parameterObject);
//				}
				
				
				
				
				
				
				
				
 //              SimpleMap parameterObject2 = new SimpleMap();
				
//				Iterator iterator = applyList.iterator();
				
//				while (iterator.hasNext()) {
//
//					EvaluateInfo evi = (EvaluateInfo) iterator.next();
//					//System.out.println("CK:"+evi.getCk());
////					 验证申请(只取得第一个不符和条件的信息)
//					if (evi.getCk() != null && evi.getCk().length() > 0) {
//										
//						List affirmList = new ArrayList() ;
//						
//						SimpleMap parameterObject1 = new SimpleMap();
//						parameterObject1.setString("DEPTID", evi.getDEPTID()) ;
//						parameterObject1.setString("POSTCODE", evi.getPOST_CODE());
//						
//						parameterObject2.setString("dept", evi.getDEPTID()) ;
//						parameterObject2.setString("yearMonth", evi.getMONTH());
//						parameterObject2.setString("post", evi.getPOST_CODE());
//						
//						affirmList=evServices.getEvaluateAffirmorList(parameterObject1);
//						
//						
//						if(affirmList == null || affirmList.size() == 0)
//						{
//							request.setAttribute("errorMsg", "暂无相关信息！");
//							
//						}
//						
////							
//							// 添加决裁者列表
//							evi.setAffirmorList((ArrayList)affirmList);
//
//							insertApplyList.add(evi) ;
//									
//					}
//				}
//				HashMap result = null;
//				// 插入评价申请数据
//				if (errorMsgList.isEmpty()) {
//					result = evServices.doEvaluateApplyByBatch(insertApplyList);
//					
//					String post = parameterObject2.getString("post");
//					
//					if("C_12004_1355928".equals(post)){
//						evServices.updateEvaluateDetailSortGongren(parameterObject2);//排序
//					}else{
//						evServices.updateEvaluateDetailSortNoGongren(parameterObject2);
//					}
//					
//					//errorMsgList.add(ErrorMessage.getErrorMessage("LeaveApply", result, request)) ;

					
						Iterator iter = applyList.iterator();
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);
						
						for(;iter.hasNext();){
							EvaluateInfo evaluateInfo = (EvaluateInfo)iter.next();
							buffer.append("'"+evaluateInfo.getNo()+"'");
							if(iter.hasNext()){								
								buffer.append(" , ");
							}
						}
						simpleMap.set("applyStr",buffer.toString());
						simpleMap.set("applyLevel",2);//二级为决裁表中的第一级决裁者，原为1
						//simpleMap.setString("applyType", "LeaveApply");
						List affirmorlist = evServices.getEvAffirmorEvaluateAffirm(simpleMap);
						
						Iterator affiter = affirmorlist.iterator();
						for(;affiter.hasNext();){
							SimpleMap sm = (SimpleMap)affiter.next();
							simpleMap.clear();
							simpleMap.set("affirmLevel", 2);//二级为决裁表中的第一级决裁者，原为1
							simpleMap.setString("affirmorId", sm.getString("AFFIRMOR_ID"));
							simpleMap.setString("applyStr", buffer.toString());
							List applyNoList = evServices.getEvaluateApplyNoForMail(simpleMap);
							
							Iterator it = applyNoList.iterator();
							int applyNo[] = new int[applyNoList.size()] ;
							for (int i=0; it.hasNext();i++) {
								SimpleMap map1 = (SimpleMap) it.next();
								applyNo[i] = Integer.parseInt(map1.getString("APPLY_NO"));
							}
							this.sendEvaluateMail(applyNo, sm.getString("AFFIRMOR_ID"), 3);
						}

				request.setAttribute("selectDept", parameterObject.getString("selectDept"));
				request.setAttribute("selectPosition", parameterObject.getString("selectPosition"));
				request.setAttribute("year", parameterObject.getString("year"));
				request.setAttribute("month", parameterObject.getString("month"));
				request.setAttribute("status_code", parameterObject.getString("status_code"));
				
			} catch (Exception e) {
				Logger.getLogger(getClass()).debug(e.toString());
				e.printStackTrace();
				//return "/error.jsp";
			}
	   }
	

	

}
