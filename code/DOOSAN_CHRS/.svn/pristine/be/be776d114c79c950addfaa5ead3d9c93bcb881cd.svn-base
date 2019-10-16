package com.ait.ev.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDetailBack;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.ess.base.ErrorMessage;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.business.EssServices;
import com.ait.ess.dao.EssArDAO;
import com.ait.ev.bean.EvaluateAffirmParam;
import com.ait.ev.bean.EvaluateInfo;
import com.ait.ev.business.EvaluateApplyServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (zhangji@ait.net.cn)
 * @Date 2011-9-28
 * 
 */
public class EvaluateAffirmCommand extends ArAbstractCommand {
	
	private EvaluateApplyServices evServices =null;
	
	private Mail mail ;
	
	private EssSysparam essSysparam = null;
	
	private String cpnyId;
	
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://portal.doosan.com" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	public EvaluateAffirmCommand(){
		evServices = new EvaluateApplyServices();
		
		mail = new Mail() ;
	}

	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		String returnPage=null;
		if(!content.equals("") && content.equals("evaluateAffrimView")){
			this.getEvaluateAffrimView(request, admin);
			returnPage="/ev_affirm_view.jsp";
		}
		else if(!content.equals("") && content.equals("doEvaluateAffrim")){
			this.doEvaluateAffrim(request, admin);
			this.getEvaluateAffrimView(request, admin);
			returnPage="/ev_affirm_view.jsp";
			
		}else if(!content.equals("") && content.equals("updateEvaluateAffirm")){
			this.updateEvaluateAffirm(request, admin);
			this.getEvaluateAffrimView(request, admin);
			returnPage="/ev_affirm_view.jsp";
			
		}
		
		else{
			return "error.jsp";
		}
//		
		return returnPage;
	}
	
	

	/*得到评价决裁信息*/
	 public String getEvaluateAffrimView(HttpServletRequest request,AdminBean admin){	  
		    EssArDAO essArDAO = new EssArDAO();
		    cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			String adminId = admin.getAdminID();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			//List deptList = new ArrayList();

			try {
				
				String key = StringUtil.checkNull(request.getParameter("key"));
				int qryType = Integer.parseInt(StringUtil.checkNull(request
						.getParameter("qryType"), "2"));


				parameterObject.put("key", key);
				parameterObject.put("qryType", qryType);
				parameterObject.set("ADMIN_ID", admin.getAdminID());
				parameterObject.set("cpnyId", admin.getCpnyId());
				
				
				List deptList=evServices.getEvaluateAffirmDeptList(parameterObject);
				List positionList=evServices.getEvaluateAffirmPositionList(parameterObject);
				
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
				
				
				
				
				
				
				//parameterObject.set("dept", request.getParameter("selectDept"));
				//parameterObject.set("position", request.getParameter("selectPosition"));
				//parameterObject.set("yearMonth", yearMonth);
				
				List itemList = evServices.getEvaluateItem(parameterObject.getString("yearMonth"),parameterObject.getString("dept"),parameterObject.getString("position"),parameterObject.getString("ADMIN_ID"),2);
				
				
				//System.out.println("************************"+itemName);

				/* paging logic */
				UserConfiguration config = UserConfiguration
						.getInstance("/system.properties");
				int pageSize = config.getInt("page.style6.pagesize");
				int pageGroupSize = config.getInt("page.style6.pagegroupsize");
				int currentPage = 0;
				// if has currentpage set value into currentPage
				if (request.getParameter("currentPage") != null
						&& !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request
							.getParameter("currentPage"));

				// 取得数据行数
				parameterObject.set("supervisor", adminId);
				
				int resultCount = evServices.getEvaluateAffirmListCnt(parameterObject);
				// 如果"当前页"大于最大页数,取最后一页
				if (currentPage > (resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize)
						&& resultCount != 0) {
					currentPage = resultCount % pageSize == 0 ? resultCount
							/ pageSize - 1 : resultCount / pageSize;
				}

				
				List evaluateList=evServices.getEvaluateAffirmList(parameterObject,currentPage,pageSize);
				
				
				for (int i = 0; i < evaluateList.size(); i++) {
					EvaluateInfo evi = (EvaluateInfo) evaluateList.get(i);
					//parameterObject.setInt("applyNo", leave.getLeaveNo());
					///parameterObject.setInt("level", leave.getAffirmLevel());
					
					// leave.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
					// ;
					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if ((evi.getNext_flag() == 0 && evi.getUp_flag() == 1 
					//		&& arModify.getActivity() != 2
							)
							|| evi.getMaxLevel_flag() == 1) {
						// 决裁状态为"待决裁"
						if (evi.getAffirm_flag() == 0)
							// 可进行"通过"和"否决"
							evi.setOpFlag(0);
						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& evi.getAffirm_flag() == 1)
							// 可进行"否决"
							evi.setOpFlag(2);
						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& evi.getAffirm_flag() == 2)
							// 可进行"通过"
							evi.setOpFlag(1);
					}
					evi.setAffirmorList((ArrayList) evServices.getAffirmorList(evi.getNo()));
					
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
				if (evaluateList.size() > 0) {
					statusMap.put("0", message1);
					statusMap.put("1", message2);
					statusMap.put("2", message3);

					colorMap.put("0", "#990099");
					colorMap.put("1", "#00CC00");
					colorMap.put("2", "#CC0000");
				}


				request.setAttribute("qryType", qryType);
//				request.setAttribute("deptID", deptID);
//				request.setAttribute("deptList", deptList);
				request.setAttribute("key", key);
				request.setAttribute("positionList",positionList);
				request.setAttribute("deptList",deptList);
				//request.setAttribute("selectDept", request.getParameter("selectDept"));
				//request.setAttribute("selectPosition", request.getParameter("selectPosition"));
				//request.setAttribute("year", request.getParameter("year"));
				//request.setAttribute("month", request.getParameter("month"));
				//request.setAttribute("itemName",itemName);
				request.setAttribute("itemList",itemList);
				
				request.setAttribute("statusMap", statusMap);
				request.setAttribute("colorMap", colorMap);
				request.setAttribute("evaluateList", evaluateList);

				// paging parameter
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
				if(evaluateList==null ||evaluateList.size()==0){
					 request.setAttribute("errorMsg", "暂无相关信息,请尝试其他查询条件！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).error(e.toString());
			}
		
		 return "/ev_affirm_view.jsp?menu_code="
			+ request.getParameter("menu_code");
		   
	   }
	 
	 
	 /*决裁*/
	 public void doEvaluateAffrim(HttpServletRequest request,AdminBean admin){	  
		 try {
				admin = (AdminBean) request.getSession().getAttribute("admin") ;
				SimpleMap parameterObject= new SimpleMap();
				String menuCode = "ess0405";
				
				List<EvaluateAffirmParam> pList = new ArrayList<EvaluateAffirmParam>() ;
				
				int flag = NumberUtil.parseInt(request.getParameter("flag"));
				String adminId = admin.getAdminID();
				String post = "";
			
				int applySize = NumberUtil.parseInt(request.getParameter("applySize"));
				for (int i = 0; i < applySize; i++) {
					if(StringUtil.checkNull(request.getParameter("affirmNo_" + i)) !=null && StringUtil.checkNull(request.getParameter("affirmNo_" + i)).length()>0){

							EvaluateAffirmParam v = new EvaluateAffirmParam();
							v.setAffirmNo(NumberUtil.parseInt(request.getParameter("affirmNo_" + i)));
							//int applyNo = NumberUtil.parseInt(request.getParameter("applyNo" + str_affirmNos[i]));
							v.setApplyNo(NumberUtil.parseInt(request.getParameter("applyNo_" + i)));
							v.setAdminId(admin.getAdminID());
							v.setRemark(StringUtil.toCN(request.getParameter("remark_" + i)));
							v.setTotal(NumberUtil.parseDouble(request.getParameter("total_" + i),0));
							v.setItem1(NumberUtil.parseDouble(request.getParameter("item1_" + i),0));
							v.setItem2(NumberUtil.parseDouble(request.getParameter("item2_" + i),0));
							v.setItem3(NumberUtil.parseDouble(request.getParameter("item3_" + i),0));
							v.setItem4(NumberUtil.parseDouble(request.getParameter("item4_" + i),0));
							v.setItem5(NumberUtil.parseDouble(request.getParameter("item5_" + i),0));
							v.setItem6(NumberUtil.parseDouble(request.getParameter("item6_" + i),0));
							v.setItem7(NumberUtil.parseDouble(request.getParameter("item7_" + i),0));
							v.setItem8(NumberUtil.parseDouble(request.getParameter("item8_" + i),0));
							v.setItem9(NumberUtil.parseDouble(request.getParameter("item9_" + i),0));
							v.setItem10(NumberUtil.parseDouble(request.getParameter("item10_" + i),0));
							v.setItem11(NumberUtil.parseDouble(request.getParameter("item11_" + i),0));
							v.setItem12(NumberUtil.parseDouble(request.getParameter("item12_" + i),0));
							v.setItem13(NumberUtil.parseDouble(request.getParameter("item13_" + i),0));
							v.setItem14(NumberUtil.parseDouble(request.getParameter("item14_" + i),0));
							v.setItem15(NumberUtil.parseDouble(request.getParameter("item15_" + i),0));
							v.setItem16(NumberUtil.parseDouble(request.getParameter("item16_" + i),0));
							v.setItem17(NumberUtil.parseDouble(request.getParameter("item17_" + i),0));
							v.setItem18(NumberUtil.parseDouble(request.getParameter("item18_" + i),0));
							v.setItem19(NumberUtil.parseDouble(request.getParameter("item19_" + i),0));
							v.setItem20(NumberUtil.parseDouble(request.getParameter("item20_" + i),0));
							v.setItem21(NumberUtil.parseDouble(request.getParameter("item21_" + i),0));
							v.setItem22(NumberUtil.parseDouble(request.getParameter("item22_" + i),0));
							v.setItem23(NumberUtil.parseDouble(request.getParameter("item23_" + i),0));
							v.setItem24(NumberUtil.parseDouble(request.getParameter("item24_" + i),0));
							v.setItem25(NumberUtil.parseDouble(request.getParameter("item25_" + i),0));
							v.setItem26(NumberUtil.parseDouble(request.getParameter("item26_" + i),0));
							v.setItem27(NumberUtil.parseDouble(request.getParameter("item27_" + i),0));
							v.setItem28(NumberUtil.parseDouble(request.getParameter("item28_" + i),0));
							v.setItem29(NumberUtil.parseDouble(request.getParameter("item29_" + i),0));
							v.setItem30(NumberUtil.parseDouble(request.getParameter("item30_" + i),0));
							v.setItem31(NumberUtil.parseDouble(request.getParameter("item31_" + i),0));
							v.setItem32(NumberUtil.parseDouble(request.getParameter("item32_" + i),0));
							v.setItem33(NumberUtil.parseDouble(request.getParameter("item33_" + i),0));
							v.setItem0(NumberUtil.parseDouble(request.getParameter("item0_" + i),0));
							
							
//							parameterObject.set("yearMonth", StringUtil.checkNull(request.getParameter("yearMonth_" + i)));
//							parameterObject.set("dept", StringUtil.checkNull(request.getParameter("dept_" + i)));
//							post=StringUtil.checkNull(request.getParameter("post_" + i));
//							parameterObject.set("post", StringUtil.checkNull(request.getParameter("post_" + i)));
							
		                    //System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+"   "+NumberUtil.parseInt(str_affirmNos[i])+"   "+applyNo+"   "+NumberUtil.parseInt(request.getParameter("total_" + i),0)+"   "+NumberUtil.parseInt(request.getParameter("item1_" + i),0));
							pList.add(v) ;
					
					}	
				}
				//flag 1,通过,2,否决
				
				int result = evServices.doEvaluateAffirm(pList,flag);
				
				
				
				
//				if(result > 0){//根据不同的职级进行排序
//					
//					SimpleMap simpleMap5 = new SimpleMap();
//					StringBuffer buffer5 = new StringBuffer(100);
//					Iterator iterator5 = pList.iterator();
//					for(int i= 0;iterator5.hasNext();i++){
//						
//						EvaluateAffirmParam param = (EvaluateAffirmParam)iterator5.next();
//						buffer5.append("'"+param.getApplyNo()+"',");	
//					}
//					simpleMap5.setString("applyStr", buffer5.substring(0,buffer5.length()-1));
//					simpleMap5.setString("curAffirmor", admin.getAdminID());
//					List list4 = evServices.getEvaluateDeptAndPosition(simpleMap5);
//					
//					if(list4.size()>0){
//						Iterator iterator = list4.iterator();
//						SimpleMap simpleMap2 = new SimpleMap();
//						for(;iterator.hasNext();){
//							SimpleMap map = (SimpleMap)iterator.next();
//							simpleMap2.setString("yearMonth", map.getString("EVALUATE_MONTH"));
//							simpleMap2.setString("dept", map.getString("DEPTID"));
//							simpleMap2.setString("post", map.getString("POSITION"));
//							
//							if("C_12005_1355928".equals(StringUtil.checkNull(map.getString("POSITION")))){
//								evServices.updateEvaluateDetailSortGongren(parameterObject);//排序
//							}else{
//								evServices.updateEvaluateDetailSortNoGongren(parameterObject);
//							}
//						}
//					
//						
//					}
//					
//				}
				
				if(result > 0 && essSysparam.isAutoSendMail())
				{
					if(flag == 1){
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);
						
						Iterator iterator3 = pList.iterator();
						for(int i= 0;iterator3.hasNext();i++){
							
							EvaluateAffirmParam param = (EvaluateAffirmParam)iterator3.next();
							buffer.append("'"+param.getApplyNo()+"',");	
						}
						
						simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
						simpleMap.setString("curAffirmor", admin.getAdminID());
						List list1 = evServices.getEvaluateNextAffirmor(simpleMap);
						
						if(list1.size()>0){
							Iterator iterator = list1.iterator();
							SimpleMap simpleMap2 = new SimpleMap();
							for(;iterator.hasNext();){
								SimpleMap map = (SimpleMap)iterator.next();
								simpleMap2.setString("affirmLevel", map.getString("AFFIRM_LEVEL"));
								simpleMap2.setString("affirmorId", map.getString("AFFIRMOR_ID"));
								simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
								List list2 = evServices.getEvaluateApplyNoForMail(simpleMap2);
								
								int applyNo[] = new int[list2.size()];
								Iterator iterator2 = list2.iterator();
								for(int i= 0;iterator2.hasNext();i++){
									SimpleMap map2 = (SimpleMap)iterator2.next();
									applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
								}
								this.sendEvaluateMail(applyNo, map.getString("AFFIRMOR_ID"), flag,adminId);//加了adminid参数调用不同的方法，增加决裁意见
							}
						
							
						}
					}

					else if(flag == 2){
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);
						
						Iterator iterator4 = pList.iterator();
						for(int i= 0;iterator4.hasNext();i++){
							
							EvaluateAffirmParam param = (EvaluateAffirmParam)iterator4.next();
							buffer.append("'"+param.getApplyNo()+"',");	
						}
						
						simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
						List list1 = evServices.getEvaluateApplyor(simpleMap);
						
						if(list1.size()>0){
							Iterator iterator = list1.iterator();
							SimpleMap simpleMap2 = new SimpleMap();
							for(;iterator.hasNext();){
								SimpleMap map = (SimpleMap)iterator.next();
								simpleMap2.setString("createBy", map.getString("CREATED_BY"));
								simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
								List list2 = evServices.getEvaluateApplyNoCreateBy(simpleMap2);
								
								int applyNo[] = new int[list2.size()];
								Iterator iterator2 = list2.iterator();
								for(int i= 0;iterator2.hasNext();i++){
									SimpleMap map2 = (SimpleMap)iterator2.next();
									applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
								}
								this.sendEvaluateMail(applyNo, map.getString("CREATED_BY"), flag,adminId);
							}
						}
					}
					
//---------------------------------------------------
				}

			} catch (Exception e) {
				Logger.getLogger(getClass()).debug(e.toString());
				e.printStackTrace();

			}
	   }
	 
	 /*修改已申请的评价信息*/
	 public void updateEvaluateAffirm(HttpServletRequest request,AdminBean admin){	  
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
					
					if(StringUtil.checkNull(request.getParameter("affirmNo_" + i)) !=null && StringUtil.checkNull(request.getParameter("affirmNo_" + i)).length()>0 )
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
							
							if("C_12005_1355928".equals(StringUtil.checkNull(map.getString("POSITION")))){
								evServices.updateEvaluateDetailSortGongren(simpleMap2);//排序
							}else{
								evServices.updateEvaluateDetailSortNoGongren(simpleMap2);
							}
							
							evServices.updateEvaluateDetailSortZero(simpleMap2); //把总分修改为零的信息进行名次处理
						}
					
						
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
	 
	 public void sendEvaluateMail(int[]Applyno,String setTo,int flag,String adminId)throws Exception{//比上面的方法多一个决裁意见
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("setTo", setTo);
			parameterObject.setString("adminId", adminId);
			SimpleMap result = (SimpleMap) evServices.setToEmail(parameterObject);
			String applyStr="";
			for(int i=0;i<Applyno.length;i++){
				applyStr +=("'"+Applyno[i]+"',");
			}
			parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
			List applyResult = (List) evServices.applyEvaluateResult(parameterObject);
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
				//content.append("<br>").append("决裁意见:").append(StringUtil.checkNull(simpleMap.getString("AFFIRM_REMARKS")));
				
				content.append("<br>");
				content.append("--------------------------------------------------------------------------");
				content.append("<br>");
			}
			content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>");
			  
			inputData.setString("EMAIL_TITLE", emailTitle);

			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());

			inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

			new Mail().sendMail(inputData) ;	
		}

	
	
	
	
}
